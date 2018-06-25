package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceManageCashTransferEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageTransferCancelCommitEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;

import java.util.List;

/**
 * @Description:
 * @Author: LiXuan
 * @Date: Created in 2018/5/10 15:00
 * @company 蜂网供应链管理（上海）有限公司
 */
public interface EsFinanceManageCashTransferService {

    /**
     * 新增现金转账
     * @param financeManageCashTransferEsDTO
     * @return
     */
    Boolean insert(FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO);

    /**
     * 更新现金转账
     * @param financeManageCashTransferEsDTO
     * @return
     */
    Boolean update(FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO);

    /**
     * 更新现金转账 更新NULL值
     * @param financeManageTransferCancelCommitEsDTO
     * @return
     */
    Boolean updateNull(FinanceManageTransferCancelCommitEsDTO financeManageTransferCancelCommitEsDTO);


    /**
     * 查询现金转账列表
     * @param financeManageCashTransferListReqDTO
     * @return
     */
    List<FinanceManageCashTransferEsDTO> getEsList(FinanceManageCashTransferListReqDTO financeManageCashTransferListReqDTO);

    /**
     * 查询总数
     * @param financeManageCashTransferListReqDTO
     * @return
     */
    Integer getEsListCount(FinanceManageCashTransferListReqDTO financeManageCashTransferListReqDTO);

    /**
     * 查询转账新增列表
     * @param financeManageCashTransferAddReqDTO
     * @return
     */
   /* List<FinanceManageCashSerialEsDTO> getEsForTransferAddList(FinanceManageCashTransferAddReqDTO financeManageCashTransferAddReqDTO);*/
    /**
     * 查询来源单号
     * @param financeManageCashTransferCodeReqDTO
     * @return
     */
    /*List<FinanceManageCashSerialEsDTO> getCodeByTransfer(FinanceManageCashTransferCodeReqDTO financeManageCashTransferCodeReqDTO);*/


    FinanceManageCashTransferEsDTO queryListBySubmitBillId(Long submitBillId);


    /**
     * 根据id快速添加
     * @param id
     * @return
     */
    /*List<FinanceManageCashSerialEsDTO> getEsByCodeForTransfer(Long id);*/

    boolean deleteById(Long id);

    /**
     * 查询详情
     * @param id
     * @return
     */
    FinanceManageCashTransferEsDTO getDetails(Long id);

    /**
     * 打印主表
     * @param req
     * @return
     */
    FinanceManageCashTransferEsDTO printCash(FinanceManagePrintCash req);

}
