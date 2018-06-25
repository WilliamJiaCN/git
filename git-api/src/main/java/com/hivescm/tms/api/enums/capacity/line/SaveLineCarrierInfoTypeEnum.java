package com.hivescm.tms.api.enums.capacity.line;

public enum SaveLineCarrierInfoTypeEnum {
	ADD(1,"新增"),
	UPDATE(2,"更新"),
	DELETE(3,"删除");
	int type;
	String name;
	SaveLineCarrierInfoTypeEnum(int type,String name){
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
