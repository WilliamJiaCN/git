package com.hivescm.tms.api.enums.capacity.line;

public enum CoverTypeEnum {
	COVER_ALL(1,"全部覆盖"),
	PARTIAL_COVERAGE(2,"部分覆盖");
	int type;
	String name;
	CoverTypeEnum(int type,String name){
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
