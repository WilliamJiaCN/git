package com.hivescm.tms.api.enums.biz.transport;

/**
 * 到货批次状态信息
 *
 * @author 张文龙
 * @since 2017/9/14 
 */
public enum TransportArriveInfoStatusEnum {

    NOT_CONFIRM(1, "未确认"),

    CONFIRMED(2, "已确认");

    int type;
    String name;

    TransportArriveInfoStatusEnum(int type, String name) {
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
