package com.hivescm.tms.api.enums.finance;

public enum PayWayEnum {
	NOWPAY 	 (4,"现付",3),
	OWEPAY 	 (5,"欠付",8),
	MONTHPAY (6,"月结",1),
	BACKPAY (7,"回单付",4),
	TOPAY (8,"到付",2),
	PAYMENTDUCTION (9,"货款扣",5),
	COLLECTIONOFGOODS (10,"代收货款"),
	COLLECTIONOFGOODSTAX(11,"代收货款手续费"),
	CHANGESENF(12,"到付送货费"),
	OTHER(13,"其他")
	;

	int type;
	String name;
	int waybillType;

	PayWayEnum(int type,String name) {
		this.type = type;
		this.name = name;
	}
	
	PayWayEnum(int type,String name,int waybillType) {
		this.type = type;
		this.name = name;
		this.waybillType=waybillType;
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

	public int getWaybillType() {
		return waybillType;
	}

	public void setWaybillType(int waybillType) {
		this.waybillType = waybillType;
	}

	public static PayWayEnum getType(int type){
		for (PayWayEnum ele : PayWayEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

	public static PayWayEnum getTypeByWaybillType(int waybillType){
		for (PayWayEnum payWayEnum : PayWayEnum.values()) {
			if (payWayEnum.getWaybillType() == waybillType)
				return payWayEnum;
		}
		return null;
	}

}
