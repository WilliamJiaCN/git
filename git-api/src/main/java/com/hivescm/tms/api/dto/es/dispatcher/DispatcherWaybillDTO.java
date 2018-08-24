package com.hivescm.tms.api.dto.es.dispatcher;

import com.google.common.collect.Lists;
import com.hivescm.tms.api.dto.es.stock.WaybillGoodsEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillStockEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 派车单查询运单时返回结果对象
 *
 * @author 李洪春
 * @since 2017/8/17 14:48
 */
@Data
@ToString
public class DispatcherWaybillDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2699265734520022235L;

    /**
     * 运单信息
     */
    @ApiModelProperty("运单信息")
    private WaybillEsDTO waybill;

    @ApiModelProperty("库存信息")
    private WaybillStockEsDTO waybillStockEsDTO;

    /**
     * 运单货物库存信息
     */
    @ApiModelProperty("运单货物库存信息")
    private List<WaybillGoodsEsDTO> waybillGoodsEsDTOList;


    @ApiModelProperty("代收货款")
    private BigDecimal businessFee;
    
    @ApiModelProperty("代收货款")
    private BigDecimal collectValue;


    
    public DispatcherWaybillDTO(WaybillEsDTO waybill) {
        this.waybill = waybill;
    }

    public DispatcherWaybillDTO(WaybillEsDTO waybill, List<WaybillGoodsEsDTO> waybillGoodsEsDTOList) {
        this.waybill = waybill;
        this.waybillGoodsEsDTOList = waybillGoodsEsDTOList;
    }

    public DispatcherWaybillDTO() {

    }

    /**
     * 添加库存货物信息
     *
     * @param waybillGoodsEsDTO 货物信息
     */
    public void addWaybillGoodsEsDTO(WaybillGoodsEsDTO waybillGoodsEsDTO) {
        if (null == waybillGoodsEsDTOList) {
            waybillGoodsEsDTOList = Lists.newArrayList();
        }
        waybillGoodsEsDTOList.add(waybillGoodsEsDTO);
    }
}
