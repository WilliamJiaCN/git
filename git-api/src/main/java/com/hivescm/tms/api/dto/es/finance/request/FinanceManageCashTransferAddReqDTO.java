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
public class FinanceManageCashTransferAddReqDTO implements Serializable{
    private static final long serialVersionUID = 8114740152376788607L;
    @Mapping
    @ApiModelProperty(value = "日期类型 1.制单时间 2.发生日期")
    @Required
    private Integer dateType;
    @Mapping
    @ApiModelProperty(value = "开始时间")
    @Required
    private Long dateStart;
    @Mapping
    @ApiModelProperty(value = "结束时间")
    @Required
    private Long dateEnd;
    @Mapping
    @ApiModelProperty("单据类型 1.运单 2.代收货款 3. 派车单 4.发车单 5.到货单 6.装卸单 7.其他收入 8.其他支出 9.现金转账")
    @Required
    private List<Integer> codType;
    @Mapping
    @ApiModelProperty("转账账号")
    private String transferAccount;
    @Mapping
    @ApiModelProperty(" 来源单号")
    private String ordersourceId;
    @Mapping
    @ApiModelProperty("现金流水ID")
    private List<Integer> serialId;
    @Mapping
    @ApiModelProperty(value = "当前网点")
    private Integer orgId;
}
