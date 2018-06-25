package com.hivescm.tms.api.enums.outbill;

/**
 * 外发单状态枚举
 * @author 	ZHANGWENLONG
 * @date 2018年03月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum OutSignStatusEnum {
	
	NOTSIGN(1,"未签收"),
	ALLSIGN(2,"全部签收"),
	PARTSIGN(3,"部分签收"),
	REFUSESIGN(4,"拒收");
	
	int type;
	String name;
	
	OutSignStatusEnum(int type,String name) {
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
	
	public static OutSignStatusEnum getType(int type){
		for (OutSignStatusEnum ele : OutSignStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
