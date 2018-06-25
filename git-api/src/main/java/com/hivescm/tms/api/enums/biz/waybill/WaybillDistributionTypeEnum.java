package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运单配送方式枚举
 * @author zhenming.du
 * @date 2017年10月9日
 * @company 蜂网供应链
 */
public enum WaybillDistributionTypeEnum {
	DELIVERY (1,"送货"), 
	GET		 (2,"自提");
	int type;
	String name;
	
	WaybillDistributionTypeEnum(int type,String name) {
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
	
	public static WaybillDistributionTypeEnum getType(int type){
		for (WaybillDistributionTypeEnum ele : WaybillDistributionTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
