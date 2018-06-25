package com.hivescm.tms.api.dto.es.handlingorder.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/23 16:51
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class PickHandlingOrderRespDTO implements Serializable {


    private static final long serialVersionUID = 699661762341836585L;

    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    @Mapping
    @ApiModelProperty("装卸批次")
    private String handlingOrderCode;

    @Mapping
    @ApiModelProperty("装货时间")
    private Long shippingTime;

    @Mapping
    @ApiModelProperty("装卸网点名称")
    private String branchName;


    @Mapping
    @ApiModelProperty("装卸队")
    private String handlingTeam;

    @Mapping
    @ApiModelProperty("总票数")
    private Integer totalWaybillNum;

    @Mapping
    @ApiModelProperty("总票数")
    private Integer totalGoodsNum;

    @Mapping
    @ApiModelProperty("总重量")
    private Integer totalWeight;

    @Mapping
    @ApiModelProperty("总体积")
    private Integer totalVolume;

    @Mapping
    @ApiModelProperty("负责人名称")
    private String handlingTeamLeaderName;

    @Mapping
    @ApiModelProperty("负责人电话")
    private String teamLeaderPhoneNo;


    @Mapping
    @ApiModelProperty("装卸费")
    private BigDecimal cost;

}
