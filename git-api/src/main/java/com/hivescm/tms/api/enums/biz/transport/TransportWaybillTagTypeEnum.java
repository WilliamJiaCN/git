package com.hivescm.tms.api.enums.biz.transport;

public enum TransportWaybillTagTypeEnum {


	LOAD(1, "装车"),//已启用
	
    UN_LOAD(2, "到货");//已启用

    int type;
    String name;

    TransportWaybillTagTypeEnum(int type, String name) {
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
