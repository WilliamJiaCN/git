package com.hivescm.tms.api.enums.biz.controlgoods;

/**
 * 控货管理操作类型枚举类
 * @author zhenming.du
 * @date 2017年9月26日
 * @company 蜂网供应链
 */
public enum ControlGoodsOperationTypeEnum {
	ALREADY(1,"放货"),//控货状态为已放货
	NORELEASE(2,"控货");//控货状态为已控货
	
	int type;
	String name;
	ControlGoodsOperationTypeEnum(int type,String name) {
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
	
	public static ControlGoodsOperationTypeEnum getType(int type){
		for (ControlGoodsOperationTypeEnum ele : ControlGoodsOperationTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}