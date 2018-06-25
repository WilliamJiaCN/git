package com.hivescm.tms.api.dto.es.bill.request;

public enum BillRequestReceivedEnum {

    APPLY_BRAND(1, "申请网点"),
    EMPLOY_BRAND(2, "使用网点");

    int type;
    String name;

    BillRequestReceivedEnum(int type, String name) {
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
