package com.hivescm.tms.api.dto.es.sitesearch.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.tms.api.dto.es.waybill.component.TmsWaybillEsDTO;
import com.hivescm.tms.api.enums.biz.waybill.WaybillFinanceStatusEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillStatusEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 综合查询单据明细响应实体
 * @author ke.huang
 * @date 2017年9月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class SearchBillDetailRespDTO implements Serializable{
	private static final long serialVersionUID = 8297898704307573846L;
	@ApiModelProperty("财务状态")
	private WaybillFinanceStatusEnum financeStatus;
	@ApiModelProperty("单据状态")
	private WaybillStatusEnum billStataus;
	@ApiModelProperty("是否异常")
	private Boolean iException;
	@ApiModelProperty("是否等通知")
	private Boolean iNotice;
	@ApiModelProperty("是否加急")
	private Boolean iEmergency;
	@ApiModelProperty("是否VIP")
	private Boolean iVip;
	@ApiModelProperty("是否拆单(是否不为整车)")
	private Boolean iNoTruck;
	@ApiModelProperty("原总运费")
	private BigDecimal originalTotalFreight;
	@ApiModelProperty("运单详情")
	private TmsWaybillEsDTO waybill;
}
