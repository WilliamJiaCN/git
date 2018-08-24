package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
@Data
@ToString
public class SignReqDTO implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	@Mapping()
	@ApiModelProperty("查询开始时间")
	private Long startTime;

	@Mapping()
	@ApiModelProperty("查询结束时间")
	private Long endTime;
	
	@Mapping()
	@ApiModelProperty("目的网点")
	private Integer destOrgId;
	
	@Mapping()
	@ApiModelProperty("发货网点")
	private Integer invoiceOrgId;
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
	@ApiModelProperty(value = "运单状态(ARRIVED = 未签收 REFUSE_SIGN = 拒签)", notes="全部界面列表(ARRIVED,REFUSE_SIGN,PARTIAL_SIGN,SIGNED),拒签(REFUSE_SIGN),未签(ARRIVED)收传此处运单状态")
	private List<WaybillStatusEnum> waybillStatus;
	
	@Mapping()
	@ApiModelProperty(value = "签收状态(SIGNED = 全部签收 PARTIAL_SIGN =部分签收)",notes="全部签收，部分签收传此处运单状态")
	private SignStatusEnum signStatus;
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

	@Mapping()
	@ApiModelProperty("客户")
	private List<String> client ;
	

	
}

