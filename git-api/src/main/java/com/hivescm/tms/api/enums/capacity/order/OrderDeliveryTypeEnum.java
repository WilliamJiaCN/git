package com.hivescm.tms.api.enums.capacity.order;

/**
 * 订单配送类型
 * @author ZHANGWENLONG
 * @date 2018年01月22日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum OrderDeliveryTypeEnum {
	LOGISTICS(1,"物流"),
    DELIVERY(2,"快递"),
    MAIL(3,"邮寄"),
    SALETAKE(4,"自提")
    ;
    int type;
    String name;
    OrderDeliveryTypeEnum(int type,String name){
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
		for(OrderDeliveryTypeEnum os:OrderDeliveryTypeEnum.values()){
			if(os.type == type){
				return os.name;
			}
		}
		return "";
	}

}
