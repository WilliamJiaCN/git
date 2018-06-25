package com.hivescm.tms.api.dto.es.global;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 全局承运商顺序配置
 * @author ke.huang
 * @date 2017年10月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GlobalCarrierConfigEsDTO implements Serializable{
	private static final long serialVersionUID = -5675259838184468598L;
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
	@ApiModelProperty("顺序")
    private Integer sort;
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
	@ApiModelProperty("承运商名称")
	private String carrierName;
	@ApiModelProperty("创建人姓名")
	private String createUserName;
	@ApiModelProperty("修改人姓名")
	private String updateUserName;

}