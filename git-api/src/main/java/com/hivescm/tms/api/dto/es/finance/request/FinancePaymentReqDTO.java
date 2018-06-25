package com.hivescm.tms.api.dto.es.finance.request;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 付款单查询参数
 * @author 杨彭伟
 * @date 下午2:00:38
 */
@Data
@ToString
public class FinancePaymentReqDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Mapping
    @ApiModelProperty("开始时间")
    private Long startTime;

    @Mapping
    @ApiModelProperty("结束时间")
    private Long endTime;

    @Mapping
    @ApiModelProperty("时间类型（1.发放 2.录单 3.签收 4.收银）")
    private Integer timeType;

    @Mapping
    @ApiModelProperty(value = "发放状态(0 = 全部；1 = 未发放 ;2 = 已发放)", notes="全部界面列表(ARRIVED, REFUSE_ARRIVED),未发放(REFUSE_ARRIVED)收传此处运单状态")
    private Integer grantStatus;

    @Mapping
    @ApiModelProperty("收款方名称")
    private String payeeObjectName;
    
    @Mapping
    @ApiModelProperty("收款方id")
    private Integer payeeObjectId;
    
    @Mapping
    @ApiModelProperty("付款组织id")
    private Integer payOriginazationId;

    @Mapping
    @ApiModelProperty("来源单号(运单号)") @Logger
    private String sourceNumber;
    @Mapping
    @ApiModelProperty(value = "公司id", required = true)
    private Long companyId;
    @Mapping
    @ApiModelProperty(value = "当前网点id", required = true)
    private Long curDotId;
    @Mapping
    @ApiModelProperty(value = "付款单号")
    private String payCode;

    @ApiModelProperty("分页 - 每页显示数")
    private Integer pageSize;

    @ApiModelProperty("分页 - 当前页数")
    private Integer currentPage;

}
