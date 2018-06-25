/**
 * 
 */
package com.hivescm.tms.api.dto.es.sign.request;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @date    2018年3月29日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class SignInitializeWayBillReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 运单号
	 */
	private String wayillCode;
	
	/**
	 * 当前网点（查询对应库存网点）
	 */
	private Integer orgId;
	
	/**
	 * 公司id
	 */
	private Integer companyId; 

}
