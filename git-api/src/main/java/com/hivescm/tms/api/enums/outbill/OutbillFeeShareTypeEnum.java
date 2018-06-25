package com.hivescm.tms.api.enums.outbill;

/**
 * 外发单费用分摊方式枚举
 * @author 	ZHANGWENLONG
 * @date 2018年03月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum OutbillFeeShareTypeEnum {
	
	MANUALSHARE(1,"手工分摊"),
	BILLINGWEIGHTSHARE(2,"按计费重量分摊"),
	PACKAGENUMSHARE(3,"按件数分摊"),
	VOLUMESHARE(4,"按体积分摊"),
	WEIGHTSHARE(5,"按重量分摊"),
	ACCOUNTSHARE(6,"按票数分摊"),
	PRODUCTFEESHARE(7,"按产值分摊");
	
	
	int type;
	String name;
	
	OutbillFeeShareTypeEnum(int type,String name) {
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
	
	public static OutbillFeeShareTypeEnum getType(int type){
		for (OutbillFeeShareTypeEnum ele : OutbillFeeShareTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
