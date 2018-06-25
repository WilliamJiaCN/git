package com.hivescm.tms.api.enums.bill.alteration;

/**
 * 收款方类型枚举
 * @author ke.huang
 * @date 2018年5月17日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ReceiveTypeEnum {
    INVOICE_COMPANY(1, "发货公司"),
    INVOICE_USER(2, "发货人"),
	RECEIPT_COMPANY(3,"收货公司"),
	RECEIPT_USER(4,"收货人");
	
    int type;
    String name;

    ReceiveTypeEnum(int type, String name) {
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

