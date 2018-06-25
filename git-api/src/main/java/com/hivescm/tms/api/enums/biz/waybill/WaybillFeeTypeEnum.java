package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运单费用枚举 - 对应waybill_attr_fee
 * 待补全
 *
 * @author ke.huang
 * @date 2017年9月12日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum WaybillFeeTypeEnum {
    BASIC_FREIGHT(51, "基本运费"),
    PICK_UP(52, "提货费"),
    DELIVERY(53, "送货费"),
    UP_STAIRS(54, "上楼费"),
    PACKING_CHARGES(55,"包装费"),
    INFORMATION_FEE(56,"信息费"),
    COLLECT_PAYMENT(57, "代收货款"),
    COLLECT_PAYMENT_SERVICE(58,"代收货款手续费"),
    INSURANCE_FEE(59,"保价费"),
    EMERGENCY_FEE(60,"加急费"),
    TAX_FEE(61,"税费"),
    BUSINESS(69, "业务费"),
    FREIGHT_ADVANCED(66, "垫付运费"),
    FREIGHT_PAYMENT(67, "垫付货款"),
    DECLARED_VALUE(68, "声明价值"),
	OTHER_FEE(70,"其他费用"),
	TERMINALCHARGES(71,"中转费"),
	FORKLIFTCHARGE(73,"叉车费"),
	WAREHOUSEFEE(74,"仓储费"),
	HANDINGCHARGE(76,"装卸费"),
	CARFEE(77,"车费"),
	LOADINGFEE(78,"装货费"),
	UNLOADINGFEE(79,"卸货费"),
	TO_PAY_DELIVERY_FEE(80,"到付送货费"),
	;
	
	
	
	
	

    int type;
    String name;

    WaybillFeeTypeEnum(int type, String name) {
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

    public static WaybillFeeTypeEnum getType(int type) {
        for (WaybillFeeTypeEnum ele : WaybillFeeTypeEnum.values()) {
            if (ele.getType() == type) {
                return ele;
            }
        }
        return null;
    }

}
