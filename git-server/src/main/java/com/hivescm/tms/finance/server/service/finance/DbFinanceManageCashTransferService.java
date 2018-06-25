package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashBatchDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashTransferDO;


public interface DbFinanceManageCashTransferService {
    /**
     * 新增现金转账
     * @param financeManageCashTransferDO
     * @param cashUpdate 
     * @return
     */
    Boolean insert(FinanceManageCashTransferDO financeManageCashTransferDO, FinanceManageCashBatchDO cashUpdate);

    /**
     * 更新现金转账
     * @param financeManageCashTransferDO
     * @return
     */
    Boolean update(FinanceManageCashTransferDO financeManageCashTransferDO);

}
