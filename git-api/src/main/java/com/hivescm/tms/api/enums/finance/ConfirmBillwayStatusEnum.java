package com.hivescm.tms.api.enums.finance;

/**
 * 收单类型
 */
public enum ConfirmBillwayStatusEnum {

    UNRECEIVE(1,"未收单"),
    RECEIVED(2,"已收单"),
    ;

    int type;
    String name;
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
    private ConfirmBillwayStatusEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static ConfirmBillwayStatusEnum getType(int type){
        for (ConfirmBillwayStatusEnum ele : ConfirmBillwayStatusEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
