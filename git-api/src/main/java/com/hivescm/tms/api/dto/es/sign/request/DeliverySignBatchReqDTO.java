package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
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
public class DeliverySignBatchReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	//批量签收元素列表
	@Required
	@ApiModelProperty("派车单明细id")
	private List<Long> detailIds;
	@Required
	@ApiModelProperty("公司id")
	private Long companyId;
	@Required
	@ApiModelProperty("当前网点id")
	private Long orgId;
	@Required
	@ApiModelProperty("当前网点名称")
	private String orgName;
	@Mapping
	@ApiModelProperty("经办人id")
	private Integer handler;
	
	@ApiModelProperty("集团id")
	private Integer groupId;
	@Mapping
	@ApiModelProperty("经办人名称")
	private String handlerName;
}
