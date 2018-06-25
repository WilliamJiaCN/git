package com.hivescm.tms.api.dto.es.handlingorder.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/25 14:35
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class PickHandlingOrderDetailRespDTO implements Serializable{

    private static final long serialVersionUID = -7686953176549554664L;

    @Mapping
    @ApiModelProperty("运单id")
    private Long waybillId;

    /**
     * 运单编码
     */
    @ApiModelProperty("运单编码")
    private String waybillCode;

    /**
     * 装车件数
     */
    @ApiModelProperty("装车件数")
    private Integer loadAmount;

    /**
     * 装车重量
     */
    @ApiModelProperty("装车重量")
    private BigDecimal loadWeight;

    /**
     * 装车体积
     */
    @ApiModelProperty("装车体积")
    private BigDecimal loadVolume;


    @Mapping
    @ApiModelProperty("收货人")
    private Long dealerId;

    @Mapping
    @ApiModelProperty("收货人")
    private String dealerName;

    @Mapping
    @ApiModelProperty("收货公司")
    private String receiptCompany;
    @Mapping
    @ApiModelProperty("收货地址")
    private String receiptAddress;

    @Mapping
    @ApiModelProperty("货物名称")
    private String goodsName;

    @Mapping
    @ApiModelProperty("包装")
    private String packingName;


    @Mapping
    @ApiModelProperty("运费")
    private BigDecimal freight;
    @Mapping
    @ApiModelProperty("产值")
    private BigDecimal outputValue;
    @Mapping
    @ApiModelProperty("业务费")
    private BigDecimal businessFee;

    /**
     * 运单状态
     */
    @ApiModelProperty("运单状态")
    private Integer waybillStatus;

    @ApiModelProperty("运单状态名称")
    private String statusName;

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
    private @Mapping @ApiModelProperty("运输线路名称") String lineName;
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
    private @Mapping @ApiModelProperty("到达网点名称") String destOrgName;

}
