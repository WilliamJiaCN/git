package com.hivescm.tms.api.enums.biz.messsage;

/**
 * tms发送短信节点
 * @author boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/11
 */
public enum TmsSmsNodeEnum {

	CRAEAT_WAYBILL(1, "创建运单"), DEPARTURE_LOADING(2, "发车配载"), ARRIVAL_CONFIRMATION(3, "到达确认"), OUTGOING_CONFIRMATION(4,
			"外发确认"), OUTGOING_RECEIPT(5, "外发签收"), SELF_DELIVERY_SIGN(6, "自提签收"), DELIVERY_CAR(7,
					"送货派车"), DELIVERY_RECEIPT(8, "送货签收"), PAYMENT_BACK(9,
							"货款收回"), PAYMENT_GRANT(10, "贷款发放"), SIGN_RECEIPT(11, "回单签收"), RETURN_RECEIPT(12, "回单返厂");
	int type;
	String name;

	TmsSmsNodeEnum(int type, String name) {

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
