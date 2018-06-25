package com.hivescm.tms.api.dto.es.goodsuser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GoodsUserListReqDTO {

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("账户状态")
    private Integer memberStatus;

    @ApiModelProperty("物流公司ID")
    private Long groupId;

    @ApiModelProperty("会员来源")
    private Long memsource;

}