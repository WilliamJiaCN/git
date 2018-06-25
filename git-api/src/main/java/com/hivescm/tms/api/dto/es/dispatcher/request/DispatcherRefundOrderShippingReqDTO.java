package com.hivescm.tms.api.dto.es.dispatcher.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.dto.db.waybill.WaybillGoodsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 派车单请求体
 * TmsDispatcherEsDTO
 *
 * @author lhf
 * @date 2017年11月16日
 * @company 蜂网供应链管理
 */
@Data
@ToString
public class DispatcherRefundOrderShippingReqDTO implements Serializable {

    private static final long serialVersionUID = -6933611350149830800L;
    @ApiModelProperty("公司id")
    private Long companyId;
    @Logger
    @ApiModelProperty("司机id")
	private Integer driverId;
    @Logger
    @ApiModelProperty("运单Id")
    private Long waybillId;
    @Logger
	@ApiModelProperty("运单号")
	private String waybillCode;
    @Logger
    @ApiModelProperty("订单号")
	private String orderCode;
    @ApiModelProperty("货物信息")
    private List<WaybillGoodsDTO>  wayGoodsList;

  

}
