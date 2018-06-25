package com.hivescm.tms.api.enums.finance;

/**
 * 操作类型枚举
 * @author wangqianqian
 *
 */
public enum OperateTypeEnum {
	INSERT(0,"新增"),
	UPDATE(1,"修改"),
	DELETE(2,"删除");
	
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
	private OperateTypeEnum(int type, String name) {
		this.type = type;
		this.name = name;
	}
	public static OperateTypeEnum getType(int type){
		for (OperateTypeEnum ele : OperateTypeEnum.values()) {
			if (ele.getType() == type)
				return ele;
		}
		return null;
	}
}
