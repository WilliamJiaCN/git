package com.hivescm.tms.api.dto.es.exception.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 异常办结请求对象
 *
 * @author 李洪春
 * @since 2017/10/9 上午9:55
 */
@Data
@ToString
public class CloseWaybillExceptionReqDTO implements Serializable {

    private static final long serialVersionUID = 9204702261295315577L;

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
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传", example = "23")
    private Integer branchId;

    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传", example = "网点23")
    private String branchName;


    /**
     * 异常单据ID
     */
    @ApiModelProperty("异常单据ID")
    private Long exceptionId;

    /**
     * 运单ID
     */
    @ApiModelProperty("运单ID")
    private Long WaybillId;

}
