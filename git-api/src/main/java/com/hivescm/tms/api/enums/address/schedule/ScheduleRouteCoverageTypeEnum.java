package com.hivescm.tms.api.enums.address.schedule;
/**
 * 线路覆盖类型枚举
 * 与数据字典对应
 * @author ke.huang
 * @date 2017年9月8日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ScheduleRouteCoverageTypeEnum {

	WHOLE (1,"整区覆盖"),
	PART (2,"部分覆盖");
	int type;
	String name;
	
	ScheduleRouteCoverageTypeEnum (int type,String name){
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

