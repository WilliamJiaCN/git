package com.hivescm.tms.api.dto.es.handlingorder.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 装卸单查询详情条件
 * <p>Title: HandLingOrderDetailsReqDTO</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-05-23-16:11
 */

public class HandLingOrderDetailsReqDTO implements Serializable {
    private static final long serialVersionUID = -532111805308641821L;


    @Mapping @ApiModelProperty("装卸开始时间") private Long handlingStartTime;
    @Mapping @ApiModelProperty("装卸截止时间") private Long handlingEndTime;

    @Mapping @ApiModelProperty("制单开始时间") private Long createStartTime;
    @Mapping @ApiModelProperty("制单开始时间") private Long createEndTime;

    @Mapping @ApiModelProperty("审核开始时间") private Long checkStartTime;
    @Mapping @ApiModelProperty("审核开始时间") private Long checkEndTime;

    @Mapping @ApiModelProperty("装卸网点ID") private Integer branchId;
    @Mapping @ApiModelProperty("装卸网点名称") private String branchName;

    @Mapping @ApiModelProperty("装卸批次") private String handlingOrderCode;


}
