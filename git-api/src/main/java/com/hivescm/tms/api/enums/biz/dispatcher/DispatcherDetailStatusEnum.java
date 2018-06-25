package com.hivescm.tms.api.enums.biz.dispatcher;


/**
 * @author boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/11
 */
public enum DispatcherDetailStatusEnum {

    ACCEPTED(2, "已接单"),
    LOADED(3, "已装货"),
    DEPARTED(5, "已发车"),
    SIGNED(6, "已签收"),
    IN_STORAGE(7, "已入库"),
	PARTIAL_SIGN(9,"部分签收"),
	REFUSE_SIGN(10,"拒绝签收"),
  COMPLETE(11, "已完成"),
  UN_COMPLETE(12, "未完成"),
  CANCEL(13, "已取消");

    int type;
    String name;

    DispatcherDetailStatusEnum(int type, String name) {
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

