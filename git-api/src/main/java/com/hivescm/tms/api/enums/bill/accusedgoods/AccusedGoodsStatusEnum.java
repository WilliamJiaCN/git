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
public enum AccusedGoodsStatusEnum {
	ACCUSED(1, "已控货"), APPROVING(2, "审批中"), RELEASED(3, "已放货");

	int type;
	String name;

	AccusedGoodsStatusEnum(int type, String name) {
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
