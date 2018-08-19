package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2018年3月24日 下午2:50:44
* 
*/
@Data
@ToString
public class QueryWaybillConditionsReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 查询条件
	 */
	private List<SearchCondition> searchCondition;
	
	/**
	 * 排序条件
	 */
	private List<OrderCondition> orderCondition;
	
	/**
	 * 分页条件
	 */
	private PageCondition pageCondition;
}
