package com.hivescm.tms.api.enums.capacity.ltlorder;


/**
 * 订单状态
 *
 * @author wenxiong.jia
 * @date 2018/4/8
 */
public enum LtlOrderStatusEnum {

    WILL_RECEIVE(1,"未接单"),
    RECEIVED(2,"已接单"),
    DISPATCHED(3,"已派车"),
    BILLED(4,"已开单"),
    CANCELED(5,"已取消")
    ;
    int type;
    String name;
    LtlOrderStatusEnum(int type,String name){
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
    
    public static LtlOrderStatusEnum getType(int type){
		for (LtlOrderStatusEnum ele : LtlOrderStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
