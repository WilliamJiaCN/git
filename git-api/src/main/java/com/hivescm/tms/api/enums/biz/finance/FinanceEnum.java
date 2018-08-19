package com.hivescm.tms.api.enums.biz.finance;

/**
 * 
 * <p>
 * Title:FinanceEnum
 * </p>
 * <p>
 * Description: 收银状态
 * </p>
 * <p>
 * Company: 蜂网供应链（上海）有限公司
 * </p>
 * 
 * @author 王小雪
 * @date 下午7:41:40
 */
public enum FinanceEnum {
	/**
	*
	*/
	REFUSE_ARRIVED(1, "未收银"), ARRIVED(2, "已收银");

	int type;
	String name;
	

	private FinanceEnum(int type, String name) {
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
	
	public static FinanceEnum getType(int type) {
        for (FinanceEnum ele : FinanceEnum.values()) {
            if (ele.getType() == type) {
                return ele;
            }
        }
        return null;
    }

}
