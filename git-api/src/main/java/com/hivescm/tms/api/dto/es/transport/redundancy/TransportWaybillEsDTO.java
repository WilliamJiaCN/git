package com.hivescm.tms.api.dto.es.transport.redundancy;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 运输批次明细冗余运单属性
 *
 * @author 李洪春
 * @since 2017/8/17 11:32
 */
@Data
@ToString
public class TransportWaybillEsDTO implements Serializable {


    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6497329597770823937L;

    /**
     * 运单ID
     */
    @ApiModelProperty("运单ID")
    private Long id;

    /**
     * 发货网点ID
     */
    @Mapping
    @ApiModelProperty("发货网点ID")
    private Integer invoiceOrgId;

    /**
     * 发货网点名称
     */
    @Mapping
    @ApiModelProperty("发货网点名称")
    private String invoiceOrgName;
    /**
     * 目的地级别
     */
    @Mapping
    @ApiModelProperty("目的地级别")
    private Integer destLevel;
    /**
     * 目的地id
     */
    @Mapping
    @ApiModelProperty("目的地id")
    private Long destId;
    /**
     * 目的地名称
     */
    @Mapping
    @ApiModelProperty("目的地名称")
    private String destName;
    /**
     * 运输线路id
     */
    @Mapping("waybillLineId")
    @ApiModelProperty("运输线路id")
    private Long lineId;
    /**
     * 运输线路名称
     */
    @Mapping("waybillLineName")
    @ApiModelProperty("运输线路名称")
    private String lineName;
    /**
     * 到达网点id
     */

    @Mapping
    @ApiModelProperty("到达网点id")
    private Integer destOrgId;

    /**
     * 到达网点名称
     */
    @Mapping
    @ApiModelProperty("到达网点名称")
    private String destOrgName;

    /**
     * 运单编码
     */
    @Mapping
    @ApiModelProperty("运单编码")
    private String waybillCode;

    /**
     * 客户单号
     */
    @Mapping
    @ApiModelProperty("客户单号")
    private String customerOrderCode;

    /**
     * 预约提货时间
     */
    @Mapping
    @ApiModelProperty("预约提货时间")
    private Long deliveryPickTime;
    /**
     * 预约送货时间
     */
    @Mapping
    @ApiModelProperty("预约送货时间")
    private Long deliverySendTime;

    /**
     * 发货人
     */
    @Mapping
    @ApiModelProperty("发货人")
    private String invoiceUser;
    /**
     * 发货单位
     */
    @Mapping
    @ApiModelProperty("发货公司")
    private String invoiceCompany;

    /**
     * 发货人微信号
     */
    @Mapping
    @ApiModelProperty("发货人微信号	")
    private String invoiceWxNo;
    /**
     * 发货人电话
     */
    @Mapping
    @ApiModelProperty("发货人电话")
    private String invoiceTelNo;
    /**
     * 发货人手机号码
     */
    @Mapping
    @ApiModelProperty("发货人手机号码")
    private String invoiceMobleNo;
    /**
     * 发货人国家ID
     */
    @Mapping
    @ApiModelProperty("发货人国家ID")
    private String invoiceCountryId;
    /**
     * 发货人国家名称
     */
    @Mapping
    @ApiModelProperty("发货人国家名称")
    private String invoiceCountryName;
    /**
     * 发货人省ID
     */
    @Mapping
    @ApiModelProperty("发货人省ID")
    private String invoiceProvId;
    /**
     * 发货人省名称
     */
    @Mapping
    @ApiModelProperty("发货人省名称")
    private String invoiceProvName;
    /**
     * 发货人城市ID
     */
    @Mapping
    @ApiModelProperty("发货人城市ID")
    private String invoiceCityId;
    /**
     * 发货人城市名称
     */
    @Mapping
    @ApiModelProperty("发货人城市名称")
    private String invoiceCityName;
    /**
     * 发货人地区ID
     */
    @Mapping
    @ApiModelProperty("发货人地区ID")
    private String invoiceDistrictId;
    /**
     * 发货人地区名称
     */
    @Mapping
    @ApiModelProperty("发货人地区名称")
    private String invoiceDistrictName;
    /**
     * 发货人街道ID
     */
    @Mapping
    @ApiModelProperty("发货人街道ID")
    private String invoiceStreetId;
    /**
     * 发货人街道名称
     */
    @Mapping
    @ApiModelProperty("发货人街道名称")
    private String invoiceStreetName;
    /**
     * 发货人详细地址
     */
    @Mapping
    @ApiModelProperty("发货人详细地址")
    private String invoiceAddress;
    /**
     * 收货方ID
     */
    @Mapping
    @ApiModelProperty("收货方ID")
    private Integer receiptCustomerId;
    /**
     * 收货人
     */
    @Mapping
    @ApiModelProperty("收货人")
    private String receiptUser;
    /**
     * 收货单位
     */
    @Mapping
    @ApiModelProperty("收货单位")
    private String receiptCompany;
    /**
     * 收货人身份证号码
     */
    @Mapping
    @ApiModelProperty("收货人身份证号码")
    private String receiptIdentityCard;
    /**
     * 收货人电话
     */
    @Mapping
    @ApiModelProperty("收货人电话")
    private String receiptConsignorTelNo;
    /**
     * 收货人手机号码
     */
    @Mapping
    @ApiModelProperty("收货人手机号码")
    private String receiptConsignorMobleNo;
    /**
     * 收货人国家ID
     */
    @Mapping
    @ApiModelProperty("收货人国家ID")
    private String receiptCountryId;
    /**
     * 收货人国家名称
     */
    @Mapping
    @ApiModelProperty("收货人国家名称")
    private String receiptCountryName;
    /**
     * 收货人省ID
     */
    @Mapping
    @ApiModelProperty("收货人省ID")
    private String receiptProvId;
    /**
     * 收货人省名称
     */
    @Mapping
    @ApiModelProperty("收货人省名称	")
    private String receiptProvName;
    /**
     * 收货人城市ID
     */
    @Mapping
    @ApiModelProperty("收货人城市ID")
    private String receiptCityId;
    /**
     * 收货人城市名称
     */
    @Mapping
    @ApiModelProperty("收货人城市名称")
    private String receiptCityName;
    /**
     * 收货人地区ID
     */
    @Mapping
    @ApiModelProperty("收货人地区ID")
    private String receiptDistrictId;
    /**
     * 收货人地区名称
     */
    @Mapping
    @ApiModelProperty("收货人地区名称")
    private String receiptDistrictName;
    /**
     * 收货人街道ID
     */
    @Mapping
    @ApiModelProperty("收货人街道ID")
    private Integer receiptStreetId;
    /**
     * 收货人街道名称
     */
    @Mapping
    @ApiModelProperty("收货人街道名称")
    private String receiptStreetName;
    /**
     * 收货人详细地址
     */
    @Mapping
    @ApiModelProperty("收货人详细地址")
    private String receiptAddress;

    /**
     * 业务日期
     */
    @Mapping
    @ApiModelProperty("业务日期")
    private Long bussTime;

    /**
     * 下单日期
     */
    @Mapping
    @ApiModelProperty("下单日期")
    private Long orderDate;

    /**
     * 接单时间
     */
    @Mapping
    @ApiModelProperty("接单日期")
    private Long acceptDate;


    /**
     * 运单状态
     */
    @Mapping("waybillStatus")
    @ApiModelProperty("运单状态")
    private Integer status;

}
