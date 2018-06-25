package com.hivescm.tms.api.enums.capacity.line;
/**
 * 城市配送服务类型
 * @author Administrator
 *
 */
public enum LineCityDeliveryServiceTypeEnum {
	
	STANDARD_SERVICE(1,"标准服务"),
	ADVANCED_SERVICES(2,"高级服务");
	int type;
	String name;
	LineCityDeliveryServiceTypeEnum(int type,String name){
		this.name = name;
		this.type = type;
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
	
}
