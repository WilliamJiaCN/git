package com.hivescm.tms.finance.server.component.pcsign.impl;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.common.utils.Assert;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.common.exception.TmsBusinessException;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.request.SignNoticeForDispatcherReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceDeleteReqDTO;
import com.hivescm.tms.api.dto.es.sign.*;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.CancelDeliverySignBatchReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.UpdateSignStatusReqDTO;
import com.hivescm.tms.api.dto.es.stock.StockLockSyncDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillStockDetailEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillStockLuckEsDTO;
import com.hivescm.tms.api.dto.es.waybill.VehicleTailAfterEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptRequirmentTypeEnum;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherStatusEnum;
import com.hivescm.tms.api.enums.biz.sign.NormalSignType;
import com.hivescm.tms.api.enums.biz.sign.RefuseSignTypeEnum;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.stock.StockLockTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.SignStockDetailEunm;
import com.hivescm.tms.api.enums.biz.waybill.WaybillDistributionTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillTrackStatusEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.constants.TmsCodeRuleBizCode;
import com.hivescm.tms.finance.server.component.pcsign.DeliverySignService;
import com.hivescm.tms.finance.server.component.pcsign.SignReceiptService;
import com.hivescm.tms.finance.server.dao.entity.sign.*;
import com.hivescm.tms.finance.server.dao.mapper.sign.*;
import com.hivescm.tms.finance.server.feign.dispatcher.DispatcherService;
import com.hivescm.tms.finance.server.feign.stock.IStockLockService;
import com.hivescm.tms.finance.server.feign.waybill.ReceiptBillService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.db.DbOperationService;
import com.hivescm.tms.finance.server.service.finance.FinanceManageReceiptService;
import com.hivescm.tms.finance.server.service.lock.SignLockService;
import com.hivescm.tms.finance.server.service.pcsign.*;
import com.hivescm.tms.finance.server.service.sign.*;
import com.hivescm.tms.intranet.gateway.api.dto.IdCode.IdCodeReqNewDTO;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.IdCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


/**
 * 零干-送货签收
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/3/26
 */
@Service
public class DeliverySignServiceImpl implements DeliverySignService {

    protected final static Logger  logger= LoggerFactory.getLogger(DeliverySignService.class);

    @Autowired
    private IGeneratedIdService generatedIdService;
    @Autowired
    private IdCodeService idCodeService;
    @Autowired
    private SignRefuseDOMapper signRefuseDOMapper;
    @Autowired
    private EsSignService esSignService;
    @Autowired
    private EsSignRefuseService esSignRefuseService;

    @Autowired
    private EsSignDetailsService esSignDetailsService;
    @Autowired
    private EsSignFeeService esSignFeeService;
    @Autowired
    private DispatcherService dispatcherService;

    @Autowired
    private WaybillService waybillService;
    @Autowired
    private DbOperationService dbOperationService;

    @Autowired
    private ReceiptBillService receiptBillService;

    @Autowired
    private SignReceiptService signReceiptService;

    @Autowired
    private SignMapper signMapper;

    @Autowired
    private SignDetailDOMapper signDetailDOMapper;

    @Autowired
    private DeliveryFailureMapper deliveryFailureMapper;

    @Autowired
    private EsDeliveryFailureService esDeliveryFailureService;

    @Autowired
    private SignFeeMapper signFeeMapper;

    @Autowired
    private BackWarehouseRecordService backWarehouseRecordService;
    @Autowired
    private BackWarehouseMapper backWarehouseMapper;

    @Autowired
    private BillTrackingService billTrackingService;
    @Autowired
    private SignFinanceService signFinanceService;
    @Autowired
    private FinanceManageReceiptService financeManageReceiptService;
    @Autowired
    private SignBillingFlowService signBillingFlowService;
    @Autowired
    private SignLockService signLockService;
    @Autowired
    private IStockLockService iStockLockService;
    @Autowired
    private SignStockService signStockService;


    private static Executor threadPool= Executors.newSingleThreadExecutor();


    /**
     * 配送签收
     * 全部签收 部分签收 拒绝签收
     * 签收动作完-> 通知派车单服务 --> 派车单完结  Y or N
     *-> 通知运单服务   --> 运单完结   Y or N
     * 04/12 签收时 不涉及库存,拒签货物在操作返回入库时才涉及库存
     * 05/22 加入分布式锁 拦重复提交
     * @param tmsSignEsDTO
     * @return
     */
    @Override
    public Boolean deliverySign(TmsSignEsDTO tmsSignEsDTO) {
        //获取分布式锁 拦重复提交
        if (!signLockService.deliverySignLock(tmsSignEsDTO.getSignEsDTO().getDispatcherDetailId())){
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "请勿重复提交");
        }
        //操作日志 todo
        SignEsDTO signEsDTO = null;
        try {
            signEsDTO = tmsSignEsDTO.getSignEsDTO();

            DispatcherDetailEsDTO dispatcherDetail = dispatcherService.queryDispatcherDetailById(signEsDTO.getDispatcherDetailId());
            //参数验证
            checkSignVaildAndSetValue(tmsSignEsDTO, dispatcherDetail);

            //************* 更新自己的业务 es & db ******************
            updateSignInnerBusiness(tmsSignEsDTO, dispatcherDetail);

            //*************DB更新成功 通知其他人******************
            outerServiceSignInfoSync(tmsSignEsDTO);

        } catch (TmsBusinessException e) {
            return handleExAndRollBack(tmsSignEsDTO, e);
        } catch (Exception e) {
            e.printStackTrace();
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, e, "签收失败");
        } finally {
            //释放分布式锁
            signLockService.deliverySignUnLock(tmsSignEsDTO.getSignEsDTO().getDispatcherDetailId());
        }
        return true;
    }

    /**
     * 处理异常 & 回滚
     * @param tmsSignEsDTO
     * @param e
     * @return
     */
    private Boolean handleExAndRollBack(TmsSignEsDTO tmsSignEsDTO,TmsBusinessException e) {
        Long signID=tmsSignEsDTO.getSignEsDTO().getId();
        ArrayList signIDs=new ArrayList();
        signIDs.add(signID);
        Long compnayid=tmsSignEsDTO.getSignEsDTO().getCompanyId();
        if(esSignService.querySignEsDTOById(signID)!=null){
            //es删除签收单表体
            esSignService.deleteBySignId(tmsSignEsDTO.getSignEsDTO().getId());
            signMapper.deleteBatcheByPrimaryKey(compnayid,signIDs);
            List<SignDetailsEsDTO> detailListDto=esSignDetailsService.queryBySignId(signID);
            if(detailListDto!=null && detailListDto.size()>0){
                //es删除详情
                esSignDetailsService.deleteBySignId(signID);
                signDetailDOMapper.deleteBatchBySignId(compnayid,signIDs);
            }
            SignRefuseEsDTO signRefuseEsDTO=esSignRefuseService.queryRefuseSignBySignId(signID);
            if(signRefuseEsDTO !=null ){
                //全部拒签或者部分拒签，es删除拒签信息，通过签收id删除拒签
                if(tmsSignEsDTO.getSignEsDTO().getRefuseNumber()>0){
                    esSignRefuseService.deleteSignRefuseEsDTOBySignId(tmsSignEsDTO.getSignEsDTO().getId());
                }
            }
        }
        //删除db
        int errorCode = e.getErrorCode();
        if (errorCode == ExceptionCodeConstants.WAYBILL_SIGN_FAILED) {
            //运单同步签收数据失败,删除签收es数据
            e.printStackTrace();
            throw ExceptionFactory.ex(ExceptionCodeConstants.DISPATCHER_SIGN_FAILED, e, "同步派车单签收信息失败");
        } else if (errorCode == ExceptionCodeConstants.DISPATCHER_SIGN_FAILED) {
            //派车单同步签收信息失败，删除签收es数据，恢复运单数据，
            Boolean updateWyabillInfoResult = updateWyabillInfoFromSign(tmsSignEsDTO.getSignEsDTO().getWaybillId(),tmsSignEsDTO.getSignEsDTO().getRefuseNumber(),tmsSignEsDTO.getSignEsDTO().getSignNumber(), WaybillDistributionTypeEnum.DELIVERY);
            e.printStackTrace();
            throw ExceptionFactory.ex(ExceptionCodeConstants.DISPATCHER_SIGN_FAILED, e, "同步派车单签收信息失败");
        } else {
            e.printStackTrace();
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, e, "签收失败");
        }
    }

    /**
     * 通知其他模块
     *
     * @param tmsSignEsDTO
     */
    private void outerServiceSignInfoSync(TmsSignEsDTO tmsSignEsDTO){
        //通知运单已签收
        DataResult<Boolean> waybillResult = noticeWaybillSignStatus(tmsSignEsDTO);
        if (waybillResult.isFailed()) {
            logger.error("签收调用外部运单服务失败，运单ID{},返回结果waybillResult{}", tmsSignEsDTO.getSignEsDTO().getWaybillId(), waybillResult);
            throw ExceptionFactory.ex(ExceptionCodeConstants.WAYBILL_SIGN_FAILED, "调用运单服务,返回结果不成功");
        }
        //通知派车单已签收
        DataResult<Boolean> dispatcherResult = noticeDispatcherSignStatus(tmsSignEsDTO);
        if (dispatcherResult.isFailed()) {
            logger.error("签收调用外部服务派车单失败,派车单ID{},dispatcherResult{}", tmsSignEsDTO.getSignEsDTO().getDispatcherDetailId(),dispatcherResult);
            throw ExceptionFactory.ex(ExceptionCodeConstants.DISPATCHER_SIGN_FAILED, "调用派车单服务,返回结果不成功");
        }

        threadPool.execute(() ->{
            //签回单
            Boolean receiptBillResult = noticeReceiptBill(tmsSignEsDTO);
            Long[] longs = {tmsSignEsDTO.getSignEsDTO().getWaybillId()};
            List<Long> waybillIds = Arrays.asList(longs);

            //财务签收
            signFinanceService.sendMq(waybillIds,SignStatusEnum.SIGNED.getType());

            List<SignEsDTO> signEsDTOS = esSignService.querySignEsByWaybillId(tmsSignEsDTO.getSignEsDTO().getWaybillId());
            //如果该运单没被签收过
            if (signEsDTOS.size()<2){
                //计费流水
                signBillingFlowService.billingFlow(waybillIds, SignStatusEnum.SIGNED.getType());
            }
            billtracking(tmsSignEsDTO);
        });


    }

    private Boolean noticeReceiptBill(TmsSignEsDTO tmsSignEsDTO) {
        WaybillEsDTO waybill = waybillService.queryWaybillEsDTO(tmsSignEsDTO.getSignEsDTO().getWaybillId());
        if (waybill.getBackTypeValue() != null && (ReceiptRequirmentTypeEnum.SIGN_BILL.getType() == waybill.getBackTypeValue().intValue() || ReceiptRequirmentTypeEnum.SIGN_GRASP.getType() == waybill.getBackTypeValue().intValue() || ReceiptRequirmentTypeEnum.SIGN_RECEIPT.getType() == waybill.getBackTypeValue().intValue())) {
            signReceiptService.signReceipt(tmsSignEsDTO);
        }
        return true;
    }

    /**
     * 运单跟踪信息
     *
     * @param tmsSignEsDTO
     */
    private void billtracking(TmsSignEsDTO tmsSignEsDTO) {
        threadPool.execute(() -> {
            try {
                VehicleTailAfterEsDTO vehicleTailAfterEsDTO = new VehicleTailAfterEsDTO();
                SignEsDTO sign = tmsSignEsDTO.getSignEsDTO();
                vehicleTailAfterEsDTO.setCompanyId(sign.getCompanyId().intValue());
                vehicleTailAfterEsDTO.setWaybillCode(sign.getCode());
                vehicleTailAfterEsDTO.setWaybillId(sign.getWaybillId());
                vehicleTailAfterEsDTO.setCompanyName(null);
                vehicleTailAfterEsDTO.setStatus(WaybillTrackStatusEnum.SIGNED.getType());
                vehicleTailAfterEsDTO.setStatusName(WaybillTrackStatusEnum.SIGNED.getName());
                vehicleTailAfterEsDTO.setCreateUser(sign.getCreateUser());
                vehicleTailAfterEsDTO.setCreateUserName(sign.getCreateUserName());
                vehicleTailAfterEsDTO.setCreateTime(System.currentTimeMillis());
                vehicleTailAfterEsDTO.setInvoiceOrgId(sign.getSignOrgId());
                vehicleTailAfterEsDTO.setInvoiceOrgName(sign.getSignOrgName());
                vehicleTailAfterEsDTO.setLightbulbType(1);//可见
                vehicleTailAfterEsDTO.setSignUserName(sign.getSignPeople());
                billTrackingService.billTracking(vehicleTailAfterEsDTO);
            } catch (Exception e) {
                // 子线程内异常不能反馈，吃掉异常，单据失败
                logger.error("同步运单跟踪信息失败 ", e);
            }
        });
    }

    private DataResult<Boolean> noticeWaybillSignStatus(TmsSignEsDTO tmsSignEsDTO) {
        List<UpdateSignStatusReqDTO> reqList = new ArrayList<>();

        UpdateSignStatusReqDTO statusReqDTO = EntityUtils.copyProperties(tmsSignEsDTO.getSignEsDTO(), UpdateSignStatusReqDTO.class);
        statusReqDTO.setTypeEnum(WaybillDistributionTypeEnum.DELIVERY);

        reqList.add(statusReqDTO);
        DataResult<Boolean> result = waybillService.updateWyabillInfoFromSign(reqList);

        logger.info("updateWyabillInfoFromSign reuselt :{}", result);

//        if (result.isSuccess()){
//            return result.getResult();
//        }
//
//        throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "调用外部服务异常:WaybillService::updateWyabillInfoFromSign");

        return result;
    }

    /**
     * 签收自己的业务
     *
     * 涉及的表:
     * Sign
     * SignDetail
     * SignFee
     * SignRefuse(感觉多余)
     * 砍掉了 拒签明细表 (GoodsDetail) 因为SignDetail 可以包含拒签信息
     *
     * @param tmsSignEsDTO
     * @return
     */
    public boolean updateSignInnerBusiness(TmsSignEsDTO tmsSignEsDTO, DispatcherDetailEsDTO dispatcherDetail) {

        SignEsDTO signEsDTO = tmsSignEsDTO.getSignEsDTO();
        SignDO sign = assembleSign(tmsSignEsDTO, dispatcherDetail);
        List<SignDetailDO> signDetailDOs = assembleSignDetails(tmsSignEsDTO);
        SignFeeDO fee = assembleSignFee(tmsSignEsDTO);

        //全部签收、部分签收、全部拒签
        if (signEsDTO.getRefuseNumber().intValue() > 0) {
            //部分签收 或者 全部拒签
            SignRefuseDO refuseDO = new SignRefuseDO();
            SignRefuseEsDTO signRefuseEsDTO = new SignRefuseEsDTO();
            //构建拒收单
            buildSignRefuseData(refuseDO, signRefuseEsDTO, tmsSignEsDTO, dispatcherDetail);
            //更新db
            //生成签收单、签收明细、费用、拒收单
            dbOperationService.saveSignInfo(sign, signDetailDOs, fee, refuseDO);
            {//更新es
                esSignRefuseService.insertSignRefuseEsDTO(signRefuseEsDTO);
                esSignService.insertSignEsDTO(signEsDTO);
                esSignDetailsService.insertSignDetailsEsDTO(tmsSignEsDTO.getSignDetailsEsDTO());
                esSignFeeService.insertSignFeeEsDTO(tmsSignEsDTO.getSignFeeEsDTO());
            }
        } else {
            //全部签收
            //生成签收单、签收明细、费用
            dbOperationService.saveSignInfo(sign,signDetailDOs, fee);
            {
                esSignService.insertSignEsDTO(signEsDTO);
                esSignDetailsService.insertSignDetailsEsDTO(tmsSignEsDTO.getSignDetailsEsDTO());
                esSignFeeService.insertSignFeeEsDTO(tmsSignEsDTO.getSignFeeEsDTO());
            }
        }

        return true;
    }

    private void buildSignRefuseData(SignRefuseDO refuseDO, SignRefuseEsDTO signRefuseEsDTO, TmsSignEsDTO tmsSignEsDTO, DispatcherDetailEsDTO dispatcherDetail) {
        SignEsDTO signEsDTO = tmsSignEsDTO.getSignEsDTO();

        EntityUtils.copyProperties(dispatcherDetail, signRefuseEsDTO);
        EntityUtils.copyProperties(signEsDTO, signRefuseEsDTO);

        signRefuseEsDTO.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_REFUSE_SIGN));
        IdCodeReqNewDTO idCodeReqNewDTO = new IdCodeReqNewDTO();
        idCodeReqNewDTO.setGroupId(tmsSignEsDTO.getGroupId());
        idCodeReqNewDTO.setBizCode(TmsCodeRuleBizCode.TMS_NEW_JS);
        DataResult<String> code = idCodeService.generatedNew(idCodeReqNewDTO);
        signRefuseEsDTO.setRefuseCode(code.getResult());
        signRefuseEsDTO.setSignId(signEsDTO.getId());
        // 运单号  ?!!
        signRefuseEsDTO.setCode(signEsDTO.getCode());
        signRefuseEsDTO.setRefuseWaybillId(signEsDTO.getWaybillId());

        signRefuseEsDTO.setRefuseTime(new Date());


//        signRefuseEsDTO.setCollectPayment(collectPayment);
//        signRefuseEsDTO.setOrderPay(tmsWaybillEsDTO.getGoodsPayment());
        signRefuseEsDTO.setInvoiceName(dispatcherDetail.getInvoiceUser());
        signRefuseEsDTO.setRefusePeople(dispatcherDetail.getReceiptUser());
        signRefuseEsDTO.setRefuseCard(dispatcherDetail.getReceiptIdentityCard());
        signRefuseEsDTO.setRefusePhone(dispatcherDetail.getReceiptConsignorMobleNo());
        signRefuseEsDTO.setCarrierName(dispatcherDetail.getCarrierGlobalName());

        signRefuseEsDTO.setCarName(dispatcherDetail.getDriverName());
        signRefuseEsDTO.setCarNumber(dispatcherDetail.getVehicleNo());
        signRefuseEsDTO.setCarPhone(dispatcherDetail.getPhoneNo());
        signRefuseEsDTO.setDispatcherNumber(dispatcherDetail.getPackageNum());
        signRefuseEsDTO.setCreateNumber(dispatcherDetail.getTotalNum());

        // 发货人
        signRefuseEsDTO.setRefuseCard(dispatcherDetail.getReceiptIdentityCard());
        signRefuseEsDTO.setDeliveryType(WaybillDistributionTypeEnum.DELIVERY.getType());
        signRefuseEsDTO.setDeliveryTypeName(WaybillDistributionTypeEnum.DELIVERY.getName());

        signRefuseEsDTO.setSignCode(signEsDTO.getSignBatchNumber());


        //拒收类型
        if (signEsDTO.getSignNumber() > 0) {

            signRefuseEsDTO.setRefuseType(RefuseSignTypeEnum.PARTIAL_REFUSE_SIGN.getType());

        } else {

            signRefuseEsDTO.setRefuseType(RefuseSignTypeEnum.REFUSE_SIGN.getType());
        }


        EntityUtils.copyProperties(signRefuseEsDTO, refuseDO);

    }


    @Override
    public Boolean deliveryFailure(TmsSignEsDTO tmsSignEsDTO) {
        // >> 0.非空信息校验（公司id，运单id，派车单明细id，派送失败原因）
        Assert.isNotNull(tmsSignEsDTO.getSignEsDTO(), "派送失败请求信息不完整，操作失败！");
        Assert.isNotNull(tmsSignEsDTO.getSignEsDTO().getCreateUser(), "创建人不允许为空，操作失败！");
        Assert.isNotNull(tmsSignEsDTO.getSignEsDTO().getCompanyId(), "公司ID不允许为空，操作失败！");
        Assert.isNotNull(tmsSignEsDTO.getSignEsDTO().getSignOrgId(), "签收网点id(当前网点)不允许为空，操作失败！");
        Assert.isNotNull(tmsSignEsDTO.getSignEsDTO().getWaybillId(), "运单ID不允许为空，操作失败！");
        Assert.isNotNull(tmsSignEsDTO.getSignEsDTO().getDispatcherId(), "派车单ID不允许为空，操作失败！");
        Assert.isNotNull(tmsSignEsDTO.getSignEsDTO().getDispatcherDetailId(), "派车单明细ID不允许为空，操作失败！");
        Assert.isNotNull(tmsSignEsDTO.getSignEsDTO().getDeliveryFailureReason(), "派送失败原因不允许为空，操作失败！");

        DispatcherDetailEsDTO dispatcherDetailEsDTO = dispatcherService.queryDispatcherDetailById(tmsSignEsDTO.getSignEsDTO().getDispatcherDetailId());
        if (dispatcherDetailEsDTO == null) {

            Assert.isNotNull(dispatcherDetailEsDTO, "无此派车记录，操作失败！");

        }


        // >> 1.根据运单号和派车批次，通知派车单更新其派车状态。
        SignNoticeForDispatcherReqDTO notice = new SignNoticeForDispatcherReqDTO();
        notice.setCompanyId(tmsSignEsDTO.getSignEsDTO().getCompanyId());
        notice.setWaybillId(tmsSignEsDTO.getSignEsDTO().getWaybillId());
        notice.setDispatcherId(tmsSignEsDTO.getSignEsDTO().getDispatcherId());
        notice.setDispatcherDetailId(tmsSignEsDTO.getSignEsDTO().getDispatcherDetailId());
        notice.setIsDeliveryFailure(true);
        DataResult<Boolean> synecDispatcherStatus = dispatcherService.synecDispatcherStatus(notice);
        if (synecDispatcherStatus == null || synecDispatcherStatus.getResult() == null || synecDispatcherStatus.getResult() != true) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "调用外部接口（同步派车单状态）失败：" + synecDispatcherStatus.getStatus().getStatusReason());
        }
        // >> 2.保存跟派送失败有关的信息（公司id，运单id，派车单明细id，派送失败原因）
        DeliveryFailureEsDTO deliveryFailureEsDTO = new DeliveryFailureEsDTO();

        //查询运单
        WaybillEsDTO waybillEsDTO = waybillService.queryWaybillEsDTO(dispatcherDetailEsDTO.getWaybillId());

        //  冗余派车单明细 冗余 运单信息
        deliveryFailureEsDTO.init().acceptDispatcherDetail(dispatcherDetailEsDTO).acceptWaybill(waybillEsDTO);

        deliveryFailureEsDTO.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_DISPATCHER));
        deliveryFailureEsDTO.setCreateUser(tmsSignEsDTO.getSignEsDTO().getCreateUser());
        deliveryFailureEsDTO.setCreateTime(new Date().getTime());
        deliveryFailureEsDTO.setDispatcherDetailId(tmsSignEsDTO.getSignEsDTO().getDispatcherDetailId());
        deliveryFailureEsDTO.setFailureReasion(tmsSignEsDTO.getSignEsDTO().getDeliveryFailureReason());
        deliveryFailureEsDTO.setOrgId(tmsSignEsDTO.getOrgId());
        deliveryFailureEsDTO.setOrgName(tmsSignEsDTO.getOrgName());

        DeliveryFailureDO deliveryFailureDO = new DeliveryFailureDO();
        BeanUtils.copyProperties(deliveryFailureEsDTO, deliveryFailureDO);
        //添加db
        Integer dbInsertDeliveryFailureRes = deliveryFailureMapper.insertSelective(deliveryFailureDO);
        //添加es
        Boolean esInsertDeliveryFailureRes = esDeliveryFailureService.insertDeliveryFailureEsDTO(deliveryFailureEsDTO);

        return esInsertDeliveryFailureRes && dbInsertDeliveryFailureRes == 1 && synecDispatcherStatus.getResult();
    }

    @Override
    public Boolean cancelDeliverySign(CancelDeliverySignBatchReqDTO cancelDeliverySignBatchReqDTO) {
        // >> 参数合法性校验
        Assert.isNotNull(cancelDeliverySignBatchReqDTO.getId(), "签收单ID不可为空！");
        Assert.isNotNull(cancelDeliverySignBatchReqDTO.getCompanyId(), "公司Id不可为空！");
        Assert.isNotNull(cancelDeliverySignBatchReqDTO.getCurentOrgId(), "当前网点不可为空！");

        //1. >> 查询签收单，签收货物明细信息
        SignEsDTO oldSignEsDTO = esSignService.querySignEsDTOById(cancelDeliverySignBatchReqDTO.getId());
        Assert.isTrue(oldSignEsDTO != null, "查询签收单Id：" + cancelDeliverySignBatchReqDTO.getId() + "数据不存在！");

        // 锁定运单防止重复操作
        Boolean getLock = lockStock(cancelDeliverySignBatchReqDTO.getCurentOrgId().longValue(), oldSignEsDTO.getWaybillId());

        if (!getLock) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_WARE_HOUSE, "该运单正被其他用户操作");
        }

//        //获得分布式锁
//        Assert.isTrue(signLockService.deliveryCancelSignLock(cancelDeliverySignBatchReqDTO.getId()),"请勿重复提交");


        try {
            Assert.isTrue(oldSignEsDTO.getSignStatus() != SignStatusEnum.NO_SIGN.getType(), "该签收单：" + oldSignEsDTO.getCod() + "未被签收过，不可取消签收！");
            Assert.isTrue(oldSignEsDTO.getSignStatus() != SignStatusEnum.CANCEL_SIGN.getType(), "改签收单已经被取消,不可重复取消");
            Assert.isTrue(vaildCanCancel(oldSignEsDTO), "该签收单已经被收银,不可取消签收！");

            List<Long> signIds = new ArrayList<>();
            signIds.add(cancelDeliverySignBatchReqDTO.getId());
            List<SignDetailsEsDTO> oldSignDetailsEsDTOs = esSignDetailsService.queryBySignIds(signIds);
            if(oldSignDetailsEsDTOs == null || oldSignDetailsEsDTOs.size() < 1){
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_PACKAGE_EMPTY, "该签收单id："+signIds+"未查询到签收单明细信息！");
            }

            List<Long> waybillids = oldSignDetailsEsDTOs.stream().map(a -> a.getWaybillId()).distinct().collect(Collectors.toList());
            if (waybillids == null || waybillids.size() != 1) {
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_PACKAGE_EMPTY, "该签收单查询到的运单号：" + waybillids.toString() + "不合法！");
            }


            //2. >> 库存回滚 + 3. >> 通知运单修改签收数量。
            Boolean noticeUpdateWyabillInfoWhenCancelDeliverySignResult = rollbackInventoryAndNoticeUpdateWyabillInfoWhenCancelDeliverySign(oldSignEsDTO,oldSignDetailsEsDTOs,
                    oldSignEsDTO.getWaybillId(),cancelDeliverySignBatchReqDTO.getCurentOrgId());

            if (noticeUpdateWyabillInfoWhenCancelDeliverySignResult){
                //4.1 >> 删除签收单 + 签收单明细的 db + es 数据。（实际为更改该签收单主表状态为作废）
                Boolean deleteSignEsAndDbResult =  deleteSignEsAndDbById(cancelDeliverySignBatchReqDTO.getCompanyId(), oldSignEsDTO.getId());
                Integer signStatus = oldSignEsDTO.getSignStatus();
                Boolean deleteSignRefuseResult = true;
                Boolean deleteBackWarehouseRecord =true;

                //若存在拒签部分
                if(signStatus == SignStatusEnum.PARTIAL_SIGN.getType() || signStatus == SignStatusEnum.REFUSE_SIGN.getType()){
                    //4.2 >> 删除对应的【签收拒绝表】也被删除，若已经发送给 OMS，则自动撤回发送的拒收单记录。（实际为更改该拒收单状态为作废，即取消签收）
                    deleteSignRefuseResult = deleteRefuseSignBySignId(oldSignEsDTO.getCompanyId(), oldSignEsDTO.getId());
                    deleteBackWarehouseRecord = deleteBackWarehouseRecordBySignId(oldSignEsDTO.getCompanyId(), oldSignEsDTO.getId());
                }
                boolean result = deleteSignEsAndDbResult && deleteSignRefuseResult && deleteBackWarehouseRecord;
                if (result) {
                    //财务-应收
                    Long[] waybillIds = {oldSignEsDTO.getWaybillId()};
                    List<Long> longs = Arrays.asList(waybillIds);
                    signFinanceService.sendMq(longs, SignStatusEnum.CANCEL_SIGN.getType());
                    signBillingFlowService.billingFlow(longs, SignStatusEnum.CANCEL_SIGN.getType());

                }
                return result;
            }
            else{
                return false;
            }
        } catch (TmsBusinessException e) {
            e.printStackTrace();
            iStockLockService.unlock(cancelDeliverySignBatchReqDTO.getCurentOrgId().longValue(),oldSignEsDTO.getWaybillId());

            throw e;
        }

    }
    /**
     * 锁定库存
     * @param orgId
     * @param waybillId
     * @return
     */
    private Boolean lockStock(Long orgId,Long waybillId){
        DataResult<Boolean> lock = iStockLockService.lock(orgId, waybillId);
        if(lock.getResult() == null || !lock.isSuccess() || !lock.getResult()){
            throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_LOCK, "该运单正被其他用户操作");
        }
        return lock.isSuccess();

    }
    private Boolean vaildCanCancel(SignEsDTO oldSignEsDTO) {
        FinanceDeleteReqDTO financeDeleteReqDTO = new FinanceDeleteReqDTO();
        financeDeleteReqDTO.setCode(oldSignEsDTO.getCode());
        financeDeleteReqDTO.setWaybillId(oldSignEsDTO.getWaybillId());
        financeDeleteReqDTO.setCompanyId(oldSignEsDTO.getCompanyId());

        Boolean flag = financeManageReceiptService.getPayStatusByCodeSign(financeDeleteReqDTO);


        return flag;
    }

    private boolean deleteBackWarehouseRecordBySignId(Long companyId, Long signId) {


        BackWarehouseEsDTO warehouse = backWarehouseRecordService.getBackWarehouseBySignId(signId);

        if (warehouse==null){
            return true;
        }
        else {

            warehouse.setIdelete(true);
            int db = backWarehouseMapper.deleteByPrimaryKey(companyId, warehouse.getId());
            boolean es = backWarehouseRecordService.updateBackWareHouse(warehouse);

            return es&&(db>0);

        }



    }


    /**
     * 根据签收单id删除签收拒绝表信息 es + db。（实际为更改该签收单主表状态为作废）
     * @param companyId
     * @param signId
     * @return
     */
    private Boolean deleteRefuseSignBySignId(Long companyId ,Long signId){
        Boolean result = false;
        try {
            SignRefuseEsDTO updateSignRefuseEsDTO = esSignRefuseService.queryRefuseSignBySignId(signId);
            Assert.isNotNull(updateSignRefuseEsDTO,"根据签收单id:" + signId +" 未查询到签收拒绝表信息ES！");
            SignRefuseDO signRefuseDO = signRefuseDOMapper.selectBySignId(companyId, signId);
            Assert.isNotNull(signRefuseDO,"根据签收单id:" + signId +" 未查询到签收拒绝表信息DB！");

            RefuseSignTypeEnum cancelRefuseSignType = RefuseSignTypeEnum.getRefuseSignTypeEnum(updateSignRefuseEsDTO.getRefuseType()).toCancelRefuseSignType();

            updateSignRefuseEsDTO.setRefuseType(cancelRefuseSignType.getType());
            result = esSignRefuseService.updateSignRefuseEsDTO(updateSignRefuseEsDTO);
            Assert.isTrue(result,"根据id:"+updateSignRefuseEsDTO.getId()+"删除签收拒绝表信息ES失败！");
            signRefuseDO.setRefuseType(cancelRefuseSignType.getType());
            result = signRefuseDOMapper.updateByPrimaryKey(signRefuseDO) == 1 ? true : false;
            Assert.isTrue(result,"根据id:"+signRefuseDO.getId()+"删除签收拒绝表信息DB失败！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_PACKAGE_EMPTY, "根据签收单id(" + signId + ")删除签收拒绝表信息操作异常：！" + e.getMessage());
        }

        return result;
    }



    /**
     * 取消签收时回滚库存
     * @param stockLuckEsDTOs
     * @param IStorage 是否返库
     * @return
     */
    private  boolean rollbackInventoryWhenCancelDeliverySign(List<WaybillStockLuckEsDTO> stockLuckEsDTOs , Boolean IStorage){
        DataResult<Boolean> signStockDetailResult = null;
        try {


            signStockDetailResult=stockSyncAdapt(stockLuckEsDTOs , IStorage);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(ExceptionCodeConstants.ERROR_BILL_INFO, "调用返库["+IStorage+"]接口，执行返库操作异常！");
        }
        Assert.isTrue(signStockDetailResult.getResult() != null && signStockDetailResult.getResult(),"是否已经返库：["+IStorage+"] || 取消签收时调用返库操作失败！");
        return signStockDetailResult.getResult() != null && signStockDetailResult.getResult();
    }
    private DataResult<Boolean>  stockSyncAdapt(List<WaybillStockLuckEsDTO> stockLuckEsDTOs , Boolean IStorage){
//        if(IStorage){//是否返库
//            return  waybillService.updateSignStockEscNum(stockLuckEsDTOs);
//        }else{
//            return  waybillService.updateSignNotArrived(stockLuckEsDTOs);
//        }

        ArrayList<StockLockSyncDTO> syncDTOS = new ArrayList<>();

        stockLuckEsDTOs.forEach(e->{
            StockLockSyncDTO stockLockSyncDTO=new StockLockSyncDTO();

            stockLockSyncDTO.setStockLockType(e.getStockLockType());
            stockLockSyncDTO.setBranchId(e.getBranchId());
            stockLockSyncDTO.setCompanyId(e.getCompanyId());
            stockLockSyncDTO.setStockDetailId(e.getStockDetailId());
            stockLockSyncDTO.setWaybillId(e.getWaybillId());
            stockLockSyncDTO.setCode(e.getCode());
            stockLockSyncDTO.setHouseId(e.getHouseId());
            stockLockSyncDTO.setHouseName(e.getHouseName());
            stockLockSyncDTO.setHouseType(e.getHouseType());


            stockLockSyncDTO.setSignNum(e.getLockNum());
            stockLockSyncDTO.setRefusalNum(e.getRefuseSignlockNum());

            if (IStorage){
                stockLockSyncDTO.setStockLockType(StockLockTypeEnum.REGOOD_ALL_DELETE_SIGN);
            }else {
                stockLockSyncDTO.setStockLockType(StockLockTypeEnum.NOT_REGOOD_ALL_DELETE_SIGN);

            }
            syncDTOS.add(stockLockSyncDTO);
        });
        signStockService.sendMq(syncDTOS);
        return DataResult.success(true,Boolean.class);

    }

    /**
     * 送货签收-取消签收时，通知运单修改信息
     * @param oldSignEsDTO 原始签收单的主数据信息
     * @param oldSignDetailsEsDTOs 原始签收单的所有表体数据信息
     * @param waybillId 要取消的签收单运单号
     * @param curentOrgId 当前网点Id
     * @return
     */
    private Boolean rollbackInventoryAndNoticeUpdateWyabillInfoWhenCancelDeliverySign(SignEsDTO oldSignEsDTO, List<SignDetailsEsDTO> oldSignDetailsEsDTOs, Long waybillId, Integer curentOrgId){
        Assert.isNotNull(oldSignEsDTO,"参数：签收单不可为空！");
        Assert.isTrue(oldSignDetailsEsDTOs != null && oldSignDetailsEsDTOs.size() > 0,"参数：签收单明细不可为空！");
        Integer signNumberSum = 0;//累计签收件数。该签收单所有[签收件数]之和。
        Integer refuseNumberSum = 0;//累计拒签件数。该签收单所有[拒签件数]之和。
        Integer refuseNumber = oldSignEsDTO.getRefuseNumber();//签收单上的[拒签件数]
        Long dipatcherDetailId = oldSignEsDTO.getDispatcherDetailId();
        DispatcherDetailEsDTO dispatcherDetailEsDTO = null;
        try {
            dispatcherDetailEsDTO = dispatcherService.queryDispatcherDetailById(dipatcherDetailId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(ExceptionCodeConstants.ERROR_BILL_INFO,"派车单接口（queryDispatcherDetail）异常：" + e.getMessage());

        }
        Assert.isNotNull(dispatcherDetailEsDTO,"根据派单明细id:" + dipatcherDetailId + "查询派车单明细信息失败！");
        Boolean IStorage = dispatcherDetailEsDTO.getStorage();//是否返库
        Integer signStatus = oldSignEsDTO.getSignStatus();//签收状态： NO_SIGN(0, 未签收), SIGNED(1,全部签收), PARTIAL_SIGN(2,部分签收), REFUSE_SIGN(3,全部拒签), CANCEL_SIGN(4,作废);
        //1.1 >>  根据该签收单下运单库存明细ID集，查询库存中的[拒收锁定数量](即库存解锁的拒签数量)集
        List<Long> waybillStockDetailIdList = oldSignDetailsEsDTOs.stream()
                .filter(item -> item.getWaybillStockDetailId() != null)
                .map(item -> item.getWaybillStockDetailId()).distinct()
                .collect(Collectors.toList());
        if(waybillStockDetailIdList == null || waybillStockDetailIdList.size() == 0){
            throw new SystemException(ExceptionCodeConstants.ERROR_BILL_INFO,"签收单id："+ oldSignDetailsEsDTOs.get(0).getSignId() + "对应签收单明细中的运单库存明细id不可为空！");
        }
        //1.2 获取库存中拒收锁定的数量EntityList -- refuseLockNumInSignDetailList
        DataResult<List<WaybillStockDetailEsDTO>> refuseLockNumInSignDetailResult = null;
        try {
            refuseLockNumInSignDetailResult = waybillService.getRefuseLockNum(waybillStockDetailIdList,new Long(curentOrgId));
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(ExceptionCodeConstants.ERROR_BILL_INFO,"调用运单接口（getRefuseLockNum）异常:" + e.getMessage());
        }
        List<WaybillStockDetailEsDTO> refuseLockNumInSignDetailList = new ArrayList<>();
        if(refuseLockNumInSignDetailResult.isSuccess() && refuseLockNumInSignDetailResult.getResult() != null){
            refuseLockNumInSignDetailList = refuseLockNumInSignDetailResult.getResult();
        }else {
            throw new SystemException(ExceptionCodeConstants.ERROR_BILL_INFO,"查询库存中的[拒收锁定数量]为空：[" + refuseLockNumInSignDetailResult.isSuccess() +"]" + refuseLockNumInSignDetailResult.getStatus().getStatusReason());
        }
        //2  >> 根据不同签收状态，对累计签收件数和累计拒签件数不同处理
        Map<String,Integer> processingDataBySignStatusMap = processingDataBySignStatusWhenCancelDeliverySign(IStorage, signStatus, signNumberSum,
                refuseNumberSum,oldSignDetailsEsDTOs, oldSignEsDTO,refuseLockNumInSignDetailList, curentOrgId, dipatcherDetailId);
        signNumberSum = processingDataBySignStatusMap.get("signNumberSum");
        refuseNumberSum = processingDataBySignStatusMap.get("refuseNumberSum");
        //3. >> 通知运单修改签收信息的数量。
        Boolean updateWyabillInfoResult = updateWyabillInfoFromSign(waybillId, signNumberSum, refuseNumberSum, WaybillDistributionTypeEnum.DELIVERY);
        return updateWyabillInfoResult;
    }


    /**
     * 送货签收-取消签收时，根据不同签收状态，获取累计签收件数和累计拒签件数
     * @param IStorage 是否返库
     * @param signStatus  签收状态
     * @param signNumberSum 累计签收件数
     * @param refuseNumberSum 累计拒签件数
     * @param oldSignDetailsEsDTOs 原始签收单的所有表体数据信息
     * @param oldSignEsDTO 原始签收单的主数据信息
     * @param refuseLockNumInSignDetailList 库存中拒收锁定的数量EntityList
     * @param curentOrgId 当前网点Id
     * @param dipatcherDetailId 派车单明细id
     * @return
     */
    private Map<String,Integer> processingDataBySignStatusWhenCancelDeliverySign(Boolean IStorage, Integer signStatus, Integer signNumberSum,
                                                                  Integer refuseNumberSum,List<SignDetailsEsDTO> oldSignDetailsEsDTOs,
                                                                  SignEsDTO oldSignEsDTO,List<WaybillStockDetailEsDTO> refuseLockNumInSignDetailList,
                                                                  Integer curentOrgId, Long dipatcherDetailId){
        Map<String,Integer> result = new HashMap<>();
        List<WaybillStockLuckEsDTO> stockLuckEsDTOs = new ArrayList<>();
        if( ( (IStorage == null || !IStorage) && (signStatus == SignStatusEnum.SIGNED.getType() || signStatus == SignStatusEnum.PARTIAL_SIGN.getType() || signStatus == SignStatusEnum.REFUSE_SIGN.getType()))  ){
            //全部签收、或[（部分签收/全部拒收）且未返回库存。则：
            //累计签收件数=累计签收件数-签收件数
            //累计拒签件数=累计拒签件数-拒签件数
            //未签件数=开单件数-累计签收件数-累计拒签件数
//            signNumberSum = 0;
//            refuseNumberSum = 0;
            stockLuckEsDTOs.clear();
            for(SignDetailsEsDTO one : oldSignDetailsEsDTOs){
                WaybillStockLuckEsDTO oneWaybillStockLuckEsDTO = new WaybillStockLuckEsDTO();
                oneWaybillStockLuckEsDTO.setStockDetailId(one.getWaybillStockDetailId());//运单库存明细id
                oneWaybillStockLuckEsDTO.setWaybillId(one.getWaybillId());//运单id
                oneWaybillStockLuckEsDTO.setSignStatus(SignStockDetailEunm.CANCELSIGN.getType());//返库类型：值为： 1部分签收  2拒签 3派送失败 4取消签收
                Integer signNum = one.getSignNumber() == null ? 0 : one.getSignNumber();
                Integer refuseNum = one.getRefuseNumber() == null ? 0 : one.getRefuseNumber();
                oneWaybillStockLuckEsDTO.setLockNum(signNum);//返库件数
                oneWaybillStockLuckEsDTO.setRefuseSignlockNum(refuseNum);//拒签锁定库存数库量
                oneWaybillStockLuckEsDTO.setCompanyId(one.getCompanyId());
                oneWaybillStockLuckEsDTO.setBranchId(curentOrgId);//当前网点
                oneWaybillStockLuckEsDTO.setSignType(oldSignEsDTO.getSignType());

                oneWaybillStockLuckEsDTO.setCode(oldSignEsDTO.getCode());

                stockLuckEsDTOs.add(oneWaybillStockLuckEsDTO);
            }
            signNumberSum = oldSignEsDTO.getSignNumber() != null ? oldSignEsDTO.getSignNumber() : 0;
            refuseNumberSum = oldSignEsDTO.getRefuseNumber() != null ? oldSignEsDTO.getRefuseNumber() : 0;
            // >> 库存回滚
            rollbackInventoryWhenCancelDeliverySign(stockLuckEsDTOs,false);
        }else if((IStorage != null && IStorage) && (signStatus == SignStatusEnum.PARTIAL_SIGN.getType() || signStatus == SignStatusEnum.REFUSE_SIGN.getType())){
            //存在拒签部分(部分签收、拒签)，并且拒签部分已经返库。则：
            //若签收单的拒签件数<=库存中拒收锁定的数量，则：
            //累计签收件数=累计签收件数-签收件数
            //累计拒签件数=累计拒签件数-拒签件数
            //未签件数=开单件数-累计签收件数-累计拒签件数
            //若签收单上的拒签件数>库存中拒收锁定的数量，则：
            //累计签收件数=累计签收件数-签收件数
            //累计拒签件数=累计拒签件数-库存解锁的拒签数量
            //未签件数=开单件数-累计签收件数-累计拒签件数
//            signNumberSum = 0;
            refuseNumberSum = 0;
            stockLuckEsDTOs.clear();
            for(SignDetailsEsDTO one : oldSignDetailsEsDTOs){
                WaybillStockLuckEsDTO oneWaybillStockLuckEsDTO = new WaybillStockLuckEsDTO();
                oneWaybillStockLuckEsDTO.setStockDetailId(one.getWaybillStockDetailId());//运单库存明细id
                oneWaybillStockLuckEsDTO.setWaybillId(one.getWaybillId());//运单id
                oneWaybillStockLuckEsDTO.setSignStatus(SignStockDetailEunm.CANCELSIGN.getType());//返库类型：值为： 1部分签收  2拒签 3派送失败 4取消签收
                oneWaybillStockLuckEsDTO.setSignType(oldSignEsDTO.getSignType());

                Integer signNum = one.getSignNumber();
                Integer refuseNum = one.getRefuseNumber();
                Integer lockNum = getWaybillStockDetailEsDTOById(refuseLockNumInSignDetailList, one.getWaybillStockDetailId());//拒签锁定库存数库量
                Integer depotNum = 0;//返库件数
                if( refuseNum.intValue() <= lockNum.intValue()){
                    oneWaybillStockLuckEsDTO.setRefuseSignlockNum(refuseNum);//拒签锁定库存数库量
                    refuseNumberSum = refuseNumberSum + refuseNum;
                }else{
                    oneWaybillStockLuckEsDTO.setRefuseSignlockNum(lockNum);//拒签锁定库存数库量
                    refuseNumberSum = refuseNumberSum + lockNum;
                }
                oneWaybillStockLuckEsDTO.setLockNum(signNum);//返库件数

                oneWaybillStockLuckEsDTO.setCompanyId(one.getCompanyId());
                oneWaybillStockLuckEsDTO.setBranchId(curentOrgId);//当前网点
                oneWaybillStockLuckEsDTO.setCode(oldSignEsDTO.getCode());
                stockLuckEsDTOs.add(oneWaybillStockLuckEsDTO);
            }
            signNumberSum = oldSignEsDTO.getSignNumber() != null ? oldSignEsDTO.getSignNumber() : 0;
            // >> 库存回滚
            rollbackInventoryWhenCancelDeliverySign(stockLuckEsDTOs,true);
        }else{
            throw new SystemException(ExceptionCodeConstants.ERROR_BILL_INFO,"数据异常：签收单（" + oldSignEsDTO.getId() + "）签收状态：" + signStatus + "  && 派车单明细（" + dipatcherDetailId +")是否返库：" + IStorage);
        }
        result.put("signNumberSum", signNumberSum);
        result.put("refuseNumberSum", refuseNumberSum);
        return result;
    }

    /**
     * 送货签收-取消签收时, 通知运单修改签收信息的数量
     * @param waybillId 运单id
     * @param signNumberSum 累计签收件数
     * @param refuseNumberSum 累计拒签件数
     * @param waybillDistributionType 送货方式
     * @return
     */
    private Boolean updateWyabillInfoFromSign(Long waybillId, Integer signNumberSum, Integer refuseNumberSum, WaybillDistributionTypeEnum waybillDistributionType){
        Boolean result = false;
        List<UpdateSignStatusReqDTO> reqList = new ArrayList<>();
        UpdateSignStatusReqDTO req = new UpdateSignStatusReqDTO();
        req.setWaybillId(waybillId);
        req.setTypeEnum(waybillDistributionType);



        req.setSignNumber(0-signNumberSum);
        req.setRefuseNumber(0-refuseNumberSum);
        reqList.add(req);
        DataResult<Boolean> updateWyabillInfoResult = null;
        try {
            updateWyabillInfoResult = waybillService.updateWyabillInfoFromSign(reqList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException(ExceptionCodeConstants.ERROR_BILL_INFO,e.getMessage());
        }
        if(updateWyabillInfoResult != null && updateWyabillInfoResult.getResult()) {
            result = updateWyabillInfoResult.getResult();
        }else{
            throw new SystemException(ExceptionCodeConstants.ERROR_BILL_INFO,"根据运单号：" + waybillId + "通知运单修改签收信息的数量失败！");
        }
        return result;
    }

    /**
     * 根据运单库存明细ID 匹配相应的运单库存明细信息,获取库存中拒收锁定的数量
     * @param refuseLockNumInSignDetailList
     * @param waybillStockDetailId
     * @return
     */
    private  Integer getWaybillStockDetailEsDTOById(List<WaybillStockDetailEsDTO> refuseLockNumInSignDetailList, Long waybillStockDetailId){
        WaybillStockDetailEsDTO result = new WaybillStockDetailEsDTO();
        for(WaybillStockDetailEsDTO one : refuseLockNumInSignDetailList){
            if(waybillStockDetailId.intValue() == one.getId().intValue()){
                result = one;
                break;
            }
        }
        Integer lockNum = 0;
        if(result != null){
            lockNum = result.getSignStockNum() == null ? 0 : result.getSignStockNum();
        }
        return lockNum;
    }

    /**
     * 根据签收单id,删除签收单和签收单明细。（实际为更改该签收单主表状态为作废，即取消签收）
     * @param
     * @return
     */
    private boolean deleteSignEsAndDbById(Long companyId,Long deleteSignId) {
        if(deleteSignId == null){
            return false;
        }
        SignEsDTO deleteOne = esSignService.querySignEsDTOById(deleteSignId);
        if(deleteOne == null){
            return true;
        }
        //es删除签收单主表
        deleteOne.setSignStatus(SignStatusEnum.CANCEL_SIGN.getType());//作废
        deleteOne.setSignStatusName("作废");
        Boolean updateSignEsDTORES =  esSignService.updateSignEsDTO(deleteOne);
        Assert.isTrue(updateSignEsDTORES,"根据签收单id:"+deleteOne.getId()+"删除签收单信息ES失败！");
        //db删除签收单主表信息
        SignDO updateSignDO = new SignDO();
        updateSignDO.setId(deleteOne.getId());
        updateSignDO.setCompanyId(deleteOne.getCompanyId());
        updateSignDO.setSignStatus(SignStatusEnum.CANCEL_SIGN.getType());//作废
        Integer updateByPrimaryKeySelectiveRES = signMapper.updateByPrimaryKeySelective(updateSignDO);
        Assert.isTrue(updateByPrimaryKeySelectiveRES != null && updateByPrimaryKeySelectiveRES == 1,"根据签收单id:"+deleteOne.getId()+"删除签收单信息DB失败！");
        //es删除签收单表体
//        List<Long> deleteSignIdList = new ArrayList<>();
//        deleteSignIdList.add(deleteSignId);
//        esSignDetailsService.deleteBySignIds(deleteSignIdList);
        //db删除签收单表体信息
//        Long deleteSignCompanyId = deleteOne.getCompanyId();
//        signDetailDOMapper.deleteBatchBySignId(deleteSignCompanyId,deleteSignIdList);
        return updateSignEsDTORES && updateByPrimaryKeySelectiveRES != null && updateByPrimaryKeySelectiveRES == 1;
    }
//    /**
//     * 更新库存
//     *
//     * @param tmsSignEsDTO
//     */
//    private Boolean noticeStockDomain(TmsSignEsDTO tmsSignEsDTO) {
//        SignEsDTO signEsDTO = tmsSignEsDTO.getSignEsDTO();
//        SignStockReqDTO signStockReqDTO=new SignStockReqDTO();
//
//        EntityUtils.copyProperties(signEsDTO,signStockReqDTO);
//        signStockReqDTO.setSignId(signEsDTO.getId());
//
//        return stockService.reduceStockForSign(signStockReqDTO);
//    }


    /**
     * 更新派车单状态
     *
     * @param tmsSignEsDTO
     */
    private DataResult<Boolean> noticeDispatcherSignStatus(TmsSignEsDTO tmsSignEsDTO) {
        SignEsDTO signEsDTO = tmsSignEsDTO.getSignEsDTO();

        SignNoticeForDispatcherReqDTO reqDTO = new SignNoticeForDispatcherReqDTO();

        EntityUtils.copyProperties(signEsDTO,reqDTO);
        reqDTO.setSignId(signEsDTO.getId());

        DataResult<Boolean> result = dispatcherService.synecDispatcherStatus(reqDTO);

        logger.info("synecDispatcherStatus reuselt :{}",result);

//        if (result.isSuccess()){
//            return result.getResult();
//        }
//        throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "调用外部服务异常:dispatcher::synecDispatcherStatus");
        return result;
    }


    private SignFeeDO assembleSignFee(TmsSignEsDTO tmsSignEsDTO) {
        SignEsDTO signEsDTO = tmsSignEsDTO.getSignEsDTO();
        tmsSignEsDTO.getSignFeeEsDTO().setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_SIGN_FEE));
        tmsSignEsDTO.getSignFeeEsDTO().setSignId(signEsDTO.getId());
        tmsSignEsDTO.getSignFeeEsDTO().setCompanyId(signEsDTO.getCompanyId());
        SignFeeDO signFeeDO = EntityUtils.copyProperties(tmsSignEsDTO.getSignFeeEsDTO(), SignFeeDO.class);
        return signFeeDO;
    }

    private List<SignDetailDO> assembleSignDetails(TmsSignEsDTO tmsSignEsDTO) {
        List<SignDetailDO> detailsDOs = new ArrayList<>();
        SignEsDTO signEsDTO = tmsSignEsDTO.getSignEsDTO();
        if (null != tmsSignEsDTO.getSignDetailsEsDTO() && tmsSignEsDTO.getSignDetailsEsDTO().size() > 0) {
            for (SignDetailsEsDTO signDetailsEsDTO : tmsSignEsDTO.getSignDetailsEsDTO()) {
                signDetailsEsDTO.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_SIGN_DETAILS));
                signDetailsEsDTO.setSignId(signEsDTO.getId());
                signDetailsEsDTO.setCompanyId(signEsDTO.getCompanyId());
                SignDetailDO detailsDO = EntityUtils.copyProperties(signDetailsEsDTO, SignDetailDO.class);
                detailsDOs.add(detailsDO);
            }
        }
        return detailsDOs;
    }

    private SignDO assembleSign(TmsSignEsDTO tmsSignEsDTO,DispatcherDetailEsDTO dispatcherDetail) {

        SignFeeEsDTO fee = tmsSignEsDTO.getSignFeeEsDTO();

        // --> 生成主键id
        SignEsDTO signEsDTO = tmsSignEsDTO.getSignEsDTO();

        signEsDTO.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_SIGN));

        IdCodeReqNewDTO idCodeReqNewDTO = new IdCodeReqNewDTO();
		idCodeReqNewDTO.setGroupId(tmsSignEsDTO.getGroupId());
		idCodeReqNewDTO.setBizCode(TmsCodeRuleBizCode.TMS_NEW_QS);
		DataResult<String> code = idCodeService.generatedNew(idCodeReqNewDTO);
		if (code.getResult()==null){
		    logger.error("generatedNew fail!  result:{}",code);
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN,"获取签收批次号失败");
        }
        signEsDTO.setSignBatchNumber(code.getResult());
        long signTime = System.currentTimeMillis();
        if(tmsSignEsDTO.getSignEsDTO().getSignTime()==null)
        {
            signEsDTO.setSignTime(signTime);
        }
        signEsDTO.setCreateTime(signTime);

        signEsDTO.setTotalReceivable(
                BigDecimal.ZERO
                .add(fee.getCollectPayment()==null?BigDecimal.ZERO:fee.getCollectPayment())
                .add(fee.getToPay() == null ? BigDecimal.ZERO : fee.getToPay())
                .add(fee.getDeliveryFee() == null ? BigDecimal.ZERO : fee.getDeliveryFee())
                .add(fee.getSecondDeliveryFee() == null ? BigDecimal.ZERO : fee.getSecondDeliveryFee())
                .add(fee.getOtherFeeStation() == null ? BigDecimal.ZERO : fee.getOtherFeeStation())
        );

        //赋值到站其他费用
        signEsDTO.setOtherFeeStation(fee.getOtherFeeStation());
        SignDO sign = EntityUtils.copyProperties(signEsDTO, SignDO.class);
        return sign;
    }


    /**
     * @param tmsSignEsDTO
     * @param dispatcherDetail
     * @return
     */
    private boolean checkSignVaildAndSetValue(TmsSignEsDTO tmsSignEsDTO, DispatcherDetailEsDTO dispatcherDetail) {

        //检查派车单状态
        //检查签收状态
        //检查签收边界值

        SignEsDTO signEsDTO = tmsSignEsDTO.getSignEsDTO();

        SignEsDTO exitsSignEsDTO = esSignService.querySignEsDTOByDispatcherDetailId(signEsDTO.getDispatcherDetailId());


        if (exitsSignEsDTO != null) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "此单已签收,不能重复签收");

        }
        //dispatcherDetail.status 是派车单主表状态   明细状态是 detailStatus -.-!
        if (dispatcherDetail.getStatus().intValue() != DispatcherStatusEnum.DEPARTED.getType()) {
            //未装车 不能签收  todo 或者其他状态
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "派车单未装车");
        }

        if (signEsDTO.getSignOrgId()==null){

            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN,"签收网点未填写");
        }

        if (signEsDTO.getWaybillId()==null){
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN,"参数不合法,运单ID为空");

        }

        //冗余上游单据的字段
        signEsDTO.acceptDispatcherDetail(dispatcherDetail); //获取派车单详情数据

        // 签收详情必须不为空
        List<SignDetailsEsDTO> signDetailsEsDTO =tmsSignEsDTO.getSignDetailsEsDTO();
        if (null == signDetailsEsDTO || signDetailsEsDTO.size() == 0) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收详情为空");
        }

        signEsDTO.setDispatcherId(dispatcherDetail.getDispatcherId());

        //拒签-通过拒签按钮进来的
        if (signEsDTO.getSignStatus()!=null&&signEsDTO.getSignStatus().intValue()==SignStatusEnum.REFUSE_SIGN.getType()){
            //设置签收主表
            signEsDTO.setSignNumber(0);
            signEsDTO.setRefuseNumber(dispatcherDetail.getPackageNum());
            signEsDTO.setSignType(SignStatusEnum.REFUSE_SIGN.getType());
            signEsDTO.setSignStatusName(SignStatusEnum.REFUSE_SIGN.getName());
            signEsDTO.setSignTypeName(SignStatusEnum.REFUSE_SIGN.getName());
            signEsDTO.setNormalSignType(NormalSignType.ABNORMAL_SIGN.getType());
            signEsDTO.setNormalSignTypeName(NormalSignType.ABNORMAL_SIGN.getName());
            //设置签收明细表
            signDetailsEsDTO.forEach(e ->{
                e.setSignNumber(0);
                e.setRefuseNumber(e.getPackageNum());
            });
        }else {
            //全部签收  部分签收   通过签收按钮进来的
            Integer signNumber = signEsDTO.getSignNumber();
            Integer refuseNumber = signEsDTO.getRefuseNumber();
            if (signNumber+refuseNumber>dispatcherDetail.getPackageNum()) {
                //边界值判断
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收参数不合法");
            }

            if (signNumber.intValue() == 0 ){
                //全部拒签
                signEsDTO.setSignStatus(SignStatusEnum.REFUSE_SIGN.getType());
                signEsDTO.setSignType(SignStatusEnum.REFUSE_SIGN.getType());
                signEsDTO.setSignStatusName(SignStatusEnum.REFUSE_SIGN.getName());
                signEsDTO.setSignTypeName(SignStatusEnum.REFUSE_SIGN.getName());
                signEsDTO.setNormalSignType(NormalSignType.ABNORMAL_SIGN.getType());
                signEsDTO.setNormalSignTypeName(NormalSignType.ABNORMAL_SIGN.getName());
            }else if (refuseNumber.intValue()>0){
                //部分签收
                signEsDTO.setSignType(SignStatusEnum.PARTIAL_SIGN.getType());
                signEsDTO.setSignTypeName(SignStatusEnum.PARTIAL_SIGN.getName());
                signEsDTO.setSignStatus(SignStatusEnum.PARTIAL_SIGN.getType());
                signEsDTO.setSignStatusName(SignStatusEnum.PARTIAL_SIGN.getName());
                signEsDTO.setNormalSignType(NormalSignType.ABNORMAL_SIGN.getType());
                signEsDTO.setNormalSignTypeName(NormalSignType.ABNORMAL_SIGN.getName());
            }else {
                //全部签收
                signEsDTO.setSignType(SignStatusEnum.SIGNED.getType());
                signEsDTO.setSignTypeName(SignStatusEnum.SIGNED.getName());
                signEsDTO.setSignStatus(SignStatusEnum.SIGNED.getType());
                signEsDTO.setSignStatusName(SignStatusEnum.SIGNED.getName());

                if (signEsDTO.getNormalSignType()==null){
                    signEsDTO.setNormalSignType(NormalSignType.NORMAL_SIGN.getType());
                    signEsDTO.setNormalSignTypeName(NormalSignType.NORMAL_SIGN.getName());
                }
//                signEsDTO.setNormalSignType(NormalSignType.NORMAL_SIGN.getType());
//                signEsDTO.setNormalSignTypeName(NormalSignType.NORMAL_SIGN.getName());
            }


        }




        return true;

    }

}
