package com.hivescm.tms.api.enums.biz.transport;

/**
 * 运输批次途经网点状态
 *
 * @author 李洪春
 * @since 2017/9/1 12:07
 */
public enum TransportLineOperateTypeEnum {

	LOAD_UNLOAD(1, "装卸"),
	
    LOAD(2, "装"),

    UNLOAD(3, "卸");
	

    int type;
    String name;

    TransportLineOperateTypeEnum(int type, String name) {
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
