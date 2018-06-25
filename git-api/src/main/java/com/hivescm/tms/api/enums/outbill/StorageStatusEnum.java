package com.hivescm.tms.api.enums.outbill;

/**
 * 外发单状态枚举
 * @author 	ZHANGWENLONG
 * @date 2018年03月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum StorageStatusEnum {
	
	NOTSTORAGE(1,"未入库"),
	STORAGED(2,"已入库");
	
	
	int type;
	String name;
	
	StorageStatusEnum(int type,String name) {
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
	
	public static StorageStatusEnum getType(int type){
		for (StorageStatusEnum ele : StorageStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
