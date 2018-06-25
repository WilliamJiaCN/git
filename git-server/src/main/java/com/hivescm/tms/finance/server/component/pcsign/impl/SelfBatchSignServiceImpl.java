package com.hivescm.tms.finance.server.component.pcsign.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.common.exception.TmsBusinessException;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.SelfSignBatchReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.UpdateSignStatusReqDTO;
import com.hivescm.tms.api.dto.es.stock.StockLockSyncDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillStockDetailEsDTO;
import com.hivescm.tms.api.dto.es.stock.request.StockLockReqDTO;
import com.hivescm.tms.api.dto.es.waybill.VehicleTailAfterEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import com.hivescm.tms.api.dto.es.waybill.component.TmsWaybillEsDTO;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptRequirmentTypeEnum;
import com.hivescm.tms.api.enums.biz.sign.NormalSignType;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.stock.StockLockTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillDistributionTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillFeeTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillTrackStatusEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.constants.TmsCodeRuleBizCode;
import com.hivescm.tms.finance.server.component.pcsign.SelfBatchSignService;
import com.hivescm.tms.finance.server.component.pcsign.SignReceiptService;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDetailDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignFeeDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignRefuseDO;
import com.hivescm.tms.finance.server.feign.stock.IStockLockService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.db.DbOperationService;
import com.hivescm.tms.finance.server.service.lock.SignLockService;
import com.hivescm.tms.finance.server.service.pcsign.BillTrackingService;
import com.hivescm.tms.finance.server.service.pcsign.SignBillingFlowService;
import com.hivescm.tms.finance.server.service.pcsign.SignFinanceService;
import com.hivescm.tms.finance.server.service.pcsign.SignStockService;
import com.hivescm.tms.finance.server.service.sign.EsSignDetailsService;
import com.hivescm.tms.finance.server.service.sign.EsSignFeeService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;
import com.hivescm.tms.intranet.gateway.api.dto.IdCode.IdCodeReqNewDTO;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.IdCodeService;
import com.hivescm.tms.utils.ThreadLocalUtils;

/**
 * @author boqiang.deng
 * @date 2018年3月30日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Service
public class SelfBatchSignServiceImpl implements SelfBatchSignService {

	private Logger logger = LoggerFactory.getLogger(SelfBatchSignServiceImpl.class);
	@Autowired
	private IGeneratedIdService generatedIdService;

	@Autowired
	private EsSignService esSignService;

	@Autowired
	private EsSignDetailsService esSignDetailsService;

	@Autowired
	private EsSignFeeService esSignFeeService;

	@Autowired
	private DbOperationService dbOperationService;

	@Autowired
	private WaybillService waybillService;

	@Autowired
	private IdCodeService idCodeService;
	
	@Autowired
	private SignLockService signLockService;
	
	@Autowired
	private SignBillingFlowService signBillingFlowService;
	
	@Autowired
	private SignReceiptService signReceiptService;
	
	private ThreadLocal<List<SignEsDTO>> signEsDTOs = new ThreadLocal<>();
	private ThreadLocal<List<SignDO>> signDOs = new ThreadLocal<>();
	private ThreadLocal<List<SignDetailsEsDTO>> signDetailsEsDTOs = new ThreadLocal<>();
	private ThreadLocal<List<SignDetailDO>> signDetailDOs = new ThreadLocal<>();
	private ThreadLocal<List<SignFeeEsDTO>> signFeeEsDTOs = new ThreadLocal<>();
	private ThreadLocal<List<SignFeeDO>> signFeeDOs = new ThreadLocal<>();
	@Autowired
	private BillTrackingService billTrackingService;
	private ExecutorService executor = Executors.newFixedThreadPool(5);
	@Autowired
	private SignFinanceService signFinanceService;
	
	@Autowired
	private SignStockService signStockService;
	
	@Autowired
	private  IStockLockService iStockLockService;
	@Override
	public Boolean batchSign(SelfSignBatchReqDTO req) {
		List<String> list = new ArrayList<>();
		try {
			this.lockStock(req.getOrgId(), req.getWaybillIds());
			// --> 查询每个运单对应的在库数量
			List<TmsWaybillEsDTO> waybills = serachWaybill(req);
			list = waybills.stream().map(a->a.getCode()+a.getCompanyId()).collect(Collectors.toList());
			// 锁定运单防止重复操作
			lockWaybill(list);
			// --> 减库存参数
			List<StockLockSyncDTO> stocks = new ArrayList<>();
			// 更新运单参数
			List<UpdateSignStatusReqDTO> reqList = new ArrayList<>();
			// 跟踪参数
			List<VehicleTailAfterEsDTO> vehicleTailAfterEsDTOs = new ArrayList<>();
			// 计费参数
			List<Long>  billFolwWaybillIds = new ArrayList<>();
			// --> 生成签收信息
			saveSign(waybills, stocks, req, reqList,vehicleTailAfterEsDTOs,billFolwWaybillIds);
			// --> 批量更新运单状态
			updateWaybill(reqList);
			// --> 减库存
			reduceStock(stocks);
			// --> 开启线程处理运单跟踪和计费
			this.newThread(vehicleTailAfterEsDTOs,billFolwWaybillIds);
			// --> 签收生成应收
			sendFinanceMq(waybills);
			// --> 接触锁定
			unlockWaybill(list);
			return true;
		} catch (SystemException se) {
			logger.error(se.getMessage(), se);
			throw se;
		} catch (Exception e) {
			unlockStock(req.getOrgId(), req.getWaybillIds());
			unlockWaybill(list);
			// TODO 回滚操作
			//rollBack(db,es,status,upWaybillStatus, tmsSignEsDTO)
			TmsBusinessException ex = ExceptionFactory.ex(ExceptionCodeConstants.ERROR_SELF_BATCH_SIGN, e,
					"自提批量签收失败,SelfSignBatchReqDTO:%s", req.toString());
			logger.error(ex.getMessage(), e);
			throw ex;
		}
	}
	/**
	 * 锁定库存
	 * @param orgId
	 * @param waybillId
	 * @return
	 */
	private Boolean lockStock(Long orgId,List<Long> waybillId){
		StockLockReqDTO req = new StockLockReqDTO();
		req.setOrgId(orgId);
		req.setWaybillId(waybillId);
		DataResult<Boolean> lock = iStockLockService.locks(req);
		if(lock.getResult() == null || !lock.isSuccess() || !lock.getResult()){
			throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_LOCK, "该运单正被其他用户操作");
		}
		return lock.isSuccess();
		
	}
	/**
	 * 释放锁定库存
	 * @param orgId
	 * @param waybillId
	 * @return
	 */
	private Boolean unlockStock(Long orgId,List<Long> waybillId){
		StockLockReqDTO req = new StockLockReqDTO();
		req.setOrgId(orgId);
		req.setWaybillId(waybillId);
		DataResult<?> lock = iStockLockService.unlocks(req);
		if(!lock.isSuccess()){
			throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_LOCK, "该运单正被其他用户操作");
		}
		return lock.isSuccess();
		
	}
	/**
	 * 起新线程 执行跟踪信息和推计费流水
	 * @param tmsSignEsDTO
	 * @param waybill
	 */
	private void newThread(List<VehicleTailAfterEsDTO> vehicleTailAfterEsDTOs,List<Long>  billFolwWaybillIds){
		executor.submit(()->{
			try {
				//运单跟踪
				billTrackingService.billTracking(vehicleTailAfterEsDTOs);
				//当运单的签收状态为空，或签收状态为未签收时推送计费流水
				signBillingFlowService.billingFlow(billFolwWaybillIds, SignStatusEnum.SIGNED.getType());
			} catch (Exception e) {
				// 子线程内异常不能反馈，吃掉异常，单据失败
				logger.error("同步运单跟踪信息失败 ", e);
			}
		});
	}
	/**
	 * 校验数据
	 * @param req
	 * @return
	 */
	private List<TmsWaybillEsDTO> serachWaybill(SelfSignBatchReqDTO req){
		List<TmsWaybillEsDTO> waybills = waybillService.queryBatchWaybillDetails(req).getResult();
		if(CollectionUtils.isEmpty(waybills)){
			 throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_LOCK,"运单已经被其他用户签收");
		}
		return waybills;
	}
	/**
	 * 调用生成应收
	 * @param waybill
	 */
	private void sendFinanceMq(List<TmsWaybillEsDTO> waybill){
		if(!CollectionUtils.isEmpty(waybill)){
			List<Long> waybillIds = waybill.stream().map(a->a.getId()).collect(Collectors.toList());
			signFinanceService.sendMq(waybillIds, SignStatusEnum.SIGNED.getType());
		}
	}
	/**
	 * 运单跟踪信息
	 * @param tmsSignEsDTO
	 */
	private void billtracking(SignEsDTO sign,List<VehicleTailAfterEsDTO> vehicleTailAfterEsDTOs){
		VehicleTailAfterEsDTO vehicleTailAfterEsDTO = new VehicleTailAfterEsDTO();
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
		vehicleTailAfterEsDTOs.add(vehicleTailAfterEsDTO);
	}
	/**
	 * 防止重复签收
	 * @param waybillCode
	 */
	private void lockWaybill(List<String> waybillcodes){
		// 锁定运单防止重复操作
		if(!signLockService.lockSign(waybillcodes)){
			 throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_LOCK, waybillcodes.toString()+"运单正在被其他用户操作");
		};
	}
	/**
	 * 解除锁定
	 * @param waybillCode
	 */
	private void unlockWaybill(List<String> waybillcodes){
		// 锁定运单防止重复操作
		signLockService.unlockSign(waybillcodes);
	}
	
	/**
	 * 减库存
	 * @param stocks
	 * @return
	 */
	private void reduceStock(List<StockLockSyncDTO> stocks){
		signStockService.sendMq(stocks);
	}
	/**
	 * 更新运单
	 * @param tmsSignEsDTO
	 * @return
	 */
	private DataResult<Boolean> updateWaybill(List<UpdateSignStatusReqDTO> reqList){
		DataResult<Boolean> upWaybillStatus = waybillService.updateWyabillInfoFromSign(reqList);
		if (null == upWaybillStatus || !upWaybillStatus.getResult()) {
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN,
					"批量签收更新运单失败,status:%s" + upWaybillStatus.getStatus());
		}
		return upWaybillStatus;
	}
	/**
	 * 数据回滚
	 * @param es
	 * @param status
	 * @param upWaybillStatus
	 */
	private void rollBack(Boolean db,Boolean es,DataResult<Integer> status,DataResult<Boolean> upWaybillStatus,TmsSignEsDTO tmsSignEsDTO){
		if((db && !es)||(null == status || 0==status.getResult().intValue())||(null == upWaybillStatus || !upWaybillStatus.getResult())){
			//删除数据库
			rollBackDb(tmsSignEsDTO);
			//删除es
			esSignService.deleteBySignIds(null);
			esSignDetailsService.deleteBySignIds(null);
			esSignFeeService.deleteBySignIds(null);
		}
		if(null == upWaybillStatus || !upWaybillStatus.getResult()){
			//TODO回滚库存
		}
	}
	/**
	 * 删除数据库数据
	 * @param tmsSignEsDTO
	 */
	private void rollBackDb(TmsSignEsDTO tmsSignEsDTO){
		SignDO signDO = new SignDO();
		signDO.setCompanyId(tmsSignEsDTO.getSignEsDTO().getCompanyId());
		SignRefuseDO signRefuseDO = new SignRefuseDO();
		signRefuseDO.setCompanyId(tmsSignEsDTO.getSignEsDTO().getCompanyId());
		SignDetailDO details = new SignDetailDO();
		details.setCompanyId(tmsSignEsDTO.getSignEsDTO().getCompanyId());
		SignFeeDO signFeeDO = new SignFeeDO();
		signFeeDO.setCompanyId(tmsSignEsDTO.getSignEsDTO().getCompanyId());
		List<Long> arrayList = new ArrayList<>();
		arrayList.add(tmsSignEsDTO.getSignEsDTO().getId());
		dbOperationService.delete(signDO, details, signFeeDO, signRefuseDO, arrayList);
	}
	/**
	 * 生成签收信息
	 * 
	 * @param waybills
	 * @param stocks
	 * @param waybillParam
	 * @return
	 */
	private Boolean saveSign(List<TmsWaybillEsDTO> waybills, List<StockLockSyncDTO> stocks,
			SelfSignBatchReqDTO req, List<UpdateSignStatusReqDTO> reqList,List<VehicleTailAfterEsDTO> vehicleTailAfterEsDTOs,List<Long>  billFolwWaybillIds) {
		List<Long> signIds = generatedIdService.getIncrBatchUniqueId(GeneratedIdConstants.TMS_SIGN, waybills.size());
		List<Long> signFeeIds = generatedIdService.getIncrBatchUniqueId(GeneratedIdConstants.TMS_SIGN_FEE,
				waybills.size());
		for (int i = 0; i < signIds.size(); i++) {
			TmsWaybillEsDTO waybill = waybills.get(i);
			compParam(waybill, signIds.get(i), req, stocks, signFeeIds.get(i), reqList,vehicleTailAfterEsDTOs,billFolwWaybillIds);
		}
		Boolean db = saveDB(this.signDOs.get(), this.signDetailDOs.get(), this.signFeeDOs.get());
		Boolean es = saveEs(this.signEsDTOs.get(), this.signDetailsEsDTOs.get(), this.signFeeEsDTOs.get());
		return db && es;
	}

	/**
	 * 组装参数
	 * 
	 * @param waybill
	 * @return
	 */
	private void compParam(TmsWaybillEsDTO waybill, Long signId, SelfSignBatchReqDTO req,
			List<StockLockSyncDTO> stocks, Long signFeeId, List<UpdateSignStatusReqDTO> reqList,List<VehicleTailAfterEsDTO> vehicleTailAfterEsDTOs,List<Long>  billFolwWaybillIds) {
		List<SignDetailsEsDTO> signDetails = new ArrayList<>();
		List<SignDetailDO> signDetailDos = new ArrayList<>();
		// TODO调用签收批次号
		List<Long> signDetailIds = generatedIdService.getIncrBatchUniqueId(GeneratedIdConstants.TMS_SIGN_DETAILS,
				waybill.getWaybillStockDetailEsDTOs().size());
		Integer signNumber = 0;
		for (int i = 0; i < signDetailIds.size(); i++) {
			// 组装签收明细信息
			SignDetailsEsDTO signDetail = compSignDetail(waybill.getWaybillStockDetailEsDTOs().get(i),
					signDetailIds.get(i), req);
			signDetail.setSignId(signId);
			signDetail.setWaybillId(waybill.getId());
			SignDetailDO detailDo = EntityUtils.copyProperties(signDetail, SignDetailDO.class);
			signDetails.add(signDetail);
			signDetailDos.add(detailDo);
			// 组装减库存操作
			compReduceStock(stocks, req, waybill.getWaybillStockDetailEsDTOs().get(i),waybill.getId());
			signNumber = signNumber + signDetail.getSignNumber();
		}
		// 组装更新运单数据
		UpdateSignStatusReqDTO reqWaybillParam = new UpdateSignStatusReqDTO();
		reqWaybillParam.setSignNumber(signNumber);
		reqWaybillParam.setRefuseNumber(0);
		reqWaybillParam.setWaybillId(waybill.getId());
		reqWaybillParam.setTypeEnum(WaybillDistributionTypeEnum.GET);
		reqWaybillParam.setCompanyId(waybill.getCompanyId().longValue());
		reqList.add(reqWaybillParam);
		// 组装费用表信息
		SignFeeEsDTO fee = compSignFee(waybill, req, signFeeId, signId);
		SignFeeDO feedo = EntityUtils.copyProperties(fee, SignFeeDO.class);
		// 组装签收主表信息
		signComp(waybill, signId, signDetails, fee, req, signNumber,vehicleTailAfterEsDTOs,billFolwWaybillIds);
		ThreadLocalUtils.isNotNull(signDetailsEsDTOs).get().addAll(signDetails);
		ThreadLocalUtils.isNotNull(signDetailDOs).get().addAll(signDetailDos);
		ThreadLocalUtils.isNotNull(signFeeEsDTOs).get().add(fee);
		ThreadLocalUtils.isNotNull(signFeeDOs).get().add(feedo);
	}

	/**
	 * 组装减库存操作
	 * 
	 * @param stocks
	 * @param req
	 * @param waybillStockDetailEsDTO
	 */
	private void compReduceStock(List<StockLockSyncDTO> stocks, SelfSignBatchReqDTO req,
			WaybillStockDetailEsDTO waybillStockDetailEsDTO,Long waybillId) {
		// 组装减库存信息
		StockLockSyncDTO stockDetail = new StockLockSyncDTO();
		stockDetail.setBranchId(req.getOrgId().intValue());
		stockDetail.setCompanyId(req.getCompanyId());
		stockDetail.setSignNum(waybillStockDetailEsDTO.getPackageNum());
		stockDetail.setStockDetailId(waybillStockDetailEsDTO.getId());
		stockDetail.setRefusalNum(0);
		stockDetail.setWaybillId(waybillId);
		stockDetail.setStockLockType(StockLockTypeEnum.SELF_REFERENCE);
		stocks.add(stockDetail);
	}

	/**
	 * 组装签收信息
	 * 
	 * @param waybill
	 */
	private void signComp(TmsWaybillEsDTO waybill, Long id, List<SignDetailsEsDTO> signDetails, SignFeeEsDTO fee,
			SelfSignBatchReqDTO req, Integer signNumber,List<VehicleTailAfterEsDTO> vehicleTailAfterEsDTOs,List<Long>  billFolwWaybillIds) {
		// --> 生成主键批次号
		IdCodeReqNewDTO idCodeReqNewDTO = new IdCodeReqNewDTO();
		idCodeReqNewDTO.setGroupId(req.getGroupId());
		idCodeReqNewDTO.setBizCode(TmsCodeRuleBizCode.TMS_NEW_QS);
		DataResult<String> code = idCodeService.generatedNew(idCodeReqNewDTO);
		SignEsDTO sign = new SignEsDTO();
		sign.setSignOrgId(req.getOrgId().intValue());
		sign.setSignOrgName(req.getOrgName());
		sign.setSignBatchNumber(code.getResult());
		sign.setCode(waybill.getCode());
		sign.setId(id);
		sign.setCompanyId(waybill.getCompanyId().longValue());
		sign.setWaybillId(waybill.getId());
		sign.setSignPeople(waybill.getReceiptUser());
		sign.setReceiptUser(waybill.getReceiptUser());
		sign.setIreceiver(true);
		sign.setPhoneNo(waybill.getReceiptConsignorMobleNo());
		sign.setIdCard(waybill.getReceiptIdentityCard());
		sign.setIcashierConfirm(false);
		sign.setDeliveryType(WaybillDistributionTypeEnum.GET.getType());
		sign.setDeliveryTypeName(WaybillDistributionTypeEnum.GET.getName());
		sign.setSignStatus(SignStatusEnum.SIGNED.getType());
		sign.setSignStatusName(SignStatusEnum.SIGNED.getName());
		//正常签收 异常签收 用新字段
		sign.setSignType(SignStatusEnum.SIGNED.getType());


		sign.setNormalSignType(NormalSignType.NORMAL_SIGN.getType());
		sign.setNormalSignTypeName(NormalSignType.NORMAL_SIGN.getName());

		sign.setSignNumber(signNumber);
		sign.setRefuseNumber(0);
		sign.setHandler(req.getHandler());
		sign.setHandlerName(req.getHandlerName());
		sign.setCreateTime(System.currentTimeMillis());
		sign.setSignTime(System.currentTimeMillis());
		sign.setCreateUser(req.getHandler());
		sign.setCreateUserName(req.getHandlerName());
		sign.setGoodsName(waybill.getGoodsName());
		sign.setCreateNumber(waybill.getTotalNum());
		sign.setUnsignedNumber(waybill.getTotalNum() - signNumber-(waybill.getTotalRefuseSignNumber()==null?0:waybill.getTotalRefuseSignNumber())-(waybill.getTotalSignNumber()==null?0:waybill.getTotalSignNumber()));
		sign.setSignWeight(waybill.getWeight().divide(new BigDecimal(waybill.getTotalNum()),2, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(signNumber)).setScale(2, BigDecimal.ROUND_HALF_UP));
		sign.setSignVolume(waybill.getVolume().divide(new BigDecimal(waybill.getTotalNum()),2, BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal(signNumber)).setScale(2, BigDecimal.ROUND_HALF_UP));
		sign.setTotalReceivable(fee.getCollectPayment().add(fee.getToPay() == null ? new BigDecimal(0) : fee.getToPay())
				.add(fee.getDeliveryFee() == null ? new BigDecimal(0) : fee.getDeliveryFee())
				.add(fee.getSecondDeliveryFee() == null ? new BigDecimal(0) : fee.getSecondDeliveryFee())
				.add(fee.getOtherFeeStation() == null ? new BigDecimal(0) : fee.getOtherFeeStation()));
		sign.setDestOrgId(waybill.getDestOrgId());
		sign.setDestOrgName(waybill.getDestOrgName());
		sign.setInvoiceOrgId(waybill.getInvoiceOrgId());
		sign.setInvoiceOrgName(waybill.getInvoiceOrgName());
		sign.setInvoiceUser(waybill.getInvoiceUser());
		sign.setReceiptUser(waybill.getReceiptUser());
		sign.setIbackConfirm(false);
		SignDO signdb = EntityUtils.copyProperties(sign, SignDO.class);
		//当运单的签收状态为空，或签收状态为未签收时推送计费流水
		if(null == waybill.getSignStatus() || waybill.getSignStatus().intValue() == SignStatusEnum.NO_SIGN.getType()){
			billFolwWaybillIds.add(waybill.getId());
		}
		ThreadLocalUtils.isNotNull(signDOs).get().add(signdb);
		ThreadLocalUtils.isNotNull(signEsDTOs).get().add(sign);
		billtracking(sign,vehicleTailAfterEsDTOs);
		//恶心的设计我也不想这样写，后期可能走mq
		TmsSignEsDTO tmsSignEsDTO = new TmsSignEsDTO();
		tmsSignEsDTO.setSignEsDTO(sign);
		receipt( tmsSignEsDTO,waybill);
	}

	/**
	 * 组装签收明细信息
	 * 
	 * @param stockDetail
	 * @return
	 */
	private SignDetailsEsDTO compSignDetail(WaybillStockDetailEsDTO stockDetail, Long id, SelfSignBatchReqDTO req) {
		SignDetailsEsDTO signDetail = new SignDetailsEsDTO();
		signDetail.setId(id);
		signDetail.setCompanyId(req.getCompanyId());
		signDetail.setGoodsName(stockDetail.getGoodsName());
		signDetail.setWaybillGoodsId(stockDetail.getGoodsId());
		signDetail.setWaybillStockDetailId(stockDetail.getId());
		signDetail.setSignNumber(stockDetail.getPackageNum());
		signDetail.setRefuseNumber(0);
		signDetail.setPackageNum(stockDetail.getPackageNum());
		signDetail.setUnsignedNumber(stockDetail.getTotalNum() - stockDetail.getPackageNum());
		signDetail.setCreateTime(System.currentTimeMillis());
		signDetail.setCreateUser(req.getHandler());
		signDetail.setPackages(stockDetail.getPackingName());
		signDetail.setCreateNumber(stockDetail.getTotalNum());
		signDetail.setDispatcherNumber(stockDetail.getPackageNum());
		return signDetail;
	}

	/**
	 * TODO 组装签收费用信息
	 * 
	 * @param waybill
	 * @return
	 */
	private SignFeeEsDTO compSignFee(TmsWaybillEsDTO waybill, SelfSignBatchReqDTO req, Long signFeeId, Long signId) {
		SignFeeEsDTO fee = new SignFeeEsDTO();
		fee.setWaybillId(waybill.getId());
		fee.setId(signFeeId);
		fee.setSignId(signId);
		fee.setCreateUser(req.getHandler());
		fee.setCreateTime(System.currentTimeMillis());
		fee.setCompanyId(waybill.getCompanyId().longValue());
		Map<Integer, WaybillFeeEsDTO> waybillFee = waybill.getWaybillFeeEsDtos().stream()
				.collect(Collectors.toMap(WaybillFeeEsDTO::getAttrId, a -> a));
		// TODO费用
		fee.setCollectPayment(waybillFee.get(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType()) == null ? new BigDecimal(0)
				: waybillFee.get(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType()).getFee());
		return fee;
	}

	/**
	 * 保存签收数据库
	 * 
	 * @param signDO
	 * @param signDetailDOs
	 * @param signFeeDO
	 * @return
	 */
	private Boolean saveDB(List<SignDO> signDOs, List<SignDetailDO> signDetailDOs, List<SignFeeDO> signFeeDOs) {
		return dbOperationService.saveBatchDB(signDOs, signDetailDOs, signFeeDOs);
	}

	/**
	 * 保存签收es信息
	 * 
	 * @param signEsDTOs
	 * @param signDetailsEsDTOList
	 * @param signFeeEsDTO
	 * @return
	 */
	private Boolean saveEs(List<SignEsDTO> signEsDTOs, List<SignDetailsEsDTO> signDetailsEsDTOList,
			List<SignFeeEsDTO> signFeeEsDTO) {
		return esSignService.insertBatchSignEs(signEsDTOs)
				&& esSignDetailsService.insertSignDetailsEsDTO(signDetailsEsDTOList)
				&& esSignFeeService.insertBatchSignFeeEs(signFeeEsDTO);
	}
	
	/**
	 * 签回单逻辑
	 * @param tmsSignEsDTO
	 * @return
	 */
	private Boolean receipt(TmsSignEsDTO tmsSignEsDTO,TmsWaybillEsDTO waybill){
		if(waybill.getBackTypeValue() !=null && (ReceiptRequirmentTypeEnum.SIGN_BILL.getType()==waybill.getBackTypeValue().intValue() || ReceiptRequirmentTypeEnum.SIGN_GRASP.getType()==waybill.getBackTypeValue().intValue() || ReceiptRequirmentTypeEnum.SIGN_RECEIPT.getType()==waybill.getBackTypeValue().intValue())){
			signReceiptService.signReceipt(tmsSignEsDTO);
		}
		return true;
	}
}
