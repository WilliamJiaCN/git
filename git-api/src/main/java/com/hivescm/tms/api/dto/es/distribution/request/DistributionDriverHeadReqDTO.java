package com.hivescm.tms.api.dto.es.distribution.request;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 司机头像设置请求实体
 * @author ke.huang
 * @date 2017年9月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverHeadReqDTO implements Serializable{
	private static final long serialVersionUID = -2592578245713461771L;
	@Mapping
	@ApiModelProperty(value="公司ID",required=true)
	private Long companyId;
	@Mapping
	@ApiModelProperty(value="司机ID",required=true)
	private Integer driverId;
	@Mapping
	@ApiModelProperty(value="司机头像地址",required=true)
	private String headPic;
	@Mapping
	@ApiModelProperty(value="修改人ID")
	private Integer updateUser;
	@Mapping
	@ApiModelProperty(value="修改人姓名")
	private String updateUserName;
	@Mapping
	@ApiModelProperty(value="修改时间")
	private Long updateTime;
	
	public Boolean isValid(){
		return null != this.companyId && null != this.driverId && null != this.headPic;
	}
}
