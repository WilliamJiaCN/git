package com.hivescm.tms.api.enums.outbill;

/**
 * 外发单费用类型枚举
 * @author 	ZHANGWENLONG
 * @date 2018年03月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum OutbillFeeTypeEnum {
	
	TRANSITFREIGHT(1,"中转运费"),
	TRANSITTAKE(2,"中转提货费"),
	TRANSITSEND(3,"中转送货费"),
	TRANSITOTHER(4,"中转其他费用");
	
	
	int type;
	String name;
	
	OutbillFeeTypeEnum(int type,String name) {
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
	
	public static OutbillFeeTypeEnum getType(int type){
		for (OutbillFeeTypeEnum ele : OutbillFeeTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
