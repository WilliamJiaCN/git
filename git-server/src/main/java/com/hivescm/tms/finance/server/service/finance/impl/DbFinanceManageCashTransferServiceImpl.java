package com.hivescm.tms.finance.server.service.finance.impl;

import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashBatchDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashTransferDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageCashSerialMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageCashTransferMapper;
import com.hivescm.tms.finance.server.service.finance.DbFinanceManageCashTransferService;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description:
 * @Author: LiXuan
 * @Date: Created in 2018/5/11 10:35
 * @company 蜂网供应链管理（上海）有限公司
 */
@Service
@Slf4j
public class DbFinanceManageCashTransferServiceImpl implements DbFinanceManageCashTransferService{

    @Autowired
    private FinanceManageCashTransferMapper financeManageCashTransferMapper;
    @Autowired
    private FinanceManageCashSerialMapper financeManageCashSerialMapper;

    @Override
    @ChainedTransaction(mapper = {FinanceManageCashTransferMapper.class,FinanceManageCashSerialMapper.class},timeout = TransactionConstants.TIME_OUT)
    public Boolean insert(@RouteParam("FinanceManageCashTransferMapper.companyId") FinanceManageCashTransferDO financeManageCashTransferDO
    		, @RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashBatchDO cashUpdate) {
    	Boolean insert = financeManageCashTransferMapper.insert(financeManageCashTransferDO)>0;
        Boolean update = true;
    	if(cashUpdate.getIds().size()>0){
            update = financeManageCashSerialMapper.updateSubmitCodeBatch(cashUpdate)>0;
        }

    	return insert&&update;
    }

    @Override
    @ChainedTransaction(mapper = {FinanceManageCashTransferMapper.class},timeout = TransactionConstants.TIME_OUT)
    public Boolean update(@RouteParam("FinanceManageCashTransferMapper.companyId") FinanceManageCashTransferDO financeManageCashTransferDO) {
        return financeManageCashTransferMapper.updateByPrimaryKeySelective(financeManageCashTransferDO)>0?true:false;
    }

}
