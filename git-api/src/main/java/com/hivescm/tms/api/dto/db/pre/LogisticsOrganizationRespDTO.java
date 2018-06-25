package com.hivescm.tms.api.dto.db.pre;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
/**
 * 物流组织信息返回实体
 * @author Administrator
 *
 */
@Data
@ToString
public class LogisticsOrganizationRespDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("物流组织公司信息")
	private List<LogisticsOrganizationCompanyRespDTO> logisticsOrganizationCompany;
	@ApiModelProperty("物流组织部门信息")
	private List<LogisticsDepartmentDTO> logisticsDepartment;
}
