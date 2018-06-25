package com.hivescm.tms.finance.server.component.pcsign.impl;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.common.utils.Assert;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.common.exception.TmsBusinessException;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchSaveExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.framework.lock.DistributedLockService;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherGoodsEsDTO;
import com.hivescm.tms.api.dto.es.sign.*;
import com.hivescm.tms.api.dto.es.sign.request.BackWarehouseReqDTO;
import com.hivescm.tms.api.dto.es.stock.StockLockSyncDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillStockLuckEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherDetailStatusEnum;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.stock.StockLockTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.SignStockDetailEunm;
import com.hivescm.tms.api.enums.biz.waybill.WaybillDistributionTypeEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.constants.TmsCodeRuleBizCode;
import com.hivescm.tms.finance.server.component.pcsign.BackWareHouseService;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.dao.entity.sign.BackWarehouseDO;
import com.hivescm.tms.finance.server.dao.entity.sign.BackWarehouseDetailDO;
import com.hivescm.tms.finance.server.dao.entity.sign.DeliveryFailureDO;
import com.hivescm.tms.finance.server.dao.mapper.sign.BackWarehouseDetailMapper;
import com.hivescm.tms.finance.server.dao.mapper.sign.BackWarehouseMapper;
import com.hivescm.tms.finance.server.dao.mapper.sign.DeliveryFailureMapper;
import com.hivescm.tms.finance.server.feign.dispatcher.DispatcherService;
import com.hivescm.tms.finance.server.feign.stock.IStockLockService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.pcsign.BackWarehouseRecordService;
import com.hivescm.tms.finance.server.service.pcsign.SignStockService;
import com.hivescm.tms.finance.server.service.sign.EsDeliveryFailureService;
import com.hivescm.tms.finance.server.service.sign.EsSignDetailsService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;
import com.hivescm.tms.intranet.gateway.api.dto.IdCode.IdCodeReqNewDTO;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.IdCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 送货签收-返回入库服务实现
 *
 * @author zouhx
 * @date 2018年6月4日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Service
public class BackWareHouseServiceImpl implements BackWareHouseService {
    private Logger logger = LoggerFactory.getLogger(BackWareHouseServiceImpl.class);

    @Autowired
    private ESSearchService eSSearchService;
    @Autowired
    private EsSignDetailsService esSignDetailsService;
    @Autowired
    private IGeneratedIdService generatedIdService;
    @Autowired
    private BackWarehouseMapper backWarehouseMapper;
    @Autowired
    private BackWarehouseDetailMapper backWarehouseDetailMapper;
    @Autowired
    private BackWarehouseRecordService backWarehouseRecordService;

    @Autowired
    private IdCodeService idCodeService;
    @Autowired
    private DispatcherService dispatcherService;
    @Autowired
    private EsSignService esSignService;
    @Autowired
    private WaybillService waybillService;
    @Autowired
    private DistributedLockService lockService;
    @Autowired
    private EsDeliveryFailureService esDeliveryFailureService;
    @Autowired
    private DeliveryFailureMapper deliveryFailureMapper;
    @Autowired
    private IStockLockService iStockLockService;
    @Autowired
    private SignStockService signStockService;
    //todo 做成 单例的
    private static Executor threadPool = Executors.newSingleThreadExecutor();

    /**
     * 返回入库对外接口服务
     *
     * @param backWarehouseReqDTO
     */
    @Override
    public Boolean addBackWareHouse(BackWarehouseReqDTO backWarehouseReqDTO) {

        //handleExAndRollBack(backWarehouseReqDTO,new ExceptionFactory.ex(ExceptionCodeConstants.BACK_HOUSE_DISPATCHER_FAILED, e, "返库同步派车单信息失败"),);
        //分布式锁拦重复提交
//		Boolean getLock = lockService.lock(IdempotencyConstants.BIZ_TMS_ADD_WAREHOUST_RECOMMIT + backWarehouseReqDTO.getDipatcherDetailId());
        // 锁定运单防止重复操作
        Boolean getLock = lockStock(backWarehouseReqDTO.getCurentOrgId().longValue(), backWarehouseReqDTO.getWaybillId());
        if (!getLock) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_WARE_HOUSE, "该运单正被其他用户操作");
        }
        //Boolean updateDispatcherDetailByIdResult = null;         //派车单详情
        //Boolean judgeUpdateWaybillDistributionTypeResult = null; //运单送货方式
        //Boolean  backWarehouseDetailsFlag = null; //入库是否成功
        Long backWarehouseId = null;
        boolean flag = false;
        try {

            //1. >> 数据合法性验证
            checkDataWhenAddBackWareHouse(backWarehouseReqDTO);
            //2. >> 查询签收单和派车单，验证是否符合返库条件；调用派车单返库接口，查询是否已经返库过。
            checkBackConditionWhenAddBackWareHouse(backWarehouseReqDTO);
            //3. >> 返库后自动生成一条入库批次，入库类型为“返回入库”,添加反库主表和详情表
            backWarehouseId = createBackWarehouseWhenAddBackWareHouse(backWarehouseReqDTO); //返回反库id
            //4. >> 返回后派送方式可以选择送货、自提，默认为“送货”，若选择“自提”，则修改原运单的“派送方式”字段。
            addBackWareHouseOuterWaybillNotice(backWarehouseReqDTO);
            //judgeUpdateWaybillDistributionTypeResult = judgeUpdateWaybillDistributionTypeWhenAddBackWareHouse(backWarehouseReqDTO);
            //5. >> 在执行返回入库后，修改派车单字段“是否返库”标记为“是”
            AddBackWareHouseOuterDispatcheNotice(backWarehouseReqDTO);
            //updateDispatcherDetailByIdResult = updateDispatcherDetailByIdWhenAddBackWareHouse(backWarehouseReqDTO);
            //7、如果派送失败，更改-派送失败记录表，反库状态：是
            updateDispatcherFailRecord(backWarehouseReqDTO.getDipatcherDetailId());
            //8、添加反库修改库存通知
            AddBackWareHouseStockNotice(backWarehouseReqDTO);
            flag = true;
        } catch (TmsBusinessException e) {
            iStockLockService.unlock(backWarehouseReqDTO.getCurentOrgId().longValue(), backWarehouseReqDTO.getWaybillId());
            //lockService.unlock(IdempotencyConstants.BIZ_TMS_ADD_WAREHOUST_RECOMMIT + backWarehouseReqDTO.getDipatcherDetailId());
            handleExAndRollBack(backWarehouseReqDTO, e, backWarehouseId);
        } catch (Exception e) {
            iStockLockService.unlock(backWarehouseReqDTO.getCurentOrgId().longValue(), backWarehouseReqDTO.getWaybillId());
            //根据异常回滚数据
            e.printStackTrace();
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_WARE_HOUSE, e, "反库失败");
        }
        return flag;
        //iStockLockService.unlock(backWarehouseReqDTO.getCurentOrgId().longValue(),backWarehouseReqDTO.getWaybillId());
        //return updateDispatcherDetailByIdResult && judgeUpdateWaybillDistributionTypeResult && backWarehouseDetailsFlag;
    }

    /**
     * 返回入库-处理异常 & 回滚
     *
     * @param backWarehouseReqDTO
     * @param e
     * @return
     */
    private void handleExAndRollBack(BackWarehouseReqDTO backWarehouseReqDTO, TmsBusinessException e, Long backWarehouseId) {
        //返回入库删除es，删除db
        Long signId = backWarehouseReqDTO.getSignId();
        //删除返回入库es
        if (backWarehouseId != null) {
            //删除反库主表es,db
            backWarehouseRecordService.deleteByBackWarehouseId(backWarehouseId);
            backWarehouseMapper.deleteByPrimaryKey(backWarehouseReqDTO.getCompanyId(), backWarehouseId);
            //删除反库详情es,db
            backWarehouseRecordService.deleteDetailsByBackWarehouseId(backWarehouseId);
        }
        //删除db
        int errorCode = e.getErrorCode();
        if (errorCode == ExceptionCodeConstants.BACK_HOUSE_WAYBILL_FAILED) {
            e.printStackTrace();
            throw ExceptionFactory.ex(ExceptionCodeConstants.BACK_HOUSE_WAYBILL_FAILED, e, "返库同步信息信息失败");
        } else if (errorCode == ExceptionCodeConstants.BACK_HOUSE_DISPATCHER_FAILED) {
            //恢复运单信息
            if (backWarehouseReqDTO.getBackDeliveryType() != null && backWarehouseReqDTO.getBackDeliveryType() == WaybillDistributionTypeEnum.GET.getType()) {
                WaybillEsDTO waybillEsDTO = waybillService.queryWaybillEsDTO(backWarehouseReqDTO.getWaybillId());
                waybillEsDTO.setDistributionType(WaybillDistributionTypeEnum.DELIVERY.getType());
                List<WaybillEsDTO> waybill = new ArrayList<>();
                waybill.add(waybillEsDTO);
                waybillService.updateWaybillStatus(waybill);
            }
            e.printStackTrace();
            throw ExceptionFactory.ex(ExceptionCodeConstants.BACK_HOUSE_DISPATCHER_FAILED, e, "返库同步派车单信息失败");
        } else {
            e.printStackTrace();
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_WARE_HOUSE, e, "返库失败");
        }
    }

    /**
     * 修改原运单派送字段为自提
     * 2018-06-04
     * 调用addBackWareHouse方法时，返回后派送方式可以选择送货、自提，默认为“送货”，若选择“自提”，则修改原运单的“派送方式”字段
     *
     * @param backWarehouseReqDTO
     */
    private Boolean addBackWareHouseOuterWaybillNotice(BackWarehouseReqDTO backWarehouseReqDTO) {
        //1:修改运单
        Boolean updateWaybillStatus = true;
        if (backWarehouseReqDTO.getBackDeliveryType() != null && backWarehouseReqDTO.getBackDeliveryType() == WaybillDistributionTypeEnum.GET.getType()) {
            //改为自提签收,需要修改运单状态,新派送方式选择自提修改原运单配送方式
            try {
                //查询原运单es对象
                WaybillEsDTO oldWaybillEsDTO = waybillService.queryWaybillEsDTO(backWarehouseReqDTO.getWaybillId());
                if (oldWaybillEsDTO == null || oldWaybillEsDTO.getId() == null) {
                    throw ExceptionFactory.ex(ExceptionCodeConstants.BACK_HOUSE_WAYBILL_FAILED, "根据运单ID：" + backWarehouseReqDTO.getWaybillId() + "查询不到相应运单信息！");
                }
                //设置运单派送方式为自提
                oldWaybillEsDTO.setDistributionType(WaybillDistributionTypeEnum.GET.getType());
                List<WaybillEsDTO> waybill = new ArrayList<>();
                waybill.add(oldWaybillEsDTO);
                updateWaybillStatus = waybillService.updateWaybillStatus(waybill);
                if (!updateWaybillStatus) {
                    throw ExceptionFactory.ex(ExceptionCodeConstants.BACK_HOUSE_WAYBILL_FAILED, "修改原运单的“派送方式”为“自提”失败!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw ExceptionFactory.ex(ExceptionCodeConstants.BACK_HOUSE_WAYBILL_FAILED, e, "修改原运单的“派送方式”为“自提”失败!");
            }
        }
        return updateWaybillStatus;
    }


    /**
     * 修改派车单返库标记为"是"
     * 调用addBackWareHouse方法时,进行修改派车单字段“是否返库”标记为“是”
     * 2018年-06-04
     *
     * @param backWarehouseReqDTO
     */
    private void AddBackWareHouseOuterDispatcheNotice(BackWarehouseReqDTO backWarehouseReqDTO) {
        DispatcherDetailEsDTO updateDispatcherDetail = new DispatcherDetailEsDTO();
        updateDispatcherDetail.setId(backWarehouseReqDTO.getDipatcherDetailId());//该派车单明细Id应该与签收单上派车单明细id一致
        updateDispatcherDetail.setCompanyId(backWarehouseReqDTO.getCompanyId());
        updateDispatcherDetail.setStorage(true); //执行返回入库后，修改派车单字段“是否返库”标记为“是”。
        updateDispatcherDetail.setDetailStatus(DispatcherDetailStatusEnum.COMPLETE.getType());//修改派车单中每条运单明细的处理状态为“已完成”。1已完成 2未完成
        Boolean updateDispatcherDetailByIdResult = false;
        try {
            updateDispatcherDetailByIdResult = dispatcherService.updateDispatcherDetailById(updateDispatcherDetail);
            if (!updateDispatcherDetailByIdResult) {
                throw ExceptionFactory.ex(ExceptionCodeConstants.BACK_HOUSE_DISPATCHER_FAILED, "修改派车单明细“是否返库”标记为“是”失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw ExceptionFactory.ex(ExceptionCodeConstants.BACK_HOUSE_DISPATCHER_FAILED, e, "调用派车单接口(updateDispatcherDetailById)失败：" + e.getMessage());
        }

    }


    /**
     * 锁定库存
     *
     * @param orgId
     * @param waybillId
     * @return
     */
    private Boolean lockStock(Long orgId, Long waybillId) {
        DataResult<Boolean> lock = iStockLockService.lock(orgId, waybillId);
        if (lock.getResult() == null || !lock.isSuccess() || !lock.getResult()) {
            throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_LOCK, "该运单正被其他用户操作");
        }
        return lock.isSuccess();

    }

    /**
     * 调用addBackWareHouse方法时，进行数据合法性验证
     *
     * @param backWarehouseReqDTO
     */
    private void checkDataWhenAddBackWareHouse(BackWarehouseReqDTO backWarehouseReqDTO) {
        Assert.isNotNull(backWarehouseReqDTO.getCurentOrgId(), "当前网点Id不可为空！");
        Assert.isNotNull(backWarehouseReqDTO.getDipatcherDetailId(), "派车单明细id不可为空！");
        Assert.isNotNull(backWarehouseReqDTO.getBackType(), "返库类型不可为空！");//值为：1部分签收、2拒签、3派送失败、4取消签收。
        Assert.isNotNull(backWarehouseReqDTO.getStorageTime(), "入库时间不可为空！");//不编辑默认当前系统时间
        Assert.isNotNull(backWarehouseReqDTO.getStorageNameId(), "仓库名称id不可为空！");
        Assert.isNotNull(backWarehouseReqDTO.getWaybillId(), "运单id不可为空！");
        Assert.isNotNull(backWarehouseReqDTO.getDeliveriesNumber(), "送货件数不可为空！");
        Assert.isNotNull(backWarehouseReqDTO.getStorageNumber(), "入库件数不可为空！");
        //Assert.isNotNull(backWarehouseReqDTO.getUpdateUser(),"操作人不可为空！");
        if (backWarehouseReqDTO.getBackType() != SignStockDetailEunm.DELIVERYFAILED.getType()) {//未被签过
            Assert.isNotNull(backWarehouseReqDTO.getSignId(), "签收单id不可为空！");
        }
    }

    /**
     * 检查查询签收单和派车单，验证是否符合返库条件
     *
     * @param backWarehouseReqDTO
     */
    private void checkBackConditionWhenAddBackWareHouse(BackWarehouseReqDTO backWarehouseReqDTO) {
        SignEsDTO oldSignEsDTO = null;
        DispatcherDetailEsDTO dispatcherDetailEsDTO = null;
        try {
            if (backWarehouseReqDTO.getBackType() != SignStockDetailEunm.DELIVERYFAILED.getType()) {
                oldSignEsDTO = esSignService.querySignEsDTOById(backWarehouseReqDTO.getSignId());
                if (oldSignEsDTO == null) {
                    throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN, "签收单不存在");
                }
            }
            dispatcherDetailEsDTO = dispatcherService.queryDispatcherDetailById(backWarehouseReqDTO.getDipatcherDetailId());
        } catch (Exception e) {
            e.printStackTrace();
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN, e.getMessage());
        }
        if (dispatcherDetailEsDTO == null) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN, "根据ID：" + backWarehouseReqDTO.getDipatcherDetailId() + "未查询到派车单明细信息！");
        }
        //签收状态。SignStockDetailEunm//NO_SIGN(0, "未签收"),SIGNED(1, "全部签收"),PARTIAL_SIGN(2, "部分签收"),REFUSE_SIGN(3, "全部拒签"),CANCEL_SIGN(4, "作废");
        Integer signStatus = dispatcherDetailEsDTO.getSignStatus();
        if ((backWarehouseReqDTO.getBackType() != SignStockDetailEunm.DELIVERYFAILED.getType()) && !(signStatus == SignStatusEnum.PARTIAL_SIGN.getType() || signStatus == SignStatusEnum.REFUSE_SIGN.getType() || (oldSignEsDTO.getIdeliveryFailure() != null && oldSignEsDTO.getIdeliveryFailure()))) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN, "只有“签收类型”为“全部拒签”、“部分签收”的运单或者“派送失败”标记为“是”的运单记录才能执行返回入库操作!");
        }
        // >> 调用返库接口，查询是否已经返库过。
        DispatcherDetailEsDTO isExistDispatcherDetailEsDTO = dispatcherService.queryDispatcherDetailById(backWarehouseReqDTO.getDipatcherDetailId());
        if (isExistDispatcherDetailEsDTO != null && isExistDispatcherDetailEsDTO.getStorage() != null && isExistDispatcherDetailEsDTO.getStorage()) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN, "该派车单明细(" + backWarehouseReqDTO.getDipatcherDetailId() + ")已经执行过返库，不可再次返库！");
        }
    }
    /**
     * 修改派车单返库标记为"是"
     * 调用addBackWareHouse方法时,进行修改派车单字段“是否返库”标记为“是”
     * 2018年-06-04
     * @param backWarehouseReqDTO
    private Boolean updateDispatcherDetailByIdWhenAddBackWareHouse(BackWarehouseReqDTO backWarehouseReqDTO){
    DispatcherDetailEsDTO updateDispatcherDetail = new DispatcherDetailEsDTO();
    updateDispatcherDetail.setId(backWarehouseReqDTO.getDipatcherDetailId());//该派车单明细Id应该与签收单上派车单明细id一致
    updateDispatcherDetail.setCompanyId(backWarehouseReqDTO.getCompanyId());
    updateDispatcherDetail.setStorage(true); //执行返回入库后，修改派车单字段“是否返库”标记为“是”。
    updateDispatcherDetail.setDetailStatus(DispatcherDetailStatusEnum.COMPLETE.getType());//修改派车单中每条运单明细的处理状态为“已完成”。1已完成 2未完成
    Boolean updateDispatcherDetailByIdResult = false;
    try {
    updateDispatcherDetailByIdResult = dispatcherService.updateDispatcherDetailById(updateDispatcherDetail);

    } catch (Exception e) {
    e.printStackTrace();
    throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN, "调用派车单接口(updateDispatcherDetailById)失败：" + e.getMessage());
    }
    if(!updateDispatcherDetailByIdResult){
    throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN, "修改派车单明细“是否返库”标记为“是”失败!");
    }

    //更改-派送失败记录表，反库状态：是
    //updateDispatcherFailRecord(backWarehouseReqDTO.getDipatcherDetailId());

    return updateDispatcherDetailByIdResult;
    }*/

    /**
     * 修改派送失败记录，反库状态为:派送失败才执行此操作
     * 2018-06-04
     */
    private void updateDispatcherFailRecord(Long dipatcherDetailId) {
        threadPool.execute(() -> {
            DeliveryFailureEsDTO deliveryFailureEsDTO = esDeliveryFailureService.queryByDetailId(dipatcherDetailId);
            if (deliveryFailureEsDTO == null) {
                logger.error("update DispatcherFail Record fail! no record exist!! dipatcherDetailId :" + dipatcherDetailId);
                return;
            }
            deliveryFailureEsDTO.setStorage(true);
            DeliveryFailureDO deliveryFailureDO = EntityUtils.copyProperties(deliveryFailureEsDTO, DeliveryFailureDO.class);
            Integer dbInsertDeliveryFailureRes = deliveryFailureMapper.updateByPrimaryKey(deliveryFailureDO);
            //添加es
            Boolean esInsertDeliveryFailureRes = esDeliveryFailureService.updateDeliveryFailureEsDTO(deliveryFailureEsDTO);
        });
    }

    /**
     * 修改原运单派送字段为自提
     * 2018-06-04
     * 调用addBackWareHouse方法时，返回后派送方式可以选择送货、自提，默认为“送货”，若选择“自提”，则修改原运单的“派送方式”字段
     * @param backWarehouseReqDTO
     */
	/*private Boolean judgeUpdateWaybillDistributionTypeWhenAddBackWareHouse(BackWarehouseReqDTO backWarehouseReqDTO){
		Boolean updateWaybillStatus = true;
		// 2自提签收; 1送货签收
		if(backWarehouseReqDTO.getBackDeliveryType()!=null&&backWarehouseReqDTO.getBackDeliveryType() == WaybillDistributionTypeEnum.GET.getType()){
			//新派送方式选择自提修改原运单配送方式
			WaybillEsDTO oldWaybillEsDTO = new WaybillEsDTO();
			try {
				//查询原运单es对象
				oldWaybillEsDTO = waybillService.queryWaybillEsDTO(backWarehouseReqDTO.getWaybillId());
			} catch (Exception e) {
				e.printStackTrace();
				throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN, "根据运单ID：" + backWarehouseReqDTO.getWaybillId() + "查询运单信息失败: "+e.getMessage());
			}
			if(oldWaybillEsDTO == null || oldWaybillEsDTO.getId() == null ){
				throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN, "根据运单ID：" + backWarehouseReqDTO.getWaybillId() + "查询不到相应运单信息！");
			}
			//设置运单派送方式为自提
			oldWaybillEsDTO.setDistributionType(WaybillDistributionTypeEnum.GET.getType());
			List<WaybillEsDTO> waybill = new ArrayList<>();
			waybill.add(oldWaybillEsDTO);
			try {
				updateWaybillStatus = waybillService.updateWaybillStatus(waybill);
			} catch (Exception e) {
				e.printStackTrace();
				throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN, "调用运单接口（updateWaybillStatus）异常：" + e.getMessage());
			}
			if(!updateWaybillStatus){
				throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN, "修改原运单的“派送方式”为“自提”失败!");
			}
		}
		return updateWaybillStatus;
	}*/

    /**
     * 调用addBackWareHouse方法时，返库后自动生成一条入库批次(主表+详情的es和db 都添加)，入库类型为“返回入库”
     *
     * @param backWarehouseReqDTO
     * @return
     */
    private Long createBackWarehouseWhenAddBackWareHouse(BackWarehouseReqDTO backWarehouseReqDTO) {
        Long backWarehouseId;
        try {
            //->保存反库主表
            backWarehouseId = saveBackWarehouse(backWarehouseReqDTO); //返回入库ID
            //->保存反库明细表
            //backWarehouseDetailsFlag = saveBackWarehouseDetails (backWarehouseReqDTO,backWarehouseId);
            saveBackWarehouseDetails(backWarehouseReqDTO, backWarehouseId);

        } catch (Exception e) {
            e.printStackTrace();
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_BACK_HOUSE, e, e.getMessage());
        }
        return backWarehouseId;
    }

    /**
     * 调用返库接口，查询是否已经返库过。
     * 部分签收/拒签/派送失败，执行返库操作。
     * 针对部分签收/拒签 和 派送失败，对于返库的数据来源有所不同。
     *
     * @param backWarehouseReqDTO
     * @return
     */
    private Boolean AddBackWareHouseStockNotice(BackWarehouseReqDTO backWarehouseReqDTO) {
        DataResult<Integer> signStockDetailResult = null;
        // 1 封装返库参数
        List<WaybillStockLuckEsDTO> stockLuckEsDTOs = new ArrayList<>();
        if (backWarehouseReqDTO.getBackType() == SignStockDetailEunm.DELIVERYFAILED.getType()) {
            //3派送失败
            //根据派车单明细Id查询出所有的该派车单货物明细信息
            List<DispatcherGoodsEsDTO> dispatcherGoodsEsDTOList = dispatcherService.getDispatcherGoodsByDetailId(backWarehouseReqDTO.getDipatcherDetailId());
            if (dispatcherGoodsEsDTOList != null && dispatcherGoodsEsDTOList.size() > 0) {
                for (DispatcherGoodsEsDTO one : dispatcherGoodsEsDTOList) {
                    if (one.getPackageNum() != null && one.getPackageNum() > 0) {
                        //封装运单库存需要参数
                        WaybillStockLuckEsDTO oneWaybillStockLuckEsDTO = new WaybillStockLuckEsDTO();
                        oneWaybillStockLuckEsDTO.setStockDetailId(one.getWaybillStockDetailId());//运单库存明细id
                        oneWaybillStockLuckEsDTO.setSignStatus(backWarehouseReqDTO.getBackType());//返库类型：值为：1部分签收、2拒签、3派送失败、4取消签收。
                        oneWaybillStockLuckEsDTO.setLockNum(one.getPackageNum());//派送失败,返库件数就是派送件数
                        oneWaybillStockLuckEsDTO.setCompanyId(one.getCompanyId());
                        oneWaybillStockLuckEsDTO.setBranchId(backWarehouseReqDTO.getCurentOrgId());//当前网点
//						oneWaybillStockLuckEsDTO.setStockName(backWarehouseReqDTO.getStorageNameId());//仓库名称

                        oneWaybillStockLuckEsDTO.setHouseId(backWarehouseReqDTO.getStorageNameId());
                        oneWaybillStockLuckEsDTO.setHouseName(backWarehouseReqDTO.getStorageName());
                        oneWaybillStockLuckEsDTO.setCode(backWarehouseReqDTO.getWaybillCode());
                        oneWaybillStockLuckEsDTO.setWaybillId(backWarehouseReqDTO.getWaybillId());
                        oneWaybillStockLuckEsDTO.setStockLockType(StockLockTypeEnum.DISPATCH_FAIL);

                        stockLuckEsDTOs.add(oneWaybillStockLuckEsDTO);
                    }
                }
            } else {
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN, "根据派车单明细Id：" + backWarehouseReqDTO.getDipatcherDetailId() + "查询该派车单货物明细信息失败！");
            }
        } else if (backWarehouseReqDTO.getBackType() == SignStockDetailEunm.SIGNNEDPARTIALLY.getType() || backWarehouseReqDTO.getBackType() == SignStockDetailEunm.SIGNREFUSED.getType()) {
            //1部分签收  2 全部拒签
            //根据签收单号查询该签收单的所有签收明细信息
            List<Long> signIds = Arrays.asList(backWarehouseReqDTO.getSignId());
            List<SignDetailsEsDTO> oldSignDetailEsDTOList = esSignDetailsService.queryBySignIds(signIds);
            SignEsDTO signEsDTO = esSignService.querySignEsDTOById(backWarehouseReqDTO.getSignId());
            if (oldSignDetailEsDTOList != null && oldSignDetailEsDTOList.size() > 0) {
                for (SignDetailsEsDTO one : oldSignDetailEsDTOList) {
                    if (one.getRefuseNumber() != null && one.getRefuseNumber() > 0) {
                        WaybillStockLuckEsDTO oneWaybillStockLuckEsDTO = new WaybillStockLuckEsDTO();
                        oneWaybillStockLuckEsDTO.setStockDetailId(one.getWaybillStockDetailId());//运单库存明细id
                        oneWaybillStockLuckEsDTO.setSignStatus(backWarehouseReqDTO.getBackType());//返库类型：值为：1部分签收、2拒签、3派送失败、4取消签收。
                        oneWaybillStockLuckEsDTO.setRefuseSignlockNum(one.getRefuseNumber()); //拒签锁定库存数量(全部拒签和部分签收的就是拒签件数)
                        oneWaybillStockLuckEsDTO.setCompanyId(one.getCompanyId());
                        oneWaybillStockLuckEsDTO.setBranchId(backWarehouseReqDTO.getCurentOrgId());   //当前网点
//						oneWaybillStockLuckEsDTO.setStockName(backWarehouseReqDTO.getStorageNameId());//仓库名称
                        oneWaybillStockLuckEsDTO.setHouseId(backWarehouseReqDTO.getStorageNameId());
                        oneWaybillStockLuckEsDTO.setHouseName(backWarehouseReqDTO.getStorageName());
                        oneWaybillStockLuckEsDTO.setCode(backWarehouseReqDTO.getWaybillCode());
                        oneWaybillStockLuckEsDTO.setWaybillId(backWarehouseReqDTO.getWaybillId());
                        oneWaybillStockLuckEsDTO.setStockLockType(StockLockTypeEnum.DELIVERY_SIGN);
//						oneWaybillStockLuckEsDTO.setLockNum(signEsDTO.getSignNumber());
                        oneWaybillStockLuckEsDTO.setLockNum(signEsDTO.getSignNumber());
                        oneWaybillStockLuckEsDTO.setSignType(signEsDTO.getSignType());
                        stockLuckEsDTOs.add(oneWaybillStockLuckEsDTO);
                    }
                }
            } else {
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_BACK_HOUSE, "根据签收单号：" + backWarehouseReqDTO.getSignId() + "查询该签收单的所有签收明细信息失败！");
            }
        }
        //2、调用库存接口同步库存
        try {
            signStockDetailResult = syncBackWarehouseStockAdapt(stockLuckEsDTOs);
        } catch (Exception e) {
            e.printStackTrace();
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_BACK_HOUSE, e, "调用返库接口，同步库存操作异常！");
        }
        if (signStockDetailResult.getResult() == null || signStockDetailResult.getStatus().getStatusCode() != 0) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_BACK_HOUSE, "调用返库接口，同步库存操作失败:" + signStockDetailResult.getStatus().getStatusReason());
        }
        return signStockDetailResult.getResult() != null && signStockDetailResult.getResult() > 0;
    }

    /**
     * 同步库存 适配
     * 库存模块做了重构
     *
     * @param stockLuckEsDTOs
     * @return
     */
    private DataResult<Integer> syncBackWarehouseStockAdapt(List<WaybillStockLuckEsDTO> stockLuckEsDTOs) {
//		return waybillService.signStockDetail(stockLuckEsDTOs);

        ArrayList<StockLockSyncDTO> syncDTOS = new ArrayList<>();
        stockLuckEsDTOs.forEach(e -> {
            StockLockSyncDTO stockLockSyncDTO = new StockLockSyncDTO();

            stockLockSyncDTO.setStockLockType(e.getStockLockType());
            stockLockSyncDTO.setBranchId(e.getBranchId());
            stockLockSyncDTO.setCompanyId(e.getCompanyId());
            stockLockSyncDTO.setStockDetailId(e.getStockDetailId());
            stockLockSyncDTO.setWaybillId(e.getWaybillId());
            stockLockSyncDTO.setCode(e.getCode());
            stockLockSyncDTO.setHouseId(e.getHouseId());
            stockLockSyncDTO.setHouseName(e.getHouseName());
            stockLockSyncDTO.setHouseType(e.getHouseType());

            if (e.getSignStatus().intValue() == 3) {
                stockLockSyncDTO.setPackageNum(e.getLockNum());
            } else {
                stockLockSyncDTO.setSignNum(e.getLockNum());
                stockLockSyncDTO.setRefusalNum(e.getRefuseSignlockNum());
            }
            syncDTOS.add(stockLockSyncDTO);
        });
        signStockService.sendMq(syncDTOS);
        return DataResult.success(1, Integer.class);
    }

    /**
     * 保存返回入库详情表
     * 2018-06-04
     *
     * @param backWarehouseReqDTO
     * @param backWarehouseId
     * @return
     * @throws Exception
     */
    private Boolean saveBackWarehouseDetails(BackWarehouseReqDTO backWarehouseReqDTO, Long backWarehouseId) throws Exception {
        //判断每个明细是否有拒签
        List<BackWarehouseDetailDO> listDO = new ArrayList<>();
        List<BackWarehouseDetailEsDTO> listEsDTO = new ArrayList<>();
        Boolean flag = false;
        try {
            if (backWarehouseReqDTO.getBackType().intValue() != SignStockDetailEunm.DELIVERYFAILED.getType()) {
                //如果不是派送失败,就有签收单
                //反库类型为：1,部分签收 2,拒签, 4,取消签收，直接从签收明细表取商品详情
                List<SignDetailsEsDTO> singDetails = esSignService.querySignDetailBySignId(backWarehouseReqDTO.getSignId());
                if (null != singDetails && singDetails.size() > 0) {
                    for (SignDetailsEsDTO item : singDetails) {
                        if (0 != item.getRefuseNumber()) {
                            BackWarehouseDetailDO details = new BackWarehouseDetailDO();
                            Long id = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_BACK_WAREHOUSE);
                            details.setId(id);
                            details.setBackWarehouseId(backWarehouseId);
                            details.setCompanyId(item.getCompanyId());
                            details.setGoodsName(item.getGoodsName());
                            details.setVolume(item.getVolume());
                            details.setWeight(item.getWeight());
                            details.setPackageNum(item.getRefuseNumber());
                            details.setPackages(item.getPackages());
                            details.setWaybillId(item.getWaybillId());
                            listDO.add(details);
                            //this.setCreateParameter(details, GeneratedIdConstants.TMS_BACK_WAREHOUSE_DETAIL);
                            BackWarehouseDetailEsDTO esDto = EntityUtils.copyProperties(details, BackWarehouseDetailEsDTO.class);
                            listEsDTO.add(esDto);
                        }
                    }
                    try {
                        backWarehouseDetailMapper.insertBatch(singDetails.get(0).getCompanyId(), listDO);
                        flag = new DefaultAbstractSearchSaveExecutor<BackWarehouseDetailEsDTO>(eSSearchService) {
                            @Override
                            public EsConfig getConfig() {
                                return EsConfig.backWarehouseDetail();
                            }
                        }.execute(listEsDTO);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_BACK_HOUSE, e, "保存返回入库明细表失败:" + e.getMessage());
                    }
                }
            } else {
                //派送失败没有签收单,将派车的货物全部反库
                //反库类型为: 3,派送失败
                List<DispatcherGoodsEsDTO> dispatcherGoods = dispatcherService.getDispatcherGoodsByDetailId(backWarehouseReqDTO.getDipatcherDetailId());
                if (null != dispatcherGoods && dispatcherGoods.size() > 0) {
                    for (DispatcherGoodsEsDTO item : dispatcherGoods) {
                        BackWarehouseDetailDO details = new BackWarehouseDetailDO();
                        Long id = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_BACK_WAREHOUSE);
                        details.setId(id);
                        details.setBackWarehouseId(backWarehouseId);
                        details.setCompanyId(item.getCompanyId());
                        details.setGoodsName(item.getGoodsName());
                        details.setVolume(item.getVolume());
                        details.setWeight(item.getWeight());
                        details.setPackageNum(item.getPackageNum());
                        details.setPackages(item.getPackages());
                        details.setWaybillId(item.getWaybillId());
                        listDO.add(details);
                        //this.setCreateParameter(details, GeneratedIdConstants.TMS_BACK_WAREHOUSE_DETAIL);
                        BackWarehouseDetailEsDTO esDto = EntityUtils.copyProperties(details, BackWarehouseDetailEsDTO.class);
                        listEsDTO.add(esDto);
                    }
                    try {
                        backWarehouseDetailMapper.insertBatch(dispatcherGoods.get(0).getCompanyId(), listDO);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_BACK_HOUSE, e, "保存返回入库明细表失败:" + e.getMessage());
                    }
                    flag = new DefaultAbstractSearchSaveExecutor<BackWarehouseDetailEsDTO>(eSSearchService) {
                        @Override
                        public EsConfig getConfig() {
                            return EsConfig.backWarehouseDetail();
                        }
                    }.execute(listEsDTO);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_BACK_HOUSE, e, "保存返回入库明细表失败:" + e.getMessage());
        }
        return flag;
    }

    /**
     * 查询签收明细表
     *
     * @param signId
     * @return 2018-06-04
     */
 /*   private List<SignDetailsEsDTO> getSignDetail(Long signId) {
        List<SearchCondition> scs = SearchConditionUtils.start()
                .addCondition("signId", signId.toString(), ConditionExpressionEnum.EQUAL)
                .end();
        List<SignDetailsEsDTO> singDetails = new DefaultAbstractSearchQueryExecutor<SignDetailsEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.signGoodsDetail();
            }
        }.list(scs);
        return singDetails;
    }*/

    /**
     * 保存返库主表信息
     *
     * @param backWarehouseReqDTO
     * @date 2018-06-04
     */
    private Long saveBackWarehouse(BackWarehouseReqDTO backWarehouseReqDTO) throws Exception {

        Long id = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_BACK_WAREHOUSE);

        try {
            BackWarehouseEsDTO esDTO = EntityUtils.copyProperties(backWarehouseReqDTO, BackWarehouseEsDTO.class);
            esDTO.setId(id);
            esDTO.setBackType(backWarehouseReqDTO.getBackType());
            esDTO.setIdelete(false);

            IdCodeReqNewDTO idCodeReqNewDTO = new IdCodeReqNewDTO();
            idCodeReqNewDTO.setGroupId(backWarehouseReqDTO.getGroupId());
            //idCodeReqNewDTO.setOrgId(backWarehouseReqDTO.getCurentOrgId());
            idCodeReqNewDTO.setBizCode(TmsCodeRuleBizCode.TMS_NEW_RK);

            //->生成入库批次号
            DataResult<String> storageBatchCodeResult = idCodeService.generatedNew(idCodeReqNewDTO);
            String storageBatchCode = storageBatchCodeResult.getResult();
            esDTO.setStorageBatchCode(storageBatchCode);

            //冗余一些运单信息 2018-05-02 秦川
            setWaybillInfo(esDTO);
            setDispatcherInfo(esDTO);
            BackWarehouseDO backWarehouse = EntityUtils.copyProperties(esDTO, BackWarehouseDO.class);
            backWarehouse.setStorageId(backWarehouseReqDTO.getStorageNameId()); //设置仓库ID
            //这个字段是冗余字段 没必要存数据库
            backWarehouse.setGoodsName(null);
            //-> 保存反库主表
            if (backWarehouseMapper.insertSelective(backWarehouse) != 1) {
                throw new RuntimeException("保存返回入库db主表失败");
            }
            //->保存es
            Boolean flag = new DefaultAbstractSearchSaveExecutor<BackWarehouseEsDTO>(eSSearchService) {
                @Override
                public EsConfig getConfig() {
                    return EsConfig.backWarehouse();
                }
            }.execute(esDTO);
            if (!flag) {
                throw new RuntimeException("保存返回入库主表Es失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_BACK_HOUSE, e, "保存返回主表失败:" + e.getMessage());
        }
        return id;
    }

    /**
     * 封装入库-派车单关联信息
     *
     * @param esDTO
     * @date 2018年6月4日
     */
    private void setDispatcherInfo(BackWarehouseEsDTO esDTO) {
        if (esDTO.getDipatcherDetailId() != null) {
            //查询派车单详情
            DispatcherDetailEsDTO dispatcherDetailEsDTO = dispatcherService.queryDispatcherDetailById(esDTO.getDipatcherDetailId());
            //开发阶段防止脏数据崩溃
            if (dispatcherDetailEsDTO != null) {
                esDTO.setVehicleNo(dispatcherDetailEsDTO.getVehicleNo());
                //esDTO.setVehicleModelName(dispatcherDetailEsDTO.getVehicleModelName());
                esDTO.setConfirmTime(dispatcherDetailEsDTO.getDispatcherTime());
                //esDTO.setDeliveriesNumber(dispatcherDetailEsDTO.getPackageNum());
                esDTO.setDriverId(dispatcherDetailEsDTO.getDriverId());
                esDTO.setDriverName(dispatcherDetailEsDTO.getDriverName());
                esDTO.setBackCode(dispatcherDetailEsDTO.getBackCode());
                esDTO.setTotalNum(dispatcherDetailEsDTO.getTotalNum());
                esDTO.setDeliveriesNumber(dispatcherDetailEsDTO.getPackageNum());
                esDTO.setVehicleModelName(dispatcherDetailEsDTO.getVehicleModelName() + "");
            }
        }
    }

    /**
     * 封装入库-运单关联的信息
     *
     * @param esDTO
     * @date 2018年6月4日
     */
    private void setWaybillInfo(BackWarehouseEsDTO esDTO) {
        //通过运单ID查询运单信息
        WaybillEsDTO waybill = waybillService.queryWaybillEsDTO(esDTO.getWaybillId());
        //开发阶段防止脏数据崩溃
        if (waybill != null) {
            esDTO.setBackBillType(waybill.getBackType());
            esDTO.setBackCode(waybill.getBackCode());
            esDTO.setBackBillTypeValue(waybill.getBackTypeValue());
            esDTO.setBackBillType(waybill.getBackType());
            esDTO.setBackNum(waybill.getBackNum());
            //设置费用信息
            esDTO.setTotalNum(waybill.getTotalNum());
            esDTO.setPickupFee(waybill.getPickupFee());
            esDTO.setBaseFreight(waybill.getBaseFreight());
            esDTO.setDeliveryFee(waybill.getDeliveryFee());
            esDTO.setUpstairsFee(waybill.getUpstairsFee());
            esDTO.setPackingCharges(waybill.getPackingCharges());
            esDTO.setDeclaredValue(waybill.getDeclaredValue());
            esDTO.setInformationFee(waybill.getInformationFee());
            esDTO.setEmergencyFee(waybill.getEmergencyFee());
            esDTO.setFreightPayment(waybill.getFreightPayment());
            esDTO.setOtherFee(waybill.getOtherFee());
            esDTO.setBusiness(waybill.getBusiness());
            esDTO.setInvoiceCompany(waybill.getInvoiceCompany());
            esDTO.setReceiptCompany(waybill.getReceiptCompany());
            esDTO.setReceiptUser(waybill.getReceiptUser());
            esDTO.setInvoiceUser(waybill.getInvoiceUser());
            //esDTO.setConfirmTime(waybill.getConfirmTime());
        }

    }


}
