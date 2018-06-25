package com.hivescm.tms.api.dto.es.exception.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 罚款单明细查询请求对象
 *
 * @author 李洪春
 * @since 2017/10/10 15:39
 */
@Data
@ToString
public class QueryForfeitBillDetailReqDTO implements Serializable {

    private static final long serialVersionUID = -2186397067599066195L;


    @ApiModelProperty("运单编码")
    private String waybillCode;

    @ApiModelProperty("罚款单ID")
    private Long forfeitBillId;

}
