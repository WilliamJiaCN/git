package com.hivescm.tms.api.dto.es.handlingorder.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author sql
 * @Date 10:442018\5\23 0023
 */
@Data
@ToString
public class HandlingOrderDetailPrintRespDTO implements Serializable {
    private static final long serialVersionUID = 4002427396438427910L;

    @Mapping
    @ApiModelProperty("运单号")
    private String code;

    @Mapping
    @ApiModelProperty("包装")
    private String packingName;
    @Mapping
    @ApiModelProperty("货物名称")
    private String goodsName;
    @Mapping
    @ApiModelProperty("装卸件数")
    private Integer packageNum;

    @Mapping
    @ApiModelProperty("装卸重量")
    private BigDecimal weight;

    @Mapping
    @ApiModelProperty("装卸体积")
    private BigDecimal volume;

    @Mapping
    @ApiModelProperty("摊分成本")
    private BigDecimal cost;
    @Mapping
    @ApiModelProperty("运费")
    private BigDecimal freight;
    @Mapping
    @ApiModelProperty("产值")
    private BigDecimal outputValue;
    @Mapping
    @ApiModelProperty("业务费")
    private BigDecimal busFee;
    @Mapping
    @ApiModelProperty("录单员")
    private String createUserName;
    @Mapping
    @ApiModelProperty("录单时间")
    private Long createTime;

    /**
     * 运单状态
     */
    private @Mapping({ "waybillStatus", "status" }) @ApiModelProperty("运单状态") Integer status;

    /**
     * 状态名称
     */
    @Mapping
    private @ApiModelProperty("运单状态名称") String statusName;

    /**
     * 总体积
     */
    private @Mapping @ApiModelProperty("总体积") BigDecimal totalVolume;
    /**
     * 总重量
     */
    private @Mapping @ApiModelProperty("总重量") BigDecimal totalWeight;
    /**
     * 总件数
     */
    private @Mapping @ApiModelProperty("总件数") Integer totalNum;
    /**
     * 目的地id
     */
    private @Mapping @ApiModelProperty("目的地id") String destId;
    /**
     * 目的地名称
     */
    private @Mapping @ApiModelProperty("目的地名称") String destName;
    /**
     * 运输线路id
     */
    private @Mapping @ApiModelProperty("运输线路id") Long lineId;
    /**
     * 运输线路名称
     */
    private @Mapping @ApiModelProperty("运输线路名称	") String lineName;
    /**
     * 回单要求（名称）
     */
    @Mapping
    private @ApiModelProperty("回单要求") String backType;

    @Mapping
    private @ApiModelProperty("回单要求") Integer backTypeValue;

    /**
     * 回单份数
     */
    private @Mapping @ApiModelProperty("回单份数") Integer backNum;
    /**
     * 回单编号
     */
    private @Mapping @ApiModelProperty("回单编号") String backCode;

    /**
     * 发货网点ID
     */
    private @Mapping @ApiModelProperty("发货网点ID") Integer invoiceOrgId;
    /**
     * 发货网点名称
     */
    private @Mapping @ApiModelProperty("发货网点名称") String invoiceOrgName;

    /**
     * 到达网点id
     */
    private @Mapping @ApiModelProperty("到达网点id") Integer destOrgId;
    /**
     * 到达网点名称
     */
    private @Mapping @ApiModelProperty("到达网点名称	") String destOrgName;

}
