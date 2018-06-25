package com.hivescm.tms.api.enums.capacity.stock;


import com.hivescm.tms.api.enums.capacity.order.OrderTypeEnum;

/**
 * 仓位信息
 */
public enum StockPositionNameTypeEnum {

    STOCK_POSITION_NAME_TYPE1(1, "到货仓"),
    STOCK_POSITION_NAME_TYPE2(2, "发货仓");
    int code;
    String desc;

    StockPositionNameTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public static OrderTypeEnum getCode(int code){
        for (OrderTypeEnum ele : OrderTypeEnum.values()) {
            if (ele.getCode() == code)
                return ele;
        }
        return null;
    }
}
