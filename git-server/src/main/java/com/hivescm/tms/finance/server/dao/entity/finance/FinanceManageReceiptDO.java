package com.hivescm.tms.finance.server.dao.entity.finance;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.math.BigDecimal;


@Data
@ToString
public class FinanceManageReceiptDO implements Serializable{


	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 收款网点ID
     */
    @Mapping
    @ApiModelProperty("收款网点ID")
    private Integer deliveryNetworkId;

    /**
     * 运单ID
     */
    @Mapping
    @ApiModelProperty("运单ID")
    private Long waybillId;

    /**
     * 来源单号
     */
    @Mapping
    @ApiModelProperty("来源单号")
    private String orderSourceCode;


    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("公司ID")
    private  Long companyId;

    /**
     * 单据类型
     */
    @Mapping
    @ApiModelProperty("单据类型")
    private Integer codeType;

    /**
     * 支付方式
     */
    @Mapping
    @ApiModelProperty("支付方式 4、现付 5、欠付 6、月结 7、回单付 8、到付 9、货款扣 10、代收货款 11、代收货款手续费 ")
    private Integer payWay;

    /**
     * 收银状态
     */
    @Mapping
    @ApiModelProperty("收银状态1.未审核 2.未收银 3.部分收银 4.已收银")
    private Integer deliveryStatus;

    /**
     * 应收金额
     */
    @Mapping
    @ApiModelProperty("应收金额")
    private BigDecimal receiptAmount;
    
    /**
     * 本次收款金额
     */
    @Mapping
    @ApiModelProperty("本次收款金额")
    private BigDecimal currentReceiptedAmount;

    /**
     * 已收金额
     */
    @Mapping
    @ApiModelProperty("已收金额")
    private BigDecimal receiptedAmount;

    /**
     * 未收金额
     */
    @Mapping
    @ApiModelProperty("未收金额")
    private BigDecimal unreceiptAmount;

    /**
     * 回单状态
     */
    @Mapping
    @ApiModelProperty("回单状态")
    private Integer backStatus;

    /**
     * 回单发放时间
     */
    @Mapping
    @ApiModelProperty("回单发放时间")
    private Long backGrantTime;

    /**
     * 签收状态
     */
    @Mapping
    @ApiModelProperty("签收状态")
    private Integer signStatus;

    /**
     * 签收时间
     */
    @Mapping
    @ApiModelProperty("签收时间")
    private Long signTime;

    /**
     * 创建时间
     */
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;


    /**
     * 创建人ID
     */
    @Mapping
    @ApiModelProperty("创建人ID")
    private Integer createUserId;

    /**
     * 修改人ID
     */
    @Mapping
    @ApiModelProperty("修改人ID")
    private  Integer updateUserId;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("修改时间")
    private  Long updateTime;

    /**
     * 修改人ID
     */
    @Mapping
    @ApiModelProperty("修改人ID")
    private  Integer checkUserId;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("修改时间")
    private  Long checkTime;

    /**
     * 修改人ID
     */
    @Mapping
    @ApiModelProperty("取消审核员ID")
    private  Integer cancelCheckUserId;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("取消审核时间")
    private  Long cancelCheckTime;


}
