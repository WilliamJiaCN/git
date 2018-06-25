package com.hivescm.tms.api.enums.bill.receipt;

/**
 * 回单库存列表查询时间筛选类型枚举
 * @author ke.huang
 * @date 2018年3月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ReceiptStockQueryTimeTypeEnum {
    RECEIPT(1, "回收日期"),
    CREATE(2, "录单日期"),
	SIGNED(3, "签收日期");
	
    int type;
    String name;

    ReceiptStockQueryTimeTypeEnum(int type, String name) {
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

}

