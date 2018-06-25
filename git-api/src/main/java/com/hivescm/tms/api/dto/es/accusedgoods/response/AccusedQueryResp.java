/**
 * 
 */
package com.hivescm.tms.api.dto.es.accusedgoods.response;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @date    2018年5月24日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class AccusedQueryResp implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<AccusedGoodsQueryResp> resp;
	
	@ApiModelProperty("总数")
	private Integer count;
}
