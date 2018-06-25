package com.hivescm.tms.api.dto.es.exception.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 责任网点信息
 *
 * @author 李洪春
 * @since 2017/9/30 15:41
 */
@ToString
@Data
public class WaybillBranchInfoDTO implements Serializable {
    private static final long serialVersionUID = -1298030555972903131L;

    @ApiModelProperty("网点ID")
    private Integer branchId;

    @ApiModelProperty("网点名称")
    private String branchName;
}
