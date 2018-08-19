package com.hivescm.tms.api.dto.es.packageinfo.request;

import com.hivescm.tms.api.dto.es.packageinfo.PackageInfoEsDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class CreatePackageInfoReqDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8646921000465653545L;
	
	private Long carrierGlobalId;
	
    private List<PackageInfoEsDTO> list;

}
