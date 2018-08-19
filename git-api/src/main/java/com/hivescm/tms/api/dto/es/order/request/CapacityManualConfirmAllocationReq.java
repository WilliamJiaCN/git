package com.hivescm.tms.api.dto.es.order.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class CapacityManualConfirmAllocationReq implements Serializable {

	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("承运商id")
	private Integer carrierId;
	@Mapping
	@ApiModelProperty("承运商名称")
	private String carrierName;
	@Mapping
	@ApiModelProperty("线路id")
	private Long lineId;
	@Mapping
	@ApiModelProperty("线路名称")
	private String lineName;
	@Mapping
	@ApiModelProperty("服务名称")
	private String serviceName;
	@Mapping
	@ApiModelProperty("服务id")
	private Long serviceId;
	@Mapping
	@ApiModelProperty("物流组织配送部门ID")
    private Integer branchId;
	@Mapping
	@ApiModelProperty("物流组织配送部门名称")
    private String branchName;
	@Mapping
	@ApiModelProperty("仓库ID")
    private Long warehouseId;
	@Mapping
	@ApiModelProperty("仓库名称")
    private String warehouseName;
	@Logger
	@ApiModelProperty("订单id")
	private List<Long> ids;
	@Mapping
	@ApiModelProperty("指定配送时间")
    private Long dispatchingTime;//yyyy-MM-dd HH:mm:ss 配送时间提取HH:mm:ss
	@Mapping
	@ApiModelProperty("指定提货时间")
	private Long deliveryTime;//yyyy-MM-dd HH:mm:ss 提货时间提取HH:mm:ss
	
	@Mapping
	@ApiModelProperty("创建人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("创建人姓名")
    private String  updateUserName;
}
