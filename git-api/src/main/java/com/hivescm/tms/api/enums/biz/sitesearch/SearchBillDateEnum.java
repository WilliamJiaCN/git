package com.hivescm.tms.api.enums.biz.sitesearch;

/**
 * 综合查询时间类型枚举 -> 数据字典 tms-billdate
 * @author ke.huang
 * @date 2017年9月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum SearchBillDateEnum {

	CREATE_DATE(1, "录单日期"),
	ORDER_DATE(2, "下单日期");

	int type;
	String name;

	SearchBillDateEnum(int type, String name) {
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
