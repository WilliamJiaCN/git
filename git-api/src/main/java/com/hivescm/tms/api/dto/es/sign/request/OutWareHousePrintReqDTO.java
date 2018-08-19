/**
 * 
 */
package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author  boqiang.deng
 * @date    2018年6月7日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class OutWareHousePrintReqDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty("签收id")
	@Required
	private Long signId;
	
}
