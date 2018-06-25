package com.hivescm.tms.api.enums.capacity.global;

public enum LogisticsOrganizationEnum {
	SELF(1,"自有"),
	EXTERNAL(2,"外部");
	int type;
	String name;
	LogisticsOrganizationEnum(int type,String name){
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
