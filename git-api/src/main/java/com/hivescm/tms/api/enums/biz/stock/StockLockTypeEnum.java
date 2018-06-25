package com.hivescm.tms.api.enums.biz.stock;

/**
 * <p>Title: StockLockEnum</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 * 库存业务节点枚举
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-05-11-20:16
 */

public enum  StockLockTypeEnum {

    // 锁定库存 解锁库存 删除锁定库存 发车确认 取消发车 到货入库
    // 自提签收 送货签收
    // 部分签收 全部拒签 全部签收  取消签收
    // 已返库 未返库 已返库取消签收,部分签收取消签收,全部签收取消签收
    // 派送失败


    // 锁定库存         lockingInventoryForWard
    LOCK_STOCK,
    //扣减库存（解锁库存）deductionLockingStock
    UNLOCKING_STOCK,
    //取消锁定库存（锁定变可用，在库库存不变）lockingStockFailure
    DELETE_LOCK_STOCK,
    //扣减库存（发车确认）    deductionLockingStock
    CONFIRM_DEPART,
    //取消扣减库存（发车确认失败）    unLockStockLocking
    CONFIRM_DEPART_FAIL,
    //取消扣减库存（取消发车）    stockPositionNameBack
    CANCEL_DEPART,
    //取消发车失败    stockPositionFailure
    CANCEL_DEPART_FAIL,
    //自提签收（包括自提全部签收,自提部分签收，自提全部拒签）deductionStock
    SELF_REFERENCE,
    //自提签收取消签收 updateSignEscStockNum
    SELF_REFERENCE_CANCEL,
    //送货签收 包括送货全部签收,送货部分签收,送货全部拒签(返库/未返库) signStockDetail
    DELIVERY_SIGN,
    //已返库取消签收  updateSignStockEscNum
    REGOOD_ALL_DELETE_SIGN,
    // 未返库取消签收  updateSignNotArrived
    NOT_REGOOD_ALL_DELETE_SIGN,
    //派送失败 signStockDetail
    DISPATCH_FAIL,

    // 解除拒签件数  updateSignStockNum
    UNLOCK_FEFUSE_SIGN,
    //到货入库
    ARRIVAL_CONFIRM,
    //单票添加
    SINGLE_VOTE,
    //生成库存
    CREATE_STOCK,
    //修改库存
    UPDATE_STOCK;
}
