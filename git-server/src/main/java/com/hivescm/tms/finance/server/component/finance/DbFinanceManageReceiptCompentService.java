package com.hivescm.tms.finance.server.component.finance;

import java.util.List;

import com.hivescm.tms.api.dto.es.finance.request.ReceivableFinanceReceiptEsDTO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageReceiptDO;

/**
 *	生成收款单业务
 * @author wangqianqian
 *
 */
public interface DbFinanceManageReceiptCompentService {

	/**
	 * 生成收款单时保存数据到DB
	 * @param createCashSerialEntity
	 * @param receivableFinanceReceiptEsDTO
	 * @return
	 */
	Boolean updateByReceipt(FinanceManageCashSerialDO createCashSerialEntity,
			ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO);
	

	/**
	 * 取消收款单时保存数据到DB
	 * @param insertDto
	 * @param updateDO
	 * @param receivableFinanceReceiptEsDTO
	 * @return
	 */
	Boolean cancleByReceipt(FinanceManageCashSerialDO insertDto,FinanceManageCashSerialDO updateDO,
			ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO);
	

	/**
	 * 生成收款单时--货款扣保存数据到DB
	 * @param createCashSerialEntity
	 * @param receivableFinanceReceiptEsDTO
	 * @return
	 */
	Boolean updateByReceiptDuction(FinanceManageCashSerialDO createCashSerialEntity,FinanceManageGoodsGrantDO record,
			ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO);


	/**
	 * 取消收款单时--货款扣保存数据到DB
	 * @param insertDto
	 * @param updateDO
	 * @param receivableFinanceReceiptEsDTO
	 * @return
	 */
	Boolean cancleByReceiptDuction(FinanceManageCashSerialDO insertDto,FinanceManageCashSerialDO updateDO,FinanceManageGoodsGrantDO record,
			ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO);


	/**
	 * 批量删除
	 * @param rollCash
	 * @param receipt
	 * @param grant
	 * @return
	 */
	boolean deleteDbBatch(List<FinanceManageCashSerialDO> rollCash, List<FinanceManageReceiptDO> receipt,
			List<FinanceManageGoodsGrantDO> grant);


	/**
	 * 批量修改
	 * @param cashDo
	 * @param receiptDo
	 * @return
	 */
	Boolean updateBatch(List<FinanceManageCashSerialDO> cashDo, List<FinanceManageReceiptDO> receiptDo);

	/**
	 * 批量修改包含货款扣
	 * @param cashDo
	 * @param receiptDo
	 * @param grantDo
	 * @return
	 */
	Boolean updateBatchByDuction(List<FinanceManageCashSerialDO> cashDo, List<FinanceManageReceiptDO> receiptDo,
			List<FinanceManageGoodsGrantDO> grantDo);

	/**
	 * 回滚
	 * @param cashDo
	 * @param receiptDo
	 * @return
	 */
	Boolean rollBatch(List<FinanceManageCashSerialDO> cashDo, List<FinanceManageReceiptDO> receiptDo);

	/**
	 * 回滚包含货款扣
	 * @param cashDo
	 * @param receiptDo
	 * @param grantDo
	 * @return
	 */
	Boolean rollBatchByDuction(List<FinanceManageCashSerialDO> cashDo, List<FinanceManageReceiptDO> receiptDo,
			List<FinanceManageGoodsGrantDO> grantDo);

}
