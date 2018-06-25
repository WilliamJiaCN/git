package com.hivescm.tms.api.enums.capacity.ltlorder;

public enum AppOrderStatusEnum {

	CANVASSION(11,"待揽货"),
	SEND_TO_DOT(12,"送至网点"),
	HAS_BILLING(13,"已开单"),
	TRANSPORTATION(14,"运输中"),
	DISPATCH(15,"派送中"),
	SINCE(16,"自提"),
	CANCEL(17,"已取消"),
	SIGNALL(18,"全部签收"),
	SIGNPART(19,"部分签收"),
	NoSign(20,"未签收"),
	NOTSIGN(21,"全部拒签");

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
	private AppOrderStatusEnum(int type, String name) {
		this.type = type;
		this.name = name;
	}
	

    public static AppOrderStatusEnum getType(int type){
		for (AppOrderStatusEnum ele : AppOrderStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
