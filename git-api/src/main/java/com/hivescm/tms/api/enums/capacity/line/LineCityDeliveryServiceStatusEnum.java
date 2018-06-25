package com.hivescm.tms.api.enums.capacity.line;
/**
 * 城市配送服务状态
 * @author Administrator
 *
 */
public enum LineCityDeliveryServiceStatusEnum {
	ENABLE(1,"启用"),
	DISABLE(2,"停用");
	int type;
	String name;
	LineCityDeliveryServiceStatusEnum(int type,String name){
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
