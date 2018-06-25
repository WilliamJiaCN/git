package com.hivescm.tms.finance.server.service.finance.impl;

import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageCashSerialMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageReceiptMapper;
import com.hivescm.tms.finance.server.service.finance.DbFinanceManageCashSerialService;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/4 18:52
 * @company 蜂网供应链管理（上海）有限公司
 */
@Service
@Slf4j
public class DbFinanceManageCashSerialServiceImpl implements DbFinanceManageCashSerialService {

    @Autowired
    private FinanceManageCashSerialMapper financeManageCashSerialMapper;

    @ChainedTransaction(mapper={FinanceManageCashSerialMapper.class},timeout= TransactionConstants.TIME_OUT)
    public Boolean insert(@RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO financeManageCashSerialDO) {
    	Boolean insert = financeManageCashSerialMapper.insert(financeManageCashSerialDO)>0;
    	return insert;
    }

    @Override
    @ChainedTransaction(mapper={FinanceManageCashSerialMapper.class},timeout= TransactionConstants.TIME_OUT)
    public boolean updateById(@RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO financeManageCashSerialDO) {
        return financeManageCashSerialMapper.updateByPrimaryKeySelective(financeManageCashSerialDO)==1?true:false;
    }

    @Override
    public boolean updateBatch(@RouteParam("FinanceManageCashSerialMapper.companyId") List<FinanceManageCashSerialDO> financeManageCashSerialDO) {
        return financeManageCashSerialMapper.updateBatch(financeManageCashSerialDO)>0;
    }

    @Override
    public boolean updateBatchContainsNull(@RouteParam("FinanceManageCashSerialMapper.companyId") List<FinanceManageCashSerialDO> financeManageCashSerialDO) {
        return financeManageCashSerialMapper.updateBatchContainsNull(financeManageCashSerialDO)>0;
    }

}
