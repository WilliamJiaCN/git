package com.hivescm.tms.api.enums.finance;

public enum BackStatusEnum {
	
	UNBACK(0,"未发放"),
	BACK(1,"已发放"),
	;
	int type;
	String name;
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
	private BackStatusEnum(int type, String name) {
		this.type = type;
		this.name = name;
	}
	public static BackStatusEnum getType(int type){
		for (BackStatusEnum ele : BackStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
