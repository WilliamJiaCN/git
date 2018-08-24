package com.hivescm.tms.api.dto.es.goodsuser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GoodsUserStateReqDTO {

    @ApiModelProperty("状态")
    private Integer memberStatus;

    @ApiModelProperty("会员ID")
    private Integer memberId;

    @ApiModelProperty("网点ID")
    private Integer orgId;
}