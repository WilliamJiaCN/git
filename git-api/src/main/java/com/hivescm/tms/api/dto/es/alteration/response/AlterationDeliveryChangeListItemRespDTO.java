package com.hivescm.tms.api.dto.es.alteration.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.bill.alteration.AlterationProcessStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 改配 list element resp dto
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/21
 */
@Data
@ToString
public class AlterationDeliveryChangeListItemRespDTO {


    private static final long serialVersionUID = -4004646839313523459L;
    /**
     * 主键
     */
    @Mapping
    private @ApiModelProperty("主键") Long id;
    /**
     * 公司id
     */
    @Mapping
    private @ApiModelProperty("公司id") Long companyId;
    /**
     * 公司名称
     */
    @Mapping
    private @ApiModelProperty("公司名称") String companyName;
    /**
     * 更改批次
     */
    @Mapping
    private @ApiModelProperty("更改批次") String chengeBatchCode;
    /**
     * 运单号
     */
    private @ApiModelProperty("运单号") String waybillCode;

    /*********************更改内容*****************************************************/
    /**
     * 处理状态
     */
    private @ApiModelProperty("处理状态") AlterationProcessStatusEnum processingStatus;
    /**
     * 配送方式
     */
    @Mapping
    private @ApiModelProperty("配送方式") Integer distributionType;

    /**
     * 配送方式名称
     */
    @Mapping
    private @ApiModelProperty("配送方式名称") String distributionTypeName;

    /**
     * (新)收货人姓名
     */
    @Mapping
    private @ApiModelProperty("(新)收货人姓名") String newReceiptUser;
    /**
     * (新)收货公司
     */
//    @Mapping
//    private @ApiModelProperty("(新)收货公司") String newReceiptCompany;
    /**
     * (新)收货人电话
     */
    @Mapping
    private @ApiModelProperty("(新)收货人电话") String newReceiptConsignorTelNo;
    /**
     *  (新)收货人手机
     */
    @Mapping
    private @ApiModelProperty("(新)收货人手机") String newReceiptConsignorMobleNo;
    /**
     * (新)收货地址
     */
    @Mapping
    private @ApiModelProperty("(新)收货地址") String newReceiptAddress;
    /**
     * 备注
     */
    @Mapping
    private @ApiModelProperty("备注") String alterationRemark;
    /**
     * 到付送货费
     */
    @Mapping
    private @ApiModelProperty("送货费") BigDecimal deliveryFee;

    private @ApiModelProperty("到付送货费") BigDecimal toPayDeliveryFee;

    /**
     * 付款方
     */
    @Mapping
    private @ApiModelProperty("付款方式名称") String payWayName;
    /**
     * 付款方ID
     */
    @Mapping
    private @ApiModelProperty("付款方式") Integer payWay;

    /**
     * 付款方
     */
    @Mapping
    private @ApiModelProperty("付款方名称") String payUserName;
    /**
     * 付款方ID
     */
    @Mapping
    private @ApiModelProperty("付款方ID") Integer payUser;/**
     * 收款方
     */
    @Mapping
    private @ApiModelProperty("收款方") String receiveWayName;
    /**
     * 收款方ID
     */
    @Mapping
    private @ApiModelProperty("收款方") Integer receiveWay;
    /**
     * 申请人
     */
    @Mapping
    private @ApiModelProperty("申请人姓名") String applicantName;
    /**
     * 申请时间
     */
    @Mapping
    private @ApiModelProperty("申请时间") Long applicantTime;
    /**
     * 申请网点
     */
    @Mapping
    private @ApiModelProperty("申请网点") String applicantNetworkName;
    /**
     * 修改人
     */
    @Mapping
    private @ApiModelProperty("修改人姓名") String updateUserName;
    /**
     * 审核状态
     */
    @Mapping
    private @ApiModelProperty("审核状态") Integer checked;
    /**
     * 修改时间
     */
    @Mapping
    private @ApiModelProperty("修改时间") Long updateTime;
    /**
     * 确认人
     */
    @Mapping
    private @ApiModelProperty("确认人") String confirmUserName;
    /**
     * 确认时间
     */
    @Mapping
    private @ApiModelProperty("确认时间") Long confirmTime;

    /**
     * 创建人Id
     */
    @Mapping
    private @ApiModelProperty("创建人Id") Integer createUser;
    /**
     * 创建人姓名
     */
    @Mapping
    private @ApiModelProperty("创建人姓名") String createUserName;
    /**
     * 创建时间
     */
    @Mapping
    private @ApiModelProperty("创建时间") Long createTime;
    /**
     * 是否删除
     */
    @Mapping("isDelete")
    private @ApiModelProperty("是否删除") Boolean idelete;

    /*********************冗余运单*****************************************************/
    /**
     * （原）收货人
     */
    @Mapping
    private @ApiModelProperty("原收货人") String receiptUser;
    /**
     * （原）收货人ID
     */
    @Mapping
    private @ApiModelProperty("原收货人ID") String receiptUserId;
    /**
     *（原） 收货人电话
     */
    @Mapping
    private @ApiModelProperty("原收货人电话") String receiptConsignorTelNo;
    /**
     *（原） 收货人手机
     */
    @Mapping
    private @ApiModelProperty("原收货人手机") String receiptConsignorMobleNo;
//    /**
//     * (原)收货公司
//     */
//    @Mapping
//    private @ApiModelProperty("原收货公司") String receiptCompany;
    /**
     * (原)收货地址
     */
    @Mapping
    private @ApiModelProperty("原收货地址") String receiptAddress;
    /**
     * 发货网点名称
     */
    @Mapping
    private @ApiModelProperty("发货网点名称") String invoiceOrgName;
    /**
     * 运输线路名称
     */
    @Mapping
    private @ApiModelProperty("运输线路名称	") String lineName;
    /**
     * 到达网点名称
     */
    @Mapping
    private @ApiModelProperty("到达网点名称") String destOrgName;
    /**
     * 发货公司
     */
    private @ApiModelProperty("发货公司") String invoiceCompany;
    /**
     * 发货人
     */
    private @ApiModelProperty("发货人") String invoiceUser;
    /**
     * 发货人电话
     */
    private @ApiModelProperty("发货人电话") String invoiceTelNo;
    /**
     * 发货人手机号码
     */
    private @ApiModelProperty("发货人手机号码") String invoiceMobleNo;
    
    /**
     * 发货地址
     */
    @Mapping
	private @ApiModelProperty("发货人详细地址") String invoiceAddress;
    /**
     * 商品名称 ,“/”间隔
     */
    @Mapping
    private @ApiModelProperty("商品名称") String goodsName;
    /**
     * 包装名称,“/”间隔
     */
    private @Mapping @ApiModelProperty("包装名称") String packingName;
    /**
     * 总体积
     */
    @Mapping
    private @ApiModelProperty("总体积") BigDecimal volume;
    /**
     * 总重量
     */
    @Mapping
    private @ApiModelProperty("总重量") BigDecimal weight;
    /**
     * 总件数
     */
    @Mapping
    private @ApiModelProperty("总件数") Integer totalNum;
    /**
     * 运单备注
     */
    @Mapping
    private @ApiModelProperty("运单备注") String remark;
    /**
     * 录单员姓名
     */
    @Mapping
    private @ApiModelProperty("录单员姓名") String userName;
    /**
     * 录单时间
     */
    @Mapping
    private @ApiModelProperty("录单时间") Long operTime;}
