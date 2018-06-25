package com.hivescm.tms.api.enums.biz.waybill.changedistribution;

public enum WaybillChangeDispatcherTypeEnum {
	RETURNWAREHOUSE    (1,"改配反仓"),
	RETURNBRANCH   (2,"改配反站点"),
	RETURNDISTRIBUTION  (3,"改配再配");
	
	int type;
	String name;
	
	WaybillChangeDispatcherTypeEnum(int type,String name) {
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
	
	public static WaybillChangeDispatcherTypeEnum getType(int type){
		for (WaybillChangeDispatcherTypeEnum ele : WaybillChangeDispatcherTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
