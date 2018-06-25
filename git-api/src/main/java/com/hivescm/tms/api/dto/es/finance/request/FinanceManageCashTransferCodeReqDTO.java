package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class FinanceManageCashTransferCodeReqDTO implements Serializable{
    private static final long serialVersionUID = -677531693453455533L;
    @Mapping
    @ApiModelProperty(value = "来源单号（必填）")
    @Required
    private String code;
    @Mapping
    @ApiModelProperty(value = "剔除的来源单号")
    private List<Long> idList;
    @Mapping
    @ApiModelProperty("转账账号")
    private String transferAccount;
    @Mapping
    @ApiModelProperty(value = "当前网点")
    private Integer orgId;
}
