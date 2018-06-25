package com.hivescm.tms.api.enums.biz.waybill.changedistribution;

public enum ResponsibleObjectEnum {
	DEALER   (1,"经销商"),
	CARRIER    (2,"承运商"),
	MERCHANT  (3,"商户"),
	STORE  (4,"门店"),
	WAREHOUSE  (5,"仓库");
	
	int type;
	String name;
	
	ResponsibleObjectEnum(int type,String name) {
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
	
	public static ResponsibleObjectEnum getType(int type){
		for (ResponsibleObjectEnum ele : ResponsibleObjectEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
