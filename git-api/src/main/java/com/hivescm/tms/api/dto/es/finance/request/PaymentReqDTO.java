package com.hivescm.tms.api.dto.es.finance.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.enums.biz.finance.PaymentEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
/**
 * 
 * <p>
 * Title:ReceiptReqDTO
 * </p>
 * <p>
 * Description: 付款单条件查询
 * </p>
 * <p>
 * Company: 蜂网供应链（上海）有限公司
 * </p>
 * 
 * @author 王小雪
 * @date 下午2:00:38
 */
public class PaymentReqDTO implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/** 签收单 */
    @Mapping
    @ApiModelProperty("签收时间")
    private Long signTime;
    
    /**
	 * 发放时间
	 */
	@Mapping
	@ApiModelProperty("发放时间")
	private Long grantTime;
	
	
	/**
     * 确认收银时间
     */
    @Mapping
    @ApiModelProperty("确认收银时间")
    private Long confirmReceiptTime;

   /* *//**
	 * 录单时间
	 *//*
	@Mapping
	@ApiModelProperty("录单时间")
	private Long grantTime;*/
	
	
	
	
	@Mapping
	@ApiModelProperty("userId")
	private Integer userId;
	@Mapping
	@ApiModelProperty("开始时间")
	private Long startTime;
	@Mapping
	@ApiModelProperty("结束时间")

	private Long endTime;
	/**
	 * 收款方
	 */
	@Mapping
	@ApiModelProperty("收款方")
	private String payeeObject;
	/**
	 * 来源单号（运单号）
	 */
	@Mapping
	@ApiModelProperty("来源单号（运单号）") @Logger
	private String sourceCode;

	@Mapping
	@ApiModelProperty(value = "发放状态(ARRIVED = 已发放 REFUSE_ARRIVED = 未发放)", notes="全部界面列表(ARRIVED, REFUSE_ARRIVED),未发放(REFUSE_ARRIVED)收传此处运单状态")
	private List<PaymentEnum> grantStatus;

	@Mapping
	@ApiModelProperty(value = "付款单主键===收款单号") @Logger
	private Long pId;
	@Mapping
	@ApiModelProperty(value = "付款单主键===收款单号") @Logger
	private String pCode;
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
