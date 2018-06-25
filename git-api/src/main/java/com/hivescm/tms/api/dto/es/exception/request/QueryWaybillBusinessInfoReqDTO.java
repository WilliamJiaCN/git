package com.hivescm.tms.api.dto.es.exception.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 运单业务单据查询请求对象
 *
 * @author 李洪春
 * @since 2017/10/11 16:46
 */
@Data
@ToString
public class QueryWaybillBusinessInfoReqDTO implements Serializable {
    private static final long serialVersionUID = 3567286425733351680L;

    @ApiModelProperty("运单ID")
    private Long waybillId;

    @ApiModelProperty("业务类型")
    private Integer type;
}
