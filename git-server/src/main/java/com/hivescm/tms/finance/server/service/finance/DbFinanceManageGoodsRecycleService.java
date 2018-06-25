package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsRecycleDO;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 15:09
 * @company 蜂网供应链管理（上海）有限公司
 */
public interface DbFinanceManageGoodsRecycleService {

    Boolean insert(FinanceManageGoodsRecycleDO financeManageGoodsRecycleDO);

    Boolean update(FinanceManageGoodsRecycleDO financeManageGoodsRecycleDO);
}
