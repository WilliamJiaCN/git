package com.hivescm.tms.api.enums.finance;

/**
 * 汇款状态
 */
public enum  RemittanceStatusEnum {

    UNREMITTANCE(1,"未汇款"),
    REMITTANCEED(2,"已汇款"),
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
    private RemittanceStatusEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static RemittanceStatusEnum getType(int type){
        for (RemittanceStatusEnum ele : RemittanceStatusEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
