package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运输方式枚举
 * @author 
 * @date 2017年11月08日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum WaybillShippingTypeEnum {
	motorWay 	 (1,"普运"),
	fastWay 	 (2,"快运"),
	skyWay 	 (3,"空运"),
	seaWay 	 (4,"海运"),
	railWay 	 (5,"铁运");
	
	
	int type;
	String name;
	
	WaybillShippingTypeEnum(int type,String name) {
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
