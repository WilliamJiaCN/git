package com.hivescm.tms.api.dto.db.base;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author 
@Talbe(name="vehicle_contact_driver")
 */
@Data
@ToString
public class VehicleContactDriverDTO implements Serializable {
	/**
	 * ID
	 * 
	 * @Id
	 * @GeneratedValue
	 * @GeneratedValue(strategy = GenerationType.IDENTITY)
	 */
	private Integer id;

	/**
	 * 车牌号ID
	 */
	@Mapping
	@ApiModelProperty("车牌号ID")
	private Integer vehicleId;

	/**
	 * 司机档案ID
	 */
	@Mapping
	@ApiModelProperty("司机档案ID")
	private Integer driverId;
	/**
	 * 车牌号是否默认
	 */
	@Mapping
	@ApiModelProperty("车牌是否默认")
	private Boolean defaults;
	/**
	 * 
	 * @NotEmpty
	 */
	@Mapping
	@ApiModelProperty(hidden = true)
	private Integer createUser;
	/**
	 * 
	 * @NotEmpty
	 */
	@Mapping
	@ApiModelProperty(hidden = true)
	private Long createTime;
	/**
	 * 
	 * @NotEmpty
	 */
	@Mapping
	@ApiModelProperty(hidden = true)
	private Integer updateUser;
	/**
	 * 
	 * @NotEmpty
	 */
	@Mapping
	@ApiModelProperty(hidden = true)
	private Long updateTime;

	@Mapping
	private static final long serialVersionUID = 1L;


   
}