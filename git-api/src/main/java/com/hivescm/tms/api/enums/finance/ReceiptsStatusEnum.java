package com.hivescm.tms.api.enums.finance;

/**
 * 进账类型
 */
public enum ReceiptsStatusEnum {

    UNRECEIPTS(1,"未进账"),
    RECEIPTSED(2,"已进账"),
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
    private ReceiptsStatusEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static ReceiptsStatusEnum getType(int type){
        for (ReceiptsStatusEnum ele : ReceiptsStatusEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
