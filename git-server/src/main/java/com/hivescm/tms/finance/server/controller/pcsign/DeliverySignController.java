package com.hivescm.tms.finance.server.controller.pcsign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.domain.PagedList;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.*;
import com.hivescm.tms.api.dto.es.sign.response.SearchBackWarehouseRespDTO;
import com.hivescm.tms.finance.server.component.pcsign.BackWareHouseService;
import com.hivescm.tms.finance.server.component.pcsign.DeliveryBatchSignService;
import com.hivescm.tms.finance.server.component.pcsign.DeliverySignService;
import com.hivescm.tms.finance.server.interfaces.pcsign.DeliverySignControllerDoc;
import com.hivescm.tms.finance.server.service.pcsign.BackWarehouseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 送货签收-签收服务
 * @author  zouhx
 * @date    2018年6月4日
 * @company 蜂网供应链管理（上海）有限公司
 */
@RestController
@RequestMapping("delivery")
public class DeliverySignController implements DeliverySignControllerDoc{

	@Autowired
	private BackWareHouseService backWareHouseService;
	@Autowired
	private BackWarehouseRecordService backWarehouseRecordService;
	@Autowired
	private DeliverySignService deliverySignService;
	@Autowired
	private DeliveryBatchSignService deliveryBatchSignService;


	/**
	 * 添加返库操作
	 * @param backWarehouseReqDTO
	 * @return
	 */
	@Override
	@PostMapping("/addBackWareHouse")
	public DataResult<Boolean> addBackWareHouse(@RequestBody BackWarehouseReqDTO backWarehouseReqDTO) {
		DataResult<Boolean> result = new DataResult<>();
		try {
			result.setResult(backWareHouseService.addBackWareHouse(backWarehouseReqDTO));
			return result;
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 获取返库信息
	 * @param searchBankWarehouseReqDTO
	 * @return
	 */
	@Override
	@PostMapping("getBackWarehouse")
	public DataResult<PagedList<SearchBackWarehouseRespDTO>> getBackWarehouse(
			@RequestBody SearchBankWarehouseReqDTO searchBankWarehouseReqDTO) {
		DataResult<PagedList<SearchBackWarehouseRespDTO>> result = new DataResult<>();
		result.setResult(backWarehouseRecordService.getBackWarehouse(searchBankWarehouseReqDTO));
		return result;
	}

	/**
	 * 通过派车单ID获取返库信息
	 * @param dispatcherDetailId
	 * @return
	 */
	@PostMapping("getBackWarehouseDetail")
	public DataResult<List<SearchBackWarehouseRespDTO>> getBackWarehouseDetail(
			@RequestParam("dispatcherDetailId") Long dispatcherDetailId) {
		DataResult<List<SearchBackWarehouseRespDTO>> result = new DataResult<>();
		result.setResult(backWarehouseRecordService.getBackWarehouseDetail(dispatcherDetailId));
		return result;
	}

	/**
	 * 签收
	 * @param tmsSignEsDTO
	 * @return
	 */
	@Override
	@PostMapping("sign")
	public DataResult<Boolean> deliverySign(@RequestBody TmsSignEsDTO tmsSignEsDTO) {
		DataResult<Boolean> result = new DataResult<>();
		result.setResult(deliverySignService.deliverySign(tmsSignEsDTO));
		return result;
	}

	/**
	 * 批量签收
	 * @param deliverySignBatchReqDTO
	 * @return
	 */
	@Override
	@PostMapping("batchSign")
	public DataResult<Boolean> batchSign(@RequestBody DeliverySignBatchReqDTO deliverySignBatchReqDTO) {
		DataResult<Boolean> result = new DataResult<>();
		result.setResult(deliveryBatchSignService.batchSign(deliverySignBatchReqDTO));
		return result;
	}

	/**
	 *  送货签收 -- 派送失败签收
	 * @param tmsSignEsDTO
	 * @return
	 */
    @Override
    @PostMapping("/deliveryFailure")
    public DataResult<Boolean> deliveryFailure(@RequestBody TmsSignEsDTO tmsSignEsDTO){
        DataResult<Boolean> result = new DataResult<>();
		result.setResult(deliverySignService.deliveryFailure(tmsSignEsDTO));
		return result;
    }

	/**
	 * 送货签收 -- 批量取消签收
	 * @param reqDTO
	 * @return
	 */
//	@Override
	@PostMapping("/cancelDeliverySign")
	public DataResult<Boolean> cancelDeliverySign(@RequestBody CancelDeliverySignReqDTO reqDTO){
		DataResult<Boolean> result = new DataResult<>();
		Boolean res = false;
		for(Long signId :reqDTO.getSignIds() ){
			CancelDeliverySignBatchReqDTO cancelDeliverySignBatchReqDTO = new CancelDeliverySignBatchReqDTO();
			cancelDeliverySignBatchReqDTO.setCompanyId(reqDTO.getCompanyId());
			cancelDeliverySignBatchReqDTO.setCurentOrgId(reqDTO.getCurentOrgId());
			cancelDeliverySignBatchReqDTO.setUpdateUser(reqDTO.getUpdateUser());
			cancelDeliverySignBatchReqDTO.setUpdateUserName(reqDTO.getUpdateUserName());
			cancelDeliverySignBatchReqDTO.setUpdateTime(reqDTO.getUpdateTime());
			cancelDeliverySignBatchReqDTO.setId(signId);
			res = deliverySignService.cancelDeliverySign(cancelDeliverySignBatchReqDTO);
			if(!res){
				break;
			}
		}
		result.setResult(res);
		return result;
	}

	/**
	 * 送货签收 -- 取消签收
	 * @param reqDTO
	 * @return
	 */
//	@Override
	@PostMapping("/cancelOneDeliverySign")
	public DataResult<Boolean> cancelSingleDeliverySign(@RequestBody CancelDeliverySignBatchReqDTO reqDTO){
		DataResult<Boolean> result = new DataResult<>();
		Boolean res = deliverySignService.cancelDeliverySign(reqDTO);
		result.setResult(res);
		return result;
	}

}
