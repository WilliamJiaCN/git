package com.hivescm.tms.api.enums.capacity.order;

/**
 * 承运商订单操作类型枚举
 * @author ke.huang 
 * @date 2017年10月30日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum OrderOperateTypeEnum {
	
	RECEIPT(1,"接单"),
    REFUSE(2,"拒接"),
    TIMER(3,"超时未接")
    ;
    int type;
    String name;
    OrderOperateTypeEnum(int type,String name){
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
