package com.hivescm.tms.finance.server.controller.finance;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsRecycleEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageRecycleListRespDTO;
import com.hivescm.tms.finance.server.component.finance.FinanceManageGoodsRecycleComponentService;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.interfaces.finance.FinanceManageGoodsRecycleControllerDoc;
import com.hivescm.tms.finance.server.service.finance.FinanceManageGoodsRecycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 代收货款回收
 * @author wangqianqian
 *
 */
@RestController
@RequestMapping("/financemanagerecyle")
public class FinanceManageGoodsRecycleController extends BaseController implements FinanceManageGoodsRecycleControllerDoc {


	@Autowired
	private FinanceManageGoodsRecycleComponentService financeManageGoodsRecycleComponentService;
	@Autowired
	private FinanceManageGoodsRecycleService financeManageGoodsRecycleService;
	

    @PostMapping("/insertSingle")
    public DataResult<Boolean> insertSingle(@RequestBody FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO) {
        DataResult<Boolean> dataResult=new DataResult<>();
        dataResult.setResult(financeManageGoodsRecycleService.insert(financeManageGoodsRecycleEsDTO));
        return dataResult;
    }

	/**
	 * 回收确认
	 * @param financeSaveRecycleDTO
	 * @return
	 */
	@PostMapping("/saveRecycle/")
    public DataResult<Boolean> saveRecycle(@RequestBody FinanceSaveRecycleDTO financeSaveRecycleDTO){
		logger.debug("回收确认：{}",financeSaveRecycleDTO);
    	Boolean dto = null;
    	try {
    		dto = financeManageGoodsRecycleComponentService.saveRecycle(financeSaveRecycleDTO);
    	} catch (Exception e) {
    		logger.error("回收确认失败",e);
    		throw e;
    	}
    	return DataResult.success(dto,Boolean.class);
	}
	
	/**
	 * 回收取消
	 * @param financeCancleReceiptDTO
	 * @return
	 */
	@PostMapping("/cancelRecycle/")
	public DataResult<Boolean> cancelRecycle(@RequestBody FinanceCancleReceiptDTO financeCancleReceiptDTO){
		logger.debug("回收确认：{}",financeCancleReceiptDTO);
		Boolean dto = null;
		try {
			dto = financeManageGoodsRecycleComponentService.cancleRecycle(financeCancleReceiptDTO);
		} catch (Exception e) {
			logger.error("回收确认失败",e);
			throw e;
		}
		return DataResult.success(dto,Boolean.class);
	}

	/**
	 * 查询货款回收列表
	 * @param financeManageRecycleListReqDTO
	 * @return
	 */
	@PostMapping("/getEsList")
	public DataResult<FinanceManageRecycleListRespDTO> getEsList(@RequestBody FinanceManageRecycleListReqDTO financeManageRecycleListReqDTO) {
		logger.debug("查询货款回收列表：{}",financeManageRecycleListReqDTO);
		DataResult<FinanceManageRecycleListRespDTO> result = new DataResult<>();
		try {
			FinanceManageRecycleListRespDTO resp = financeManageGoodsRecycleService.getEsList(financeManageRecycleListReqDTO);
			result.setResult(resp);
		}catch (Exception e){
			logger.error("查询货款回收列表失败", e);
			throw e;
		}

		return result;
	}

	/**
	 * 查询回收确认列表
	 * @param financeManageRecycleConfirmQueryReqDTO
	 * @return
	 */
	@PostMapping("/getEsForRecycleConfirmList")
	public DataResult<List<FinanceManageGoodsRecycleEsDTO>> getEsForRecycleConfirmList(@RequestBody FinanceManageRecycleConfirmQueryReqDTO financeManageRecycleConfirmQueryReqDTO) {
		logger.debug("查询发放确认列表：{}",financeManageRecycleConfirmQueryReqDTO);
		DataResult<List<FinanceManageGoodsRecycleEsDTO>> result = new DataResult<>();
		try {
			List<FinanceManageGoodsRecycleEsDTO> esList = financeManageGoodsRecycleService.getEsForRecyckeConfirmList(financeManageRecycleConfirmQueryReqDTO);
			if(esList==null) {
				esList = new ArrayList<>();
			}
			result.setResult(esList);
		}catch (Exception e){
			logger.error("查询发放确认列表", e);
			throw e;
		}

		return result;
	}

	/**
	 * 查询运单号
	 * @param financeManageRecycleCodeReqDTO
	 * @return
	 */
	@PostMapping("/getCodeByRecycle")
	public DataResult<List<FinanceManageGoodsRecycleEsDTO>> getCodeByRecycle(@RequestBody FinanceManageRecycleCodeReqDTO financeManageRecycleCodeReqDTO) {
		logger.debug("查询运单号：{}",financeManageRecycleCodeReqDTO);
		DataResult<List<FinanceManageGoodsRecycleEsDTO>> result = new DataResult<>();
		try {
			List<FinanceManageGoodsRecycleEsDTO> esList = financeManageGoodsRecycleService.getCodeByRecycle(financeManageRecycleCodeReqDTO);
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
	@PostMapping("/getEsByCodeForRecycle/{id}")
	public DataResult<List<FinanceManageGoodsRecycleEsDTO>> getEsByCodeForRecycle(@PathVariable("id") Long id) {
		logger.debug("根据id快速添加：{}",id);
		DataResult<List<FinanceManageGoodsRecycleEsDTO>> result = new DataResult<>();
		try {
			List<FinanceManageGoodsRecycleEsDTO> esList = financeManageGoodsRecycleService.getEsByCodeForRecycle(id);
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
	 * 汇款
	 * @param financeManageRecycleRemitReqDTO
	 * @return
	 */
	@PostMapping("/financeManageRecycleRemit")
	public DataResult<Boolean> financeManageRecycleRemit(@RequestBody FinanceManageRecycleRemitReqDTO financeManageRecycleRemitReqDTO) {
		DataResult<Boolean> dataResult=new DataResult<>();
		dataResult.setResult(financeManageGoodsRecycleComponentService.financeManageRecycleRemit(financeManageRecycleRemitReqDTO));
		return dataResult;
	}
}
