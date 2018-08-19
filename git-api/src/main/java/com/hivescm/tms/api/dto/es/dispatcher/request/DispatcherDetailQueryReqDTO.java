package com.hivescm.tms.api.dto.es.dispatcher.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 派车单明细查询类
 *
 * @author zhenming.du
 * @since 2017/9/5
 */
@Data
@ToString
public class DispatcherDetailQueryReqDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7213772997156206777L;

    /**
     * 派车网点名称
     */
    @ApiModelProperty("派车网点ID")
    private List<Long> branchId;

    /**
     * 派车批次
     */
    @Logger
    @ApiModelProperty("派车批次")
    private String batchCode;
    @Logger
    @ApiModelProperty("派车单ID")
    private Long dispatcherId;
    
    @ApiModelProperty("公司ID")
    private Long companyId;
    /**
     * 运单编码
     */
    @Logger
    @ApiModelProperty("运单编码")
    private String waybillCode;
    @Logger
    @ApiModelProperty("订单号")
    private String orderCode;
    @Logger
    @ApiModelProperty("发货人")
    private String invoiceUser;
    
    @Logger
    @ApiModelProperty("收货人")
    private String receiptUser;

    @Logger
    @ApiModelProperty("收货人手机")
    private String receiptUserPhone;
    @Logger
    @ApiModelProperty("关键字查询")
    private String keywords;
    
    @Logger
    @ApiModelProperty("执行任务")
    private Integer taskId;
    /**
     * 派车单制单开始日期
     */
    @ApiModelProperty("制单开始日期")
    private Long dispatcherBeginCreateTime;

    /**
     * 派车单制单结束日期
     */
    @ApiModelProperty("制单结束日期")
    private Long dispatcherEndCreateTime;

    /**
     * 发车开始日期
     */
    @ApiModelProperty("发车开始日期")
    private Long dispatcherBeginTime;

    /**
     * 发车结束日期
     */
    @ApiModelProperty("发车结束日期")
    private Long dispatcherEndTime;

    /**
     * 配送开始日期
     */
    @ApiModelProperty("配送开始日期")
    private Long deliveryBeginDate;
    /**
     * 运单录单结束日期
     */
    @ApiModelProperty("配送结束日期")
    private Long deliveryEndDate;

    
    /**
     * 配送开始日期
     */
    @ApiModelProperty("装货开始日期")
    private Long shippingBeginDate;
    /**
     * 运单录单结束日期
     */
    @ApiModelProperty("装货结束日期")
    private Long shippingEndDate;

    /**
     * 配送开始日期
     */
    @ApiModelProperty("签收开始日期")
    private Long signBeginDate;
    /**
     * 运单录单结束日期
     */
    @ApiModelProperty("签收结束日期")
    private Long signEndDate;
    

    @ApiModelProperty("分页大小")
    private Integer pageSize = 10;

    @ApiModelProperty("当前页数")
    private Integer currentPage = 1;

    /**
     * 初始化分页信息
     */
    public void initPage() {
        if (null == pageSize) {
            pageSize = 10;
        }
        if (null == currentPage) {
            currentPage = 1;
        }
    }

}
