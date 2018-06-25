package com.hivescm.tms.api.dto.es.transport.component;

import com.hivescm.tms.api.dto.es.transport.redundancy.RfTransportWaybillEsDTO;
import com.hivescm.tms.api.dto.es.transport.redundancy.TransportWaybillTagEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/4/18 18:03
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class RfTransportArriveDTO implements Serializable{

    private static final long serialVersionUID = -6134295136739369843L;
    /**
     * 发车批次ID
     */
    private @ApiModelProperty("到货批次批次ID") Long arrivalId;
    /**
     * 车辆id
     */
    private   @ApiModelProperty("车辆id") Integer vehicleId;

    private   @ApiModelProperty("发车批次ID") Long transportId;
    /**
     * 运单信息
     */
    private @ApiModelProperty("运单信息")
    RfTransportWaybillEsDTO rfTransportWaybillEsDTO;

    /**
     * 标签详情
     */
    private @ApiModelProperty("标签信息")
    List<TransportWaybillTagEsDTO> transportWaybillTagEsDTOList;
}
