package com.hivescm.tms.api.dto.es.transport.component;

import com.hivescm.tms.api.dto.es.transport.TransportArrivalCostDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportArrivalInfoEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportInfoEsDTO;

import com.hivescm.tms.api.dto.es.transport.TransportLineEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 到货批次信息
 *
 * @author 李洪春
 * @since 2017/9/8 14:12
 */
@Data
@ToString
public class TmsTransportArriveDTO implements Serializable {

    private static final long serialVersionUID = 2200730150725245103L;

    /**
     * 到货基本信息
     */
    @ApiModelProperty("到货基本信息")
    private TransportArrivalInfoEsDTO transportArrivalInfo;

    /**
     * 到货费用列表
     */
    @ApiModelProperty("到货费用列表")
    private List<TransportArrivalCostDetailEsDTO> transportArrivalCostDetailList;

    /**
     * 到货运单列表
     */
    @ApiModelProperty("到货运单、货物信息")
    private List<TmsTransportDetailDTO> tmsTransportDetailDTOList;
    
    /**
     * 发车批次信息
     */
    @ApiModelProperty("发车批次信息")
    private TransportInfoEsDTO transportInfo;

    @ApiModelProperty("发车批次线路")
    private TransportLineEsDTO transportLineEsDTO;


}
