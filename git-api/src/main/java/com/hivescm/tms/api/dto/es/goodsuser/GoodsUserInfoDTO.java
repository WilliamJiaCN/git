package com.hivescm.tms.api.dto.es.goodsuser;

import io.swagger.annotations.ApiModelProperty;

public class GoodsUserInfoDTO {
    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private Integer sex;

    @ApiModelProperty("身份证号")
    private String idCard;

    @ApiModelProperty("3PL公司ID")
    private Long groupId;

    @ApiModelProperty("头像照片")
    private String imageUrl;

    @ApiModelProperty("会员来源")
    private Integer memsource;
}
