package com.hivescm.tms.api.enums.capacity.order;

/**
 * 
 * @Description 销退单类型枚举  
 * @author wangchunfeng
 * @date 2017年10月25日  
 *
 */
public enum ReturnTypeEnum {
    /**
     * 普通退货
     * value=1
     */
    AUTO("AUTO",1,"普通销退"),
    /**
     * 拒收
     * value=2
     */
    REFUSE("REFUSE",2,"拒收销退"),
    /**
     * 取消订单
     * value=3
     */
    REVOKE("REVOKE",3,"取消销退");
    
    private ReturnTypeEnum(String code,int value,String name){
        this.code=code;
        this.value=value;
        this.name=name;
    }
    private String code;

    private int value;

    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static ReturnTypeEnum getValue(int value){
		for (ReturnTypeEnum ele : ReturnTypeEnum.values()) {
			if (ele.getValue() == value)
				return ele;
		}
		return null;
	}
}
