package com.hivescm.tms.api.dto.es.global;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
/**
 * 经销商选取承运商线路信息
 * @author ke.huang
 * @date 2017年10月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GlobalDealerCarrierLineInfoEsDTO implements Serializable{
	private static final long serialVersionUID = 4564721924897468880L;
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
	@ApiModelProperty("市ID")
    private String cityId;
	@Mapping
	@ApiModelProperty("经销商ID")
	private Long dealerId;
	@Mapping
	@ApiModelProperty("承运商ID")
	private Long carrierId;
	@Mapping
	@ApiModelProperty("线路ID")
    private Long lineId;
	@Mapping
	@ApiModelProperty("比例")
    private BigDecimal portion;
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
	
	/****************************冗余字段**********************/
	@Mapping
	@ApiModelProperty("市名称")
	private String cityName;
	@Mapping
	@ApiModelProperty("经销商名称")
	private String dealerName;
	@Mapping
	@ApiModelProperty("承运商名称")
	private String carrierName;
	@Mapping
	@ApiModelProperty("线路名称")
	private String lineName;
	@Mapping
	@ApiModelProperty("创建人姓名")
	private String createUserName;
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;


}