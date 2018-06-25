package com.hivescm.tms.finance.server.component.finance;

import com.hivescm.tms.api.dto.es.finance.component.SettleOrgDTO;
import com.hivescm.tms.intranet.gateway.api.dto.boss.LogisticsOrgFuncInfoBean;
import com.hivescm.tms.intranet.gateway.api.dto.boss.finance.CodeOpParam;
import com.hivescm.tms.intranet.gateway.api.dto.boss.finance.SettleBillDTO;

/**
 * @author 杨彭伟
 * @date 2018-01-22 20:44
 */
public interface FinanceCommonService {
    /**
     * 查询结算组织
     * @param id
     * @param type 1.carrier 2.dealer
     * @return
     */
    SettleOrgDTO getSettleOrg(Integer id, int type);

    /**
     * 查询组织ID
     *
     * @param id
     * @return
     */
    LogisticsOrgFuncInfoBean getOrgId(Integer id);

    /**
     * 创建收/付款单
     *
     * @param settleParam
     * @return
     */
    SettleBillDTO createPayInfo(SettleBillDTO settleParam);

    /**
     * 取消收/付款单
     *
     * @param opParam
     * @return
     */
    Boolean deleteBySource(CodeOpParam opParam);
}
