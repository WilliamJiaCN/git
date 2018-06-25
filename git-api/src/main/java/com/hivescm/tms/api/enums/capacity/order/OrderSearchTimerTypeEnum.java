package com.hivescm.tms.api.enums.capacity.order;

/**
 * 订单列表时间筛选类型 -> tms-ostt
 * @author ke.huang 
 * @date 2017年10月30日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum OrderSearchTimerTypeEnum {
	
	CREATE_TIMER(1,"下单时间"),
    RECEIVE_TIMER(2,"接收时间"),
    CAPACITY_TIMER(3,"运力分配时间"),
    ACCEPT_TIMER(4,"接单时间"),
    REFUSE_TIMER(5,"拒接时间"),
    RETRACT_TIMER(6,"撤回时间")
    ;
    int type;
    String name;
    OrderSearchTimerTypeEnum(int type,String name){
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

}
