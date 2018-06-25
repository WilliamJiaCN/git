package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 取消付款 数据传输类
 * 
 * @author wenxiong.jia
 * @date 2018/5/2
 */
@Data
@ToString
public class FinanceManagePayCancelReqDTO implements Serializable{

	private static final long serialVersionUID = 1814811300099167100L;

	/**
	 * 主键
	 */
	@Logger
	@Required
	@ApiModelProperty(value = "主键", required = true)
	private Long id;
	/**
	 * 操作人ID
	 */
	@ApiModelProperty(value = "操作人ID", hidden = true)
	private Integer oprId;
	/**
	 * 操作人姓名
	 */
	@ApiModelProperty(value = "操作人姓名", hidden = true)
	private String oprName;
}

