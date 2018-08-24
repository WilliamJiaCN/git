package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.enums.biz.finance.FinanceEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
/**
 * 
 * <p>
 * Title:ReceiptReqDTO
 * </p>
 * <p>
 * Description: 收款单条件查询
 * </p>
 * <p>
 * Company: 蜂网供应链（上海）有限公司
 * </p>
 * 
 * @author 王小雪
 * @date 下午2:00:38
 */
public class ReceiptReqDTO implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 确认收银时间- 开始范围getEndSignTime
	 */
	private @ApiModelProperty("确认收银时间 - 开始范围") Long startConfirmReceiptTime;
	/**
	 * 确认收银时间- 结束范围
	 */
	private @ApiModelProperty("确认收银时间- 结束范围") Long endConfirmReceiptTime;
/*	
	*//**
	 * 确认收银时间
	 *//*
	@Mapping
    @ApiModelProperty("确认收银时间")
    private Long confirmReceiptTime;*/
	
	/**
	 * 收款时间- 开始范围
	 */
	private @ApiModelProperty("收款时间 - 开始范围") Long startCollectionTime;
	/**
	 * 收款时间- 结束范围
	 */
	private @ApiModelProperty("收款时间- 结束范围") Long endCollectionTime;
	
	
	/**
	 * 收款时间
	 *//*
	@Mapping
	@ApiModelProperty("收款时间===-收款单生成日期")
	private Long collectionTime;*/
	
	/**
	 * 签收时间 - 开始范围
	 */
	private @ApiModelProperty("签收时间 - 开始范围") Long startSignTime;
	/**
	 * 签收时间 - 结束范围
	 */
	private @ApiModelProperty("签收时间 - 结束范围") Long endSignTime;
	
	/**
	 * 单据日期(签收时间)
	 *//*
	@Mapping
	@ApiModelProperty("单据日期(签收时间)")
	private Long billDate;*/

	/*@Mapping
	@ApiModelProperty("开始时间")
	private Long startTime;
	@Mapping
	@ApiModelProperty("结束时间")

	private Long endTime;*/
	@Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;

	@Mapping
	@ApiModelProperty(value = "收银状态(ARRIVED = 已收银 REFUSE_ARRIVED = 未收银)", notes="全部界面列表(ARRIVED, REFUSE_ARRIVED),未收银(REFUSE_ARRIVED)收传此处运单状态")
	private List<FinanceEnum> collectStatus;
	
	@Mapping
	@ApiModelProperty("付款方")
	private String payObject;
	@Mapping
	@ApiModelProperty(value = "收款单主键===收款单号")
	private Long riId;
	
	@Mapping
	@ApiModelProperty(value = "公司Id")
	private Long companyId;
	
	@Mapping
	@ApiModelProperty(value = "网点ID")
	private Long branchId;
	@Mapping
	@ApiModelProperty(value = "收款单主键===收款单号") @Logger
	private String code;
	/**
	 * 来源单号(签收单号)
	 */
	@Mapping
	@ApiModelProperty("来源单号(签收单号)") @Logger
	private String sourceNumber;
	@Mapping
	@ApiModelProperty("派车批次") @Logger
	private String batchCode;
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
