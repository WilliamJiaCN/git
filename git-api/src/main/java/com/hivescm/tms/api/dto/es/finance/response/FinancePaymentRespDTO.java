package com.hivescm.tms.api.dto.es.finance.response;

import com.hivescm.common.domain.PagedList;
import com.hivescm.tms.api.dto.es.finance.PayOriginazationDTO;
import com.hivescm.tms.api.dto.es.finance.PayeeObjectDTO;
import com.hivescm.tms.api.dto.es.finance.component.FinancePaymentESDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
@ToString
public class FinancePaymentRespDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -931532901072859210L;
	
	PagedList<FinancePaymentESDTO> financePaymentEsList = new PagedList<FinancePaymentESDTO>(); //付款记录,分页信息等
	
	List<PayOriginazationDTO> payOriginazationList = new ArrayList<PayOriginazationDTO>(); //付款组织
	
	List<PayeeObjectDTO> payeeObjectList = new ArrayList<PayeeObjectDTO>(); //收款方

}
