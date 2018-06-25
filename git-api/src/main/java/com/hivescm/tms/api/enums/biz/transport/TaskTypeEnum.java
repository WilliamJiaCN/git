package com.hivescm.tms.api.enums.biz.transport;
/**
 * 执行任务枚举 - 数据字典
 * @author ke.huang
 * @date 2017年9月11日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum TaskTypeEnum {
		DELIVERY_INBOUND(1, "提货入库"),
	    OUTBOUND_DELIVERY(2, "出库送货"),
	    PICKUP_DELIVERY(3, "提货送货");



	    int type;
	    String name;

	    TaskTypeEnum(int type, String name) {
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
}
