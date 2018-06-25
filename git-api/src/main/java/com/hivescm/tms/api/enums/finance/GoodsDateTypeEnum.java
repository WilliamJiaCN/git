package com.hivescm.tms.api.enums.finance;

/**
 * 日期筛选
 */
public enum GoodsDateTypeEnum {
    RECORDTIME(1,"录单日期"),
    SIGNEDTIME(2,"签收日期"),
    RECEIPTTIME(3,"回收日期"),
    REMITTANCETIME(4,"汇款日期"),
    FRANTTIME(5,"发放日期"),
    ARRIVEDAMOUNTTIME(1,"到账日期"),
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
    private GoodsDateTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static GoodsDateTypeEnum getType(int type){
        for (GoodsDateTypeEnum ele : GoodsDateTypeEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
