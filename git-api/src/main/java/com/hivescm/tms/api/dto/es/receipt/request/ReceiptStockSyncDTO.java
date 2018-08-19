package com.hivescm.tms.api.dto.es.receipt.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptBusinessTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 回单库存同步请求DTO
 * @author ke.huang
 * @date 2018年3月28日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptStockSyncDTO implements Serializable{
	private static final long serialVersionUID = -8546071186703774424L;
	@Required @Logger
	@ApiModelProperty(value="公司ID",required=true)
	private Long companyId;
	@Required @Logger
	@ApiModelProperty(value="运单ID",required=true)
	private Long waybillId;
	@Required @Logger
	@ApiModelProperty(value="所在库存网点ID",required=true)
	private Long stockOrgId;
	@Required @Logger
	@ApiModelProperty(value="库存网点名称",required=true)
	private String stockOrgName;
	@ApiModelProperty(value="业务类型",required=true)
	private ReceiptBusinessTypeEnum businessType;
	@Required
	@ApiModelProperty(value="操作人ID",required=true)
	private Long operUserId;
	@Required
	@ApiModelProperty(value="操作人姓名",required=true)
	private String operUserName;
	@Required
	@ApiModelProperty(value="操作时间",required=true)
	private Long operTime;
	@ApiModelProperty(value="发车配载/派车单发车时回单跟第一车操作依据，模块ID")
	private Long dispatcherTransportId;
	
}
