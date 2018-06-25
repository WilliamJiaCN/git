package com.hivescm.tms.api.enums.finance;

/**
 * 单据类型
 */
public enum CodeTypeEnum {
	YD(1,"运单"),
	DSHK(8,"代收货款"),
	OTHER(9,"其他收入"),
	YCHD(10,"异常单"),
	CASH(13,"现金转账");

	int type;
	String name;
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
	private CodeTypeEnum(int type, String name) {
		this.type = type;
		this.name = name;
	}
	public static CodeTypeEnum getType(int type){
		for (CodeTypeEnum ele : CodeTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
