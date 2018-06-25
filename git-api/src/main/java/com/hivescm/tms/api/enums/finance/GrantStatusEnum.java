package com.hivescm.tms.api.enums.finance;

/**
 * 发放状态
 */
public enum  GrantStatusEnum {
    UNGRANT(1,"未发放"),
    PART_GRANT(2,"部分发放"),
    GRANTED(3,"已发放 "),
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
    private GrantStatusEnum(int type, String name) {
        this.type = type;
        this.name = name;
    }
    public static GrantStatusEnum getType(int type){
        for (GrantStatusEnum ele : GrantStatusEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
