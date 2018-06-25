package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运单状态枚举
 * @author ke.huang
 * @date 2017年7月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ChangeWaybillStatusEnum {
	NOT_CONFIRM (0,"未确认"), // >>> 物流公司接单初始状态
	CONFIRM    (1,"已确认"); // >> OMS订单初始状态

	int type;
	String name;
	
	ChangeWaybillStatusEnum(int type,String name) {
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
	
	public static ChangeWaybillStatusEnum getType(int type){
		for (ChangeWaybillStatusEnum ele : ChangeWaybillStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
