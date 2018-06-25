package com.hivescm.tms.api.enums.biz.finance;

/**
 * 货款发放状态
 * @author 杨彭伟
 * @date 2017-11-24 20:09
 */
public enum GrantStatusEnum {
    /**
     * 未发放
     */
    NOT_GRANT(1, "未发放"),
    /**
     * 已发放
     */
    GRANTED(2, "已发放");

    int type;
    String name;

    GrantStatusEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

}