package com.hivescm.tms.api.enums.bill.receipt;

/**
 * 回单提取库存类型
 * @author ke.huang
 * @date 2018年3月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ReceiptPickStockTypeEnum {
	RECOVERY,//回收库存
	TRANSMIT,//寄出库存
	GRANT,//发放库存
	ALL // 不限制 
}

