package com.hivescm.tms.api.enums.capacity.capacity;


/**
 * 运力分配匹配顺序规则
 * @author ke.huang
 * @date 2017年10月26日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum CapacityRegularEnum {
	PRICE(1,"价格优先"),
	TIMER(2,"时效优先"),
	CREDIT(3,"信誉优先"),
	RANGE(4,"距离优先");
	int type;
	String name;
	CapacityRegularEnum(int type,String name){
		this.name = name;
		this.type = type;
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
	
	public static CapacityRegularEnum getType(int type){
		for (CapacityRegularEnum ele : CapacityRegularEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
