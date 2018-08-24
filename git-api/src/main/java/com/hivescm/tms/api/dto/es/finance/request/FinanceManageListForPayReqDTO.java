package com.hivescm.tms.api.dto.es.finance.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 待付款列表查询条件
 * @author wangqianqian
 *
 */
@Data
@ToString
public class FinanceManageListForPayReqDTO implements Serializable{

	private static final long serialVersionUID = 4527891484312255725L;

	@ApiModelProperty(value = "开始时间")
	private Long dateStart;
	
	@ApiModelProperty(value = "结束时间")
	private Long dateEnd;
	
	@ApiModelProperty(value = "单据类型 1.运单 2.派车单 3.发车配载单 4.外发中转单 5 装货单 6 卸货单 7 到货确认单")
	private List<Integer> sheetType;
	
	@ApiModelProperty(value = "费用类型")
	private List<Integer> feeType;
	
	@ApiModelProperty(value = "来源单号")
	private String dataSourceSheetId;
	
	@ApiModelProperty(value = "来源单号批量查")
	private List<String> dataSourceSheetCodes;

	@ApiModelProperty(value = "收款方")
	private String payee;

	@ApiModelProperty(value = "当前网点")
	private Integer orgId;
}
