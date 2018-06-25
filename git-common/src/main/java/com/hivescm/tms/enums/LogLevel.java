package com.hivescm.tms.enums;

/**
 * 注解式日志级别<br/>
 * INFO > DEBUG > WARN > ERROR > FATAL
 * @author ke.huang
 * @date 2017年12月26日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum LogLevel {
	INFO("info级别日志，记录程序运行过程"),
	DEBUG("debug级别日志，调试程序"),
	WARN("warn级别日志，潜在异常调试"),
	ERROR("eror级别日志，SystemException日志"),
	FATAL("Exception以上运行时日志，不包含SystemException");
	
	private LogLevel(String name) {
		this.desc = name;
	}
	
	String desc;
	
	public String getDesc() {
		return desc;
	}
	public void setName(String name) {
		this.desc = name;
	}
	
	public static LogLevel getEnum(String code) {
		for (LogLevel ele : LogLevel.values()) {
			if (ele.name().equals(code))
				return ele;
		}
		return null;
	}
}
