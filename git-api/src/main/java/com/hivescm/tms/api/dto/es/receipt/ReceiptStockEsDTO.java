package com.hivescm.tms.api.dto.es.receipt;

import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptExceptionStatusEnum;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptRequirmentTypeEnum;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptSignStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 回单库存
 * @author ke.huang
 * @date 2018年3月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptStockEsDTO implements Serializable,Cloneable{
	private static final long serialVersionUID = 1263778070945502278L;
	@Logger @Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Logger @Mapping
	@ApiModelProperty("公司ID")
	private Long companyId;
	@Logger @Mapping
	@ApiModelProperty("运单号")
    private String waybillCode;
	@Logger @Mapping
	@ApiModelProperty("运单ID")
    private Long waybillId;
	@ApiModelProperty("回单要求")
    private ReceiptRequirmentTypeEnum requirement;
	@Mapping
	@ApiModelProperty("回单份数")
    private Integer num;
	@Logger @Mapping
	@ApiModelProperty("回单编号")
    private String code;
	@Mapping
	@ApiModelProperty("回单状态")
    private String status;
	@Mapping
	@ApiModelProperty("库存网点")
    private Long stockOrgId;
	@Mapping
	@ApiModelProperty("回单图片")
    private String pic;
	@ApiModelProperty("签收状态")
    private ReceiptSignStatusEnum signStatus;
	@Mapping
	@ApiModelProperty("签收经办人")
    private Long signHandleUserId;
	@Mapping
	@ApiModelProperty("签收时间")
    private Long signTime;
	@ApiModelProperty("货物异常状态")
    private ReceiptExceptionStatusEnum exceptionStatus;
	@Mapping
	@ApiModelProperty("回收操作人")
    private Long recoveryOperUserId;
	@Mapping
	@ApiModelProperty("回收时间")
    private Long recoveryTime;
	@Mapping
	@ApiModelProperty("回收备注")
    private String recoveryRemark;
	@Mapping
	@ApiModelProperty("取消回收人")
    private Long cancelRecoveryUserId;
	@Mapping
	@ApiModelProperty("取消回收时间")
    private Long cancelRecoveryTime;
	@Mapping
	@ApiModelProperty("发放操作人")
    private Long grantOperUserId;
	@Mapping
	@ApiModelProperty("发放时间")
    private Long grantTime;
	@Mapping
	@ApiModelProperty("发放备注")
    private String grantRemark;
	@Logger @Mapping
	@ApiModelProperty("发放批次")
    private String grantBatchCode;
	@Mapping
	@ApiModelProperty("发放批次id")
    private Long grantId;
	@Mapping
	@ApiModelProperty("取消发放人")
    private Long cancelGrantUserId;
	@Mapping
	@ApiModelProperty("取消发放时间")
    private Long cancelGrantTime;
	@Mapping
	@ApiModelProperty("快递费分摊")
    private BigDecimal expressFee;
	@Logger @Mapping
	@ApiModelProperty("快递费关联批次")
    private String expressBatchCode;
	@Mapping
	@ApiModelProperty("回单库存")
    private Integer stockNum;
	@Mapping
    @ApiModelProperty(" 创建人")
	private Integer createUser;
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;
	@Mapping("isDelete")
	@ApiModelProperty("是否删除")
    private Boolean idelete;
	@Mapping
	@ApiModelProperty("回收状态")
	private String recoveryStatus;
	@Mapping
	@ApiModelProperty("发放状态")
	private String grantStatus;
	@Mapping
	@ApiModelProperty("外发公司")
	private String externalCompanyName;
	
	/**冗余名称*/
	@Mapping
	@ApiModelProperty("库存网点名称")
	private String stockOrgName;
	@Mapping
	@ApiModelProperty("签收经办人名称")
	private String signHandleUserName;
	@Mapping
	@ApiModelProperty("回收操作人姓名")
	private String recoveryOperUserName;
	@Mapping
	@ApiModelProperty("取消回收人姓名")
	private String cancelRecoveryUserName;
	@Mapping
	@ApiModelProperty("发放操作人姓名")
	private String grantOperUserName;
	@Mapping
	@ApiModelProperty("创建人名称") 
	private String createUserName;
	@Mapping
	@ApiModelProperty("修改人名称") 
	private String updateUserName;
	@Mapping
	@ApiModelProperty("取消发放人姓名")
	private String cancelGrantUserName;
	@Required @Mapping
	@ApiModelProperty("回收网点ID")
	private Long recoveryOrgId;
	@Required @Mapping
	@ApiModelProperty("回收网点名称")
	private String recoveryOrgName;
	
	
	/**冗余运单信息**/
	/**
	 * 发货网点ID	
	 */
	private @Mapping  @ApiModelProperty("发货网点ID	") Integer invoiceOrgId;
	/**
	 * 到达网点id	
	 */
	private @Mapping  @ApiModelProperty("到达网点id")Integer destOrgId;
	/**
	 * 发货网点名称	
	 */
	private @Mapping  @ApiModelProperty("发货网点名称	")String invoiceOrgName;
	/**
	 * 目的地名称	
	 */
	private @Mapping  @ApiModelProperty("目的地名称")String destName;
	/**
	 * 运输线路名称	
	 */
	private @Mapping  @ApiModelProperty("运输线路名称	")String lineName;
	/**
	 * 到达网点名称	
	 */
	private @Mapping  @ApiModelProperty("到达网点名称	")String destOrgName;
	/**
	 * 发货方名称		
	 */
	private @Mapping  @ApiModelProperty("发货方名称")String invoiceCompany;
	/**
	 * 发货人		
	 */
	private @Mapping  @ApiModelProperty("发货人")String invoiceUser;
	/**
	 * 发货人电话		
	 */
	private @Mapping  @ApiModelProperty("发货人电话")String invoiceTelNo;
	/**
	 * 发货人手机号码	
	 */
	private @Mapping  @ApiModelProperty("发货人手机号码")String invoiceMobleNo;
	/**
	 * 发货人详细地址	 	
	 */
	private @Mapping  @ApiModelProperty("发货人详细地址")String invoiceAddress;
	/**
	 * 收货方名称	
	 */
	private @Mapping  @ApiModelProperty("收货方名称")String receiptCompany;
	/**
	 * 收货人		
	 */
	private @Mapping  @ApiModelProperty("收货人")String receiptUser;
	/**
	 * 收货人电话		
	 */
	private @Mapping  @ApiModelProperty("收货人电话")String receiptConsignorTelNo;
	/**
	 * 收货人手机号码		
	 */
	private @Mapping  @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;
	/**
	 * 收货人详细地址		
	 */
	private @Mapping  @ApiModelProperty("收货人详细地址")String receiptAddress;
	/**
	 * 商品名称 ,“/”间隔
	 */
	private @Mapping @ApiModelProperty("商品名称") String goodsName;
	/**
	 * 包装名称 ,“/”间隔
	 */
    private @Mapping  @ApiModelProperty("包装名称") String packingName;
    /**
    * 总体积
    */
    private @Mapping  @ApiModelProperty("总体积") BigDecimal volume;
    /**
    * 总重量
    */
    private @Mapping  @ApiModelProperty("总重量") BigDecimal weight;
    /**
    * 总件数
    */
    private @Mapping  @ApiModelProperty("总件数") Integer totalNum;
    /**
	 * 派送方式
	 */
    private @Mapping @ApiModelProperty("配送方式") Integer distributionType;
    /**
	 * 付款方式名称
	 */
	private @Mapping @ApiModelProperty("付款方式名称") String payWayName;
	/**
	 * 总运费	
	 */
	private @Mapping  @ApiModelProperty("总运费")BigDecimal totalFee;
	
	
	@Override
	public ReceiptStockEsDTO clone() {
		try {
			ReceiptStockEsDTO stock = (ReceiptStockEsDTO)super.clone();
			stock.setRequirement(this.requirement);
			stock.setSignStatus(this.signStatus);
			stock.setExceptionStatus(this.exceptionStatus);
			return stock;
		} catch (CloneNotSupportedException e) {
			throw ExceptionFactory.ex(e);
		}
	}
	
	

}