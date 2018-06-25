package com.hivescm.tms.api.dto.es.waybill.response;

import java.io.Serializable;

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
public class ChangeWaybillPrintRespDTO implements Serializable {


	private static final long serialVersionUID = -6933611350149830800L;

	/**
	 * 运单id
	 */
	private @ApiModelProperty("运单号") String waybillCode;

	private @ApiModelProperty("录单员") String createUserName;

	private @ApiModelProperty("录单员") String businessUserName;

	/**
	 * 录单日期
	 */
	private @ApiModelProperty("录单日期 ") Long createTime;

	private @ApiModelProperty("更改内容") String changeContent;

	private @ApiModelProperty("更改备注") String changeRemark;

	private @ApiModelProperty("申请人") String applyUserName;

	private @ApiModelProperty("申请时间 ") Long applyTime;

	private @ApiModelProperty("确认人") String confirmUserName;

	private @ApiModelProperty("确认时间 ") Long confirmTime;

	private @ApiModelProperty("打印人") String printUserName;

	private @ApiModelProperty("打印时间 ") Long printTime;

}
