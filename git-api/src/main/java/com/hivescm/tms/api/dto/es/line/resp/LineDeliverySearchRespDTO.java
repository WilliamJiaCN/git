package com.hivescm.tms.api.dto.es.line.resp;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.line.LineAreaEsDTO;
import com.hivescm.tms.api.dto.es.line.LineElectronicFenceEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class LineDeliverySearchRespDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
    @ApiModelProperty("省份编码")
    private String provinceCode;
	@ApiModelProperty("城市编码")
	private String cityCode;
	@Mapping
    @ApiModelProperty("省份名称")
    private String provinceName;
    @Mapping
    @ApiModelProperty("城市名称")
	private String cityName;
	@ApiModelProperty("线路名称")
    private String lineName;
	@ApiModelProperty("线路说明")
	private String lineDesc;
	@ApiModelProperty("覆盖区域")
	private List<LineAreaEsDTO> lineArea;
	@ApiModelProperty("电子围栏")
	private List<LineElectronicFenceEsDTO> lineElectronicFence;
	@ApiModelProperty("配送服务")
	private List<LineServiceRespDTO> lineService;
}
