package com.hivescm.tms.api.enums.finance;

/**
 * 回收状态
 */
public enum RecycleStatusEnum {

    UNRECYCLE(1,"未回收"),
    PARY_RECYCLE(2,"部分回收"),
    RECYCLEED(3,"已回收"),
    ;
    int type;
    String name;
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
    private RecycleStatusEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static RecycleStatusEnum getType(int type){
        for (RecycleStatusEnum ele : RecycleStatusEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
