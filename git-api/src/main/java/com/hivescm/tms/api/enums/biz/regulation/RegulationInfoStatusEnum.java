package com.hivescm.tms.api.enums.biz.regulation;

/**
 * 调拨批次状态信息
 *
 * @author 张文龙
 * @since 
 */
public enum RegulationInfoStatusEnum {

	NOT_LEAVE_LIBRARY(1, "未出库"),

    LEAVE_LIBRARY(2, "已出库"),

    ARRIVED_LIBRARY(3, "已入库");


    int type;
    String name;

    RegulationInfoStatusEnum(int type, String name) {
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
    
    public static RegulationInfoStatusEnum getType(int type){
		for (RegulationInfoStatusEnum ele : RegulationInfoStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
