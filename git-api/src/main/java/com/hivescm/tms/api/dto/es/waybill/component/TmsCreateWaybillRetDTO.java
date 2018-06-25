package com.hivescm.tms.api.dto.es.waybill.component;

import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@ToString
@Data
public class TmsCreateWaybillRetDTO {
	
	/**
	 * 运单id
	 */
	@Mapping
	private @ApiModelProperty("waybillId") Long waybillId;
	/**
	 * 运单编号
	 */
	@Mapping
	private @ApiModelProperty("waybillCode") String waybillCode;
	/**
	 * 网点id
	 */
	@Mapping
	private @ApiModelProperty("invoiceOrgId") Integer invoiceOrgId;
	/**
	 * 网点名称
	 */
	@Mapping
	private @ApiModelProperty("invoiceOrgName") String invoiceOrgName;
	/**
	 * 订单号
	 */
	@Mapping
	private @ApiModelProperty("orderCode") String orderCode;
	/**
	 * 运费
	 */
	@Mapping
	private @ApiModelProperty("freight") BigDecimal freight;
	/**
	 * 仓储服务商在承运商集团下的集团客户ID
	 */
	@Mapping
	private @ApiModelProperty("warehouseServerGroupId") Long warehouseServerGroupId;

}
