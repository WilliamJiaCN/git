package com.hivescm.tms.api.dto.es.base.request;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class VehicleOperationDTO extends PageQurey {

	private String[] ids;
	
	
	private Integer opreatUserId;
	private String opreatUserName;
	
	private Long companyId;


}
