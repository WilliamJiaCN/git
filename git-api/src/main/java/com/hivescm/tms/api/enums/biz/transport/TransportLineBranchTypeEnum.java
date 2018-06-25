package com.hivescm.tms.api.enums.biz.transport;


public enum TransportLineBranchTypeEnum {
	

	DEPATR_BRANCH(1, "始发网点"),
	
	LINE_BRANCH(2, "途经网点"),

	ARRIVAL_BRANCH(3, "目的网点");
	

    int type;
    String name;

    TransportLineBranchTypeEnum(int type, String name) {
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
