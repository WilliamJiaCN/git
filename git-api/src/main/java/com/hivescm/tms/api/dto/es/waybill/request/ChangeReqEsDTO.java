package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 运单请求体
 * 
 * @author ke.huang
 * @date 2017年7月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ChangeReqEsDTO implements Serializable {

	private static final long serialVersionUID = -6933611350149830800L;

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
	private @ApiModelProperty("申请网点") Long applyOrgId;
	/**
	 * 运单状态
	 */
	@ApiModelProperty("申请状态:-1,全部," + "0,未确认," + "1,已确认" )
	private Integer status;
	/**
	 * 录单日期 - 开始范围
	 */
	private @ApiModelProperty("录单日期 - 开始范围") Long startCreateTime;
	/**
	 * 录单日期 - 结束范围
	 */
	private @ApiModelProperty("录单日期 - 结束范围") Long endCreateTime;
	
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
