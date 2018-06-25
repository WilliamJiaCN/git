package com.hivescm.tms.api.dto.es.exception.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 完结罚款单请求对象
 *
 * @author 李洪春
 * @since 2017/10/11 下午3:06
 */
@Data
@ToString
public class CloseForfeitBillReqDTO implements Serializable {
    private static final long serialVersionUID = 6033011709893314168L;
    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传", example = "2")
    private Long companyId;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传", example = "2")
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传", example = "李洪春")
    private String opUserName;

    /**
     * 异常单据ID
     */
    @ApiModelProperty("罚款单ID")
    private Long forfeitBillId;
}
