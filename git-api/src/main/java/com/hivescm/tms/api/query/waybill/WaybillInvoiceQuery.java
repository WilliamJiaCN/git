package com.hivescm.tms.api.query.waybill;

import com.hivescm.tms.api.dto.es.base.request.PageQurey;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WaybillInvoiceQuery extends PageQurey {

	private Integer id;
	private Integer companyId ;
	private Integer waybillId;
	
	private Integer createUser;
	private Integer updateUser;
	
	private String startTime;
	private String endTime;


	
}
