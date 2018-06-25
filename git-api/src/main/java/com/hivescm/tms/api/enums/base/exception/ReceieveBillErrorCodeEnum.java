
package com.hivescm.tms.api.enums.base.exception;
/**
 * ClassName: VehicleErrorCodeEnum.java
 * Function:  TODO  <br/>
 * Reason:    抢单错误码	2060-2065  <br/>
 * Date:      2017年6月28日 下午3:08:24 <br/>
 * @author    anning
 * @version   
 * @since     JDK 1.8
 * @see       
 */

public enum ReceieveBillErrorCodeEnum {
	
	FAILED_BILL(2060,"抢单失败！"),
	HAVED_RECEIVED(2061,"已被其他人抢单！"),
	SUCCESS_BILL(2062,"抢单成功！");
	
	private int errorCode;
	private String errorReason;
	
	private ReceieveBillErrorCodeEnum(int errorCode, String errorReason){
		this.errorCode = errorCode;
		this.errorReason = errorReason;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorReason() {
		return errorReason;
	}
	
	public static String getErrorCode(int errorCode){
		for(ReceieveBillErrorCodeEnum ele: ReceieveBillErrorCodeEnum.values()){
			if(ele.getErrorCode() == errorCode){
				return ele.getErrorReason();
			}
		}
		return null;
	}
	
	public static ReceieveBillErrorCodeEnum getEnum(int errorCode){
		for(ReceieveBillErrorCodeEnum ele: ReceieveBillErrorCodeEnum.values()){
			if(ele.getErrorCode() == errorCode){
				return ele;
			}
		}
		return ReceieveBillErrorCodeEnum.FAILED_BILL;
	}
}
