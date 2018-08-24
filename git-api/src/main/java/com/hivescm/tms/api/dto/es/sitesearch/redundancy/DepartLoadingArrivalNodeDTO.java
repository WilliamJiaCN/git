package com.hivescm.tms.api.dto.es.sitesearch.redundancy;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 短途到货信息
 * 提取运单关联的到货批次信息
 * @author ke.huang
 * @date 2017年9月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DepartLoadingArrivalNodeDTO implements Serializable{
	private static final long serialVersionUID = 3635998657941256661L;
	@ApiModelProperty("到货批次")
	private String arrivalBatch;//到货单据的到货批次
	@ApiModelProperty("关联批次")
	private String departBatch;//到货单据关联的发车批次
	@ApiModelProperty("到货网点")
	private String branchName;//到货单据的到货网点
	@ApiModelProperty("到货件数")
	private Integer unloadAmount;//到货单据的到货件数
	@ApiModelProperty("到达时间")
	private Long arrivalTime;//到货单据的到货时间
	@ApiModelProperty("备注")
	private String remark;//到货单据的的备注
	@ApiModelProperty("经办人")
	private String createUserName;//到货单据的制单人
}
