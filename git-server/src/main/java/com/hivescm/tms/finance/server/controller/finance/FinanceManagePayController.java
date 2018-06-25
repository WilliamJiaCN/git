package com.hivescm.tms.finance.server.controller.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.common.exception.TmsBusinessException;
import com.hivescm.tms.api.dto.es.fare.request.BillInfoReqDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManagePayEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageListRespDTO;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.component.finance.FinanceManagePayComponentService;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManagePayDO;
import com.hivescm.tms.finance.server.interfaces.finance.FinanceManagePayControllerDoc;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManagePayService;
import com.hivescm.tms.finance.server.service.finance.FinanceManagePayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wenxiong.jia
 * @date 2017/4/27
 */
@RestController
@RequestMapping("/financemanagepay")
public class FinanceManagePayController extends BaseController implements FinanceManagePayControllerDoc {

    @Autowired
    private FinanceManagePayService financeManagePayService;
    @Autowired
    private EsFinanceManagePayService esFinanceManagePayService;
    @Autowired
    private FinanceManagePayComponentService financeManagePayComponentService;

    @Override
    @PostMapping(value = "/createPayInfo")
    public DataResult<Boolean> createPayInfo(@RequestBody FinanceManagePayReqDTO financeManagePayReqDto) {
        logger.debug("创建应付款记录{}", financeManagePayReqDto);
        DataResult<Boolean> result = new DataResult<>();
        try {
            boolean isSuccess = financeManagePayService.createPayInfo(financeManagePayReqDto);
            result.setResult(isSuccess);
        } catch (Exception e) {
            logger.error("创建应付款记录失败，", e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_PAY_INSERT, "创建应付款记录异常：" + financeManagePayReqDto);
        }
        return result;
    }

    @PostMapping(value = "/createBatchPayInfo")
    public DataResult<Boolean> createBatchPayInfo(@RequestBody List<FinanceManagePayReqDTO> financeManagePayReqDtoList) {
        logger.debug("批量创建应付款记录{}", financeManagePayReqDtoList);
        DataResult<Boolean> result = new DataResult<>();
        try {
            boolean isSuccess = financeManagePayService.createBatchPayInfo(financeManagePayReqDtoList);
            result.setResult(isSuccess);
        } catch (Exception e) {
            logger.error("批量创建应付款记录失败，", e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_PAY_INSERT, "批量创建应付款记录异常：" + financeManagePayReqDtoList);
        }
        return result;
    }

    /**
     * 查询列表
     *
     * @param financeManageListReqDTO
     * @return
     */
    @PostMapping(value = "/getList")
    public DataResult<FinanceManageListRespDTO> getEsList(@RequestBody FinanceManageListReqDTO financeManageListReqDTO) {
        logger.debug("查询应付费用列表：{}", financeManageListReqDTO);
        DataResult<FinanceManageListRespDTO> result = new DataResult<>();
        try {
            FinanceManageListRespDTO resp = financeManagePayService.getEsList(financeManageListReqDTO);
            result.setResult(resp);
        } catch (Exception e) {
            logger.error("查询应付费用列表失败", e);
            throw e;
        }
        return result;
    }

    /**
     * 查询新增付款列表
     *
     * @param financeManageListForPayReqDTO
     * @return
     */
    @PostMapping(value = "/getEsListForPay")
    public DataResult<List<FinanceManagePayEsDTO>> getEsListForPay(@RequestBody FinanceManageListForPayReqDTO financeManageListForPayReqDTO) {
        logger.debug("查询新增付款列表：{}", financeManageListForPayReqDTO);
        DataResult<List<FinanceManagePayEsDTO>> result = new DataResult<>();
        try {
            List<FinanceManagePayEsDTO> esList = financeManagePayService.getEsListForPay(financeManageListForPayReqDTO);
            if(esList==null) {
                esList = new ArrayList<>();
            }
            result.setResult(esList);
        } catch (Exception e) {
            logger.error("查询新增付款列表失败", e);
            throw e;
        }
        return result;
    }

    /**
     * 查询来源单号
     *
     * @param financeSheetCodeReqDTO
     * @return
     */
    @PostMapping(value = "/getCodeForPay")
    public DataResult<List<FinanceManagePayEsDTO>> getCodeForPay(@RequestBody FinanceSheetCodeReqDTO financeSheetCodeReqDTO) {
        logger.debug("查询来源单号：{}", financeSheetCodeReqDTO);
        DataResult<List<FinanceManagePayEsDTO>> result = new DataResult<>();
        try {
            List<FinanceManagePayEsDTO> esList = financeManagePayService.getCodeForPay(financeSheetCodeReqDTO);
            if(esList==null) {
                esList = new ArrayList<>();
            }
            result.setResult(esList);
        } catch (Exception e) {
            logger.error("查询来源单号失败", e);
            throw e;
        }
        return result;
    }

    /**
     * 通过id快速添加
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/getEsBySheetCode/{id}")
    public DataResult<List<FinanceManagePayEsDTO>> getEsBySheetCode(@PathVariable("id") Long id) {
        logger.debug("通过id快速添加：{}", id);
        DataResult<List<FinanceManagePayEsDTO>> result = new DataResult<>();
        try {
            List<FinanceManagePayEsDTO> esList = financeManagePayService.getEsBySheetCode(id);
            if(esList==null) {
                esList = new ArrayList<>();
            }
            result.setResult(esList);
        } catch (Exception e) {
            logger.error("通过id快速添加", e);
            throw e;
        }
        return result;
    }

    /**
     * 批量审核
     *
     * @param financeCheckReqDTO
     * @return
     */
    @PostMapping(value = "/checkFee")
    public DataResult<Boolean> checkFee(@RequestBody FinanceCheckReqDTO financeCheckReqDTO) {
        logger.debug("批量审核应付款项：{}", financeCheckReqDTO);
        Boolean dto = null;
        try {
            dto = financeManagePayService.checkFee(financeCheckReqDTO);
        } catch (Exception e) {
            logger.error("批量审核应付款项失败", e);
            throw e;
        }
        return DataResult.success(dto, Boolean.class);
    }

    /**
     * 批量取消审核
     *
     * @param financeCheckReqDTO
     * @return
     */
    @PostMapping(value = "/cancleCheckFee")
    public DataResult<Boolean> cancleCheckFee(@RequestBody FinanceCheckReqDTO financeCheckReqDTO) {
        logger.debug("批量取消审核应付款项：{}", financeCheckReqDTO);
        Boolean dto = null;
        try {
            dto = financeManagePayService.cancleCheckFee(financeCheckReqDTO);
        } catch (Exception e) {
            logger.error("批量取消审核应付款项失败", e);
            throw e;
        }
        return DataResult.success(dto, Boolean.class);
    }

    /**
     * 过账
     *
     * @param financeCheckReqDTO
     * @return
     */
    @PostMapping(value = "/postConfirme")
    public DataResult<Boolean> postConfirme(@RequestBody FinanceCheckReqDTO financeCheckReqDTO) {
        logger.debug("批量过账应付款项：{}", financeCheckReqDTO);
        Boolean dto = null;
        try {
            dto = financeManagePayService.postConfirme(financeCheckReqDTO);
        } catch (Exception e) {
            logger.error("批量过账应付款项失败", e);
            throw e;
        }
        return DataResult.success(dto, Boolean.class);
    }

    /**
     * 根据id和来源单号查询是否已审核
     *
     * @param financeDeleteReqDTO
     * @return
     */
    @PostMapping("/getPayStatusByCode")
    public DataResult<Boolean> getPayStatusByCode(@RequestBody FinanceDeleteReqDTO financeDeleteReqDTO) {
        logger.debug("根据id和来源单号查询是否已审核：{}", financeDeleteReqDTO);
        Boolean dto = null;
        try {
            dto = financeManagePayService.getPayStatusByCode(financeDeleteReqDTO);
        } catch (Exception e) {
            logger.error("根据id和来源单号查询是否已审核失败", e);
            throw e;
        }
        return DataResult.success(dto, Boolean.class);
    }

    @Override
    @PostMapping(value = "/payConfirm")
    public DataResult<Boolean> payConfirm(@RequestBody FinanceManagePayConfirmReqDTO financeManagePayConfirmReqDto) {
        logger.debug("创建付款单{}", financeManagePayConfirmReqDto);
        DataResult<Boolean> result = new DataResult<>();
        try {
            boolean isSuccess = financeManagePayComponentService.payConfirm(financeManagePayConfirmReqDto);
            result.setResult(isSuccess);
        } catch (TmsBusinessException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("创建付款单失败", e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_PAY_PAYCONFIRM, "创建付款单异常");
        }
        return result;
    }

    @Override
    @PostMapping(value = "/cancelPay")
    public DataResult<Boolean> cancelPay(@RequestBody FinanceManagePayCancelReqDTO financeManagePayCancelReqDto) {
        logger.debug("取消付款{}", financeManagePayCancelReqDto);
        DataResult<Boolean> result = new DataResult<>();
        try {
            FinanceManagePayDO financeManagePayDo = new FinanceManagePayDO();
            FinanceManageCashSerialDO financeManageCashSerialDo = new FinanceManageCashSerialDO();
            financeManagePayDo.setCompanyId(0L);//随便给一个值，作为参数传递用，实际用ES中的值
            financeManageCashSerialDo.setCompanyId(0L);
            boolean isSuccess = financeManagePayComponentService.cancelPay(financeManagePayDo, financeManageCashSerialDo, financeManageCashSerialDo, financeManagePayCancelReqDto);
            result.setResult(isSuccess);
        } catch (TmsBusinessException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("取消付款失败", e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_PAY_PAYCANCEL, "取消付款异常");
        }
        return result;
    }
    @Override
    @PostMapping(value = "/checkPaymentStatusIsNoCheck")
    public DataResult<Boolean> checkPaymentStatusIsNoCheck(@RequestBody FinanceCancelReqDTO financeCancelReqDto) {
        DataResult<Boolean> result = new DataResult<>();
        boolean isSuccess = financeManagePayService.checkPaymentStatusIsNoCheck(financeCancelReqDto);
        result.setResult(isSuccess);
        return result;
    }

    /**
     * 通过批次号、单据类型、费用类型查询应付信息
     *
     * @param reqDTO
     * @return
     */
    @PostMapping(value = "/getListForFeeUpdate")
    public DataResult<List<FinanceManagePayEsDTO>> getListForFeeUpdate(@RequestBody BillInfoReqDTO reqDTO) {
        logger.debug("通过批次号、单据类型、费用类型查询应付信息：{}", reqDTO);
        DataResult<List<FinanceManagePayEsDTO>> result = new DataResult<>();
        try {
            List<FinanceManagePayEsDTO> esList = financeManagePayService.getListForFeeUpdate(reqDTO);
            if(esList==null) {
                esList = new ArrayList<>();
            }
            result.setResult(esList);
        } catch (Exception e) {
            logger.error("通过批次号、单据类型、费用类型查询应付信息失败", e);
            throw e;
        }
        return result;
    }

    @Override
    @PostMapping(value = "/getListByBatchCode")
    public DataResult<List<FinanceManagePayEsDTO>> getListByBatchCode(@RequestBody FinanceQueryReqDTO financeQueryReqDTO) {
        DataResult<List<FinanceManagePayEsDTO>> result = new DataResult<>();
        result.setResult(esFinanceManagePayService.getEsListByBatchCode(financeQueryReqDTO));
        return result;
    }
}
