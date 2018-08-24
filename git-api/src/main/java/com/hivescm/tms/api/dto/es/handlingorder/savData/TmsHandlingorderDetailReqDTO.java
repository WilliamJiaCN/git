package com.hivescm.tms.api.dto.es.handlingorder.savData;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class TmsHandlingorderDetailReqDTO  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3554386572338719958L;
	@ApiModelProperty("装卸单运单信息")
    private HandlingorderDetailReqDTO handlingorderDetailEsDTO;
	@ApiModelProperty("装卸单货物列表")
	private List<HandlingorderGoodsReqDTO> handlingorderGoodsEsDTOList;
}
