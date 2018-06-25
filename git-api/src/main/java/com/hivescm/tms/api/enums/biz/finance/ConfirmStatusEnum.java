package com.hivescm.tms.api.enums.biz.finance;

/**
 * 是否确认收银
 * @author 杨彭伟
 * @date 2017-11-24 15:57
 */
public enum ConfirmStatusEnum {
    /**
     * 未确认
     */
    UNCONFIRM(1,"未确认"),
    /**
     * 已确认
     */
    CONFIRMED(2,"已确认");

    int type;
    String name;

    ConfirmStatusEnum(int type, String name) {
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
