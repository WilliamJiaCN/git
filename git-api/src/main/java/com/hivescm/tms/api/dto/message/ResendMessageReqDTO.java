package com.hivescm.tms.api.dto.message;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
@Data
@ToString
public class ResendMessageReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("节点Id")
	private Integer bussNode;
	@ApiModelProperty("运单id")
	private Long waybillId;
	@ApiModelProperty("目标对象类型")
	private Integer sendTarget;
}

