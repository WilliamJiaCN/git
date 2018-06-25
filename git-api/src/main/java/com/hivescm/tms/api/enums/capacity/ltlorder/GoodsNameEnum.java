package com.hivescm.tms.api.enums.capacity.ltlorder;

public enum GoodsNameEnum {
	
	COMMONDITY(0,"日用品"),
	PARTS(1,"配件"),
	ELECTRONIC_PRODUCT(2,"电子产品"),
	HOUSE_ELE_PRO(3,"家电"),
	FOOD_DRINK(4,"食品饮料"),
	FURNITURE(5,"家具"),
	DRUGS(6,"药品"),
	MECHAINICAL(7,"机械设备"),
	WRAPPER(8,"包装材料"),
	BUILDING(9,"建材"),
	CHEMICAL_MATERIAL(10,"化工原料"),
	OTHER(11,"其他");
	int type;
	String name;
	
	private GoodsNameEnum(int type, String name) {
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

    public static GoodsNameEnum getType(int type){
		for (GoodsNameEnum ele : GoodsNameEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
	
}
