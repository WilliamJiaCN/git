package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运单发货方式枚举
 * @author 
 * @date 2017年11月07日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum WaybillDeliveryTypeEnum {
	homeDelivery 	 (1,"上门提货");
	
	
	int type;
	String name;
	
	WaybillDeliveryTypeEnum(int type,String name) {
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
