package com.hivescm.tms.api.dto.es.waybill.component;


import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class TmsWaybillStockESDTO  implements Serializable {

    private static final long serialVersionUID = -4546616826654068363L;

    @ApiModelProperty
    List<TmsWaybillStockDTO> tmsWaybillStockDTO;

    @Mapping
    @ApiModelProperty("目的网点")
    private Integer destOrgId;

    @Mapping
    @ApiModelProperty("目的网点名称")
    private String destOrgName;


}
