/**
 * Project Name:wms-base-server
 * File Name:CommonStatusEnum.java
 * Package Name:com.hivescm.wms.server.base.domain.enums
 * Date:2017年6月22日上午11:30:07
 *
*/

package com.hivescm.tms.enums;
/**
 * ClassName:CommonStatusEnum <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	   通用状态枚举类. <br/>
 * Date:     2017年6月22日 上午11:30:07 <br/>
 * @author   zhangwenhao
 * @version
 * @since    JDK 1.8
 * @see
 */
public enum CommonStatusEnum {

	SAVE(0, "存盘"),
	ON(1, "启用"),
	OFF(2,"停用");

	int type;
	String name;

	CommonStatusEnum(int type, String name) {
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
		for (CommonStatusEnum ele : CommonStatusEnum.values()) {
			if (ele.getType() == type)
				return ele.getName();
		}
		return null;
	}
/*	public static int getType(int type) {
		for (CommonStatusEnum ele : CommonStatusEnum.values()) {
			if (ele.getType() == type)
				return ele.getType();
		}
		return 1;
	}*/
	
	public static CommonStatusEnum getEnum(int type) {
		for (CommonStatusEnum ele : CommonStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return CommonStatusEnum.ON;
	}

}

