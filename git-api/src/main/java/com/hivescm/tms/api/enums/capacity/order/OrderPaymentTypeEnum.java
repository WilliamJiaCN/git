package com.hivescm.tms.api.enums.capacity.order;


import java.util.ArrayList;
import java.util.List;

public enum OrderPaymentTypeEnum {

	/**
	 * code = 1
	 * 
	 * value =现付
	 * 
	 */
	CASH_PAYMENT(1,"现付"),
	
	/**
	 * code = 2
	 * 
	 * value = 货到付款
	 */
	DELIVERY_PAYMENT(2,"货到付款"),
	/**
	 * code = 3
	 * 
	 * value = 账期付
	 */
	ACCOUNT_PAYMENT(3,"账期付"),
	
	/**
	 * code = 4
	 * 
	 * value = 在线支付
	 */
	ONLINE_PAYMENT(4,"在线支付");

	
	private int code;
	
	private String value;
	
	private OrderPaymentTypeEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public static String getPaymentEnumName(int code){
		for(OrderPaymentTypeEnum op:OrderPaymentTypeEnum.values()){
			if(op.code == code){
				return op.value;
			}
		}
		return "";
	}

	
	public static List<String> getAllEnum(){
		List<String> enumList = new ArrayList<>();
		for(OrderPaymentTypeEnum op:OrderPaymentTypeEnum.values()){
			enumList.add(op.getValue());
		}
		return enumList;	
	}
	
	
	
	@Override
	public String toString() {
		return "code:"+this.code + ",name:" + this.value;
	}
}
