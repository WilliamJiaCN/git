package com.hivescm.tms.api.enums.biz.transport;

/**
 * 发车类型
 *
 * @author 李洪春
 * @since 2017/8/31 10:48
 */
public enum DepartTypeEnum {

    FEEDER(1, "短途"),
    ARTERY(2, "干线");
    int type;
    String name;

    DepartTypeEnum(int type, String name) {
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
