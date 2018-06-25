package com.hivescm.tms.api.dto.es.sitesearch.response;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 综合查询所有单据响应实体
 * @author ke.huang
 * @date 2017年9月20日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class SearchBillDocRespDTO implements Serializable{
	private static final long serialVersionUID = 4883585278785699046L;
	@ApiModelProperty("单据类型")
	private String docType;
	@ApiModelProperty("单据批次")
	private String batchCode;
	@ApiModelProperty("操作时间")
	private Long time;
	@ApiModelProperty("件数")
	private Integer packageNum;
	@ApiModelProperty("操作网点")
	private String dotName;
	@ApiModelProperty("操作人")
	private String operateUserName;
	@ApiModelProperty("单据状态")
	private String billStatus;
	@ApiModelProperty("收付状态")
	private String payStatus;
	@ApiModelProperty("单据费用")
	private BigDecimal fee;
	@ApiModelProperty("备注")
	private String remark;
	
}
