package com.hivescm.tms.finance.server.component.finance;

import java.util.List;

import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsRecycleDO;

public interface DbFinanceGoodsRecycleComponentService {

	/**
	 * 回收确认
	 * @param goodsGrant 
	 * @param financeSaveRecycleDTO
	 * @return
	 */
	Boolean saveRecycle(FinanceManageCashSerialDO createCashSerialEntity,FinanceManageGoodsRecycleDO record, FinanceManageGoodsGrantDO goodsGrant);
	
	/**
	 * 回收确认--更新发放的收款人
	 * @param goodsGrant 
	 * @param financeSaveRecycleDTO
	 * @return
	 */
	Boolean saveRecycleUpdateGrant(FinanceManageCashSerialDO createCashSerialEntity,FinanceManageGoodsRecycleDO record, FinanceManageGoodsGrantDO goodsGrant);
	
	/**
	 * 回收确认
	 * @param financeSaveRecycleDTO
	 * @return
	 */
	Boolean cancleRecycle(FinanceManageCashSerialDO createCashSerialEntity,FinanceManageCashSerialDO updateDO,
			FinanceManageGoodsRecycleDO record,FinanceManageGoodsGrantDO goodsGrant);
	
	/**
	 * 回收确认--删除发放记录
	 * @param financeSaveRecycleDTO
	 * @return
	 */
	Boolean cancleRecycleDeleteGrant(FinanceManageCashSerialDO createCashSerialEntity,FinanceManageCashSerialDO updateDO,
			FinanceManageGoodsRecycleDO record,FinanceManageGoodsGrantDO goodsGrant);

	/**
	 * 批量更新
	 * @param cashDo
	 * @param recyleDo
	 * @return
	 */
	Boolean updateBatch(List<FinanceManageCashSerialDO> cashDo, List<FinanceManageGoodsRecycleDO> recyleDo);

	/**
	 * 批量更新-包含发放的新增
	 * @param cashDo
	 * @param recyleDo
	 * @param grantAdd
	 * @return
	 */
	Boolean updateBatchAndGrantInsert(List<FinanceManageCashSerialDO> cashDo,
			List<FinanceManageGoodsRecycleDO> recyleDo, List<FinanceManageGoodsGrantDO> grantAdd);

	/**
	 * 回滚
	 * @param cashDo
	 * @param recyleDo
	 * @return
	 */
	Boolean rollBatch(List<FinanceManageCashSerialDO> cashDo, List<FinanceManageGoodsRecycleDO> recyleDo);

	/**
	 * 回滚包含发放的删除
	 * @param cashDo
	 * @param recyleDo
	 * @param grantAdd
	 * @return
	 */
	Boolean rollBatchGrant(List<FinanceManageCashSerialDO> cashDo, List<FinanceManageGoodsRecycleDO> recyleDo,
			List<FinanceManageGoodsGrantDO> grantAdd);
}
