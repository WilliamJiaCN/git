package com.hivescm.tms.api.enums.capacity.ltlorder;

/**
 * 订单发货方式
 * @author wenxiong.jia
 * @date 2018/4/8
 */
public enum LtlOrderDeliveryTypeEnum {
	HOME_DELIVERY(1,"上门提货"),
    SELF_ADDRESS(2,"送至网点")
    ;
    int type;
    String name;
    LtlOrderDeliveryTypeEnum(int type,String name){
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
    
    public static String getName(int type){
		for(LtlOrderDeliveryTypeEnum os:LtlOrderDeliveryTypeEnum.values()){
			if(os.type == type){
				return os.name;
			}
		}
		return "";
	}

}
