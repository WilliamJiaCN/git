package com.hivescm.tms.api.enums.biz.handlingorder;

/**
 * 派车类型
 * 1=提货、2=送货、3=提送
 *
 * @author 李洪春
 * @since 2017/8/15 17:12
 */
public enum HandlingorderTypeEnum {
	COUNTRY(1, "城配"),//不在库存
    CARLOAD(2, "零担");


    int type;
    String name;

    HandlingorderTypeEnum(int type, String name) {
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
    public static HandlingorderTypeEnum getType(int type) {
		for (HandlingorderTypeEnum ele : HandlingorderTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
