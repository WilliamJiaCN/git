package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageReceiptListRespDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;

import java.util.List;

public interface FinanceManageReceiptService {

    /**
     * 批量添加应收
     * @param financeManageReceiptReqDTO
     * @return
     */
    Boolean insertBatch(SaveFinanceManageReceiptReqDTO financeManageReceiptReqDTO);

    /**
     * 查询列表
     * @param financeManageReceiptListReqDTO
     * @return
     */
    FinanceManageReceiptListRespDTO getEsList(FinanceManageReceiptListReqDTO financeManageReceiptListReqDTO);

    /**
     * 查询新增收款列表
     * @param financeManageListForReceiptReqDTO
     * @return
     */
    List<FinanceManageReceiptEsDTO> getEsListForReceipt(FinanceManageListForReceiptReqDTO financeManageListForReceiptReqDTO);

    Boolean insert(WaybillEsDTO waybillEsDTO, FinanceManageReceiptEsDTO financeManageReceiptEsDTO);

    /**
     * 根据查询条件
     * @param financeManageReceiptReqDTO
     * @return
     */
    FinanceManageReceiptEsDTO findFinanceManageReceipt(FinanceManageReceiptReqDTO financeManageReceiptReqDTO);

    /**
     * 查询来源单号
     * @param financeReceiptCodeReqDTO
     * @return
     */
    List<FinanceManageReceiptEsDTO> getCodeForReceipt(FinanceReceiptCodeReqDTO financeReceiptCodeReqDTO);

    /**
     * 根据来源单号快速添加
     * @param id
     * @return
     */
    List<FinanceManageReceiptEsDTO> getEsByCodeForReceipt(Long id);

    /**
     * 根据运单id删除应收、应付
     * @param code
     * @return
     */
	Boolean deleteByCode(FinanceDeleteReqDTO financeDeleteReqDTO);
	
	/**
     * 根据运单号检索是否有付款
     * @param code
     * @return
     */
	Boolean getPayStatusByCode(FinanceDeleteReqDTO financeDeleteReqDTO);

	/**
     * 应收审核/取消审核
     * @param verifyFinanceReceiptReqDTO
     * @return
     */
    Boolean verify(VerifyFinanceReceiptReqDTO verifyFinanceReceiptReqDTO);

    /**
     * 修改金额
     * @param receivableFinanceReceiptReqDTO
     * @return
     */
    Boolean updateAmount(ReceivableFinanceReceiptReqDTO receivableFinanceReceiptReqDTO);
    

    /**
     * 根据ID查询
     * @param financeManageReceiptReqDTO
     * @return
     */
    FinanceManageReceiptEsDTO findFinanceManageReceiptById(Long id);

    /**
     * 根据运单ID查询是可以取消签收
     * @param financeDeleteReqDTO
     * @return
     */
	Boolean getPayStatusByCodeSign(FinanceDeleteReqDTO financeDeleteReqDTO);

    /**
     * 根据运单id、运单号、付款方式删除应收信息
     * @param code
     * @return
     */
    Boolean deleteByPayway(FinanceDeleteReqDTO financeDeleteReqDTO);

    /**
     * 根据运单ID、运单号、付款方式删除应收信息
     * @param financeDeleteReqDTO
     * @return
     */
    Boolean deleteReceiptInfo(FinanceDeleteReqDTO financeDeleteReqDTO);
    /**
     * 更新签收状态
     * @param financeReceiptStatusUpdateDTO
     * @return
     */
    Boolean updateSignStatus(FinanceReceiptStatusUpdateDTO financeReceiptStatusUpdateDTO);

    /**
     * 根据中转ID查询是可以取消中转
     * @param financeDeleteReqDTO
     * @return 
     */
	Boolean getPayStatusByCodeTrans(FinanceDeleteReqDTO financeDeleteReqDTO);

	 /**
     * 根据运单ID查询是否可以修改收货人、发货人
     * @param financeForUpdateWaybill
     * @return
     */
	Boolean getStatusForUpdateWaybill(FinanceForUpdateWaybill financeForUpdateWaybill);

    /**
     * 批量添加应收
     * @param financeManageReceiptReqDTO
     * @return
     */
    Boolean insertBatchForExpClose(SaveFinanceManageReceiptReqDTO financeManageReceiptReqDTO);
}
