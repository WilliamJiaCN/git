package com.hivescm.tms.api.enums.capacity.ltlorder;

/**
 * 订单列表时间筛选类型 
 * @author wenxiong.jia 
 * @date 2018/4/8
 */
public enum LtlOrderSearchTimerTypeEnum {
	
	CREATE_TIMER(1,"下单时间"),
    RECEIVE_TIMER(2,"接单时间"),
    CANCEL_TIMER(3,"取消时间")
    ;
    int type;
    String name;
    LtlOrderSearchTimerTypeEnum(int type,String name){
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
