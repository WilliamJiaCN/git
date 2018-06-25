package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.fare.request.BillInfoReqDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManagePayEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageListRespDTO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManagePayDO;

import java.util.List;


/*
 * 财务管理-应付表的Service接口
 * 
 * @author wenxiong.jia
 * @date 2018/4/27
 */
public interface FinanceManagePayService {

    /**
     * 新增
     *
     * @param financeManagePayReqDto
     * @return
     */
    Boolean createPayInfo(FinanceManagePayReqDTO financeManagePayReqDto);

    /**
     * 批量新增
     *
     * @param financeManagePayReqDtoList
     * @return
     */
    Boolean createBatchPayInfo(List<FinanceManagePayReqDTO> financeManagePayReqDtoList);
    /**
	 * 查询列表
	 * @param financeManageListReqDTO
	 * @return
	 */
    FinanceManageListRespDTO getEsList(FinanceManageListReqDTO financeManageListReqDTO);
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
	 * 批量审核
	 * @param financeCheckReqDTO
	 * @return
	 */
	Boolean checkFee(FinanceCheckReqDTO financeCheckReqDTO);

	/**
	 * 批量过账
	 * @param financeCheckReqDTO
	 * @return
	 */
	Boolean postConfirme(FinanceCheckReqDTO financeCheckReqDTO);

	/**
	 * 批量取消审核
	 * @param financeCheckReqDTO
	 * @return
	 */
	Boolean cancleCheckFee(FinanceCheckReqDTO financeCheckReqDTO);

	 /**
     * 根据单号查询是已审核
     * @param financeDeleteReqDTO
     * @return
     */
	Boolean getPayStatusByCode(FinanceDeleteReqDTO financeDeleteReqDTO);

	/**
	 * 根据来源单ID、费用类型删除应付信息
	 *
	 * @param financeManagePayDo
	 * @param financeManagePayQueryReqDto
	 * @return
	 */
	Boolean deletePayInfo(FinanceManagePayDO financeManagePayDo, FinanceManagePayQueryReqDTO financeManagePayQueryReqDto);

	/**
	 * 检查付款状态是否都是未审核状态，否返回false，是返回true
	 *
	 * @param financeCancelReqDto
	 * @return
	 */
	Boolean checkPaymentStatusIsNoCheck(FinanceCancelReqDTO financeCancelReqDto);

	 /**
     * 通过批次号、单据类型、费用类型查询应付信息
     *
      * @param reqDTO
     * @return
     */
     List<FinanceManagePayEsDTO> getListForFeeUpdate(BillInfoReqDTO reqDTO);
}
