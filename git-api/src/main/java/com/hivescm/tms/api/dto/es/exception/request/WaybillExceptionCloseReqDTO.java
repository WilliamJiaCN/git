package com.hivescm.tms.api.dto.es.exception.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 异常办结请求DTO
 */
@Data
@ToString
public class WaybillExceptionCloseReqDTO implements Serializable{

    /**
     * 责任认定信息
     */
    @ApiModelProperty(value = "责任认定信息")
    private List<WaybillExceptionCloseDTO> waybillExceptionCloseDTOList;

    /**
     * 附件信息
     */
    @ApiModelProperty(value = "附件信息")
    private List<WaybillExceptionAttachDTO> waybillExceptionAttachDTOList;

    /**
     * 主键
     */
    @Logger
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 公司ID
     */
    @ApiModelProperty(value = "公司ID", hidden = true)
    private Long companyId;

    /**
     * 发生原因
     */
    @ApiModelProperty(value = "异常发生原因 1.内部 2.承运商 3.客户 4.不可抗", required = true)
    private Integer reason;

    /**
     * 发生原因
     */
    @ApiModelProperty(value = "异常发生原因 1.内部 2.承运商 3.客户 4.不可抗", required = true)
    private String reasonName;

    /**
     * 处理方式
     */
    @ApiModelProperty(value = "处理方式 1.理赔 2.罚款 3.赔偿货损 4.理赔/罚款", required = true)
    private Integer dealType;

    /**
     * 处理方式
     */
    @ApiModelProperty(value = "处理方式 1.理赔 2.罚款 3.赔偿货损 4.理赔/罚款", required = true)
    private String dealTypeName;

    @Mapping
    @ApiModelProperty("处理人ID")
    private Integer dealUserId;;
    
    /**
     * 处理人
     */
    @ApiModelProperty(value = "处理人", required = true)
    private String dealUserName;
    

    @Mapping
    @ApiModelProperty("处理方Id")
    private Integer dealBusinessNetworkId;

    /**
     *
     */
    @ApiModelProperty(value = "处理方", required = true)
    private String dealBusinessNetworkName;


    @Mapping
    @ApiModelProperty("理赔对象是否VIP")
    private Boolean claimTargetIsVip;

    @Mapping
    @ApiModelProperty("理赔对象id")
    private Integer claimTargetId;
    /**
     * 理赔对象
     */
    @ApiModelProperty(value = "理赔对象")
    private String claimTarget;

    /**
     * 理赔金额
     */
    @ApiModelProperty(value = "理赔金额")
    private BigDecimal claimAmount;

    /**
     * 判定描述
     */
    @ApiModelProperty("判定描述")
    private String handResultJudgeAdvise;

    /**
     * 付款网点ID
     */
    @ApiModelProperty("付款网点ID")
    private Integer payBranchId;

    /**
     * 付款网点名称
     */
    @ApiModelProperty("付款网点名称")
    private String payBranchName;

    /**
     * 操作人ID
     */
    @ApiModelProperty(value = "操作人ID", hidden = true)
    private Integer oprId;

    /**
     * 操作人名称
     */
    @ApiModelProperty(value = "操作人名称", hidden = true)
    private String oprName;
}
