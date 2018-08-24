package com.hivescm.tms.api.dto.es.distribution.request;

import com.hivescm.tms.api.enums.biz.dispatcher.DistributionBillTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class DistributionReceiveBillReqDTO implements Serializable{
	private static final long serialVersionUID = -2801125059760228371L;
	@ApiModelProperty("单据ID")
    private  long id;
	
	@ApiModelProperty("运单ID")
    private  long waybillId;
	 /**
     * 公司id
     */
    @ApiModelProperty("公司id")
    private  Long companyId;
    
    
    @ApiModelProperty("司机id")
    private  Integer driverId;
    
    @ApiModelProperty("车辆Id")
    private  Integer vehicleId;
    
    
    
    
    @ApiModelProperty("单据类型")
    private DistributionBillTypeEnum billType;
}
