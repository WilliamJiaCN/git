package com.hivescm.tms.finance.server.interfaces.pcsign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.domain.PagedList;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.BackWarehouseReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.DeliverySignBatchReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SearchBankWarehouseReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SearchBackWarehouseRespDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(value="deliverySign",description="送货签收接口")
public interface DeliverySignControllerDoc {
	@ApiOperation(value = "返回入库接口")
	@ApiImplicitParam(value="返回入库请求实体",name = "backWarehouseReqDTO",required = true,paramType="body",dataType = "BackWarehouseReqDTO")
	DataResult<Boolean> addBackWareHouse( BackWarehouseReqDTO backWarehouseReqDTO);
	
	@ApiOperation(value = "反库明细接口")
	@ApiImplicitParam(value="反库明细请求实体",name = "searchBankWarehouseReqDTO",required = true,paramType="body",dataType = "SearchBankWarehouseReqDTO")
	DataResult<PagedList<SearchBackWarehouseRespDTO>> getBackWarehouse(SearchBankWarehouseReqDTO searchBankWarehouseReqDTO);

	@ApiOperation(value = "新增派送签收")
	@ApiImplicitParam(value="新增派送签收",name = "tmsSignEsDTO",required = true,paramType="body",dataType = "TmsSignEsDTO")
	DataResult<Boolean> deliverySign( TmsSignEsDTO tmsSignEsDTO);


	@ApiOperation(value = "批量签收")
	@ApiImplicitParam(value="批量签收",name = "deliverySignBatchReqDTO",required = true,paramType="body",dataType = "DeliverySignBatchReqDTO")
	DataResult<Boolean> batchSign( DeliverySignBatchReqDTO deliverySignBatchReqDTO);


//	@ApiOperation(value = "拒签")
//	@ApiImplicitParam(value="拒签",name = "tmsSignEsDTO",required = true,paramType="body",dataType = "tmsSignEsDTO")
//	DataResult<Boolean> refuseSign( TmsSignEsDTO tmsSignEsDTO);

    @ApiOperation(value = "送货签收 -- 派送失败")
    @ApiImplicitParam(value="派送失败请求实体",name = "tmsSignEsDTO",required = true,paramType="body",dataType = "TmsSignEsDTO")
    DataResult<Boolean> deliveryFailure( TmsSignEsDTO tmsSignEsDTO);

//	@ApiOperation(value = "送货签收 -- 取消签收")
//	@ApiImplicitParam(value="送货签收取消签收实体",name = "cancelDeliverySignBatchReqDTOList",required = true,paramType="body",dataType = "CancelDeliverySignBatchReqDTO")
//	DataResult<Boolean> cancelDeliverySign( List<CancelDeliverySignBatchReqDTO> cancelDeliverySignBatchReqDTOList);


}
