package com.hivescm.tms.api.enums.biz.finance;

/**
 * 结算方式
 * @author 杨彭伟
 * @date 2017-11-22 20:09
 */
public enum SettlementModeEnum {
    /**
     *
     */
    NONE(-1, "无"),
    CASH(1, "现金"),
    QR_CODE(2, "扫码"),
    TRANSFER(3, "银行转账");
    int type;
    String name;

    SettlementModeEnum(int type, String name) {
        this.name = name;
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static SettlementModeEnum getSettlementMode(int type) {
        switch (type) {
            case -1:
                return NONE;
            case 1:
                return CASH;
            case 2:
                return QR_CODE;
            case 3:
                return TRANSFER;
            default:
                return null;
        }
    }

    public static String getName(int type) {
        switch (type) {
            case -1:
                return NONE.name;
            case 1:
                return CASH.name;
            case 2:
                return QR_CODE.name;
            case 3:
                return TRANSFER.name;
            default:
                return "";
        }
    }
}
