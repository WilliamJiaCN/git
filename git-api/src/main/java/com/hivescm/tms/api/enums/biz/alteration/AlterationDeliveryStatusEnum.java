package com.hivescm.tms.api.enums.biz.alteration;

/**
 * 更改送货、自提状态枚举
 * @author zhenming.du
 * @date 2017年9月26日
 * @company 蜂网供应链
 */
public enum AlterationDeliveryStatusEnum {
	UN_SUBMIT(0,"未提交"),
	UN_CONFIRMED(1,"未确认"),
	CONFIRMED(2,"已确认");
	
	
	int type;
	String name;
	AlterationDeliveryStatusEnum(int type,String name) {
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
	
	public static AlterationDeliveryStatusEnum getType(int type){
		for (AlterationDeliveryStatusEnum ele : AlterationDeliveryStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}