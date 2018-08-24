package com.hivescm.tms.api.dto.es.dispatcher.towarehouse;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 派车单与仓库交接单，接口实体
 * 明细表
 * @author  m5itao
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/11/3
 */
@Data
@ToString
public class DispatcherDetail2WarehouseDTO {

	  @Logger
    @ApiModelProperty("外部订单号")
    private String customerOrderCode;
	  @Logger
    @ApiModelProperty("原订单号")
    private String originalOrderCode;
	  @Logger
    @ApiModelProperty("运单号")
    private String waybillCode;
	  @Logger
    @ApiModelProperty("配送次序")
    private Integer lineSort;


}
