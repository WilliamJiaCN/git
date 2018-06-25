package com.hivescm.tms.api.enums.biz.dispatcher;


/**
 * 接收仓库发送派车单状态
 *
 * @author m5itao
 * @since 2017/11/8
 */
public enum DispatcherStatusFromWarehouseEnum {
    HANDEDOVER, //已交接
    LOADED,     //已装货
    DISCARD     //已作废
}
