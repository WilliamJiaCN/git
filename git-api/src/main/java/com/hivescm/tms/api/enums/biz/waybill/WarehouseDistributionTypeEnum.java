package com.hivescm.tms.api.enums.biz.waybill;


/**
 * 运单仓配类型
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum WarehouseDistributionTypeEnum {
	UNITEDELIVERY(1,"统仓统配"),
	SELFDELIVERY(2,"统仓自配");
	int type;
	String name;
	WarehouseDistributionTypeEnum(int type,String name){
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
	
	public static WarehouseDistributionTypeEnum getType(int type){
		for (WarehouseDistributionTypeEnum ele : WarehouseDistributionTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
