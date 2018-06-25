package com.hivescm.tms.api.enums.waybillexception;

/**
 * 异常处理方式
 */
public enum ExceptionDealTypeEnum {

    CLAIM(1,"理赔"),
    FORFEIT(2,"罚款"),
    DAMAGE(3,"赔偿货损"),
    CLAIMFORFEIT(4,"理赔/罚款"),
    DONOTDEAL(5,"无需处理"),
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
    private ExceptionDealTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static ExceptionDealTypeEnum getType(int type){
        for (ExceptionDealTypeEnum ele : ExceptionDealTypeEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
