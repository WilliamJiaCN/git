package com.hivescm.tms.api.dto.es.distribution.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/11
 */
@Data
@ToString
public class DistributionPendingBillReqDTO extends DistributionPublicReqDTO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("录单日期 - 开始范围")
	private Long startOrderDate;
	@ApiModelProperty("录单日期 - 结束范围")
	private Long endOrderDate;
	@Mapping
	@ApiModelProperty(value = "收货区域",notes = "格式例如：陕西省/西安市/未央区/张家堡")
	private String ReceivingArea;
}
