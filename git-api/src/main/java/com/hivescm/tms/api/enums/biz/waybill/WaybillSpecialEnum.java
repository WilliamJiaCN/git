package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运单状态枚举
 * @author ke.huang
 * @date 2017年7月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum WaybillSpecialEnum {
	BREAK 	 (9,"拆件"),
	MODIFIER (10,"修改"),
	WAIT  	 (11,"等通知"),
	VIP 	 (12,"VIP"),
	EXCEPTION (13,"是否为异常单"),
	IEMERGANCY (14,"是否加急");

	
	int type;
	String name;
	
	WaybillSpecialEnum(int type,String name) {
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

}
