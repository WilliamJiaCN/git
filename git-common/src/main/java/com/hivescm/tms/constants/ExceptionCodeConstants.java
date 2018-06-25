package com.hivescm.tms.constants;

/**
 * 业务异常码常量类
 *
 * @author ke.huang
 * @date 2017年7月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
public class ExceptionCodeConstants {

	/*
     * 异常码参照表 --------------------------------- | 框架错误 | 2001 - 2400 |
	 * --------------------------------- | 基础数据 | 2401 - 2600 |
	 * --------------------------------- | 运单管理 | 2601 - 2800 |
	 * --------------------------------- | 派车单 | 2801 - 3000 |
	 * --------------------------------- | 短途运输 | 3001 - 3200 |
	 * --------------------------------- | 发车配载 | 3201 - 3400 |
	 * --------------------------------- | 发车配载 | 3401 - 3600 |
	 * --------------------------------- | 城配 | 3601 - 3800 |
	 * --------------------------------- | 控货管理 | 3801 - 4000 |
	 * --------------------------------- | 库存、调拨管理 | 4001 - 4200 |
	 * --------------------------------- | 签收管理 | 4201 - 4400 |
	 * --------------------------------- | 异常管理 | 4401 - 4600 |
	 * --------------------------------- | 车辆计划 | 4601 - 4800 |
	 * --------------------------------- | 司机线路 | 4801 - 5000 |
	 * --------------------------------- | 综合查询 | 5001 - 5200 |
	 * --------------------------------- | 单据管理 | 5201 - 5400 |
	 * --------------------------------- | 送货、自提更改 | 5401 - 5600 |
	 * --------------------------------- | 运力分配全局配置 | 5601 - 5800 |
	 * --------------------------------- | 运力分配-->线路 | 5701 - 5750 |
	 * --------------------------------- | 运力分配-->接单 | 5751 - 5770 |
	 * --------------------------------- | 运力分配-->订单 | 5771 - 5800 |
	 * --------------------------------- | 回单库存 | 5801 - 5830 |
	 * --------------------------------- | 外发单 | 5831 - 5899 |
	 * --------------------------------- | 网点管理 | 5900 - 5930 |
	 * --------------------------------- | 装卸单 | 5931 - 5999 |
	 * --------------------------------- | 车费更改 | 8500 - 8600 |
	 */

	/*****************************************************
	 * | 框架错误 | 2001 - 2400 |
	 ************************************************************************/
	/**
	 * 外部依赖接口异常代码
	 */
	public static int ERROR_DEPENDENCY = 2001;
	/**
	 * 实体转换失败
	 */
	public static int ERROR_ENTITY_CONVERT_ERROR = 2002;
	/**
	 * 消息发送失败
	 */
	public static int ERROR_MESSAGE_SEND = 2003;
	/**
	 * 生成es查询条件列表失败
	 */
	public static int ERROR_GEN_CONDITION = 2004;
	/**
	 * 搜索引擎失败
	 */
	public static final int ERROR_SEARCH_ENGINES = 2005;
	/**
	 * 发送短息失败
	 */
	public static final int ERROR_SEND_SMS = 2006;

	/**
	 * 数据校验失败
	 */
	public static final int ERROR_REQUIRED_VALIDATION = 2007;

	/**
	 * 登录信息异常
	 */
	public static final int ERROR_LOGIN_INFO = 2008;

	/**
	 * APP推送失败
	 */
	public static final int ERROR_APP_PUSH = 2009;

	/**
	 * 请求消息头无用户id
	 */
	public static final int ERROR_HTTPHEADER_INFO = 2010;

	/**
	 * ExceptionHandler 返回系统运行时异常
	 */
	public static final int ERROR_SYSTEM_RUNTIME = 2011;

	/*****************************************************
	 * | 基础数据 | 2401 - 2600 |
	 ************************************************************************/
	/**
	 * 司机档案添加
	 */
	public static final int ERROR_BASE_DRIVER = 2401;
	/**
	 * 司机账户添加
	 */
	public static final int ERROR_BASE_DRIVER_BANK = 2402;
	/**
	 * 删除司机信息
	 */
	public static final int ERROR_DELETE_BASE_DRIVER = 2403;
	/**
	 * 修改司机信息
	 */
	public static final int ERROR_UPDATE_BASE_DRIVER = 2404;
	/**
	 * 批量删除司机车辆关联信息
	 */
	public static final int ERROR_BATCHDELETE_BASE_VEHICLEANDDRIVER = 2405;
	/**
	 * 批量插入司机车辆关联信息
	 */
	public static final int ERROR_BATCHINSERT_BASE_VEHICLEANDDRIVER = 2406;
	/**
	 * 插入司机车辆关联信息
	 */
	public static final int ERROR_INSERT_BASE_VEHICLEANDDRIVER = 2407;
	/**
	 * 插入司机车辆关联信息
	 */
	public static final int ERROR_DELETE_BASE_VEHICLEANDDRIVER = 2408;
	/**
	 * 修改司机车辆关联信息
	 */
	public static final int ERROR_UPDATE_BASE_VEHICLEANDDRIVER = 24209;
	/**
	 * 查询司机车辆关联信息
	 */
	public static final int ERROR_SELECT_BASE_VEHICLEANDDRIVER = 2410;
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>2011-2015<<<<<<<<<<<<<<<<<<<<<<<<<
	/**
	 * 按线路id查询到达网点信息
	 */
	public static final int ERROR_SELCTE_BASE_LINE_ARRIVEORG = 2416;
	/**
	 * 批量插入到达网点
	 */
	public static final int ERROR_BATCHINSERT_BASE_LINE_ARRIVEORG = 2417;
	/**
	 * 批量删除到达网点
	 */
	public static final int ERROR_BATCHDELETE_BASE_LINE_ARRIVEORG = 2418;
	/**
	 * 按线路id查询始发网点信息
	 */
	public static final int ERROR_SELCTE_BASE_LINE_STARTORG = 2419;
	/**
	 * 批量插入始发网点
	 */
	public static final int ERROR_BATCHINSERT_BASE_LINE_STARTORG = 2420;
	/**
	 * 批量删除始发网点
	 */
	public static final int ERROR_BATCHDELETE_BASE_LINE_STARTORG = 2421;
	/**
	 * 新增线路
	 */
	public static final int ERROR_INS_BASE_LINE = 2422;
	/**
	 * 修改线路
	 */
	public static final int ERROR_UP_BASE_LINE = 2423;
	/**
	 * 删除线路
	 */
	public static final int ERROR_DEL_BASE_LINE = 2424;
	/**
	 * 查询线路
	 */
	public static final int ERROR_QU_BASE_LINE = 2425;
	/**
	 * 添加仓库信息
	 */
	public static final int ERROR_ADD_WARE_HOUSE = 2426;
	/**
	 * 修改仓库信息
	 */
	public static final int ERROR_UP_WARE_HOUSE = 2427;
	/**
	 * 删除仓库信息
	 */
	public static final int ERROR_DEL_WARE_HOUSE = 2428;
	/**
	 * 查询仓库信息
	 */
	public static final int ERROR_QU_WARE_HOUSE = 2429;
	/**
	 * 添加库区信息
	 */
	public static final int ERROR_ADD_HOUSE_AREA = 2430;
	/**
	 * 修改库区信息
	 */
	public static final int ERROR_UP_HOUSE_AREA = 2431;
	/**
	 * 删除库区信息
	 */
	public static final int ERROR_DEL_HOUSE_AREA = 2432;
	/**
	 * 查询库区信息
	 */
	public static final int ERROR_QU_HOUSE_AREA = 2433;
	/**
	 * 操作库位
	 */
	public static final int ERROR_OPERATE_WAREHOUSEPOSITION = 2426;

	/*****************************************************
	 * | 运单管理 | 2601 - 2800 |
	 ************************************************************************/
	/**
	 * 运单添加操作异常码
	 */
	public static int TMS_WAYBILL = 2601;
	/**
	 * 运单属性查询
	 */
	public static final int ERROR_WAYBILL_ATTR_QR = 2602;
	/**
	 * 保存公司运单配置失败
	 */
	public static final int ERROR_WAYBILL_COM_CONFIG_SAVE = 2603;

	/**
	 * 查询公司运单配置失败
	 */
	public static final int ERROR_WAYBILL_COM_CONFIG_QR = 2604;

	/**
	 * 保存打印配置失败
	 */
	public static final int ERROR_WAYBILL_PRINT_TEM_SAVE = 2605;

	/**
	 * 查询打印配置失败
	 */
	public static final int ERROR_WAYBILL_PRINT_TEM_QR = 2606;

	/**
	 * 查询总运费计算项失败
	 */
	public static final int ERROR_WAYBILL_TOTAL_FEE_ITEM_QR = 2607;

	/**
	 * 删除总运费计算项失败
	 */
	public static final int ERROR_WAYBILL_TOTAL_FEE_ITEM_DELETE = 2608;

	/**
	 * 批量保存总运费计算项失败
	 */
	public static final int ERROR_WAYBILL_TOTAL_FEE_ITEM_SAVE = 2609;

	/**
	 * 保存短信日志失败
	 */
	public static final int ERROR_WAYBILL_SMS_LOG_SAVE = 2610;

	/**
	 * 保存用户光标路径失败
	 */
	public static final int ERROR_WAYBILL_CURSOR_PATH_SAVE = 2611;

	/**
	 * 查询用户光标路径失败
	 */
	public static final int ERROR_WAYBILL_CURSOR_PATH_GET = 2612;

	/**
	 * 保存用户开单配置失败
	 */
	public static final int ERROR_WAYBILL_USER_CONFIG_SAVE = 2613;

	/**
	 * 获取用户开单配置失败
	 */
	public static final int ERROR_WAYBILL_USER_CONFIG_GET = 2614;

	/**
	 * 短信重发时更新短信日志失败
	 */
	public static final int ERROR_WAYBILL_SMS_LOG_UPDATE = 2615;
	/**
	 * 锁定库存失败
	 */
	public static final int ERROR_LOCK_WAYBILL_STOCK = 2616;
	/**
	 * 释放库存失败
	 */
	public static final int ERROR_UNLOCK_WAYBILL_STOCK = 2617;
	/**
	 * 运单不存在
	 */
	public static final int ERROR_WAYBILL_NOT_EXIST = 2618;

	/**
	 * 删除用户开单配置失败
	 */
	public static final int ERROR_WAYBILL_USER_CONFIG_DElETE = 2619;
	
	/**
	 * 运单标签
	 */
	public static final int ERROR_WAYBILL_LABLE = 2620;


	public static final int ERROR_UPDATE_WAYBILL_STOCK = 2621;

	/*****************************************************
	 * | 派车单 | 2801 - 3000 |
	 ************************************************************************/
	/**
	 * 保存派车单失败
	 */
	public static final int ERROR_SAVE_DISPATCHER = 2801;
	/**
	 * 派车单发车失败
	 */
	public static final int ERROR_DISPATCHER_DEPART = 2802;

	/**
	 * 派车单取消发车失败
	 */
	public static final int ERROR_DISPATCHER_CANCEL_DEPART = 2803;

	/**
	 * 删除派车单失败
	 */
	public static final int ERROR_DISPATCHER_DEL = 2804;
	/**
	 * 修改派车单失败
	 */
	public static final int ERROR_DISPATCHER_MODIFY = 2805;

	/**
	 * 派车单提货入库失败
	 */
	public static final int ERROR_DISPATCHER_STORAGE = 2806;

	/**
	 * 派车单提货入库失败
	 */
	public static final int ERROR_DISPATCHER_PUBLISH = 2807;

	/**
	 * 派车单取消发布失败
	 */
	public static final int ERROR_DISPATCHER_CANCEL_PUBLISH = 2808;

	/**
	 * 派车单不存在
	 */
	public static final int ERROR_DISPATCHER_NOT_EXIST = 2809;

	/**
	 * 校验派车单明细网点一致
	 */
	public static final int ERROR_DISPATCHER_BRANCH = 2810;

	/**
	 * 校验派车单明细订单类型一致
	 */
	public static final int ERROR_DISPATCHER_ORDER_TYPE = 2811;

	/**
	 * 校验运单明细是否存在
	 */
	public static final int ERROR_DISPATCHER_WAYBILL_NOT_EXIST = 2812;

	/**
	 * 校验派车单明细是否存在
	 */
	public static final int ERROR_DISPATCHER_DETAIL_NOT_EXIST = 2813;

	/**
	 * 派车单已存在运单
	 */
	public static final int ERROR_DISPATCHER_WAYBILL_HAVED_EXIST = 2814;

	/**
	 * 运单揽货失败
	 */
	public static final int ERROR_DISPATCHER_WAYBILL_SHIPPING_FAIL = 2815;

	/*****************************************************
	 * | 短途运输 | 3001 - 3200 |
	 ************************************************************************/

	/*****************************************************
	 * | 发车配载 | 3201 - 3400 |
	 ************************************************************************/
	/**
	 * 运输批次创建失败
	 */
	public static final int ERROR_TRANSPORT_CREATE = 3201;

	/**
	 * 运输批次发车失败
	 */
	public static final int ERROR_TRANSPORT_DEPART = 3202;

	/**
	 * 运输批次取消发车失败
	 */
	public static final int ERROR_TRANSPORT_CANCEL_DEPART = 3203;

	/**
	 * 运输批次到车失败
	 */
	public static final int ERROR_TRANSPORT_VEHICLE_ARRIVED = 3204;

	/**
	 * 单票添加失败
	 */
	public static final int ERROR_TRANSPORT_WAYBILL_ADD = 3205;

	/**
	 * 单票移除失败
	 */
	public static final int ERROR_TRANSPORT_WAYBILL_DEL = 3206;
	/**
	 * 删除运输批次失败
	 */
	public static final int ERROR_TRANSPORT_DELETE_TRANSPORT = 3207;

	/**
	 * 运输批次操作异常码
	 */
	public static final int TMS_TRANSPORT = 3208;

	/**
	 * 到货确认新增失败
	 */
	public static final int ERROR_TRANSPORTGOODSARRIVE = 3209;
	/**
	 * 待选发车批次明细查询失败
	 */
	public static final int ERROR_TRANSPORT_WAYBILL_DETAIL_SELECT = 3210;
	/**
	 * 途径装货失败
	 */
	public static final int ERROR_WAY_OF_LOADING = 3211;
	/**
	 * 成本分摊失败
	 */
	public static final int ERROR_SHARE_FEES = 3212;

	/**
	 * 批次编码失败
	 */
	public static final int ERROR_BATCH_CODE = 3213;

	/**
	 * 到货入库失败
	 */
	public static final int ERROR_GOODS_IN_OUT_STOCK = 3214;

	/**
	 * 到货批次移除运输明细失败
	 */
	public static final int ERROR_REMOVE_TRANSPORT_WAYBILL_DETAIL = 3215;
	/**
	 * 执行到货确认失败
	 */
	public static final int ERROR_CONFIRM_ARRIVAL = 3216;
	
	/**
	 * 运输批次修改失败
	 */
	public static final int ERROR_TRANSPORT_UPDATE = 3207;
	
	/**
	 * 运输批次发车校验没有发车任务
	 */
	public static final int ERROR_NO_TRANSPORT_DEPART = 3208;

	/*****************************************************
	 * | 城配 | 3601 - 3800 |
	 ************************************************************************/
	/**
	 * 车辆状态失败
	 */
	public static final int ERROR_DISTRIBUTION_VEHICLE_STATUS = 3601;

	/**
	 * 忽略订单状态失败
	 */
	public static final int ERROR_DISTRIBUTION_BLOCK_DRIVER = 3602;
	/**
	 * 更新派车单状态为已装货失败
	 */
	public static final int ERROR_UPDATE_DISTRIBUTION_STATUS_LOADING = 3603;
	/**
	 * 更新派车单状态失败
	 */
	public static final int ERROR_UPDATE_DISTRIBUTION_STATUS_SIGNED = 3604;
	/**
	 * 城配错误码
	 */
	public static final int ERROR_DISTRIBUTION_STATUS = 3605;
	/**
	 * 城配指派司机失败
	 */
	public static final int ERROR_DISTRIBUTION_DRIVER = 3606;
	/**
	 * APP接单失败
	 */
	public static final int ERROR_DISTRIBUTION = 3607;
	/**
	 * APP个人中心失败
	 */
	public static final int ERROR_DISTRIBUTION_DRIVER_CENTER = 3608;
	/**
	 * 城配司机调整路线失败
	 */
	public static final int ERROR_DISTRIBUTION_SORTING_LINE = 3609;
	/**
	 * 更新城配派车单为已发车
	 */
	public static final int ERROR_UPDATE_DISTRIBUTION_STATUS_DEPARTING = 3610;
	/**
	 * 城配司机车辆状态失败
	 */
	public static final int ERROR_DRIVER_VEHICLE_STATUS = 3611;
	/**
	 * 城配智能调度错误
	 */
	public static final int ERROR_DISTRIBUTION_SCHEDULE = 3612;
	/**
	 * 城配后台查询运单列表失败
	 */
	public static final int ERROR_DISTRIBUTION_QUERY_LIST = 3613;
	/**
	 * 城配司机状态失败
	 */
	public static final int ERROR_DISTRIBUTION_DRIVER_STATUS = 3614;
	/*****************************************************
	 * | 控货管理 | 3801 - 4000 |
	 ************************************************************************/
	/**
	 * 控货管理运单不存在
	 */
	public static final int ERROR_CONTROL_GOODS = 3801;
	
	/**
	 * 控货管理运单不存在
	 */
	public static final int ERROR_ADD_CONTROL_GOODS = 3802;
	/*****************************************************
	 * | 库存、调拨管理 | 4001 - 4200 |
	 ************************************************************************/
	/**
	 * 创建仓位转移
	 */
	public static final int ERROR_CREATE_POSITIONTRANS_INFO = 4001;
	/**
	 * 调拨批次新增
	 */
	public static final int ERROR_CREATE_REGULATION_INFO = 4002;
	/**
	 * 调拨批次删除
	 */
	public static final int ERROR_DELETE_REGULATION_INFO = 4003;
	/**
	 * 修改批次删除
	 */
	public static final int ERROR_MODIFY_REGULATION_INFO = 4004;

	/**
	 * 调拨单出库确认
	 */
	public static final int ERROR_CONFIRM_REGULATION_LEAVELIBRARY = 4005;

	/**
	 * 调拨单取消出库
	 */
	public static final int ERROR_CANCEL_REGULATION_LEAVELIBRARY = 4005;

	/**
	 * 网点入库时异常
	 */
	public static final int ERROR_WAYBILL_STOCK_DETAILL = 4006;

	/**
	 * 批量更新入库网点库存信息异常
	 */
	public static final int ERROR_UPDATE_WAYBILL_STOCK_DETAILL = 4007;

	/**
	 * 批量更新入库网点库存回滚信息异常
	 */
	public static final int ERROR_ROOLBACK_WAYBILL_STOCK_DETAILL = 4008;

	/**
	 * 部分签收,拒签,派送失败信息异常
	 */
	public static final int ERROR_SIGN_STOCK_DETAILL = 4009;


	/**
	 * 解除拒签数量时发生异常
	 */
	public static final int ERROR_SIGN_NUMBER = 4010;

	/**
	 * 未入库取消发车异常信息
	 */
	public static final int ERROR_SIGN_NOT_ARRIVED = 4011;
	/**
	 * 回滚库存异常
	 */
	public static final int ERROR_STOCK_ROLLBACK_DETAILL = 4012;

	/**
	 * 扣减库存失败
	 */
	public static final int ERROR_STOCK_DEDYCTION_STOCK = 4013;

	/**
	 * 解除锁定拒签异常
	 */
	public static final int ERROR_STOCK_UPDATE_SIGN_STOCK_NUM = 4014;
	/**
	 * 自提签收异常
	 */
	public static final int ERROR_DEDUCTION_SIGN = 4015;

	public static final int ERROR_STOCK_IN_OUT_STOCK = 4016;

	public static final int ERROR_SIGNGLE_VOTE_STOCK = 4017;


	/*****************************************************
	 * | 签收管理 | 4201 - 4400 |
	 ************************************************************************/

	/**
	 * 新增签收件信息失败
	 */
	public static final int ERROR_ADD_SIGN = 4201;

	/**
	 * 取消签收失败
	 */
	public static final int ERROR_CANCEL_SIGN = 4202;
	/**
	 * 取消签收失败
	 */
	public static final int ERROR_UPDATE_SIGN = 4220;

	/**
	 *查询签收信息失败
	 */
	public static final int ERROR_SEARCH_SIGN = 4203;

	/**
	 * 待签收运单中包裹为空
	 */
	public static final int ERROR_PACKAGE_EMPTY = 4204;
	/**
	 * 拒绝签收失败
	 */
	public static final int ERROR_REFUSE_SIGN = 4205;
	/**
	 * 拒签状态不存在
	 */
	public static final int ERROR_SIGN_STATUS_NOT_EXIST = 4206;
	/**
	 * 拒收单保存失败
	 */
	public static final int ERROR_REFUSE_SIGN_SAVE_FAIL = 4207;
	/**
	 * 拒收商品列表为空
	 */
	public static final int ERROR_REFUSE_GOODS_IS_EMPTY = 4208;
	/**
	 * 订单支付信息保存失败
	 */
	public static final int ERROR_SIGN_PATMENT_SAVE_FAIL = 4209;
	/**
	 * 更新支付状态失败
	 */
	public static final int ERROR_SIGN_PATMENT_ORDER_STATUS = 4210;
	/**
	 * 修改运单状态失败
	 */
	public static final int ERROR_WILLSTATUS_FAIL = 4211;

	/**
	 * 签收生成二维码失败
	 */
	public static final int ERROR_SIGN_GETQC_CODE_FAIL = 4212;

	/**
	 * 查询支付结果失败
	 */
	public static final int ERROR_QUERY_PAY_RESULT_FAIL = 4213;

	/**
	 * 对账管理
	 */
	public static final int ERROR_ACCOUNT_CHECKING = 4214;
	/**
	 * 签收单不存在
	 */
	public static final int ERROR_SIGN_NOT_EXIST = 4215;

	/**
	 * 自提批量签收失败
	 */
	public static final int ERROR_SELF_BATCH_SIGN = 4216;
	
	/**
	 * 外发签收失败
	 */
	public static final int ERROR_OUTBOUND_ADD_SIGN = 4217;
	
	/**
	 * 外发签收失败
	 */
	public static final int ERROR_OUTBOUND_CANCEL_SIGN = 4218;
	/**
	 * 签收锁定运单
	 */
	public static final int ERROR_SIGN_LOCK = 4219;

	/*
	* 送货签收-同步运单签收信息失败
	*/
	public static final int WAYBILL_SIGN_FAILED = 4220;

	/**
	 * 送货签收-同步派车单信息失败
	 */
	public static final int DISPATCHER_SIGN_FAILED = 4221;

	/*
	 * 送货签收-返回入库同步运单信息失败
	 */
	public static final int BACK_HOUSE_WAYBILL_FAILED = 4222;

	/**
	 * 送货签收-返回入库同步派车单信息失败
	 */
	public static final int BACK_HOUSE_DISPATCHER_FAILED= 4223;

	/**
	 * 送货签收-添加反库记录
	 */
	public static final int ERROR_ADD_BACK_HOUSE= 4224;


	/*****************************************************
	 * | 异常管理 | 4401 - 4600 |
	 ************************************************************************/
	/**
	 * 新增异常件信息失败
	 */
	public static final int ERROR_ADD_WAYBILL_EXCEPTION_INFO = 4401;
	/**
	 * 更新异常件信息失败
	 */
	public static final int ERROR_UPDATE_WAYBILL_EXCEPTION_INFO = 4402;

	/**
	 * 查询异常件信息失败
	 */
	public static final int ERROR_QR_WAYBILL_EXCEPTION_INFO = 4403;

	/**
	 * 删除失败
	 */
	public static final int ERROR_DEL_WAYBILL_EXCEPTION_INFO = 4404;

	/**
	 * 办结失败
	 */
	public static final int ERROR_CLOSE_WAYBILL_EXCEPTION = 4405;

	/**
	 * 新增罚款单失败
	 */
	public static final int ERROR_ADD_FORFEIT_BILL = 4406;
	/**
	 * 修改罚款单失败
	 */
	public static final int ERROR_UPDATE_FORFEIT_BILL = 4407;
	/**
	 * 删除罚款单失败
	 */
	public static final int ERROR_DELETE_FORFEIT_BILL = 4408;
	/**
	 * 查询失败
	 */
	public static final int ERROR_GET_FORFEIT_BILL = 4408;
	/**
	 * 提交失败
	 */
	public static final int ERROR_SUBMIT_WAYBILL_EXCEPTION = 4409;

	/*****************************************************
	 * | 车辆计划 | 4601 - 4800 |
	 ************************************************************************/

	/*****************************************************
	 * | 司机线路 | 4801 - 5000 |
	 ************************************************************************/
	/**
	 * 保存公司线路失败
	 */
	public static final int ERROR_OPERATE_ROUTE = 4801;
	/**
	 * 更新公司线路失败
	 */
	public static final int ERROR_UPDATE_ROUTE = 4802;

	/**
	 * 操作区域信息失败
	 */
	public static final int ERROR_OPERATE_REGION = 4812;
	/**
	 * 导入关键词失败
	 */
	public static final int ERROR_IMPORT_KEY_WORD = 4813;
	/**
	 * 操作司机派送线路失败
	 */
	public static final int ERROR_OPERATE_DELIVERY = 4814;

	/**
	 * 操作公司道路失败
	 */
	public static final int ERROR_OPERATE_ROAD = 4815;

	/**
	 * 操作关键词失败
	 */
	public static final int ERROR_OPERATE_KEY_WORD = 4816;
	/**
	 * 操作电子围栏
	 */
	public static final int ERROR_OPERATE_BLOCK = 4817;

	/**
	 * 操作记录
	 */
	public static final int ERROR_OPERATE_RECORD = 4818;
	/**
	 * 关联门店
	 */
	public static final int ERROR_OPERATE_STORE = 4819;
	/*****************************************************
	 * | 综合查询 | 5001 - 5200 |
	 ************************************************************************/
	/**
	 * 综合查询列表失败
	 */
	public static final int ERROR_SITESEARCH_SEARCH = 5001;
	/**
	 * 综合查询单据明细失败
	 */
	public static final int ERROR_SITESEARCH_DETAIL = 5002;
	/**
	 * 综合查询运营信息失败
	 */
	public static final int ERROR_SITESEARCH_NODE = 5003;

	/*****************************************************
	 * | 单据管理 | 5201 - 5400 |
	 ************************************************************************/
	/**
	 * 单据库存失败
	 */
	public static final int ERROR_BILL_STOCK = 5201;
	/**
	 * 单据详情失败
	 */
	public static final int ERROR_BILL_INFO = 5202;
	/**
	 * 单据领用失败
	 */
	public static final int ERROR_BILL_REQUEST_RECEIVED = 5203;

	/*****************************************************
	 * | 送货、自提更改 | 5401 - 5600 |
	 **********************************************************************/
	/**
	 * 删除送货、自提更改失败
	 */
	public static final int ERROR_ALTERATION_DELIVERY_DEL = 5401;
	/**
	 * 新增送货、自提更改失败
	 */
	public static final int ERROR_ALTERATION_DELIVERY_INS = 5402;
	/**
	 * 修改送货、自提更改失败
	 */
	public static final int ERROR_ALTERATION_DELIVERY_UP = 5403;
	/**
	 * 按id查询送货、自提更改失败
	 */
	public static final int ERROR_ALTERATION_DELIVERY_GET = 5404;
	/**
	 * 更改确认失败
	 */
	public static final int ERROR_ALTERATION_DELIVERY_CH = 5405;

	/*****************************************************
	 * | 运力分配 - 全局配置 | 5601 - 5800 |
	 ************************************************************************/
	/**
	 * 全局配置失败
	 */
	public static final int ERROR_GLOBAL_CONFIG = 5601;
	/**
	 * 新增承运商接单规则失败
	 */
	public static final int ERROR_CREATE_ORDERRULE = 5602;

	/**
	 * 修改承运商接单规则失败
	 */
	public static final int ERROR_MODIFY_ORDERRULE = 5603;
	/**
	 * 全局承运商顺序配置失败
	 */
	public static final int ERROR_GLOBAL_CARRIER_CONFIG = 5604;
	/**
	 * 全局承运商比例配置失败
	 */
	public static final int ERROR_GLOBAL_LINE_SCALE_CONFIG = 5605;
	/**
	 * 全局经销商配置失败
	 */
	public static final int ERROR_GLOBAL_DEALER_CARRIER_CONFIG = 5606;
	/**
	 * 经销商设置承运商失败
	 */
	public static final int ERROR_GLOBAL_DEALER_SET_CARRIER_CONFIG = 5607;

	/*****************************************************
	 * | 运力分配-->线路 | 5701 - 5750 |
	 ************************************************************************/
	/**
	 * 城市配送服务新增失败
	 */
	public static final int ERROR_CITY_DELIVERY_SERVICE_ADD = 5701;
	/**
	 * 城市配送服务删除失败
	 */
	public static final int ERROR_CITY_DELIVERY_SERVICE_DELETE = 5702;
	/**
	 * 城市配送服务更新失败
	 */
	public static final int ERROR_CITY_DELIVERY_SERVICE_UPDATE = 5703;
	/**
	 * 配送线路新增失败
	 */
	public static final int ERROR_LINE_DELIVERY_INFO_ADD = 5704;
	/**
	 * 配送线路删除失败
	 */
	public static final int ERROR_LINE_DELIVERY_INFO_DELETE = 5705;
	/**
	 * 配送线路修改失败
	 */
	public static final int ERROR_LINE_DELIVERY_INFO_UPDATE = 5706;
	/**
	 * 配送线路修改失败
	 */
	public static final int ERROR_LINE_CARRIER_SERVICE_ADD = 5707;
	/**
	 * 承运商运力配置失败
	 */
	public static final int ERROR_CARRIER_CAPACITY_CONFIG = 5608;

	/**
	 * 承运商运力配置提交失败
	 */
	public static final int ERROR_COMMIT_CARRIER_CAPACITY_CONFIG = 5609;

	/*****************************************************
	 * | 运力分配-->接单 | 5751 - 5770 |
	 ************************************************************************/

	/**
	 * 运力分配模块-接单管理异常
	 */
	public static final int ERROR_CAPACITY_RECEIVE = 5751;
	/**
	 * 运力分配模块-TMS->OMS订单同步异常
	 */
	public static final int ERROR_CAPACITY_SYN_ORDER = 5752;
	/**
	 * 运力分配模块-TMS->人工分配异常
	 */
	public static final int ERROR_CAPACITY_MANUAL_ORDER = 5753;
	/**
	 * 运力分配模块-TMS->智能分配异常
	 */
	public static final int ERROR_INTELLIGENT_ALLOCATE_ORDER = 5754;

	/*****************************************************
	 * | 运力分配-->订单 | 5771 - 5800 |
	 ************************************************************************/
	/**
	 * 订单接收异常
	 */
	public static final int ERROR_ORDRE_RECEIPT = 5771;
	/**
	 * 订单取消异常
	 */
	public static final int ERROR_ORDRE_CANCEL = 5772;
	/**
	 * 订单查询异常
	 */
	public static final int ERROR_ORDRE_QUERY = 5773;

	/*****************************************************
	 * | 收款单 | 5801 - 5900 |
	 ************************************************************************/
	/**
	 * 查询收款单异常
	 */
	public static final int ERROR_FINANCE_SEARCH = 5801;
	/**
	 * 查询付款的异常
	 */
	public static final int ERROR_PAYMENT_SEARCH = 5802;
	/**
	 * 收款单保存失败 receipt save fail
	 */
	public static final int ERROR_RECEIPT_SAVE_FAIL = 5803;
	/**
	 * 结算方式参数错误
	 */
	public static final int ERROR_PARAM_SETTLEMENT_MODE = 5804;

	/*****************************************************
	 * | 快递运力 全局配置 | 5901 - 6000 |
	 ************************************************************************/
	/**
	 * 快递仓储服务配置失败
	 */
	public static final int ERROR_DELIVERY_GLOBAL_SERVICE_PROVIDER_CONFIG = 5901;
	/**
	 * 快递全局经销商配置失败
	 */
	public static final int ERROR_DELIVERY_GLOBAL_DEALER_CARRIER_CONFIG = 5902;
	/**
	 * 快递经销商设置承运商失败
	 */
	public static final int ERROR_DELIVERY_GLOBAL_DEALER_SET_CARRIER_CONFIG = 5903;
	/*****************************************************
	 * | 回单库存 | 5801 - 5830 |
	 ************************************************************************/
	/**
	 * 回单库存异常
	 */
	public static final int ERROR_RECEIPT_STOCK = 5801;
	/**
	 * 回单查询异常
	 */
	public static final int ERROR_RECEIPT_LIST = 5802;
	/**
	 * 回单回收异常
	 */
	public static final int ERROR_RECEIPT_RECOVERY = 5803;
	/**
	 * 回单出入库
	 */
	public static final int ERROR_RECEIPT_IN_OUT_STOCK = 5803;
	/**
	 * 回单发放
	 */
	public static final int ERROR_RECEIPT_GRANT = 5804;
	/**
	 * 回单取消发放
	 */
	public static final int ERROR_RECEIPT_GRANT_CANCEL = 5805;
	/**
	 * 同步回单库存签收状态
	 */
	public static final int ERROR_RECEIPT_STOCK_SIGN_STATUS = 5806;
	/**
	 * 回单寄出
	 */
	public static final int ERROR_RECEIPT_TRANSMIT = 5807;
	/**
	 * 回单寄出接收明细
	 */
	public static final int ERROR_RECEIPT_TRANSMIT_RECEIVE_DETAIL = 5808;
	/**
	 * 回单接收
	 */
	public static final int ERROR_RECEIPT_RECEIVE = 5809;
	/*****************************************************
	 * | 外发单 | 5831 - 5899 |
	 ************************************************************************/
	/**
	 * 外发单新增异常
	 */
	public static final int ERROR_OUTBILL_ADD = 5831;

	/**
	 * 外发单修改异常
	 */
	public static final int ERROR_OUTBILL_MODIFY = 5832;

	/**
	 * 外发单查询异常
	 */
	public static final int ERROR_OUTBILL_QUERY = 5833;

	/**
	 * 外发单删除异常
	 */
	public static final int ERROR_OUTBILL_DELETE = 5834;
	/**
	 * 外发单跟踪异常
	 */
	public static final int ERROR_OUTBILL_TRACK = 5835;

	/**
	 * 外发单签收异常
	 */
	public static final int ERROR_OUTBILL_SIGN = 5836;

	/*****************************************************
	 * | 网点管理 | 5900 - 5930 |
	 ************************************************************************/

	/**
	 * 网点管理覆盖异常
	 */
	public static final int ERROR_NETWORK_COVER = 5900;

	/**
	 * 网点管理获得网点列表异常（调用boss业务）
	 */
	public static final int ERROR_NETWORK_LIST = 5901;

	/**
	 * 网点管理获得网点详情异常（调用boss业务）
	 */
	public static final int ERROR_NETWORK_DETAILS = 5902;

	/*****************************************************
	 * | 运力分配-->零担订单 | 6001 - |
	 ************************************************************************/
	/**
	 * 零担订单新增异常
	 */
	public static final int ERROR_LTLORDRE_INSERT = 6001;
	/**
	 * 零担订单修改异常
	 */
	public static final int ERROR_LTLORDRE_UPDATE = 6002;

	/**
	 * 零担订单删除异常
	 */
	public static final int ERROR_LTLORDRE_DELETE = 6003;
	/**
	 * 零担订单取消异常
	 */
	public static final int ERROR_LTLORDRE_CANCEL = 6004;
	/**
	 * 零担订单接单异常
	 */
	public static final int ERROR_LTLORDRE_RECEIVE = 6005;
	/**
	 * 零担订单查询异常
	 */
	public static final int ERROR_LTLORDRE_QUERY = 6006;
	/**
	 * 评价新增异常
	 */
	public static final int ERROR_EVALUATE_CREATE = 6100;
	/**
	 * 评价删除异常
	 */
	public static final int ERROR_EVALUATE_DELETE = 6101;
	/**
	 * 订单货物跟踪新增异常
	 */
	public static final int ERROR_VEHICLE_TAIL_AFTER_ORDER_CREATE = 6102;
	/**
	 * 订单货物跟踪删除异常
	 */
	public static final int ERROR_VEHICLE_TAIL_AFTER_ORDER_DELETE = 6103;
	/**
	 * 常发货物新增异常
	 */
	public static final int ERROR_USUALLY_SEND_GOODS_CREATE = 6104;
	/**
	 * 常发货物修改异常
	 */
	public static final int ERROR_USUALLY_SEND_GOODS_UPDATE = 6105;
	/**
	 * 常发货物删除异常
	 */
	public static final int ERROR_USUALLY_SEND_GOODS_DELETE = 6106;
	/**
	 * 订单查看异常
	 */
	public static final int ERROR_ORDER_SCAN = 6107;

	/********************************财务管理-应付*************************/
	/**
	 * 创建应付款记录
	 */
	public static final int ERROR_FINANCE_MANAGE_PAY_INSERT = 6200;
	/**
	 * 生成付款单
	 */
	public static final int ERROR_FINANCE_MANAGE_PAY_PAYCONFIRM = 6201;
	/**
	 * 取消付款
	 */
	public static final int ERROR_FINANCE_MANAGE_PAY_PAYCANCEL = 6202;
	/**
	 * 货款发放确认
	 */
	public static final int ERROR_FINANCE_MANAGE_GRANT_GRANTCONFIRM = 6203;
	/**
	 * 货款发放取消
	 */
	public static final int ERROR_FINANCE_MANAGE_GRANT_GRANTCANCEL = 6204;
	/**
	 * 收单确认
	 */
	public static final int ERROR_FINANCE_MANAGE_GRANT_RECEIVECONFIRM = 6205;
	/**
	 * 进账确认
	 */
	public static final int ERROR_FINANCE_MANAGE_GRANT_RECEIPTCONFIRM = 6206;
	/**
	 * 创建应收失败
	 */
	public static final int ERROR_FINANCE_MANAGE_RECEIPT_INSERT = 6300;
	/**
	 * 应收审核
	 */
	public static final int ERROR_FINANCE_MANAGE_RECEIPT_VERITY = 6301;
	/**
	 * 查询应收失败
	 */
	public static final int ERROR_FINANCE_MANAGE_RECEIPT_QUERY = 6304;

	/**
	 * 应收修改实收金额，状态失败
	 */
	public static final int ERROR_FINANCE_MANAGE_RECEIPT_AMOUNT = 6302;

	/**
	 * 应收插入入参为空
	 */
	public static final int ERROR_FINANCE_MANAGE_RECEIPT_PARAM = 6303;

	/**
	 * 现金流水-添加失败
	 */
	public static final int ERROR_FINANCE_MANAGE_CASH_INSERT = 6400;
	/**
	 * 生成收款单
	 */
	public static final int ERROR_FINANCE_MANAGE_RECEIPT_PAYCONFIRM = 6501;
	/**
	 * 删除收款单
	 */
	public static final int ERROR_FINANCE_MANAGE_RECEIPT_PAYDELETE = 6502;

	/**
	 * 更新转账单列表
	 */
	public static final int ERROR_FINANCE_TRANSFER_UPDATE = 7001;
	/**
	 * 删除转账单列表
	 */
	public static final int ERROR_FINANCE_TRANSFER_DELETE = 7002;
	/**
	 * 新增转账单列表
	 */
	public static final int ERROR_FINANCE_TRANSFER_INSERT = 7003;

	/**
	 * 更新现金流水
	 */
	public static final int ERROR_FINANCE_SERIAL_UPDATE = 8001;

	/**
	 * 查询现金流水
	 */
	public static final int ERROR_FINANCE_SERIAL_QUERY = 8001;

	/**
	 * 打印转账详情
	 */
	public static final int ERROR_PRINT_CASH_TRANSFER = 8002;


	/**
	 * 装卸单批次
	 */
	public static final int ERROR_HANGDLING_BATCH_NUMBER_ERROR = 5931;
	/**
	 * 装卸单查询
	 */
	public static final int ERROR_HANGDLING_QUERY_ERROR = 5934;
	/**
	 * 装卸单明细查询
	 */
	public static final int ERROR_HANGDLING_DETAIL_QUERY_ERROR = 5935;
	/**
	 * 装卸货批次
	 */
	public static final int ERROR_HANGDLING_ORDER_NUMBER_ERROR = 5932;

	public static final int ERROR_HANDLING_ORDER = 5933;

    /********************************车费更改*************************/
    /**
     * 车费更改
     */
    public static final int ERROR_FARE_CHANGE = 8500;
	/**
	 * 汇款
	 */
	public static final int ERROR_FINANCE_GOODS_GRANT_REMIT = 5934;

}
