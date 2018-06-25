package com.hivescm.tms.api.enums.bill.receipt;

import com.hivescm.tms.api.enums.biz.waybill.WaybillPayWayEnum;

/**
 * 回单单据签收状态
 * 1=未签收 2=正常签收 3=部分签收 4=拒签
 * @author ke.huang
 * @date 2018年3月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
public enum ReceiptRequirmentTypeEnum {
    SIGN_RECEIPT(1, "签回单"),
    SIGN_GRASP(2,"签回执"),
    SIGN_BILL(3,"签原单");
	
    int type;
    String name;

    ReceiptRequirmentTypeEnum(int type, String name) {
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
    
    public static ReceiptRequirmentTypeEnum getType(int type){
		for (ReceiptRequirmentTypeEnum ele : ReceiptRequirmentTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}

}

