package com.hivescm.tms.api.dto.es.exception.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 请求DTO 适用于异常单提交
 *
 * @author wenxiong.jia
 * @since 2018/5/24
 */
@Data
@ToString
public class WaybillExceptionCommonReqDTO implements Serializable {

    private static final long serialVersionUID = 7104840532546912332L;

    /**
     * 主键列表
     */
    @ApiModelProperty(value = "主键列表", required = true)
    private List<Long> idList;

    /**
     * 操作员ID
     */
    @ApiModelProperty(value = "操作员ID", hidden = true)
    private Integer oprId;

    /**
     * 操作员姓名
     */
    @ApiModelProperty(value = "操作员姓名", hidden = true)
    private String oprName;
}
