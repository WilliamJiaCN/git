/**
 * 
 */
package com.hivescm.tms.finance.server.service.lock;

import java.util.List;

/**
 * @author  boqiang.deng
 * @date    2018年4月24日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
public interface SignLockService {

	/**
	 * 签收模块锁定运单
	 * @param signIds
	 * @return
	 */
	Boolean lockSign(List<String> waybillCodes);
	
	/**
	 * 签收模块解锁运单
	 * @param signIds
	 * @return
	 */
	Boolean unlockSign(List<String> waybillCodes);


	/**
	 * 拒签 和签收  重复提交 分布式锁
	 *
	 * 获得锁
	 *
	 * 即提交了全部签收了，就不能反悔了，即使点击返回 并且再次修改部分签收，点击提交的时候也会验证之前做过的操作
	 * @return
	 */
	Boolean signRecommitLock(Object waybillId);
	/**
	 * 拒签 和签收  重复提交 分布式锁
	 *
	 * 释放锁
	 *
	 * 即提交了全部签收了，就不能反悔了，即使点击返回 并且再次修改部分签收，点击提交的时候也会验证之前做过的操作
	 * @return
	 */
	Boolean signRecommitUnLock(Object waybillId);

	/**
	 * 送货签收防止 重复提交 分布式锁
	 * 获得锁
	 * @return
	 */
	Boolean deliverySignLock(Object DispatcherDetailId);
	/**
	 * 送货签收防止 重复提交 分布式锁
	 * 释放锁
	 * @return
	 */
	Boolean deliverySignUnLock(Object DispatcherDetailId);
	/**
	 * 送货签收取消签收防止 重复提交 分布式锁
	 * 获得锁
	 * @return
	 */
	Boolean deliveryCancelSignLock(Object signID);
	/**
	 * 送货签收取消签收防止 重复提交 分布式锁
	 * 释放锁
	 * @return
	 */
	Boolean deliveryCancelSignUnLock(Object signID);
}
