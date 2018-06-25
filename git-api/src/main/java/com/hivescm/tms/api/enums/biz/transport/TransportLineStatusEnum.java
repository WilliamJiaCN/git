package com.hivescm.tms.api.enums.biz.transport;

/**
 * 运输批次途经网点状态
 *
 * @author 李洪春
 * @since 2017/9/1 12:07
 */
public enum TransportLineStatusEnum {

	NOT_ARRIVED(1, "未到达"),//已启用
	
    VEHICLE_ARRIVED(2, "已到达"),//已启用

    DEPARTED(3, "已发车"),//已启用
    
    GOODS_UNLOAD(4, "已卸货"),//废弃

    GOODS_LOAD(5, "已配载"),//废弃

	DESTINATION_ARRIVED(6, "到达目的网点");//废弃
	

    int type;
    String name;

    TransportLineStatusEnum(int type, String name) {
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
