package com.hivescm.tms.api.enums.biz.sign;

/**
 * 签收类型
 *
 * @author 杨彭伟
 * @update 2017/11/23 14:51
 */
public enum SignStatusEnum {
    /**
     * 未签收
     */
    NO_SIGN(0, "未签收"),
    SIGNED(1, "全部签收"),
    PARTIAL_SIGN(2, "部分签收"),
    REFUSE_SIGN(3, "全部拒签"),
    CANCEL_SIGN(4, "作废");
    int type;
    String name;

    SignStatusEnum(int type, String name) {
        this.name = name;
        this.type = type;
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

    public static String getName(int type) {
        switch (type) {
            case 0:
                return NO_SIGN.name;
            case 1:
                return SIGNED.name;
            case 2:
                return PARTIAL_SIGN.name;
            case 3:
                return REFUSE_SIGN.name;
            case 4:
                return CANCEL_SIGN.name;
            default:
                return "";
        }
    }
}
