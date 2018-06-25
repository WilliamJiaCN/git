package com.hivescm.tms.api.dto.es.storage.req;

import java.util.List;

import com.hivescm.tms.api.dto.es.storage.StorageInfoEsDTO;
import com.hivescm.tms.api.dto.es.storage.common.StorageDetailDTO;

import io.swagger.annotations.ApiModelProperty;

import com.hivescm.tms.api.dto.es.storage.StorageFeeEsDTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StorageAddReqDTO {

	@ApiModelProperty("入库主表信息")
	private StorageInfoEsDTO storageInfoEsDTO;
	
	@ApiModelProperty("入库费用信息")
	private List<StorageFeeEsDTO> storagelFeeEsDTOs;
	
	@ApiModelProperty("入库明细信息")
	private List<StorageDetailDTO> storageDetailDTOs;
	
	@ApiModelProperty("公司ID")
	private Long companyId;
	
	@ApiModelProperty("公司名称")
	private String companyName;
	
	@ApiModelProperty("集团ID")
	private Integer groupId;
	
	@ApiModelProperty("用户ID")
	private Integer uesrId;
	
	@ApiModelProperty("用户名")
	private String userName;
	
	@ApiModelProperty("网点ID")
	private Integer branchId;
	
	@ApiModelProperty("网点名称")
	private String branchName;
	
}
