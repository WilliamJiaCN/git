package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运单财务状态，对接BOSS结算状态
 * @author ke.huang
 * @date 2017年9月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum WaybillFinanceStatusEnum {
	SETTLED 	 (1,"已结算审核"),
	COMED_OUT    (2,"已出帐单"),
	REALIZED  	 (3,"已销帐");
	
	int type;
	String name;
	
	WaybillFinanceStatusEnum(int type,String name) {
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
	
	public static WaybillFinanceStatusEnum getType(int type){
		for (WaybillFinanceStatusEnum ele : WaybillFinanceStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
