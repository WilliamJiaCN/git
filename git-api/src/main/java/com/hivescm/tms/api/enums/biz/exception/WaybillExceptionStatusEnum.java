package com.hivescm.tms.api.enums.biz.exception;

/**
 * 异常件状态
 *
 * @author 李洪春
 * @since 2017/9/28 14:23
 */
public enum WaybillExceptionStatusEnum {
    /**
     * 无审批流程初始状态
     */
    UNCONFIRMED(1, "未确认"),

    /**
     * 有审批流程初始状态
     */
    UNCOMMITTED(2, "未提交"),

    /**
     * 有审批流程 流程开始后状态
     */
    IN_APPROVAL(3, "审批中"),
    /**
     * 完结
     */
    CONFIRMED(4, "已确认");

    int type;
    String name;

    WaybillExceptionStatusEnum(int type, String name) {
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
