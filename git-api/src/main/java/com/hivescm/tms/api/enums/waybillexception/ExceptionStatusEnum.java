package com.hivescm.tms.api.enums.waybillexception;

/**
 * 异常状态
 */
public enum ExceptionStatusEnum {

    UNSUBMIT(1,"未提交"),
    UNPROCESS(2,"未处理"),
    PROCESSING(3,"处理中"),
    CLOSED(4,"已办结"),
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
    private ExceptionStatusEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static ExceptionStatusEnum getType(int type){
        for (ExceptionStatusEnum ele : ExceptionStatusEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
