
package com.hivescm.tms.enums;
/**
 * ClassName: ContainerOperationStatusEnum.java
 * Function:  TODO  <br/>
 * Reason:     <br/>
 * Date:      2017年6月23日 上午10:15:38 <br/>
 * @author    anning
 * @version   
 * @since     JDK 1.8
 * @see       
 */

public enum DataStatusEnum {
	
	OCCUPY(0, "占用"),
	LEISURE(1, "空闲");

	int type;
	String name;
	
	DataStatusEnum(int type, String name){
		this.type = type;
		this.name = name();
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
		for (DataStatusEnum ele : DataStatusEnum.values()) {
			if (ele.getType() == type)
				return ele.getName();
		}
		return null;
	}

	public static DataStatusEnum getEnum(int type) {
		for (DataStatusEnum ele : DataStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return DataStatusEnum.OCCUPY;
	}
}
