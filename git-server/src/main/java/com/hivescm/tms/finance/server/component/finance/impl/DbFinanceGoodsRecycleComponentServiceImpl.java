package com.hivescm.tms.finance.server.component.finance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.component.finance.DbFinanceGoodsRecycleComponentService;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsRecycleDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageCashSerialMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageGoodsGrantMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageGoodsRecycleMapper;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;

@Service
public class DbFinanceGoodsRecycleComponentServiceImpl implements DbFinanceGoodsRecycleComponentService {

	@Autowired
	private FinanceManageCashSerialMapper financeManageCashSerialMapper;
	@Autowired
	private FinanceManageGoodsRecycleMapper financeManageGoodsRecycleMapper;
	@Autowired
	private FinanceManageGoodsGrantMapper financeManageGoodsGrantMapper;
	
	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageGoodsRecycleMapper.class,FinanceManageGoodsGrantMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean saveRecycle(@RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO createCashSerialEntity,
			@RouteParam("FinanceManageGoodsRecycleMapper.companyId")FinanceManageGoodsRecycleDO record,
			@RouteParam("FinanceManageGoodsGrantMapper.companyId")FinanceManageGoodsGrantDO goodsGrant) {
		Boolean cashDo = financeManageCashSerialMapper.insert(createCashSerialEntity)>0;
		Boolean update = financeManageGoodsRecycleMapper.updateByPrimaryKeySelective(record)>0;
		Boolean grant = financeManageGoodsGrantMapper.insert(goodsGrant)>0;
		return cashDo&&update&&grant;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageGoodsRecycleMapper.class,FinanceManageGoodsGrantMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean cancleRecycle(@RouteParam("FinanceManageCashSerialMapper.companyId")FinanceManageCashSerialDO createCashSerialEntity,
			@RouteParam("FinanceManageCashSerialMapper.companyId")FinanceManageCashSerialDO updateDO,
			@RouteParam("FinanceManageGoodsRecycleMapper.companyId")FinanceManageGoodsRecycleDO record,
			@RouteParam("FinanceManageGoodsGrantMapper.companyId")FinanceManageGoodsGrantDO goodsGrant) {
		Boolean cashDo = financeManageCashSerialMapper.insert(createCashSerialEntity)>0;
		Boolean update = financeManageCashSerialMapper.updateByPrimaryKeySelective(updateDO)>0;
		Boolean updateRecyle = financeManageGoodsRecycleMapper.updateByPrimaryKeySelective(record)>0;
		Boolean updateGrant = financeManageGoodsGrantMapper.updateByPrimaryKeySelective(goodsGrant)>0;
		return cashDo&&update&&updateRecyle&&updateGrant;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageGoodsRecycleMapper.class,FinanceManageGoodsGrantMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean saveRecycleUpdateGrant(@RouteParam("FinanceManageCashSerialMapper.companyId")FinanceManageCashSerialDO createCashSerialEntity,
			@RouteParam("FinanceManageGoodsRecycleMapper.companyId")FinanceManageGoodsRecycleDO record, 
			@RouteParam("FinanceManageGoodsGrantMapper.companyId")FinanceManageGoodsGrantDO goodsGrant) {
		Boolean cashDo = financeManageCashSerialMapper.insert(createCashSerialEntity)>0;
		Boolean update = financeManageGoodsRecycleMapper.updateByPrimaryKeySelective(record)>0;
		Boolean grant = financeManageGoodsGrantMapper.updateByPrimaryKeySelective(goodsGrant)>0;
		return cashDo&&update&&grant;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageGoodsRecycleMapper.class,FinanceManageGoodsGrantMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean cancleRecycleDeleteGrant(@RouteParam("FinanceManageCashSerialMapper.companyId")FinanceManageCashSerialDO createCashSerialEntity,
			@RouteParam("FinanceManageCashSerialMapper.companyId")FinanceManageCashSerialDO updateDO, 
			@RouteParam("FinanceManageGoodsRecycleMapper.companyId")FinanceManageGoodsRecycleDO record,
			@RouteParam("FinanceManageGoodsGrantMapper.companyId")FinanceManageGoodsGrantDO goodsGrant) {
		Boolean cashDo = financeManageCashSerialMapper.insert(createCashSerialEntity)>0;
		Boolean update = financeManageCashSerialMapper.updateByPrimaryKeySelective(updateDO)>0;
		Boolean updateRecyle = financeManageGoodsRecycleMapper.updateByPrimaryKeySelective(record)>0;
		Boolean updateGrant = financeManageGoodsGrantMapper.delete(goodsGrant)>0;
		return cashDo&&update&&updateRecyle&&updateGrant;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageGoodsRecycleMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean updateBatch(@RouteParam("FinanceManageCashSerialMapper.companyId") List<FinanceManageCashSerialDO> cashDo, 
			@RouteParam("FinanceManageGoodsRecycleMapper.companyId") List<FinanceManageGoodsRecycleDO> recyleDo) {
		Boolean cash = financeManageCashSerialMapper.insertBatch(cashDo)>0;
		Boolean update = financeManageGoodsRecycleMapper.updateByBatch(recyleDo)>0;
		return cash&&update;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageGoodsRecycleMapper.class,FinanceManageGoodsGrantMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean updateBatchAndGrantInsert(@RouteParam("FinanceManageCashSerialMapper.companyId") List<FinanceManageCashSerialDO> cashDo,
			@RouteParam("FinanceManageGoodsRecycleMapper.companyId")  List<FinanceManageGoodsRecycleDO> recyleDo, 
			@RouteParam("FinanceManageGoodsGrantMapper.companyId") List<FinanceManageGoodsGrantDO> grantAdd) {
		Boolean cash = financeManageCashSerialMapper.insertBatch(cashDo)>0;
		Boolean update = financeManageGoodsRecycleMapper.updateByBatch(recyleDo)>0;
		Boolean grant = financeManageGoodsGrantMapper.insertBatch(grantAdd)>0;
		return cash&&update&&grant;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageGoodsRecycleMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean rollBatch(@RouteParam("FinanceManageCashSerialMapper.companyId")List<FinanceManageCashSerialDO> cashDo, 
			@RouteParam("FinanceManageGoodsRecycleMapper.companyId") List<FinanceManageGoodsRecycleDO> recyleDo) {
		Boolean cash = financeManageCashSerialMapper.deleteBatch(cashDo)>0;
		Boolean update = financeManageGoodsRecycleMapper.updateByBatch(recyleDo)>0;
		return cash&&update;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageGoodsRecycleMapper.class,FinanceManageGoodsGrantMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean rollBatchGrant(@RouteParam("FinanceManageCashSerialMapper.companyId")List<FinanceManageCashSerialDO> cashDo, 
			@RouteParam("FinanceManageGoodsRecycleMapper.companyId") List<FinanceManageGoodsRecycleDO> recyleDo,
			@RouteParam("FinanceManageGoodsGrantMapper.companyId") List<FinanceManageGoodsGrantDO> grantAdd) {
		Boolean cash = financeManageCashSerialMapper.deleteBatch(cashDo)>0;
		Boolean update = financeManageGoodsRecycleMapper.updateByBatch(recyleDo)>0;
		Boolean grant = financeManageGoodsGrantMapper.deleteBatch(grantAdd)>0;
		return cash&&update&&grant;
	}
}
