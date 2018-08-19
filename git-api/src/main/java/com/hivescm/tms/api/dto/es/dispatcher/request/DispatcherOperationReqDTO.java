package com.hivescm.tms.api.dto.es.dispatcher.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 派车单发车请求数据对象
 *
 * @author 李洪春
 * @since 2017/8/14 20:51
 */
@Data
@ToString
public class DispatcherOperationReqDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -875225972115997910L;

    /**
     * 公司Id
     */
    @Required
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
    private Long companyId;

    /**
     * 操作用户ID
     */
    @Required
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @Required
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;

    /**
     * 派车单Id
     */
    @Logger
    @Required
    @ApiModelProperty("派车单Id")
    private Long dispatcherId;
    
    /**
     * 派车单Id
     */
    @Logger
    @ApiModelProperty("派车单明细Id")
    private Long dispatcherDetailId;

    /**
     * 网点ID
     */
    @ApiModelProperty("网点ID")
    private Integer branchId;

    /**
     * 当前网点名称
     */
    @ApiModelProperty("网点名称")
    private String branchName;

    /**
     * 来源类型
     */
    @ApiModelProperty("来源类型 --1为TMS 2为WMS")
    private Integer sourceType;
    @Logger
    @ApiModelProperty("仓库交接单号")
    private String warehouseReceiptNo;

    /**
     * 派车单明细ID列表
     */
    @ApiModelProperty(value = "派车单明细ID列表", notes = "派车单提货入库时需传此参数")
    private List<Long> dispatcherDetailIdList;
}
