package com.hivescm.tms.api.dto.es.dispatcher.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.db.waybill.WaybillGoodsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  m5itao
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/11/10
*/
@Data
@ToString
public class DispatcherDirverWaybillReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("公司id")
	@Required
	private Long companyId;
	
	@Mapping
	@ApiModelProperty("司机id")
	private Integer driverId;
	@Logger
	@Mapping
	@ApiModelProperty("运单号")
	@Required
	private String waybillCode;
	@Logger
	@Mapping
	@ApiModelProperty("订单号")
	private String orderCode;
	@Logger
	@Mapping
	@ApiModelProperty("运单Id")
    private Long waybillId;

    @Mapping
    @ApiModelProperty("订单运输的类型，包括：销售订单、销退单；自动接收（非必填）")
    private Integer orderType;
    
    @Mapping
    @ApiModelProperty("货物信息")
    private List<WaybillGoodsDTO>  wayGoodsList;
    
    @Mapping
    @ApiModelProperty("操作人")
    private String operateUserName;

}

