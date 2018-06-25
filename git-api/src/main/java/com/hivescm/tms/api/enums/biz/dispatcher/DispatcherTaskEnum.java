package com.hivescm.tms.api.enums.biz.dispatcher;

/**
 * 派车类型
 * 1=提货、2=送货、3=提送
 *
 * @author 李洪春
 * @since 2017/8/15 17:12
 */
public enum DispatcherTaskEnum {
	PICKSEND(1, "提货送货"),//不在库存
    PICKINBAND(2, "提货入库"),//不在库存
    OUTBAND(3, "出库送货");//在库存


    int type;
    String name;

    DispatcherTaskEnum(int type, String name) {
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
