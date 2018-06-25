package com.hivescm.tms.api.enums.waybillexception;

/**
 * 异常对象（办结）
 */
public enum ExceptionObjectEnum {

    BRANCH(1,"网点"),
    VEHICLE(2,"车辆"),
    CARRIER(3,"承运商"),
    HANDLINGTEAM(4,"装卸队"),
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
    private ExceptionObjectEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static ExceptionObjectEnum getType(int type){
        for (ExceptionObjectEnum ele : ExceptionObjectEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
