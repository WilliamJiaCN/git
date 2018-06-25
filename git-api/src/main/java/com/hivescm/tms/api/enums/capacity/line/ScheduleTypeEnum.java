package com.hivescm.tms.api.enums.capacity.line;

public enum ScheduleTypeEnum {
	DAILY_ALLOCATION(1,"每日配"),
	ISOLATION_ALLOCATION(2,"隔日配"),
	USER_DEFINED(3,"自定义");
	int type;
	String name;
	ScheduleTypeEnum(int type,String name){
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
