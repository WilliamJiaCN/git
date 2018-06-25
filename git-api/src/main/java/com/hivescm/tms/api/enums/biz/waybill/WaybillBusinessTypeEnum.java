package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运单业务类型枚举
 *
 * @author 李洪春
 * @since 2017/10/11 16:30
 */
public enum WaybillBusinessTypeEnum {

    /**
     * 派车单
     */
    DISPATCHER(0, "派车批次"),

    /**
     * 发车配载、短途运输
     */
    DEPART(1, "发车批次"),

    /**
     * 到货批次
     */
    ARRIVE(2, "到货批次"),

    /**
     * 异常件
     */
    EXCEPTION(3, "异常件");

    // 装货批次、外发批次后续补充

    int type;
    String name;

    WaybillBusinessTypeEnum(int type, String name) {
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

    public static ChangeWaybillStatusEnum getType(int type) {
        for (ChangeWaybillStatusEnum ele : ChangeWaybillStatusEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
