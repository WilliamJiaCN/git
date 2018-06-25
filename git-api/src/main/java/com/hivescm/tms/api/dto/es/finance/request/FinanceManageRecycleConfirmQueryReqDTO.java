package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class FinanceManageRecycleConfirmQueryReqDTO implements Serializable{
    private static final long serialVersionUID = -2669664224565531795L;
    @Mapping
    @ApiModelProperty(value = "日期类型 0制单时间 1签收时间")
    private Integer dateType;
    @Mapping
    @ApiModelProperty(value = "开始时间")
    private Long dateStart;
    @Mapping
    @ApiModelProperty(value = "结束时间")
    private Long dateEnd;
    @Mapping
    @ApiModelProperty("收货方")
    private String receiptCompanyName;
    @Mapping
    @ApiModelProperty("中转公司")
    private String transitCompanyName;
    @Mapping
    @ApiModelProperty("运单号")
    private String orderSourceCode;
    @Mapping
    @ApiModelProperty(value = "当前网点")
    private Integer orgId;
}
