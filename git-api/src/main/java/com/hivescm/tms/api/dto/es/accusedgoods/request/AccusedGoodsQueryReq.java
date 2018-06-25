/**
 * 
 */
package com.hivescm.tms.api.dto.es.accusedgoods.request;

import java.io.Serializable;
import java.util.List;


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
public class AccusedGoodsQueryReq implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name="开始时间",hidden=true)
	private Long startConfirmTime;
	
	@ApiModelProperty(name="结束时间",hidden=true)
	private Long endConfirmTime;
	
	@ApiModelProperty("开始时间")
	private Long startApplicantTime;
	
	@ApiModelProperty("结束时间")
	private Long endApplicantTime;
	
	@ApiModelProperty("申请网点")
	private List<Integer> applicantOrg;
	
	@ApiModelProperty("目的网点")
	private List<Integer> destOrg;
	
	@ApiModelProperty("发货网点")
	private List<Integer> invoiceOrg;
	
	@ApiModelProperty("运单号")
	private String waybillCode;
	
	private @ApiModelProperty("收货人")String receiptUser;
	
	private @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;
	
	@ApiModelProperty("当前网点id")
	private Long orgId;
	
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
