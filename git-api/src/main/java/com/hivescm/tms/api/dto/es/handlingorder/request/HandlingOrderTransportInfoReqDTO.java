package com.hivescm.tms.api.dto.es.handlingorder.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/23 16:30
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class HandlingOrderTransportInfoReqDTO implements Serializable {

    private static final long serialVersionUID = 2890457168760890253L;

    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传",hidden = true)
    private Long companyId;


    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传",hidden = true)
    private Integer branchId;


    @ApiModelProperty("装卸批次")
    private String handlingOrderCode;

    @ApiModelProperty(value = "装卸队名称")
    private String handlingTeamName;

    @ApiModelProperty("装卸开始时间")
    private Long startTime;

    @ApiModelProperty("装卸截止时间")
    private Long endTime;
}
