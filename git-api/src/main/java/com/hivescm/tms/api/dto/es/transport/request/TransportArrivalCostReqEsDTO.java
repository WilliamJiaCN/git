package com.hivescm.tms.api.dto.es.transport.request;

import com.hivescm.tms.api.dto.es.transport.TransportArrivalCostDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportArrivalInfoEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportWaybillDetailEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/31 10:02
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class TransportArrivalCostReqEsDTO implements Serializable{

    @ApiModelProperty("批次")
    private TransportArrivalInfoEsDTO transportArrivalInfoEsDTO;

    @ApiModelProperty("新增费用明细")
    private List<TransportArrivalCostDetailEsDTO> addList ;//新增

    @ApiModelProperty("修改费用明细")
    private List<TransportArrivalCostDetailEsDTO> updateList;//修改

    @ApiModelProperty("删除的费项ID")
    private List<Long> costId;

    @ApiModelProperty("费用分摊")
    private List<TransportWaybillDetailEsDTO> transportWaybillDetailEsDTOList;
}
