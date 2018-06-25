package com.hivescm.tms.api.enums.biz.transport;

/**
 * 发车批次明细枚举类
 */
public class TransportWaybillConstants {

    public enum ArrivalTypeEnum{

        NORMAL_ARRIVE(1,"正常到货"),
        ERROR_ARRIVE(2,"异常到货")
        ;

        ArrivalTypeEnum(Integer type, String name) {
            this.type = type;
            this.name = name;
        }

        private Integer type;
        private String name;

        public Integer getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
