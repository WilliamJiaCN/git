package com.hivescm.tms.api.enums.finance;

/**
 * 收款状态
 */
public enum DeliveryStatusEnum {
	NO_CHECK(1,"未审核"),
	NO_DELIVERY(2,"未收银"),
	PARTLY_DELIVERY(3,"部分收银"),
	DELIVERY(4,"已收银");
	
	int type;
	String name;
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
	private DeliveryStatusEnum(int type, String name) {
		this.type = type;
		this.name = name;
	}
	public static DeliveryStatusEnum getType(int type){
		for (DeliveryStatusEnum ele : DeliveryStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
