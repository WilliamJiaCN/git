package com.hivescm.tms.finance.server.component.finance.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.common.domain.DataResult;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsGrantEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceCancleReceiptDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageReceiptReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceSaveReceiptDTO;
import com.hivescm.tms.api.dto.es.finance.request.ReceivableFinanceReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.WayBillCashierConfirmationReqDTO;
import com.hivescm.tms.api.enums.finance.CalculationFeeEnum;
import com.hivescm.tms.api.enums.finance.CodeTypeEnum;
import com.hivescm.tms.api.enums.finance.FinanceIdEnum;
import com.hivescm.tms.api.enums.finance.FlowStatusEnum;
import com.hivescm.tms.api.enums.finance.PayWayEnum;
import com.hivescm.tms.api.enums.finance.PaymentStatusEnum;
import com.hivescm.tms.api.enums.finance.TransferAccountsStatusEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.finance.server.component.finance.DbFinanceManageReceiptCompentService;
import com.hivescm.tms.finance.server.component.finance.FinanceManageReceiptComponentService;
import com.hivescm.tms.finance.server.component.pcsign.CashierConfirmationService;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageReceiptDO;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageCashSerialService;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageGoodsGrantService;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageReceiptService;
import com.hivescm.tms.finance.server.service.finance.FinanceManageCashSerialService;
import com.hivescm.tms.finance.server.service.finance.constant.FinanceConstant;
import com.hivescm.tms.intranet.gateway.api.dto.boss.BizUnitWithFuncDetailVo;
import com.hivescm.tms.intranet.gateway.api.dto.boss.QueryByIdReqParam;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.ReconciliationStatus;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.Status;
import com.hivescm.tms.intranet.gateway.api.dto.boss.finance.CodeOpParam;
import com.hivescm.tms.intranet.gateway.api.dto.boss.finance.SettleBillDTO;
import com.hivescm.tms.intranet.gateway.api.dto.boss.finance.SettleDetailDTO;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.boss.BossBizUnitApi;
import com.hivescm.tms.intranet.gateway.api.feign.boss.IBossFinanceNewReceiptPayApi;

@Service
public class FinanceManageReceiptComponentServiceImpl implements FinanceManageReceiptComponentService {

	@Autowired
	private BossBizUnitApi bossBizUnitApi;
	  
    @Autowired
    private DbFinanceManageReceiptCompentService dbFinanceManageReceiptCompentService;
    
    @Autowired
    private EsFinanceManageReceiptService esFinanceManageReceiptService;
    
    @Autowired
    private EsFinanceManageCashSerialService esFinanceManageCashSerialService;
	
	@Autowired
	private FinanceManageCashSerialService financeManageCashSerialService;
	
	@Autowired
	private FinanceManageReceiptComponentServiceImpl financeManageReceiptComponentServiceImpl;
	
	@Autowired
	private EsFinanceManageGoodsGrantService esFinanceManageGoodsGrantService;
	 /**
     * id 生成器
     */
    @Autowired
    private IGeneratedIdService generatedIdService;
    
    @Autowired
    private IBossFinanceNewReceiptPayApi bossFinanceNewReceiptPayApi;
    
    @Autowired
    private CashierConfirmationService ashierConfirmationService;
    
	
	@Override
	public Boolean saveReceipt(FinanceSaveReceiptDTO financeSaveReceiptDTO) {
		//1. 》》 组装收款单记录 + 调用接口生产收款单记录 + 返回生成的相应收款单数据（含收款单id）
		if(financeSaveReceiptDTO.getCompanyId()==null) {
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_PAYCONFIRM, "创建收款单失败参数错误，companyId不能为null");
		}
		boolean db = false;
		boolean es = false;
		List<FinanceManageReceiptEsDTO> list = financeSaveReceiptDTO.getList();
		list = list.stream().map(l->{
			FinanceManageReceiptEsDTO findFinanceManageReceipt = esFinanceManageReceiptService.findFinanceManageReceipt(l.getId());
			findFinanceManageReceipt.setCurrentReceiptedAmount(l.getCurrentReceiptedAmount());
			return findFinanceManageReceipt;
		}).collect(Collectors.toList());
		List<FinanceManageCashSerialEsDTO> cashInsert = new ArrayList<>();
		List<ReceivableFinanceReceiptEsDTO> receiptUpdate = new ArrayList<>();
		List<FinanceManageGoodsGrantEsDTO> grantUpdate = new ArrayList<>();
		List<FinanceManageGoodsGrantEsDTO> grantBack = new ArrayList<>();
		ArrayList<Long> cahsierID = new ArrayList<>();
		try {
			for(FinanceManageReceiptEsDTO esDTO:list) {
				ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO = createUpdateEntity(esDTO,1);
				receivableFinanceReceiptEsDTO.setUpdateUserId(financeSaveReceiptDTO.getUserId());
				receiptUpdate.add(receivableFinanceReceiptEsDTO);
				SettleBillDTO settleBillDTO = createDateNew(financeSaveReceiptDTO, esDTO);
				DataResult<SettleBillDTO> info = bossFinanceNewReceiptPayApi.createPayInfo(settleBillDTO);
				FinanceManageCashSerialEsDTO createCashSerialEntity = createCashSerialEntity(esDTO, financeSaveReceiptDTO);
				if(info!=null&&info.getResult()!=null) {
					String receiptCode = info.getResult().getCode();
					Long id = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_CASH_SERIAL);
					createCashSerialEntity.setId(id);
					createCashSerialEntity.setReceiptCode(receiptCode);
					cashInsert.add(createCashSerialEntity);
					if(esDTO.getPayWay()!=null&&esDTO.getPayWay()==PayWayEnum.PAYMENTDUCTION.getType()) {
						FinanceManageGoodsGrantEsDTO grant = createUpdateGrantEntity(esDTO, 1,grantBack);
						if(grant!=null) {
							grant.setUpdateTime(System.currentTimeMillis());
							grant.setUpdateUserId(financeSaveReceiptDTO.getUserId());
							grant.setUpdateUserName(financeSaveReceiptDTO.getUserName());
							grantUpdate.add(grant);
						}
					}else if(esDTO.getPayWay()!=null&&esDTO.getPayWay()==PayWayEnum.TOPAY.getType()) {
						cahsierID.add(esDTO.getWaybillId());
					}
				}else {
					throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_PAYCONFIRM, info.getStatus().getStatusReason());
				}
			}
			db = saveDb(cashInsert,receiptUpdate,grantUpdate);
			es = saveEs(cashInsert,receiptUpdate,grantUpdate);
			//更新签收的收银状态
			if(cahsierID.size()>0) {
				WayBillCashierConfirmationReqDTO cashier = new WayBillCashierConfirmationReqDTO();
				cashier.setCompanyId(financeSaveReceiptDTO.getCompanyId());
				cashier.setCashierConfirmStatus(2);
				cashier.setWaybillIds(cahsierID);
				ashierConfirmationService.updateSignCashierStatus(cashier);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollBack(cashInsert,list,grantBack);
			throw e;
		}
		return true;
	}


	private void rollBack(List<FinanceManageCashSerialEsDTO> cashInsert, List<FinanceManageReceiptEsDTO> list,
			List<FinanceManageGoodsGrantEsDTO> grantBack) {
		if(CollectionUtils.isEmpty(cashInsert)) {
			return;
		}
		List<FinanceManageCashSerialDO> cashDo = cashInsert.stream().map(c->{
			return EntityUtils.copyProperties(c, FinanceManageCashSerialDO.class);}).collect(Collectors.toList());
		List<FinanceManageReceiptDO> receiptDo = list.stream().map(r->{
			return EntityUtils.copyProperties(r, FinanceManageReceiptDO.class);}).collect(Collectors.toList());
		if(CollectionUtils.isNotEmpty(grantBack)) {
			List<FinanceManageGoodsGrantDO> grantDo = grantBack.stream().map(r->{return EntityUtils.copyProperties(r, FinanceManageGoodsGrantDO.class);}).collect(Collectors.toList());
			dbFinanceManageReceiptCompentService.rollBatchByDuction(cashDo,receiptDo,grantDo);
		}else {
			dbFinanceManageReceiptCompentService.rollBatch(cashDo,receiptDo);
		}
		List<Long> ids = cashInsert.stream().map(c->{
			return c.getId();}).collect(Collectors.toList());
		esFinanceManageCashSerialService.deleteEsBatch(ids);
		List<ReceivableFinanceReceiptEsDTO> receiptEs = list.stream().map(r->{
			return EntityUtils.copyProperties(r, ReceivableFinanceReceiptEsDTO.class);}).collect(Collectors.toList());
		esFinanceManageReceiptService.updateReceivableFinanceReceipt(receiptEs);
		if(CollectionUtils.isNotEmpty(grantBack)) {
			esFinanceManageGoodsGrantService.updateBatchEs(grantBack);
		}
		cashInsert.forEach(s->{
			CodeOpParam opParam = new CodeOpParam();
			opParam.setTypeSeparate(1);
			opParam.setGroupId(s.getGroupId());
			opParam.setOpUser(s.getCreateUserId());
			opParam.setOpTime(System.currentTimeMillis());
			opParam.setCode(s.getReceiptCode());
			bossFinanceNewReceiptPayApi.deleteBySource(opParam);
		});
		
	}


	private boolean saveEs(List<FinanceManageCashSerialEsDTO> cashInsert,
			List<ReceivableFinanceReceiptEsDTO> receiptUpdate, List<FinanceManageGoodsGrantEsDTO> grantUpdate) {
		Boolean cash = esFinanceManageCashSerialService.insertBatch(cashInsert);
		Boolean receipt =  esFinanceManageReceiptService.updateReceivableFinanceReceipt(receiptUpdate);
		if(CollectionUtils.isNotEmpty(grantUpdate)) {
			Boolean grant = esFinanceManageGoodsGrantService.updateBatchEs(grantUpdate);
			return cash&&receipt&&grant;
		}
		return cash&&receipt;
	}


	private boolean saveDb(List<FinanceManageCashSerialEsDTO> cashInsert,
			List<ReceivableFinanceReceiptEsDTO> receiptUpdate, List<FinanceManageGoodsGrantEsDTO> grantUpdate) {
		Boolean flag = false;
		List<FinanceManageCashSerialDO> cashDo = cashInsert.stream().map(c->{
			return EntityUtils.copyProperties(c, FinanceManageCashSerialDO.class);}).collect(Collectors.toList());
		List<FinanceManageReceiptDO> receiptDo = receiptUpdate.stream().map(r->{
			return EntityUtils.copyProperties(r, FinanceManageReceiptDO.class);}).collect(Collectors.toList());
		if(CollectionUtils.isNotEmpty(grantUpdate)) {
			List<FinanceManageGoodsGrantDO> grantDo = grantUpdate.stream().map(r->{return EntityUtils.copyProperties(r, FinanceManageGoodsGrantDO.class);}).collect(Collectors.toList());
			flag = dbFinanceManageReceiptCompentService.updateBatchByDuction(cashDo,receiptDo,grantDo);
		}else {
			flag = dbFinanceManageReceiptCompentService.updateBatch(cashDo,receiptDo);
		}
		return flag;
	}


	@Override
	public Boolean cancleReceipt(FinanceCancleReceiptDTO financeCancleReceiptDTO) {
		//通过ID查找资金流水 --》通过收款单号调用boss删除接口---》增加资金流水---》修改应收里面的金额
		try {
			FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO = financeManageCashSerialService.findById(financeCancleReceiptDTO.getId());
			CodeOpParam opParam = new CodeOpParam();
			opParam.setTypeSeparate(1);
			opParam.setGroupId(financeManageCashSerialEsDTO.getGroupId());
			opParam.setOpUser(financeCancleReceiptDTO.getUserId());
			opParam.setOpTime(System.currentTimeMillis());
			opParam.setCode(financeManageCashSerialEsDTO.getReceiptCode());
			Boolean result = bossFinanceNewReceiptPayApi.deleteBySource(opParam).getResult();
			if(result!=null&&result) {
				BigDecimal receiptAmount = financeManageCashSerialEsDTO.getReceiptAmount();
				//---->修改资金流水状态为取消
				FinanceManageCashSerialEsDTO updateDO = new FinanceManageCashSerialEsDTO();
				updateDO.setId(financeManageCashSerialEsDTO.getId());
				updateDO.setCompanyId(financeManageCashSerialEsDTO.getCompanyId());
				updateDO.setStatus(FlowStatusEnum.CANCEL.getType());
				//--->新增资金流水
				Long id = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_CASH_SERIAL);
				financeManageCashSerialEsDTO.setId(id);
				financeManageCashSerialEsDTO.setReceiptAmount(receiptAmount.negate());
				financeManageCashSerialEsDTO.setStatus(FlowStatusEnum.CANCELLATION.getType());
				financeManageCashSerialEsDTO.setRemark("取消收银");
				//--->修改实收金额
				FinanceManageReceiptEsDTO findFinanceManageReceiptById = esFinanceManageReceiptService.findFinanceManageReceipt(financeManageCashSerialEsDTO.getFinanceId());
				if(findFinanceManageReceiptById==null) {
					FinanceManageGoodsGrantEsDTO grant = esFinanceManageGoodsGrantService.getById(financeManageCashSerialEsDTO.getFinanceId());
					FinanceManageReceiptReqDTO financeManageReceiptReqDTO = new FinanceManageReceiptReqDTO();
					financeManageReceiptReqDTO.setCodeType(CodeTypeEnum.YD.getType());
					financeManageReceiptReqDTO.setOrderSourceId(grant.getOrderSourceCode());
					financeManageReceiptReqDTO.setPayWay(PayWayEnum.PAYMENTDUCTION.getType());
					findFinanceManageReceiptById = esFinanceManageReceiptService.findFinanceManageReceipt(financeManageReceiptReqDTO);
				}
				findFinanceManageReceiptById.setCurrentReceiptedAmount(receiptAmount);
				ReceivableFinanceReceiptEsDTO createUpdateEntity = createUpdateEntity(findFinanceManageReceiptById, 2);
				createUpdateEntity.setCompanyId(financeCancleReceiptDTO.getCompanyId());
				createUpdateEntity.setUpdateTime(System.currentTimeMillis());
				createUpdateEntity.setUpdateUserId(financeCancleReceiptDTO.getUserId());
				//2.1 》》 更新收款状态、金额
				if(findFinanceManageReceiptById.getPayWay()!=null&&findFinanceManageReceiptById.getPayWay()==PayWayEnum.PAYMENTDUCTION.getType()) {
					FinanceManageGoodsGrantEsDTO grant = createUpdateGrantEntity(findFinanceManageReceiptById, 2,null);
					if(grant!=null) {
						grant.setUpdateTime(System.currentTimeMillis());
						grant.setUpdateUserId(financeCancleReceiptDTO.getUserId());
						grant.setUpdateUserName(financeCancleReceiptDTO.getUserName());
						financeManageReceiptComponentServiceImpl.cancleByReceiptDuction(financeManageCashSerialEsDTO, updateDO, grant, createUpdateEntity);
					}else {
						financeManageReceiptComponentServiceImpl.cancleByReceipt(financeManageCashSerialEsDTO, updateDO, createUpdateEntity);
					}
				}else {
					financeManageReceiptComponentServiceImpl.cancleByReceipt(financeManageCashSerialEsDTO, updateDO, createUpdateEntity);
				}
			}else {
				throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_PAYDELETE, "外部服务异常：删除收款单失败");
			}
		} catch (Exception e) {
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_PAYDELETE, "外部服务异常：删除收款单失败");
		}
		return true;
	}
	

	@Override
	public Boolean updateByReceipt(FinanceManageCashSerialEsDTO createCashSerialEntity,
			ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO) {
		createCashSerialEntity.setCreateTime(System.currentTimeMillis());
        FinanceManageCashSerialDO copyProperties = EntityUtils.copyProperties(createCashSerialEntity,FinanceManageCashSerialDO.class);
        Boolean db = dbFinanceManageReceiptCompentService.updateByReceipt(copyProperties,receivableFinanceReceiptEsDTO);
		boolean updateEs = esFinanceManageReceiptService.updateByReceipt(receivableFinanceReceiptEsDTO);
		boolean insert = esFinanceManageCashSerialService.insert(createCashSerialEntity);
		return db&&updateEs&&insert;
	}

	@Override
	public Boolean cancleByReceipt(FinanceManageCashSerialEsDTO insertDto, FinanceManageCashSerialEsDTO updateDO,
			ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO) {
		insertDto.setCreateTime(System.currentTimeMillis());
        FinanceManageCashSerialDO copyPropertiesInsert = EntityUtils.copyProperties(insertDto,FinanceManageCashSerialDO.class);
		FinanceManageCashSerialDO copyProperties = EntityUtils.copyProperties(updateDO, FinanceManageCashSerialDO.class);
		Boolean db = dbFinanceManageReceiptCompentService.cancleByReceipt(copyPropertiesInsert,copyProperties,receivableFinanceReceiptEsDTO);
		boolean updateEs = esFinanceManageReceiptService.updateByReceipt(receivableFinanceReceiptEsDTO);
		boolean insert = esFinanceManageCashSerialService.insert(insertDto);
		boolean updateCash = esFinanceManageCashSerialService.updateEs(updateDO);
		return db&&updateEs&&insert&&updateCash;
	}
	


	@Override
	public Boolean updateByReceiptDuction(FinanceManageCashSerialEsDTO createCashSerialEntity,
			FinanceManageGoodsGrantEsDTO grant, ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO) {
		createCashSerialEntity.setCreateTime(System.currentTimeMillis());
        FinanceManageCashSerialDO copyProperties = EntityUtils.copyProperties(createCashSerialEntity,FinanceManageCashSerialDO.class);
        FinanceManageGoodsGrantDO grantDo = EntityUtils.copyProperties(grant,FinanceManageGoodsGrantDO.class);
        Boolean db = dbFinanceManageReceiptCompentService.updateByReceiptDuction(copyProperties, grantDo, receivableFinanceReceiptEsDTO);
		boolean updateEs = esFinanceManageReceiptService.updateByReceipt(receivableFinanceReceiptEsDTO);
		boolean insert = esFinanceManageCashSerialService.insert(createCashSerialEntity);
		boolean updateGrant = esFinanceManageGoodsGrantService.update(grant);
		return db&&updateEs&&insert&&updateGrant;
	}


	@Override
	public Boolean cancleByReceiptDuction(FinanceManageCashSerialEsDTO insertDto, FinanceManageCashSerialEsDTO updateDO,
			FinanceManageGoodsGrantEsDTO grant, ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO) {
		insertDto.setCreateTime(System.currentTimeMillis());
        FinanceManageCashSerialDO copyPropertiesInsert = EntityUtils.copyProperties(insertDto,FinanceManageCashSerialDO.class);
		FinanceManageCashSerialDO copyProperties = EntityUtils.copyProperties(updateDO, FinanceManageCashSerialDO.class);
		 FinanceManageGoodsGrantDO grantDo = EntityUtils.copyProperties(grant,FinanceManageGoodsGrantDO.class);
		 Boolean db = dbFinanceManageReceiptCompentService.cancleByReceiptDuction(copyPropertiesInsert, copyProperties, grantDo, receivableFinanceReceiptEsDTO);
		boolean updateEs = esFinanceManageReceiptService.updateByReceipt(receivableFinanceReceiptEsDTO);
		boolean insert = esFinanceManageCashSerialService.insert(insertDto);
		boolean updateCash = esFinanceManageCashSerialService.updateEs(updateDO);
		boolean updateGrant = esFinanceManageGoodsGrantService.update(grant);
		return db&&updateEs&&insert&&updateCash&&updateGrant;
	}
	
	private FinanceManageGoodsGrantEsDTO createUpdateGrantEntity(FinanceManageReceiptEsDTO esDTO,int type, List<FinanceManageGoodsGrantEsDTO> grantBack) {
		FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO = esFinanceManageGoodsGrantService.findByWaybillId(esDTO.getWaybillId());
		if(financeManageGoodsGrantEsDTO!=null) {
			if(grantBack!=null) {
				grantBack.add(financeManageGoodsGrantEsDTO);
			}
			FinanceManageGoodsGrantEsDTO result = new FinanceManageGoodsGrantEsDTO();
			BigDecimal currentReceiptedAmount = esDTO.getCurrentReceiptedAmount()==null?new BigDecimal(0):esDTO.getCurrentReceiptedAmount();
			BigDecimal goodsAmount = financeManageGoodsGrantEsDTO.getGoodsAmount()==null?new BigDecimal(0):financeManageGoodsGrantEsDTO.getGoodsAmount();
			BigDecimal unGoodsAmount = financeManageGoodsGrantEsDTO.getUngoodsAmount()==null?new BigDecimal(0):financeManageGoodsGrantEsDTO.getUngoodsAmount();
			if(type==1) {
				goodsAmount = goodsAmount.add(currentReceiptedAmount);
				unGoodsAmount = unGoodsAmount.subtract(currentReceiptedAmount);
			}else if(type==2) {
				goodsAmount = goodsAmount.subtract(currentReceiptedAmount);
				unGoodsAmount = unGoodsAmount.add(currentReceiptedAmount);
			}
			if(unGoodsAmount.compareTo(BigDecimal.ZERO)<0) {
				throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_PAYCONFIRM, "实付金额大于应收金额");
			}
			result.setId(financeManageGoodsGrantEsDTO.getId());
			result.setGoodsAmount(goodsAmount);
			result.setCompanyId(esDTO.getCompanyId());
			result.setUngoodsAmount(unGoodsAmount);
			return result;
		}
		return null;
	}
	
	private FinanceManageCashSerialEsDTO createCashSerialEntity(FinanceManageReceiptEsDTO esDTO,FinanceSaveReceiptDTO financeSaveReceiptDTO) {
		FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO = new FinanceManageCashSerialEsDTO();
		financeManageCashSerialEsDTO.setFinanceId(esDTO.getId());
		financeManageCashSerialEsDTO.setType(FinanceIdEnum.FINANCERECEIPT.getType());
		financeManageCashSerialEsDTO.setCompanyId(financeSaveReceiptDTO.getCompanyId());
		financeManageCashSerialEsDTO.setOrdersourceId(esDTO.getOrderSourceCode());
		financeManageCashSerialEsDTO.setCodType(esDTO.getCodeType());
		financeManageCashSerialEsDTO.setCodTypeName(esDTO.getCodeTypeName());
		financeManageCashSerialEsDTO.setFundAccount(String.valueOf(financeSaveReceiptDTO.getReceiveAccount()));
		financeManageCashSerialEsDTO.setReceiptAmount(esDTO.getCurrentReceiptedAmount());
		financeManageCashSerialEsDTO.setReceiptPayNetworkId(esDTO.getDeliveryNetworkId());
		financeManageCashSerialEsDTO.setReceiptPayNetworkName(esDTO.getDeliveryNetworkName());
		financeManageCashSerialEsDTO.setRemark(financeSaveReceiptDTO.getComment());
		financeManageCashSerialEsDTO.setSettlementMethod(financeSaveReceiptDTO.getSettlementMode());
		financeManageCashSerialEsDTO.setSettlementMethodName(financeSaveReceiptDTO.getSettlementModeName());
		if(financeSaveReceiptDTO.getPayChannel()!=null)
			financeManageCashSerialEsDTO.setPayMethod(Integer.parseInt(financeSaveReceiptDTO.getPayChannel()));
		financeManageCashSerialEsDTO.setPayMethodName(financeSaveReceiptDTO.getPayChannelName());
		financeManageCashSerialEsDTO.setCreateBillTime(System.currentTimeMillis());
		financeManageCashSerialEsDTO.setReceiptPayTime(financeSaveReceiptDTO.getCashierTime());
		financeManageCashSerialEsDTO.setStatus(FlowStatusEnum.NORMAL.getType());
		financeManageCashSerialEsDTO.setCreateUserId(financeSaveReceiptDTO.getUserId());
		financeManageCashSerialEsDTO.setCreateUserName(financeSaveReceiptDTO.getUserName());
		financeManageCashSerialEsDTO.setPayAccount(financeSaveReceiptDTO.getPayAccount());
		financeManageCashSerialEsDTO.setReceivableBankAccount(financeSaveReceiptDTO.getReceiveAccount());
		financeManageCashSerialEsDTO.setGroupId(financeSaveReceiptDTO.getGroupId());
		financeManageCashSerialEsDTO.setSubmitBillState(TransferAccountsStatusEnum.UNTRANSFER.getType());
		financeManageCashSerialEsDTO.setSubmitBillStateName(TransferAccountsStatusEnum.UNTRANSFER.getName());
		financeManageCashSerialEsDTO.setPaymentType(esDTO.getPayWay());
		financeManageCashSerialEsDTO.setPaymentTypeName(esDTO.getPayWayName());
		financeManageCashSerialEsDTO.setPayeeName(esDTO.getPayeeName());
		financeManageCashSerialEsDTO.setCreateTime(System.currentTimeMillis());
		//--->核算网点，付款方
		if(esDTO.getPayWay()==PayWayEnum.TOPAY.getType()||esDTO.getPayWay()==PayWayEnum.PAYMENTDUCTION.getType()) {
			financeManageCashSerialEsDTO.setSettleNetworkId(setCheckObjectId(esDTO.getDestOrgId()));
			financeManageCashSerialEsDTO.setSettleNetworkName(setSettleObjectName(esDTO.getDestOrgId()));
		}else {
			financeManageCashSerialEsDTO.setSettleNetworkId(setCheckObjectId(esDTO.getDeliveryNetworkId()));
			financeManageCashSerialEsDTO.setSettleNetworkName(setSettleObjectName(esDTO.getDeliveryNetworkId()));
			
		}
		return financeManageCashSerialEsDTO;
	}

	private ReceivableFinanceReceiptEsDTO createUpdateEntity(FinanceManageReceiptEsDTO esDTO,int type) {
		ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO = new ReceivableFinanceReceiptEsDTO();
		BigDecimal currentReceiptedAmount = esDTO.getCurrentReceiptedAmount()==null?new BigDecimal(0):esDTO.getCurrentReceiptedAmount();
		BigDecimal unreceiptAmount = esDTO.getUnreceiptAmount()==null?esDTO.getReceiptAmount():esDTO.getUnreceiptAmount();
		BigDecimal receiptAmount = esDTO.getReceiptedAmount()==null?new BigDecimal(0):esDTO.getReceiptedAmount();
		if(type==1) {
			receiptAmount = receiptAmount.add(currentReceiptedAmount);
			unreceiptAmount = unreceiptAmount.subtract(currentReceiptedAmount);
		}else if(type==2) {
			receiptAmount = receiptAmount.subtract(currentReceiptedAmount);
			unreceiptAmount = unreceiptAmount.add(currentReceiptedAmount);
		}
		Integer deliveryStatus = null;
		if(receiptAmount.compareTo(BigDecimal.ZERO)<0) {
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_PAYCONFIRM, "已收金额为负数");
		}
		if(unreceiptAmount.compareTo(esDTO.getReceiptAmount())==0) {
			deliveryStatus = PaymentStatusEnum.NO_PAYMENT.getType();
		}else if(unreceiptAmount.compareTo(BigDecimal.ZERO)>0) {
			deliveryStatus = PaymentStatusEnum.PARTLY_PAYMENT.getType();
		}else if(unreceiptAmount.compareTo(BigDecimal.ZERO)==0) {
			deliveryStatus = PaymentStatusEnum.ALL_PAYMENT.getType();
		}else {
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_PAYCONFIRM, "实付金额大于应收金额");
		}
		receivableFinanceReceiptEsDTO.setId(esDTO.getId());
		receivableFinanceReceiptEsDTO.setDeliveryStatus(deliveryStatus);
		receivableFinanceReceiptEsDTO.setReceiptedAmount(receiptAmount);
		receivableFinanceReceiptEsDTO.setUnreceiptAmount(unreceiptAmount);
		receivableFinanceReceiptEsDTO.setUpdateTime(System.currentTimeMillis());
		receivableFinanceReceiptEsDTO.setCompanyId(esDTO.getCompanyId());
		return receivableFinanceReceiptEsDTO;
	}
	
	/**
	 * 构建的收款单数据 + 调用BOSS接口生成相应收款数据
	 * @param financeSaveReceiptDTO 
	 * @return
	 */
	private SettleBillDTO createDateNew(FinanceSaveReceiptDTO financeSaveReceiptDTO,FinanceManageReceiptEsDTO esDTO){
		SettleBillDTO settleBillDTO = new SettleBillDTO();
		SettleDetailDTO detailDto = new SettleDetailDTO();
		List<SettleDetailDTO> details = new ArrayList<>();
		details.add(detailDto);
		settleBillDTO.setDetails(details);
		setCommonEntity(settleBillDTO, esDTO, financeSaveReceiptDTO);
		Integer payWay = esDTO.getPayWay();
		if(esDTO.getIsTransit()!=null&&esDTO.getIsTransit()) {
			setTransEntity(settleBillDTO, esDTO);
		}else {
			if(esDTO.getCodeType()!=null&&esDTO.getCodeType()==CalculationFeeEnum.YCHD.getType()) {
				settleBillDTO.setBillType(FinanceConstant.RECEIPT_OTHER_TYPE); // 收款单类型(10001 销售收款单;10002 采购退货收款单;10003 代收货款收款单;10004 其他收款单;10005 服务收款单)")
				settleBillDTO.setSourceBillType(CalculationFeeEnum.YCHD.getDocType()); // 来源单据类型(数据字典 BOSS030)
				detailDto.setOriginalBillType(CalculationFeeEnum.YCHD.getDocType()); // 源头单类型
				settleBillDTO.setSettleOrg(setSettleObjectId(esDTO.getDeliveryNetworkId()));//收款组织 - 收款网点的结算组织ID
				settleBillDTO.setSettleParty(esDTO.getPayeeId()==null?0:esDTO.getPayeeId());//付款方--“供应商”ID
				detailDto.setAccountOrg(setCheckObjectId(esDTO.getDeliveryNetworkId()));//应收组织 - 收款网点的核算组织ID
				detailDto.setAccountParty(esDTO.getPayeeId()==null?0:esDTO.getPayeeId());//应付方 - 供应商ID
			}else {
				switch(payWay) {
				//TODO 现付
				case 4:
					setPayEntity(settleBillDTO, esDTO);
					detailDto.setSettleCharactor(FinanceConstant.DEPOSIT_RECEIVABLE);//收款性质--预收账款
					break;
				//TODO 到付
				case 8:case 12:
					setToPayEntity(settleBillDTO,esDTO);
					break;
				//TODO 月结 欠付 回单付 货款扣
				case 6:case 5:case 7:case 9:
					setPayEntity(settleBillDTO, esDTO);
					break;
				}
			}
		}
		return settleBillDTO;
	}
	
	
	private void setCommonEntity(SettleBillDTO settleBillDTO,FinanceManageReceiptEsDTO esDTO,FinanceSaveReceiptDTO financeSaveReceiptDTO) {
		SettleDetailDTO detailDto = settleBillDTO.getDetails().get(0);
		long time = System.currentTimeMillis();
		settleBillDTO.setTypeSeparate(1); // 类型区分(1收款;2付款)
		settleBillDTO.setGroupId(financeSaveReceiptDTO.getGroupId()); // 集团 ID
		settleBillDTO.setBillType(FinanceConstant.RECEIPT_SERVICE_TYPE); // 收款单类型(10001 销售收款单;10002 采购退货收款单;10003 代收货款收款单;10004 其他收款单;10005 服务收款单)")
		settleBillDTO.setSourceBillType(FinanceConstant.SOURCE_BILL_TYPE); // 来源单据类型(数据字典 BOSS030)
		settleBillDTO.setBillDate(time); // 单据日期
		settleBillDTO.setBillStatus(Status.APPROVED); // 单据状态-已审批
		settleBillDTO.setSettleOrgAccount(financeSaveReceiptDTO.getReceiveAccount()); // 结算组织账户(收款组织账号)
		settleBillDTO.setCooperationType(2); // 往来对象类型(1-供应商;2-客户;3-部门;4-人员;5-会员)")
		settleBillDTO.setCurrencyId(1);// 币种 ID (人民币为 1)
		settleBillDTO.setSettleOrgAccount(financeSaveReceiptDTO.getReceiveAccount());
		settleBillDTO.setSettlePartyAccount(financeSaveReceiptDTO.getPayAccount());
		settleBillDTO.setPushed(true);
		settleBillDTO.setPullable(false);
		settleBillDTO.setCreateUser(financeSaveReceiptDTO.getUserId());
		settleBillDTO.setCreateTime(time);
		settleBillDTO.setRemark(financeSaveReceiptDTO.getComment());// 备注
		detailDto.setGroupId(financeSaveReceiptDTO.getGroupId());
		detailDto.setSourceBillId(esDTO.getId()); // 来源单ID--运单ID
		detailDto.setSourceBillCode(esDTO.getOrderSourceCode()); // 来源单编号--运单号
		detailDto.setSourceDetailId(1L); // 来源单行号
		detailDto.setSourceTaxedAmount(esDTO.getReceiptAmount()); // 来源单金额
		detailDto.setOriginalBillId(esDTO.getWaybillId()); // 源头单ID--应收记录ID
		detailDto.setOriginalBillCode(esDTO.getOrderSourceCode());//// 源头单编号
		detailDto.setOriginalBillType(FinanceConstant.SOURCE_BILL_TYPE); // 源头单类型
		detailDto.setOriginalDetailId(1L); // 源头单行号
		detailDto.setTotalAmmount(esDTO.getCurrentReceiptedAmount());
		detailDto.setSettleType(FinanceConstant.RECEIPT_PAY_TYPE_SERVICE_ID); //收款类型 - 服务款id
		detailDto.setSettleCharactor(FinanceConstant.RECEIVABLES_PROPERTY_RECEIVABLE);//应收、预收
		detailDto.setPayMode(String.valueOf(esDTO.getPayWay()));
		detailDto.setSettleMode(financeSaveReceiptDTO.getSettlementMode());
		detailDto.setRemark(financeSaveReceiptDTO.getComment());
		detailDto.setReceiveAccount(financeSaveReceiptDTO.getReceiveAccount());
		detailDto.setPayAccount(financeSaveReceiptDTO.getPayAccount());
		detailDto.setCheckStatus(ReconciliationStatus.RECONCILICATIONSUCCESS); //收款对账状态 - 对账成功
		//新加字段
		settleBillDTO.setTotalAmmount(esDTO.getCurrentReceiptedAmount());
		settleBillDTO.setSettledAmount(esDTO.getCurrentReceiptedAmount());
		detailDto.setSettledAmount(esDTO.getCurrentReceiptedAmount());
	}

	private void setToPayEntity(SettleBillDTO settleBillDTO,FinanceManageReceiptEsDTO esDTO) {
		SettleDetailDTO detailDto = settleBillDTO.getDetails().get(0);
		Integer payObject = 0;//付款方、业务方、应付方
		//运单中收款方Id和收款会员Id只能有一个有值
		if(esDTO.getReceiptCustomerId() != null ){
			payObject = esDTO.getReceiptCustomerId();
		}else if(esDTO.getReceiptCustomerVipId() != null){
			payObject = esDTO.getReceiptCustomerVipId().intValue();
		}
		settleBillDTO.setSettleOrg(setSettleObjectId(esDTO.getDestOrgId()));//收款组织 - 目的网点的结算组织ID
		settleBillDTO.setSettleParty(payObject);//付款方--收货方在客商档案的客户ID 或者收货人在会员档案的会员ID
		detailDto.setAccountOrg(setCheckObjectId(esDTO.getDestOrgId()));//应收组织 - 目的网点的核算组织ID
		detailDto.setAccountParty(payObject);//应付方 - 收货方在客商档案的客户ID 或者收货人在会员档案的会员ID
	}
	
	private void setPayEntity(SettleBillDTO settleBillDTO,FinanceManageReceiptEsDTO esDTO) {
		SettleDetailDTO detailDto = settleBillDTO.getDetails().get(0);
		Integer payObject = 0;//付款方、业务方、应付方
		//运单中收款方Id和收款会员Id只能有一个有值
		if(esDTO.getInvoiceCustomerId() != null ){
			payObject = esDTO.getInvoiceCustomerId();
		}else if( esDTO.getInvoiceCustomerVipId() != null){
			payObject = esDTO.getInvoiceCustomerVipId().intValue();
		}
		settleBillDTO.setSettleOrg(setSettleObjectId(esDTO.getInvoiceOrgId()));//收款组织 - 发货网点的结算组织ID
		settleBillDTO.setSettleParty(payObject);//付款方--发货公司的集团客户ID 或者发货人在会员档案的会员ID
		detailDto.setAccountOrg(setCheckObjectId(esDTO.getInvoiceOrgId()));//应收组织 - 发货网点的核算组织ID
		detailDto.setAccountParty(payObject);//应付方 - 发货公司的集团客户ID 或者 发货人在会员档案的会员ID
	}
	
	private void setTransEntity(SettleBillDTO settleBillDTO,FinanceManageReceiptEsDTO esDTO) {
		SettleDetailDTO detailDto = settleBillDTO.getDetails().get(0);
		settleBillDTO.setSettleOrg(setSettleObjectId(esDTO.getTransitNetworkId()));//收款组织 - 中转网点的结算组织
		settleBillDTO.setSettleParty(esDTO.getTransitCompanyId());//付款方--外发承运商的ID
		detailDto.setAccountOrg(setCheckObjectId(esDTO.getTransitNetworkId()));//应收组织 - 中转网点的核算组织
		detailDto.setAccountParty(esDTO.getTransitCompanyId());//应付方 - 外发承运商ID
	}

	//核算组织
	private Integer setCheckObjectId(Integer id) {
		if(id!=null) {
			QueryByIdReqParam queryByIdReqParam = new QueryByIdReqParam();
			queryByIdReqParam.setId(id);
			BizUnitWithFuncDetailVo result = bossBizUnitApi.queryBizUnitById(queryByIdReqParam).getResult();
			if(result!=null&&result.getLogistics()!=null) {
				return result.getLogistics().getAccountOrgId();
			}
		}
		return null;
	}
	
	//结算组织
	private Integer setSettleObjectId(Integer id) {
		if(id!=null) {
			QueryByIdReqParam queryByIdReqParam = new QueryByIdReqParam();
			queryByIdReqParam.setId(id);
			BizUnitWithFuncDetailVo result = bossBizUnitApi.queryBizUnitById(queryByIdReqParam).getResult();
			if(result!=null&&result.getLogistics()!=null) {
				return result.getLogistics().getSettleOrgId();
			}
		}
		return null;
	}
	
	//结算组织名称
	private String setSettleObjectName(Integer id) {
		if(id!=null) {
			QueryByIdReqParam queryByIdReqParam = new QueryByIdReqParam();
			queryByIdReqParam.setId(id);
			BizUnitWithFuncDetailVo result = bossBizUnitApi.queryBizUnitById(queryByIdReqParam).getResult();
			if(result!=null&&result.getLogistics()!=null) {
				return result.getLogistics().getSettleOrgName();
			}
		}
		return null;
	}

	
}
