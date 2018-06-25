package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class FinanceManageRecycleCodeReqDTO implements Serializable{

    private static final long serialVersionUID = 8018862473430371294L;
    @Mapping
    @ApiModelProperty(value = "来源单号")
    private String code;
    @Mapping
    @ApiModelProperty(value = "剔除的来源单号")
    private List<Long> idList;
    @Mapping
    @ApiModelProperty(value = "当前网点")
    private Integer orgId;
}
