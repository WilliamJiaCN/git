/**
 * 
 */
package com.hivescm.tms.finance.server.component.pcsign.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.common.domain.DataResult;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.tms.api.dto.es.receipt.request.ReceiptStockSignReqDTO;
import com.hivescm.tms.api.dto.es.receipt.request.ReceiptStockSyncDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptBusinessTypeEnum;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptSignStatusEnum;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.component.pcsign.SignReceiptService;
import com.hivescm.tms.finance.server.feign.waybill.ReceiptBillService;

/**
 * 
 * 签收调用回单服务
 * @author boqiang.deng
 * @date 2018年4月12日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Service
public class SignReceiptServiceImpl implements SignReceiptService {

	@Autowired
	private ReceiptBillService receiptBillService;
	@Override
	public Boolean signReceipt(TmsSignEsDTO tmsSignEsDTO) {
		ReceiptStockSyncDTO stockParam = receiptstock(tmsSignEsDTO);
		ReceiptStockSignReqDTO stockSign = receiptSignStatus(tmsSignEsDTO);
		DataResult<Boolean> stock = receiptBillService.stock(stockParam);
		DataResult<Boolean> receiptSignStatus = receiptBillService.signStatus(stockSign);
		if(null == stock || null==stock.getResult() || stock.isFailed()){
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收调用回单库存接口失败,status:%s"+ stockParam.toString());
		}
		if(null == receiptSignStatus || null == receiptSignStatus.getResult() || receiptSignStatus.isFailed()){
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收调用回单库存签收信息接口失败,status:%s"+ stockParam.toString());
		}
		return stock.getResult() && receiptSignStatus.getResult();
	}

	/**
	 * 调用回单库存
	 * 
	 * @param tmsSignEsDTO
	 * @return
	 */
	private ReceiptStockSyncDTO receiptstock(TmsSignEsDTO tmsSignEsDTO) {
		
		ReceiptStockSyncDTO receipt = new ReceiptStockSyncDTO();
		if (null !=tmsSignEsDTO.getSignEsDTO().getIbackConfirm() && tmsSignEsDTO.getSignEsDTO().getIbackConfirm()) {
			// 区分拒签回收和签收回收
			if (SignStatusEnum.REFUSE_SIGN.getType() == tmsSignEsDTO.getSignEsDTO().getSignStatus().intValue()) {
				receipt.setBusinessType(ReceiptBusinessTypeEnum.RECOVERY_REFUSE);
			}else{
				receipt.setBusinessType(ReceiptBusinessTypeEnum.RECOVERY_SIGN);
			}
		} else {
			receipt.setBusinessType(ReceiptBusinessTypeEnum.RECOVERY_NOT_SIGN);
		}
		receipt.setCompanyId(tmsSignEsDTO.getSignEsDTO().getCompanyId());
		receipt.setStockOrgId(tmsSignEsDTO.getSignEsDTO().getSignOrgId().longValue());
		receipt.setStockOrgName(tmsSignEsDTO.getSignEsDTO().getSignOrgName());
		receipt.setWaybillId(tmsSignEsDTO.getSignEsDTO().getWaybillId());
		receipt.setOperTime(tmsSignEsDTO.getSignEsDTO().getSignTime());
		receipt.setOperUserId(tmsSignEsDTO.getSignEsDTO().getCreateUser().longValue());
		receipt.setOperUserName(tmsSignEsDTO.getSignEsDTO().getCreateUserName());
		return receipt;

	}

	/**
	 * 调用回单签收状态
	 * 
	 * @param tmsSignEsDTO
	 * @return
	 */
	private ReceiptStockSignReqDTO receiptSignStatus(TmsSignEsDTO tmsSignEsDTO) {
		ReceiptStockSignReqDTO receiptStock = new ReceiptStockSignReqDTO();
		if (SignStatusEnum.SIGNED.getType() == tmsSignEsDTO.getSignEsDTO().getSignStatus().intValue()) {
			receiptStock.setSignStatus(ReceiptSignStatusEnum.SIGNED);
		}
		if (SignStatusEnum.PARTIAL_SIGN.getType() == tmsSignEsDTO.getSignEsDTO().getSignStatus().intValue()) {
			receiptStock.setSignStatus(ReceiptSignStatusEnum.SIGNED_PART);
		}
		if (SignStatusEnum.REFUSE_SIGN.getType() == tmsSignEsDTO.getSignEsDTO().getSignStatus().intValue()) {
			receiptStock.setSignStatus(ReceiptSignStatusEnum.SIGNED_REFUSE);
		}
		receiptStock.setSignHandleUserId(tmsSignEsDTO.getSignEsDTO().getCreateUser().longValue());
		receiptStock.setSignHandleUserName(tmsSignEsDTO.getSignEsDTO().getCreateUserName());
		receiptStock.setSignTime(tmsSignEsDTO.getSignEsDTO().getSignTime());
		receiptStock.setWaybillId(tmsSignEsDTO.getSignEsDTO().getWaybillId());
		return receiptStock;
	}
}
