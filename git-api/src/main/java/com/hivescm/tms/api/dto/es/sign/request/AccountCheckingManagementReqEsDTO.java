package com.hivescm.tms.api.dto.es.sign.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class AccountCheckingManagementReqEsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty("主键")
    private Long id;

	
	@ApiModelProperty("公司id")
    private Long companyId;

	@ApiModelProperty("发货网点ID")
	private List<Integer> invoiceOrgIds;
	
	@ApiModelProperty("对账开始时间")
    private Long startAccountCheckingTime;
	
	@ApiModelProperty("对账结束时间")
    private Long endAccountCheckingTime;
	
	@ApiModelProperty("支付金额")
    private BigDecimal payFee;
	
	@ApiModelProperty("金额范围")
	private String feeScope;
	
	@ApiModelProperty("对账结果")
    private String accountCheckingResult;
	@Logger
	@ApiModelProperty("交易流水号")
    private String tradeSerialNo;
	
	private @ApiModelProperty("操作用户id") Integer userId;
	
	private @ApiModelProperty("分页 - 每页显示数") Integer pageSize = 10;
	
	private @ApiModelProperty("分页 - 当前页数") Integer currentPage = 1;
	
	public void initPage() {
		if (null == pageSize || pageSize == 0) {
			pageSize = 10;
		}
		if (null == currentPage || currentPage == 0) {
			currentPage = 1;
		}
	}
	
}
