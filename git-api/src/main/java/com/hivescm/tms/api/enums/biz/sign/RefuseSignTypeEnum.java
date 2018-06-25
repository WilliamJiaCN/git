/**
 * 
 */
package com.hivescm.tms.api.enums.biz.sign;

/**
 *
 * 取消签收  在原来的拒收状态基础上 + 10
 * @author boqiang.deng
 * @date 2018年4月3日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
public enum RefuseSignTypeEnum {
	PARTIAL_REFUSE_SIGN(2, "部分签收"),
	REFUSE_SIGN(3, "全部拒签"),
	PARTIAL_REFUSE_SIGN_CANCEL(12, "部分签收 已取消"),
	REFUSE_SIGN_CANCEL(13, "全部拒签 已取消"),

    /**
     * 不要用这个,在拒收单类型未被赋值的情况下 进行取消签收
     */
    @Deprecated
    CANCEL(4, "已取消");

	int type;
	String name;
	RefuseSignTypeEnum(int type, String name) {
		this.name = name;
		this.type = type;
	}

	public RefuseSignTypeEnum  toCancelRefuseSignType(){


		if (this.getType()==PARTIAL_REFUSE_SIGN.getType()){

			return PARTIAL_REFUSE_SIGN_CANCEL;
		}else {

			return REFUSE_SIGN_CANCEL;
		}


	}

	public static RefuseSignTypeEnum getRefuseSignTypeEnum(int type){

		for (RefuseSignTypeEnum e : RefuseSignTypeEnum.values()) {

			if (type==e.type){
				return e;
			}

		}

		//todo throw  ex

		return CANCEL;
	};




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
