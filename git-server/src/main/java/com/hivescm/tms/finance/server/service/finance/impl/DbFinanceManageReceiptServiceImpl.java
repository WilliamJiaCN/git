package com.hivescm.tms.finance.server.service.finance.impl;

import com.hivescm.tms.api.dto.es.finance.request.FinanceDeleteReqDTO;
import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageReceiptDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageReceiptMapper;
import com.hivescm.tms.finance.server.service.finance.DbFinanceManageReceiptService;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DbFinanceManageReceiptServiceImpl implements DbFinanceManageReceiptService {


    @Autowired
    private FinanceManageReceiptMapper financeManageReceiptMapper;

    @Override
    @ChainedTransaction(mapper={FinanceManageReceiptMapper.class},timeout= TransactionConstants.TIME_OUT)
    public Boolean insert(@RouteParam("FinanceManageReceiptMapper.companyId") FinanceManageReceiptDO record) {
        return this.financeManageReceiptMapper.insertSelective(record)==1?true:false;
    }

    @Override
    @ChainedTransaction(mapper={FinanceManageReceiptMapper.class},timeout= TransactionConstants.TIME_OUT)
    public int insertSelective(@RouteParam("FinanceManageReceiptMapper.companyId") FinanceManageReceiptDO record) {
        return this.financeManageReceiptMapper.insertSelective(record);
    }

    @Override
    @ChainedTransaction(mapper={FinanceManageReceiptMapper.class},timeout= TransactionConstants.TIME_OUT)
    public int updateByPrimaryKeySelective(@RouteParam("FinanceManageReceiptMapper.companyId") FinanceManageReceiptDO record) {
        return this.financeManageReceiptMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    @ChainedTransaction(mapper={FinanceManageReceiptMapper.class},timeout= TransactionConstants.TIME_OUT)
    public int updateByPrimaryKey(@RouteParam("FinanceManageReceiptMapper.companyId") FinanceManageReceiptDO record) {
        return this.financeManageReceiptMapper.updateByPrimaryKey(record);
    }

    @ChainedTransaction(mapper={FinanceManageReceiptMapper.class},timeout= TransactionConstants.TIME_OUT)
    public int insertBatch(@RouteParam("FinanceManageReceiptMapper.companyId")List<FinanceManageReceiptDO> list) {
        return this.financeManageReceiptMapper.insertBatch(list);
    }

	@Override
	@ChainedTransaction(mapper={FinanceManageReceiptMapper.class},timeout= TransactionConstants.TIME_OUT)
	public int deleteByCode(@RouteParam("FinanceManageReceiptMapper.companyId")FinanceDeleteReqDTO financeDeleteReqDTO) {
		return this.financeManageReceiptMapper.deleteByCode(financeDeleteReqDTO);
	}

    @Override
    @ChainedTransaction(mapper={FinanceManageReceiptMapper.class},timeout= TransactionConstants.TIME_OUT)
    public boolean updateByPrimaryKeySelectiveBatch(@RouteParam("FinanceManageReceiptMapper.companyId")List<FinanceManageReceiptDO> financeManageReceiptDOList) {
        return this.financeManageReceiptMapper.updateByPrimaryKeySelectiveBatch(financeManageReceiptDOList)>0?true:false;
    }

    @Override
    public boolean deleteByPayway(FinanceDeleteReqDTO financeDeleteReqDTO) {
        return financeManageReceiptMapper.deleteByPayway(financeDeleteReqDTO) > 0;
    }
}
