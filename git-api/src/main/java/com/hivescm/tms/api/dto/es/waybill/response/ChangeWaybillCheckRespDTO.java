package com.hivescm.tms.api.dto.es.waybill.response;

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
public class ChangeWaybillCheckRespDTO implements Serializable {
	
	private static final long serialVersionUID = -2547776949800963585L;
	
	private @ApiModelProperty("是否可作运单信息更改") Boolean updateWaybill = false;
	
	private @ApiModelProperty("是否可作收货人信息更改") Boolean updateRecieptInfo = false;
	
	private @ApiModelProperty("是否可作发货人信息更改") Boolean updateInvoiceInfo = false;
	
	private @ApiModelProperty("是否可作费用和付款方式更改") Boolean updateFeeAndPayWay = false;
	
	private @ApiModelProperty("是否可作货物详情更改") Boolean updateWaybillGoods = false;

}
