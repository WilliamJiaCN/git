package com.hivescm.tms.api.dto.es.finance.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashTransferEsDTO;
import com.hivescm.tms.api.enums.finance.FinanceTransferStatusEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FinanceManageCashTransferInsertReqDTO implements Serializable{

	private static final long serialVersionUID = -8094994079790724337L;
	@Mapping
    @ApiModelProperty(value="公司ID",hidden=true)
    private Long companyId;
	
	@Mapping
    @ApiModelProperty(value="集团ID",hidden=true)
    private Integer groupId;
	
	@ApiModelProperty("转账信息 （必填）")
	private FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO;
	
	@ApiModelProperty("现金流水（必填）")
	private List<FinanceManageCashSerialEsDTO> cashDetails;
	

	@Mapping
    @ApiModelProperty(value="用户ID",hidden=true)
    private Integer userId;
	
	@Mapping
	@ApiModelProperty(value="用户姓名",hidden=true)
	private String userName;
	
	@Mapping
	@ApiModelProperty(value="网点ID",hidden=true)
	private Integer orgId;
	
	@Mapping
	@ApiModelProperty(value="网点姓名",hidden=true)
	private String orgName;
	
	public void initOnCreate() {
		financeManageCashTransferEsDTO.setCompanyId(companyId);
		financeManageCashTransferEsDTO.setTransferNetworkId(orgId);
		financeManageCashTransferEsDTO.setTransferNetworkName(orgName);
		financeManageCashTransferEsDTO.setTransferStatus(FinanceTransferStatusEnum.UNTRANSFER.getType());
		financeManageCashTransferEsDTO.setTransferStatusName(FinanceTransferStatusEnum.UNTRANSFER.getName());
		financeManageCashTransferEsDTO.setCreatebillTime(System.currentTimeMillis());
		financeManageCashTransferEsDTO.setCreatebillUserId(userId);
		financeManageCashTransferEsDTO.setCreatebillUserName(userName);
		financeManageCashTransferEsDTO.setCreateUserId(userId);
		financeManageCashTransferEsDTO.setCreateUserName(userName);
		financeManageCashTransferEsDTO.setCreateTime(System.currentTimeMillis());
		financeManageCashTransferEsDTO.setTransferNetworkId(orgId);
		financeManageCashTransferEsDTO.setTransferNetworkName(orgName);
	}
}
