package com.hivescm.tms.api.dto.es.delivery;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.global.GlobalServiceProviderLineConfigEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
//快递服务设置（仓库快递公司关系）
@Data
@ToString
public class DeliveryGlobalServiceProviderConfigEsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Required
	@Mapping
	@ApiModelProperty("仓储服务商ID")
	private Long providerId;
	@Required
	@Mapping
	@ApiModelProperty("仓库ID")
	private Long warehouseId;
	@Mapping
	@ApiModelProperty("站点ID")
	private Long siteId;
	@Mapping({"isUnify","GlobalServiceProviderConfigDO.isUnfiy"})
	@ApiModelProperty("是否使用统一物流组织")
	private Boolean iunify;
	@Mapping
	@ApiModelProperty("统一物流组织类型")
	private Integer unifyType;
	@Mapping
	@ApiModelProperty("统一物流组织类型枚举名")
	private String unifyTypeEn;
	@Required
	@Mapping
	@ApiModelProperty("快递公司Id")
	private Long unifyProviderId;
	@Mapping
	@ApiModelProperty("统一物流组织配送部门ID")
	private Long unifyProviderDepartId;
	@Mapping({"isLine","GlobalServiceProviderConfigDO.isLine"})
	@ApiModelProperty("是否按线路指定物流组织")
	private Boolean iline;
	@Mapping
	@ApiModelProperty("创建时间")
	private Long createTime;
	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;
	@Mapping
	@ApiModelProperty("创建人")
	private Integer createUser;
	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;
	@Mapping
	@ApiModelProperty("创建人名称")
	private String createUserName;
	@Mapping
	@ApiModelProperty("修改人名称")
	private String updateUserName;
	/**********************************冗余数据*****************************************/
	@Mapping
	@ApiModelProperty("仓储服务商ID")
	private String providerName;
	@Mapping
	@ApiModelProperty("仓库名称")
	private String warehouseName;
	@Mapping
	@ApiModelProperty("站点名称")
	private String siteName;
	@Mapping
	@ApiModelProperty("统一物流组织配送部门名称")
	private String unifyProviderDepartName;

	@Mapping
	@ApiModelProperty("快递公司名称")
	private String unifyProviderName;


}
