package com.hivescm.tms.api.enums.finance;

/**
 * 资金流水状态
 */
public enum FlowStatusEnum {

    NORMAL(1,"正常"),
    CANCELLATION(2,"作废"),
    CANCEL(3,"取消"),
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
    private FlowStatusEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static FlowStatusEnum getType(int type){
        for (FlowStatusEnum ele : FlowStatusEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
