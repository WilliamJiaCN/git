package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;

import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/4 18:50
 * @company 蜂网供应链管理（上海）有限公司
 */
public interface DbFinanceManageCashSerialService {
    /**
     * 保存
     * @param financeManageCashSerialDO:保存对象
     * @return:是否保存成功
     */
    Boolean insert(FinanceManageCashSerialDO financeManageCashSerialDO);

    /**
     * 更新
     * @param financeManageCashSerialDO
     * @return
     */
    boolean updateById(FinanceManageCashSerialDO financeManageCashSerialDO);

    /**
     * 更新
     * @param financeManageCashSerialDO
     * @return
     */
    boolean updateBatch(List<FinanceManageCashSerialDO> financeManageCashSerialDO);


    /**
     * 转账 修改业务更新null值
     */
    boolean updateBatchContainsNull(List<FinanceManageCashSerialDO> financeManageCashSerialDO);
}
