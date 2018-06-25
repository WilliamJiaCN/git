package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运单状态枚举
 * @author ke.huang
 * @date 2017年7月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum WaybillStatusEnum {
	
	PENDING    (2,"待确认"), // >> 
	
	// 城配、派车状态，(送货中)
	// 表示城配中已装货和已发车状态
	// 城配状态 ：城配单(已开单、待处理、已到达) -> 已指派 -> 已接单 -> 已装货 <-> 已发车 -> 已签收(已撤回)
	// 已指派不生成城配批次 已指派和已撤回无实际状态，从推送表中反查运单状态
	TRANSITING (5,"送货中"), // >> 城配已装货和已发车状态
	// 网点已到达 1. 客户自提后状态变更为已签收
	// 网点已到达 2. 派车单 已到达 -> 送货中 -> 已签收 
	// 网点已到达 3. 城配单(已开单、待处理、已到达) -> 已指派 -> 已接单 -> 已装货 <-> 已发车 -> 已签收
	OUTERHAIR  (7,"已外发"), // >> 对接外部系统
	//异常件 >> 异常件，不作为运单状态，由处理人和时间区分
	PARTIAL_SIGN(9,"部分签收"),
	REFUSE_SIGN(10,"拒绝签收"),
	
	/***************************当前在用的运单状态***************************/
	ARRIVED    (4,"已到达"), // >> 发车配载到货确认状态
	UNDEFINED(0,"全部"),//不是运单状态，运单列表检索用
	HASBILLING (1,"已开单"), // >>> 物流公司接单初始状态
	HASGOODS (11,"已揽货"), // >>> 司机APP装货确认
	ENROUTE    (3,"已配送"), // >> 仓库出库确认
	SIGNED     (6,"已签收"), // >> 客户签收
	HANDOVERCANCEL     (13,"已交接取消"), // >> 开单作废
	NOTHANDOVERCANCEL     (14,"未交接取消"),
	CANCEL(8,"已取消"),  //该状态为保留字段，前台传值使用（实际状态已无该值）
	
	ACCEPT(20,"已接单"),
	ONWAY(21,"在途中"),
	DISCARD(22,"已作废"),
	ASSIGNED(23,"已派车");

	
	
	int type;
	String name;
	
	WaybillStatusEnum(int type,String name) {
		this.type = type;
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static WaybillStatusEnum getType(int type){
		for (WaybillStatusEnum ele : WaybillStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
