package com.hivescm.tms.api.dto.es.line;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class LineCarrierServiceEsDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Logger
	@Mapping
	@ApiModelProperty("主键")
    private Long id;
	@Mapping
    @ApiModelProperty("承运商线路id")
    private Long carrierLineId;
	@Mapping
    @ApiModelProperty("线路服务id")
    private Long lineServiceId;
	@Mapping
    @ApiModelProperty("承运商线路服务状态")
    private Integer status;
	@Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;
	/******************************************冗余线路服务信息字段**************************************/
	@Mapping
	@ApiModelProperty("服务id")
	private Long serviceId;
}
