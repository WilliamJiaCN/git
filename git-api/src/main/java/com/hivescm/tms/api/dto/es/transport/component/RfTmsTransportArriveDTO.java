package com.hivescm.tms.api.dto.es.transport.component;

import com.hivescm.tms.api.dto.es.transport.TransportArrivalInfoEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportGoodsDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportWaybillDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.redundancy.TransportWaybillTagEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/4/19 11:33
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class RfTmsTransportArriveDTO implements Serializable {

    private static final long serialVersionUID = 228351801332220172L;

    private @ApiModelProperty("到货批次") TransportArrivalInfoEsDTO transportArrivalInfoEsDTO;

    private @ApiModelProperty("批次运单") List<TransportWaybillDetailEsDTO> transportWaybillDetailEsDTOList;

    private @ApiModelProperty("运单标签") List<TransportWaybillTagEsDTO> transportWaybillTagList;

    private @ApiModelProperty("批次货物") List<TransportGoodsDetailEsDTO> transportGoodsDetailEsDTOList;
}
