package com.hivescm.tms.api.enums.capacity.order;

/**
 * 配送服务类型
 * @author ke.huang
 * @date 2017年10月30日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum OrderServerTypeEnum {
    GENERAL(1,"统仓统配"),
    AUTOGAMY(2,"统仓自配"),
    ;
    int type;
    String name;
    OrderServerTypeEnum(int type,String name){
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
