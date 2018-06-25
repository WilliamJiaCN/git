/**
 * 
 */
package com.hivescm.tms.api.dto.es.accusedgoods.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @date    2018年5月18日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class AccusedInitiaResp implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private @Mapping  @ApiModelProperty("运单状态名称") String statusName;

	private @Mapping  @ApiModelProperty("发货人")String invoiceUser;
	
	private @Mapping  @ApiModelProperty("发货方名称")String invoiceCompany;
	
	private @Mapping  @ApiModelProperty("发货人电话")String invoiceTelNo;
	
	private @Mapping  @ApiModelProperty("发货人手机号码")String invoiceMobleNo;
	
	private @Mapping  @ApiModelProperty("发货人详细地址")String invoiceAddress;
	
	private @Mapping  @ApiModelProperty("收货人")String receiptUser;
	
	private @Mapping  @ApiModelProperty("收货方名称")String receiptCompany;
	
	private @Mapping  @ApiModelProperty("收货人电话")String receiptConsignorTelNo;
	
	private @Mapping  @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;
	
	private @Mapping  @ApiModelProperty("收货人详细地址")String receiptAddress;
	
	private @Mapping @ApiModelProperty("货物名称") String goodsName;
	
	private @Mapping  @ApiModelProperty("总体积") BigDecimal volume;
	
	private @Mapping  @ApiModelProperty("总重量") BigDecimal weight;
	
	private @Mapping  @ApiModelProperty("总件数") Integer totalNum;
	
	private @Mapping  @ApiModelProperty("目的网点")String destOrgName;
	
	private @Mapping  @ApiModelProperty("发货网点")String invoiceOrgName;
	
	private @Mapping @ApiModelProperty("配送方式") String distributionTypeName;
	
	private @Mapping @ApiModelProperty("付款方式名称") String payWayName;
	
	private @Mapping  @ApiModelProperty("到付")BigDecimal cod;
	
	private @Mapping @ApiModelProperty("送货费") BigDecimal deliveryFee;
	
    private @Mapping @ApiModelProperty("代收货款") BigDecimal collectPayment;
    
    private @Mapping @ApiModelProperty("备注")String remark;
    
    private @Mapping  @ApiModelProperty("录单时间") Long createTime;
    
    private @Mapping  @ApiModelProperty("是否等通知")Boolean iwaitNotice;
    
    private @Mapping  @ApiModelProperty("签收状态名称")String signStatusName;
    
	private  @Mapping @ApiModelProperty("是否为异常单")Boolean exception;
	
	private @Mapping @ApiModelProperty("运单号") String code ;
	
	private @Mapping @ApiModelProperty("运单id")Long id;
	
	private @Mapping  @ApiModelProperty("签收状态,1=NO_SIGN未签收,2=SIGNED签收,3=PARTIAL_SIGN部分签收,4=REFUSE_SIGN拒签")Integer signStatus;
    
	private @Mapping  @ApiModelProperty("发货人完整地址")String invoiceFullAddress;
    
    private @Mapping  @ApiModelProperty("收货人完整地址")String receiptFullAddress;
}
