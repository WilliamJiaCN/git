package com.hivescm.tms.finance.server.constants;

/**
 * 搜索引擎常量类
 * @author ke.huang
 * @date 2018年1月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
public class EsConstants {
    /**
     * 签收主表
     */
    public static final String INDEX_SIGN = "tms-sign";
    public static final String TYPE_SIGN = "tms-sign-list";

    /**
     * 签收明细表
     */
    public static final String INDEX_SIGN_DETAILS = "tms-sign-details";
    public static final String TYPE_SIGN_DETAILS = "tms-sign-details-list";

    /**
     * 签收费用表
     */
    public static final String INDEX_SIGN_FEE = "tms-sign-fee";
    public static final String TYPE_SIGN_FEE = "tms-sign-fee-list";

    /**
     * 拒绝签收
     */
    public static final String INDEX_REFUSE_SIGN = "tms-refuse-sign";
    public static final String TYPE_REFUSE_SIGN = "tms-refuse-sign-list";

    /**
     * 派送失败
     */
    public static final String INDEX_DELIVERY_FAILURE = "tms-delivery-failure";
    public static final String TYPE_DELIVERY_FAILURE = "tms-delivery-failure-list";

    /**
     * 拒绝签收商品详情
     */
    public static final String INDEX_GOODS_DETAILS = "tms-goods-details";
    public static final String TYPE_GOODS_DETAILS = "tms-goods-details-list";
    /**
     * 订单支付表信息索引
     */
    public static final String INDEX_ORDER_PAYMENT_INFO = "tms-order-payment-info";
    public static final String TYPE_ORDER_PAYMENT_INFO = "tms-order-payment-info-list";
    
    /**
     * 收款单索引、类型
     */
    public static final String INDEX_RECEIPT = "tms-receipt";
    public static final String TYPE_RECEIPT = "tms-receipt-list";
    
    
    /**
     * 付款单索引
     */
    public static final String INDEX_PAYMENT = "tms-payment";
    public static final String TYPE_PAYMENT = "tms-payment-list";
    
    
    /**
     * 对账管理
     */
    public static final String INDEX_ACCOUNT_CHECKING_MANAGEMENT = "tms-account-checking-management";
    public static final String TYPE_ACCOUNT_CHECKING_MANAGEMENT = "tms-account-checking-management-list";


    /**
     * 返库主表
     */
    public static final String INDEX_BACK_WAREHOUSE = "tms-back-warehouse";
    public static final String TYPE_BACK_WAREHOUSE = "tms-back-warehouse-list";

    /**
     * 返库明细表
     */
    public static final String INDEX_BACK_WAREHOUSE_DETAIL = "tms-back-warehouse-detail";
    public static final String TYPE_BACK_WAREHOUSE_DETAIL = "tms-back-warehouse-detail-list";

    /**
     * 财务管理-应付
     */
    public static final String INDEX_FINANCE_MANAGE_PAY = "tms-finance-manage-pay";
    public static final String TYPE_FINANCE_MANAGE_PAY = "tms-finance-manage-pay-list";

    /**
     * 财务管理-应收
     */
    public static final String INDEX_FINANCE_MANAGE_RECEIPT = "tms-finance-manage-receipt";
    public static final String TYPE_FINANCE_MANAGE_RECEIPT = "tms-finance-manage-receipt-list";

    /**
     * 财务管理-现金水流
     */
    public static final String INDEX_FINANCE_MANAGE_CASH = "tms-finance-manage-cash";
    public static final String TYPE_FINANCE_MANAGE_CASH = "tms-finance-manage-cash-list";

    /**
     * 财务管理-货款发放
     */
    public static final String INDEX_FINANCE_MANAGE_GOODS_GRANT = "tms-finance-manage-goods-grant";
    public static final String TYPE_FINANCE_MANAGE_GOODS_GRANT  = "tms-finance-manage-goods-grant-list";

    /**
     * 财务管理-货款回收
     */
    public static final String INDEX_FINANCE_MANAGE_GOODS_RECYCLE = "tms-finance-manage-goods-recycle";
    public static final String TYPE_FINANCE_MANAGE_GOODS_RECYCLE = "tms-finance-manage-goods-recycle-list";

    /**
     * 财务管理-现金转账
     */
    public static final String INDEX_FINANCE_MANAGE_CASH_TRANSFER = "tms-finance-manage-cash-transfer";
    public static final String TYPE_FINANCE_MANAGE_CASH_TRANSFER = "tms-finance-manage-cash-transfer-list";
    
    
    /**
     * 财务数据来源日志索引
     */
    public static final String INDEX_FINANCE_ERRORLOG = "tms-finance-errorlog";
    
    /**
     * 财务数据来源日志类型
     */
    public static final String TYPE_FINANCE_ERRORLOG = "tms-finance-errorlog-list";
}


