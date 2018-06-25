package com.hivescm.tms.api.enums.biz.waybill.changedistribution;

public enum WaybillChangeDispatcherReasonEnum {
	MERCHANTREASON    (10,"司机按时送达商户却无法收货"),
	DIRVERREASON    (20,"司机配送迟到"),
	OTHERREASON   (99,"其它原因");
	
	int type;
	String name;
	
	WaybillChangeDispatcherReasonEnum(int type,String name) {
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
	
	public static WaybillChangeDispatcherReasonEnum getType(int type){
		for (WaybillChangeDispatcherReasonEnum ele : WaybillChangeDispatcherReasonEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
