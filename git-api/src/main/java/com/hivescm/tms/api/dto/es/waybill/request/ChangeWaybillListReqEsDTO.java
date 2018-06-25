package com.hivescm.tms.api.dto.es.waybill.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 运单请求体
 * 
 * @author ke.huang
 * @date 2017年7月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ChangeWaybillListReqEsDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3688991791111694911L;
	/**
	 * 运单id
	 */
	@Logger
	private @ApiModelProperty("运单号") String waybillCode;
	/**
	 * 公司id
	 */
	private @ApiModelProperty("公司id") Long companyId;
	
	/**
	 * 申请网点
	 */
	private @ApiModelProperty("申请网点") List<Long> applyOrgIdList;
	/**
	 * 运单状态
	 */
	@ApiModelProperty("申请状态:0,全部," + "1,未确认," + "2,已确认" )
	private Integer status;
	/**
	 * 录单日期 - 开始范围
	 */
	private @ApiModelProperty("日期 - 开始范围") Long startTime;
	/**
	 * 录单日期 - 结束范围
	 */
	private @ApiModelProperty("日期 - 结束范围") Long endTime;
	
	private @ApiModelProperty("日期类型：1、申请日期     2、更改日期") Integer timeType;  //(日期类型：1、申请日期     2、更改日期)
	
	/**
	 * 分页 - 每页显示数
	 */
	private @ApiModelProperty("分页 - 每页显示数") Integer pageSize = 10;
	/**
	 * 分页 - 当前页数
	 */
	private @ApiModelProperty("分页 - 当前页数") Integer currentPage = 1;

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
