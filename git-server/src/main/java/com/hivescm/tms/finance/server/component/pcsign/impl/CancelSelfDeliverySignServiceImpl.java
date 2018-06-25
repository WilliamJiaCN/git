package com.hivescm.tms.finance.server.component.pcsign.impl;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.hivescm.tms.api.dto.es.finance.request.FinanceDeleteReqDTO;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.CancelSelfDeliverySignReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.UpdateSignStatusReqDTO;
import com.hivescm.tms.api.dto.es.stock.StockLockSyncDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillStockDetailEsDTO;
import com.hivescm.tms.api.dto.es.stock.request.StockLockReqDTO;
import com.hivescm.tms.api.enums.biz.sign.RefuseSignTypeEnum;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.stock.StockLockTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillDistributionTypeEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.component.pcsign.CancelSelfDeliverySignService;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignRefuseDO;
import com.hivescm.tms.finance.server.feign.stock.IStockLockService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.db.DbOperationService;
import com.hivescm.tms.finance.server.service.finance.FinanceManageReceiptService;
import com.hivescm.tms.finance.server.service.lock.SignLockService;
import com.hivescm.tms.finance.server.service.pcsign.SignBillingFlowService;
import com.hivescm.tms.finance.server.service.pcsign.SignFinanceService;
import com.hivescm.tms.finance.server.service.pcsign.SignStockService;
import com.hivescm.tms.finance.server.service.sign.EsSignDetailsService;
import com.hivescm.tms.finance.server.service.sign.EsSignRefuseService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;

@Service
public class CancelSelfDeliverySignServiceImpl implements CancelSelfDeliverySignService {
	private Logger logger = LoggerFactory.getLogger(CancelSelfDeliverySignServiceImpl.class);

	@Autowired
	private EsSignDetailsService esSignDetailsService;
	@Autowired
	private EsSignService esSignService;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private EsSignRefuseService esSignRefuseService;
	@Autowired
	private DbOperationService dbOperationService;
	@Autowired
	private SignLockService signLockService;
    @Autowired
    private FinanceManageReceiptService financeManageReceiptService;
    @Autowired
	private SignFinanceService signFinanceService;
    
    @Autowired
	private SignBillingFlowService signBillingFlowService;
    
    @Autowired
	private SignStockService signStockService;
    
    @Autowired
	private  IStockLockService iStockLockService;
	
    private ExecutorService executor = Executors.newFixedThreadPool(5);
	@Override
	public Boolean cancelSelfDeliverySign(CancelSelfDeliverySignReqDTO cancelSelfDeliverySignReqDTO) {
		List<String> list = new ArrayList<>();
		List<Long> waybillIds = new ArrayList<>();
		Long orgId = null;
		try {
			// -> 查询签收货物明细信息
			List<SignDetailsEsDTO> signDetailsEsDTOs = esSignDetailsService
					.queryBySignIds(cancelSelfDeliverySignReqDTO.getSignIds());
			List<Long> signids = signDetailsEsDTOs.stream().map(a -> a.getSignId()).distinct()
					.collect(Collectors.toList());
			List<SignEsDTO> signEsDTO = esSignService.querySignEsByIds(signids);
			if(CollectionUtils.isEmpty(signEsDTO)){
				return false;
			}
			List<SignEsDTO> signEsDTOs = checkCancel(signEsDTO);
			waybillIds = signEsDTOs.stream().map(a->a.getWaybillId()).distinct().collect(Collectors.toList());
			orgId = signEsDTOs.get(0).getSignOrgId().longValue();
			//this.lockStock(orgId, waybillIds);
			list = signEsDTOs.stream().map(a->a.getCode()).distinct().collect(Collectors.toList());
			// 锁定运单防止重复操作
			lockWaybill(list);
			// 查询拒签库存锁定数量
			List<WaybillStockDetailEsDTO> stockDetails = queryRefuseLockNum(signDetailsEsDTOs,signEsDTOs.get(0).getSignOrgId().longValue());
			// 更新签收信息
			updateSignInfo(cancelSelfDeliverySignReqDTO, signids);
			// ->更新运单状态
			updateWyabillInfoFromSign(signEsDTOs,cancelSelfDeliverySignReqDTO);
			// ->回滚库存
			updateSignEscStockNum(stockDetails,signDetailsEsDTOs,signEsDTOs.get(0).getSignOrgId(),cancelSelfDeliverySignReqDTO);
			// 删除计费流水
			newThread(signEsDTOs);
			// 取消签收删除应收
			sendFinanceMq(signEsDTOs);
			unlockWaybill(list);
			return true;
		} catch (SystemException se) {
			logger.error(se.getMessage(), se);
			throw se;
		} catch (Exception e) {
			unlockStock(orgId, waybillIds);
			unlockWaybill(list);
			// TODO 回滚操作
			TmsBusinessException ex = ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN, e,
					"取消签收失败,TmsSignEsDTO:%s", cancelSelfDeliverySignReqDTO.toString());
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
	 * 起新线程 推计费流水
	 * @param tmsSignEsDTO
	 * @param waybill
	 */
	private void newThread(List<SignEsDTO> signEsDTOs){
		executor.submit(()->{
			try {
				//取消签收删除计费流水，只有当运单全部取消时才删除，逻辑在财务那块做
				List<Long> waybillids = signEsDTOs.stream().map(a->a.getWaybillId()).collect(Collectors.toList());
				signBillingFlowService.billingFlow(waybillids, SignStatusEnum.CANCEL_SIGN.getType());
			} catch (Exception e) {
				// 子线程内异常不能反馈，吃掉异常，单据失败
				logger.error("同步运单跟踪信息失败 ", e);
			}
		});
	}

	/**
	 * 检查能否取消
	 * @param signEsDTO
	 * @return
	 */
	private List<SignEsDTO> checkCancel(List<SignEsDTO> signEsDTO){
		List<SignEsDTO> signEsDTOs = new ArrayList<>();
		signEsDTO.forEach(a->{
			FinanceDeleteReqDTO financeDeleteReqDTO = new FinanceDeleteReqDTO();
			financeDeleteReqDTO.setCode(a.getCode());
			financeDeleteReqDTO.setCompanyId(a.getCompanyId().longValue());
			financeDeleteReqDTO.setWaybillId(a.getWaybillId());
			Boolean flag = financeManageReceiptService.getPayStatusByCodeSign(financeDeleteReqDTO);
			if(flag){
				signEsDTOs.add(a);
			}
		});
		if(CollectionUtils.isEmpty(signEsDTOs)){
			throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_LOCK,"运单已经收银审核不能取消");
		}
		return signEsDTOs;
		
	}
	/**
	 * 调用生成应收
	 * @param waybill
	 */
	private void sendFinanceMq(List<SignEsDTO> signEsDTOs){
		List<Long> waybillIds = signEsDTOs.stream().map(a->a.getWaybillId()).collect(Collectors.toList());
		signFinanceService.sendMq(waybillIds, SignStatusEnum.CANCEL_SIGN.getType());
	}
	/**
	 * 查询拒签锁定数量
	 * @param signDetailsEsDTOs
	 * @param orgId
	 * @return
	 */
	private List<WaybillStockDetailEsDTO> queryRefuseLockNum(List<SignDetailsEsDTO> signDetailsEsDTOs,Long orgId){
		List<Long> stockDetailIds = signDetailsEsDTOs.stream().map(a -> a.getWaybillStockDetailId()).distinct()
				.collect(Collectors.toList());
		DataResult<List<WaybillStockDetailEsDTO>> stockDetailsResult = waybillService
				.getRefuseLockNum(stockDetailIds, orgId);
		if (null == stockDetailsResult || stockDetailsResult.isFailed()) {
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN, "取消签收查询拒签锁定数量失败");
		}
		return stockDetailsResult.getResult();
	}
	/**
	 * 库存操作
	 * @param stockDetails
	 * @param signDetailsEsDTOs
	 * @param orgId
	 * @param cancelSelfDeliverySignReqDTO
	 * @return
	 */
	private void updateSignEscStockNum(List<WaybillStockDetailEsDTO> stockDetails,List<SignDetailsEsDTO> signDetailsEsDTOs,Integer orgId,CancelSelfDeliverySignReqDTO cancelSelfDeliverySignReqDTO){
		List<StockLockSyncDTO> stocks = rollbackStock(signDetailsEsDTOs, stockDetails,
				cancelSelfDeliverySignReqDTO.getCompanyId(), orgId);
		signStockService.sendMq(stocks);
	}
	/**
	 * 运单操作
	 * @param signEsDTOs
	 * @param cancelSelfDeliverySignReqDTO
	 * @return
	 */
	private DataResult<Boolean> updateWyabillInfoFromSign(List<SignEsDTO> signEsDTOs,CancelSelfDeliverySignReqDTO cancelSelfDeliverySignReqDTO){
		List<UpdateSignStatusReqDTO> reqList = compWaybillUpdateParam(signEsDTOs,
				cancelSelfDeliverySignReqDTO.getCompanyId());
		DataResult<Boolean> upWaybillStatus = waybillService.updateWyabillInfoFromSign(reqList);
		if (null == upWaybillStatus || !upWaybillStatus.getResult()) {
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_CANCEL_SIGN,
					"取消签收更新运单失败,,status:%s" + upWaybillStatus.getStatus());
		}
		return upWaybillStatus;
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
	 * 更新签收信息
	 * 
	 * @param cancelSelfDeliverySignReqDTO
	 * @param signIds
	 * @return
	 */
	private Boolean updateSignInfo(CancelSelfDeliverySignReqDTO cancelSelfDeliverySignReqDTO, List<Long> signIds) {
		// 更新数据库签收主表和拒签表
		ArrayList<SignEsDTO> signEsDTO = new ArrayList<>();
		signIds.forEach(a -> {
			SignEsDTO signEsDO = new SignEsDTO();
			signEsDO.setId(a);
			signEsDO.setSignStatus(SignStatusEnum.CANCEL_SIGN.getType());
			signEsDO.setSignStatusName(SignStatusEnum.CANCEL_SIGN.getName());
			signEsDO.setUpdateTime(System.currentTimeMillis());
			signEsDO.setUpdateUser(cancelSelfDeliverySignReqDTO.getUpdateUser());
			signEsDO.setUpdateUserName(cancelSelfDeliverySignReqDTO.getUpdateUserName());
			signEsDO.setCompanyId(cancelSelfDeliverySignReqDTO.getCompanyId());
			signEsDTO.add(signEsDO);
		});
		// 查询拒收信息
		List<SignRefuseEsDTO> refuseSign = esSignRefuseService.queryRefuseSignBySignIds(signIds);
		List<Long> refuseids = new ArrayList<>();
		List<SignRefuseEsDTO> signRefuses = new ArrayList<>();
		if (!CollectionUtils.isEmpty(refuseSign)) {
			refuseids = refuseSign.stream().map(a -> a.getId()).collect(Collectors.toList());
			refuseSign.forEach(a -> {
				SignRefuseEsDTO signRefuseEsDTO = new SignRefuseEsDTO();
				signRefuseEsDTO.setId(a.getId());
				signRefuseEsDTO.setRefuseType(RefuseSignTypeEnum.getRefuseSignTypeEnum(a.getRefuseType()).toCancelRefuseSignType().getType());
				signRefuseEsDTO.setUpdateTime(System.currentTimeMillis());
				signRefuseEsDTO.setUpdateUser(cancelSelfDeliverySignReqDTO.getUpdateUser());
				signRefuses.add(signRefuseEsDTO);
			});
		}
		SignDO signDO = new SignDO();
		signDO.setCompanyId(cancelSelfDeliverySignReqDTO.getCompanyId());
		signDO.setSignStatus(SignStatusEnum.CANCEL_SIGN.getType());
		signDO.setUpdateTime(System.currentTimeMillis());
		signDO.setUpdateUser(cancelSelfDeliverySignReqDTO.getUpdateUser());
		SignRefuseDO signRefuseDO = new SignRefuseDO();
		signRefuseDO.setCompanyId(cancelSelfDeliverySignReqDTO.getCompanyId());
		signRefuseDO.setRefuseType(SignStatusEnum.CANCEL_SIGN.getType());
		signRefuseDO.setUpdateTime(System.currentTimeMillis());
		signRefuseDO.setUpdateUser(cancelSelfDeliverySignReqDTO.getUpdateUser());
		Boolean db = dbOperationService.updateBatchSignAndRefuseSignStatusBySignId(signDO, signRefuseDO, signIds,
				refuseids);
		Boolean es = updateEs(signEsDTO, signRefuses);
		return db && es;
	}

	/**
	 * 更新es
	 * 
	 * @param signEs
	 * @param signRefuse
	 * @return
	 */
	private Boolean updateEs(List<SignEsDTO> signEs, List<SignRefuseEsDTO> signRefuse) {
		Boolean flagsign = false, flagRefuseSign = false;
		if (!CollectionUtils.isEmpty(signEs)) {
			flagsign = esSignService.updateSignEsBatchById(signEs);
		}
		if (!CollectionUtils.isEmpty(signRefuse)) {
			flagRefuseSign = esSignRefuseService.updateBatchSignRefuseEsDTO(signRefuse);
		} else {
			flagRefuseSign = true;
		}
		return flagsign && flagRefuseSign;
	}

	/**
	 * 回滚库存表操作
	 * 
	 * @param
	 * @return
	 */
	private List<StockLockSyncDTO> rollbackStock(List<SignDetailsEsDTO> signDetails,
			List<WaybillStockDetailEsDTO> stockDetails, Long companyId, Integer orgId) {
		// 减库存操作
		List<StockLockSyncDTO> stocks = new ArrayList<>();
		// 将库存放在map里方便后面取值
		Map<Long, WaybillStockDetailEsDTO> stockDetailsMap = new HashMap<>();
		if (!CollectionUtils.isEmpty(stockDetails)) {
			stockDetailsMap
					.putAll(stockDetails.stream().collect(Collectors.toMap(WaybillStockDetailEsDTO::getId, a -> a)));
		}
		// 根据库存id
		Map<Long, List<SignDetailsEsDTO>> collect = signDetails.stream()
				.collect(Collectors.groupingBy(SignDetailsEsDTO::groupByWaybillStockDetailId));
		collect.forEach((k, v) -> {
			// 组装减库存信息
			StockLockSyncDTO stockDetail = new StockLockSyncDTO();
			stockDetail.setBranchId(orgId);
			stockDetail.setCompanyId(companyId);
			stockDetail.setStockDetailId(k);
			stockDetail.setStockLockType(StockLockTypeEnum.SELF_REFERENCE_CANCEL);
			v.forEach(a -> {
				// stock.setLockNum((stock.getLockNum()==null?0:stock.getLockNum())+(a.getSignNumber()==null?0:a.getSignNumber())+(a.getRefuseNumber()==null?0:a.getRefuseNumber()));
				// 拒签数量逻辑放在库存那边
				stockDetail.setWaybillId(a.getWaybillId());
				stockDetail.setSignNum((stockDetail.getSignNum() == null ? 0 : stockDetail.getSignNum())
						+ (a.getSignNumber() == null ? 0 : a.getSignNumber()));
				stockDetail.setRefusalNum((stockDetail.getRefusalNum() == null ? 0 : stockDetail.getRefusalNum())
						+ (a.getRefuseNumber() == null ? 0 : a.getRefuseNumber()));
			});
			// 拒签锁定库存数量
			if (!CollectionUtils.isEmpty(stockDetailsMap)) {
				if (null != stockDetailsMap.get(k)) {
					if (null == stockDetailsMap.get(k).getSignStockNum()) {
						stockDetail.setRefusalNum(0);
					}
					if (stockDetailsMap.get(k).getSignStockNum().intValue() < stockDetail.getRefusalNum().intValue()) {
						stockDetail.setRefusalNum(stockDetailsMap.get(k).getSignStockNum());
					}
				}
			}
			stocks.add(stockDetail);
		});
		return stocks;
	}

	/**
	 * 根据查询签收信息来更新运单状态
	 * 
	 * @param waybillIds
	 * @return
	 */
	private List<UpdateSignStatusReqDTO> compWaybillUpdateParam(List<SignEsDTO> signEsDTOs, Long companyId) {

		List<UpdateSignStatusReqDTO> reqList = new ArrayList<>();
		if (null != signEsDTOs && signEsDTOs.size() > 0) {
			Map<Long, List<SignEsDTO>> map = signEsDTOs.stream()
					.collect(Collectors.groupingBy(SignEsDTO::groupKeyByWaybillId));
			map.forEach((k, v) -> {
				Integer signNumber = v.stream().mapToInt(SignEsDTO::getSumBySignNumber).sum();
				Integer refuseNumber = v.stream().mapToInt(SignEsDTO::getSumByRefuseSignNumber).sum();
				UpdateSignStatusReqDTO waybillEsDTO = new UpdateSignStatusReqDTO();
				waybillEsDTO.setWaybillId(k);
				waybillEsDTO.setCompanyId(companyId);
				waybillEsDTO.setRefuseNumber(-refuseNumber);
				waybillEsDTO.setSignNumber(-signNumber);
				waybillEsDTO.setTypeEnum(WaybillDistributionTypeEnum.GET);
				reqList.add(waybillEsDTO);
			});
		}
		return reqList;
	}
}
