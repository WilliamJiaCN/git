package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class FinanceManageGrantConfirmQueryReqDTO implements Serializable{

    private static final long serialVersionUID = 6932410963088700455L;

    @Mapping
    @ApiModelProperty(value = "日期类型 0录单日期 1 签收日期")
    private Integer dateType;
    @Mapping
    @ApiModelProperty(value = "开始时间")
    private Long dateStart;
    @Mapping
    @ApiModelProperty(value = "结束时间")
    private Long dateEnd;
    @Mapping
    @ApiModelProperty("发货方")
    private String invoiceCompanyName;
    @Mapping
    @ApiModelProperty(value = "发货人")
    private String invoiceUserName;
    @Mapping
    @ApiModelProperty(value = "运单号")
    private String orderSourceCode;
    @Mapping
    @ApiModelProperty(value = "当前网点")
    private Integer orgId;

}
