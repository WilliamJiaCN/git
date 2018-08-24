package com.hivescm.tms.api.dto.es.global.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.dto.es.global.GlobalCarrierConfigEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 全局承运商顺序配置请求DTO
 * @author ke.huang
 * @date 2017年10月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GlobalCarrierConfigReqDTO implements Serializable{
	private static final long serialVersionUID = -681906014060178105L;
	@ApiModelProperty("经销商顺序列表 ")
	private List<GlobalCarrierConfigEsDTO> list;
	@ApiModelProperty("城市ID") @Logger 
	private String cityId;
	@ApiModelProperty("更新人ID")
	private Integer updateUser;
	@ApiModelProperty("更新人姓名")
	private String updateUserName;
	
	public Boolean isValid(){
		return null != list && list.size() != 0 && null != cityId;
	}
}
