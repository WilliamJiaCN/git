package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.CashSerialRecordReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageCashSerialListReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageCashSerialListRespDTO;

import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/4 17:55
 * @company 蜂网供应链管理（上海）有限公司
 */
public interface FinanceManageCashSerialService {

    Boolean insert(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO);

    /**
     * 现金流水修改
     * @param financeManageCashSerialEsDTO
     * @return
     */
    Boolean updateEs(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO);

    /**
     * 根据资金流水id查询
     * @param id
     * @return
     */
    FinanceManageCashSerialEsDTO findById(Long id);

    /**
     * 根据Id修改状态
     * @param id:主键
     * @param status:状态
     * @param companyId:公司ID
     * @return
     */
    Boolean updateStatusById(Long id,Integer status,Long companyId);

    /**
     * 根据应收或者应付ID查询资金记录
     * @param cashSerialRecordReqDTO
     * @param type 
     * @return
     */
    List<FinanceManageCashSerialEsDTO> checkFundRecord(CashSerialRecordReqDTO cashSerialRecordReqDTO);

    /**
     * 查询现金流水列表
     * @param financeManageCashSerialListReqDTO
     * @return
     */
    FinanceManageCashSerialListRespDTO getCashSerialEsList(FinanceManageCashSerialListReqDTO financeManageCashSerialListReqDTO);

    /**
     * 详情查询（现金转账）
     * @param submitBillId
     * @return
     */
    /*List<FinanceManageCashSerialListRespDTO> getDetails(Long submitBillId);*/
}
