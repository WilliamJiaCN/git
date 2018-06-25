package com.hivescm.tms.api.enums.capacity.ltlorder;

public enum CancelEnum {

	CLICK_WRONG(0,"点错了"),
	NOT_DELIVERY(1,"货不发了"),
	WRONG_ORDER(2,"下错单了"),
	NO_CANVASSION(3,"一直没有揽货"),
	OTHER(4,"其他原因");
	int type;
	String name;
	private CancelEnum(int type, String name) {
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
	

    public static CancelEnum getType(int type){
		for (CancelEnum ele : CancelEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
