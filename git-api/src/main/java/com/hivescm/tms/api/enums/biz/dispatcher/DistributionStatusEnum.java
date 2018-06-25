package com.hivescm.tms.api.enums.biz.dispatcher;

public enum DistributionStatusEnum {
	NO_RECEIVE (1,"未接单"), // >>> 初始状态为未接单
	IGNORE    (2,"已忽略"), // >> 操作忽略订单时
	EXPIRED    (3,"已失效"), // >> 半小时内未接单为已失效
	OVERTIME    (4,"已过期"), // >> 运单已被成功抢单，其他单子状态为已过期
	ACCEPTED    (5,"已接单"), // >> 成功抢到单子
    FAILED(6,"接单失败");
	
	int type;
	String name;
	
	DistributionStatusEnum(int type,String name) {
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
