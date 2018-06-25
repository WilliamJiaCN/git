package com.hivescm.tms.api.enums.biz.dispatcher;

/**
 * 派车业务
 * 1=城配、2=零担
 *
 * @author 鲁婷婷
 * @since 2018/4/17 14:12
 */
public enum DispatcherBusinessEnum {
	COUNTRY(1, "城配"),//不在库存
    CARLOAD(2, "零担");


    int type;
    String name;

    DispatcherBusinessEnum(int type, String name) {
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
