package com.hivescm.tms.api.enums.biz.waybill;

public enum WaybillCheckStatusEnum {
	UN_CHECKED  (1,"未审核"),
	CHECKED (2,"已审核");
	
	int type;
	String name;
	
	WaybillCheckStatusEnum(int type,String name) {
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
