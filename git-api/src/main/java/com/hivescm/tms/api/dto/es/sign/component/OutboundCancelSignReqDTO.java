/**
 * 
 */
package com.hivescm.tms.api.dto.es.sign.component;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @date    2018年4月17日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class OutboundCancelSignReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("签收批次号")
	@Required
	private List<String> signBatchNumber;
	
	@ApiModelProperty("取消签收人")
	@Required
	private Integer updateUser;
	
	@ApiModelProperty("公司id")
	@Required
	private Long companyId;

}
