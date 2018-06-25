package com.hivescm.tms.api.dto.es.outbill.req;

import java.util.List;

import com.hivescm.tms.api.dto.es.outbill.OutBillCompanyEsDTO;
import com.hivescm.tms.api.dto.es.outbill.OutBillDetailEsDTO;
import com.hivescm.tms.api.dto.es.outbill.OutBillFeeEsDTO;
import com.hivescm.tms.api.dto.es.outbill.OutBillInfoEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OutBillAddReqDTO {

	@ApiModelProperty("外发主表信息")
	private OutBillInfoEsDTO OutBillInfoEsDTO;
	
	@ApiModelProperty("外发公司信息")
	private List<OutBillCompanyEsDTO> outBillCompanys;
	
	@ApiModelProperty("外发明细信息")
	private List<OutBillDetailEsDTO> outBillDetails;
	
	@ApiModelProperty("外发费用信息")
	private List<OutBillFeeEsDTO> outBillFees;
	
	@ApiModelProperty("公司ID")
	private Long companyId;
	
	@ApiModelProperty("公司名称")
	private String companyName;
	
	@ApiModelProperty("网点ID")
	private Long branchId;
	
	@ApiModelProperty("网点名称")
	private String branchName;
	
	@ApiModelProperty("用户ID")
	private Integer uesrId;
	
	@ApiModelProperty("集团ID")
	private Integer groupId;
	
}
