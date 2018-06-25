package com.hivescm.tms.api.dto.es.transport.component;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


/**
 *  RF 车辆信息
 * @author zxm
 *
 */
@Data
@ToString
public class RfTransportVehicleDTO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8810966850142946997L;
	

	private @ApiModelProperty("车辆id") Integer vehicleId;
	
	private @ApiModelProperty("车牌号") String vehicleNo;
	
	private @ApiModelProperty("司机id") Integer driverId;
	 
	 private @ApiModelProperty("司机姓名") String driverName;
	 
	 private @ApiModelProperty("司机手机号码") String driverPhoneNo;
	 
	 /**
	  * 车辆状态
	  */
	 private @ApiModelProperty("未发车=途经网点已到达=2，已发车=途经网点已发车=3") Integer vehicleStatus;
	 
	 private @ApiModelProperty("到车时间")Long vehicleArrivedTime;
	 
	 
	 private @ApiModelProperty("发车时间") Long departConfirmTime;
	 
	 private @ApiModelProperty("批次ID") Long transportId;
	
}
