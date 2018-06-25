package com.hivescm.tms.api.enums.capacity.line;

public enum NumberTypeEnum {
	COVER_ALL(1,"全部覆盖"),
	EVEN_NUMBER_COVERAGE(2,"双号覆盖"),
	ODD_NUMBERS_COVERING(3,"单号覆盖");
	int type;
	String name;
	NumberTypeEnum(int type,String name){
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
