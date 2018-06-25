package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 取消签收参数体
 */
@Data
@ToString
public class CancelDeliverySignBatchReqDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Required
	@ApiModelProperty("签收单ID")
	private Long id;
	@Required
	@ApiModelProperty("公司ID")
	private  Long companyId;
//	@Required
//	@ApiModelProperty("运单ID")
//	private Long waybillId;
//	@Required
//	@ApiModelProperty("签收件数")
//	private Integer signNumber;
//	@Required
//	@ApiModelProperty("拒签件数")
//	private Integer refuseNumber;

	@Required
	@ApiModelProperty("当前网点ID")
	private Integer curentOrgId;

	/**
	 * 修改人
	 */
	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;

	/**
	 * 修改时间
	 */
	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;

	@ApiModelProperty("修改人姓名")
	private String updateUserName;
}
