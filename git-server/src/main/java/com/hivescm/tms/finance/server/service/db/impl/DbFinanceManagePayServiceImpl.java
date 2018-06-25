package com.hivescm.tms.finance.server.service.db.impl;

import com.hivescm.tms.api.dto.es.finance.request.FinanceDeleteReqDTO;
import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManagePayDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageCashSerialMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManagePayMapper;
import com.hivescm.tms.finance.server.service.db.DbFinanceManagePayService;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wenxiong.jia
 * @since 2018/4/27
 */
@Service
public class DbFinanceManagePayServiceImpl implements DbFinanceManagePayService {

    @Autowired
    private FinanceManagePayMapper financeManagePayMapper;
    @Autowired
    private FinanceManageCashSerialMapper financeManageCashSerialMapper;

    @Override
    @ChainedTransaction(mapper = {FinanceManagePayMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public Boolean insertBatchPayInfo(@RouteParam("FinanceManagePayMapper.companyId") List<FinanceManagePayDO> financeManagePayDoList) {
        financeManagePayMapper.insertBatch(financeManagePayDoList);
        return true;
    }

    @Override
    @ChainedTransaction(mapper = {FinanceManagePayMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public Boolean updateBatchPayInfo(@RouteParam("FinanceManagePayMapper.companyId") List<FinanceManagePayDO> financeManagePayDoList) {
        financeManagePayDoList.forEach(financeManagePayMapper::updateByPrimaryKeySelective);
        return true;
    }

    @Override
    public Boolean oprPayConfirmDB(FinanceManagePayDO financeManagePayDo, FinanceManageCashSerialDO financeManageCashSerialDo) {
        financeManagePayMapper.updateByPrimaryKeySelective(financeManagePayDo);
        financeManageCashSerialMapper.insert(financeManageCashSerialDo);
        return true;
    }

    @Override
    public Boolean oprPayCancelDB(FinanceManagePayDO financeManagePayDo, FinanceManageCashSerialDO originalFinanceManageCashSerialDo, FinanceManageCashSerialDO newFinanceManageCashSerialDo) {
        financeManagePayMapper.updateByPrimaryKeySelective(financeManagePayDo);
        financeManageCashSerialMapper.updateByPrimaryKeySelective(originalFinanceManageCashSerialDo);
        financeManageCashSerialMapper.insert(newFinanceManageCashSerialDo);
        return true;
    }

    @Override
    public int deleteByCode(FinanceDeleteReqDTO financeDeleteReqDTO) {
        return financeManagePayMapper.deleteByCode(financeDeleteReqDTO);
    }
}
