package com.hivescm.tms.finance.server.controller.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.common.exception.TmsBusinessException;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsGrantEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageGrantListRespDTO;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.component.finance.FinanceManageGrantComponentService;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsRecycleDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManagePayDO;
import com.hivescm.tms.finance.server.interfaces.finance.FinanceManageGrantControllerDoc;
import com.hivescm.tms.finance.server.service.finance.FinanceManageGoodsGrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * @author wenxiong.jia
 * @date 2017/5/7
 */
@RestController
@RequestMapping("/FinanceManageGrant")
public class FinanceManageGrantController extends BaseController implements FinanceManageGrantControllerDoc {

    @Autowired
    private FinanceManageGoodsGrantService financeManageGoodsGrantService;
    @Autowired
    private FinanceManageGrantComponentService financeManageGrantComponentService;

    @PostMapping("/insertSingle")
    public DataResult<Boolean> insertSingle(@RequestBody FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO) {
        DataResult<Boolean> dataResult=new DataResult<>();
        dataResult.setResult(financeManageGoodsGrantService.insert(financeManageGoodsGrantEsDTO));
        return dataResult;
    }
   
    /**
     * 查询货款发放列表
     * @param financeManageGrantListReqDTO
     * @return
     */
    @PostMapping(value = "/getEsList")
    public DataResult<FinanceManageGrantListRespDTO> getEsList(@RequestBody FinanceManageGrantListReqDTO financeManageGrantListReqDTO) {
       logger.debug("查询货款发放列表：{}",financeManageGrantListReqDTO);
        DataResult<FinanceManageGrantListRespDTO> result = new DataResult<>();

        try {
            FinanceManageGrantListRespDTO resp = financeManageGoodsGrantService.getEsList(financeManageGrantListReqDTO);
            result.setResult(resp);
        } catch (Exception e) {
            logger.error("查询货款发放列表失败", e);
            throw e;
        }
        return result;
    }

    @Override
    public DataResult<List<FinanceManageGoodsGrantEsDTO>> getEsList(@RequestBody FinanceManageGrantConfirmQueryReqDTO financeManageGrantConfirmQueryReqDTO) {
        return null;
    }

    /**
     *查询发放确认列表
     * @param financeManageGrantConfirmQueryReqDTO
     * @return
     */
    @PostMapping("/getEsForGrantConfirmList")
    public DataResult<List<FinanceManageGoodsGrantEsDTO>> getEsForGrantConfirmList(@RequestBody FinanceManageGrantConfirmQueryReqDTO financeManageGrantConfirmQueryReqDTO) {
        logger.debug("查询发放确认列表：{}",financeManageGrantConfirmQueryReqDTO);
        DataResult<List<FinanceManageGoodsGrantEsDTO>> result = new DataResult<>();
        try {
            List<FinanceManageGoodsGrantEsDTO> esList = financeManageGoodsGrantService.getEsForGrantConfirmList(financeManageGrantConfirmQueryReqDTO);
            if(esList==null) {
                esList = new ArrayList<>();
            }
            result.setResult(esList);
        }catch (Exception e){
            logger.error("查询发放确认列表失败", e);
            throw e;
        }

        return result;
    }

    /**
     * 查询运单号
     * @param financeManageGrantCodeReqDTO
     * @return
     */
    @PostMapping("/getCodeByGrant")
    public DataResult<List<FinanceManageGoodsGrantEsDTO>> getCodeByGrant(@RequestBody FinanceManageGrantCodeReqDTO financeManageGrantCodeReqDTO) {
        logger.debug("查询运单号：{}",financeManageGrantCodeReqDTO);
        DataResult<List<FinanceManageGoodsGrantEsDTO>> result = new DataResult<>();
        try {
            List<FinanceManageGoodsGrantEsDTO> esList = financeManageGoodsGrantService.getCodeByGrant(financeManageGrantCodeReqDTO);
            if(esList==null) {
                esList = new ArrayList<>();
            }
            result.setResult(esList);
        } catch (Exception e) {
            logger.error("查询运单号失败",e);
            throw e;
        }
        return result;
    }

    /**
     * 根据id快速添加
     * @param id
     * @return
     */
    @PostMapping("/getEsByCodeForGrant/{id}")
    public DataResult<List<FinanceManageGoodsGrantEsDTO>> getEsByCodeForGrant(@PathVariable("id") Long id) {
        logger.debug("根据id快速添加：{}",id);
        DataResult<List<FinanceManageGoodsGrantEsDTO>> result = new DataResult<>();
        try {
            List<FinanceManageGoodsGrantEsDTO> esList = financeManageGoodsGrantService.getEsByCodeForGrant(id);
            if(esList==null) {
                esList = new ArrayList<>();
            }
            result.setResult(esList);
        }catch (Exception e){
            logger.error("根据id快速添加失败", e);
            throw e;
        }
        return result;
    }

    /**
     * 货款发放确认
     *
     * @param financeManageGrantConfirmReqDto
     * @return
     */
    @PostMapping("/grantConfirm")
    public DataResult<Boolean> grantConfirm(@RequestBody FinanceManageGrantConfirmReqDTO financeManageGrantConfirmReqDto) {
        logger.debug("货款发放确认{}", financeManageGrantConfirmReqDto);
        DataResult<Boolean> result = new DataResult<>();
        try {
            boolean isSuccess = financeManageGrantComponentService.grantConfirm(financeManageGrantConfirmReqDto);
            result.setResult(isSuccess);
        } catch (TmsBusinessException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("货款发放确认失败", e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_GRANT_GRANTCONFIRM, "货款发放确认异常");
        }
        return result;
    }

    /**
     * 货款发放取消
     *
     * @param financeManagePayCancelReqDto
     * @return
     */
    @PostMapping("/cancelGrantPay")
    public DataResult<Boolean> cancelGrantPay(@RequestBody FinanceManagePayCancelReqDTO financeManagePayCancelReqDto) {
        logger.debug("货款发放取消{}", financeManagePayCancelReqDto);
        DataResult<Boolean> result = new DataResult<>();
        try {
            FinanceManageGoodsGrantDO financeManageGoodsGrantDo = new FinanceManageGoodsGrantDO();
            FinanceManageCashSerialDO financeManageCashSerialDo = new FinanceManageCashSerialDO();
            FinanceManageGoodsRecycleDO financeManageGoodsRecycleDO = new FinanceManageGoodsRecycleDO();
            financeManageGoodsGrantDo.setCompanyId(0L);//随便给一个值，作为参数传递用，实际用ES中的值
            financeManageCashSerialDo.setCompanyId(0L);
            financeManageGoodsRecycleDO.setCompanyId(0L);
            boolean isSuccess = financeManageGrantComponentService.cancelGrantPay(financeManageGoodsGrantDo, financeManageCashSerialDo, financeManageCashSerialDo, financeManageGoodsRecycleDO, financeManagePayCancelReqDto);
            result.setResult(isSuccess);
        } catch (TmsBusinessException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("货款发放取消失败", e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_GRANT_GRANTCANCEL, "货款发放取消异常");
        }
        return result;
    }

    /**
     * 货款手续费收款取消
     *
     * @param financeManagePayCancelReqDto
     * @return
     */
    @PostMapping("/cancelGoodsFeeReceipt")
    public DataResult<Boolean> cancelGoodsFeeReceipt(@RequestBody FinanceManagePayCancelReqDTO financeManagePayCancelReqDto) {
        logger.debug("货款手续费收款取消{}", financeManagePayCancelReqDto);
        DataResult<Boolean> result = new DataResult<>();
        try {
            FinanceManageGoodsGrantDO financeManageGoodsGrantDo = new FinanceManageGoodsGrantDO();
            FinanceManageCashSerialDO financeManageCashSerialDo = new FinanceManageCashSerialDO();
            financeManageGoodsGrantDo.setCompanyId(0L);//随便给一个值，作为参数传递用，实际用ES中的值
            financeManageCashSerialDo.setCompanyId(0L);
            boolean isSuccess = financeManageGrantComponentService.cancelGoodsFeeReceipt(financeManageGoodsGrantDo, financeManageCashSerialDo, financeManageCashSerialDo, financeManagePayCancelReqDto);
            result.setResult(isSuccess);
        } catch (TmsBusinessException ex) {
            throw ex;
        } catch (Exception e) {
            logger.error("货款手续费收款取消失败", e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_GRANT_GRANTCANCEL, "货款手续费收款取消异常");
        }
        return result;
    }

    /**
     * 收单确认
     *
     * @param financeManageGrantCommonReqDto
     * @return
     */
    @PostMapping("/receiveConfirm")
    public DataResult<Boolean> receiveConfirm(@RequestBody FinanceManageGrantCommonReqDTO financeManageGrantCommonReqDto) {
        logger.debug("收单确认{}", financeManageGrantCommonReqDto);
        DataResult<Boolean> result = new DataResult<>();
        try {
            boolean isSuccess = financeManageGoodsGrantService.receiveConfirm(financeManageGrantCommonReqDto);
            result.setResult(isSuccess);
        } catch (Exception e) {
            logger.error("收单确认失败", e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_GRANT_RECEIVECONFIRM, "收单确认异常");
        }
        return result;
    }

    /**
     * 进账确认
     *
     * @param financeManageGrantCommonReqDto
     * @return
     */
    @Override
    @PostMapping("/incomeConfirm")
    public DataResult<Boolean> incomeConfirm(@RequestBody FinanceManageGrantCommonReqDTO financeManageGrantCommonReqDto) {
        logger.debug("进账确认{}", financeManageGrantCommonReqDto);
        DataResult<Boolean> result = new DataResult<>();
        try {
            boolean isSuccess = financeManageGrantComponentService.incomeConfirm(financeManageGrantCommonReqDto);
            result.setResult(isSuccess);
        } catch (Exception e) {
            logger.error("进账确认失败", e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_GRANT_RECEIPTCONFIRM, "进账确认异常");
        }
        return result;
    }
}
