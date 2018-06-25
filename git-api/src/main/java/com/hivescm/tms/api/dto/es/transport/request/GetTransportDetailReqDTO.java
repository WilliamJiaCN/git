package com.hivescm.tms.api.dto.es.transport.request;

import java.io.Serializable;

import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class GetTransportDetailReqDTO implements Serializable {
    private static final long serialVersionUID = -532151097077810783L;


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

    /**当前网点
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
     * 运输批次Id
     */
    @Required
    @ApiModelProperty(value = "运输批次Id",required=true)
    private Long transportId;
    
    /**
     * 运单号
     */
    @Required
    @ApiModelProperty(value = "运单号",required=true)
    private String waybillCode;

}
