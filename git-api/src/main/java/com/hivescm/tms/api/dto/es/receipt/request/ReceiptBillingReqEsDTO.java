package com.hivescm.tms.api.dto.es.receipt.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptRequirmentTypeEnum;
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
public class ReceiptBillingReqEsDTO implements Serializable{
	private static final long serialVersionUID = 1263778070945502278L;
	@Logger @Mapping @Required
	@ApiModelProperty(value="公司ID",required=true)
	private Long companyId;
	@Logger @Mapping @Required
	@ApiModelProperty(value="运单号",required=true)
    private String waybillCode;
	@Logger @Mapping @Required
	@ApiModelProperty(value="运单ID",required=true)
    private Long waybillId;
	@Required
	@ApiModelProperty(value="回单要求",required=true)
    private ReceiptRequirmentTypeEnum requirement;
	@Mapping @Required
	@ApiModelProperty(value="回单份数",required=true)
    private Integer num;
	@Logger @Mapping
	@ApiModelProperty(value="回单编号",required=true)
    private String code;
	@Mapping @Required // >> 发货网点
	@ApiModelProperty(value="库存网点",required=true)
    private Long stockOrgId;
	@Required @Mapping
	@ApiModelProperty(value="库存网点名称",required=true) 
	private String stockOrgName;
	@Mapping @Required // >> 回单份数
	@ApiModelProperty(value="回单库存",required=true)
    private Integer stockNum;
	@Mapping @Required
    @ApiModelProperty(value=" 创建人",required=true)
	private Integer createUser;
	@Mapping @Required
	@ApiModelProperty(value="创建时间",required=true)
    private Long createTime;
	
	/**名称冗余*/
	@ApiModelProperty(value="创建人名称",required=true) 
	@Mapping @Required
	private String createUserName;
	
	
	
	/**冗余运单信息**/
	/**
	 * 发货网点ID	
	 */
	private @Mapping  @ApiModelProperty(value="发货网点ID	",required=true) Integer invoiceOrgId;
	/**
	 * 到达网点id	
	 */
	private @Mapping  @ApiModelProperty(value="到达网点id",required=true)Integer destOrgId;
	/**
	 * 发货网点名称	
	 */
	private @Mapping @Required  @ApiModelProperty(value="发货网点名称",required=true)String invoiceOrgName;
	/**
	 * 目的地名称	
	 */
	private @Mapping  @ApiModelProperty(value="目的地名称")String destName;
	/**
	 * 运输线路名称	
	 */
	private @Mapping  @ApiModelProperty(value="运输线路名称	",required=true)String lineName;
	/**
	 * 到达网点名称	
	 */
	private @Mapping  @ApiModelProperty(value="到达网点名称	")String destOrgName;
	/**
	 * 发货方名称		
	 */
	private @Mapping  @ApiModelProperty(value="发货方名称")String invoiceCompany;
	/**
	 * 发货人		
	 */
	private @Mapping @Required  @ApiModelProperty(value="发货人")String invoiceUser;
	/**
	 * 发货人电话		
	 */
	private @Mapping  @ApiModelProperty(value="发货人电话")String invoiceTelNo;
	/**
	 * 发货人手机号码	
	 */
	private @Mapping  @ApiModelProperty(value="发货人手机号码")String invoiceMobleNo;
	/**
	 * 发货人详细地址	 	
	 */
	private @Mapping @ApiModelProperty(value="发货人详细地址",required=true)String invoiceAddress;
	/**
	 * 收货方名称	
	 */
	private @Mapping  @ApiModelProperty(value="收货方名称")String receiptCompany;
	/**
	 * 收货人		
	 */
	private @Mapping @Required  @ApiModelProperty(value="收货人",required=true)String receiptUser;
	/**
	 * 收货人电话		
	 */
	private @Mapping  @ApiModelProperty(value="收货人电话")String receiptConsignorTelNo;
	/**
	 * 收货人手机号码		
	 */
	private @Mapping  @ApiModelProperty(value="收货人手机号码")String receiptConsignorMobleNo;
	/**
	 * 收货人详细地址		
	 */
	private @Mapping @Required  @ApiModelProperty(value="收货人详细地址",required=true)String receiptAddress;
	/**
	 * 商品名称 ,“/”间隔
	 */
	private @Mapping @Required @ApiModelProperty(value="商品名称",required=true) String goodsName;
	/**
	 * 包装名称 ,“/”间隔
	 */
    private @Mapping  @ApiModelProperty(value="包装名称",required=true) String packingName;
    /**
    * 总体积
    */
    private @Mapping @Required  @ApiModelProperty(value="总体积",required=true) BigDecimal volume;
    /**
    * 总重量
    */
    private @Mapping @Required  @ApiModelProperty(value="总重量",required=true) BigDecimal weight;
    /**
    * 总件数
    */
    private @Mapping @Required @ApiModelProperty(value="总件数",required=true) Integer totalNum;
    /**
	 * 派送方式
	 */
    private @Mapping @Required @ApiModelProperty(value="配送方式",required=true) Integer distributionType;
    /**
	 * 付款方式名称
	 */
	private @Mapping @ApiModelProperty(value="付款方式名称",required=true) String payWayName;
	/**
	 * 总运费	
	 */
	private @Mapping @Required  @ApiModelProperty(value="总运费",required=true)BigDecimal totalFee;

}