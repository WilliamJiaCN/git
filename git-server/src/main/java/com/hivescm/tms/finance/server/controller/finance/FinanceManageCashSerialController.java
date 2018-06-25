package com.hivescm.tms.finance.server.controller.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.CashSerialRecordReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageCashSerialListReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageCashSerialListRespDTO;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.interfaces.finance.FinanceManageCashSerialControllerDoc;
import com.hivescm.tms.finance.server.service.finance.FinanceManageCashSerialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/financeManageCashSerial")
public class FinanceManageCashSerialController extends BaseController implements FinanceManageCashSerialControllerDoc {

    @Autowired
    private FinanceManageCashSerialService financeManageCashSerialService;

    @Override
    public DataResult<Boolean> updateEs(Integer id) {
        return null;
    }

    /**
     * 查询资金记录
     * @param cashSerialRecordReqDTO
     * @return
     */
    @PostMapping(value="/checkFundRecord/")
    public DataResult<List<FinanceManageCashSerialEsDTO>> checkFundRecord(@RequestBody CashSerialRecordReqDTO cashSerialRecordReqDTO) {
        logger.debug("查询资金记录：{}",cashSerialRecordReqDTO);
        DataResult<List<FinanceManageCashSerialEsDTO>> result = new DataResult<>();
        try {
            List<FinanceManageCashSerialEsDTO> esDTOList = financeManageCashSerialService.checkFundRecord(cashSerialRecordReqDTO);
            if(esDTOList==null) {
                esDTOList = new ArrayList<>();
            }
            result.setResult(esDTOList);
        } catch (Exception e) {
            logger.error("查询资金记录失败",e);
            throw e;
        }
        return result;
    }

    /**
     * 查询现金流水列表
     * @param financeManageCashSerialListReqDTO
     * @return
     */
    @PostMapping("/getCashSerialEsList")
    public DataResult<FinanceManageCashSerialListRespDTO> getCashSerialEsList(@RequestBody FinanceManageCashSerialListReqDTO financeManageCashSerialListReqDTO) {
        logger.debug("查询现金流水列表：{}",financeManageCashSerialListReqDTO);
        DataResult<FinanceManageCashSerialListRespDTO> result =new DataResult<>();
        try {
            FinanceManageCashSerialListRespDTO resp = financeManageCashSerialService.getCashSerialEsList(financeManageCashSerialListReqDTO);
            result.setResult(resp);
        } catch (Exception e) {
            logger.error("查询现金流水列表失败",e);
            throw e;
        }
        return result;
    }
}
