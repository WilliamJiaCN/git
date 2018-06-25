package com.hivescm.tms.api.enums.biz.exception;

/**
 * 罚款分摊方式
 *
 * @author 李洪春
 * @since 2017/10/12 上午9:06
 */
public enum ForfeitShareTypeEnum {
    /**
     * 手工分摊
     */
    BY_MANUAL(1, "手工分摊"),

    /**
     * 按票数分摊
     */
    BY_WAYBILL(2, "按票数分摊");

    int type;
    String name;

    ForfeitShareTypeEnum(int type, String name) {
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
