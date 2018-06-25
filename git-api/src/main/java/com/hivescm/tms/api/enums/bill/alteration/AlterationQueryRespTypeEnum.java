package com.hivescm.tms.api.enums.bill.alteration;

/**
 * 运单查询响应结果类型
 * @author ke.huang
 * @date 2018年5月17日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum AlterationQueryRespTypeEnum {
    NULL(1, "运单不存在"),
    NOT_CHANGE(2, "运单不需要修改"),
	NORMAL(3, "正常处理"),
	SIGNED(4,"运单被签收"),
	EXISTS(5,"运单被做过改送");
	
    int type;
    String name;

    AlterationQueryRespTypeEnum(int type, String name) {
        this.type = type;
        this.name = name;
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

