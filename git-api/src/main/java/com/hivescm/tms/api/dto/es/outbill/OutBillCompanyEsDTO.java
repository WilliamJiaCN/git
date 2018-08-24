package com.hivescm.tms.api.dto.es.outbill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OutBillCompanyEsDTO {
	@Mapping
	@ApiModelProperty("主键ID")
	private Long id;
	
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
	
	@Mapping
	@ApiModelProperty("序号")
    private Integer sequence;
	
	@Mapping
	@ApiModelProperty("外发单ID")
    private Long outbillId;
	
	@Mapping
	@ApiModelProperty("外发公司ID")
    private Long outCompanyId;
	@Mapping
	@ApiModelProperty("外发公司名称")
    private String outCompanyName;
	
	@Mapping
	@ApiModelProperty("外发公司联系人")
    private String outContactName;
	
	@Mapping
	@ApiModelProperty("外发公司联系电话")
    private String outContactTel;
	
	@Mapping
	@ApiModelProperty("外发网点ID")
    private Long startBranch;
	
	@Mapping
	@ApiModelProperty("外发网点名称")
    private String startBranchName;
	
	@Mapping
	@ApiModelProperty("到达网点id")
    private Long endBranch;
	
	@Mapping
	@ApiModelProperty("到达网点名称")
    private String endBranchName;
	
	@Mapping
	@ApiModelProperty("网点联系电话")
    private String branchContactName;
	
	@Mapping
	@ApiModelProperty("网点联系电话")
    private String branchContactTel;
	@Mapping
    private Integer createUser;
	@Mapping
    private String createUserName;
	@Mapping
    private Long createTime;
	@Mapping
    private String updateUserName;
	@Mapping
    private Integer updateUser;
	@Mapping
    private Long updateTime;
}
