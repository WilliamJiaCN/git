package com.hivescm.tms.api.dto.es.dispatcher.request;

import java.io.Serializable;

import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 派车单查询待处理运单、库存运单查询条件
 *
 * @author 李洪春
 * @since 2017/8/17 14:38
 */
@Data
@ToString
public class DispatcherWaybillQueryReqEsDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7213772997156206777L;

    /**
     * 网点ID
     * invoiceOrgId
     */
    @ApiModelProperty("网点ID")
    private Integer branchId;

    @ApiModelProperty("公司ID")
    private Long companyId;
    
    /**
     * 运单编码
     */
    @Logger
    @ApiModelProperty("运单编码")
    private String waybillCode;
    
    @ApiModelProperty("过滤运单编号")
    private String waybillCodes;

    /**
     * 仓库ID
     */
    @ApiModelProperty("仓库ID")
    private Integer warehouseId;

    /**
     * 客户单号
     * customerOrderCode
     */
    @ApiModelProperty("客户单号")
    private String customerOrderCode;

    
    
    /**
     * 提货截止时间
     */
    @ApiModelProperty("提货开始时间")
    private Long pickBeginTime;
    

    /**
     * 提货截止时间
     */
    @ApiModelProperty("提货截止时间")
    private Long pickEndTime;
    
    
    @ApiModelProperty("送货开始时间")
    private Long startSendTime;

    @ApiModelProperty("送货截止时间")
    private Long endSendTime;
    
    

    /**
     * 制单日期 - 开始范围
     */
    @ApiModelProperty("制单日期 - 开始范围")
    private Long startCreateTime;

    /**
     * 制单日期 - 结束范围
     */
    @ApiModelProperty("制单日期 - 结束范围")
    private Long endCreateTime;
    
    /**
     * 接单截止时间
     */
    @ApiModelProperty("订单类型")
    private Integer orderType;
    
    
    @ApiModelProperty("波次号")
    private String waveNumber;
    
   

    /**
     * 分页大小
     */
    @ApiModelProperty("分页大小")
    private Integer pageSize = 10;


    /**
     * 当前页数
     */
    @ApiModelProperty("当前页数")
    private Integer currentPage = 1;




}
