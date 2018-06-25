/**
 * Project Name:wms-base-server
 * File Name:ReturnCodeEnum.java
 * Package Name:com.hivescm.wms.server.base.domain.enums
 * Date:2017年6月20日下午6:26:13
 *
*/

package com.hivescm.tms.enums;
/**
 * ClassName:ReturnCodeEnum <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 操作返回信息枚举. <br/>
 * Date:     2017年6月20日 下午6:26:13 <br/>
 * @author   zhangwenhao
 * @version
 * @since    JDK 1.8
 * @see
 */
public enum ReturnCodeEnum {

	SUCC(1, "操作成功！"),
	FAIL(0, "操作失败！");
	
	

	int type;
	String name;

	ReturnCodeEnum(int type, String name) {
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
		for (ReturnCodeEnum ele : ReturnCodeEnum.values()) {
			if (ele.getType() == type)
				return ele.getName();
		}
		return null;
	}

	public static ReturnCodeEnum getEnum(int type) {
		for (ReturnCodeEnum ele : ReturnCodeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return ReturnCodeEnum.FAIL;
	}

}

