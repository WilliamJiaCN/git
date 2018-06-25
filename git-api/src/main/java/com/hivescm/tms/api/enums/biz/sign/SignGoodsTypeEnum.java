package com.hivescm.tms.api.enums.biz.sign;

/**
 * 签收商品分类
 * @author 杨彭伟
 * @date 2017-11-18 13:50
 */
public enum SignGoodsTypeEnum {
    REFUSE_SIGN(1, "拒收"),
    SIGN(2, "签收");

    int type;
    String name;

    SignGoodsTypeEnum(int type, String name) {
        this.name = name;
        this.type = type;
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
