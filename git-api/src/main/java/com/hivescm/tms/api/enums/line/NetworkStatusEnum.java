/**
 * Project Name:wms-base-server
 * File Name:CommonStatusEnum.java
 * Package Name:com.hivescm.wms.server.base.domain.enums
 * Date:2017年6月22日上午11:30:07
 */

package com.hivescm.tms.api.enums.line;

/**
 * ClassName:CommonStatusEnum <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	   通用状态枚举类. <br/>
 * Date:     2017年6月22日 上午11:30:07 <br/>
 * @author zhangwenhao
 * @version
 * @since JDK 1.8
 * @see
 */
public enum NetworkStatusEnum {
    STARTORG(1, "始发网点"),
    ARRIVEORG(2, "到达网点");
    int type;
    String name;

    NetworkStatusEnum(int type, String name) {
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
        for (NetworkStatusEnum ele : NetworkStatusEnum.values()) {
            if (ele.getType() == type)
                return ele.getName();
        }
        return null;
    }

    public static NetworkStatusEnum getEnum(int type) {
        for (NetworkStatusEnum ele : NetworkStatusEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }

}

