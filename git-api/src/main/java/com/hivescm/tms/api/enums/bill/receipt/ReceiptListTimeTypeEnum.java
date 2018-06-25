package com.hivescm.tms.api.enums.bill.receipt;

/**
 * 回单管理列表查询时间类型枚举
 * @author ke.huang
 * @date 2018年3月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ReceiptListTimeTypeEnum {
    CREATE_TIME,//录单日期
    SIGNED_TIME,//签收日期
    RECOVERY_TIME,//回收日期
    EXPRESS_TIME,//寄出日期
    RECEIVE_TIME,//接收日期
    GRANT_TIME;//发放日期
	
}

