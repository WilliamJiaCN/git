package com.hivescm.tms.finance.server.config;

import com.hivescm.framework.elasticsearch.conf.TypeIndexConfiguration;
import com.hivescm.tms.finance.server.constants.EsConstants;

public class EsConfig extends TypeIndexConfiguration {
    protected EsConfig(String indexName, String typeName) {
        this.setIndexName(indexName);
        this.setTypeName(typeName);
    }

    public static EsConfig sign() {
        return signInstance.conf;
    }



    private static class signInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_SIGN, EsConstants.TYPE_SIGN);
    }

    public static EsConfig signGoodsDetail() {
        return signGoodsDetailInstance.conf;
    }

    private static class signGoodsDetailInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_SIGN_DETAILS, EsConstants.TYPE_SIGN_DETAILS);
    }

    public static EsConfig signFee() {
        return signFeeInstance.conf;
    }

    private static class signFeeInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_SIGN_FEE, EsConstants.TYPE_SIGN_FEE);
    }

    /**
     * 拒绝签收表
     */
    private static class RefuseSignInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_REFUSE_SIGN, EsConstants.TYPE_REFUSE_SIGN);
    }

    public static EsConfig refuseSign() {
        return RefuseSignInstance.conf;
    }

    /**
     * 派送失败表
     */
    private static class DeliveryFailureInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_DELIVERY_FAILURE, EsConstants.TYPE_DELIVERY_FAILURE);
    }

    public static EsConfig deliveryFailure() {
        return DeliveryFailureInstance.conf;
    }

    /**
     * 拒绝签收单商品详情表
     */
    private static class RefuseSignGoodsDetailsInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_GOODS_DETAILS, EsConstants.TYPE_GOODS_DETAILS);
    }

    public static EsConfig refuseSignGoodsDetails() {
        return RefuseSignGoodsDetailsInstance.conf;
    }
    /**
     * 订单支付信息
     */
    private static class OrderPaymentInfoInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_ORDER_PAYMENT_INFO, EsConstants.TYPE_ORDER_PAYMENT_INFO);
    }

    public static EsConfig orderPaymentInfo() {
        return OrderPaymentInfoInstance.conf;
    }
    
    /**
     * 收款单
     *
     * @author ke.huang
     * @date 2017年8月14日
     * @company 蜂网供应链管理（上海）有限公司
     */
    private static class FinanceInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_RECEIPT, EsConstants.TYPE_RECEIPT);
    }

    public static EsConfig finance() {
        return FinanceInstance.conf;
    }
    
    /**
     * 付款单
     * @author Administrator
     *
     */
    private static class PaymentInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_PAYMENT, EsConstants.TYPE_PAYMENT);
    }

    public static EsConfig payment() {
        return PaymentInstance.conf;
    }
    
    /**
     * 对账管理
     */
    private static class AccountCheckingManagementInstance {
    	private static final EsConfig conf = new EsConfig(EsConstants.INDEX_ACCOUNT_CHECKING_MANAGEMENT, EsConstants.TYPE_ACCOUNT_CHECKING_MANAGEMENT);
    }
    
    public static EsConfig  accountChecking() {
    	return AccountCheckingManagementInstance.conf;
    }



    /**
     * 返库主表
     */
    private static class BackWarehouseInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_BACK_WAREHOUSE, EsConstants.TYPE_BACK_WAREHOUSE);
    }

    public static EsConfig  backWarehouse() {
        return BackWarehouseInstance.conf;
    }

    /**
     * 返库明细表
     */
    private static class BackWarehouseDetailInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_BACK_WAREHOUSE_DETAIL, EsConstants.TYPE_BACK_WAREHOUSE_DETAIL);
    }

    public static EsConfig  backWarehouseDetail() {
        return BackWarehouseDetailInstance.conf;
    }

    /**
     * 财务管理-应付
     */
    private static class FinanceManagePayInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_FINANCE_MANAGE_PAY, EsConstants.TYPE_FINANCE_MANAGE_PAY);
    }

    public static EsConfig  financeManagePay() {
        return FinanceManagePayInstance.conf;
    }

    /*public static TypeIndexConfiguration financeManageReceipt() { return FinanceManageReceiptInstance.conf; }*/

    /**
     * 财务管理-应收
     */
    private static class FinanceManageReceiptInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_FINANCE_MANAGE_RECEIPT, EsConstants.TYPE_FINANCE_MANAGE_RECEIPT);
    }

    public static EsConfig  financeManageReceipt() {
        return FinanceManageReceiptInstance.conf;
    }

    /**
     * 财务管理-应收
     */
    private static class FinanceManageCashSerialInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_FINANCE_MANAGE_CASH, EsConstants.TYPE_FINANCE_MANAGE_CASH);
    }

    public static EsConfig  financeManageCash() {
        return FinanceManageCashSerialInstance.conf;
    }


    /**
     * 财务管理-货款发放
     */
    private static class FinanceManageGoodsGrantInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_FINANCE_MANAGE_GOODS_GRANT, EsConstants.TYPE_FINANCE_MANAGE_GOODS_GRANT);
    }

    public static EsConfig  financeManageGoodsGrant() {
        return FinanceManageGoodsGrantInstance.conf;
    }

    /**
     * 财务管理-货款回收
     */
    private static class FinanceManageGoodsRecycleInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_FINANCE_MANAGE_GOODS_RECYCLE, EsConstants.TYPE_FINANCE_MANAGE_GOODS_RECYCLE);
    }

    public static EsConfig  financeManageGoodsRecycle() {
        return FinanceManageGoodsRecycleInstance.conf;
    }

    /**
     * 财务管理-现金转账
     */
    private static class FinanceManageCashTransferInstance {
        private static final EsConfig conf = new EsConfig(EsConstants.INDEX_FINANCE_MANAGE_CASH_TRANSFER, EsConstants.TYPE_FINANCE_MANAGE_CASH_TRANSFER);
    }

    public static EsConfig  financeManageCashTransfer() {
        return FinanceManageCashTransferInstance.conf;
    }
    
    
    /**
     * 财务数据来源错误日志
     */
    private static class FinanceErrorLogInstance {
    	private static final EsConfig conf = new EsConfig(EsConstants.INDEX_FINANCE_ERRORLOG, EsConstants.TYPE_FINANCE_ERRORLOG);
    }
    
    public static EsConfig  financeErrorLog() {
    	return FinanceErrorLogInstance.conf;
    }
}
