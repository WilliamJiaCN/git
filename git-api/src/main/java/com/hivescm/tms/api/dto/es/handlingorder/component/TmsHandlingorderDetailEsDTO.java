package com.hivescm.tms.api.dto.es.handlingorder.component;

import com.hivescm.tms.api.dto.es.handlingorder.HandlingorderDetailEsDTO;
import com.hivescm.tms.api.dto.es.handlingorder.HandlingorderGoodsEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class TmsHandlingorderDetailEsDTO  implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3554386572338719958L;
	@ApiModelProperty("装卸单运单信息")
    private HandlingorderDetailEsDTO handlingorderDetailEsDTO;
	@ApiModelProperty("装卸单货物列表")
	private List<HandlingorderGoodsEsDTO> handlingorderGoodsEsDTOList;
}
