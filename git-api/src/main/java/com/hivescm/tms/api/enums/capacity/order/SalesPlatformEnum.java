package com.hivescm.tms.api.enums.capacity.order;


/**
 * 销售平台
 * @author li_xj
 *
 */
public enum SalesPlatformEnum {

//	1.自助售货机  2.微商小程序  3.供销平台  4.自有商城   5.合作伙伴商城
	
	 SUPPLY_PLATFORM(101,"供销平台"),

	 OWN_SHOPPIING(102,"自有商城  "),

	 PARTNERS_SHOPPING(103,"合作伙伴商城"),

	 SMALL_PROGARM(201,"微商小程序"),

	 SELF_HELP_MACHINE(301,"自助售货机"),

	 MICO_SHOPPING(601,"微百货"),

	 YOUDAO(701,"有赞平台");
	
	private int code;
	
	private String name;
	
	
	private SalesPlatformEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public static String getName(int code){
		for(SalesPlatformEnum os:SalesPlatformEnum.values()){
			if(os.code == code){
				return os.name;
			}
		}
		return "";
	}
	
	public static SalesPlatformEnum getType(int type){
		for (SalesPlatformEnum ele : SalesPlatformEnum.values()) {
			if (ele.getCode() == type)
				return ele;
		}
		return null;
	}
	
}
