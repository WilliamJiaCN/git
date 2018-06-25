package com.hivescm.tms.api.enums.capacity.order;

/**
 * 订单类型
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/10/26
 */
public enum OrderTypeEnum {

    SALE_ORDER(1, "销售单"),
    CANCEL_ORDER(2, "销退单");
    int code;
    String desc;

    OrderTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public static OrderTypeEnum getCode(int code){
		for (OrderTypeEnum ele : OrderTypeEnum.values()) {
			if (ele.getCode() == code)
				return ele;
		}
		return null;
	}
}
