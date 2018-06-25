package com.hivescm.tms.api.dto.es.transport.component;

import com.hivescm.tms.api.dto.es.transport.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/23 10:49
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ConfirmTransportArriveDTO implements Serializable {

    private static final long serialVersionUID = -9127346835575609818L;
    /**
     * 到货基本信息
     */
    @ApiModelProperty("到货基本信息")
    private TransportArrivalInfoEsDTO transportArrivalInfo;


    @ApiModelProperty("运输单运单信息")
    private List<TransportWaybillDetailEsDTO> transportWaybillDetail;
    /**
     * 发车批次信息
     */
    @ApiModelProperty("发车批次信息")
    private TransportInfoEsDTO transportInfo;


}
