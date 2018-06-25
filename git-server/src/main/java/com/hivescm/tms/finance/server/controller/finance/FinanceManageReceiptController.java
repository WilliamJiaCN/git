package com.hivescm.tms.finance.server.controller.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptUpdateSingleDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageReceiptListRespDTO;
import com.hivescm.tms.api.enums.finance.PayWayEnum;
import com.hivescm.tms.finance.server.component.finance.FinanceManageReceiptComponentService;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.interfaces.finance.FinanceManageReceiptControllerDoc;
import com.hivescm.tms.finance.server.service.finance.FinanceManageReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/financeManageReceipt")
public class FinanceManageReceiptController extends BaseController implements FinanceManageReceiptControllerDoc{

    @Autowired
    private FinanceManageReceiptService financeManageReceiptService;
    
    @Autowired
    private FinanceManageReceiptComponentService financeManageReceiptComponentService;

//    @PostMapping("/insertSingle")
//    public DataResult<Boolean> insertSingle(@RequestBody FinanceManageReceiptEsDTO financeManageReceiptEsDTO) {
//        DataResult<Boolean> dataResult=new DataResult<>();
//        dataResult.setResult(financeManageReceiptService.insert(financeManageReceiptEsDTO));
//        return dataResult;
//    }

    @PostMapping("/insertBatch")
    public DataResult<Boolean> insertBatch(@RequestBody SaveFinanceManageReceiptReqDTO saveFinanceManageReceiptReqDTO) {
        DataResult<Boolean> dataResult=new DataResult<>();
        dataResult.setResult(financeManageReceiptService.insertBatch(saveFinanceManageReceiptReqDTO));
        return dataResult;
    }

    @PostMapping("/updateBatch")
    public DataResult updateBatch(FinanceManageReceiptUpdateSingleDTO financeManageReceiptUpdateSingleDTO) {
        return null;
    }

    /**
     * 查询列表
     * @param financeManageReceiptListReqDTO
     * @return
     */
    @PostMapping("/getEsList")
    public DataResult<FinanceManageReceiptListRespDTO> getEsList(@RequestBody FinanceManageReceiptListReqDTO financeManageReceiptListReqDTO) {

        logger.debug("查询应收费用列表：{}",financeManageReceiptListReqDTO);
        DataResult<FinanceManageReceiptListRespDTO> result = new DataResult<>();
        try {
            FinanceManageReceiptListRespDTO resp = financeManageReceiptService.getEsList(financeManageReceiptListReqDTO);
            result.setResult(resp);
        }catch (Exception e){
            logger.error("查询应收费用列表失败", e);
            throw e;
        }

        return result;
    }

    /**
     * 查询新增收款列表
     * @param financeManageListForReceiptReqDTO
     * @return
     */
    @PostMapping("/getEsForReceiptList")
    public DataResult<List<FinanceManageReceiptEsDTO>> getEsForReceiptList(@RequestBody FinanceManageListForReceiptReqDTO financeManageListForReceiptReqDTO) {
        logger.debug("查询新增收款列表：{}",financeManageListForReceiptReqDTO);
        DataResult<List<FinanceManageReceiptEsDTO>> result = new DataResult<>();
        try {
        	List<FinanceManageReceiptEsDTO> esList = financeManageReceiptService.getEsListForReceipt(financeManageListForReceiptReqDTO);
        	if(esList==null) {
        		esList = new ArrayList<>();
        	}
            result.setResult(esList);
        }catch (Exception e){
            logger.error("查询新增收款列表", e);
            throw e;
        }

        return result;
    }

    /**
     * 查询来源单号
     * @param financeReceiptCodeReqDTO
     * @return
     */
    @PostMapping("/getCodeByReceipt")
    public DataResult<List<FinanceManageReceiptEsDTO>> getCodeByReceipt(@RequestBody FinanceReceiptCodeReqDTO financeReceiptCodeReqDTO) {
        logger.debug("查询来源单号：{}",financeReceiptCodeReqDTO);
        DataResult<List<FinanceManageReceiptEsDTO>> result = new DataResult<>();
        try {
        	List<FinanceManageReceiptEsDTO> esList = financeManageReceiptService.getCodeForReceipt(financeReceiptCodeReqDTO);
            if(esList==null) {
        		esList = new ArrayList<>();
        	}
            result.setResult(esList);
        } catch (Exception e) {
            logger.error("查询来源单号失败",e);
            throw e;
        }
        return result;
    }

    @PostMapping("/findFinanceManageReceipt")
    public DataResult<FinanceManageReceiptEsDTO> findFinanceManageReceipt(@RequestBody FinanceManageReceiptReqDTO financeManageReceiptReqDTO){
        DataResult<FinanceManageReceiptEsDTO> dataResult=new DataResult<>();
        dataResult.setResult(financeManageReceiptService.findFinanceManageReceipt(financeManageReceiptReqDTO));
        return dataResult;
    }

    /**
     * 应收审核/取消审核
     * @param verifyFinanceReceiptReqDTO
     * @return
     */
    @PostMapping("/verify")
    public DataResult<Boolean> verify(@RequestBody VerifyFinanceReceiptReqDTO verifyFinanceReceiptReqDTO) {
        DataResult<Boolean> dataResult=new DataResult<>();
        dataResult.setResult(financeManageReceiptService.verify(verifyFinanceReceiptReqDTO));
        return dataResult;
    }

    /**
     * 根据来源单号快速添加
     * @param code
     * @return
     */
    @PostMapping("/getEsByCodeForReceipt/{id}")
    public DataResult<List<FinanceManageReceiptEsDTO>> getEsByCodeForReceipt(@PathVariable("id") Long id) {
        logger.debug("通过来源单号快速添加：{}",id);
        DataResult<List<FinanceManageReceiptEsDTO>> result = new DataResult<>();
        try {
        	List<FinanceManageReceiptEsDTO> esList = financeManageReceiptService.getEsByCodeForReceipt(id);
        	if(esList==null) {
        		esList = new ArrayList<>();
        	}
            result.setResult(esList);
        }catch (Exception e){
            logger.error("查询应收费用列表失败", e);
            throw e;
        }
        return result;
    }
    
    /**
     * 根据运单ID删除
     * @param financeDeleteReqDTO
     * @return
     */
    @PostMapping("/deleteByCode/")
    public DataResult<Boolean> deleteByCode(@RequestBody FinanceDeleteReqDTO financeDeleteReqDTO) {
    	logger.debug("根据运单号删除：{}",financeDeleteReqDTO);
    	Boolean dto = null;
    	try {
    		dto = financeManageReceiptService.deleteByCode(financeDeleteReqDTO);
    	} catch (Exception e) {
    		logger.error("根据运单号删除失败",e);
    		throw e;
    	}
    	return DataResult.success(dto,Boolean.class);
    }
    
    /**
     * 根据运单ID查询是否有审核
     * @param financeDeleteReqDTO
     * @return
     */
    @PostMapping("/getPayStatusByCode/")
    public DataResult<Boolean> getPayStatusByCode(@RequestBody FinanceDeleteReqDTO financeDeleteReqDTO) {
    	logger.debug("根据运单ID查询是否有审核：{}",financeDeleteReqDTO);
    	Boolean dto = null;
    	try {
    		dto = financeManageReceiptService.getPayStatusByCode(financeDeleteReqDTO);
    	} catch (Exception e) {
    		logger.error("根据运单ID查询是否有审核失败",e);
    		throw e;
    	}
    	return DataResult.success(dto,Boolean.class);
    }
    
    /**
     * 根据运单ID查询是可以取消签收
     * @param financeDeleteReqDTO
     * @return 
     */
    @PostMapping("/getPayStatusByCodeSign/")
    public DataResult<Boolean> getPayStatusByCodeSign(@RequestBody FinanceDeleteReqDTO financeDeleteReqDTO) {
    	logger.debug("根据运单ID查询是可以取消签收：{}",financeDeleteReqDTO);
    	Boolean dto = null;
    	try {
    		dto = financeManageReceiptService.getPayStatusByCodeSign(financeDeleteReqDTO);
    	} catch (Exception e) {
    		logger.error("根据运单ID查询是可以取消签收失败",e);
    		throw e;
    	}
    	return DataResult.success(dto,Boolean.class);
    }
    
    /**
     * 根据中转ID,中转单号查询是可以取消中转
     * @param financeDeleteReqDTO
     * @return 
     */
    @PostMapping("/getPayStatusByCodeTrans/")
    public DataResult<Boolean> getPayStatusByCodeTrans(@RequestBody FinanceDeleteReqDTO financeDeleteReqDTO) {
    	logger.debug("根据中转ID查询是可以取消中转：{}",financeDeleteReqDTO);
    	Boolean dto = null;
    	try {
    		dto = financeManageReceiptService.getPayStatusByCodeTrans(financeDeleteReqDTO);
    	} catch (Exception e) {
    		logger.error("根据中转ID查询是可以取消中转失败",e);
    		throw e;
    	}
    	return DataResult.success(dto,Boolean.class);
    }
    
    /**
     * 创建收款单
     * @param financeSaveReceiptDTO
     * @return
     */
    @PostMapping("/saveReceipt/")
    public DataResult<Boolean> saveReceipt(@RequestBody FinanceSaveReceiptDTO financeSaveReceiptDTO) {
    	logger.debug("创建收款单：{}",financeSaveReceiptDTO);
    	Boolean dto = null;
    	try {
    		dto = financeManageReceiptComponentService.saveReceipt(financeSaveReceiptDTO);
    	} catch (Exception e) {
    		logger.error("创建收款单失败",e);
    		throw e;
    	}
    	return DataResult.success(dto,Boolean.class);
    }
    
    /**
     * 取消收银
     * @param financeDeleteReqDTO
     * @return
     */
    @PostMapping("/cancleReceipt/")
    public DataResult<Boolean> cancleReceipt(@RequestBody FinanceCancleReceiptDTO financeCancleReceiptDTO) {
    	logger.debug("取消收银：{}",financeCancleReceiptDTO);
    	Boolean dto = null;
    	try {
    		dto = financeManageReceiptComponentService.cancleReceipt(financeCancleReceiptDTO);
    	} catch (Exception e) {
    		logger.error("取消收银失败",e);
    		throw e;
    	}
    	return DataResult.success(dto,Boolean.class);
    }
    
    /**
     * 更新回单状态
     * @param financeDeleteReqDTO
     * @return
     */
    @PostMapping("/updateBackStatus/")
    public DataResult<Boolean> updateBackStatus(@RequestBody FinanceReceiptStatusUpdateDTO financeReceiptStatusUpdateDTO) {
    	logger.debug("更新回单状态：{}",financeReceiptStatusUpdateDTO);
    	Boolean dto = null;
    	try {
    		financeReceiptStatusUpdateDTO.setPayWay(PayWayEnum.BACKPAY.getType());
    		dto = financeManageReceiptService.updateSignStatus(financeReceiptStatusUpdateDTO);
    	} catch (Exception e) {
    		logger.error("更新回单状态失败",e);
    		throw e;
    	}
    	return DataResult.success(dto,Boolean.class);
    }
    
    /**
     * 根据运单ID查询是否可以修改收货人、发货人
     * @param financeForUpdateWaybill
     * @return
     */
    @PostMapping("/getStatusForUpdateWaybill/")
    public DataResult<Boolean> getStatusForUpdateWaybill(@RequestBody FinanceForUpdateWaybill financeForUpdateWaybill) {
    	logger.debug("根据运单ID查询是否可以修改收货人、发货人：{}",financeForUpdateWaybill);
    	Boolean dto = null;
    	try {
    		dto = financeManageReceiptService.getStatusForUpdateWaybill(financeForUpdateWaybill);
    	} catch (Exception e) {
    		logger.error("根据运单ID查询是否可以修改收货人、发货人失败",e);
    		throw e;
    	}
    	return DataResult.success(dto,Boolean.class);
    }

}
