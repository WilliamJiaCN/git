
package com.hivescm.tms.api.enums.base.exception;
/**
 * ClassName: VehicleErrorCodeEnum.java
 * Function:  TODO  <br/>
 * Reason:    车辆错误码	2040-2049  <br/>
 * Date:      2017年6月28日 下午3:08:24 <br/>
 * @author    anning
 * @version   
 * @since     JDK 1.8
 * @see       
 */

public enum VehicleErrorCodeEnum {
	
	CONTAINER_NOT_EXISTS(2040,"车辆信息不存在！"),
	CONTAINER_EXISTS(2041,"已存在的车牌号码，请重新核对！");
	
	private int errorCode;
	private String errorReason;
	
	private VehicleErrorCodeEnum(int errorCode, String errorReason){
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
		for(VehicleErrorCodeEnum ele: VehicleErrorCodeEnum.values()){
			if(ele.getErrorCode() == errorCode){
				return ele.getErrorReason();
			}
		}
		return null;
	}
	
	public static VehicleErrorCodeEnum getEnum(int errorCode){
		for(VehicleErrorCodeEnum ele: VehicleErrorCodeEnum.values()){
			if(ele.getErrorCode() == errorCode){
				return ele;
			}
		}
		return VehicleErrorCodeEnum.CONTAINER_NOT_EXISTS;
	}
}
