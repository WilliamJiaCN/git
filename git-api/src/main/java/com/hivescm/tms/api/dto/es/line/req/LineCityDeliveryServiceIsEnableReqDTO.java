package com.hivescm.tms.api.dto.es.line.req;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.capacity.line.LineCityDeliveryServiceStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class LineCityDeliveryServiceIsEnableReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Logger
	@Required
	@Mapping
	@ApiModelProperty("服务id")
	private Long id;
	@Required
	@ApiModelProperty("服务状态")
	private LineCityDeliveryServiceStatusEnum status;
	@ApiModelProperty("修改人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;
}
