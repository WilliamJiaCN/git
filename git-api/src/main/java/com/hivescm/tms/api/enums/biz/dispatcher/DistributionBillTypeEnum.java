package com.hivescm.tms.api.enums.biz.dispatcher;

public enum DistributionBillTypeEnum {
	ASSIGN_BILL (1,"指派单"),
	PLATFORM_BILL (2,"平台单"),
	INTELLIGENCE_BILL(3,"智能调度单");
	int type;
	String name;
	
	DistributionBillTypeEnum(int type,String name) {
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
