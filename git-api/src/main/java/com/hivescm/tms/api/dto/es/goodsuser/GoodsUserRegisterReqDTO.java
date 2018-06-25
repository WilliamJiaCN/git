package com.hivescm.tms.api.dto.es.goodsuser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class GoodsUserRegisterReqDTO {

    @ApiModelProperty("会员手机号")
    private String memberMobile;

    @ApiModelProperty("会员姓名")
    private String memberName;
    
    @ApiModelProperty("会员性别")
    private Integer sex;
    
    @ApiModelProperty("会员身份证号")
    private String memberCard;
    
    @ApiModelProperty("物流公司Id")
    private Long groupId;
    
    @ApiModelProperty("3PL公司名称")
    private String companyName;
    
    @ApiModelProperty("业务单元Id 网点信息")
    private Integer orgId;
    
    @ApiModelProperty("会员来源")
    private Integer memsource;

    @ApiModelProperty("用户状态")
    private Integer memberStatus;

    @ApiModelProperty("管理员类型")
    private Integer managerLevel;

    @ApiModelProperty("身份类型")
    private Integer identityType;

    @ApiModelProperty("创建用户")
    private String createUser;

    @ApiModelProperty("注册来源")
    private Integer soure;
}