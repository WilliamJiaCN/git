package com.hivescm.tms.api.enums.finance;

/**
 * 资金流水转账状态
 */
public enum TransferAccountsStatusEnum {
    UNTRANSFER(1,"未转账"),
    TRANSFERED(2,"已转账"),
    RECEIPTED(3,"已进账"),
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
    private TransferAccountsStatusEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static TransferAccountsStatusEnum getType(int type){
        for (TransferAccountsStatusEnum ele : TransferAccountsStatusEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
