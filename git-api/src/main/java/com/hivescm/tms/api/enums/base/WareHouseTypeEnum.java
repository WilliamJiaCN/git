package com.hivescm.tms.api.enums.base;

/**
 * ClassName:WareHouseTypeEnum <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	   通用状态枚举类. <br/>
 * Date:       2018年3月30日09:38:59 <br/>
 *
 * @author liupeng
 * @see
 * @since JDK 1.8
 */
public enum WareHouseTypeEnum {
    OTHER_WAREHOUSE(0, "普通仓"),
    SEND_WAREHOUSE(1, "发货仓"),
    RECEIVE_WAREHOUSE(2, "到货仓");

    int type;
    String name;

    WareHouseTypeEnum(int type, String name) {
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
