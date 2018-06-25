package com.hivescm.tms.api.dto.es.transport.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 删除运输批次请求对象
 * @author 张文龙
 * @since 2017/9/7 
 */
@Data
@ToString
public class DelTransportReqDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8748793261495040325L;


    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
    private Long companyId;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;

    /**
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;
    
    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
    private String branchName;


    /**
     * 运输批次ID
     */
    @ApiModelProperty("运输批次ID")
    private Long transportId;
}
