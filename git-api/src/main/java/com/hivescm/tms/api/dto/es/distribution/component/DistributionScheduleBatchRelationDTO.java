package com.hivescm.tms.api.dto.es.distribution.component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 城配智能调度司机运单匹配关系实体
 * @author ke.huang
 * @date 2017年9月4日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionScheduleBatchRelationDTO implements Serializable{
	private static final long serialVersionUID = -8789464413538244507L;
	
	//司机车辆
	@Mapping
	@ApiModelProperty("司机ID")
	private Integer driverId;
	@Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;
	@Mapping
	@ApiModelProperty("手机号码")
	private String phoneNo;
	@Mapping
	@ApiModelProperty("车辆ID")
	private Integer vehicleId;
	@Mapping
	@ApiModelProperty("车牌号码")
	private String vehicleNum;
//	@Mapping
//	@ApiModelProperty("装载率(重量)")
//	private String volWeight;
//	@Mapping
//	@ApiModelProperty("装载率(体积)")
//	private String volLoading;
	@Mapping
	@ApiModelProperty("预计总里程")
	private String totalMileage;
	
	//运单
	@Mapping
	@ApiModelProperty("运单ID，按线路顺序排序")
	private List<Long> waybillId;
	@ApiModelProperty("运费") // >> 派车成本
	private BigDecimal cost;
	
}
