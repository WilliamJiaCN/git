package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;

import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 15:08
 * @company 蜂网供应链管理（上海）有限公司
 */
public interface DbFinanceManageGoodsGrantService {

    Boolean insert(FinanceManageGoodsGrantDO financeManageGoodsGrantDO);

    Boolean update(FinanceManageGoodsGrantDO financeManageGoodsGrantDO);

    boolean updateBatch(List<FinanceManageGoodsGrantDO> financeManageGoodsGrantDoList);
}
