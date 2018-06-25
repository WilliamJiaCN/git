package com.hivescm.tms.api.dto.db.pre;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2017年11月21日 下午7:35:17
* 经销商和仓储服务商请求体
*/
@Data
@ToString
public class DistributorsServiceProviderReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("业务单元名字")
	private String keywords;
	@ApiModelProperty("职能类型 仓储服务商==2,经销商 == 1")
	private Integer funcType;
}
