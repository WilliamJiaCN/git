package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运单结算方式枚举
 * @author 
 * @date 2017年11月08日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum WaybillPayWayEnum {
	nowpay (1,"现付"),
	monthly (2,"月结"),
	backpay (3,"回单付"),
	topay (4,"到付"),
	morepay (5,"多笔付"),
	forfree (6,"免费"),
	paymentdeduction (7,"货款扣"),
	tardypay(8,"欠付");
	
	
	
	int type;
	String name;
	
	WaybillPayWayEnum(int type,String name) {
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
	
	public static WaybillPayWayEnum getType(int type){
		for (WaybillPayWayEnum ele : WaybillPayWayEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
