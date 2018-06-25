package com.hivescm.tms.api.enums.waybillexception;

/**
 * 异常类型
 */
public enum ExceptionTypeEnum {

    BREAK(1,"破损"),
    LESSGOODS(2,"少货"),
    MOREGOODS(3,"多货"),
    OVERWEIGHT(4,"超重超方"),
    REFUSAL(5,"拒签"),
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
    private ExceptionTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static ExceptionTypeEnum getType(int type){
        for (ExceptionTypeEnum ele : ExceptionTypeEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
