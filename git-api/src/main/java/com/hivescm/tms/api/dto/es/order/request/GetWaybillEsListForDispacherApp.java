package com.hivescm.tms.api.dto.es.order.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class GetWaybillEsListForDispacherApp implements Serializable {
	
	private static final long serialVersionUID = 4982720399297784380L;

	@ApiModelProperty("仓库ID")
    private Long warehouseId;
    
	/**
	 * 单号（可能是运单或订单）
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
