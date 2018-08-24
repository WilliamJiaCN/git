package com.hivescm.tms.api.dto.es.line.req;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class LineElectronicFenceReqDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Logger
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Logger
	@Mapping
	@ApiModelProperty("线路id")
    private Long lineId;
	@Mapping
	@ApiModelProperty("区块名称")
    private String name;
	@Mapping
	@ApiModelProperty("经度")
    private String lngs;
	@Mapping
	@ApiModelProperty("纬度")
    private String lats;
	@Mapping
	@ApiModelProperty("仓库id")
	private Long warehouseId;
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
}
