package com.hivescm.tms.api.enums.biz.bill;

import com.hivescm.tms.api.enums.biz.waybill.WaybillDiscardTypeEnum;

/**
 * 单据类型枚举 -> tms-billstatus 数据字典
 * @author ke.huang
 * @date 2017年9月26日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum BillDiscardTypeEnum {
	ERRORINFORMATION    (1,"信息错误"),
	BREAKAGE   (2,"封面破损"),
	FUZZYPRINT   (3,"打印模糊"),
	OTHERREASON   (4,"其他原因");
	
	int type;
	String name;
	
	BillDiscardTypeEnum(int type,String name) {
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
	
	public static BillDiscardTypeEnum getType(int type){
		for (BillDiscardTypeEnum ele : BillDiscardTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
