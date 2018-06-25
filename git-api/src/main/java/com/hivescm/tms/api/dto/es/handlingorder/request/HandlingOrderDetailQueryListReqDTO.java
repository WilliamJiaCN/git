package com.hivescm.tms.api.dto.es.handlingorder.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Author sql
 * @Date 19:392018\5\22 0022
 */
@Data
@ToString
public class HandlingOrderDetailQueryListReqDTO implements Serializable {
    private static final long serialVersionUID = 5205574927707434914L;


    @Mapping
    @ApiModelProperty("装卸网点id")
    private List<Long> branchId;
    @Mapping
    @ApiModelProperty("装卸队名称")
    private String handlingTeam;
    @Mapping
    @ApiModelProperty("装卸批次")
    private String handlingOrderCode;
    @Mapping
    @ApiModelProperty("装卸类型")
    private Integer handlingType;
    @Mapping
    @ApiModelProperty("负责人")
    private Integer handlingTeamLeader;
    @Mapping
    @ApiModelProperty("联系电话")
    private String teamLeaderPhoneNo;
    @Mapping
    @ApiModelProperty("来源批次")
    private String batchCode;
    @Mapping
    @ApiModelProperty("运单号")
    private String waybillCode;
    @Mapping
    @ApiModelProperty("装卸开始时间")
    private Long startTime;
    @Mapping
    @ApiModelProperty("装卸截止时间")
    private Long endTime;
    @Mapping
    @ApiModelProperty("日期类型1:制单日期2:审核日期3:装卸日期")
    private Integer type;


    @ApiModelProperty(value = "分页大小",required = true)
    private Integer pageSize = 10;

    @ApiModelProperty(value = "当前页数",required = true)
    private Integer currentPage = 1;


}
