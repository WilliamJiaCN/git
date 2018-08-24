package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author  boqiang.deng
 * @date    2018年3月30日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class SelfSignBatchReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Required
	@Logger
	@ApiModelProperty("批量签收的运单id")
	private List<Long> waybillIds;
	@ApiModelProperty("公司id")
	private Long companyId;
	@ApiModelProperty("当前网点id")
	private Long orgId;
	
	@ApiModelProperty("当前网点id名称")
	private String orgName;
	
	@ApiModelProperty("集团id")
	private Integer groupId;
	
	@Mapping
	@ApiModelProperty("经办人id")
	private Integer handler;
	
	@Mapping
	@ApiModelProperty("经办人名称")
	private String handlerName;
}
