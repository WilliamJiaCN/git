package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class WaybillListReqEsDTO implements Serializable{
    private static final long serialVersionUID = -6266283198484194252L;


    /**
     * 公司id
     */
    private @ApiModelProperty("公司id") Long companyId;
    /**
     * 运单状态
     */
    @ApiModelProperty("运单状态:1,待处理（已开单还未派送）," + "2,已揽货," + "3,已配送," + "4,已签收," + "5,已取消")
    private Integer status;

    /**
     * 录单日期 - 开始范围
     */
    private @ApiModelProperty("录单日期 - 开始范围") Long startCreateTime;
    /**
     * 录单日期 - 结束范围
     */
    private @ApiModelProperty("录单日期 - 结束范围") Long endCreateTime;


    /**
     * 业务日期	 - 开始范围
     */
    private @ApiModelProperty("业务日期 - 开始范围") Long startBussTime;
    /**
     * 业务日期	 - 结束范围
     */
    private @ApiModelProperty("业务日期 - 结束范围") Long endBussTime;


    /**
     * 发货网点ID
     */
    private @ApiModelProperty("发货网点ID") List<Integer> invoiceOrgIds;
    /**
     * 到达网点ID
     */
    private @ApiModelProperty("到达网点ID") List<Integer> destOrgId;


    @ApiModelProperty("运单号")
    @Logger
    private String code ;

    /**
     * 发货公司
     */
    private @ApiModelProperty("发货公司")String invoiceCompany;
    /**
     * 发货人
     */
    private @ApiModelProperty("发货人")String invoiceUser;

    /**
     * 发货人电话
     */
    private @ApiModelProperty("发货人电话")String invoiceTelNo;
    /**
     * 发货人手机号码
     */
    private @ApiModelProperty("发货人手机号码")String invoiceMobleNo;

    /**
     * 收货人
     */
    private @ApiModelProperty("收货人")String receiptUser;
    /**
     * 收货方名称
     */
    private @ApiModelProperty("收货方名称")String receiptCompany;

    /**
     * 收货人电话
     */
    private @ApiModelProperty("收货人电话")String receiptConsignorTelNo;
    /**
     * 收货人手机号码
     */
    private @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;

    /**
     * 付款方式
     */
    private @ApiModelProperty("付款方式") List<Integer> payWay;

    /**
     * 回单要求
     */
    private  @ApiModelProperty("回单要求") String backType;

    /**
     * 派送方式
     */
    private @ApiModelProperty("配送方式") Integer distributionType;

    /**
     * 订单编号
     */
    private @ApiModelProperty("订单编号") @Logger String orderCode ;

    /**
     * 目的地ID
     */
    private @ApiModelProperty("目的地名称") String destName;

    /**
     * 运输线路id
     */
    private @ApiModelProperty("运输线路id")List<Long> lineId;

    /**
     * 运输线路名称
     */
    private @ApiModelProperty("运输线路名称") String lineName;

    /**
     * 分页 - 每页显示数
     */
    private @ApiModelProperty("分页 - 每页显示数") Integer pageSize = 30;
    /**
     * 分页 - 当前页数
     */
    private @ApiModelProperty("分页 - 当前页数") Integer currentPage = 1;

    /**
     * 初始化分页信息
     */
    public void initPage() {
        if (null == pageSize || pageSize == 0) {
            pageSize = 30;
        }
        if (null == currentPage || currentPage == 0) {
            currentPage = 1;
        }
    }
}
