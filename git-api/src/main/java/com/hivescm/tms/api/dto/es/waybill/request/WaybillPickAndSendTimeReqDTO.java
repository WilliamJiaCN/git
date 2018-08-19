package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 修改运单预约提货和送货时间请求体
 * @author lifan
 *
 * 2017年12月14日
 *
 */

@Data
@ToString
public class WaybillPickAndSendTimeReqDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Logger
	@ApiModelProperty("运单ID")
	private Long id;
	
	@ApiModelProperty("公司ID")
	private Integer companyId;
	
	@ApiModelProperty("预约提货时间") 
    private Long deliveryPickTime;

	@ApiModelProperty("预约送货时间") 
    private Long deliverySendTime;
	@Logger
	@ApiModelProperty("订单编号") 
    private String orderCode;
	@Logger
	@ApiModelProperty("运单号") 
    private String code;
	
	@ApiModelProperty("仓库ID")
    private Long warehouseId;

}
