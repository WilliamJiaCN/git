package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 收单确认 数据传输类
 * 
 * @author wenxiong.jia
 * @date 2018/5/9
 */
@Data
@ToString
public class FinanceManageGrantCommonReqDTO implements Serializable{

	private static final long serialVersionUID = 604196868366428534L;

	/**
	 * id
	 */
	@Logger
	@Required
	@ApiModelProperty(value = "Id")
	private List<Long> ids;
	/**
	 * 当前网点ID
	 */
	@ApiModelProperty("当前网点ID(非必需)")
	private Integer curNetworkId;

	/**
	 * 当前网点名称
	 */
	@ApiModelProperty("当前网点名称(非必需)")
	private String curNetworkName;
	/**
	 * 操作人ID
	 */
	@ApiModelProperty(value = "操作人ID(非必需)")
	private Integer oprId;
	/**
	 * 操作人姓名
	 */
	@ApiModelProperty(value = "操作人姓名(非必需)")
	private String oprName;
}

