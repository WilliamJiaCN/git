package com.hivescm.tms.api.dto.es.outbill.resp;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class OutBillTrackWaybillEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 运单号
     */
    @Logger
    private @Mapping
    @ApiModelProperty("运单号")
    String waybillCode;
    /**
     * 发货网点ID
     */
    private @Mapping
    @ApiModelProperty("发货网点ID")
    Integer invoiceOrgId;
    /**
     * 发货网点名称
     */
    private @Mapping
    @ApiModelProperty("发货网点名称")
    String invoiceOrgName;
    /**
     * 到达网点id
     */
    private @Mapping
    @ApiModelProperty("到达网点id")
    Integer destOrgId;
    /**
     * 到达网点名称
     */
    private @Mapping
    @ApiModelProperty("到达网点名称	")
    String destOrgName;
    /**
     * 录单时间
     */
    private @Mapping
    @ApiModelProperty("录单时间")
    Long createTime;
    /**
     * 发货单位
     */
    private @Mapping
    @ApiModelProperty("发货单位")
    String invoiceCompany;
    /**
     * 收货方名称
     */
    private @Mapping
    @ApiModelProperty("收货单位")
    String receiptCompany;
    /**
     * 收货人
     */
    private @Mapping
    @ApiModelProperty("收货人")
    String receiptUser;
    /**
     * 发货人手机
     */
    private @Mapping
    @ApiModelProperty("发货人手机")
    String invoiceMobleNo;
    /**
     * 发货地址
     */
    private @Mapping
    @ApiModelProperty("发货地址")
    String invoiceAddress;
    /**
     * 发货人
     */
    private @Mapping
    @ApiModelProperty("发货人")
    String invoiceUser;
    /**
     * 收货人手机
     */
    private @Mapping
    @ApiModelProperty("收货人手机")
    String receiptConsignorMobleNo;
    /**
     * 收货地址
     */
    private @Mapping
    @ApiModelProperty("收货地址")
    String receiptAddress;
    /**
     * 总件数
     */
    private @Mapping
    @ApiModelProperty("总件数")
    Integer totalNum;
    /**
     * 总重量
     */
    private @Mapping
    @ApiModelProperty("总重量")
    BigDecimal weight;
    /**
     * 总体积
     */
    private @Mapping
    @ApiModelProperty("总体积")
    BigDecimal volume;
}
