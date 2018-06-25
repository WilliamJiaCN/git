package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.query.FinanceManageReceiptQueryDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;

import java.util.List;

public interface EsFinanceManageReceiptService {


    boolean insertBatch(SaveFinanceManageReceiptReqDTO financeManageReceiptReqDTO);

    //查询列表
    List<FinanceManageReceiptEsDTO> getEsList(FinanceManageReceiptListReqDTO financeManageReceiptListReqDTO);

    //查询总数
    Integer getEsListCount(FinanceManageReceiptListReqDTO financeManageReceiptListReqDTO);

    //查询新增收款列表
    List<FinanceManageReceiptEsDTO> getEsForReceiptList(FinanceManageListForReceiptReqDTO financeManageReceiptListForPayReqDTO);

    //查询来源单号
    List<FinanceManageReceiptEsDTO> getCodeForReceipt(FinanceReceiptCodeReqDTO financeReceiptCodeReqDTO);

    //通过来源单ID查找
    List<FinanceManageReceiptEsDTO> getEsByCodeForReceipt(Long id);

    //入参为queryDTO
    List<FinanceManageReceiptEsDTO> getEsForReceipt(FinanceManageReceiptQueryDTO financeManageReceiptQueryDTO);

    FinanceManageReceiptEsDTO findFinanceManageReceipt(FinanceManageReceiptReqDTO financeManageReceiptReqDTO);
	
	boolean deleteByCode(FinanceDeleteReqDTO financeDeleteReqDTO);

    boolean deleteByPayway(FinanceDeleteReqDTO financeDeleteReqDTO);

	List<FinanceManageReceiptEsDTO> getPayStatusByCode(FinanceDeleteReqDTO financeDeleteReqDTO);

    /**
     * 根据id更新付款状态
     * @param verifyFinanceReceiptReqDTO
     * @return
     */
    boolean updateDeliveryStatus(VerifyFinanceReceiptReqDTO verifyFinanceReceiptReqDTO);

    /**
     * 修改金额
     * @param receivableFinanceReceiptEsDTOList
     * @return
     */
    boolean updateReceivableFinanceReceipt(List<ReceivableFinanceReceiptEsDTO> receivableFinanceReceiptEsDTOList);

    List<ReceivableFinanceReceiptEsDTO> findReceivableFinanceReceiptById(List<Long> idList);

    FinanceManageReceiptEsDTO findFinanceManageReceipt(Long id);
    /**
     * 应收插入
     * @param financeManageReceiptEsDTO
     * @return
     */
    boolean insertFinanceManageReceiptEsDTO(FinanceManageReceiptEsDTO financeManageReceiptEsDTO);

    /**
     * 应收根据主键删除
     * @param id
     * @return
     */
    boolean deleteById(Long id);

	boolean updateByReceipt(ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO);
    /**
     * 更新应收信息
     *
     * @param financeManagePayEsDto
     * @return
     */
    Boolean updateEs(FinanceManageReceiptEsDTO financeManageReceiptEsDTO);

    /**
     * 根据运单ID查询是可以取消签收
     * @param financeDeleteReqDTO
     * @return
     */
	List<FinanceManageReceiptEsDTO> getPayStatusByCodeSign(FinanceDeleteReqDTO financeDeleteReqDTO);

	List<FinanceManageReceiptEsDTO> getPayStatusByCodeNowPay(FinanceDeleteReqDTO financeDeleteReqDTO);

	boolean updateSignStatus(FinanceReceiptStatusUpdateDTO financeReceiptStatusUpdateDTO);

	FinanceManageReceiptEsDTO findByWaybillId(Long c);
}
