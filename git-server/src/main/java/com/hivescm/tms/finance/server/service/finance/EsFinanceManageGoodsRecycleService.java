package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsRecycleEsDTO;
import com.hivescm.tms.api.dto.es.finance.query.FinanceManageRecyleQueryDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceDeleteReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageRecycleCodeReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageRecycleConfirmQueryReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageRecycleListReqDTO;

import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 15:49
 * @company 蜂网供应链管理（上海）有限公司
 */
public interface EsFinanceManageGoodsRecycleService {

    Boolean insert(FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO);

    Boolean update(FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO);
    
    FinanceManageGoodsRecycleEsDTO findByID(Long id);

    /**
     * 查询货款回收列表
     * @param financeManageRecycleListReqDTO
     * @return
     */
    List<FinanceManageGoodsRecycleEsDTO> getEsList(FinanceManageRecycleListReqDTO financeManageRecycleListReqDTO);
    /**
     * 查询总数
     * @param financeManageRecycleListReqDTO
     * @return
     */
    Integer getEsListCount(FinanceManageRecycleListReqDTO financeManageRecycleListReqDTO);

    /**
     * 查询回收确认列表
     * @param financeManageRecycleConfirmQueryReqDTO
     * @return
     */
    List<FinanceManageGoodsRecycleEsDTO> getEsForRecyckeConfirmList(FinanceManageRecycleConfirmQueryReqDTO financeManageRecycleConfirmQueryReqDTO);
    /**
     * 查询运单号
     * @param financeManageRecycleCodeReqDTO
     * @return
     */
    List<FinanceManageGoodsRecycleEsDTO> getCodeByRecycle(FinanceManageRecycleCodeReqDTO financeManageRecycleCodeReqDTO);


    FinanceManageGoodsRecycleEsDTO getRecycleByWaybillCode(FinanceManageRecyleQueryDTO financeManageRecyleQueryDTO);

    /**
     * 根据id快速添加
     * @param id
     * @return
     */
    List<FinanceManageGoodsRecycleEsDTO> getEsByCodeForRecycle(Long id);

    /**
     *根据运单ID查询是否有审核的记录
     * @param financeDeleteReqDTO
     * @return
     */
	List<FinanceManageGoodsRecycleEsDTO> getPayStatusByCodeSign(FinanceDeleteReqDTO financeDeleteReqDTO);

	/**
	 * 通过运单ID查询是否有货款回收记录
	 * @param id
	 * @return
	 */
	FinanceManageGoodsRecycleEsDTO findByWaybillId(Long id);

    /**
     * 根据运单ID删除货款回收信息
     *
     * @param waybillId
     * @return
     */
    Boolean deleteEsByWaybillId(Long waybillId);

	/**
	 * 批量修改
	 * @param rollReceipt
	 * @return
	 */
	Boolean updateByBatch(List<FinanceManageGoodsRecycleEsDTO> rollReceipt);

    /**
     * 根据汇款回收ID集合和当前网点查询货款回收记录
     * @param idList
     * @param branchId
     * @return
     */
    List<FinanceManageGoodsRecycleEsDTO> findByIdList(List<Long> idList, Integer branchId);
}
