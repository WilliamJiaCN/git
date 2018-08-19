package com.hivescm.tms.api.dto.es.line;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class LineDeliveryInfoEsDTO implements Serializable{

	private static final long serialVersionUID = 1L;
    @Logger
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
    @Logger
	@Mapping
    @ApiModelProperty("仓库id")
    private Long warehouseId;
	@Mapping
    @ApiModelProperty("线路名称")
    private String lineName;
	@Mapping
    @ApiModelProperty("省份编码")
    private String provinceCode;
	@Mapping
    @ApiModelProperty("城市编码")
    private String cityCode;
	@Mapping
    @ApiModelProperty("线路说明")
    private String lineDesc;
	@Mapping
    @ApiModelProperty("线路状态")
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
	
	/***********************************冗余数据*****************************/
	@Mapping
    @ApiModelProperty("仓库名称")
    private String warehouseName;
	@Mapping
    @ApiModelProperty("创建人")
    private String createUserName;
	@Mapping
	@ApiModelProperty("修改人")
    private String updateUserName;
	@Mapping
    @ApiModelProperty("省份编码")
    private String provinceName;
    @Mapping
    @ApiModelProperty("城市编码")
	private String cityName;
}
