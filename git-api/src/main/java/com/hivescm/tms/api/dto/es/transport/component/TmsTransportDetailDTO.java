package com.hivescm.tms.api.dto.es.transport.component;

import com.google.common.collect.Lists;
import com.hivescm.tms.api.dto.es.transport.TransportGoodsDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportWaybillDetailEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 运输批次明细信息
 *
 * @author 李洪春
 * @since 2017/8/29 16:50
 */
@Data
@ToString
public class TmsTransportDetailDTO implements Serializable {

    private static final long serialVersionUID = 2713552911429364262L;

    /**
     * 运输单运单信息
     */
    @ApiModelProperty("运输单运单信息")
    private TransportWaybillDetailEsDTO transportWaybillDetail;


    /**
     * 运输单货物明细列表
     */
    @ApiModelProperty("运输单货物明细列表")
    private List<TransportGoodsDetailEsDTO> transportGoodsDetailList;

    public TmsTransportDetailDTO() {
    }

    public TmsTransportDetailDTO(TransportWaybillDetailEsDTO transportWaybillDetail) {
        this.transportWaybillDetail = transportWaybillDetail;
    }

//    @ApiModelProperty("查询总条数")
    private Integer count = 0;

    /**
     * 添加货物信息
     *
     * @param transportGoodsDetailEsDTO 货物信息
     */
    public void addTransportGoodsDetailDTO(TransportGoodsDetailEsDTO transportGoodsDetailEsDTO) {
        if (null == transportGoodsDetailList) {
            transportGoodsDetailList = Lists.newArrayList();
        }
        transportGoodsDetailList.add(transportGoodsDetailEsDTO);
    }
}
