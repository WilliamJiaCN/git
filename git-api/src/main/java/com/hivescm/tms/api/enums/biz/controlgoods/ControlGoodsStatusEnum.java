package com.hivescm.tms.api.enums.biz.controlgoods;

/**
 * 控货管理状态枚举
 * @author zhenming.du
 * @date 2017年9月26日
 * @company 蜂网供应链
 */
public enum ControlGoodsStatusEnum {
	NORELEASE(1,"未放货"),//运单等通知状态为是，控货管理中状态为未放货
	NOCONTROL(2,"未控货"),
	ALREADY(3,"已放货");//运单等通知状态被修改为否时，控货管理中状态为放货
	
	int type;
	String name;
	ControlGoodsStatusEnum(int type,String name) {
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
	
	public static ControlGoodsStatusEnum getType(int type){
		for (ControlGoodsStatusEnum ele : ControlGoodsStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}