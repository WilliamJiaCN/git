package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运单跟踪状态枚举
 * @author qsk
 * @date 2017年11月20日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum WaybillTrackStatusEnum {

	HASBILLING (1,"已开单"), // >>> 物流公司接单初始状态
	HASGOODS(2, "揽货"), // >>> 司机APP装货确认
	ENROUTE(3, "派送"), // >> 仓库出库确认
	SIGNED(4, "签收"), // >> 客户签收
    ASSIGNED(5, "发车"), // >> 发车配载发车确认
    ARRIVED(6, "到达目的地"); // >> 发车配载到货确认

	int type;
	String name;

	WaybillTrackStatusEnum(int type, String name) {
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
	
	public static WaybillTrackStatusEnum getType(int type){
		for (WaybillTrackStatusEnum ele : WaybillTrackStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
