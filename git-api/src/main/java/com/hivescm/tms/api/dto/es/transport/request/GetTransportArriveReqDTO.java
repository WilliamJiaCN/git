package com.hivescm.tms.api.dto.es.transport.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 到货确认查看请求体
 * @author 张文龙
 * @since 2017/9/13
 */
@Data
@ToString
public class GetTransportArriveReqDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8748793261495040325L;


    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传",hidden = true)
    private Long companyId;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传",hidden = true)
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传",hidden = true)
    private String opUserName;

    /**
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传",hidden = true)
    private Integer branchId;
    
    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传",hidden = true)
    private String branchName;


    /**
     * 运输批次ID
     */
    @ApiModelProperty("运输批次ID")
    private Long transportId;
    
    /**
     * 到货批次ID
     */
    @Required
    @ApiModelProperty(value = "到货批次ID",required = true)
    @Logger
    private Long arriveId;

}
