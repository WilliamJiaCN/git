package com.hivescm.tms.finance.server.service.db.impl;

import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageReceiptDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageCashSerialMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageGoodsGrantMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageReceiptMapper;
import com.hivescm.tms.finance.server.service.db.DbFinanceManageGrantService;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author wenxiong.jia
* @since 2018/5/8
*/
@Service
public class DbFinanceManageGrantServiceImpl implements DbFinanceManageGrantService {

	@Autowired
	private FinanceManageGoodsGrantMapper financeManageGoodsGrantMapper;
	@Autowired
	private FinanceManageCashSerialMapper financeManageCashSerialMapper;
	@Autowired
	private FinanceManageReceiptMapper financeManageReceiptMapper;

	@Override
	public Boolean oprGrantConfirmDB(FinanceManageGoodsGrantDO financeManageGoodsGrantDo, FinanceManageCashSerialDO financeManageCashSerialDo) {
		financeManageGoodsGrantMapper.updateByPrimaryKeySelective(financeManageGoodsGrantDo);
		financeManageCashSerialMapper.insert(financeManageCashSerialDo);
		return true;
	}

	@Override
	public Boolean oprGrantConfirmDB(FinanceManageGoodsGrantDO financeManageGoodsGrantDo, FinanceManageReceiptDO financeManageReceiptDo, FinanceManageCashSerialDO financeManageCashSerialDo) {
		financeManageGoodsGrantMapper.updateByPrimaryKeySelective(financeManageGoodsGrantDo);
		financeManageReceiptMapper.updateByPrimaryKeySelective(financeManageReceiptDo);
		financeManageCashSerialMapper.insert(financeManageCashSerialDo);
		return true;
	}

	@Override
	public Boolean oprGrantConfirmDB(FinanceManageGoodsGrantDO financeManageGoodsGrantDo, FinanceManageCashSerialDO originalFinanceManageCashSerialDo, FinanceManageCashSerialDO newFinanceManageCashSerialDo) {
		financeManageGoodsGrantMapper.updateByPrimaryKeySelective(financeManageGoodsGrantDo);
		financeManageCashSerialMapper.updateByPrimaryKeySelective(originalFinanceManageCashSerialDo);
		financeManageCashSerialMapper.insert(newFinanceManageCashSerialDo);
		return true;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageGoodsGrantMapper.class, FinanceManageCashSerialMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean oprIncomeConfirmDB(@RouteParam("FinanceManageGoodsGrantMapper.companyId") FinanceManageGoodsGrantDO financeManageGoodsGrantDo, @RouteParam("FinanceManageCashSerialMapper.companyId") List<FinanceManageCashSerialDO> financeManageCashSerialDoList) {
		financeManageGoodsGrantMapper.updateBatchAcountStatus(financeManageGoodsGrantDo);
		financeManageCashSerialMapper.insertBatch(financeManageCashSerialDoList);
		return true;
	}
}
