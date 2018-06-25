package com.hivescm.tms.finance.server.service.finance;

import java.util.List;

import com.hivescm.tms.intranet.gateway.api.dto.boss.IFinanceEntrustRelationInfoDto;

/**
 * 财务账户接口
 *
 * @author 杨彭伟
 * @date 2017-11-21 15:58
 */
public interface FinanceRelationService {

	/**
	 * 查询组织的结算组织和核算组织
	 * @param orgId 组织id
	 * @return
	 */
	List<IFinanceEntrustRelationInfoDto> getRelationship(Integer orgId);
}
