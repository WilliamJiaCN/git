/**
 * 
 */
package com.hivescm.tms.api.dto.es.receipt.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptRequirmentTypeEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author boqiang.deng
 * @date 2018年6月7日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class ReceiptPrintDetailResp implements Serializable {

	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("运单号")
	private String waybillCode;
	@Mapping
	@ApiModelProperty("寄出备注")
	private String transmitRemark;
	@ApiModelProperty("回单要求")
	private String requirementName;
	@Mapping
	@ApiModelProperty("回单份数")
	private Integer num;
	@Mapping
	@ApiModelProperty("回单编号")
	private String code;
	private @Mapping @ApiModelProperty("发货方名称") String invoiceCompany;
	private @Mapping @ApiModelProperty("发货人") String invoiceUser;
	private @Mapping @ApiModelProperty("收货方名称") String receiptCompany;
	private @Mapping @ApiModelProperty("收货人") String receiptUser;
	private @Mapping @ApiModelProperty("商品名称") String goodsName;
	private @Mapping @ApiModelProperty("包装名称") String packingName;
	private @Mapping @ApiModelProperty("总体积") BigDecimal volume;
	private @Mapping @ApiModelProperty("总重量") BigDecimal weight;
	private @Mapping @ApiModelProperty("总件数") Integer totalNum;
	private @Mapping @ApiModelProperty("付款方式名称") String payWayName;
	private @Mapping @ApiModelProperty("总运费") BigDecimal totalFee;
	
	@Logger @Mapping
	@ApiModelProperty("寄出接收批次id")
    private Long transmitReceiveId;
	
}
