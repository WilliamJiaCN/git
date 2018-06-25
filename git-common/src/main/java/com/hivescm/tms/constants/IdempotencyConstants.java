package com.hivescm.tms.constants;

/**
 * 幂等性配置
 * @author ke.huang
 * @date 2018年1月3日
 * @company 蜂网供应链管理（上海）有限公司
 */
public class IdempotencyConstants {
	
	public static final String SYSTEM_NAME = "tms-idempotency";
	
	/**--------------------------------------------------**/
	/**    请在下方定义幂等校验规则，并注明应用场景                 **/
	/**    命名规范 - 模块名称_业务范围_CHECK              **/
	/**--------------------------------------------------**/
	
	/**
	 * OMS下订单
	 * 包含销售订单、销退订单
	 */
	public static final String ORDER_OMS_CHECK = "check-oms-order-receipt";
	
	/**
	 * OMS下订单
	 * 包含销售订单、销退订单
	 */
	public static final String BIZ_WMS_PACKAGE = "check-wms-packagecode";
	/**
	 * WMS新增派车单
	 */
	public static final String BIZ_WMS_INSERT_DISPATCHER = "check-wms-insert-dispatcher";
	
	/**
	 * WMS新增派车单
	 */
	public static final String BIZ_WMS_INSERT_WAREHOUSE = "check-wms-insert-warehouse";
	/**
	 * WMS修改派车单
	 */
	public static final String BIZ_WMS_UPDATE_DISPATCHER = "check-wms-update-dispatcher";
	
	/**
	 * WMS交接
	 */
	public static final String BIZ_WMS_HANDLE_DISPATCHER = "check-wms-handle-dispatcher";
	
	/**
	 * WMS配送
	 */
	public static final String BIZ_WMS_SEND_DISPATCHER = "check-wms-send-dispatcher";
	
	/**
	 * WMS作废
	 */
	public static final String BIZ_WMS_DISCAED_DISPATCHER = "check-wms-discard-dispatcher";
	
	/**
	 * TMS单个装货
	 */
	public static final String BIZ_TMS_SHIPMENT_DISPATCHER = "check-tms_shipment_dispatcher";
	
	/**
	 * TMS批量装货
	 */
	public static final String BIZ_TMS_SHIPMENTBATCH_DISPATCHER = "check-tms_shipmentbatch_dispatcher";
	
	
	/**
	 * 生成签收单
	 */
	public static final String BIZ_TMS_SIGN_INSERT = "check-tms_sign_insert";
	
	/**
	 * 生成拒收单
	 */
	public static final String BIZ_TMS_REFUSE_INSERT = "check-tms_refuse_insert";

	/**
	 * 禁止重复提交
	 *
	 */
	public static final String BIZ_TMS_SIGN_TYPE_RECOMMIT = "check-tms_sign_recommit_forbidden";

	/**
	 * 送货签收 取消签收
	 *
	 */
	public static final String BIZ_TMS_DELIVERY_CANCEL_SIGN_DEMPOTENCY  = "check-tms_delivery_cancel_sign";

	/**
	 * 反库操作 防止重复提交
	 */
	public static final String BIZ_TMS_ADD_WAREHOUST_RECOMMIT  = "check-tms_add_warehouse_recommit_forbidden";

	/**
	 * 派送失败 防止重复提交
	 */
	public static final String BIZ_TMS_DISPATCHER_FAIL_RECOMMIT  = "check-tms_dispatcher_fail_recommit_forbidden";
	/**
	 * 送货签收幂等key
	 */
	public static final String BIZ_TMS_DELIVERY_DEMPOTENCY = "check-tms_delivery_sign";
	/**
	 * 派送批量签收幂等key
	 */
	public static final String BIZ_TMS_BATCH_DELIVERYI_DEMPOTENCY = "check-tms_sign_recommit_forbidden";
	/**
	 * 司机档案新增身份证校验
	 */
	public static final String BASE_DRIVER_ADD = "check-base-driver-add";
	
	/**
	 *零担网点开单
	 */
	public static final String BILL_WAYBILL_ADD = "check-tms-waybill";
	
	/**
	 * 零担派车单幂等key
	 */
	public static final String BIZ_TMS_DISPATCHER_DEMPOTENCY = "check-tms_dispatcher";

	/**
	 *运单操作幂等校验-正在操作时不能作其他操作
	 */
	public static final String CHECK_WAYBILL = "tms-waybill-update-check";

	/**
	 *零担网点运单修改
	 */
	public static final String BILL_WAYBILL_UPDATE = "tms-waybill-update";
}
