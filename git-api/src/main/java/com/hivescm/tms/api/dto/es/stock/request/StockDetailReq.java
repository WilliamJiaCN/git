/**
 * 
 */
package com.hivescm.tms.api.dto.es.stock.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.framework.validation.annotation.Required;

import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @date    2018年6月7日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class StockDetailReq implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 查询条件
	 */
	@Required
	private List<SearchCondition> searchCondition;

}
