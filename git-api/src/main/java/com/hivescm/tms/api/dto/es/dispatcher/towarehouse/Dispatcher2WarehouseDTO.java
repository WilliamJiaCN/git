package com.hivescm.tms.api.dto.es.dispatcher.towarehouse;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


/**
 * 派车单与仓库交接单，接口实体
 * 
 * @author m5itao
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/11/3
 */
@Data
@ToString
public class Dispatcher2WarehouseDTO {
	  @Logger
	@ApiModelProperty("派车单主键")
	private Long transportDispatcherId;

	// @ApiModelProperty("派车批次")
	// private String transportDispatcherBatchCode;

	@ApiModelProperty("仓库主键")
	private Long warehouseId;
	  @Logger
	  @Required
	@ApiModelProperty("仓库交接单主键")
	private Long warehouseReceiptId;
	  @Logger
	@Required
	@ApiModelProperty("仓库交接单号")
	private String warehouseReceiptNo;
	@Required
	@ApiModelProperty("承运商主键")
	private Long companyId;

	@ApiModelProperty("承运商名称")
	private String companyName;

	@ApiModelProperty("状态")
	private Integer warehouseReceiptStatus;

	@ApiModelProperty("交接操作人")
	private String warehouseHandoverName;

	@ApiModelProperty("交接操作时间")
	private Long warehouseHandoverTime;

	@ApiModelProperty("是否生成装卸单")
	private Boolean allowAdd = false;

	@ApiModelProperty("出库确认人")
	private String warehouseOutstockName;

	@ApiModelProperty("出库确认时间")
	private Long warehouseOutstockTime;

	@ApiModelProperty("派车单创建人，1--TMS或2--WMS")
	private Integer founder;

	@ApiModelProperty("备注")
	private String remark;

	@ApiModelProperty("派车单明细")
	private List<DispatcherDetail2WarehouseDTO> dispatcherDetail2WarehouseDTOList = new ArrayList<DispatcherDetail2WarehouseDTO>();

}
