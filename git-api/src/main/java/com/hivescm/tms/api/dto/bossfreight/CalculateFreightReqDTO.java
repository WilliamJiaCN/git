package com.hivescm.tms.api.dto.bossfreight;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.stock.WaybillGoodsEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CalculateFreightReqDTO {
    /**
     * 包装名称 取货重量  取货体积 取货数量需要填写
     * packingName  deliveryWeight  deliveryVolume  deliveryNum
     */
    @Mapping
    @ApiModelProperty("货物信息")
    private WaybillGoodsEsDTO goodsEsDTO;
    /**
     * 始发地城市  始发地区域  目的地城市  目的地区域这四个计费维度字段及发货网点字段需要填写
     * invoiceCityId  invoiceDistrictId  receiptCityId  receiptDistrictId
     */
    @Mapping
    @ApiModelProperty("运单信息")
    private WaybillEsDTO waybill;
}
