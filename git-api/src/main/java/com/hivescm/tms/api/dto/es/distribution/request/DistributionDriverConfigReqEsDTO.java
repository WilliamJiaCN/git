package com.hivescm.tms.api.dto.es.distribution.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 司机个人中心配置DTO
 * @author ke.huang
 * @date 2017年8月29日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverConfigReqEsDTO implements Serializable{
	private static final long serialVersionUID = -544350785132545057L;
	@Mapping
	@ApiModelProperty(value="公司ID",required=true)
    private Long companyId;
	@Mapping
	@ApiModelProperty(value="司机ID",required=true)
    private Integer driverId;
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
	@Mapping
	@ApiModelProperty("更新人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("更新人姓名")
	private String updateUserName;
	@Mapping
	@ApiModelProperty("更新时间")
    private Long updateTime;
}
