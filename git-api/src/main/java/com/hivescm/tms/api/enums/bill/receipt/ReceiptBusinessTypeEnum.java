package com.hivescm.tms.api.enums.bill.receipt;

/**
 * 回单库存业务节点类型
 * @author ke.huang
 * @date 2018年3月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ReceiptBusinessTypeEnum {
    DEPART_CONFIRM,//发车确认 - 出库
    DEPART_CANCEL,//取消发车 - 入库
    ARRIVAL_CONFIRM,//到货确认 - 入库
    RECOVERY_BACK,//返库回收 - 入库
    RECOVERY_REFUSE,//拒签回收 - 入库
    RECOVERY_SIGN,//签收回收 - 入库
    RECOVERY_NOT_SIGN//签收未回收
    //SEND_CONFIRM,//寄出 - 出库
    //SEND_CANCEL,//取消寄出 - 入库
    //RECEIVE,//接收 - 入库
    //GRANT,//发放 - 出库
    //GRANT_CANCEL//取消发放 - 入库
	
}

