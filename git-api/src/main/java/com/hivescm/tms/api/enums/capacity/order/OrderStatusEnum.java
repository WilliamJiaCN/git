package com.hivescm.tms.api.enums.capacity.order;


/**
 * description
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/10/21
 */
public enum OrderStatusEnum {

	/**
	 * 注：1，已分配  即是 待接单
	 *    2，运力分配订单列表使用1-8
	 *    3，接单管理订单列表使用4-8 +2
	 */
    WILL_DISTRIBUTE(1,"待分配"),
    DISTRIBUTED(2,"已分配"),
    DISTRIBUTE_FAILED(3,"分配失败"),
    RECEIVED(4,"已接单"),
    REJECTED(5,"已拒接"),
    TIMEOUT(6,"超时未接"),
    RETRACTED(7,"已撤回"),
    CANCELED(8,"已取消")
    ;
    int type;
    String name;
    OrderStatusEnum(int type,String name){
        this.name = name;
        this.type = type;
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
    
    public static OrderStatusEnum getType(int type){
		for (OrderStatusEnum ele : OrderStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
