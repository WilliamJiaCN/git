package com.hivescm.tms.api.enums.biz.dispatcher;

import com.hivescm.tms.api.enums.biz.waybill.WaybillFeeTypeEnum;

/**
 * 派车单来源类型
 * @author lutingting
 *
 */
public enum DispatcherSourceTypeEnum {
	TMS (1,"tms"), // >>> 初始状态为未接单
	WMS    (2,"wms"); // >> 操作忽略订单时

	
	int type;
	String name;
	
	DispatcherSourceTypeEnum(int type,String name) {
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
	 public static DispatcherSourceTypeEnum getType(int type) {
	        for (DispatcherSourceTypeEnum ele : DispatcherSourceTypeEnum.values()) {
	            if (ele.getType() == type) {
	                return ele;
	            }
	        }
	        return null;
	    }
}
