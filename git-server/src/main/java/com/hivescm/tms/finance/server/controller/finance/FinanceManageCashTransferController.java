package com.hivescm.tms.finance.server.controller.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.domain.Status;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.common.exception.TmsBusinessException;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.open.inner.gateway.bean.crm.StatusEnum;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashTransferEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.*;
import com.hivescm.tms.api.dto.es.receipt.response.ReceiptPrintDetailResp;
import com.hivescm.tms.api.enums.finance.ResultEnum;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.interfaces.finance.FinanceManageCashTransferControllerDoc;
import com.hivescm.tms.finance.server.service.finance.FinanceManageCashTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author LiXuan
 * @date 2017/5/10
 */
@RestController
@RequestMapping("/FinanceManageCashTransfer")
public class FinanceManageCashTransferController extends BaseController implements FinanceManageCashTransferControllerDoc {

    @Autowired
    private FinanceManageCashTransferService financeManageCashTransferService;

    /**
     * 新增现金转账
     * @param financeManageCashTransferInsertReqDTO
     * @return
     */
    @PostMapping("/insertSingle")
    @Override
    public DataResult<Boolean> insertSingle(@RequestBody FinanceManageCashTransferInsertReqDTO financeManageCashTransferInsertReqDTO){
        DataResult<Boolean> dataResult = new DataResult<>();
        dataResult.setResult(financeManageCashTransferService.insert(financeManageCashTransferInsertReqDTO));
        return  dataResult;
    }

    /**
     * 详情查询
     * @param id
     * @return
     */
    @Override
    @PostMapping("/getDetails/{id}")
    public DataResult<FinanceManageCashTransferDetailsRespDTO> getDetails(@PathVariable("id") Long id) {
        logger.debug("详情查询：{}",id);
        DataResult<FinanceManageCashTransferDetailsRespDTO> result = new DataResult<>();

        try {
            FinanceManageCashTransferDetailsRespDTO resp = financeManageCashTransferService.getDetails(id);
            result.setResult(resp);
        } catch (Exception e) {
            logger.error("详情查询失败", e);
            throw e;
        }
        return result;
    }

    /**
     * 详情主表打印
     * @param req
     * @return
     */
    @Override
    @PostMapping("/printCash")
    public DataResult<FinanceManagePrintTransferRsepDTO> printCash(@RequestBody  FinanceManagePrintCash req) {
        DataResult<FinanceManagePrintTransferRsepDTO> result = new DataResult<>();
        try {
            result.setResult(financeManageCashTransferService.printCash(req));
        } catch (TmsBusinessException e) {
            logger.error("打印转账详情失败 ，",e);
            throw ExceptionFactory.ex(e);
        }
        return result;
    }

    /**
     * 详情明细打印
     * @param req
     * @return
     */
    @Override
    @PostMapping("/printCashDetails")
    public DataResult<List<FinanceManagePrintTransferDteailsRsepDTO>> printCashDetails(@RequestBody FinanceManagePrintCashDetails req) {
        logger.debug("详情明细打印：{}",req);
        DataResult<List<FinanceManagePrintTransferDteailsRsepDTO>> result = new DataResult<>();
        List<FinanceManagePrintTransferDteailsRsepDTO> esList = new ArrayList<>();
        try {
            List<FinanceManagePrintTransferDteailsRsepDTO> es = financeManageCashTransferService.printCashDetails(req);
            if(es!=null){
                esList = es.stream().map(d->{
                    return EntityUtils.copyProperties(d,FinanceManagePrintTransferDteailsRsepDTO.class);
                }).collect(Collectors.toList());
            }
            result.setResult(esList);
        }catch (Exception e){
            logger.error("打印明细失败", e);
            throw e;
        }

        return result;
    }


    /**
     * 查询现金转账列表
     * @param financeManageCashTransferListReqDTO
     * @return
     */
    @PostMapping("/getEsList")
    public DataResult<FinanceManageCashTransferListRespDTO> getEsList(@RequestBody FinanceManageCashTransferListReqDTO financeManageCashTransferListReqDTO) {
        logger.debug("查询现金转账列表：{}",financeManageCashTransferListReqDTO);
        DataResult<FinanceManageCashTransferListRespDTO> result = new DataResult<>();

        try {
            FinanceManageCashTransferListRespDTO resp = financeManageCashTransferService.getEsList(financeManageCashTransferListReqDTO);
            result.setResult(resp);
        } catch (Exception e) {
            logger.error("查询现金转账列表失败", e);
            throw e;
        }
        return result;
    }

    /**
     * 查询转账新增列表
     * @param financeManageCashTransferAddReqDTO
     * @return
     */
    @PostMapping("/getEsForTransferAddList")
    public DataResult<List<FinanceManageCashTransferAddRespDTO>> getEsForTransferAddList(@RequestBody FinanceManageCashTransferAddReqDTO financeManageCashTransferAddReqDTO) {
        logger.debug("查询转账新增列表：{}",financeManageCashTransferAddReqDTO);
        DataResult<List<FinanceManageCashTransferAddRespDTO>> result = new DataResult<>();
        List<FinanceManageCashTransferAddRespDTO> esList = new ArrayList<>();
        try {
            List<FinanceManageCashSerialEsDTO> es = financeManageCashTransferService.getEsForTransferAddList(financeManageCashTransferAddReqDTO);
            if(es!=null){
                esList = es.stream().map(d->{
                    return EntityUtils.copyProperties(d,FinanceManageCashTransferAddRespDTO.class);
                        }).collect(Collectors.toList());
            }
            result.setResult(esList);
        }catch (Exception e){
            logger.error("查询转账新增列表失败", e);
            throw e;
        }

        return result;
    }

    /**
     * 查询来源单号
     * @param financeManageCashTransferCodeReqDTO
     * @return
     */
    @PostMapping("/getCodeByTransfer")
    public DataResult<List<FinanceManageCashTransferAddRespDTO>> getCodeByTransfer(@RequestBody FinanceManageCashTransferCodeReqDTO financeManageCashTransferCodeReqDTO) {
        logger.debug("查询来源单号：{}",financeManageCashTransferCodeReqDTO);
        DataResult<List<FinanceManageCashTransferAddRespDTO>> result = new DataResult<>();
        List<FinanceManageCashTransferAddRespDTO> esList = new ArrayList<>();
        try {
            List<FinanceManageCashSerialEsDTO> es = financeManageCashTransferService.getCodeByTransfer(financeManageCashTransferCodeReqDTO);
            if(es!=null){
                esList = es.stream().map(d->{
                    return EntityUtils.copyProperties(d,FinanceManageCashTransferAddRespDTO.class);
                }).collect(Collectors.toList());
            }
            result.setResult(esList);
        } catch (Exception e) {
            logger.error("查询来源单号失败",e);
            throw e;
        }
        return result;
    }

    /**
     * 根据id快速添加
     * @param id
     * @return
     */
    @PostMapping("/getEsByCodeForTransfer/{id}")
    public DataResult<List<FinanceManageCashTransferAddRespDTO>> getEsByCodeForTransfer(@PathVariable("id") Long id) {
        logger.debug("根据id快速添加：{}",id);
        DataResult<List<FinanceManageCashTransferAddRespDTO>> result = new DataResult<>();
        List<FinanceManageCashTransferAddRespDTO> esList = new ArrayList<>();
        try {
            List<FinanceManageCashSerialEsDTO> es = financeManageCashTransferService.getEsByCodeForTransfer(id);
            if(es!=null){
                esList = es.stream().map(d->{
                    return EntityUtils.copyProperties(d,FinanceManageCashTransferAddRespDTO.class);
                }).collect(Collectors.toList());
            }
            result.setResult(esList);
        }catch (Exception e){
            logger.error("根据id快速添加失败", e);
            throw e;
        }
        return result;
    }
    
    /**
     *@author sql
     *@Date 2018\5\15 0015
     *@param 
     *@Describe 转账提交接口
     */
    @PostMapping("/commit")
    public DataResult<Boolean> commit(@RequestBody FinanceManageCashTransferReqDTO transferAccountsReqDTO){

        DataResult<Boolean> result = new DataResult<>();
        boolean flag = false;
        try {
            flag = financeManageCashTransferService.commit(transferAccountsReqDTO);
        } catch (Exception e) {
            logger.error("转账提交接口异常{}",e.getMessage(),e);
            throw ExceptionFactory.ex(e);
        }
        if(!flag){
            result.setStatus(new Status(ResultEnum.FAIL.getType(),"提交失败!"));
            result.setResult(false);
        }
        result.setResult(flag);
        return result;

    }

    /**
     *@author sql
     *@Date 2018\5\17 0017
     *@param
     *@Describe 转账 取消提交接口
     */
    @Override
    @PostMapping("/cancelCommit")
    public DataResult<Boolean> cancelCommit(@RequestBody FinanceManageCashTransferReqDTO transferAccountsReqDTO) {
        DataResult<Boolean> result = new DataResult<>();
        boolean cancelCommitFlag = false;
        try {
            cancelCommitFlag = financeManageCashTransferService.cancelCommit(transferAccountsReqDTO);
            result.setResult(cancelCommitFlag);
        } catch (Exception e) {
            logger.error("取消提交接口异常{}",e.getMessage(),e);
            throw ExceptionFactory.ex(e);
        }
        result.setResult(cancelCommitFlag);
        return result;
    }

    /**
     *@author sql
     *@Date 2018\5\17 0017
     *@param
     *@Describe 进账接口
     */
    @Override
    @PostMapping("/intoAccount")
    public DataResult<Boolean> intoAccount(@RequestBody FinanceManageCashTransferReqDTO transferAccountsReqDTO) {

        DataResult<Boolean> result = new DataResult<>();

        boolean intoAccountFlag = false;
        try {
            intoAccountFlag = financeManageCashTransferService.intoAccount(transferAccountsReqDTO);
        } catch (Exception e) {
            logger.error("intoAccount接口异常{}",e.getMessage(),e);
            throw ExceptionFactory.ex(e);
        }
        result.setResult(intoAccountFlag);
        return result;
    }

    /**
     *@author sql
     *@Date 2018\5\18 0018
     *@param
     *@Describe 删除接口
     */
    @Override
    @PostMapping("/delete")
    public DataResult<Boolean> delete(@RequestBody FinanceManageCashTransferReqDTO transferAccountsReqDTO) {

        DataResult<Boolean> result = new DataResult<>();

        boolean flag = false;
        try {
            flag = financeManageCashTransferService.delete(transferAccountsReqDTO);
        } catch (Exception e) {
            logger.error("转账：删除接口异常{}",e.getMessage(),e);
            throw ExceptionFactory.ex(e);
        }
        result.setResult(flag);
        return result;
    }

    /**
     *@author sql
     *@Date 2018\5\18 0018
     *@param
     *@Describe  转账-修改接口
     */
    @Override
    @PostMapping("/update")
    public DataResult<Boolean> update(@RequestBody FinanceManageCashTransferReqDTO transferAccountsReqDTO) {

        DataResult<Boolean> result = new DataResult<>();

        boolean flag = false;
        try {
            flag = financeManageCashTransferService.update(transferAccountsReqDTO);
        } catch (Exception e) {
            logger.error("转账-修改接口异常{}",e.getMessage(),e);
            throw ExceptionFactory.ex(e);
        }

        result.setResult(flag);

        return result;
    }

}
