/**
 * 
 */
package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author  boqiang.deng
 * @date    2018年5月30日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class QuerySignBySignBatchNumberReq implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("签收批次号")
	@Logger
	private String signCode;
	
	@ApiModelProperty("公司id")
	private Long companyId;

}
