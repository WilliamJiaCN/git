package com.hivescm.tms.api.dto.es.sign.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/4/21
 */
@Data
@ToString
public class SignRefuseRespDTO extends SignRefuseEsDTO {

    private List<SignDetailsEsDTO> refuseDetail;


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
     * 签收单号
     */
    @Mapping
    @ApiModelProperty("签收单号")
    private Long signId;

    @Mapping
    @ApiModelProperty("签收批次号")
    private String signCode;

    /**
     * 拒收时间
     */
    @Mapping
    @ApiModelProperty("拒收时间")
    private Date refuseTime;

    /**
     * 拒收人
     */
    @Mapping
    @ApiModelProperty("拒收人")
    private String refusePeople;

    /**
     * 拒收人电话
     */
    @Mapping
    @ApiModelProperty("拒收人电话")
    private String refusePhone;

    /**
     * 拒收人身份证号码
     */
    @Mapping
    @ApiModelProperty("拒收人身份证号码")
    private String refuseCard;

    /**
     * 运单id
     */
    @Mapping
    @ApiModelProperty("运单id")
    private Long waybillId;
    /**
     * 运单号
     */
    @Mapping
    private @ApiModelProperty("运单号") String code;
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
     * 订单支付金额(原代收货款 + 运费 - 销退金额 = 订单支付金额)
     */
    @Mapping
    @ApiModelProperty("订单支付金额")
    private BigDecimal orderPay;

    /**
     * 销退单对应的运单id
     */
    @Mapping
    @ApiModelProperty("销退单对应的运单id")
    private Long refuseWaybillId;
    /**
     * 当有代收款时 拒收部分商品对应的货款金额
     */
    @Mapping
    @ApiModelProperty("当有代收款时 拒收部分商品对应的货款金额")
    private BigDecimal refusePay;

    @Mapping
    @ApiModelProperty("原代收货款")
    private BigDecimal collectPayment;

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
     * 派送的批次号
     */
    @Mapping
    @ApiModelProperty("派送的批次号")
    private String batchCode;
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
    @Mapping
    @ApiModelProperty("司机名称")
    private String carName;

    /**
     * 司机手机号码
     */
    @Mapping
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
    @Mapping
    @ApiModelProperty("经销商发货人姓名")
    private String invoiceName;

    /**
     * 经销商发货人联系电话
     */
    @Mapping
    @ApiModelProperty("经销商发货人联系电话")
    private String invoiceNamePhone;



    /**
     * 发货仓库地址
     */
    @Mapping
    @ApiModelProperty("发货仓库地址")
    private String invoiceAddress;

    /**
     * 目的网点id
     */
    @Mapping
    @ApiModelProperty("目的网点id")
    private Integer destOrgId;
    /**
     * 目的地名称
     */
    @Mapping
    @ApiModelProperty("收货地址")
    private String destName;
    /**
     * 到达网点名称
     */
    @Mapping
    @ApiModelProperty("收货公司")
    private String destOrgName;

    @Mapping
    @ApiModelProperty("货物名称")
    private String goodsName;

    @Mapping
    @ApiModelProperty("开单件数")
    private Integer createNumber;
    @Mapping
    @ApiModelProperty("派送件数")
    private Integer dispatcherNumber;
    @Mapping
    @ApiModelProperty("签收件数")
    private Integer signNumber;
    @Mapping
    @ApiModelProperty("拒收件数")
    private Integer refuseNumber;
    @Mapping
    @ApiModelProperty("总方量(体积)")
    private BigDecimal volume;
    @Mapping
    @ApiModelProperty("总重量")
    private BigDecimal weight;

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

    @Mapping
    @ApiModelProperty("创建人名称")
    private  String createUserName;
    @Mapping
    @ApiModelProperty("修改人名称")
    private  String updateUserName;

    /********************************* 拓展字段 ***********************************/
    @Mapping
    @ApiModelProperty("收款方式(1=现金支付,2=二维码支付)")
    private Integer settlementMethod;
    @Mapping
    @ApiModelProperty("司机id")
    private Long driverId;
    @Mapping
    @ApiModelProperty("派车单id")
    private Long dispatcherId;
    @Mapping
    @ApiModelProperty("发货方Id")
    private Integer invoiceCustomerId;
    @Mapping
    @ApiModelProperty("派车网点名称")
    private String branchName;

    @Mapping
    @ApiModelProperty("派车网点Id")
    private Integer branchId;

    /*********************************** 运单字段 **********************************/
    @Mapping
    @ApiModelProperty("仓储服务商名称")
    private String warehouseServerName;// 冗余
    @Mapping
    @ApiModelProperty("仓库名称")
    private String warehouseName;// 冗余
    @Mapping
    @ApiModelProperty("商户名称")
    private String merchantName;// 冗余
    @Mapping
    @ApiModelProperty("门店名称")
    private String storeName;
    private @Mapping @ApiModelProperty("收货人") String receiptUser;
    /**
     * 收货人电话
     */
    private @Mapping  @ApiModelProperty("收货人电话")String receiptConsignorTelNo;

    private @Mapping  @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;

    private @Mapping  @ApiModelProperty("收货人详细地址") String receiptAddress;



    private @Mapping @ApiModelProperty("装车件数") Integer packageNum;

}
