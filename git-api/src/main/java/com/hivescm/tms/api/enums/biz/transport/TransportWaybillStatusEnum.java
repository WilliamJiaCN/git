package com.hivescm.tms.api.enums.biz.transport;

/**
 * 运输批次明细状态
 *
 * @author 李洪春
 * @since 2017/9/7 9:22
 */
public enum TransportWaybillStatusEnum {
	
	LOAD(0, "已装车"),

    NOT_ARRIVED(1, "已发车"),

    ARRIVED(2, "已到货");


    int type;
    String name;

    TransportWaybillStatusEnum(int type, String name) {
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
