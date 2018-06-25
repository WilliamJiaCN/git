/**
 * 
 */
package com.hivescm.tms.api.enums.bill.accusedgoods;

/**
 * @author boqiang.deng
 * @date 2018年5月17日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
public enum AccusedGoodsOperateTypeEnum {

	ACCUSED(1, "控货"), RELEASED(2, "放货");

	int type;
	String name;

	AccusedGoodsOperateTypeEnum(int type, String name) {
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

}
