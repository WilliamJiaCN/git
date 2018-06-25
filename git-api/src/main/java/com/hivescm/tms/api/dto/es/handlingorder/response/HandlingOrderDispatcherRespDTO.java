package com.hivescm.tms.api.dto.es.handlingorder.response;

import com.hivescm.tms.api.dto.es.packageinfo.PackageDetailEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillStockDetailEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillStockEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/26
 */

@ToString
@Data
public class HandlingOrderDispatcherRespDTO {


    @ApiModelProperty("装卸单运单信息")
    private PickHandlingOrderDetailRespDTO pickHandlingOrderDetailRespDTO;

    @ApiModelProperty("装卸单货物列表")
    private List<PickHandlingOrderGoodsRespDTO> pickHandlingOrderGoodsResDTOList;

    /**
     * 运单库存明细
     */
    private @ApiModelProperty("运单库存明细") List<WaybillStockDetailEsDTO> waybillStockDetailEsDTOs;
    /**
     * 运单库存
     */
    private @ApiModelProperty("运单库存")
    WaybillStockEsDTO waybillStockEsDto;
    /**
     * 运单运费
     */
    private @ApiModelProperty("运单运费") List<WaybillFeeEsDTO> waybillFeeEsDtos;
    /**
     * 包裹信息
     */
    private @ApiModelProperty("包裹信息") List<PackageDetailEsDTO> packageDetailEsDtos;


}
