package com.hivescm.tms.api.dto.es.accusedgoods.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author  boqiang.deng
 * @date    2018年5月21日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class AccusedGoodsQueryWaybillReqDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(name="当前网点")
	private Long orgId;
	@Required
	@ApiModelProperty(name="运单号")
	private String waybillCode;
	
	@ApiModelProperty(name="公司id")
	private Long companyId;
}
