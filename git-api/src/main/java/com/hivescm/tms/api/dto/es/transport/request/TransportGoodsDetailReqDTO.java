package com.hivescm.tms.api.dto.es.transport.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
public class TransportGoodsDetailReqDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7135378985769926276L;
	
	 @Required
	 @ApiModelProperty(value = "主键",required=true)
	  private Long id;
	  
	 @Required
	 @ApiModelProperty(value = "库存ID",required=true)
	  private Long waybillStockDetailId;
	
	 @Required
	 @ApiModelProperty(value ="移除件数",required=true)
	  private Integer removeAmount;
	 
}
