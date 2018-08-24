package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 付款单账户查询参数
 * @author 杨彭伟
 * @date 2018-01-07 17:52
 */
@Data
@ToString
public class PaymentAccountReqDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Mapping
    @ApiModelProperty("当前网点id'")
    private Long curDotId;
    @Mapping
    @ApiModelProperty("经销商id")
    private Long dearId;
}
