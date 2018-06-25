package com.hivescm.tms.api.dto.db.pre;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2017年11月20日 上午10:11:48
* 
*/
@Data
@ToString
public class ScmWarehouseAreaDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("省国标码")
	private String provinceCode;
	@ApiModelProperty("省名称")
	private String provinceName;
	@ApiModelProperty("市国标码")
	private String cityCode;
    // 区域名称
	@ApiModelProperty("市名称")
    private String cityName;
	@ApiModelProperty("区国标码")
	private String distinctCode;
	@ApiModelProperty("区名称")
	private String distinctName;
	@ApiModelProperty("街道区国标码")
	private String streetCode;
	@ApiModelProperty("街道名称")
	private String streetName;
    // 区域级别
    private Integer areaLevel;
    // 上级行政区域id
    private String parentId;
    // 覆盖区域类型：1：全部 0：部分
    private Integer overAreaFlag;
}
