package com.hivescm.tms.api.dto.es.global.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.dto.es.global.redundancy.GlobalLineScaleConfigDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 全局配置承运商比例分配请求DTO
 * @author ke.huang
 * @date 2017年10月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GlobalLineScaleConfigReqDTO implements Serializable{
	private static final long serialVersionUID = -337002305722279278L;
	@ApiModelProperty("城市ID") @Logger 
	private String cityId;
	@ApiModelProperty("城市名称")
	private String cityName;
	@ApiModelProperty("线路列表") 
	private List<GlobalLineScaleConfigDTO> list;
	@ApiModelProperty("更新人")
	private Integer updateUser;
	@ApiModelProperty("更新人姓名")
	private String updateUserName;
	
	public Boolean isValid(){
		return null != cityId && null != list && list.size() != 0;
	}
}
