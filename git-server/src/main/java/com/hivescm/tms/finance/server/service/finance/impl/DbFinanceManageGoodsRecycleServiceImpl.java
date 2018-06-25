package com.hivescm.tms.finance.server.service.finance.impl;

import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsRecycleDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageGoodsRecycleMapper;
import com.hivescm.tms.finance.server.service.finance.DbFinanceManageGoodsRecycleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 15:09
 * @company 蜂网供应链管理（上海）有限公司
 */
@Service
@Slf4j
public class DbFinanceManageGoodsRecycleServiceImpl implements DbFinanceManageGoodsRecycleService {

    @Autowired
    private FinanceManageGoodsRecycleMapper financeManageGoodsRecycleMapper;

    @Override
    public Boolean insert(FinanceManageGoodsRecycleDO financeManageGoodsRecycleDO) {
        return financeManageGoodsRecycleMapper.insert(financeManageGoodsRecycleDO)==1?true:false;
    }

    @Override
    public Boolean update(FinanceManageGoodsRecycleDO financeManageGoodsRecycleDO) {
        return financeManageGoodsRecycleMapper.updateByPrimaryKeySelective(financeManageGoodsRecycleDO)==1?true:false;
    }
}
