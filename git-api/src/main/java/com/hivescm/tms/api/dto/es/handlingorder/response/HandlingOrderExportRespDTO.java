package com.hivescm.tms.api.dto.es.handlingorder.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author sql
 * @Date 17:232018\5\29 0029
 */
@Data
@ToString
public class HandlingOrderExportRespDTO implements Serializable {
    private static final long serialVersionUID = 9020721031890712472L;

    @Mapping
    @ApiModelProperty("装卸批次")
    private String handlingOrderCode;

    @Mapping
    @ApiModelProperty("类型")
    private String handlingTypeName;

    @Mapping
    @ApiModelProperty("装卸网点名称")
    private String branchName;

    @Mapping
    @ApiModelProperty("装货时间")
    private Long shippingTime;

    @Mapping
    @ApiModelProperty("状态")
    private String statusName;

    @Mapping
    @ApiModelProperty("装卸队")
    private String handlingTeam;

    @Mapping
    @ApiModelProperty("负责人名称")
    private String handlingTeamLeaderName;

    @Mapping
    @ApiModelProperty("负责人电话")
    private String teamLeaderPhoneNo;

    @Mapping
    @ApiModelProperty("来源车辆")
    private String vehicheNo;

    @Mapping
    @ApiModelProperty("来源批次")
    private String batchCode;

    @Mapping
    @ApiModelProperty("装车时间")
    private Long shippingTime1;

    @Mapping
    @ApiModelProperty("装卸成本")
    private BigDecimal cost;



    @Mapping
    @ApiModelProperty("总票数")
    private Integer totalWaybillNum;

    @Mapping
    @ApiModelProperty("装卸数量")
    private Integer totalGoodsNum;

    @Mapping
    @ApiModelProperty("装卸重量")
    private BigDecimal totalWeight;

    @Mapping
    @ApiModelProperty("装卸体积")
    private BigDecimal totalVolume;

    @Mapping
    @ApiModelProperty("备注")
    private String remark;

    @Mapping
    @ApiModelProperty("制单人名称")
    private String createUserName;

    @Mapping
    @ApiModelProperty("制单时间")
    private Long createTime;

    @Mapping
    @ApiModelProperty("修改人")
    private String updateUserName;

    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    @Mapping
    @ApiModelProperty("审核人名称")
    private String checkUserName;

    @Mapping
    @ApiModelProperty("审核时间")
    private Long checkTime;

    @Mapping
    @ApiModelProperty("取消审核人名称")
    private String cancelCheckUserName;

    @Mapping
    @ApiModelProperty("取消审核时间")
    private Long cancelCheckTime;
}
