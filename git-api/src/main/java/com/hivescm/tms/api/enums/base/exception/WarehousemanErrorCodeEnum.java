/**
 * Project Name:wms-common
 * File Name:WarehousemanErrorCodeEnum.java
 * Package Name:com.hivescm.wms.enums.base.exception
 * Date:2017年6月30日上午9:55:48
 *
*/

package com.hivescm.tms.api.enums.base.exception;

/**
 * ClassName:WarehousemanErrorCodeEnum <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 异常提示信息枚举. <br/>
 * Date:     2017年6月30日 上午9:55:48 <br/>
 * @author   zhangwenhao
 * @version
 * @since    JDK 1.8
 * @see
 */
public enum WarehousemanErrorCodeEnum {

	CURD(2000, "系统错误"),
	NOTSAVESTATUS(2001, "此条记录不是存盘状态不能删除");

	int type;
	String name;

	WarehousemanErrorCodeEnum(int type, String name) {
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

	public static String getName(int type) {
		for (WarehousemanErrorCodeEnum ele : WarehousemanErrorCodeEnum.values()) {
			if (ele.getType() == type)
				return ele.getName();
		}
		return null;
	}

	public static WarehousemanErrorCodeEnum getEnum(int type) {
		for (WarehousemanErrorCodeEnum ele : WarehousemanErrorCodeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return WarehousemanErrorCodeEnum.CURD;
	}



}

