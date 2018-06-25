/**
 * 
 */
package com.hivescm.tms.api.dto.es.sign.request;

import java.io.Serializable;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @date    2018年4月23日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class SearchSignCodeReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("运单号")
	@Logger
	@Required
	private String waybillCode;
	
	@ApiModelProperty("当前网点")
	private Integer currentDotId;
	
	@ApiModelProperty("公司id")
	private Long companyId;

}
