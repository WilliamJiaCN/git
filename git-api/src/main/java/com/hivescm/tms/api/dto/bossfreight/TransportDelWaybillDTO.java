package com.hivescm.tms.api.dto.bossfreight;

import com.hivescm.tms.api.dto.es.transport.TransportGoodsDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportInfoEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportLineEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportWaybillDetailEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * tms单票移除
 *
 */
@Data
@ToString
public class TransportDelWaybillDTO implements Serializable {
    private static final long serialVersionUID = 3686052235566638193L;


    @ApiModelProperty("运输批次基本信息")
    private TransportInfoEsDTO info;

    @ApiModelProperty("运输批次线路信息")
    private TransportLineEsDTO line;



    @ApiModelProperty("运输单运单信息")
    private TransportWaybillDetailEsDTO way;

    @ApiModelProperty("运输单货物明细列表")
    private List<TransportGoodsDetailEsDTO> goodsList;


    @ApiModelProperty("移除运单信息")
    private TransportWaybillDetailEsDTO removeWay;

    @ApiModelProperty("移除货物明细列表")
    private List<TransportGoodsDetailEsDTO> removeGoodsList;
}
