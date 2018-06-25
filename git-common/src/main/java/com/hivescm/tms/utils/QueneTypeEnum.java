/**
 * Project Name:wms-common
 * File Name:ReturnCodeEnum.java
 * Package Name:com.hivescm.wms.enums.common
 * Date:2017年6月28日下午2:53:20
 */

package com.hivescm.tms.utils;

/**
 * ClassName:QueneTypeEnum <br/>
 * Function: 业务单据类型. <br/>
 * Date:     2017年7月27日 下午2:53:20 <br/>
 *
 * @author yuancongcong
 * @see
 * @since JDK 1.8
 */
public enum QueneTypeEnum {

    ACCEPT_BILL(1, "接单"),
	
	ACCEPT_ORDER(2, "接订单");


    int type;
    String name;

    QueneTypeEnum(int type, String name) {
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

    public static String getName(int type) {
        for (QueneTypeEnum ele : QueneTypeEnum.values()) {
            if (ele.getType() == type)
                return ele.getName();
        }
        return null;
    }

    public static QueneTypeEnum getEnum(int type) {
        for (QueneTypeEnum ele : QueneTypeEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return QueneTypeEnum.ACCEPT_BILL;
    }

}


