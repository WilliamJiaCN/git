package com.hivescm.tms.api.enums.capacity.order;

/**
 * 订单运输路径 同城、干线
 * @author ke.huang
 * @date 2017年10月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum TransportTypeEnum {

    CITY(1,"同城"),
    TRUNK(2,"干线");
    int code;
    String name;
    TransportTypeEnum(int code,String name){
        this.name = name;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
