package com.hivescm.tms.api.dto.es.outbill;

import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.elasticsearch.annotation.Cascade;
import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
@Cascade(value = "destName", fields = { "receiptProvName", "receiptCityName", "receiptDistrictName" })
public class OutBillDetailEsDTO {
	@Mapping({"OutBillWaybillDTO.outWaybillId","OutBillDetailDO.id","StorageInfoForPrintDTO.id"})
	@ApiModelProperty("主键ID")
	private Long id;
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
	
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
    @ApiModelProperty("外发网点")
    private String outBranchName;

    @Mapping
    @ApiModelProperty("外发公司名称")
    private String outCompanyName;
    
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
	@ApiModelProperty("运单商品名称")
    private String waybillGoodsNames;
	
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
	@ApiModelProperty("分摊方式")
    private Integer feeShareType;
	
	@Mapping
	@ApiModelProperty("分摊方式名称")
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
	@ApiModelProperty("中转结算方式 1,现结 2,欠款3,回单结")
    private Integer transitSettlementType;
	
	@Mapping
	@ApiModelProperty("中转结算方式名称")
    private String transitSettlementTypeName;
	
	private @Mapping  @ApiModelProperty("实际运费")BigDecimal totalFee;
	
	@Mapping
    private Integer createUser;
	@Mapping
    private String createUserName;
	@Mapping
    private Long createTime;
	@Mapping
    private String updateUserName;
	@Mapping
    private Integer updateUser;
	@Mapping
    private Long updateTime;
	
	@ApiModelProperty("外发商品信息")
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
	
	@Mapping
	@ApiModelProperty("外发状态")
    private Integer outStatus;
	
	@Mapping
	@ApiModelProperty("外发状态名称")
    private String outStatusName;
	
	@Mapping
	@ApiModelProperty("外发确认人")
    private String confirmUserName;
	
	@Mapping
	@ApiModelProperty("外发确认时间")
    private Long confirmTime;
	
	@Mapping
	@ApiModelProperty("支付方式")
    private Integer paymentType;
	
	@Mapping
	@ApiModelProperty("支付方式名称")
    private String paymentTypeName;
	/********************************外发入库 需要同步的字段********************************/
	
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
    @ApiModelProperty("入库状态名称")
    private String storageStatusName;
    
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
	@ApiModelProperty("仓库ID")
    private Long warehouseId;
    
    @Mapping
	@ApiModelProperty("仓库名称")
    private String warehouseName;
    
    /**********************************签收相关******************************/
    
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
	@ApiModelProperty("经办人（系统操作员）名称")
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
    
    /**
	 * 收货人省名称		
	 */
	private @Mapping  @ApiModelProperty("收货人省名称")String receiptProvName;
	/**
	 * 收货人城市名称		
	 */
	private @Mapping  @ApiModelProperty("收货人城市名称")String receiptCityName;
	/**
	 * 收货人地区名称		
	 */
	private @Mapping  @ApiModelProperty("收货人地区名称")String receiptDistrictName;
	
	/**
	 * 到达网点id	
	 */
	private @Mapping  @ApiModelProperty("到达网点id")Integer destOrgId;
	/**
	 * 到达网点名称	
	 */
	private @Mapping  @ApiModelProperty("到达网点名称	")String destOrgName;
  
}
