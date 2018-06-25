package com.hivescm.tms.finance.server.feign.waybill;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.bossfreight.BillingRecordReq;
import com.hivescm.tms.api.dto.es.packageinfo.PackageDetailEsDTO;
import com.hivescm.tms.api.dto.es.packageinfo.PackageInfoEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.*;
import com.hivescm.tms.api.dto.es.sign.response.NoSignWaybillRespDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillGoodsEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillStockDetailEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillStockLuckEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import com.hivescm.tms.api.dto.es.waybill.component.TmsWaybillEsDTO;
import com.hivescm.tms.api.dto.es.waybill.request.SyncWaybillStatusToOmsReq;
import com.hivescm.tms.api.dto.print.waybill.WaybillCombinationInfoForPrintDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * @author 杨彭伟
 * @date 2018-01-30 16:28
 */
@FeignClient(value = "${tms.billcenter.application.name}", path = "${tms.billcenter.application.path}")
public interface WaybillService {

	@GetMapping("/waybill/getWaybillById/{waybillId}")
	WaybillEsDTO queryWaybillEsDTO(@PathVariable("waybillId") Long waybillId);

	@PostMapping("/waybill/getWaybillsByOrderCode")
	List<WaybillEsDTO> getWaybillsByOrderCode(@RequestParam("orderCode") String orderCode);

	@GetMapping("/waybillGoods/getWaybillGoodsByWaybillId/{waybillId}")
	List<WaybillGoodsEsDTO> queryWaybillGoodsEsDTOList(@PathVariable("waybillId") Long waybillId);

	@PostMapping("/waybillFee/queryWaybillFeeEsDTO")
	List<WaybillFeeEsDTO> queryWaybillFeeEsDTOList(@RequestBody WaybillFeeEsDTO waybillFeeEsDTO);

	@GetMapping("/waybillPackage/queryPackageListByWaybillId/{waybillId}")
	List<PackageInfoEsDTO> queryPackageListByWaybillId(@PathVariable("waybillId") Long waybillId);

	@GetMapping("/waybillPackage/getPackageDetailsByPackageId/{packageId}")
	List<PackageDetailEsDTO> getPackageDetailsByPackageId(@PathVariable("packageId") Long packageId);

	@GetMapping("/waybillPackage/getPackageDetailsByWaybillId/{waybillId}")
	List<PackageDetailEsDTO> getPackageDetailsByWaybillId(@PathVariable("waybillId") Long waybillId);

	@PostMapping("/waybill/syncWaybillStatus")
	Boolean syncWaybillStatusToOms(@RequestBody SyncWaybillStatusToOmsReq req);

	@PostMapping("/waybill/waybillCombinationInfo/{waybillId}")
	public DataResult<WaybillCombinationInfoForPrintDTO> queryWaybillCombinationInformationForPrint(@PathVariable("waybillId") Long waybillId);

	@GetMapping("/waybillFee/getWaybillFeeByWaybillId/{waybillId}")
	List<WaybillFeeEsDTO> getWaybillFeeByWaybillId(@PathVariable("waybillId") Long waybillId);

	@PostMapping("/waybill/getWaybillListByConditions")
	List<WaybillEsDTO> queryWaybillByConditions(@RequestBody QueryWaybillConditionsReqDTO req);

	@PostMapping("/waybill/getWaybillDteails")
	DataResult<TmsWaybillEsDTO> queryWaybillDteails(@RequestBody SignInitializeWayBillReqDTO req);

	@PostMapping("/waybill/batchUpdateWaybillEs")
	Boolean updateWaybillStatus(@RequestBody List<WaybillEsDTO> waybill);


	/**
	 * 送货签收未到库取消接口
	 * ps:在送货途中,产生的业务场景
	 * 例:出库100件货物,签收60件,拒签40件。当还未返回入库的时候,执行取消签收,此时根据签收单上的签收数量以及拒签数量对该仓库进行操作。
	 * @param stockLuckEsDTOs
	 * @return
	 */
	@PostMapping("/waybillStock/updateSignNotArrived")
	DataResult<Boolean> updateSignNotArrived(@RequestBody List<WaybillStockLuckEsDTO> stockLuckEsDTOs);
	/**
	 * 自提签收取消（还未返库）
	 * @param stockLuckEsDTOs
	 * @return
	 */
	@PostMapping("/waybillStock/updateSignEscStockNum")
	DataResult<Boolean> updateSignEscStockNum(@RequestBody List<WaybillStockLuckEsDTO> stockLuckEsDTOs);
	/**
	 * 送货签收取消（已返库）
	 * @param stockLuckEsDTOs
	 * @return
	 */
	@PostMapping("/waybillStock/updateSignStockEscNum")
	DataResult<Boolean> updateSignStockEscNum(@RequestBody List<WaybillStockLuckEsDTO> stockLuckEsDTOs);
	@GetMapping("/waybill/getWaybillByCode/{waybillCode}/{companyId}")
    WaybillEsDTO getWaybillByCode(@PathVariable("waybillCode") String waybillCode,@PathVariable("companyId") Integer companyId);

	/**
	 * 部分签收/拒签/派送失败，执行返库操作。
	 * @param stockLuckEsDTOs
	 * @return
	 */
	@PostMapping("/waybillStock/signStockDetail")
	DataResult<Integer> signStockDetail(@RequestBody List<WaybillStockLuckEsDTO> stockLuckEsDTOs);


	@PostMapping("/waybill/waybillnosign")
	DataResult<NoSignWaybillRespDTO> getWaybillNoSign(@RequestBody SignQueryWaybillReqDTO req);
	
	@PostMapping("/waybill/appwaybillnosign")
	DataResult<NoSignWaybillRespDTO> getAppWaybillNoSign(@RequestBody AppQueryReqDTO appQueryReqDTO);


	@PostMapping("/waybill/queryBatchWaybillDteails")
	DataResult<List<TmsWaybillEsDTO>> queryBatchWaybillDetails(@RequestBody SelfSignBatchReqDTO req);
	
	@PostMapping("/waybill/updateWaybillInfoFromSign")
    DataResult<Boolean> updateWyabillInfoFromSign(@RequestBody List<UpdateSignStatusReqDTO> reqList);


	/**
	 * 通过运单编号查询运单列表-批量
	 *
	 * @param
	 * @param codes
	 * @return 运单列表
	 */
	@GetMapping("/waybill/getWaybillListByCodes/{companyId}")
	List<WaybillEsDTO> getWaybillEsDTOListByCodes(@RequestBody Collection<String> codes,@PathVariable("companyId") Long companyId);
	/**
	 * 取消签收查询拒签锁定数量
	 * @param detailIds
	 * @param orgId
	 * @return
	 */
	@PostMapping("/waybill/getrefuselocknum")
    DataResult<List<WaybillStockDetailEsDTO>> getRefuseLockNum(@RequestParam("detailIds") List<Long> detailIds,@RequestParam("orgId") Long orgId);
	
	/**
	 * 零担干线计费流水接口
	 * @param req
	 * @return
	 */
	@PostMapping("/deliveryCollect/sendWaybillInfoToBossNew")
	Boolean sendWaybillInfoToBossNew(@RequestBody BillingRecordReq req);

}
