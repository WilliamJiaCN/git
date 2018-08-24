package com.hivescm.tms.api.dto.es.distribution.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 城配司机车辆Response DTO
 * @author lutingting
 * @date 2017年8月31日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverRespEsDTO implements Serializable{
	private static final long serialVersionUID = 5515857463193520270L;
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
	@Mapping
	@ApiModelProperty("公司名称")
    private String companyName;
	@Mapping
	@ApiModelProperty("用户ID")
	private Integer userId;
	
	/**
	 * driver
	 */
	@Mapping
	@ApiModelProperty("司机ID")
	private Integer driverId;
	@Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;
	@Mapping
	@ApiModelProperty("手机号码")
	private String phoneNo;
	
	@Mapping
	@ApiModelProperty("车辆ID")
    private Integer vehicleId;
	
}
