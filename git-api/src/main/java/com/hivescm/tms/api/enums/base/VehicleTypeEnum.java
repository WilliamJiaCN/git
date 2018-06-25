/**
 * Project Name:wms-base-server
 * File Name:CommonStatusEnum.java
 * Package Name:com.hivescm.wms.server.base.domain.enums
 * Date:2017年6月22日上午11:30:07
 *
*/

package com.hivescm.tms.api.enums.base;
/**
 * ClassName:CommonStatusEnum <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	   通用状态枚举类. <br/>
 * Date:     2017年6月22日 上午11:30:07 <br/>
 * @author   lutingting
 * @version
 * @since    JDK 1.8
 * @see
 */
public enum VehicleTypeEnum {	
	SOCIAL(1, "社会车辆"),
	PUBLIC_CAR(2, "合同车辆"),
	SELF_CAR(3,"自有车辆");
	
	
	
	
	
	

	int type;
	String name;

	VehicleTypeEnum(int type, String name) {
		this.type = type;
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getName(int type) {
		for (VehicleTypeEnum ele : VehicleTypeEnum.values()) {
			if (ele.getType() == type)
				return ele.getName();
		}
		return null;
	}
/*	public static int getType(int type) {
		for (CommonStatusEnum ele : CommonStatusEnum.values()) {
			if (ele.getType() == type)
				return ele.getType();
		}
		return 1;
	}*/
	
	public static VehicleTypeEnum getEnum(int type) {
		for (VehicleTypeEnum ele : VehicleTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return VehicleTypeEnum.SOCIAL;
	}

}

