package com.hivescm.tms.api.enums.biz.handlingorder;

/**
 * <p>
 * Title: HandlingTypeEnum
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Email: syenging@gmail.com
 * </p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-05-19-16:21
 */

public enum HandlingSourceEnum {

	DISPATCHER(1, "派车单"), // 来源批次类型
	TRANSPORT(2, "发车配载"); // 来源批次类型

	int type;
	String name;

	HandlingSourceEnum(int type, String name) {
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

	public static HandlingorderTypeEnum getType(int type) {
		for (HandlingorderTypeEnum ele : HandlingorderTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
