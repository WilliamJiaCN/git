package com.hivescm.tms.api.dto.es.distribution.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 司机线路启用状态请求DTO
 * @author ke.huang
 * @date 2017年9月11日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverUsedReqDTO implements Serializable{
	private static final long serialVersionUID = 8100562565560766521L;
	@Logger
	@ApiModelProperty(value="公司ID",required=true)
	private Long companyId;
	@ApiModelProperty(value="启用状态",required=true)
	private Boolean used;
	@ApiModelProperty("修改人")
	private Integer updateUser;
	@ApiModelProperty("修改人姓名")
	private String updateUserName;
	@ApiModelProperty(value="司机ID",required=true)
	private List<Integer> driverIds;
	
	public Boolean isValid(){
		return null != this.companyId && null != this.used && null != this.driverIds && this.driverIds.size() != 0;
	}
}
