package com.hivescm.tms.api.dto.es.line.req;

import java.io.Serializable;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class LineDeliveryInfoReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
    @Logger
	@ApiModelProperty("主键")
	private Long id;
    @Logger
    @ApiModelProperty("仓库id")
    private Long warehouseId;

    @ApiModelProperty("线路名称")
    private String lineName;

    @ApiModelProperty("省份编码")
    private String provinceCode;

    @ApiModelProperty("城市编码")
    private String cityCode;

    @ApiModelProperty("线路说明")
    private String lineDesc;

    @ApiModelProperty("线路状态")
    private Integer status;

    @ApiModelProperty("创建人")
    private Integer createUser;

	@ApiModelProperty("创建时间")
    private Long createTime;

	@ApiModelProperty("修改人")
    private Integer updateUser;

	@ApiModelProperty("修改时间")
    private Long updateTime;
   
}
