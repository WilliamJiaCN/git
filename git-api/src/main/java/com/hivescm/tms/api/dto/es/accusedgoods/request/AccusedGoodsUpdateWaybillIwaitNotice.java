/**
 * 
 */
package com.hivescm.tms.api.dto.es.accusedgoods.request;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author  boqiang.deng
 * @date    2018年5月21日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class AccusedGoodsUpdateWaybillIwaitNotice implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long waybillId;
	
	private Integer updateUser;
	
	private String updateUserName;
	
	private Boolean iwaitNotice;
	
	private Long companyId;

}
