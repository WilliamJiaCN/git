package com.hivescm.tms.finance.server.component.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsGrantEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsRecycleEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceCancleReceiptDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageRecycleRemitReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceSaveRecycleDTO;

import java.util.List;

public interface FinanceManageGoodsRecycleComponentService {

	/**
	 * 回收确认
	 * @param financeSaveRecycleDTO
	 * @return
	 */
	Boolean saveRecycle(FinanceSaveRecycleDTO financeSaveRecycleDTO);
	
	/**
	 * 回收取消
	 * @param financeSaveRecycleDTO
	 * @return
	 */
	Boolean cancleRecycle(FinanceCancleReceiptDTO financeCancleReceiptDTO);
	
	/**
	 * 回收确认--数据更改DB和ES--修改发放记录
	 * @param financeManageGoodsGrantEsDTO 
	 * @param financeSaveRecycleDTO
	 * @return
	 */
	Boolean updateByRecycle(FinanceManageCashSerialEsDTO createCashSerialEntity,FinanceManageGoodsRecycleEsDTO record, FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO);
	
	/**
	 * 回收取消--数据更改DB和ES
	 * @param financeManageGoodsGrantEsDTO 
	 * @param financeSaveRecycleDTO
	 * @return
	 */
	Boolean cancleByRecycle(FinanceManageCashSerialEsDTO createCashSerialEntity,FinanceManageCashSerialEsDTO updateDO,
			FinanceManageGoodsRecycleEsDTO record, FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO);

	/**
	 * 回收确认--数据更改DB和ES--新增发放记录
	 * @param financeManageGoodsGrantEsDTO 
	 * @param financeSaveRecycleDTO
	 * @return
	 */
	Boolean updateByRecycleGrantInsert(FinanceManageCashSerialEsDTO createCashSerialEntity,
			FinanceManageGoodsRecycleEsDTO updateEntity, FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO);

	/**
	 * 回收取消--数据更改DB和ES--删除发放记录
	 * @param financeManageGoodsGrantEsDTO 
	 * @param financeSaveRecycleDTO
	 * @return
	 */
	Boolean cancleByRecycleDelete(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO,
			FinanceManageCashSerialEsDTO updateDO, FinanceManageGoodsRecycleEsDTO updateEntity,
			FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO);

	/**
	 * 汇款
	 * @param financeManageRecycleRemitReqDTO
	 * @return
	 */
    Boolean financeManageRecycleRemit(FinanceManageRecycleRemitReqDTO financeManageRecycleRemitReqDTO);
	/**
	 * 获取结算网点id
	 */
	Integer setSettleObjectId(Integer id);

	/**
	 * 汇款DB操作
	 * @param financeManageGoodsGrantEsDTOList
	 * @param financeManageGoodsRecycleEsDTOList
	 * @param financeManageCashSerialEsDTOList
	 * @return
	 */
	Boolean updateManageRecycleRemit(List<FinanceManageGoodsGrantEsDTO> financeManageGoodsGrantEsDTOList, List<FinanceManageGoodsRecycleEsDTO> financeManageGoodsRecycleEsDTOList, List<FinanceManageCashSerialEsDTO> financeManageCashSerialEsDTOList);
}
