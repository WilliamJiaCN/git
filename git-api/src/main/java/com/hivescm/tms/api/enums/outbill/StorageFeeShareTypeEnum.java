package com.hivescm.tms.api.enums.outbill;

/**
 * 入库单费用分摊方式枚举
 * @author 	ZHANGWENLONG
 * @date 2018年03月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum StorageFeeShareTypeEnum {
	
	BILLINGWEIGHTSHARE(1,"按计费重量分摊"),
	PACKAGENUMSHARE(2,"按件数分摊"),
	VOLUMESHARE(3,"按体积分摊"),
	WEIGHTSHARE(4,"按重量分摊"),
	ACCOUNTSHARE(5,"按票数分摊");
	
	
	int type;
	String name;
	
	StorageFeeShareTypeEnum(int type,String name) {
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
	
	public static StorageFeeShareTypeEnum getType(int type){
		for (StorageFeeShareTypeEnum ele : StorageFeeShareTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
