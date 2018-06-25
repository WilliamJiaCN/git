package com.hivescm.tms.constants;
/**
 * 短信发送节点常量类
 * @author Administrator
 *
 */
public class SmsTemplateContentConstants {
	
	/**
	 * 创建运单发货人
	 */
	public static final String CREATE_WAYBILL_INVOICE = "【%s】您发往[%s]的单号[%s]共[%s]件[%s]已揽收，联系电话：[%s]";
	
	/**
	 * 创建运单收货人
	 */
	public static final String CREATE_WAYBILL_RECEIPT = "【%s】您的单号[%s]共[%s]件[%s]已揽收，到付运费[%s]元，代收货款[%s]元，如需查货请登陆官网或者关注微信公众号，联系电话：[%s]";
	
	/**
	 * 发车配载发货人
	 */
	public static final String DEPARTURE_LOADING_INVOICE= "【%s】您发往[%s]的单号[%s]共[%s]件[%s]已出库，联系电话：[%s]";

	/**
	 * 发车配载收货人
	 */
	public static final String DEPARTURE_LOADING_RECEIPT= "【%s】您的[%s]件[%s]单号[%s]已经出库，到付运费[%s]元，代收货款[%s]元，，如需查货请登陆官网或者关注微信公众号，联系电话：[%s]";
	
	/**
	 * 到达确认发货人
	 */
	public static final String ARRIVAL_CONFIRMATION_INVOICE= "【%s】您发往[%s]的单号[%s]共[%s]件[%s]已到达[%s]，联系电话：[%s]";
	
	/**
	 * 到达确认收货人
	 */
	public static final String ARRIVAL_CONFIRMATION_RECEIPT= "【%s】您的[%s]件[%s]单号[%s]已经到达[%s]，到付运费[%s]元，代收货款[%s]元，地址：[%s]，联系电话[%s]";
	
	/**
	 * 外发确认发货人
	 */
	public static final String OUTGOING_CONFIRMATION_INVOICE= "";
	
	/**
	 * 外发确认收货人
	 */
	public static final String OUTGOING_CONFIRMATION_RECEIPT= "";
	
	/**
	 * 外发签收发货人
	 */
	public static final String OUTGOING_RECEIPT_INVOICE= "【%s】您的单号[%s]共[%s][%s]已由[%s]签收。如有疑问请联系：[%s]";
	
	/**
	 * 外发签收收货人
	 */
	public static final String OUTGOING_RECEIPT_RECEIPT= "【%s】您的货物已签收，单号[%s],签收人[%s]。如有疑问请联系[%s]";
	
	/**
	 * 自提签收发货人
	 */
	public static final String SELF_DELIVERY_SIGN_INVOICE= "【%s】您的单号[%s]共[%s][%s]已由[%s]签收。如有疑问请联系：[%s]";
	
	/**
	 * 自提签收收货人
	 */
	public static final String SELF_DELIVERY_SIGN_RECEIPT= "【%s】您的货物已签收，单号[%s],签收人[%s]。如有疑问请联系[%s]";
	
	/**
	 * 送货派车发货人
	 */
	public static final String DELIVERY_CAR_INVOICE= "【%s】您的单号[%s]共[%s][%s]已安排派送，如有疑问请联系：[%s]";
	
	/**
	 * 送货派车收货人
	 */
	public static final String DELIVERY_CAR_RECEIPT= "【%s】您的单号[%s]共[%s][%s]派送中，取货时请出示验证码：[%s]，到付运费[%s]元，代收货款[%s]元，请保持联系电话畅通，联系电话[%s]。";
	
	/**
	 * 送货签收发货人
	 */
	public static final String DELIVERY_RECEIPT_INVOICE= "【%s】您的单号[%s]共[%s][%s]已由[%s]签收。如有疑问请联系：[%s]";
	
	/**
	 * 送货签收收货人
	 */
//	public static final String DELIVERY_RECEIPT_RECEIPT= "【%s】您的货物已签收，单号[%s],签收人[%s]。如有疑问请联系[%s]";
//	public static final String DELIVERY_RECEIPT_RECEIPT= "【%s】您的订单【订单号】由配送员【司机姓名】，电话【手机号码】正在给您派送，取货验证码【验证码】，代收货款【代收货款】元，请您耐心等待。";
	public static final String DELIVERY_RECEIPT_RECEIPT= "您的订单%s由配送员%s，电话%s正在给您派送，取货验证码%s，代收货款%s元，请您耐心等待。";
	public static final String GOODSUSER_REGISTER_SUCCESS="【蜂网供应链】尊敬的客户，您好。您的APP账户：%s，初始密码：123456，APP下载地址：XXXX（%s物流）";
	public static final String GOODSUSER_REGISTER_EXIST="【蜂网供应链】尊敬的客户，您好。您已开通APP登陆账户，可直接登录使用。(XX物流）";
	/**
	 * 货款收回发货人
	 */
	public static final String PAYMENT_BACK_INVOICE= "";
	
	/**
	 * 货款收回收货人
	 */
	public static final String PAYMENT_BACK_RECEIPT= "";
	
	/**
	 * 贷款发放发货人
	 */
	public static final String PAYMENT_GRANT_INVOICE= "";
	
	/**
	 * 贷款发放收货人
	 */
	public static final String PAYMENT_GRANT_RECEIPT= "";
	
	/**
	 * 回单签收发货人
	 */
	public static final String SIGN_RECEIPT_INVOICE= "";
	
	/**
	 * 回单签收收货人
	 */
	public static final String SIGN_RECEIPT_RECEIPT= "";
	
	/**
	 * 回单签收发货人
	 */
	public static final String RETURN_RECEIPT_INVOICE= "";
	
	/**
	 * 回单返厂收货人
	 */
	public static final String RETURN_RECEIPT_RECEIPT= "";

}
