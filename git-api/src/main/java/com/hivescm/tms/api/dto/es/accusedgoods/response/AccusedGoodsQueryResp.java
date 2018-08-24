/**
 * 
 */
package com.hivescm.tms.api.dto.es.accusedgoods.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author  boqiang.deng
 * @date    2018年5月18日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class AccusedGoodsQueryResp implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("控货模块字段")
	private AccuseGoodsResp accuseGoods;
	
	@ApiModelProperty("运单字段")
	private AccuseGoodsWaybillResp waybill;
	
	@ApiModelProperty("总数")
	private Integer count;
}
