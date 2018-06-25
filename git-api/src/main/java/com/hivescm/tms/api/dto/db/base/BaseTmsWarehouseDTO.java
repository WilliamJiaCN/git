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
public class BaseTmsWarehouseDTO implements Serializable {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("公司Id")
    private Long companyId;

    @ApiModelProperty("仓库名称")
    private String warehouseName;

    @ApiModelProperty("所属网点")
    private Integer belongOrg;

    @ApiModelProperty("联系人")
    private String contact;

    @ApiModelProperty("手机号码")
    private String phoneNo;

    @ApiModelProperty("电话号码")
    private String telNo;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("仓库面积(㎡)")
    private Long warehouseArea;

    @ApiModelProperty("国家")
    private String country;

    @ApiModelProperty("省")
    private String province;

    @ApiModelProperty("市")
    private String city;

    @ApiModelProperty("区")
    private String county;

    @ApiModelProperty("街道")
    private String street;

    @ApiModelProperty("详细地址")
    private String address;

    @ApiModelProperty("制单人")
    private Integer createUser;

    @ApiModelProperty("制单时间")
    private Long createTime;

    @ApiModelProperty("所属网点的名称")
    private String belongOrgName;

    @ApiModelProperty("修改人")
    private Integer updateUser;

    @ApiModelProperty("修改时间")
    private Long updateTime;

    @ApiModelProperty("制单人名称")
    private String createUserName;

    @ApiModelProperty("修改人名称")
    private String updateUserName;

    @ApiModelProperty("仓库类型  (1.发货仓 2.到货仓)")
    private Integer type;

}