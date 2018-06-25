package com.hivescm.tms.api.dto.es.transport.redundancy;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 运输批次 运单到货信息
 * 用于更新运输批次明细的到货信息
 *
 * @author 李洪春
 * @since 2017/9/18 下午9:57
 */
@Data
@ToString
public class TransportWaybillArriveEsDTO implements Serializable {

    private static final long serialVersionUID = 5375752231583308113L;

    /**
     * 到货批次
     */
    @Mapping
    @ApiModelProperty("到货批次")
    private Long arrivalId;

    /**
     * 实收件数
     */
    @Mapping
    @ApiModelProperty("实收件数")
    private Integer actualAmount;

    /**
     * 到货仓库ID
     */
    @Mapping
    @ApiModelProperty("到货仓库ID")
    private Integer warehouseId;

    /**
     * 到货类型：正常到货、异常到货
     */
    @Mapping
    @ApiModelProperty("到货类型：正常到货、异常到货")
    private Integer arrivalType;

    /**
     * 到货备注信息，手动录入
     */
    @Mapping
    @ApiModelProperty("到货备注信息，手动录入")
    private String arrivalRemark;

    /**
     * 到货分摊成本
     */
    @Mapping
    @ApiModelProperty(value = "到货分摊成本", example = "111.11")
    private BigDecimal arrivalShareCost;
}
