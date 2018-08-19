package com.hivescm.tms.api.dto.es.distribution.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 城配指派司机请求DTO
 * @author lutingting
 * @date 2017年8月9日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverGroupRequestDTO implements Serializable{
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

	
	
	@ApiModelProperty("车队ID")
    private Integer fleetId;

	@ApiModelProperty("车队名称")
    private String fleetName;
	
	

	

}
