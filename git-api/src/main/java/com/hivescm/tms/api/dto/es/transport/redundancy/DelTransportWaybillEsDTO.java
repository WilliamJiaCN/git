package com.hivescm.tms.api.dto.es.transport.redundancy;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/16 15:22
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DelTransportWaybillEsDTO implements Serializable {

    private static final long serialVersionUID = 9071005048070577818L;

    /**
     * 到货批次
     */
    @Mapping
    @ApiModelProperty("到货批次ID")
    private Long arrivalId;

    @ApiModelProperty("到货批次编码")
    private String arrivalBatch;

    /**
     * 实收件数
     */
    @Mapping
    @ApiModelProperty("实收件数")
    private Integer actualAmount;

    @Mapping
    @ApiModelProperty("实收重量")
    private BigDecimal actualWeight;

    @Mapping
    @ApiModelProperty("实收体积")
    private BigDecimal actualVolume;

    /**
     * 到货仓库ID
     */
    @Mapping
    @ApiModelProperty("到货仓库ID")
    private Integer warehouseId;

    /**
     * 到货仓库名称
     */
    @Mapping
    @ApiModelProperty("到货仓库名称")
    private String warehouseName;

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
     * 是否已经卸载
     */
    @Mapping
    @ApiModelProperty("是否已经卸载")
    private Boolean unload;

}
