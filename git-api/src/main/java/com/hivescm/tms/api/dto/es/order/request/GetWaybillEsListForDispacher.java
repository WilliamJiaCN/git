package com.hivescm.tms.api.dto.es.order.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class GetWaybillEsListForDispacher implements Serializable {
	
	private static final long serialVersionUID = 4982720399297784380L;

	@ApiModelProperty("仓库ID")
    private Long warehouseId;
    
    /**
     * 订单号
     */
    @ApiModelProperty(value = "订单号")
    private String orderCode;
    /**
     * 运单号
     */
    @ApiModelProperty(value = "运单号")
    private String code;

    @ApiModelProperty("目的地名称")
    private String destName;
    
    @ApiModelProperty("收货人")
    private String receiptUser;
    
    @ApiModelProperty("剔除的waybillId")
    private List<Long> waybillIdList;
}
