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

public enum HandlingTypeEnum {

	SHIPMENT(1, "装货"), // 装货
	UNLOAD(2, "卸货"); // 卸货

	int type;
	String name;

	HandlingTypeEnum(int type, String name) {
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
