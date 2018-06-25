package com.hivescm.tms.api.dto.es.distribution.response;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 司机个人中心拼单设置响应DTO
 * @author ke.huang
 * @date 2017年8月29日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverConfigRespEsDTO implements Serializable{
	private static final long serialVersionUID = 6526365559268927426L;
	@Mapping
	@ApiModelProperty("是否接收整车")
	private Boolean receiveAlone; // >> 
	@Mapping
	@ApiModelProperty("是否接收零担拼车")
    private Boolean receiveSplit; // >> 
	@Mapping
	@ApiModelProperty("接收距离（KM）")
    private Integer receiveDistance; // >> 
	@Mapping
	@ApiModelProperty("目的地区域 逗号间隔 全区=-1")
    private String destId; // >> 
	@Mapping
	@ApiModelProperty("目的地区域名称 逗号间隔")
    private String destName; // >> 
	@Mapping
	@ApiModelProperty("待命区域 逗号间隔 全区=-1")
    private String standbyId; // >> 
	@Mapping
	@ApiModelProperty("待命区域名称 逗号间隔")
    private String standbyName; // >> 
}
