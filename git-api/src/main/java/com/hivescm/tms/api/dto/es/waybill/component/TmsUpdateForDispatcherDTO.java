package com.hivescm.tms.api.dto.es.waybill.component;


import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class TmsUpdateForDispatcherDTO {

	/**
	 * 派车单Id
	 */
	@Logger
	@Required
	@ApiModelProperty("派车单Id")
	private Long dispatcherId;

	/**
	 * 派车单Id
	 */
	@Logger
	@Required
	@ApiModelProperty("派车单明细Id")
	private Long dispatcherDetailId;


	@Required
	@ApiModelProperty("操作类型（1.提货入库 2补录运单）")
	private Integer type;
	
	@Mapping
	private @ApiModelProperty("修改人") Integer updateUser;
	/**
	 * 修改人姓名
	 */
	@Mapping
	private @ApiModelProperty("修改人姓名") String updateUserName;

	/**
	 * 派车单明细ID列表
	 */
	@ApiModelProperty(value = "运单基本信息", notes = "派车单提货入库时需传此参数")
	private TmsWaybillEsDTO waybillEsDto;
	
	
	

}
