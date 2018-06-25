package com.hivescm.tms.constants;
/**
 * 此常量类暂时为app签收时候,获取支付二维码图片时调用支付平台创建订单接口的一些固定参数
 * @author lhf
 */
public class SignPaymentConstants {
	/**
     * 交易类型
     * (CONSUME:消费,
	 * WITHDRAW:提现,
	 * RECHARGE:充值,
	 * CREDITZ:信贷, 
	 * RUFUND:退款,
	 * CANCEL:取消交易)
     */
    public static final String TRAN_TYPE  = "CONSUME";
    /**
     * 担保交易状态
     * (NOTPASSAGE:不担保,ONPASSAGE:担保)
     * todo 0604 改成非担保交易
     */
//    public static final String TAKE_DELIVERY = "ONPASSAGE";
    public static final String TAKE_DELIVERY = "NOTPASSAGE";
    /**
     *币种(目前只有RMB)    
     */
    public static final String  CURRENCY ="RMB";
    /**
     * 业务平台编号(B2B：6000,TMS:4000,BOSS:100)
     */
    public static final String  BUSINESS_CODE = "4000";
    /**
     * tms端回调通知url
     */
    public static final String  NOTIFY_URL = "http://tms-finance-server/tms-finance-server/signPayment/payResultNotify";
    /**
     * 支付方式 :账户余额：SOOPAY,微信主扫：WXINITIATIVE,微信被扫：WXPASSIVE,微信公众号：WXOTHER ,支付宝主扫：ZFBINITIATIVE,
     *        支付宝被扫：ZFBPASSIVE 支付宝立码支付：ZFBOTHER, 支付宝SDK支付：ZFBSDK 微信SDK:
     */
    public static final String PAYMENT_MODE = "WXINITIATIVE";
}
