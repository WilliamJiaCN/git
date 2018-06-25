package com.hivescm.tms.api.dto.es.goodsuser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class GoodsUserMobileReqDTO implements Serializable {
    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("网点")
    private Integer orgId;
}
