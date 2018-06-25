package com.hivescm.tms.enums;

/**
 * 自定义查询码
 * @author ke.huang
 * @date 2017年12月26日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum LogCustomCodeEnum {
	
	DEFAULT(0,"默认查询码"),
	ORDER_LIST_SEARCH(1000,"订单列表查询");
	
	private LogCustomCodeEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	int code;
	String name;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static String getName(int type) {
		for (LogCustomCodeEnum ele : LogCustomCodeEnum.values()) {
			if (ele.getCode() == type)
				return ele.getName();
		}
		return null;
	}

	public static LogCustomCodeEnum getEnum(int code) {
		for (LogCustomCodeEnum ele : LogCustomCodeEnum.values()) {
			if (ele.getCode() == code)
				return ele;
		}
		return LogCustomCodeEnum.DEFAULT;
	}
	
	
}
