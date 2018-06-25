package com.hivescm.tms.api.dto.es.fare.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.fare.FareInfoEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class FareChangeReqDTO {
    @Mapping
    @Logger
    @ApiModelProperty(value = "主键", required = true)
    private Long id;
    @Mapping
    @ApiModelProperty("更改批次号")
    private String changeCode;
    @Mapping
    @Required
    @Logger
    @ApiModelProperty(value = "公司Id", hidden = true)
    private Long companyId;
    @Mapping
    @Required
    @Logger
    @ApiModelProperty(value = "公司名称", hidden = true)
    private String companyName;
    @Mapping
    @ApiModelProperty("单据id")
    private @Required
    @Logger
    Long billId;
    @Mapping
    @Required
    @Logger
    @ApiModelProperty(value = "单据批次号", required = true)
    private String billCode;
    @Mapping
    @Logger
    @Required
    @ApiModelProperty(value = "单据类型（2派车单3配载单7到货确认单）", required = true)
    private Integer billType;
    @Mapping
    @ApiModelProperty(value = "申请人", hidden = true)
    private Integer createUser;
    @Mapping
    @ApiModelProperty(value = "修改人", hidden = true)
    private Integer updateUser;
    @Mapping
    @ApiModelProperty(value = "确认人", hidden = true)
    private Integer confirmUser;
    //-----------------------Es--------------------------------
    @Mapping
    @ApiModelProperty(value = "更改备注", required = true)
    private String remarks;
    @Mapping
    @ApiModelProperty(value = "操作说明", required = true)
    private String description;
    @Mapping
    @ApiModelProperty(value = "申请人姓名", hidden = true)
    private String createUserName;
    @Mapping
    @ApiModelProperty(value = "修改人姓名", hidden = true)
    private String updateUserName;
    @Mapping
    @ApiModelProperty(value = "确认人姓名", hidden = true)
    private String confirmUserName;
    @Mapping
    @ApiModelProperty(value = "申请网点id", hidden = true)
    private Integer createOrgId;
    @Mapping
    @ApiModelProperty(value = "申请网点名称", hidden = true)
    private String createOrgName;
//    @Mapping
//    @ApiModelProperty("起始网点id")
//    private Long startNetworkId;
//    @Mapping
//    @ApiModelProperty("到达网点id")
//    private Long endNetworkId;
//    @Mapping
//    @ApiModelProperty("运输线路id")
//    private Long lineId;
//    @Mapping
//    @ApiModelProperty("起始网点名称")
//    private String startNetworkName;
//    @Mapping
//    @ApiModelProperty("到达网点名称")
//    private String endNetworkName;
//    @Mapping
//    @ApiModelProperty("运输线路名称")
//    private String lineName;

    @ApiModelProperty("原费用信息")
    private List<FareInfoEsDTO> oldFareInfoEsDTOList;

    @ApiModelProperty("费用信息")
    private List<FareInfoEsDTO> newFareInfoEsDTOList;
}
