package com.hivescm.tms.api.dto.db.base;

import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class ResultVehicleDTO {

	private List<BaseVehicleDTO> bseVehicleList;
	
	private Integer totalNum;
	
	private Integer totalPage;
	
}
