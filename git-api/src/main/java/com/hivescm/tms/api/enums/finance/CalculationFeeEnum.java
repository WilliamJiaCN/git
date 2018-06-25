package com.hivescm.tms.api.enums.finance;

/**
 * 业务类型枚举
 * @author wangqianqian
 *
 */
public enum CalculationFeeEnum {
	YD(1,"运单","TMS202"),
	PCD(2,"派车单","TMS303"),
	FCPZD(3,"发车配载单","TMS701"),
	WFZZD(4,"外发中转单","TMS801"),
	ZHD(5,"装卸单","TMS405"),
	XDD(6,"卸货单","TMS406"),
	DHQRD(7,"到货确认单","TMS901"),
	DSHK(8,"代收货款"),
	OTHER(9,"其他收入"),
	YCHD(10,"异常单","TMS1001"),
	CASH(13,"现金转账"),
	DSHKHS(14,"代收货款回收"),
	DSHKHK(15,"代收货款汇款"),
	DSHKJZ(16,"代收货款进账"),
	DSHKFF(17,"代收货款发放"),
	DSHKSXF(18,"代收货款手续费"),
	SIGN(20,"签收"),
	CHANGESEND(21,"自提改配送"),
	CHANGEPCD(22,"派车单费项更改"),
	CHANGEDHQRD(23,"到货确认单费项更改"),
	EXPCLOSECLAIM(24,"异常单办结理赔"),
	EXPCLOSEFORFEIT(25,"异常单办结罚款"),
	EXPCLOSECLAIMFORFEIT(26,"异常单办结理赔并罚款"),
	;
	int type;
	String name;
	String docType;
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
	
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	private CalculationFeeEnum(int type, String name, String docType) {
		this.type = type;
		this.name = name;
		this.docType = docType;
	}
	private CalculationFeeEnum(int type, String name) {
		this.type = type;
		this.name = name;
	}

	public static CalculationFeeEnum getType(int type){
		for (CalculationFeeEnum ele : CalculationFeeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
