package com.hivescm.tms.api.dto.es.global;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 全局承运商线路比例
 * @author ke.huang
 * @date 2017年10月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GlobalLineScaleConfigEsDTO implements Serializable{
	private static final long serialVersionUID = 2371618979722865923L;
	@Mapping
	@ApiModelProperty("主键")
    private Long id;
	@Mapping
	@ApiModelProperty("城市ID")
	private String cityId;
	@Mapping
	@ApiModelProperty("承运商ID")
    private Long carrierId;
	@Mapping
	@ApiModelProperty("线路ID")
    private Integer lineId;
	@Mapping
	@ApiModelProperty("比例")
    private BigDecimal scale;
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
	
	
	/************************************冗余字段**********************************************/
	@ApiModelProperty("城市名称")
	private String cityName;
	@ApiModelProperty("仓库名称")
	private String warehouseName;
	@ApiModelProperty("仓库ID")
	private Long warehouseId;
	@ApiModelProperty("承运商名称")
	private String carrierName;
	@ApiModelProperty("线路名称")
	private String lineName;
	@ApiModelProperty("创建人姓名")
	private String createUserName;
	@ApiModelProperty("修改人姓名")
	private String updateUserName;

}