package com.hivescm.tms.api.dto.es.goodsuser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GoodsUserDetailsReqDTO {

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("会员ID")
    private Integer memberId;

}