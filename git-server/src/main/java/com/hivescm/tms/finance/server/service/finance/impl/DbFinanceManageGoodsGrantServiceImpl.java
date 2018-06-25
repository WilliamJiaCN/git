package com.hivescm.tms.finance.server.service.finance.impl;

import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageGoodsGrantMapper;
import com.hivescm.tms.finance.server.service.finance.DbFinanceManageGoodsGrantService;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 15:20
 * @company 蜂网供应链管理（上海）有限公司
 */
@Service
public class DbFinanceManageGoodsGrantServiceImpl implements DbFinanceManageGoodsGrantService {

    @Autowired
    private FinanceManageGoodsGrantMapper financeManageGoodsGrantMapper;


    @Override
    @ChainedTransaction(mapper={FinanceManageGoodsGrantMapper.class},timeout= TransactionConstants.TIME_OUT)
    public Boolean insert(@RouteParam("FinanceManageGoodsGrantMapper.companyId")FinanceManageGoodsGrantDO financeManageGoodsGrantDO) {
        return financeManageGoodsGrantMapper.insert(financeManageGoodsGrantDO)==1?true:false;
    }

    @Override
    @ChainedTransaction(mapper={FinanceManageGoodsGrantMapper.class},timeout= TransactionConstants.TIME_OUT)
    public Boolean update(@RouteParam("FinanceManageGoodsGrantMapper.companyId") FinanceManageGoodsGrantDO financeManageGoodsGrantDO) {
        return financeManageGoodsGrantMapper.updateByPrimaryKeySelective(financeManageGoodsGrantDO)==1?true:false;
    }

    @Override
    @ChainedTransaction(mapper={FinanceManageGoodsGrantMapper.class},timeout= TransactionConstants.TIME_OUT)
    public boolean updateBatch(@RouteParam("FinanceManageGoodsGrantMapper.companyId") List<FinanceManageGoodsGrantDO> financeManageGoodsGrantDoList) {
        financeManageGoodsGrantDoList.forEach(financeManageGoodsGrantMapper::updateByPrimaryKeySelective);
        return true;
    }
}
