package com.hivescm.tms.api.enums.capacity.ltlorder;

public enum MessageEnum {

	EARPLY_PICK_GOODS(0,"尽早提货"),
	LARGE_VOLUME(1,"体积大"),
	THREE(2,"易碎品"),
	FRAGILE_CARGO(3,"来前电话"),
	FACE_SHEET(4,"须带面单"),
	CARTON(5,"请带纸箱");
	int type;
	String name;
	private MessageEnum(int type, String name) {
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
	
	 public static MessageEnum getType(int type){
			for (MessageEnum ele : MessageEnum.values()) {
				if (ele.getType() == type)
					return ele;
			}
			return null;
		}
}
