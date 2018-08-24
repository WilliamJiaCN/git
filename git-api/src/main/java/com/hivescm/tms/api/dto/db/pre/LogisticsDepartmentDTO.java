package com.hivescm.tms.api.dto.db.pre;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 物流组织部门
 * @author Administrator
 *
 */
@Data
@ToString
public class LogisticsDepartmentDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("物流组织id")
	private Integer companyId;
	@ApiModelProperty("物流组织名称")
	private String companyName;
	@ApiModelProperty("物流组织部门id")
	private Integer departmentId;
	@ApiModelProperty("物流组织部门名称")
	private String departmentName;
}
