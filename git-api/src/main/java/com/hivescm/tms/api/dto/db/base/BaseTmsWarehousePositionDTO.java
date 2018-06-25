package com.hivescm.tms.api.dto.db.base;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 
 */
@Data
@ToString
public class BaseTmsWarehousePositionDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Mapping
    @ApiModelProperty("库位编号")
    private Integer id;

    /**
     * 库位编号
     */
    @Mapping
    @ApiModelProperty("库位编号")
    private String warehousePositionCode;

    /**
     * 库位名称
     */
    @Mapping
    @ApiModelProperty("库位名称")
    private String warehousePositionName;
    
    /**
     * 备注
     */
    @Mapping
    @ApiModelProperty("备注")
    private String remarks;

    /**
     * 所属组织
     */
    @Mapping
    @ApiModelProperty("所属组织")
    private Integer belongOrg;

    /**
     * 库区id
     */
    @Mapping
    @ApiModelProperty("库区id")
    private Integer warehouseAreaId;

    /**
     * 仓库id
     */
    @Mapping
    @ApiModelProperty("仓库id")
    private Integer warehouseId;

    /**
     * 创建人
     */
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;

    /**
     * 创建时间
     */
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    /**
     * 修改人
     */
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;


    /**
     * 公司id
     */
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 库区名称
     */
    @Mapping
    @ApiModelProperty("库区名称")
    private String warehouseAreaName;

    /**
     * 修改人名称
     */
    @Mapping
    @ApiModelProperty("修改人名称")
    private String updateUserName;

    /**
     * 新增人名称
     */
    @Mapping
    @ApiModelProperty("制单人名称")
    private String createUserName;

}