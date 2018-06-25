package com.hivescm.tms.api.dto.es.finance;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 根据签收单信息生成收款单
 *
 * @author 杨彭伟
 * @date 2017-11-21 19:31
 */
@Data
@ToString
public class FinanceReceiptCreateDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Mapping
    @ApiModelProperty("userId")
    private Integer userId;
    @Mapping
    @ApiModelProperty(value = "公司id", required = true)
    private Long companyId;
    @Mapping
    @ApiModelProperty(value = "运单id", required = true)
    private Long waybillId;
    @Mapping
    @ApiModelProperty(value = "签收单", required = true)
    private SignEsDTO signEsDTO;
    @Mapping
    @ApiModelProperty(value = "结算方式(1.现金 2.扫码)", required = true)
    private Integer settlementMode;
    @Mapping
    @ApiModelProperty(value = "支付渠道")
    private String payChannel;
}
