package com.hivescm.tms.api.feign.base;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.db.base.BaseDriverDTO;
import com.hivescm.tms.api.dto.db.base.BaseVehicleDTO;
import com.hivescm.tms.api.dto.es.base.request.VehicleQueryDTO;
import com.hivescm.tms.constants.FeignEnvironment;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = FeignEnvironment.BASE_SERVER_NAME, path = FeignEnvironment.BASE_SERVER_PATH)
public interface VehicleService {
	/**
	 * 根据车牌号检索车辆
	 * @param vehicleQueryDTO
	 * @return
	 */
	@PostMapping("/vehicle/queryVehicleByVehicleNo")
	public DataResult<List<BaseVehicleDTO>> queryVehicle(@RequestBody VehicleQueryDTO vehicleQueryDTO); 
	/**
	 * 根据车牌号查询司机列表信息
	 * @param vehicleQueryDTO
	 * @return
	 */
	@PostMapping("/driver/queryContactDriverByVehicleNo")
	public DataResult<List<BaseDriverDTO>> queryContactDriverByVehicleNo(@RequestBody VehicleQueryDTO vehicleQueryDTO);
}
