package com.hivescm.tms.api.enums.base.exception;

/**
 * wms业务异常状态码定义范围 :3100~3199
 * 
 * @author yuancongcong
 *
 */
public enum WarehouseAreaErrorCodeEnum {

	BEAN_COPY_SOURCE_IS_NULL(3998,"传入原有类不能为空"),
	BEAN_COPY_TARGET_IS_NULL(3997,"目标类不能为空"),
	CURD(500, "数据库操作异常！"),
//	NOT_SAVE_STATUS(3999, "不是存盘状态不可以删除！"),
	
//	WAREHOUSE_NOT_EXISTS(3001, "仓库不存在"), // 以下对应仓库异常
	
	// 以下对应库区模块异常
	WAREHOUSEAREA_NOT_EXISTS(3101, "库区不存在"),
	WAREHOUSEAREA_CODE_EXISTS(3102, "库区编码已存在"), 
	WAREHOUSEAREA_NAME_EXISTS(3103, "库区名称存在"),
	WAREHOUSEAREA_NOT_ENABLE(3104, "库区没有启用"),
	GOODSPOSITION_NOT_DISABLE(3105, "库区下有启用状态的货位，不能停用库区！"),
	;
	
	private int errorCode;
	private String errorReason;

	public int getErrorCode() {
		return this.errorCode;
	}

	public String getErrorReason() {
		return this.errorReason;
	}

	private WarehouseAreaErrorCodeEnum(int errorCode, String errorReason) {
		this.errorCode = errorCode;
		this.errorReason = errorReason;
	}
}
