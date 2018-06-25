package com.hivescm.tms.api.dto.es.global.request;

import java.io.Serializable;

import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2017年12月14日 上午10:44:44
* 
*/
@Data
@ToString
public class GlobalProviderReq implements Serializable{
	private static final long serialVersionUID = 1L;
	@Logger 
	@ApiModelProperty("业务单元id")
	private Long orgId;
	@Logger 
	@ApiModelProperty("关键字")
	private String keyword;
}
