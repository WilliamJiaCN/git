package com.hivescm.tms.api.enums.biz.fare;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/30
 */
public enum  FareInfoStatusEnum {

    NOT_CONFIRM(1,"未审核"),
    NOT_PAY(2, "未付款"),
    PART_PAID(3, "部分付款"),
    PAID(4, "已付款");

    private int type;

    private String name;

    FareInfoStatusEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }


    public int getType() {
        return type;
    }


    public String getName() {
        return name;
    }

}
