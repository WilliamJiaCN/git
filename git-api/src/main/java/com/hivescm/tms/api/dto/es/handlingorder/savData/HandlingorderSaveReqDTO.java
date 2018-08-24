package com.hivescm.tms.api.dto.es.handlingorder.savData;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class HandlingorderSaveReqDTO implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -461566631237735126L;
	@Mapping
	@ApiModelProperty("主键(修改时传值)")
	private Long id;
	/**
	 * 公司Id
	 */
	@ApiModelProperty(hidden = true)
	private Long companyId;
	@ApiModelProperty(hidden = true)
	private String companyName;
	
	@ApiModelProperty(hidden = true)
	private Long branchId;
	@ApiModelProperty(hidden = true)
	private String branchName;
	
	/**
	 * 公司Id
	 */
	@ApiModelProperty(hidden = true)
	private Integer groupId;

	/**
	 * 操作用户ID
	 */
	@ApiModelProperty(hidden = true)
	private Integer opUserId;

	/**
	 * 操作用户名称
	 */
	@ApiModelProperty(hidden = true)
	private String opUserName;

	/**
	 * "装卸单主表信息"
	 */
	@ApiModelProperty("装卸单主表信息")
	private HandlingorderReqDTO handlingorderEsDTO;

	/**
	 * 装卸单明细信息列表
	 */
	@ApiModelProperty("装卸单明细信息列表")
	private List<TmsHandlingorderDetailReqDTO> handlingorderDetailEsDTOList;

	@ApiModelProperty("装卸单費用列表")
	private List<HandlingorderFeeReqDTO> handlingorderFeeEsDTOList;
 

}
