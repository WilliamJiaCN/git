package com.hivescm.tms.api.enums.address.schedule;

/**
 * 线路号码类型枚举
 * 与数据字典对应
 * @author ke.huang
 * @date 2017年9月8日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ScheduleRouteNumTypeEnum {

	SINGLE (1,"单号覆盖"),
	DOUBLE (2,"双号覆盖"),
	WHOLE(3, "全部覆盖"),
	TOTALLY(4, "全路覆盖");
	int type;
	String name;
	
	ScheduleRouteNumTypeEnum (int type,String name){
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
	
	public static ScheduleRouteNumTypeEnum getType(int type){
		for (ScheduleRouteNumTypeEnum ele : ScheduleRouteNumTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
	
}

