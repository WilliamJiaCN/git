package com.hivescm.tms.api.enums.biz.bill;

/**
 * 单据类型枚举 -> tms-billstatus 数据字典
 * @author ke.huang
 * @date 2017年9月26日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum BillStatusEnum {
	NOT_RECEIVED 	  (1,"未领用"),
	NOT_USED          (2,"未使用"),
	USED  	          (3,"已使用"),
	DISCARD 	      (4,"已作废"),
	CANCELED  	      (5,"已取消");
	
	int type;
	String name;
	
	BillStatusEnum(int type,String name) {
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

}
