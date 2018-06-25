package com.hivescm.tms.api.enums.biz.bill;

/**
 * 单据类型枚举 -> tms-billtype 数据字典
 * @author ke.huang
 * @date 2017年9月26日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum BillTypeEnum {
	WAYBILL 	  (1,"运单"),
	INVOICE       (2,"发票"),
	CHEQUE  	  (3,"支票");
	
	int type;
	String name;
	
	BillTypeEnum(int type,String name) {
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
