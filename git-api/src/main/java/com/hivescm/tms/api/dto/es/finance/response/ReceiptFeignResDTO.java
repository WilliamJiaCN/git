package com.hivescm.tms.api.dto.es.finance.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 增加boss创建收款单返回DTO
 * @author 杨彭伟
 * @date 2017-11-30 15:01
 */
@Data
@ToString
public class ReceiptFeignResDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Mapping
    @ApiModelProperty("付款单ID")
    private Long receiptId;
    @Mapping
    @ApiModelProperty("付款单编号")
    private String receiptCode;
    @Mapping
    @ApiModelProperty("付款单编号")
    private String waybillId;
    @Mapping
    @ApiModelProperty("付款金额")
    private BigDecimal payMoney;

}
