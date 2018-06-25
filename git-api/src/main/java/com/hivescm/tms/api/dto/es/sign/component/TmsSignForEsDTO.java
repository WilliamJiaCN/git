package com.hivescm.tms.api.dto.es.sign.component;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TmsSignForEsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("签收主表信息")
	private SignEsDTO signEsDTO;
	@ApiModelProperty("签收明细信息")
	private List<SignDetailsEsDTO> signDetailsEsDTO;
	@ApiModelProperty("费用信息")
	private SignFeeEsDTO signFeeEsDTO;
	
}
