package com.hivescm.tms.api.dto.db.base;

import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class ResultDriverDTO {

	private List<BaseDriverDTO> baseDriverDTOs;
	
	private Integer totalNum;
	
	private Integer totalPage;
	
}
