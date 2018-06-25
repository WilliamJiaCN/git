package com.hivescm.tms.finance.server.component.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsGrantEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceCancleReceiptDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceSaveReceiptDTO;
import com.hivescm.tms.api.dto.es.finance.request.ReceivableFinanceReceiptEsDTO;

/**
 * 收款单
 * @author wangqianqian
 *
 */
public interface FinanceManageReceiptComponentService {

	/**
	 * 创建收款单
	 * @param financeSaveReceiptDTO
	 * @return
	 */
	Boolean saveReceipt(FinanceSaveReceiptDTO financeSaveReceiptDTO);

	/**
	 * 取消收银
	 * @param id
	 * @param companyId 
	 * @return
	 */
	Boolean cancleReceipt(FinanceCancleReceiptDTO financeCancleReceiptDTO);
	
	/**
	 * 生成收款单操作DB和ES
	 * @param createCashSerialEntity
	 * @param receivableFinanceReceiptEsDTO
	 * @return
	 */
	Boolean updateByReceipt(FinanceManageCashSerialEsDTO createCashSerialEntity,ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO);
	
	/**
	 * 取消收款单操作DB和ES
	 * @param insertDto
	 * @param updateDO
	 * @param receivableFinanceReceiptEsDTO
	 * @return
	 */
	Boolean cancleByReceipt(FinanceManageCashSerialEsDTO insertDto,FinanceManageCashSerialEsDTO updateDO,
			ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO);
	
	/**
	 * 生成收款单--货款扣操作DB和ES
	 * @param createCashSerialEntity
	 * @param receivableFinanceReceiptEsDTO
	 * @return
	 */
	Boolean updateByReceiptDuction(FinanceManageCashSerialEsDTO createCashSerialEntity,
			FinanceManageGoodsGrantEsDTO grant,ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO);
	
	/**
	 * 取消收款单--货款扣操作DB和ES
	 * @param insertDto
	 * @param updateDO
	 * @param receivableFinanceReceiptEsDTO
	 * @return
	 */
	Boolean cancleByReceiptDuction(FinanceManageCashSerialEsDTO insertDto,FinanceManageCashSerialEsDTO updateDO,
			FinanceManageGoodsGrantEsDTO grant,ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO);

}
