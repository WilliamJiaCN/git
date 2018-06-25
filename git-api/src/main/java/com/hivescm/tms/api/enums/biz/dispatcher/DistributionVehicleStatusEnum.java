package com.hivescm.tms.api.enums.biz.dispatcher;
/**
 * 车辆状态枚举
 * @author ke.huang
 * @date 2017年8月11日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum DistributionVehicleStatusEnum {
	NO_LOAD (1,"空载"),
	HALF_LOAD (2,"半载"),
	FULL_LOAD (3,"满载");
	
	int type;
	String name;
	
	DistributionVehicleStatusEnum(int type,String name) {
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