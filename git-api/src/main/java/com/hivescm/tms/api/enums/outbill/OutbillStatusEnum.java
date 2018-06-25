package com.hivescm.tms.api.enums.outbill;

/**
 * 外发单状态枚举
 * @author 	ZHANGWENLONG
 * @date 2018年03月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum OutbillStatusEnum {
	
	NOTCONFIRM(1,"未中转"),
	CONFIRMED(2,"已中转");
	
	
	int type;
	String name;
	
	OutbillStatusEnum(int type,String name) {
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
	
	public static OutbillStatusEnum getType(int type){
		for (OutbillStatusEnum ele : OutbillStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
