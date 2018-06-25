package com.hivescm.tms.api.dto.es.transport.request;

import java.io.Serializable;
import java.util.Arrays;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 运单请求体
 * @author ke.huang
 * @date 2017年7月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WaybillForTransportReqDTO implements Serializable{
	
	private static final long serialVersionUID = -6933611350149830800L;
	
	
	/**
	 * 网点id
	 */
	private @ApiModelProperty("网点ID") Long orgId;
	/**
	 * 运单id
	 */
	private @ApiModelProperty("运单号") String waybillCode;

	/**
	 * 运输批次ID
	 */
	@ApiModelProperty("运输批次ID")
	private Long transportId;
	

	/**
	 * 录单日期 - 开始范围
	 */
	private @ApiModelProperty("录单日期 - 开始范围") Long startCreateTime;
	/**
	 * 录单日期 - 结束范围
	 */
	private @ApiModelProperty("录单日期 - 结束范围") Long endCreateTime;
	
	/**
	 * 目的网点ID
	 */
	private @ApiModelProperty("目的网点ID") Integer destOrgId;
	
	/**
	 * 运输线路
	 */
	private @ApiModelProperty("运输线路ID") Integer lineId;
	
	/**
	 * 仓库ID
	 */
	private @ApiModelProperty("仓库ID") Integer warehouseId;
	/**
	 * 动态字段
	 * 根据用户前台选择筛选条件
	 */
	private @ApiModelProperty("动态字段") String[] dynamicKey;
	/**
	 * 动态字段值
	 */
	private @ApiModelProperty("动态字段值") String[] dynamicValue;
	/**
	 * 分页 - 开始条数
	 */
	private @ApiModelProperty("分页 - 开始条数") Integer start;
	/**
	 * 分页 - 每页显示数
	 */
	private @ApiModelProperty("分页 - 每页显示数") Integer limit = 10;
	/**
	 * 分页 - 当前页数
	 */
	private @ApiModelProperty("分页 - 当前页数") Integer currentPage;
	
	
}
