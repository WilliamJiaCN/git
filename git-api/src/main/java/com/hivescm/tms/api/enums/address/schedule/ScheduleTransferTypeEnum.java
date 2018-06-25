package com.hivescm.tms.api.enums.address.schedule;

/**
 * 转移类型枚举
 * 与数据字典对应
 *
 * @author qiushengkun
 * @date 2017年9月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ScheduleTransferTypeEnum {
    TRANSFER(1, "转移"),
    TRANSFERALL(2, "全部转移"),
    COPY(3, "复制"),
    COPYALL(4, "全部复制");
    int type;
    String name;

    ScheduleTransferTypeEnum(int type, String name) {
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
