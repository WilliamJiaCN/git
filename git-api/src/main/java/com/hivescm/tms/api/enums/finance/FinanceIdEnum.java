package com.hivescm.tms.api.enums.finance;

/**
 * 资金流水type类型
 */
public enum  FinanceIdEnum {
    FINANCERECEIPT(1,"应收"),
    FINANCEPAY(2,"应付"),
    FIANCEGOODSRECYCLE(3,"货款回收"),
    FINANCEGOODSGRANT(4,"货款发放"),
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
    private FinanceIdEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static FinanceIdEnum getType(int type){
        for (FinanceIdEnum ele : FinanceIdEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
