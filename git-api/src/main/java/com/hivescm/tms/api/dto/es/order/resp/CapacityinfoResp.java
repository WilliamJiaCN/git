package com.hivescm.tms.api.dto.es.order.resp;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 手动分配查询运力信息返回信息
 * @author Administrator
 *
 */
@Data
@ToString
public class CapacityinfoResp implements Serializable{

	private static final long serialVersionUID = 1L;

	@Mapping
	@ApiModelProperty("承运商名称")
	private String unifyProviderName;
	@Mapping
	@ApiModelProperty("线路名称")
	private String lineName;
	@Mapping
	@ApiModelProperty("线路id")
	private Long lineId;
	@Mapping
	@ApiModelProperty("承运商id")
	private Integer unifyProviderId;
	@Mapping
	@ApiModelProperty("提货时间")
	private Long deliveryTime;
	@Mapping
	@ApiModelProperty("派车时间")
	private Long dispatcherTime;
	@Mapping
	@ApiModelProperty("服务名称")
	private String serviceName;
	@Mapping
	@ApiModelProperty("服务id")
	private Long serviceId;
	
	@Mapping
	@ApiModelProperty("物流组织配送部门ID")
    private Long unifyProviderDepartId;
	@Mapping
	@ApiModelProperty("物流组织配送部门名称")
    private String unifyProviderDepartName;
	
	@Mapping
	@ApiModelProperty("仓储服务商ID")
    private String providerName;
	@Mapping
	@ApiModelProperty("仓储服务商ID")
    private Long providerId;
	@Mapping
	@ApiModelProperty("仓库ID")
    private Long warehouseId;
	@Mapping
	@ApiModelProperty("仓库名称")
    private String warehouseName;
	@Mapping
	@ApiModelProperty("站点名称")
    private String siteName;
	
}
