package com.hivescm.tms.api.enums.biz.sign;

/**
 * 签收生命周期状态
 *
 * @author m5itao
 * @update 2017/12/4
 */
public enum SignLifeCycleStatusEnum {

    UNSIGNE(1, "待签收"),
    UNAUDIT(2, "待审核"),
    UNPAY(3, "待支付"),
    SIGNED(4, "已签收");
    int type;
    String name;

    SignLifeCycleStatusEnum(int type, String name) {
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
