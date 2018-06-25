package com.hivescm.tms.api.enums.outbill;

/**
 * 入库单费用类型枚举
 * @author 	ZHANGWENLONG
 * @date 2018年03月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum StorageFeeTypeEnum {
	
	HANDINGCHARGE(1,"装卸费"),
	FORKLIFTTRUCKFEE(2,"叉车费"),
	OTHERFEE(3,"其他费用");
	
	
	int type;
	String name;
	
	StorageFeeTypeEnum(int type,String name) {
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
	
	public static StorageFeeTypeEnum getType(int type){
		for (StorageFeeTypeEnum ele : StorageFeeTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
