package com.hivescm.tms.api.enums.biz.sitesearch;

/**
 * 综合查询多条件查询类型 -> tms-bcte 数据字典
 * @author ke.huang
 * @date 2017年9月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum SearchBillConditionTypeEnum {

	INVOICE_COMPANY        (1, "发货公司"),
	INVOICE_USER           (2, "发货人"),
	INVOICE_USER_TEL_NO    (3, "发货人电话"),
	INVOICE_MOBLE_NO       (4, "发货人手机号"),
	RECEIPT_COMPANY        (5, "收货公司"),
	RECEIPT_USER           (6, "收货人"),
	RECEIPT_USER_TEL_NO    (7, "收货人电话"),
	RECEIPT_MOBLE_NO       (8, "收货人手机号"),
	GOODS_NAME             (9,"货物名称"),
	PACKAGE_NUM            (10,"件数"),
	WEIGHT                 (11,"重量"),
	VOLUME                 (12,"体积");

	int type;
	String name;

	SearchBillConditionTypeEnum(int type, String name) {
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
