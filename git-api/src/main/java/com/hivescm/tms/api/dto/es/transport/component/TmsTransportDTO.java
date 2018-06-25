package com.hivescm.tms.api.dto.es.transport.component;

import com.hivescm.tms.api.dto.es.transport.TransportCostDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportInfoEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportLineEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * tms运输批次详细信息
 *
 * @author 李洪春
 * @since 2017/8/29 16:47
 */
@Data
@ToString
public class TmsTransportDTO implements Serializable {

    private static final long serialVersionUID = 3686052235566638193L;

    /**
     * 运输批次基本信息
     */
    @ApiModelProperty("运输批次基本信息")
    private TransportInfoEsDTO transportInfo;

    /**
     * 运输批次线路信息
     */
    @ApiModelProperty("运输批次线路信息")
    private List<TransportLineEsDTO> transportLineEsDTOList;

    /**
     * 运输批次费用信息
     */
    @ApiModelProperty("运输批次费用明细")
    private List<TransportCostDetailEsDTO> transportCostDetailList;


    /**
     * 运输批次明细信息列表
     */
    @ApiModelProperty("运输批次明细信息列表")
    private List<TmsTransportDetailDTO> tmsTransportDetailList;
    
    /**
     * 在途时长(在途库存专用)
     */
    @ApiModelProperty("在途时长")
    private Long time;
}
