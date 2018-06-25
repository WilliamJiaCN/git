package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 库存明细是否删除
 * 运单明细数量未完全出库  表示还未删除
 * 运单明细数量已完全出库  表示删除操作
 */
public enum WaybillStockAtoStatusEunm {

    DETAIL_STOCK_FLAG1 (1,"运单明细数量未完全出库"),
    DETAIL_STOCK_FLAG2 (2,"运单明细数量已完全出库"),

    STOCK_FLAG1(1,"库存运单未完全出库"),
    STOCK_FLAG2(2,"库存运单已全部出库"),


    STOCK_STATUS1(1,"发货仓"),
    STOCK_STATUS2(2,"到货仓");


    int type;
    String name;

    WaybillStockAtoStatusEunm(int type, String name) {
        this.type = type;
        this.name = name;
    }

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

    public static WaybillTrackStatusEnum getType(int type){
        for (WaybillTrackStatusEnum ele : WaybillTrackStatusEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
