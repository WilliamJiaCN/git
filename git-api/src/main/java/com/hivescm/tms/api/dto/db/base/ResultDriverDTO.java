package com.hivescm.tms.api.dto.db.base;

import java.util.List;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class ResultDriverDTO {

	private List<BaseDriverDTO> baseDriverDTOs;
	
	private Integer totalNum;
	
	private Integer totalPage;
	
}
