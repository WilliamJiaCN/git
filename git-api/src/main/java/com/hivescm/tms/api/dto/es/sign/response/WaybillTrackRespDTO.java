/**
 * 
 */
package com.hivescm.tms.api.dto.es.sign.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @date    2018年4月10日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class WaybillTrackRespDTO {
	@ApiModelProperty("签收时间")
	private Long signTime;
	
	@ApiModelProperty("签收图片")
	private String pictures;
 
}
