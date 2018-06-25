package com.hivescm.tms.api.enums.bill.receipt;

/**
 * 回单货物异常状态
 * 1=无异常 2=未处理 3=已处理
 * @author ke.huang
 * @date 2018年3月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ReceiptExceptionStatusEnum {
    NOT_EXCEPTION(1, "无异常"),
    UNTREATED(2, "未处理"),
	PROCESSED(3, "已处理");
	
    int type;
    String name;

    ReceiptExceptionStatusEnum(int type, String name) {
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

