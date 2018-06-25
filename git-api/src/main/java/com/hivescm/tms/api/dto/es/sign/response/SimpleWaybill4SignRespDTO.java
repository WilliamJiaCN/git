package com.hivescm.tms.api.dto.es.sign.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/2
 */
@Data
@ToString
@ApiModel
public class SimpleWaybill4SignRespDTO {
	
    private @Mapping({"waybillId"}) @ApiModelProperty("主键ID") Long id;

    private @Mapping  @ApiModelProperty("公司名称") String companyName ;
    private @Mapping  @ApiModelProperty("公司Id") Integer companyId ;

    private @Mapping  @ApiModelProperty("客户单号") String  customerCode;

    @Mapping({"waybillCode", "code"}) @Logger
    private  @ApiModelProperty("运单号") String code ;

    private @Mapping  @ApiModelProperty("订单编号") @Logger String orderCode ;
   
    private @Mapping  @ApiModelProperty("主订单编号")String mainOrderCode ;
    
    private @Mapping  @ApiModelProperty("原订单号") String originalOrderCode;
    
    private @Mapping  @ApiModelProperty("发货网点ID") Integer invoiceOrgId;

    private @Mapping  @ApiModelProperty("发货网点名称")String invoiceOrgName;

    private @Mapping  @ApiModelProperty("目的地名称")String destName;

    private @Mapping  @ApiModelProperty("到达网点名称")String destOrgName;
   
    private @Mapping  @ApiModelProperty("发货人")String invoiceUser;

    private @Mapping  @ApiModelProperty("发货方名称")String invoiceCompany;

    private @Mapping  @ApiModelProperty("发货人手机号码")String invoiceMobleNo;

    private @Mapping  @ApiModelProperty("发货人详细地址")String invoiceAddress;
   
    private @Mapping  @ApiModelProperty("收货人")String receiptUser;
   
    private @Mapping  @ApiModelProperty("收货方名称")String receiptCompany;
   
    private @Mapping  @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;
   
    private @Mapping  @ApiModelProperty("收货人详细地址")String receiptAddress;

    @Mapping
    private @ApiModelProperty("回单要求") String backType;

    @Mapping
    private @ApiModelProperty("回单要求") Integer backTypeValue;

    private @Mapping  @ApiModelProperty("回单份数") Integer backNum;
    
    private @Mapping  @ApiModelProperty("回单编号")String backCode;

    private @Mapping  @ApiModelProperty("累计签收件数")Integer totalSignNumber;

    private @Mapping  @ApiModelProperty("累计拒签件数")Integer totalRefuseSignNumber;

    private @Mapping  @ApiModelProperty("总件数") Integer totalNum;
    
    private @Mapping @ApiModelProperty("商品名称") String goodsName;

    private @Mapping  @ApiModelProperty("总运费")BigDecimal totalFee;
    
    private @Mapping @ApiModelProperty("付款方式名称") String payWayName;
    
    private @Mapping  @ApiModelProperty("到付")BigDecimal cod;
    
	private @Mapping("waybillStatus")  @ApiModelProperty("运单状态")Integer status;
	
	private @Mapping  @ApiModelProperty("创建时间") Long createTime;
	
	@Mapping
	private @ApiModelProperty("配送方式名称") String distributionTypeName;
	
    private @Mapping  @ApiModelProperty("包装名称") String packingName;
    
	private @Mapping  @ApiModelProperty("到达网点id")Integer destOrgId;
	
	@Mapping
	private @ApiModelProperty("运单状态名称") String statusName;

    private @ApiModelProperty("未签件数") Integer unSignNumber;

    /**
     * 签收状态（运单的签收状态）
     */
    private @Mapping  @ApiModelProperty("签收状态")Integer signStatus;

    /**
     * 签收状态名称（运单的签收状态）
     */
    private @Mapping  @ApiModelProperty("签收状态名称")String signStatusName;

}
