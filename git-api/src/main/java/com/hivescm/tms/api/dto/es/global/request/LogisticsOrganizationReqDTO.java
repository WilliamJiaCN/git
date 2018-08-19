package com.hivescm.tms.api.dto.es.global.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.enums.capacity.global.LogisticsOrganizationEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
/**
 * 请求boss物流组织dto
 * @author Administrator
 *
 */
@Data
@ToString
public class LogisticsOrganizationReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("物流组织类型")
	private LogisticsOrganizationEnum unifyType;
	@ApiModelProperty("所属组织id")
	private Integer stockOrg;
	/**
	 * 城市数据字典码（选填）（集团内必填）
	 */
	@Logger 
	@ApiModelProperty("城市id")
	private String cityDicCode;
	/**
	 * 仓库ID（选填）（集团间必填）
	 */
	@Logger 
	@ApiModelProperty("仓库id")
	private Long warehouseId;
	
	@ApiModelProperty("物流组织id--在请求部门信息时传")
	private Long companyId;
}
