package com.hivescm.tms.api.enums.capacity.capacitytconfig;

/**
 * 承运商运力配置状态信息
 *
 * @author 张文龙
 * @since 
 */
public enum CarrierCapacityConfigStatusEnum {

	NOT_COMMITED(1, "未提交"),

	COMMITED(2, "已提交"),

    EXPIRED(3, "已过期");


    int type;
    String name;

    CarrierCapacityConfigStatusEnum(int type, String name) {
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
    
    public static CarrierCapacityConfigStatusEnum getType(int type){
		for (CarrierCapacityConfigStatusEnum ele : CarrierCapacityConfigStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
