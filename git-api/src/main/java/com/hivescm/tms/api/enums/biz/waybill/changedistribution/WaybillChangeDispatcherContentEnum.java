package com.hivescm.tms.api.enums.biz.waybill.changedistribution;

public enum WaybillChangeDispatcherContentEnum {
	CHANGEDISTIME    (1,"改配送时间"),
	CHANGEDISTYPE   (2,"改配送方式");
	
	int type;
	String name;
	
	WaybillChangeDispatcherContentEnum(int type,String name) {
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
	
	public static WaybillChangeDispatcherContentEnum getType(int type){
		for (WaybillChangeDispatcherContentEnum ele : WaybillChangeDispatcherContentEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
