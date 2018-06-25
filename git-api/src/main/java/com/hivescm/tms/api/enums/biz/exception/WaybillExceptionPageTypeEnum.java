package com.hivescm.tms.api.enums.biz.exception;

/**
 * 异常件列表页签类型
 *
 * @author 李洪春
 * @since 2017/9/28 15:08
 */
public enum WaybillExceptionPageTypeEnum {

    /**
     * 全部页签
     */
    ALL(1, "全部"),

    /**
     * 未确认页签
     */
    UNCONFIRMED(2, "未确认"),

    /**
     * 已确认页签
     */
    CONFIRMED(3, "已确认");

    int type;
    String name;

    WaybillExceptionPageTypeEnum(int type, String name) {
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
