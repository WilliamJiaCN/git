package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运单状态枚举
 * @author 
 * @date 
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum WaybillProdTypeEnum {
	ORDINARY (1,"普通");
	int type;
	String name;
	
	WaybillProdTypeEnum(int type,String name) {
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
	
	public static WaybillProdTypeEnum getType(int type){
		for (WaybillProdTypeEnum ele : WaybillProdTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
