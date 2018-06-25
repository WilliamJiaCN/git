package com.hivescm.tms.finance.server.service.finance.constant;

/**
 * 财务常量
 *
 * @author 杨彭伟
 * @date 2017-11-27 10:39
 */
public class FinanceConstant {
    /**
     * 币种-人民币
     */
    public static final int CHINA_CURRENCY = 1;
    public static final String CHINA_CURRENCY_NAME = "人民币";
    /**
     * 支付方式-货到付款
     */
    public static final String COD_PAYMENT_MODE = "2";
    public static final String COD_PAYMENT_MODE_NAME = "货到付款";
    /**
     * 付款性质-应付账款
     */
    public static final String PAYABLES_PROPERTY_PAYABLE = "4";
    public static final String PAYABLES_PROPERTY_PAYABLE_NAME = "应付账款";

    /**
     * 付款性质-其他应付款
     */
    public static final String PAYABLES_PROPERTY_OTHER_PAYABLE = "6";
    public static final String PAYABLES_PROPERTY_OTHER_PAYABLE_NAME = "其他应付款";
    /**
     * 收款性质-应收账款
     */
    public static final String RECEIVABLES_PROPERTY_RECEIVABLE = "1";
    public static final String RECEIVABLES_PROPERTY_RECEIVABLE_NAME = "应收账款";
    /**
     * 收款性质-预收账款
     */
    public static final String DEPOSIT_RECEIVABLE = "2";
    public static final String DEPOSIT_RECEIVABLE_NAME = "预收账款";

    /**
     * 收款性质-其他应收款
     */
    public static final String RECEIVABLES_PROPERTY_OTHER_RECEIVABLE = "3";
    public static final String RECEIVABLES_PROPERTY_OTHER_RECEIVABLE_NAME = "其他应收款";
    /**
     * 收款类型-代收货款
     */
    public static final int RECEIVABLES_TYPE_ID = 32;
    public static final String RECEIVABLES_TYPE = "代收货款";
    /**
     * 付款单类型-代付货款付款单
     */
    public static final int PAY_BILL_TYPE = 10003;
    /**
     * 付款单类型-服务付款单
     */
    public static final long PAY_SERVICE_TYPE = 10005;
    /**
     * 收款单类型-代收货款收款单
     */
    public static final long RECEIPT_BILL_TYPE = 10003;
    /**
     * 收款单类型-服务收款单
     */
    public static final long RECEIPT_SERVICE_TYPE = 10005;
    /**
     * 付款单-来源单据类型
     */
    public static final String SOURCE_BILL_TYPE = "TMS202";
    public static final String SOURCE_BILL_TYPE_NAME = "运单";
    /**
     * 收款单-来源单据类型
     */
    public static final String RECEIPT_SOURCE_BILL_TYPE = "TMS505";
    public static final String RECEIPT_SOURCE_BILL_TYPE_NAME = "签收单";
    /**
     * 源头单据类型
     */
    public static final String ORIGINAL_BILL_TYPE = "TMS101";
    public static final String ORIGINAL_BILL_TYPE_NAME = "销售订单";
    /**
     * 结算方式code（现金）
     */
    public static final String SETTLE_MODE_CASH_CODE = "JSFS01";
    /**
     * 结算方式code（扫码）
     */
    public static final String SETTLE_MODE_QR_CODE_CODE = "JSFS10";

    /**
     * 收、付款类型 - 服务款code
     */
    public static final String RECEIPT_PAY_TYPE_SERVICE_CODE = "SFLX02";
    /**
     * 收、付款类型 - 代收货款code
     */
    public static final String RECEIPT_PAY_TYPE_COLLECTIONONDELIVERY_CODE = "SFLX10";

    /**
     * 收、付款类型 - 服务款ID
     */
    public static final Integer RECEIPT_PAY_TYPE_SERVICE_ID = 2;
    /**
     * 收、付款类型 - 代收货款ID
     */
    public static final Integer RECEIPT_PAY_TYPE_COLLECTIONONDELIVERY_ID = 10;
    /**
     * 收款单类型-其他收款单
     */
    public static final long RECEIPT_OTHER_TYPE = 10004;
}
