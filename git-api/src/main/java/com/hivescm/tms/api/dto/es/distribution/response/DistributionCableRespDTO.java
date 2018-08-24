package com.hivescm.tms.api.dto.es.distribution.response;

import com.hivescm.tms.api.dto.es.distribution.redundancy.DistributionDriverVehicleCableDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 城配智能调度一键排线响应实体
 * 包含一键排线司机与运单关联关系
 * 包含未匹配到的运单与司机
 * @author ke.huang
 * @date 2017年9月7日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionCableRespDTO implements Serializable{
	private static final long serialVersionUID = 7727787049110925327L;
	@ApiModelProperty("城配司机车辆->运单关联关系")
	private List<DistributionDriverVehicleCableDTO> relations; 
	@ApiModelProperty("未匹配到的运单")
	private List<WaybillEsDTO> waybills = new ArrayList<>();
}
