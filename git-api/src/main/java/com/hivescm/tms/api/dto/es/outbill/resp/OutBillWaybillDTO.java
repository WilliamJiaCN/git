package com.hivescm.tms.api.dto.es.outbill.resp;

import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.outbill.OutBillGoodsEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OutBillWaybillDTO {
	@Mapping
	@ApiModelProperty("外发明细ID")
	private Long outWaybillId;
	@Mapping
	@ApiModelProperty("公司ID")
	private Integer companyId;

	@Mapping
	@ApiModelProperty("运单号")
	private String waybillCode;

	@Mapping
	@ApiModelProperty("运单id")
	private Long waybillId;

	@Mapping
	@ApiModelProperty("外发单id")
	private Long outbillId;

	@Mapping
	@ApiModelProperty("外发批次")
	private String outbillCode;

	@Mapping
	@ApiModelProperty("外发单号")
	private String outbillNum;

	@Mapping
	@ApiModelProperty("外发网点id")
	private Integer outBranchId;

	@Mapping
	@ApiModelProperty("外发公司id")
	private String outCompanyId;
	
	@Mapping
    @ApiModelProperty("外发网点")
    private String outBranchName;

    @Mapping
    @ApiModelProperty("外发公司名称")
    private String outCompanyName;
    
    @Mapping
	@ApiModelProperty("外发状态名称")
    private String outStatusName;

	@Mapping
	@ApiModelProperty("是否入库")
	private Boolean inStorage;

	@Mapping
	@ApiModelProperty("入库网点")
	private Integer inBranchId;
	
	@Mapping
	@ApiModelProperty("入库网点名称")
    private String inBranchName;

	@Mapping
	@ApiModelProperty("运单商品id")
	private Long waybillGoodsId;

	@Mapping
	@ApiModelProperty("件数")
	private Integer packageNum;

	@Mapping
	@ApiModelProperty("体积")
	private BigDecimal volume;

	@Mapping
	@ApiModelProperty("重量")
	private BigDecimal weight;

	@Mapping
	@ApiModelProperty("中转分摊方式")
	private Integer feeShareType;
	
	@Mapping
	@ApiModelProperty("中转分摊方式名称")
	private String feeShareTypeName;

	@Mapping
	@ApiModelProperty("应收")
	private BigDecimal accountsReceivable;

	@Mapping
	@ApiModelProperty("应付")
	private BigDecimal accountsCost;

	@Mapping
	@ApiModelProperty("中转运费")
	private BigDecimal transitFreight;

	@Mapping
	@ApiModelProperty("中转提货费")
	private BigDecimal transitTake;

	@Mapping
	@ApiModelProperty("中转送货费")
	private BigDecimal transitSend;

	@Mapping
	@ApiModelProperty("中转其他费用")
	private BigDecimal transitOther;

	@Mapping
	@ApiModelProperty("中转总费用")
	private BigDecimal transitAll;

	@Mapping
	@ApiModelProperty("支付方式")
	private Integer paymentType;
	
	@Mapping
	@ApiModelProperty("中转结算方式 1,现结 2,月结")
    private Integer transitSettlementType;
	
	@Mapping
	@ApiModelProperty("中转结算方式名称")
    private String transitSettlementTypeName;

	@Mapping
	private Integer createUser;
	@Mapping
	private Long createTime;
	@Mapping
	private Integer updateUser;
	@Mapping
	private Long updateTime;

	private List<OutBillGoodsEsDTO> outBillGoods;

	@Mapping
	@ApiModelProperty("运费")
	private BigDecimal freightFee;

	@Mapping
	@ApiModelProperty("产值")
	private BigDecimal productFee;

	@Mapping
	@ApiModelProperty("业务费")
	private BigDecimal businessFee;

	@Mapping
	@ApiModelProperty("到付")
	private BigDecimal toPay;

	@Mapping
	@ApiModelProperty("代收货款")
	private BigDecimal pay;

	@Mapping
	@ApiModelProperty("外发备注")
    private String detailRemark;

	/********************************
	 * 外发入库 需要同步的字段
	 ********************************/

	@Mapping
	@ApiModelProperty("入库单ID")
	private Long storageId;

	@Mapping
	@ApiModelProperty("入库类型")
	private Integer storageType;
	
	@Mapping
	@ApiModelProperty("入库类型名称")
	private String storageTypeName;

	@Mapping
	@ApiModelProperty("入库批次")
	private String storageCode;

	@Mapping
	@ApiModelProperty("入库状态")
	private Integer storageStatus;

	@Mapping
	@ApiModelProperty("入库时间")
	private Long storageTime;

	@Mapping
	@ApiModelProperty("入库备注")
	private String storageRamark;

	@Mapping
	@ApiModelProperty("实收件数")
	private Integer realPackageNum;

	@Mapping
	@ApiModelProperty("仓库名称")
	private String warehouseName;

	/******************************** 运单字段 ********************************/
	@Mapping
	@ApiModelProperty("收货人城市名称")
	private String receiptCityName;

	@Mapping
	@ApiModelProperty("运单录单时间")
	private Long waybillCreateTime;

	@Mapping
	@ApiModelProperty("发货网点名称")
	private String invoiceOrgName;

	@Mapping
	@ApiModelProperty("到达网点名称")
	private String destOrgName;

	@Mapping
	@ApiModelProperty("运单状态名称")
	private String statusName;

	@Mapping
	@ApiModelProperty("运单备注")
	private String waybillRremark;

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
	@ApiModelProperty("发货人详细地址")
	private String invoiceAddress;
	
	/**
	 * 发货方名称		
	 */
	private @Mapping  @ApiModelProperty("发货公司")String invoiceCompany;

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
	@ApiModelProperty("收货人详细地址")
	private String receiptAddress;
	
	/**
	 * 收货方名称	
	 */
	private @Mapping  @ApiModelProperty("收货公司")String receiptCompany;
	
	@Mapping
	@ApiModelProperty("运单商品名称")
    private String waybillGoodsNames;
	
	@Mapping
	private @ApiModelProperty("目的地名称")String destName;
	
	private @Mapping @ApiModelProperty("回单要求") String backType;
	
	private @Mapping  @ApiModelProperty("回单份数") Integer backNum;
	
	private @Mapping  @ApiModelProperty("回单编号")String backCode;
	
    private @Mapping  @ApiModelProperty("开单总件数") Integer totalNum;
	
	private @Mapping  @ApiModelProperty("累计签收件数")Integer totalSignNumber;
	
	private @Mapping  @ApiModelProperty("累计拒签件数")Integer totalRefuseSignNumber;

	/******************************** 签收相关 ********************************/

	@Mapping
	@ApiModelProperty("签收单号")
	private String signNum;

	@Mapping
	@ApiModelProperty("签收人")
	private String signName;

	@Mapping
	@ApiModelProperty("签收人手机")
	private String signMobile;

	@Mapping
	@ApiModelProperty("签收人身份证号")
	private String signIdentityCard;

	@Mapping
	@ApiModelProperty("签收时间")
	private Long signTime;

	@Mapping
	@ApiModelProperty("签收类型")
	private Integer signType;

	@Mapping
	@ApiModelProperty("签收类型名称")
	private String signTypeName;

	@Mapping
	@ApiModelProperty("签收说明")
	private String signRemark;

	@Mapping
	@ApiModelProperty("签收照片")
	private String signPicture;

	@Mapping
	@ApiModelProperty("经办人（系统操作员）")
	private Integer signOperator;
	
	@Mapping
	@ApiModelProperty("经办人（系统操作员）")
	private String signOperatorName;

	@Mapping
	@ApiModelProperty("签收状态")
	private Integer signStatus;

	@Mapping
	@ApiModelProperty("签收状态名称")
	private String signStatusName;

	@Mapping
	@ApiModelProperty("签收件数")
	private Integer signCount;

	@Mapping
	@ApiModelProperty("拒签件数")
	private Integer refusePackageNum;

	@Mapping
	@ApiModelProperty("拒签原因")
	private String refuseSignReason;
	
	private @Mapping  @ApiModelProperty("库存件数") Integer stockPackageNum;
	
	private @ApiModelProperty("是否等通知")Boolean iwaitNotice;
	private @ApiModelProperty("是否加急")Boolean iemergency;
	private @ApiModelProperty("是否VIP客户") Boolean vip;
	private @ApiModelProperty("是否为异常单") Boolean exception;
	private @Mapping  @ApiModelProperty("是否修改") Boolean isUpdate;
	private @Mapping  @ApiModelProperty("是否拆单") Boolean isDismantling;
}
