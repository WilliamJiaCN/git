package com.hivescm.tms.finance.server.service.sign;

import java.util.List;

import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;

/**
* 费用es原子接口
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2018年3月23日 下午5:08:12
* 
*/
public interface EsSignFeeService {
	 Boolean insertSignFeeEsDTO(SignFeeEsDTO signFeeEsDTO);
	 /**
	  * 根据运单id查询费用表
	  * @param waybillId
	  * @return
	  */
	 List<SignFeeEsDTO> querySignFeeEsDTOByWaybillId(Long waybillId);

	/**
	 * 通过签收单id删除数据
	 * 
	 * @param signIds
	 * @return
	 */
	Boolean deleteBySignIds(List<Long> signIds);
	
	/**
	 * 批量保存es费用
	 * @param signFeeEsDTO
	 * @return
	 */
    Boolean insertBatchSignFeeEs(List<SignFeeEsDTO> signFeeEsDTOs);
    
    /**
	  * 根据运单id查询费用表
	  * @param waybillId
	  * @return
	  */
	 List<SignFeeEsDTO> querySignFeeEsDTOByWaybillId(List<Long> waybillId);
	 
	 /**
	  * 根据签收id查询费用表
	  * @param waybillId
	  * @return
	  */
	 SignFeeEsDTO querySignFeeEsDTOBySignId(Long signId);
	/**
	 * @param signId
	 * @return
	 */
	Boolean deleteBySignId(Long signId);
    
}
