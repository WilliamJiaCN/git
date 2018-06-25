package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.fare.request.BillInfoReqDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManagePayEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;

import java.util.List;
/**
 * 应付款es操作
 * @author wangqianqian
 *
 */
public interface EsFinanceManagePayService {
	/**
	 * 保存ES
	 *
	 * @param financeManagePayEsDto
	 * @return
	 */
	Boolean insertEs(FinanceManagePayEsDTO financeManagePayEsDto);
    /**
     * 批量保存ES
     *
     * @param financeManagePayEsDTOList
     * @return
     */
    Boolean insertBatchEs(List<FinanceManagePayEsDTO> financeManagePayEsDTOList);
	/**
	 * 查询列表
	 * @param financeManageListReqDTO
	 * @return
	 */
	List<FinanceManagePayEsDTO> getEsList(FinanceManageListReqDTO financeManageListReqDTO);
	/**
	 * 查询总数
	 * @param financeManageListReqDTO
	 * @return
	 */
	Integer getEsListCount(FinanceManageListReqDTO financeManageListReqDTO);
	/**
	 * 查询新增付款列表
	 * @param financeManageListForPayReqDTO
	 * @return
	 */
	List<FinanceManagePayEsDTO> getEsListForPay(FinanceManageListForPayReqDTO financeManageListForPayReqDTO);
	/**
	 * 查询代付款的来源单号
	 * @param financeSheetCodeReqDTO
	 * @return
	 */
	List<FinanceManagePayEsDTO> getCodeForPay(FinanceSheetCodeReqDTO financeSheetCodeReqDTO);
	/**
	 * 通过来源单号查询应付款项
	 * @param id
	 * @return
	 */
	List<FinanceManagePayEsDTO> getEsBySheetCode(Long id);
	/**
	 * 修改状态
	 * @param esFinance
	 * @return
	 */
	Boolean checkFeeBatch(List<FinanceManagePayEsDTO> esFinance);
	/**
	 * 查询应付款
	 * @param ids
	 * @return
	 */
	List<FinanceManagePayEsDTO> getEsListByIds(List<Long> ids);
	/**
	 * 根据单号查询是已审核
	 * @param financeDeleteReqDTO
	 * @return
	 */
	List<FinanceManagePayEsDTO> getPayStatusByCode(FinanceDeleteReqDTO financeDeleteReqDTO);
	/**
	 * 根据主键获取应付记录
	 *
	 * @param id
	 * @return
	 */
	FinanceManagePayEsDTO getEs(Long id);
	/**
	 * 批量更新应付款记录信息
	 *
	 * @param orders
	 * @return
	 */
	Boolean updateBatchEs(List<FinanceManagePayEsDTO> financeManagePayEsDtoList);
	/**
	 * 更新应付款记录信息
	 *
	 * @param financeManagePayEsDto
	 * @return
	 */
	Boolean updateEs(FinanceManagePayEsDTO financeManagePayEsDto);
	/**
	 * 根据运单号删除Es记录
	 * @param financeDeleteReqDTO
	 * @return
	 */
	boolean deleteByCode(FinanceDeleteReqDTO financeDeleteReqDTO);
	/**
	 * 根据主键删除Es记录
	 * @param id
	 * @return
	 */
	boolean deleteById(Long id);

	/**
	 * 根据来源单ID和费用类型查询应付信息
	 *
	 * @param financeManagePayQueryReqDto
	 * @return
	 */
	FinanceManagePayEsDTO getEsBySheetIdFeeType(FinanceManagePayQueryReqDTO financeManagePayQueryReqDto);

	/**
	 * 根据来源单ID和费用类型删除应付信息
	 *
	 * @param financeManagePayQueryReqDto
	 * @return
	 */
	Boolean deleteEsBySheetIdFeeType(FinanceManagePayQueryReqDTO financeManagePayQueryReqDto);

	/**
	 * 根据来源单ID和来源单号查询应付信息
	 *
	 * @param financeCancelReqDto
	 * @return
	 */
	List<FinanceManagePayEsDTO> getEsListBySheetIdListAndDataSourceCode(FinanceCancelReqDTO financeCancelReqDto);
	 /**
     * 通过批次号、单据类型、费用类型查询应付信息
     *
	  * @param reqDTO
	  * @return
     */
	 List<FinanceManagePayEsDTO> getListForFeeUpdate(BillInfoReqDTO reqDTO);

	/**
	 * 通过批次号查询应付信息
	 *
	 * @param financeQueryReqDTO
	 * @return
	 */
	List<FinanceManagePayEsDTO> getEsListByBatchCode(FinanceQueryReqDTO financeQueryReqDTO);
	/**
	 * 查询中转费
	 * @param financeDeleteReqDTO
	 * @return
	 */
	List<FinanceManagePayEsDTO> getPayStatusByTran(FinanceDeleteReqDTO financeDeleteReqDTO);
}
