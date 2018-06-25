package com.hivescm.tms.api.dto.es.distribution.redundancy;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.distribution.response.DistributionDriverVehicleRespEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 城配智能调度一键排线司机车辆冗余实体
 * @author ke.huang
 * @date 2017年9月4日
 * @company 蜂网供应链管理（上海）有限公司
 */
@EqualsAndHashCode(callSuper=false)
@Data
@ToString
public class DistributionDriverVehicleCableDTO extends DistributionDriverVehicleRespEsDTO implements Serializable{
	private static final long serialVersionUID = 13701894720933720L;
	@ApiModelProperty("关联运单")
	private List<WaybillEsDTO> waybillEsDTOs; 
}
