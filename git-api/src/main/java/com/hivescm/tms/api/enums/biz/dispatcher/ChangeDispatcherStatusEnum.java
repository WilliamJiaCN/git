package com.hivescm.tms.api.enums.biz.dispatcher;

/**
 * 派车类型
 * 1=提货、2=送货、3=提送
 *
 * @author 鲁婷婷
 * @since 2017/8/15 17:12
 */
public enum ChangeDispatcherStatusEnum {
    CHECKING(1, "改配审核中"),
    CHECKED(2, "改配审核通过"),
    UNCHECKED(3, "改配审核不通过");

    int type;
    String name;

    ChangeDispatcherStatusEnum(int type, String name) {
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
