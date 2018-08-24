package com.hivescm.tms.api.dto.es.handlingorder.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 异常提供派车单信息
 *
 * @author 李洪春
 */
@Data
@ToString
public class HandlingExceptionResDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 72214749865435368L;

	  @Mapping
	  @ApiModelProperty("主键")
	   private Long id;
	  
		@Mapping
		@ApiModelProperty("装卸队Id")
		private Long handlingTeam;
		
		@Mapping
		@ApiModelProperty("装卸队名称")
		private String handlingTeamName;

		@Mapping
		@ApiModelProperty("装卸单号")
		private String handlingOrderCode;
		
		@Mapping
		@ApiModelProperty("装卸类型（1，装货 2卸货）")
		private Integer handlingType;

		@Mapping
		@ApiModelProperty("装卸类型名称")
		private String handlingTypeName;
}