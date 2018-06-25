package com.hivescm.tms.api.enums.biz.dispatcher;

/**
 * 派车类型
 * 1=提货、2=送货、3=提送
 *
 * @author 李洪春
 * @since 2017/8/15 17:12
 */
public enum DispatcherTypeEnum {
    PICKING(1, "提货"),
    DELIVERY(2, "送货"),
    BOTH(3, "提送");


    int type;
    String name;

    DispatcherTypeEnum(int type, String name) {
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
    public static DispatcherTypeEnum getType(int type) {
		for (DispatcherTypeEnum ele : DispatcherTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
