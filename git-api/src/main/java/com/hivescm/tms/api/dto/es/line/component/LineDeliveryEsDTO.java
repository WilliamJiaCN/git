package com.hivescm.tms.api.dto.es.line.component;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.line.req.LineAreaReqDTO;
import com.hivescm.tms.api.dto.es.line.req.LineElectronicFenceReqDTO;
import com.hivescm.tms.api.dto.es.line.req.LineServiceReqDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class LineDeliveryEsDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
    @ApiModelProperty("仓库id")
    private Long warehouseId;
    @Mapping
    @ApiModelProperty("线路名称(在做修改时前端注意要校验名称是否重复)")
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
    @ApiModelProperty("覆盖区域")
    private List<LineAreaReqDTO> lineArea;
    @Mapping
    @ApiModelProperty("电子围栏")
    private List<LineElectronicFenceReqDTO> lineElectronicFence;
    @Mapping
    @ApiModelProperty("线路服务")
    private List<LineServiceReqDTO> lineService;
    
    /***********************************冗余数据*****************************/
    @Mapping
    @ApiModelProperty("仓库名称")
    private String warehouseName;
    @Mapping
    @ApiModelProperty("创建人姓名")
    private String createUserName;
	@Mapping
	@ApiModelProperty("修改人姓名")
    private String updateUserName;
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
	@Mapping
    @ApiModelProperty("省份名称")
    private String provinceName;
    @Mapping
    @ApiModelProperty("城市名称")
	private String cityName;
}
