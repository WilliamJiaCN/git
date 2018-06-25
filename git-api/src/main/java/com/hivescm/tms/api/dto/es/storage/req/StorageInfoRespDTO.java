package com.hivescm.tms.api.dto.es.storage.req;

import java.util.List;

import com.hivescm.tms.api.dto.es.storage.StorageInfoEsDTO;

import io.swagger.annotations.ApiModelProperty;

import com.hivescm.tms.api.dto.es.outbill.resp.OutBillWaybillDTO;
import com.hivescm.tms.api.dto.es.storage.StorageFeeEsDTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StorageInfoRespDTO {

	@ApiModelProperty("入库主表信息")
	private StorageInfoEsDTO storageInfoEsDTO;
	
	@ApiModelProperty("入库费用信息")
	private List<StorageFeeEsDTO> storagelFeeEsDTOs;
	
	@ApiModelProperty("入库明细信息")
	private List<OutBillWaybillDTO> outbillDetails;
	
	@ApiModelProperty("公司ID")
	private Integer companyId;
	
	@ApiModelProperty("用户ID")
	private Integer uesrId;
	
	@ApiModelProperty("用户名")
	private String userName;
	
	@ApiModelProperty("网点ID")
	private Integer branchId;
	
}
