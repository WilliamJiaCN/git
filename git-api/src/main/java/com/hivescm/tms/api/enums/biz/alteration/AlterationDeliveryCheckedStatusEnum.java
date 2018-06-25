package com.hivescm.tms.api.enums.biz.alteration;

/**
 * 更改送货、自提审核状态枚举
 * @author zhenming.du
 * @date 2017年9月26日
 * @company 蜂网供应链
 */
public enum AlterationDeliveryCheckedStatusEnum {
	UN_CHECKED(0,"未审核"),
	CHECKING(1,"审核中"),
	CHECKED(2,"已审核"),
	REFUSE(-1,"已拒绝");
	
	int type;
	String name;
	AlterationDeliveryCheckedStatusEnum(int type,String name) {
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
	
	public static AlterationDeliveryCheckedStatusEnum getType(int type){
		for (AlterationDeliveryCheckedStatusEnum ele : AlterationDeliveryCheckedStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}