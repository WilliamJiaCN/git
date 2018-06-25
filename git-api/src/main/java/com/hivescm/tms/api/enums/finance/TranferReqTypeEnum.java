package com.hivescm.tms.api.enums.finance;

/**
 * @Author sql
 * @Date 20:522018\5\21 0021
 */
public enum TranferReqTypeEnum {

    SUBMITACCOUNT(1,"交账"),
    ALLOT(2,"调拨"),
    RECEIVABLE(3,"回款"),
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
    private TranferReqTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public static TranferReqTypeEnum getType(int type){
        for (TranferReqTypeEnum ele : TranferReqTypeEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
