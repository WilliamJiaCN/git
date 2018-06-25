package com.hivescm.tms.api.dto.es.order.resp;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.order.request.ManualConfirmOrderReq;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class CapacityManualConfirmAllocationResp implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("状态发生改变得运单号")
	private List<String> failed;
	@ApiModelProperty("分配成功的数量")
	private Integer successCount;
	@ApiModelProperty("是否更新成功")
	private Boolean flag;
}
