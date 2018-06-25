package com.hivescm.tms.api.enums.biz.dispatcher;

/**
 * 派车单状态 1=已指派 2=已接单 3=已装货 4=未发车 5=已发车
 *
 * @author 李洪春
 * @since 2017/8/14 19:24
 */
public enum DispatcherStatusEnum {

	ASSIGNED(1, "已指派"),

	ACCEPTED(2, "已接单"),

	LOADED(3, "已装货"),

	NOT_DEPARTING(4, "未发车"),//已用

	DEPARTED(5, "已发车"),//已用

	SIGNED(6, "已完成"),//已用

	CANCELED(7, "已撤单"),

	DISCARD(8, "已作废"), //已用

	COMMITED(9, "已交接"),//已用

	SEND(10, "已配送");//已用

	// NOT_DEPARTING(3, "未发车"),
	//
	// DEPARTED(4, "已发车"),
	//
	// COMMITED(5,"已交接"),
	//
	// SEND(6,"已配送"),

	int type;
	String name;

	DispatcherStatusEnum(int type, String name) {
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

	public static DispatcherStatusEnum getType(int type) {
		for (DispatcherStatusEnum ele : DispatcherStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
