package com.hivescm.tms.api.enums.capacity.line;

public enum ServiceTypeEnum {
	CARRIER(1,"承运商"),
	LINE(2,"线路");
	int type;
	String name;
	ServiceTypeEnum(int type,String name){
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
