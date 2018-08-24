package com.hivescm.tms.api.dto.es.dispatcher.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author  lhf
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/11/9
*/
@Data
@ToString
public class VehicleDriverRespDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 车辆ID
	 */
	@Mapping
	@ApiModelProperty("车辆ID")
	private Integer vehicleId;

	/**
	 * 司机ID
	 *
	 * @{link com.hivescm.tms.api.dto.base.BaseDriverDTO#id}
	 */
	@Mapping
	@ApiModelProperty("司机ID")
	private Integer driverId;
}

