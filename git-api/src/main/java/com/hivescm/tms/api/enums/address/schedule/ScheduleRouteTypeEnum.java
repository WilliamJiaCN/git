package com.hivescm.tms.api.enums.address.schedule;

/**
 * 线路类型枚举
 * 与数据字典对应
 *
 * @author qiushengkun
 * @date 2017年9月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ScheduleRouteTypeEnum {

    COMPANY(1, "公司线路"),
    DRIVER(2, "司机线路");
    int type;
    String name;

    ScheduleRouteTypeEnum(int type, String name) {
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
