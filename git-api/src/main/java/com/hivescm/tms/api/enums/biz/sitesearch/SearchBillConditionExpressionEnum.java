package com.hivescm.tms.api.enums.biz.sitesearch;

/**
 * 综合查询多条件查询表达式 -> 
 * tms-bcee 数据字典(字符型)
 * tms-bcex 数据字典(数字型)
 * @author ke.huang
 * @date 2017年9月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum SearchBillConditionExpressionEnum {

	CONTAINS                (1, "包含"), 
	NOT_CONTAINS            (2,"不包含"),
	EQUALS                  (3, "等于"), //tms-bcex
	NOT_EQUALS              (4,"不等于"), //tms-bcex
	GREATER_THAN            (5,"大于"), //tms-bcex
	LESS_THAN               (6,"小于"), //tms-bcex
	GREATER_THAN_EQUALS     (7,"大于等于"), //tms-bcex
	LESS_THAN_OR_EQUALS     (8,"小于等于"), //tms-bcex
	NULL                    (9,"空值"),
	NOT_NULL                (10,"非空值");

	int type;
	String name;

	SearchBillConditionExpressionEnum(int type, String name) {
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
