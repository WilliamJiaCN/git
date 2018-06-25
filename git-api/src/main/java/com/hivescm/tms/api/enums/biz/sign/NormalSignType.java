/**
 * 
 */
package com.hivescm.tms.api.enums.biz.sign;

/**
 * @author  boqiang.deng
 * @date    2018年4月2日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
public enum NormalSignType {
	NORMAL_SIGN(1,"正常签收"),
	ABNORMAL_SIGN(2,"异常签收");
	int type;
	String name;
	NormalSignType(int type,String name){
		this.type =type;
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
