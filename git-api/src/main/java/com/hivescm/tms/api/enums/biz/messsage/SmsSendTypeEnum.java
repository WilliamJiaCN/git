package com.hivescm.tms.api.enums.biz.messsage;
/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
public enum SmsSendTypeEnum {

	AOTOSEND (1,"自动发送"),
	MANUALSEND (2,"手动发送");
	int type;
	String name;
	
	SmsSendTypeEnum (int type,String name){
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

