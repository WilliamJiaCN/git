package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageSerialCancelCommitEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageSerialUpdateEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageSerialdeleteEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageCashSerialListRespDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageCashTransferAddRespDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManagePrintTransferDteailsRsepDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/4 19:27
 * @company 蜂网供应链管理（上海）有限公司
 */
public interface EsFinanceManageCashSerialService {
    /**
     * 现金流水插入
     * @param financeManageCashSerialEsDTO
     * @return
     */
    boolean insert(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO);
    /**
     * 现金流水插入-批量
     * @param financeManageCashSerialEsDTO
     * @return
     */
    boolean insertBatch(List<FinanceManageCashSerialEsDTO> financeManageCashSerialList);
    /**
     * 现金流水修改
     * @param financeManageCashSerialEsDTO
     * @return
     */
    boolean updateEs(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO);
    /**
     * 现金流水修改，可修改值为空
     * @param financeManageCashSerialEsDTO
     * @return
     */
    boolean updateEsWithNull(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO);
    /**
     * 现金流水查询
     * @param id
     * @return
     */
    FinanceManageCashSerialEsDTO getEs(Long id);

    /**
     * 根据主键删除现金流水
     * @param id
     * @return
     */
    boolean deleteById(Long id);

    /**
     * 根据现金流水Id查询
     * @param id
     * @return
     */
    FinanceManageCashSerialEsDTO findById(Long id);

    /**
     * 根据id修改状态
     * @param id
     * @param status
     * @return
     */
    boolean updateStatusById(Long id, Integer status);

    /**
     * 根据应收或者应付ID查询资金记录
     * @param id
     * @param type 
     * @return
     */
    List<FinanceManageCashSerialEsDTO> checkFundRecord(Integer id, Integer type);

    /**
     * 查询现金流水列表
     * @param financeManageCashSerialListReqDTO
     * @return
     */
    List<FinanceManageCashSerialEsDTO> getCashSerialEsList(FinanceManageCashSerialListReqDTO financeManageCashSerialListReqDTO);
    /**
     * 查询总数
     * @param financeManageListReqDTO
     * @return
     */
    Integer getEsListCount(FinanceManageCashSerialListReqDTO financeManageListReqDTO);

    /**
     * 获取上期余额
     * @param financeManageCashSerialListReqDTO
     * @return
     */
    BigDecimal getOldBalance(FinanceManageCashSerialListReqDTO financeManageCashSerialListReqDTO);

    /**
     * 根据来源单号查询资金记录
     * @param cashSerialRecordReqDTO
     * @return
     */
	List<FinanceManageCashSerialEsDTO> checkFundRecordByCode(CashSerialRecordReqDTO cashSerialRecordReqDTO);

	 /**
     * 根据应收或者应付ID查询资金记录
     * @param cashSerialRecordReqDTO
     * @return
     */
	List<FinanceManageCashSerialEsDTO> checkFundRecordById(CashSerialRecordReqDTO cashSerialRecordReqDTO);

	/**
	 * 删除ES
	 * @param ids
	 * @return
	 */
	boolean deleteEsBatch(List<Long> ids);

	boolean updateBySubmitBillId(Long submitBillId,FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO);

    /**
     *@Describe 取消接口更新专用
     */
	boolean cancelCommitUpdateBySubmitBillId(Long submitBillId,FinanceManageSerialCancelCommitEsDTO financeManageSerialCancelCommitEsDTO);


	boolean updateBatchByIdContainsNull(List<FinanceManageSerialUpdateEsDTO> financeManageSerialdeleteEsDTO);


	/**
	 *@Describe 转账删除接口更新专用
	 */
	boolean deleteUpdateBySubmitBillId(Long submitBillId,FinanceManageSerialdeleteEsDTO financeManageSerialdeleteEsDTO);

    /**
     * 根据 id集合查询
     */
    List<FinanceManageCashSerialEsDTO> querySerialListByIDS(List<Long> ids);

	/**
	 * 批量更新现金流水
	 * @param cashDetails
	 * @return
	 */
	boolean updateBatch(List<FinanceManageCashSerialEsDTO> cashDetails);

    /**
     * 查询转账新增列表（现金转账）
     * @param financeManageCashTransferAddReqDTO
     * @return
     */
    List<FinanceManageCashSerialEsDTO> getEsForTransferAddList(FinanceManageCashTransferAddReqDTO financeManageCashTransferAddReqDTO);

    /**
     * 查询来源单号（现金转账）
     * @param financeManageCashTransferCodeReqDTO
     * @return
     */
    List<FinanceManageCashSerialEsDTO> getCodeByTransfer(FinanceManageCashTransferCodeReqDTO financeManageCashTransferCodeReqDTO);

    /**
     * 根据id快速添加（现金转账）
     * @param id
     * @return
     */
    List<FinanceManageCashSerialEsDTO> getEsByCodeForTransfer(Long id);

    /**
     * 查询现金流水LIST
     */
    List<FinanceManageCashSerialEsDTO> querySerialList(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO);

    /**
     * 详情查询（现金转账）
     * @param submitBillId
     * @return
     */
    List<FinanceManageCashTransferAddRespDTO> getDetails(Long submitBillId);

    /**
     * 打印明细
     */
    List<FinanceManageCashSerialEsDTO> printCashDetails(FinanceManagePrintCashDetails req);
}
