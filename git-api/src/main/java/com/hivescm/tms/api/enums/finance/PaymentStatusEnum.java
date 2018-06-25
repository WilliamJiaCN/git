package com.hivescm.tms.api.enums.finance;

public enum PaymentStatusEnum {
	NO_CHECK(1,"未审核"),
	NO_PAYMENT(2,"未付款"),
	PARTLY_PAYMENT(3,"部分付款"),
	ALL_PAYMENT(4,"已付款");
	
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
	private PaymentStatusEnum(int type, String name) {
		this.type = type;
		this.name = name;
	}
	public static PaymentStatusEnum getType(int type){
		for (PaymentStatusEnum ele : PaymentStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
