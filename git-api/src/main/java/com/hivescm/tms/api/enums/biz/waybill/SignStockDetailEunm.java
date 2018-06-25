package com.hivescm.tms.api.enums.biz.waybill;


public enum  SignStockDetailEunm {

    SIGNNEDPARTIALLY(1,"部分签收"),
    SIGNREFUSED(2,"拒签"),
    DELIVERYFAILED(3,"派送失败"),
    CANCELSIGN(4,"取消签收"),


    SIGNNEDPARTIALLYCANCEL(2,"部分签收返库取消签收");

    int type;
    String name;
    SignStockDetailEunm(int type,String name){
        this.name = name;
        this.type = type;
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

    public static WarehouseDistributionTypeEnum getType(int type){
        for (WarehouseDistributionTypeEnum ele : WarehouseDistributionTypeEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
