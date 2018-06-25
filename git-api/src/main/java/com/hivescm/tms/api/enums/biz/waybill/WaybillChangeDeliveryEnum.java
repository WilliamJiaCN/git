package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运单改配
 * @author lifan
 *
 * 2018年3月2日
 *
 */
public enum WaybillChangeDeliveryEnum {
	
	CHANGED    (1,"已改配"), 
	NOTCHANGED    (2,"未改配"); 
	
	int type;
	String name;
	
	WaybillChangeDeliveryEnum(int type,String name) {
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
	
	public static WaybillChangeDeliveryEnum getType(int type){
		for (WaybillChangeDeliveryEnum ele : WaybillChangeDeliveryEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
