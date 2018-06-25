package com.hivescm.tms.api.enums.biz.dispatcher;

import com.hivescm.tms.api.enums.base.VehicleModelsEnum;

/**
 * 派车单费用类型
 * 1、派车费
 * 2、装卸费
 * 3、叉车费
 * 4、其他费用

 *
 * @author 李洪春
 * @since 2017/8/15 17:16
 */
public enum DispatcherFeeTypeEnum {
	DISPATCH(1, "派车费"),
    DISCHARGE(2, "装卸费"),
    FORKLIFT(3, "叉车费"),
    OTHER(4, "其他费用"),
    NOW_PAY_FARE(5, "现付车费"),
    NOW_PAY_FUEL(6, "现付油卡"),
   BACK_PAY_FARE(7, "回付车费"),
    BACk_PAY_FUEL(8, "回付油卡"),
    ARRIVE_PAY_FARE(9, "到付车费"),
    MONTHLY_PAY_FARE(10, "月结车费") ;

    int type;
    String name;

    DispatcherFeeTypeEnum(int type, String name) {
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
	public static String getName(int type) {
		for (VehicleModelsEnum ele : VehicleModelsEnum.values()) {
			if (ele.getType() == type)
				return ele.getName();
		}
		return null;
	}
}


















