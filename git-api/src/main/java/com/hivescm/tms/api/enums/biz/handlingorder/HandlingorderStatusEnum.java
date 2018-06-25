package com.hivescm.tms.api.enums.biz.handlingorder;

/**
 * 派车单状态 1=已指派 2=已接单 3=已装货 4=未发车 5=已发车
 *
 * @author 李洪春
 * @since 2017/8/14 19:24
 */
public enum HandlingorderStatusEnum {

	UN_CHECK(1, "未审核"),

	CHECKED(2, "已审核"),

	LOADED(3, "已装车");


	int type;
	String name;

	HandlingorderStatusEnum(int type, String name) {
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

	public static HandlingorderStatusEnum getType(int type) {
		for (HandlingorderStatusEnum ele : HandlingorderStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
