package com.hivescm.tms.api.enums.finance;

/**
 * @Author sql
 * @Date 14:002018\5\18 0018
 */
public enum ResultEnum {

    SUCCESS(200,"success"),
    FAIL(500,"fail"),
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
    private ResultEnum(int type, String name) {
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
