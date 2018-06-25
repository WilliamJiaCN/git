package com.hivescm.tms.api.dto.es.distribution.request;
import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 司机状态DTO
 * @author ke.huang
 * @date 2017年8月11日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverStatusReqEsDTO implements Serializable {
	private static final long serialVersionUID = 4169729285135683482L;
	private @Mapping @ApiModelProperty(value="公司ID",required=true) Long companyId;
	private @Mapping @ApiModelProperty("司机ID") Integer driverId;
	private @Mapping @ApiModelProperty("是否上班") Boolean isWork;
	private @Mapping @ApiModelProperty("修改人") Integer updateUser;
    private @Mapping @ApiModelProperty("修改人姓名") String updateUserName;
    private @Mapping @ApiModelProperty("修改时间") Long updateTime;
    
    public Boolean isValid(){
    	return null != this.companyId && null != this.driverId && null != this.isWork;
    }
	
}