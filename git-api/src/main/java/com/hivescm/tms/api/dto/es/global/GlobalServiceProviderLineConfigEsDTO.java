package com.hivescm.tms.api.dto.es.global;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class GlobalServiceProviderLineConfigEsDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 根据id分组
	 * @return
	 */
	public Long groupByLineId() {
		return this.lineId;
	}
	@Mapping
	@ApiModelProperty("主键")
    private Long id;
	@Mapping
	@ApiModelProperty("仓储服务商ID")
    private Long providerId;
	@Mapping
	@ApiModelProperty("仓库ID")
    private Long warehouseId;
	@Mapping
	@ApiModelProperty("线路ID")
    private Long lineId;
	@Mapping
	@ApiModelProperty("物流组织类型")
    private Integer unifyType;
	@Mapping
	@ApiModelProperty("物流组织服务商ID")
    private Long unifyProviderId;
	@Mapping
	@ApiModelProperty("物流组织配送部门ID")
    private Long unifyProviderDepartId;
	@Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;
	
	/*********************************冗余数据********************************/
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
	@ApiModelProperty("物流组织配送部门名称")
    private String unifyProviderDepartName;
	@Mapping
	@ApiModelProperty("物流组织服务商名称")
    private String unifyProviderName;
	@Mapping
	@ApiModelProperty("线路名称")
    private String lineName;
	@Mapping
	@ApiModelProperty("物流组织类型枚举名")
    private String unifyTypeEn;
	@ApiModelProperty("未设置")
	private Boolean notSet;
	
	@Mapping
	@ApiModelProperty("修改人姓名")
    private String updateUserName;
	
	@Mapping
    @ApiModelProperty("创建人姓名")
    private String createUserName;
}
