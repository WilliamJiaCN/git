
package com.hivescm.tms.api.enums.base.exception;
/**
 * ClassName: WarehouseErrorCodeEnum.java
 * Function:  TODO  <br/>
 * Reason:    仓库错误码	2000-2009  <br/>
 * Date:      2017年6月28日 下午3:08:24 <br/>
 * @author    anning
 * @version   
 * @since     JDK 1.8
 * @see       
 */

public enum WarehouseErrorCodeEnum {
	
	WAREHOUSE_NOT_EXISTS(2000,"仓库信息不存在！");
	
	private int errorCode;
	private String errorReason;
	
	private WarehouseErrorCodeEnum(int errorCode, String errorReason){
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
		for(WarehouseErrorCodeEnum ele: WarehouseErrorCodeEnum.values()){
			if(ele.getErrorCode() == errorCode){
				return ele.getErrorReason();
			}
		}
		return null;
	}
	
	public static WarehouseErrorCodeEnum getEnum(int errorCode){
		for(WarehouseErrorCodeEnum ele: WarehouseErrorCodeEnum.values()){
			if(ele.getErrorCode() == errorCode){
				return ele;
			}
		}
		return WarehouseErrorCodeEnum.WAREHOUSE_NOT_EXISTS;
	}
}
