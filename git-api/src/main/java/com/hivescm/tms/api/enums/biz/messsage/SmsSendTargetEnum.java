package com.hivescm.tms.api.enums.biz.messsage;

/**
 * @author boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/11
 */
public enum SmsSendTargetEnum {

	INVOICE(1, "发货方"), Receipt(2, "收货方");

	int type;
	String name;

	SmsSendTargetEnum(int type, String name) {
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
