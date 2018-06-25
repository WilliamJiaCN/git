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
public enum VehicleModelsEnum {	
	TRICYCLE(1, "三轮车"),
	LARGE_CAR(2, "大型货车"),
	VAN_CAR(3,"面包车"),
	FLATBED_CAR(4,"平板车"),
	GAOLAN_VAN(5,"高栏面包车"),
	SMALL_VAN(6,"厢式小货车"),
	MEDIUM_VAN(7,"中型面包车"),
	SEVEN_METER_BOX(8,"7.6米厢式"),
	MIDDLE_BUS(9,"中巴车"),
	MINI_VAN(10,"微型面包车"),
	CENTER_CAR(11,"中卡"),
	HEAVEY_CAR(12,"重卡"),
	LARGE_FLAT_CAR(13,"大型平板车"),
	CARGO_BOX(14,"小厢货"),
	LIU_FACE(15,"柳面"),
	TAXI(16,"的士"),
	FIVE_METER_BOX(17,"5.7米厢式"),
	COMMERCE(21,"商务"),
	LVECO(24,"依维柯");

	
	
	
	

	int type;
	String name;

	VehicleModelsEnum(int type, String name) {
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
		for (VehicleModelsEnum ele : VehicleModelsEnum.values()) {
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
	
	public static VehicleModelsEnum getEnum(int type) {
		for (VehicleModelsEnum ele : VehicleModelsEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return VehicleModelsEnum.TRICYCLE;
	}

}

