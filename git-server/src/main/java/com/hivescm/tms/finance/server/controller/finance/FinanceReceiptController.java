package com.hivescm.tms.finance.server.controller.finance;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.tms.api.dto.es.finance.FinanceReceiptConfirmDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceReceiptCreateDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceReceiptReqDTO;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.component.finance.FinanceReceiptService;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.interfaces.finance.FinanceReceiptControllerDoc;
import com.hivescm.tms.finance.server.service.finance.EsReceiptService;
import com.hivescm.tms.utils.StringUtil;

/**
 * @author 杨彭伟
 * @date 2017-11-23 21:17
 */
@RestController
@RequestMapping("/finance/receipt")
public class FinanceReceiptController extends BaseController implements FinanceReceiptControllerDoc {
    @Autowired
    private FinanceReceiptService financeReceiptService;
    @Autowired
    private EsReceiptService esReceiptService;

    @Override
    @PostMapping("/save")
    public DataResult saveReceipt(@RequestBody FinanceReceiptCreateDTO financeReceiptCreateDTO) {
        try {
            return DataResult.success(financeReceiptService.saveReceipt(financeReceiptCreateDTO));
        } catch (Exception e) {
            return DataResult.faild(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, e.getLocalizedMessage());
        }
    }

    @Override
    @PostMapping("/confirm")
    public DataResult confirm(@RequestBody FinanceReceiptConfirmDTO financeReceiptConfirmDTO) {
        DataResult dataResult = validConfirm(financeReceiptConfirmDTO);
        if (dataResult != null) {
            return dataResult;
        }
        try {
            return DataResult.success(financeReceiptService.updateReceiptToConfirm(financeReceiptConfirmDTO));
        }catch (SystemException e) {
            return DataResult.faild(e.getErrorCode(), e.getErrorReason());
        } catch (Exception e) {
            return DataResult.faild(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, e.getLocalizedMessage());
        }
    }

    @Override
    @PostMapping("/list")
    public DataResult list(@RequestBody FinanceReceiptReqDTO financeReceiptReqDTO) {
        try {
            return DataResult.success(esReceiptService.getReceiptList(financeReceiptReqDTO));
        } catch (Exception e) {
            return DataResult.faild(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, e.getLocalizedMessage());
        }
    }

    @Override
    @GetMapping("/getReceiptAccount/{userSn}")
    public DataResult getReceiptAccount(@PathVariable String userSn) {
        try {
            return DataResult.success(financeReceiptService.getAccountList(userSn));
        } catch (Exception e) {
            return DataResult.faild(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, e.getLocalizedMessage());
        }
    }

    private DataResult validConfirm(FinanceReceiptConfirmDTO financeReceiptConfirmDTO) {
        if (financeReceiptConfirmDTO.getId() == null || financeReceiptConfirmDTO.getId().size() == 0) {
            return DataResult.faild(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "收款记录id不可为空");
        }
        if (financeReceiptConfirmDTO.getCompanyId() == null) {
            return DataResult.faild(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "公司id不可为空");
        }
        if (financeReceiptConfirmDTO.getOperatingUserId() == null) {
            return DataResult.faild(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "操作人id不可为空");
        }
        if (StringUtil.isBlank(financeReceiptConfirmDTO.getOperatingUser())) {
            return DataResult.faild(ExceptionCodeConstants.ERROR_RECEIPT_SAVE_FAIL, "操作人名称不可为空");
        }
        return null;
    }
    
    @PostMapping("/resolvePaymentData/{receiptId}")
    public DataResult<List<Long>> resolvePaymentData(@PathVariable("receiptId")Long receiptId){
    	DataResult<List<Long>> dataResult = new DataResult<List<Long>>();
    	List<Long> list = financeReceiptService.resolvePaymentData(receiptId);
    	dataResult.setResult(list);
    	return dataResult;
    }
}
