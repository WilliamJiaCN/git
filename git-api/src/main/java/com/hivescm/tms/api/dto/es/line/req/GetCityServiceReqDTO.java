package com.hivescm.tms.api.dto.es.line.req;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serializable;
@Data
@Service
public class GetCityServiceReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Required
	@ApiModelProperty("改变后城市编码")
	private String changeCityCode;
	
	@ApiModelProperty("线路id")
	private Long lineId;
}
