package com.hivescm.tms.api.enums.biz.handlingorder;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/28
 */
public enum HandingOrderLoadOperationTypeEnum {


    LOADED(1, "已装车"),
    CANCEL_LOAD(2, "取消装车");


    HandingOrderLoadOperationTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }

    private int type;

    private String name;

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
