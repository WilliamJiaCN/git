package com.hivescm.tms.api.enums.biz.dispatcher;

public enum DistributionOperateTypeEnum {
	DRIVER (1,"指派司机"),
	VEHICLE (2,"指派车队"),
	MAP(3,"地图指派"),
	SCHEDULE(4,"智能调度");
	int type;
	String name;
	
	DistributionOperateTypeEnum(int type,String name) {
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
}
