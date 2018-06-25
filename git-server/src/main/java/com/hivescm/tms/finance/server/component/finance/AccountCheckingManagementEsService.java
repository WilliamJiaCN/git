package com.hivescm.tms.finance.server.component.finance;

import java.util.List;

import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.tms.api.dto.es.sign.AccountCheckingManagementEsDTO;

public interface AccountCheckingManagementEsService {
	
	public Boolean batchSave(List<AccountCheckingManagementEsDTO> list)throws Exception;
	
	public List<AccountCheckingManagementEsDTO> getAllSelective(List<SearchCondition> searchConditions,List<OrderCondition> orderConditions,PageCondition pageCondition) throws Exception;
}
