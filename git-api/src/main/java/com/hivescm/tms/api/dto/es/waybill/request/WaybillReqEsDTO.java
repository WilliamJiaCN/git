package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 运单请求体
 * 
 * @author ke.huang
 * @date 2017年7月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WaybillReqEsDTO implements Serializable {

	private static final long serialVersionUID = -6933611350149830800L;

	/**
	 * 运单id
	 */
	private @ApiModelProperty("运单id") @Logger Long id;
	/**
	 * 公司id
	 */
	private @ApiModelProperty("公司id") Long companyId;
	/**
	 * 运单状态
	 */
	@ApiModelProperty("运单状态:1,待处理（已开单还未派送）," + "2,已揽货," + "3,已配送," + "4,已签收," + "5,已取消")
	private Integer status;
	
	@ApiModelProperty("订单类型:1,销售单,"+"2,销退单")
	private Integer orderType;
	
	@ApiModelProperty("波次号")
    private String waveCode;
	
	@ApiModelProperty("运单号") @Logger
	private String code ;
	
	@ApiModelProperty("是否已指派")
	private Boolean truckOrdered ;
	
	@ApiModelProperty("司机姓名")
	private String driverName;
	
	/**
	 * 录单日期 - 开始范围
	 */
	private @ApiModelProperty("录单日期 - 开始范围") Long startCreateTime;
	/**
	 * 录单日期 - 结束范围
	 */
	private @ApiModelProperty("录单日期 - 结束范围") Long endCreateTime;
	
	
	/**
	 * 发车时间 - 开始范围
	 */
	private @ApiModelProperty("发车时间 - 开始范围") Long startDispatcherTime;
	/**
	 * 发车时间 - 结束范围
	 */
	private @ApiModelProperty("发车时间 - 结束范围") Long endDispatcherTime;
	
	
	/**
	 * 揽货时间 - 开始范围
	 */
	private @ApiModelProperty("揽货时间 - 开始范围") Long startFreightTime;
	/**
	 * 揽货时间 - 结束范围
	 */
	private @ApiModelProperty("揽货时间 - 结束范围") Long endFreightTime;
	
	
	/**
	 * 配送时间 - 开始范围
	 */
	private @ApiModelProperty("配送时间 - 开始范围") Long startDeliveryTime;
	/**
	 * 配送时间 - 结束范围
	 */
	private @ApiModelProperty("配送时间 - 结束范围") Long endDeliveryTime;
	
	
	/**
	 * 签收时间 - 开始范围
	 */
	private @ApiModelProperty("签收时间 - 开始范围") Long startSignTime;
	/**
	 * 签收时间 - 结束范围
	 */
	private @ApiModelProperty("签收时间 - 结束范围") Long endSignTime;
	
	
	/**
	 * 取消时间 - 开始范围
	 */
	private @ApiModelProperty("取消时间 - 开始范围") Long startDiscardTime;
	/**
	 * 取消时间 - 结束范围
	 */
	private @ApiModelProperty("取消时间 - 结束范围") Long endDiscardTime;
	
	/**
	 * 发货网点ID
	 */
	private @ApiModelProperty("发货网点ID") List<Integer> invoiceOrgIds;
	/**
	 * 到达网点ID
	 */
	private @ApiModelProperty("到达网点ID") Integer destOrgId;
	/**
	 * 分页 - 每页显示数
	 */
	private @ApiModelProperty("分页 - 每页显示数") Integer pageSize = 30;
	/**
	 * 分页 - 当前页数
	 */
	private @ApiModelProperty("分页 - 当前页数") Integer currentPage = 1;

	private @ApiModelProperty("操作用户id") Integer userId;
	
	private @ApiModelProperty("用户客户列表") List<String> clientInfo;
	
	/**
	 * 初始化分页信息
	 */
	public void initPage() {
		if (null == pageSize || pageSize == 0) {
			pageSize = 30;
		}
		if (null == currentPage || currentPage == 0) {
			currentPage = 1;
		}
	}

}
