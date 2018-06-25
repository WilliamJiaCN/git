package com.hivescm.tms.api.dto.db.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 
 */
@Data
@ToString
public class BaseTmsWarehouseAreaDTO implements Serializable {

    @ApiModelProperty("ID")
    private Integer id;

    @ApiModelProperty("库区名称")
    private String warehouseAreaName;

    @ApiModelProperty("库区编号")
    private String warehouseAreaCode;

    @ApiModelProperty("所属网点")
    private Integer belongOrg;

    @ApiModelProperty("主键")
    private Integer warehouseId;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("公司Id")
    private Long companyId;

    @ApiModelProperty("制单人")
    private Integer createUser;

    @ApiModelProperty("制单时间")
    private Long createTime;

    @ApiModelProperty("仓库名称")
    private String warehouseName;

    @ApiModelProperty("修改人")
    private Integer updateUser;

    @ApiModelProperty("修改时间")
    private Long updateTime;

    @ApiModelProperty("修改人名称")
    private String updateUserName;

    @ApiModelProperty("制单人名称")
    private String createUserName;

}