package com.hivescm.tms.finance.server.component.finance.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.tms.api.dto.es.finance.request.ReceivableFinanceReceiptEsDTO;
import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.component.finance.DbFinanceManageReceiptCompentService;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageReceiptDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageCashSerialMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageGoodsGrantMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageReceiptMapper;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;

@Service
public class DbFinanceManageReceiptCompentServiceImpl implements DbFinanceManageReceiptCompentService {

	@Autowired
    private FinanceManageReceiptMapper financeManageReceiptMapper;
	@Autowired
	private FinanceManageCashSerialMapper financeManageCashSerialMapper;
	@Autowired
	private FinanceManageGoodsGrantMapper financeManageGoodsGrantMapper;
	
	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageReceiptMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean updateByReceipt(@RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO createCashSerialEntity,
			@RouteParam("FinanceManageReceiptMapper.companyId")ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO) {
		Boolean cashDo = financeManageCashSerialMapper.insert(createCashSerialEntity)>0;
		Boolean cash = financeManageReceiptMapper.updateAmount(receivableFinanceReceiptEsDTO)>0;
		return cashDo&&cash;
	
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageReceiptMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean cancleByReceipt(@RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO insertDto, 
			@RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO updateDO,
			@RouteParam("FinanceManageReceiptMapper.companyId") ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO) {
		Boolean cashDo = financeManageCashSerialMapper.insert(insertDto)>0;
		Boolean update = financeManageCashSerialMapper.updateByPrimaryKeySelective(updateDO)>0;
		Boolean cash = financeManageReceiptMapper.updateAmount(receivableFinanceReceiptEsDTO)>0;
		return cashDo&&update&&cash;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageReceiptMapper.class,FinanceManageGoodsGrantMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean updateByReceiptDuction(@RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO createCashSerialEntity,
			@RouteParam("FinanceManageGoodsGrantMapper.companyId") FinanceManageGoodsGrantDO record, 
			@RouteParam("FinanceManageReceiptMapper.companyId")ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO) {
		Boolean cashDo = financeManageCashSerialMapper.insert(createCashSerialEntity)>0;
		Boolean cash = financeManageReceiptMapper.updateAmount(receivableFinanceReceiptEsDTO)>0;
		Boolean recyle = financeManageGoodsGrantMapper.updateByPrimaryKeySelective(record)>0;
		return cashDo&&cash&&recyle;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageReceiptMapper.class,FinanceManageGoodsGrantMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean cancleByReceiptDuction(@RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO insertDto, 
			@RouteParam("FinanceManageCashSerialMapper.companyId")  FinanceManageCashSerialDO updateDO,
			@RouteParam("FinanceManageGoodsGrantMapper.companyId") FinanceManageGoodsGrantDO record, 
			@RouteParam("FinanceManageReceiptMapper.companyId") ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO) {
		Boolean cashDo = financeManageCashSerialMapper.insert(insertDto)>0;
		Boolean update = financeManageCashSerialMapper.updateByPrimaryKeySelective(updateDO)>0;
		Boolean cash = financeManageReceiptMapper.updateAmount(receivableFinanceReceiptEsDTO)>0;
		Boolean recyle = financeManageGoodsGrantMapper.updateByPrimaryKeySelective(record)>0;
		return cashDo&&update&&cash&&recyle;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageReceiptMapper.class,FinanceManageGoodsGrantMapper.class},timeout= TransactionConstants.TIME_OUT)
	public boolean deleteDbBatch(@RouteParam("FinanceManageCashSerialMapper.companyId") List<FinanceManageCashSerialDO> rollCash, 
			@RouteParam("FinanceManageReceiptMapper.companyId") List<FinanceManageReceiptDO> receipt,
			@RouteParam("FinanceManageGoodsGrantMapper.companyId") List<FinanceManageGoodsGrantDO> grant) {
		financeManageCashSerialMapper.deleteBatch(rollCash);
		financeManageReceiptMapper.updateByPrimaryKeySelectiveBatch(receipt);
		financeManageGoodsGrantMapper.updateBatch(grant);
		return true;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageReceiptMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean updateBatch(@RouteParam("FinanceManageCashSerialMapper.companyId") List<FinanceManageCashSerialDO> cashDo, 
			@RouteParam("FinanceManageReceiptMapper.companyId") List<FinanceManageReceiptDO> receiptDo) {
		Boolean cash = financeManageCashSerialMapper.insertBatch(cashDo)>0;
		Boolean receipt = financeManageReceiptMapper.updateByPrimaryKeySelectiveBatch(receiptDo)>0;
		return cash&&receipt;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageReceiptMapper.class,FinanceManageGoodsGrantMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean updateBatchByDuction(@RouteParam("FinanceManageCashSerialMapper.companyId") List<FinanceManageCashSerialDO> cashDo, 
			@RouteParam("FinanceManageReceiptMapper.companyId") List<FinanceManageReceiptDO> receiptDo,
			@RouteParam("FinanceManageGoodsGrantMapper.companyId") List<FinanceManageGoodsGrantDO> grantDo) {
		Boolean cash = financeManageCashSerialMapper.insertBatch(cashDo)>0;
		Boolean receipt = financeManageReceiptMapper.updateByPrimaryKeySelectiveBatch(receiptDo)>0;
		Boolean grant = financeManageGoodsGrantMapper.updateBatch(grantDo)>0;
		return cash&&receipt&&grant;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageReceiptMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean rollBatch(@RouteParam("FinanceManageCashSerialMapper.companyId") List<FinanceManageCashSerialDO> cashDo, 
			@RouteParam("FinanceManageReceiptMapper.companyId") List<FinanceManageReceiptDO> receiptDo) {
		Boolean cash = financeManageCashSerialMapper.deleteBatch(cashDo)>0;
		Boolean receipt = financeManageReceiptMapper.updateByPrimaryKeySelectiveBatch(receiptDo)>0;
		return cash&&receipt;
	}

	@Override
	@ChainedTransaction(mapper={FinanceManageCashSerialMapper.class,FinanceManageReceiptMapper.class,FinanceManageGoodsGrantMapper.class},timeout= TransactionConstants.TIME_OUT)
	public Boolean rollBatchByDuction(@RouteParam("FinanceManageCashSerialMapper.companyId") List<FinanceManageCashSerialDO> cashDo, 
			@RouteParam("FinanceManageReceiptMapper.companyId") List<FinanceManageReceiptDO> receiptDo,
			@RouteParam("FinanceManageGoodsGrantMapper.companyId") List<FinanceManageGoodsGrantDO> grantDo) {
		Boolean cash = financeManageCashSerialMapper.deleteBatch(cashDo)>0;
		Boolean receipt = financeManageReceiptMapper.updateByPrimaryKeySelectiveBatch(receiptDo)>0;
		Boolean grant = financeManageGoodsGrantMapper.updateBatch(grantDo)>0;
		return cash&&receipt&&grant;
	}

}
