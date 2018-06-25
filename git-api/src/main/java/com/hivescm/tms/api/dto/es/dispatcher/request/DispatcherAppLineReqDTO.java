package com.hivescm.tms.api.dto.es.dispatcher.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 派车单请求体
 * TmsDispatcherEsDTO
 *
 * @author lutingting
 * @date 2017年11月11日
 * @company 蜂网供应链管理
 */
@Data
@ToString
public class DispatcherAppLineReqDTO implements Serializable {

    private static final long serialVersionUID = -6933611350149830800L;

    /**
     * 公司id
     */

    @ApiModelProperty("公司id")
    private Long companyId;
    /**
     * 公司id
     */
    @Logger
    @ApiModelProperty("派车单id")
    private Long dispatcherId;
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
     * 派车单ID
     */

    @ApiModelProperty("运单Id")
    private List<Long> waybillIds;

   

  

}
