package com.hivescm.tms.api.dto.es.order.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 通过承运商名称插叙运力请求dto
 * @author Administrator
 *
 */
@Data
@ToString
public class ManualSearchCapacityReqDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@Logger
	@ApiModelProperty("承运商名称关键字")
	private String keyWord;
	@Logger
	@ApiModelProperty("仓库id")
	private Long wareHouseId;
	@Logger
	@ApiModelProperty("线路id")
	private Long LineId;
}
