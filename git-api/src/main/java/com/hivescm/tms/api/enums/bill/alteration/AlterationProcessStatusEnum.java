package com.hivescm.tms.api.enums.bill.alteration;

/**
 * 运单更改处理状态
 * @author ke.huang
 * @date 2018年5月17日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum AlterationProcessStatusEnum {
    NOT_CONFIRM(1, "未确认"),
    CONFIRM(2, "已确认");
	
    int type;
    String name;

    AlterationProcessStatusEnum(int type, String name) {
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

