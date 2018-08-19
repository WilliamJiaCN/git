package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 运单状态请求ES DTO
 * @author ke.huang
 * @date 2017年8月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@ToString
@Data
public class ChangeWaybillReqEsDTO implements Serializable{
	private static final long serialVersionUID = -8623365026731916885L;
	@Logger
	@Mapping({"WaybillEsDTO.id"})
	@ApiModelProperty(value="运单ID",required=true)
	private Long waybillId;
	@Mapping
	@ApiModelProperty("公司ID")
	private Integer companyId;
	@Mapping
	@ApiModelProperty(value="运单状态",required=true)
	private Integer status;
	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;
	
}
