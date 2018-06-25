package com.hivescm.tms.finance.server.service.sign;

import java.util.Collection;
import java.util.List;

import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;

/**
 * 签收明细es原子接口
 * 
 * @author boqiang.deng
 * @company 蜂网供应链(上海)管理有限公司
 * @version 2018年3月23日 下午5:01:51
 * 
 */
public interface EsSignDetailsService {
	Boolean insertSignDetailsEsDTO(List<SignDetailsEsDTO> signDetailsEsDTOList);

	/**
	 * 根据运单id查签收明细
	 * 
	 * @param waybillIds
	 * @return
	 */
	List<SignDetailsEsDTO> querySignDetailsEsDTOByWaybillId(Long waybillId);

	/**
	 * 通过签收单id删除数据
	 * 
	 * @param signIds
	 * @return
	 */
	Boolean deleteBySignIds(List<Long> signIds);
	
	/**
	 * 通过签收单id删除数据
	 * 
	 * @param signIds
	 * @return
	 */
	Boolean deleteBySignId(Long signId);
	
	/**
	 * 通过签收单id查询数据
	 * 
	 * @param signIds
	 * @return
	 */
	List<SignDetailsEsDTO> queryBySignIds(Collection<Long> signIds);
	
	/**
	 * 通过签收单id查询数据
	 * 
	 * @param signIds
	 * @return
	 */
	List<SignDetailsEsDTO> queryBySignId(Long signId);
}
