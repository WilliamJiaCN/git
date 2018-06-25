package com.hivescm.tms.api.enums.outbill;

/**
 * 外发中转结算方式
 * @author 	ZHANGWENLONG
 * @date 2018年03月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum OutbillTransitSettlementTypeEnum {
	
	IMMEDIATELYPAY(1,"现结"),
	OWEPAY(2,"欠款"),
	RETURNPAY(3,"回单结");
	
	
	int type;
	String name;
	
	OutbillTransitSettlementTypeEnum(int type,String name) {
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
	
	public static OutbillTransitSettlementTypeEnum getType(int type){
		for (OutbillTransitSettlementTypeEnum ele : OutbillTransitSettlementTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
