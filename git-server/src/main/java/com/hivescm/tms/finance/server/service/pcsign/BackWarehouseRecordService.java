package com.hivescm.tms.finance.server.service.pcsign;

import com.hivescm.common.domain.PagedList;
import com.hivescm.tms.api.dto.es.sign.BackWarehouseEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.SearchBankWarehouseReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SearchBackWarehouseRespDTO;

import java.util.List;

public interface BackWarehouseRecordService {

	/**
	 * 反库单查询
	 * @param searchBankWarehouseReqDTO
	 * @return
	 */
	PagedList<SearchBackWarehouseRespDTO> getBackWarehouse(SearchBankWarehouseReqDTO searchBankWarehouseReqDTO);

    List<SearchBackWarehouseRespDTO> getBackWarehouseDetail(Long dispatcherDetailId);

	BackWarehouseEsDTO getBackWarehouseBySignId(Long signId);

	boolean updateBackWareHouse(BackWarehouseEsDTO backWarehouseEsDTO);

	/**
	 * 通过反库id删除数据
	 * @param backWarehouseId
	 * @return
	 */
	Boolean deleteByBackWarehouseId(Long backWarehouseId);

	/**
	 * 通过反库id删除反库详情数据
	 * @param backWarehouseId
	 * @return
	 */
	Boolean deleteDetailsByBackWarehouseId(Long backWarehouseId);


}
