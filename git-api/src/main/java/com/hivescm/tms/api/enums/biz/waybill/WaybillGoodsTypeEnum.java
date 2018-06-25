package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运单状态枚举
 * @author 
 * @date 
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum WaybillGoodsTypeEnum {
	/**
	 * 当重量(kg)>0, 体积(m³)=0 时，货物类型为“重货”；
     当重量(kg)=0, 体积(m³)>0 时，货物类型为“轻货”。
	 */
	LightGoods (1,"轻货"), // >>> 【轻货】 区间：200≤重量(kg)/体积(m³)<280；
	PureLightGoods (2,"轻泡货"), // >>> 【纯轻货】 区间：0<重量(kg)/体积(m³)<200；
	HeavyGoods    (3,"重货"), // >> 【重货】 区间：340≤重量(kg)/体积(m³)<1000；
	LittleHeavyGoods (4,"重泡货"), // >>> 【轻货】 区间：280≤重量(kg)/体积(m³)<340；
	PureHeavyGoods     (5,"纯重货"); // >> 【纯重货】 区间：1000≤重量(kg)/体积(m³)<以上
	int type;
	String name;
	
	WaybillGoodsTypeEnum(int type,String name) {
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
	
	public static WaybillGoodsTypeEnum getType(int type){
		for (WaybillGoodsTypeEnum ele : WaybillGoodsTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
