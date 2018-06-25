package com.hivescm.tms.api.enums.address.schedule;

/**
 * 区块类型枚举
 * 与数据字典对应
 *
 * @author qsk
 * @date 2017年9月8日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ScheduleBlockTypeEnum {
    FENCE(1, "电子围栏"),
    BLOCK(2, "区块管理");
    int type;
    String name;

    ScheduleBlockTypeEnum(int type, String name) {
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

