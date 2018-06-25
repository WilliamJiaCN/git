package com.hivescm.tms.api.enums.bill.receipt;

/**
 * 回单单据签收状态
 * 1=未签收 2=正常签收 3=部分签收 4=拒签
 * @author ke.huang
 * @date 2018年3月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ReceiptSignStatusEnum {
    NOT_SIGNED(1, "未签收"),
    SIGNED(2, "正常签收 "),
	SIGNED_PART(3, "部分签收 "),
	SIGNED_REFUSE(4, "全部拒签");
	
    int type;
    String name;

    ReceiptSignStatusEnum(int type, String name) {
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

