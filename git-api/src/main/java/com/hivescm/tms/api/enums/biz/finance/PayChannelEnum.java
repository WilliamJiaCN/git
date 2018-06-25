package com.hivescm.tms.api.enums.biz.finance;

/**
 * @author 杨彭伟
 * @date 2017-11-29 14:53
 */
public enum PayChannelEnum {
    /**
     * 微信
     */
    WX(1, "微信"),
    /**
     * 支付宝
     */
    ALIPAY(2, "支付宝"),
    /**
     * 快钱
     */
    KUAI_QIAN(3, "快钱");

    int type;
    String name;

    PayChannelEnum(int type, String name) {
        this.name = name;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

}
