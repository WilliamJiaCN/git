package com.hivescm.tms.api.dto.es.distribution.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 城配指派司机请求DTO
 * @author lutingting
 * @date 2017年8月9日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverRequestDTO implements Serializable{
	private static final long serialVersionUID = -2801125059760228371L;
	
//    @ApiModelProperty("id")
//    private Long id;
	 /**
     * 公司id
     */
    @ApiModelProperty("公司ID")
    private Long companyId;
	
	@ApiModelProperty("公司名称")
    private String companyName;

//	@ApiModelProperty("运单ID")
//    private Long waybillId;

	@ApiModelProperty("司机ID")
    private Integer driverId;

	@ApiModelProperty("司机姓名")
    private String driverName;
	
	@ApiModelProperty("司机手机号")
    private String driverTelNumber;
	
	@ApiModelProperty("车辆ID")
    private Integer vehicleId;

	@ApiModelProperty("车辆号码")
    private String vehicleName;

	

	

}
