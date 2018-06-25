package com.hivescm.tms.api.enums.waybillexception;

/**
 * 异常原因
 */
public enum ExceptionReasonEnum {

    INSIDE(1,"内部"),
    CARRIER(2,"承运商"),
    CLIENT(3,"客户"),
    FORCE(4,"不可抗"),
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
    private ExceptionReasonEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static ExceptionReasonEnum getType(int type){
        for (ExceptionReasonEnum ele : ExceptionReasonEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
