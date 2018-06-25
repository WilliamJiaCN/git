/**
 * 
 */
package com.hivescm.tms.api.enums.bill.accusedgoods;

/**
 * @author boqiang.deng
 * @date 2018年5月21日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
public enum ApprovalFlowEnum {

	HAVE(1, "有审批流"), NOHAVE(2, "没有审批流");

	int type;

	String name;

	ApprovalFlowEnum(int type, String name) {
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
