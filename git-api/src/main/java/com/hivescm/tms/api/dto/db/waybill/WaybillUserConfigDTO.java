package com.hivescm.tms.api.dto.db.waybill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 李洪春
 * @since 2017/7/17 15:12
 */
@Data
@ToString
public class WaybillUserConfigDTO implements Serializable {
    /**
     * id
     */
    @Mapping
    private Integer id;

    /**
     * 用户id
     */
    @Mapping
    private Integer userId;

    /**
     * 公司id
     */
    @Mapping
    private Long companyId;

    /**
     * 默认货物名称
     */
    @Mapping
    @ApiModelProperty("默认货物名称")
    private String goodsName;

    /**
     * 默认货物数量
     */
    @Mapping
    @ApiModelProperty("默认货物数量")
    private Integer goodsAmount;

    /**
     * 默认包装类型
     */
    @Mapping
    @ApiModelProperty("默认包装类型")
    private Integer packageType;

    /**
     * 默认单价
     */
    @Mapping
    @ApiModelProperty("默认单价")
    private BigDecimal unitPrice;

    /**
     * 默认计价单位
     */
    @Mapping
    @ApiModelProperty("默认计价单位")
    private Integer priceUnit;

    /**
     * 默认配送方式
     */
    @Mapping
    @ApiModelProperty("默认配送方式")
    private Integer distributionMode;

    /**
     * 默认运输方式
     */
    @Mapping
    @ApiModelProperty("默认运输方式")
    private Integer transportMode;

    /**
     * 默认打印运单
     */
    @Mapping("isPrintWaybill")
    @ApiModelProperty("默认打印运单")
    private Boolean printWaybill;

    /**
     * 运单打印业务费
     */
    @Mapping("isWaybillPrintBusinessFee")
    @ApiModelProperty("运单打印业务费")
    private Boolean waybillPrintBusinessFee;

    /**
     * 不打印体积
     */
    @Mapping("isNotPrintVolume")
    @ApiModelProperty("不打印体积")
    private Boolean notPrintVolume;

    /**
     * 不打印重量
     */
    @Mapping("isNotPrintWeight")
    @ApiModelProperty("不打印重量")
    private Boolean notPrintWeight;

    /**
     * 不打印运费
     */
    @Mapping("isNotPrintFreight")
    @ApiModelProperty("不打印运费")
    private Boolean notPrintFreight;

    /**
     * 默认打印标签
     */
    @Mapping("isPrintTag")
    @ApiModelProperty("默认打印标签")
    private Boolean printTag;

    /**
     * 最大标签数
     */
    @Mapping
    @ApiModelProperty("最大标签数")
    private Integer tagMax;

    /**
     * 是否打印信封
     */
    @Mapping("isPrintEnvelop")
    @ApiModelProperty("是否打印信封")
    private Boolean printEnvelop;

    /**
     * 默认打印业务费
     */
    @Mapping("isPrintBusinessFee")
    @ApiModelProperty("默认打印业务费")
    private Boolean printBusinessFee;

    /**
     * 创建人
     */
    @Mapping
    @ApiModelProperty("操作人")
    private Integer createUser;

    /**
     * 创建时间
     */
    @Mapping
    @ApiModelProperty(hidden = true)
    private Long createTime;

    /**
     * 修改人
     */
    @Mapping
    @ApiModelProperty(hidden = true)
    private Integer updateUser;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty(hidden = true)
    private Long updateTime;

    private static final long serialVersionUID = 1L;

}