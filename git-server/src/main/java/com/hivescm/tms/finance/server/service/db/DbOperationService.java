package com.hivescm.tms.finance.server.service.db;

import java.util.List;

import com.hivescm.tms.finance.server.dao.entity.sign.SignDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDetailDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignFeeDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignRefuseDO;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2018年3月26日 下午7:01:42
* 
*/
public interface DbOperationService {

	/**
	 * 新增保存数据库
	 * @param signDO
	 * @param signDetailDOs
	 * @param signFeeDO
	 * @return
	 */
	Boolean saveSignInfo(SignDO signDO, List<SignDetailDO> signDetailDOs, SignFeeDO signFeeDO,SignRefuseDO record);
	/**
	 * 新增保存数据库
	 * @param signDO
	 * @param signDetailDOs
	 * @param signFeeDO
	 * @return
	 */
	Boolean saveSignInfo(SignDO signDO, List<SignDetailDO> signDetailDOs, SignFeeDO signFeeDO);
	
	/**
	 * 取消删除数据库
	 * @param companyId
	 * @param signIds
	 * @return
	 */
	Boolean cancelSignInfo(SignDO signDO, List<SignDetailDO> signDetailDOs, SignFeeDO signFeeDO,SignRefuseDO record,List<Long> signIds);
	
	/**
	 * 批量签收保存数据库
	 * @param signDO
	 * @param signDetailDOs
	 * @param signFeeDO
	 * @return
	 */
	Boolean saveBatchDB(List<SignDO> signDOs,List<SignDetailDO> signDetailDOs,List<SignFeeDO> signFeeDOs);
	
	/**
	 * 外发签收批量保存数据库
	 * @param signDOs
	 * @param signDetailDOs
	 * @param signRefuseDO
	 * @return
	 */
	Boolean saveOutboundBatchDB(List<SignDO> signDOs,List<SignDetailDO> signDetailDOs,List<SignRefuseDO> signRefuseDO);
	
	/**
	 * 根据签收id更新签收单状态和拒签单状态
	 * @param signDO
	 * @param signRefuseDO
	 * @param signIds
	 * @param status
	 * @return
	 */
	Boolean updateBatchSignAndRefuseSignStatusBySignId(SignDO signDO,SignRefuseDO signRefuseDO,List<Long> signIds,List<Long> refuseSignIds);
	
	/**
	 * 取消删除数据库
	 * @param companyId
	 * @param signIds
	 * @return
	 */
	Boolean delete(SignDO signDO, SignDetailDO signDetailDO, SignFeeDO signFeeDO,SignRefuseDO record,List<Long> signIds);
}
