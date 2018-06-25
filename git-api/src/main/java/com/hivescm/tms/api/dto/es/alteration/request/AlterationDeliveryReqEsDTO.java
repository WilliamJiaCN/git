package com.hivescm.tms.api.dto.es.alteration.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 自提or送货更改请求体
 * @author zhenming.du
 * @date 2017年9月28日
 * @company 蜂网供应链
 */
@Data
@ToString
public class AlterationDeliveryReqEsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5590028177111958876L;

	/**
	 * 申请时间 - 开始
	 */
	private @ApiModelProperty("申请时间 - 开始") Long startApplicantTime;
	/**
	 * 申请时间 - 结束
	 */

	private @ApiModelProperty("申请时间 - 结束") Long endApplicantTime;
	/**
	 * 确认 - 起始时间
	 */
	private @ApiModelProperty("确认 - 起始时间") Long startConfirmTime;
	/**
	 * 确认 - 结束时间
	 */
	private @ApiModelProperty("确认 - 结束时间") Long endConfirmTime;
	/**
	 * 申请网点
	 */
	private @ApiModelProperty("申请网点") Integer applicantOrg;
	/**
	 * 发货网点ID
	 */
	private @ApiModelProperty("发货网点ID	") Integer invoiceOrgId;
	/**
	 * 到达网点id
	 */
	private @ApiModelProperty("到达网点id") Integer destOrgId;
	/**
	 * 运单号
	 */
	private @ApiModelProperty("运单号") String waybillCode;
	/**
	 * 页大小
	 */
	private @ApiModelProperty("页大小") Integer pageSize = 10;
	/**
	 * 当前页
	 */
	private @ApiModelProperty("当前页") Integer currentPage = 1;

	/**
	 * 初始化分页信息
	 */
	public void initPage() {
		if (null == pageSize || pageSize == 0) {
			pageSize = 10;
		}
		if (null == currentPage || currentPage == 0) {
			currentPage = 1;
		}
	}

}
