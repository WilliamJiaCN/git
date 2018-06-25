package com.hivescm.tms.api.enums.biz.transport;

/**
 * 运输批次状态信息
 *
 * @author 李洪春
 * @since 2017/8/31 11:04
 */
public enum TransportStatusEnum {

    NOT_DEPARTING(1, "未发车"),

    DEPARTED(2, "在途中"),

    VEHICLE_ARRIVED(3, "已到车"),

    GOODS_ARRIVED(4, "已到货");


    int type;
    String name;

    TransportStatusEnum(int type, String name) {
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
    
    public static TransportStatusEnum getType(int type){
		for (TransportStatusEnum ele : TransportStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
