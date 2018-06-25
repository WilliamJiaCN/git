package com.hivescm.tms.api.enums.capacity.delivery;


/**
 * description
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/10/21
 */
public enum OrderDeliveryStatusEnum {

	/**
	 * 注：1，
	 *    2，
	 *    3，
	 */
	TODISTRIBUTE(0,"待分配"),
	RECEIVED(1,"已接单"),
	SIGNED(2,"已签收"),
	REFUSE_SIGN(3,"已拒签"),
    CANCELED(4,"已取消"),
    DISTRIBUTE_FAILED(5,"分配失败")
    ;
    int type;
    String name;
    OrderDeliveryStatusEnum(int type,String name){
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
    
    public static OrderDeliveryStatusEnum getType(int type){
		for (OrderDeliveryStatusEnum ele : OrderDeliveryStatusEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}
