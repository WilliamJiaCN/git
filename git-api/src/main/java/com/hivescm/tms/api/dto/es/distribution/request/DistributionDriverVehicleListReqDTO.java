package com.hivescm.tms.api.dto.es.distribution.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 查询城配司机车辆列表请求DTO
 * @author ke.huang
 * @date 2017年9月1日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionDriverVehicleListReqDTO implements Serializable{
	private static final long serialVersionUID = -1488986465583138897L;
	@Mapping
	@ApiModelProperty(value="公司ID",required=true)
	private Long companyId;
	@Mapping
	@ApiModelProperty(value="关键词",required=false)
	private String keywords;
	@Mapping
	@ApiModelProperty(value="操作网点ID",required=true)
	private List<Long> branchId;
	@Mapping
	@ApiModelProperty(value="操作网点ID",required=true)
	private Integer orgId;
	@Mapping
	@ApiModelProperty(value="网点所在市",required=true)
	private String cityId;
}
