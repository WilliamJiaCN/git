package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2018年3月24日 上午11:51:01
* 
*/
@Data
@ToString
public class PCSignReq implements Serializable{
	private static final long serialVersionUID = 1L;

	@Mapping()
	@ApiModelProperty("查询开始时间")
	private Long startTime;

	@Mapping()
	@ApiModelProperty("查询结束时间")
	private Long endTime;
	
	@Mapping()
	@ApiModelProperty("目的网点")
	private List<Integer> destOrgId;
	
	@Mapping()
	@ApiModelProperty("发货网点")
	private List<Integer> invoiceOrgId;
	@Mapping()
	@ApiModelProperty("userId")
	private Integer userId;
	
	@Mapping
	@ApiModelProperty("当前网点Id(必输)")
	private Integer currentDotId;
	
	@Mapping()
	@ApiModelProperty("收货人")
	private String receiptUser;
	
	@Mapping()
	@ApiModelProperty("运单号") @Logger
	private String waybillCode;
	
	@Mapping()
	@ApiModelProperty("签收批次号") @Logger
	private String signBatchNumber;
	
	@Mapping()
	@ApiModelProperty("签收人")
	private String signPeople;
	
	@Mapping()
	@ApiModelProperty("公司id")
	private Long companyId;

	@Mapping()
	@ApiModelProperty("派送方式")
	private Integer deliveryType;

	@Mapping()
	@ApiModelProperty(value = "签收状态(NO_SIGN= 未签收 SIGNED = 全部签收 PARTIAL_SIGN =部分签收 REFUSE_SIGN= 全部拒签)")
//	@Required
	private SignStatusEnum signStatus;
	private @ApiModelProperty("分页 - 每页显示数") Integer pageSize = 100;
	/**
	 * 分页 - 当前页数
	 */
	private @ApiModelProperty("分页 - 当前页数") Integer currentPage = 1;
}
