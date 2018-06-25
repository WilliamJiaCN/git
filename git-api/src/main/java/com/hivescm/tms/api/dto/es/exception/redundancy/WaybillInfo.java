package com.hivescm.tms.api.dto.es.exception.redundancy;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 异常单据冗余运单信息
 *
 * @author 李洪春
 * @since 2017/9/29 15:42
 */
@Data
@ToString
public class WaybillInfo implements Serializable {

    private static final long serialVersionUID = -6690752541879550664L;
    @ApiModelProperty("运单ID")
    private Long id;

    @ApiModelProperty("运单号")
    private String waybillCode;

    @Mapping
    @ApiModelProperty("录单时间")
    private Long createTime;

    @ApiModelProperty("始发地")
    private String name;

    @Mapping
    @ApiModelProperty("目的地")
    private String destName;

    @Mapping
    @ApiModelProperty("发货网点")
    private String invoiceOrgName;

    @Mapping
    @ApiModelProperty("目的网点")
    private String destOrgName;

    @Mapping
    @ApiModelProperty("发货人")
    private String invoiceUser;
    @Mapping
    @ApiModelProperty("发货人电话")
    private String invoiceTelNo;
    @Mapping
    @ApiModelProperty("发货人手机号码")
    private String invoiceMobleNo;

    @Mapping
    @ApiModelProperty("收货人")
    private String receiptUser;
    @Mapping
    @ApiModelProperty("收货人电话")
    private String receiptConsignorTelNo;
    @Mapping
    @ApiModelProperty("收货人手机号码")
    private String receiptConsignorMobleNo;

    @Mapping
    @ApiModelProperty("配送方式")
    private Integer distributionType;

    @Mapping
    @ApiModelProperty("商品名称")
    private String goodsName;

    @ApiModelProperty("包装名称")
    private String packingName;

    @Mapping
    @ApiModelProperty("总体积")
    private String volume;

    @Mapping
    @ApiModelProperty("总重量")
    private String weight;

    @Mapping
    @ApiModelProperty("总件数")
    private Integer totalNum;

    @Mapping
    @ApiModelProperty("回单要求")
    private String backType;

    @Mapping
    @ApiModelProperty("回单份数")
    private Integer backNum;

    @Mapping
    @ApiModelProperty("运输线路")
    private String lineName;


}
