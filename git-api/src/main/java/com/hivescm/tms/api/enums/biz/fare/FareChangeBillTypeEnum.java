package com.hivescm.tms.api.enums.biz.fare;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/30
 */
public enum  FareChangeBillTypeEnum {

    DISPATCHER(2, "派车单"),
    TRANSPORT(3, "配载单"),
    ARRIVE(7, "到货单");

    private int type;

    private String name;

    FareChangeBillTypeEnum(int type, String name) {
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

