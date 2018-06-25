package com.hivescm.tms.api.enums.biz.dispatcher;

/**
 * 派车单费用分摊类型
 * 1、按计费重量分摊、
 * 2、按件数分摊
 * 3、按重量分摊
 * 4、按体积分摊
 * 5、按票数分摊
 * 6、手工分摊
 *
 * @author 李洪春
 * @since 2017/8/15 17:16
 */
public enum DispatcherFeeShareTypeEnum {
    BY_CHARGED_WEIGHT(1, "按计费重量分摊"),
    BY_PACKAGE_NUM(2, "按件数分摊"),
    BY_WEIGHT(3, "按重量分摊"),
    BY_VOLUME(4, "按体积分摊"),
    BY_WAYBILL(5, "按票数分摊"),
    BY_MANUAL(6, "手工分摊"),
    BY_OUTPUT(7, "按产值分摊");
	
    int type;
    String name;

    DispatcherFeeShareTypeEnum(int type, String name) {
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

    public static DispatcherFeeShareTypeEnum getByType(int type){
        for(DispatcherFeeShareTypeEnum dis:DispatcherFeeShareTypeEnum.values()){
            if(dis.getType()==type){
                return dis;
            }
        }
        return null;
    }

}
