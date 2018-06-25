package com.hivescm.tms.api.enums.biz.regulation;

/**
 * <p>Title: RegulationBranchTypeEnum</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-06-05-20:51
 */

public enum  RegulationBranchTypeEnum {

    FOLD_BRANCH(1,"调入网点"),
    TUNE_OUT_BRANCH(2,"调出网点");
    int type;
    String name;

    RegulationBranchTypeEnum(int type, String name) {
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

    public static RegulationInfoStatusEnum getType(int type){
        for (RegulationInfoStatusEnum ele : RegulationInfoStatusEnum.values()) {
            if (ele.getType() == type)
                return ele;
        }
        return null;
    }
}
