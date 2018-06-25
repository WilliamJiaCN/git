package com.hivescm.tms.api.enums.outbill;

/**
 * 外发单状态枚举
 * @author 	ZHANGWENLONG
 * @date 2018年03月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum StorageTypeEnum {
	
	OUTBILLTOSTOCK(1,"外发入库"),
	RETURNTOSTOCK(2,"返回入库");
	
	int type;
	String name;
	
	StorageTypeEnum(int type,String name) {
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
	
	public static StorageTypeEnum getType(int type){
		for (StorageTypeEnum ele : StorageTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
