package com.hivescm.tms.finance.server.dao.entity.sign;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 拒簽列表
 *
 * @author 王小雪
 */
@Data
@ToString
public class SignRefuseDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 拒收id主键
     */
    @Mapping
    @ApiModelProperty("拒收id主键")
    private Long id;

    /**
     * 拒收单号
     */
    @Mapping
    @ApiModelProperty("拒收单号")
    private String refuseCode;

    /**
     * 拒收单的对应签收单类型
     */
    @Mapping
    @ApiModelProperty("拒收单的对应签收单类型")
    private Integer refuseType;

    /**
     * 销退单id
     */
    @Mapping
    @ApiModelProperty("销退单id")
    private Long salesReturnId;

    /**
     * 销退单对应的运单id
     */
    @Mapping
    @ApiModelProperty("销退单对应的运单id")
    private Long refuseWaybillId;


    /**
     * 签收单号
     */
    @Mapping
    @ApiModelProperty("签收单号")
    private Long signId;

    /**
     * 拒收时间
     */
    @Mapping
    @ApiModelProperty("拒收时间")
    private Date refuseTime;

    /**
     * 拒收人
     */
    @Mapping({"refusePeople", "receiptUser"})
    @ApiModelProperty("拒收人")
    private String refusePeople;

    /**
     * 拒收人电话
     */
    @Mapping({"refusePhone", "receiptConsignorTelNo"})
    @ApiModelProperty("拒收人电话")
    private String refusePhone;

    /**
     * 拒收人身份证号码
     */
    @Mapping({"refuseCard", "receiptIdentityCard"})
    @ApiModelProperty("拒收人身份证号码")
    private String refuseCard;

    /**
     * 运单id
     */
    @Mapping
    @ApiModelProperty("运单id")
    private Long waybillId;

    /**
     * 订单编号
     */
    @Mapping
    @ApiModelProperty("订单编号")
    private String orderCode;

    /**
     * 承运商名称
     */
    @Mapping
    @ApiModelProperty("承运商名称")
    private String carrierName;

    /**
     * 原订单收货款金额
     */
    @Mapping
    @ApiModelProperty("原订单收货款金额")
    private BigDecimal orderPay;

    /**
     * 当有代收款时 拒收部分商品对应的货款金额
     */
    @Mapping
    @ApiModelProperty("当有代收款时 拒收部分商品对应的货款金额")
    private BigDecimal refusePay;

    /**
     * 运单的配送方式类型
     */
    @Mapping
    @ApiModelProperty("运单的配送方式类型")
    private Integer distributionType;

    /**
     * 运单派送的批次id
     */
    @Mapping
    @ApiModelProperty("运单派送的批次id")
    private Long dispacherCode;

    /**
     * 发车网点 运单派送的网点名称
     */
    @Mapping
    @ApiModelProperty("发车网点 运单派送的网点名称")
    private String invoicewayName;

    /**
     * 派送运单的车辆车牌号码
     */
    @Mapping
    @ApiModelProperty("派送运单的车辆车牌号码")
    private String carNumber;

    /**
     * 司机名称
     */
    @Mapping({"carName", "driverName"})
    @ApiModelProperty("司机名称")
    private String carName;

    /**
     * 司机手机号码
     */
    @Mapping({"carPhone", "phoneNo"})
    @ApiModelProperty("司机手机号码")
    private String carPhone;

    /**
     * 发货公司 经销商名称
     */
    @Mapping
    @ApiModelProperty("发货公司 经销商名称")
    private String invoiceCompany;

    /**
     * 经销商发货人姓名
     */
    @Mapping({"invoiceName", "invoiceUser"})
    @ApiModelProperty("经销商发货人姓名")
    private String invoiceName;

    /**
     * 经销商发货人联系电话
     */
    @Mapping({"invoiceNamePhone", "invoiceMobleNo"})
    @ApiModelProperty("经销商发货人联系电话")
    private String invoiceNamePhone;

    /**
     * 发货仓库地址
     */
    @Mapping
    @ApiModelProperty("发货仓库地址")
    private String invoiceAddress;

    /**
     * 是否装车
     */
    @Mapping
    @ApiModelProperty("是否装车")
    private Boolean loaded;

    /**
     * 公司id
     */
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;

    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;


}