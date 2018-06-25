package com.hivescm.tms.api.enums.bill.receipt;

/**
 * 回单管理列表查询运单条件类型枚举
 * @author ke.huang
 * @date 2018年3月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ReceiptWaybillTypeEnum {
     WAYBILL_CODE,//运单号
     INVOICE_USER_NAME,//发货人
     INVOICE_USER_COMPANY_NAME,//发货人公司
     INVOICE_USER_PHONE,//发货人手机
     RECEIPT_USER_NAME,//收货人
     RECEIPT_USER_COMPANY_NAME,//收货人公司
     RECEIPT_USER_PHONE,//收货人手机
	 GRANT_BATCH_CODE;//发放批次
}

