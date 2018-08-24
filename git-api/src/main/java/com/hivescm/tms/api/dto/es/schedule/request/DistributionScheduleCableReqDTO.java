package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.tms.api.dto.es.distribution.response.DistributionDriverVehicleRespEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 城配智能匹配排线请求实体
 * @author ke.huang
 * @date 2017年9月8日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionScheduleCableReqDTO implements Serializable{
	private static final long serialVersionUID = -7955609406064875920L;
	private List<DistributionDriverVehicleRespEsDTO> driverVehicles;
	private List<WaybillEsDTO> waybills;
	public DistributionScheduleCableReqDTO(List<DistributionDriverVehicleRespEsDTO> driverVehicles,
			List<WaybillEsDTO> waybills) {
		super();
		this.driverVehicles = driverVehicles;
		this.waybills = waybills;
	}
	public DistributionScheduleCableReqDTO() {
		super();
	}
}
