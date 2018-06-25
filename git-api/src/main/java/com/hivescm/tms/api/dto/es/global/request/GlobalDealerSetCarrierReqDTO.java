package com.hivescm.tms.api.dto.es.global.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.dto.es.global.redundancy.GlobalDealerSetCarrierDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 经销商设置承运商线路请求DTO
 * @author ke.huang
 * @date 2017年10月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GlobalDealerSetCarrierReqDTO implements Serializable{
	private static final long serialVersionUID = 8184471085792900582L;
	@ApiModelProperty("市ID") @Logger 
	private String cityId;
	@ApiModelProperty("市名称")
	private String cityName;
	@ApiModelProperty("经销商ID") @Logger 
	private Long dealerId;
	@ApiModelProperty("经销商姓名")
	private String dealerName;
	@ApiModelProperty("承运商列表")
	private List<GlobalDealerSetCarrierDTO> carriers;
	
	@ApiModelProperty("修改人")
	private Integer updateUser;
	@ApiModelProperty("修改人姓名")
	private String updateUsrName;
	
	public Boolean isValid(){
		return null != cityId && null != carriers && carriers.size() != 0;
	}
}
