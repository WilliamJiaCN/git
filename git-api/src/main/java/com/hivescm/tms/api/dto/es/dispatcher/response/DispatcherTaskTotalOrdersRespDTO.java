package com.hivescm.tms.api.dto.es.dispatcher.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author  lhf
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/11/27
*/
@Data
@ToString
public class DispatcherTaskTotalOrdersRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("公司id")
	private Long companyId;
	@Mapping
	@ApiModelProperty("司机id")
	private Integer driverId;
    @Mapping
    @ApiModelProperty("接单总数")
    private int totalOrderCounts;
    @Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;
    @Mapping
	@ApiModelProperty("司机手机号码")
	private String phoneNo;
    @Mapping
    @ApiModelProperty("车牌号码")
	private String vehicleNo;
	
}

