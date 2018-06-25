package com.hivescm.tms.api.dto.es.base.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WarehouseAreaReqDTO extends PageQurey{

    @ApiModelProperty("库区ID")
    private Integer id;
    @ApiModelProperty("公司Id")
    private Long companyId;
    @ApiModelProperty("库区名称")
    private String warehouseAreaName;
    @ApiModelProperty("库区编码")
    private String warehouseAreaCode;
    @ApiModelProperty("所属网点")
    private Integer belongOrg;
    @ApiModelProperty("所属仓库")
    private Integer warehouseId;
    @ApiModelProperty("备注")
    private String remarks;
    @ApiModelProperty("制单人")
    private Integer createUser;
    @ApiModelProperty("制单时间")
    private Long createTime;
    @ApiModelProperty("制单人名称")
    private String createUserName;
    @ApiModelProperty("修改人")
    private Integer updateUser;
    @ApiModelProperty("修改时间")
    private Long updateTime;
    @ApiModelProperty("修改人名称")
    private String updateUserName;
    @ApiModelProperty("仓库名称")
    private String warehouseName;

}
