package com.hivescm.tms.api.enums.biz.sign;

public enum SignPaymentStatusEnum {
	//交易创建
	WAIT_BUYER_PAY(1, "WAIT_BUYER_PAY"),
	//交易中
	TRADE_ING(2, "TRADE_ING"),
	//成功
	TRADE_SUCCESS(3, "TRADE_SUCCESS"),
	//撤销中
	CANCEL_ING(4, "CANCEL_ING"),
	//交易撤销
	TRADE_CANCEL(5, "TRADE_CANCEL"),
	//交易失败
	TRADE_FAIL(6, "TRADE_FAIL"),
	//交易关闭
	TRADE_CLOSED(7, "TRADE_CLOSED");
    int type;
    String name;

    SignPaymentStatusEnum(int type, String name) {
        this.name = name;
        this.type = type;
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
