package com.hivescm.tms.api.enums.biz.stock;

/**
 * 业务节点操作
 * <p>Title: StockSNIEnum</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-05-24-19:14
 */

public class StockSNIEnum {

    public static final String LOCK_STOCK = "库存锁定";
    public static final String UNLOCKING_STOCK = "解锁库存";
    public static final String DELETE_LOCK_STOCK = "删除库存锁定";
    public static final String NOTARIZE_DEPART = "确认发车";
    public static final String CANCEL_DEPART = "取消发车";
    public static final String SINCE_ALL_SIGN = "自提全部签收";
    public static final String SINCE_PORTION_SIGN = "自提部分签收";
    public static final String SINCE_ALL_REFUSAL = "自提全部拒签";
    public static final String SINCE_CANCEL_SIGN = "自提签收取消签收";
    public static final String DELIVER_GOODS_ALL_SIGN = "送货全部签收";
    public static final String DELIVER_GOODS_PORTION_SIGN = "送货部分签收";
    public static final String DELIVER_GOODS_ALL_REFUSAL = "送货全部拒签";
    public static final String REGOOD_ALL_DELETE_SIGN = "已返库取消签收";
    public static final String NOT_REGOOD_ALL_DELETE_SIGN = "未返库取消签收";
}
