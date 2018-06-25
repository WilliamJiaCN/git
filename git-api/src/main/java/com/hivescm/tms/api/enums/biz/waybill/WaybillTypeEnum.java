package com.hivescm.tms.api.enums.biz.waybill;

/**
 * 运单类型
 * <p>Title: WaybillTypeEnum</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 * <p>Company:http://hivescm.com/ </p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-03-22-2:20 PM
 */
public enum WaybillTypeEnum {


    OMSENTRANCE(0,"OMS上游录入"),//  >> oms系统订单转运单类型标志位(目前一期的单子，该字段为空，在做相关操作的时候，请加上null的判断)
    SELFENTRANCE(1,"手动创建"),	//  >> 手动录入运单类型标志位
	APP(2,"货主app");	//  >> 货主app

    int type;
    String name;

    WaybillTypeEnum(int type, String name) {
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
