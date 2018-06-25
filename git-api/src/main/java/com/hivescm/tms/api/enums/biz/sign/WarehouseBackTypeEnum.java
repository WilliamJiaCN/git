package com.hivescm.tms.api.enums.biz.sign;

public enum WarehouseBackTypeEnum {

	NO_SIGNED     (1,"已签收"),
	PARTIAL_SIGN(2,"部分签收"),
	REFUSE_SIGN(3,"拒绝签收");
	int type;
	String name;
	
	WarehouseBackTypeEnum(int type,String name) {
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
