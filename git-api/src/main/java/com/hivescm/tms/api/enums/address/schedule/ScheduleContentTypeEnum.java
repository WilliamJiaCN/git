package com.hivescm.tms.api.enums.address.schedule;

/**
 * 转移复制类型枚举
 * 与数据字典对应
 *
 * @author qiushengkun
 * @date 2017年9月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ScheduleContentTypeEnum {

    KEYWORD(1, "关键词"),
    REGION(2, "区域"),//线路
    BLOCK(3, "电子围栏");
    int type;
    String name;

    ScheduleContentTypeEnum(int type, String name) {
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
