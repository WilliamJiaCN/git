package com.hivescm.tms.api.dto.es.handlingorder.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/22 14:57
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class HandlingOrderQueryListReqDTO implements Serializable {

    private static final long serialVersionUID = -2170692063815183307L;

    @ApiModelProperty("装卸网点id")
    private List<Long> branchId;

    @ApiModelProperty("装卸队名称")
    private String handlingTeam;

    @ApiModelProperty("装卸批次")
    private String handlingOrderCode;

    @ApiModelProperty("装卸类型")
    private Integer handlingType;

    @ApiModelProperty("负责人")
    private Integer handlingTeamLeader;

    @ApiModelProperty("联系电话")
    private String teamLeaderPhoneNo;

    @ApiModelProperty("来源批次")
    private String batchCode;

    @ApiModelProperty("来源车辆")
    private String vehicheNo;

    @ApiModelProperty("装卸开始时间")
    private Long startTime;

    @ApiModelProperty("装卸截止时间")
    private Long endTime;

    @ApiModelProperty("日期类型1:制单日期2:审核日期3:装卸日期")
    private Integer type;


    @ApiModelProperty(value = "分页大小",required = true)
    private Integer pageSize = 10;

    @ApiModelProperty(value = "当前页数",required = true)
    private Integer currentPage = 1;

}
