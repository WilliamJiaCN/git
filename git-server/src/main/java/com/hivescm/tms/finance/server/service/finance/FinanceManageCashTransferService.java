package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashTransferEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageCashTransferDetailsRespDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageCashTransferListRespDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManagePrintTransferDteailsRsepDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManagePrintTransferRsepDTO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description:
 * @Author: LiXuan
 * @Date: Created in 2018/5/10 15:00
 * @company 蜂网供应链管理（上海）有限公司
 */
public interface FinanceManageCashTransferService {

    /**
     * 新增现金转账
     * @param financeManageCashTransferInsertReqDTO
     * @return
     */
    Boolean insert(FinanceManageCashTransferInsertReqDTO financeManageCashTransferInsertReqDTO);

    /**
     * 更新现金转账
     * @param financeManageCashTransferEsDTO
     * @return
     */
    Boolean update(FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO);

    /**
     * 查询现金转账列表
     * @param financeManageCashTransferListReqDTO
     * @return
     */
    FinanceManageCashTransferListRespDTO getEsList(FinanceManageCashTransferListReqDTO financeManageCashTransferListReqDTO);

    /**
     * 查询转账新增列表
     * @param financeManageCashTransferAddReqDTO
     * @return
     */
    List<FinanceManageCashSerialEsDTO> getEsForTransferAddList(FinanceManageCashTransferAddReqDTO financeManageCashTransferAddReqDTO);
    /**
     * 查询来源单号
     * @param financeManageCashTransferCodeReqDTO
     * @return
     */
    List<FinanceManageCashSerialEsDTO> getCodeByTransfer(FinanceManageCashTransferCodeReqDTO financeManageCashTransferCodeReqDTO);

    /**
     * 根据id快速添加
     * @param id
     * @return
     */
    List<FinanceManageCashSerialEsDTO> getEsByCodeForTransfer(Long id);

    /**
     *@author sql
     *@Date 2018\5\16 0016
     *@param
     *@Describe 转账提交
     */
    boolean commit(FinanceManageCashTransferReqDTO transferAccountsReqDTO);
    /**
     *@author sql
     *@Date 2018\5\17 0017
     *@param
     *@Describe 取消提交
     */
    boolean cancelCommit(FinanceManageCashTransferReqDTO transferAccountsReqDTO);

    /**
     *@author sql
     *@Date 2018\5\17 0017
     *@param
     *@Describe 进账接口
     */
    boolean intoAccount(FinanceManageCashTransferReqDTO transferAccountsReqDTO);


    /**
     *@author sql
     *@Date 2018\5\18 0018
     *@param
     *@Describe 转账：删除接口
     */
    boolean delete(FinanceManageCashTransferReqDTO transferAccountsReqDTO);


    boolean update(FinanceManageCashTransferReqDTO transferAccountsReqDTO);

    /**
     * 详情查询
     * @param id
     * @return
     */
    FinanceManageCashTransferDetailsRespDTO getDetails(Long id);

    /**
     * 打印主表
     * @param req
     * @return
     */
    FinanceManagePrintTransferRsepDTO printCash(FinanceManagePrintCash req);

    /**
     * 打印明细
     * @param req
     * @return
     */
    List<FinanceManagePrintTransferDteailsRsepDTO> printCashDetails(FinanceManagePrintCashDetails req);
}
