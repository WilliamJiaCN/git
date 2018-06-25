package com.hivescm.tms.api.enums.biz.controlgoods;

/**
 * 控货管理审核状态枚举
 * @author zhenming.du
 * @date 2017年9月26日
 * @company 蜂网供应链
 */
public enum ControlGoodsCheckedStatusEnum {
	FINISH(1,"完成"),//无流程时默认完成
	UN_CHECKED(1,"未审核"),
	CHECKED(2,"已审核");
	
	int type;
	String name;
	ControlGoodsCheckedStatusEnum(int type,String name) {
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
	
	public static ControlGoodsCheckedStatusEnum getType(int type){
		for (ControlGoodsCheckedStatusEnum ele : ControlGoodsCheckedStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}