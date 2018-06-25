package com.hivescm.tms.api.enums.biz.dispatcher;

public enum DistributionHistorySearchConditionsEnum {

	TODAY(1, "当天"),
    MONTH(2, "当月"),
    YEAR(3, "本年"),
    LASTMONTH(4, "上月"),
    WEEKDAY(5,"本周"),
    CUSTOM(6, "自定义");
	int type;
	String name;
	DistributionHistorySearchConditionsEnum(int type, String name) {
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
