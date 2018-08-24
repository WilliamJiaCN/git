package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;

/**
 * @Author zouhx
 * @Date 2018/06/10
 */
@Data
@ToString
public class WayBillCashierConfirmationReqDTO {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;
    @Mapping
    @ApiModelProperty("运单ids")
    private ArrayList<Long> waybillIds;

    @Mapping
    @ApiModelProperty(value = "收银状态( 1=未收银 2= 已收银 )")
    private Integer cashierConfirmStatus;

}
