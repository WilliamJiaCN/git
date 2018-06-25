package com.hivescm.tms.api.dto.es.waybill.component;

import com.google.common.collect.Lists;
import com.hivescm.tms.api.dto.es.stock.WaybillStockDetailEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillStockEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 运单库存信息
 *
 * @author 李洪春
 * @since 2017/9/19 下午9:01
 */
@Data
@ToString
public class TmsWaybillStockDTO implements Serializable {
    private static final long serialVersionUID = -4546616726654068363L;

    @ApiModelProperty("库存信息")               // 库存主表
    private WaybillStockEsDTO waybillStock;

    @ApiModelProperty("商品库存明细")            // 库存货物明细
    private List<WaybillStockDetailEsDTO> waybillStockDetailEsDTOList;

//    @ApiModelProperty("查询总条数")
//    private Integer count = 0;

    /**
     * 添加商品库存信息
     *
     * @param waybillGoodsEsDTO 商品库存信息
     */
    public void addWaybillStockDetailEsDTO(WaybillStockDetailEsDTO waybillGoodsEsDTO){
        if (null == waybillStockDetailEsDTOList) {
            waybillStockDetailEsDTOList = Lists.newArrayList();
        }
        waybillStockDetailEsDTOList.add(waybillGoodsEsDTO);
    }


    public TmsWaybillStockDTO() {
    }

    public TmsWaybillStockDTO(WaybillStockEsDTO waybillStock) {
        this.waybillStock = waybillStock;
    }
}
