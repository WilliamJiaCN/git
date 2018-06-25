package com.hivescm.tms.api.enums.biz.fare;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/24
 */
public enum FareChangeStatusEnum {


    NO_CONFIRM(1,"未确认"),
    AGREED(2,"已同意"),
    DISAGREED(3,"已否决");


    FareChangeStatusEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    private int type;
    private String name;

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
