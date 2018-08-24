package com.hivescm.tms.api.dto.es.line.req;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class LineAreaReqDTO implements Serializable{
	
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
	@ApiModelProperty("区县编码")
    private String districtId;
	@Mapping
	@ApiModelProperty("街道(乡镇)编码")
    private String streetId;
	@ApiModelProperty("覆盖类型COVER_ALL=1全部覆盖 PARTIAL_COVERAGE=2部分覆盖")
    private Integer coverType;
	@Mapping
	@ApiModelProperty("详细地址")
    private String address;
	@ApiModelProperty("号码类型COVER_ALL= 全部覆盖 EVEN_NUMBER_COVERAGE=双号覆盖 ODD_NUMBERS_COVERING=单号覆盖")
    private Integer numType;
	@Mapping
	@ApiModelProperty("起始号码")
    private Integer numBegin;
	@Mapping
	@ApiModelProperty("结束号码")
    private Integer numEnd;
	@Mapping
	@ApiModelProperty("仓库id")
	private Long warehouseId;
	@Mapping
	@ApiModelProperty("城市编码")
	private String cityCode;
	@Mapping
	@ApiModelProperty("省份编码")
	private String provinceCode;
	@Mapping
	@ApiModelProperty("城市名称")
	private String cityName;
	@Mapping
	@ApiModelProperty("省份名称")
	private String provinceName;
	@Mapping
	@ApiModelProperty("区县名称")
    private String districtName;
	@Mapping
	@ApiModelProperty("街道(乡镇)名称")
    private String streetName;
	@Mapping
	@ApiModelProperty("创建人")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
}
