package com.hivescm.tms.api.dto.es.outbill.resp;

import com.hivescm.tms.api.dto.es.outbill.OutBillCompanyEsDTO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class OutbillInfoToFinance {

	private String outbillNum;
	
	private Long outbillDetailId;
	
	private List<OutBillCompanyEsDTO> companys;
}
