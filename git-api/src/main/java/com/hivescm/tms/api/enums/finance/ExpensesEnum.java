package com.hivescm.tms.api.enums.finance;

public enum ExpensesEnum {
	HANDINGCHARGE(76,"装卸费",1,"126019488004214784"),
	FORKLIFTCHARGE(73,"叉车费",2,"126019686717775872"),
	OTHERFEE(70,"其他费",3,"126019763255406592"),
	CARFEE(77,"车费",4,"126018823580348416"),
	TERMINALCHARGE(71,"中转费",5,"126018731955769344"),
	LOADINGFEE(78,"装货费",6,"126019488004214784"),//和装卸费一致
	UNLOADINGFEE(79,"卸货费",7,"126019488004214784"),//和装卸费一致
	BUSINESS(69,"业务费",8,"126018650783375360"),
	DELIVERFEE(80,"到付送货费",9,"126018490749689856"),
	FERIGHTADVANCED(66,"垫付费",10,"126018578150629376"),
	COLLECTIONOFMONEY(67,"代收货款",11,"11"),//暂时boss那边没有，所以自定一下
	CLAIM(68,"理赔费",12,"138345454822948864"),
	FK(69,"罚款费",13,"138345141370044416"),
	COLLECTIONFEE(70,"代收货款手续费",14,"14"),
	TOTALFEE(100,"总运费",100,"126018411003432960"),
	;
	int type;
	String name;
	String sheetName;
	int feeType;
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
	private ExpensesEnum(int type, String name) {
		this.type = type;
		this.name = name;
	}
	
	private ExpensesEnum(int type, String name, int feeType) {
		this.type = type;
		this.name = name;
		this.feeType = feeType;
	}
	
	private ExpensesEnum(int type, String name, int feeType, String docType) {
		this.type = type;
		this.name = name;
		this.feeType = feeType;
		this.docType = docType;
	}
	public String getDocType() {
		return docType;
	}
	public void setDocType(String docType) {
		this.docType = docType;
	}
	public int getFeeType() {
		return feeType;
	}
	public void setFeeType(int feeType) {
		this.feeType = feeType;
	}
	public static ExpensesEnum getType(int type){
		for (ExpensesEnum ele : ExpensesEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
	public static ExpensesEnum getTypeByFeeType(int feetype){
		for (ExpensesEnum ele : ExpensesEnum.values()) {
			if (ele.getFeeType() == feetype)
				return ele;
		}
		return null;
	}
	public String getSheetName() {
		return sheetName;
	}
	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}
	private ExpensesEnum(String name, String sheetName, int feeType, String docType) {
		this.name = name;
		this.sheetName = sheetName;
		this.feeType = feeType;
		this.docType = docType;
	}
	private ExpensesEnum(int type, String name, String sheetName, int feeType, String docType) {
		this.type = type;
		this.name = name;
		this.sheetName = sheetName;
		this.feeType = feeType;
		this.docType = docType;
	}
	
}
