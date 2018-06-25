package com.hivescm.tms.api.dto.es.transport.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/4/11 10:45
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class RemoveTransportWaybillReqDTO implements Serializable{

    private static final long serialVersionUID = 1518393136891871997L;

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
     * 运输批次ID
     */
    @ApiModelProperty("运输批次ID")
    private Long transportId;

    /**
     * 运单批次ID
     */
    @ApiModelProperty("运输批次明细ID")
    private Long transportWaybillDetailId;
}
