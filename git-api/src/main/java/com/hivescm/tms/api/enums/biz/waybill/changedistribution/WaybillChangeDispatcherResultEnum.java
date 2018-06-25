package com.hivescm.tms.api.enums.biz.waybill.changedistribution;

public enum WaybillChangeDispatcherResultEnum {
	SUCCESS    (1,"审核通过"),
	FAILED   (2,"审核未通过");
	
	int type;
	String name;
	
	WaybillChangeDispatcherResultEnum(int type,String name) {
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
	
	public static WaybillChangeDispatcherResultEnum getType(int type){
		for (WaybillChangeDispatcherResultEnum ele : WaybillChangeDispatcherResultEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
