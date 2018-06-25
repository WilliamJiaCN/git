package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherDetailStatusEnum;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherStatusEnum;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherTaskEnum;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillDistributionTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 送货签收列表查询请求
 *
 * @author 杨彭伟
 * @date 2017-12-07 14:55
 */
@Data
@ToString
@ApiModel
public class SignQueryReqDTO {
    private static final long serialVersionUID = 1L;

    @Mapping()
    @ApiModelProperty(value = "查询开始时间", required = true)
    private Long startTime;

    @Mapping()
    @ApiModelProperty(value = "查询结束时间", required = true)
    private Long endTime;

    @Mapping()
    @ApiModelProperty(value = "查询时间类型 0.签收时间 1.派送时间", required = true,
            allowableValues = "0, 1", dataType = "Integer", position = -1)
    private Integer timeType;

    @Mapping()
    @ApiModelProperty(value = "当前用户userId")
    private Integer userId;

    @Mapping
    @ApiModelProperty(value = "当前网点Id")
    private Long curDotId;

    @Mapping
    @ApiModelProperty(value = "公司Id")
    private Long companyId;

    @Mapping()
    @ApiModelProperty("运单号")
    @Logger
    private String waybillCode;

    @Mapping
    @ApiModelProperty("车牌号码")
    private String vehicleNo;

    @Mapping()
    @ApiModelProperty("签收批次号(签收单号)")
    @Logger
    private String signBatchNumber;

    @Mapping()
    @ApiModelProperty("派车批次（派送批次）")
    @Logger
    private String batchCode;

    @Logger
    @Mapping()
    @Required
    @ApiModelProperty("签收状态")
    private SignStatusEnum signStatus;

    @Mapping()
    @ApiModelProperty("是否要装车返库")
    private Boolean loaded;

    @ApiModelProperty("是否查询派送失败")
    private Boolean deliveryFailure;

    @ApiModelProperty("模糊搜索")
    private String like;


    @Mapping()
    @ApiModelProperty("客户")
    private List<String> client;

    @ApiModelProperty(value = "分页 - 每页显示数", required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "分页 - 当前页数", required = true)
    private Integer currentPage;

    /**
     * 初始化分页信息
     */
    public void initPage() {
        if (null == pageSize || pageSize == 0) {
            pageSize = 10;
        }
        if (null == currentPage || currentPage == 0) {
            currentPage = 1;
        }
    }

    /**
     * 构建搜索条件
     *
     * @return
     */
    public List<SearchCondition> buildDispatcherDetailSearchCondition() {
        List<SearchCondition> list = new ArrayList<>();

        //排除 发车之前的状态
        /*
         *
         * ASSIGNED(1, "已指派"),
         * ACCEPTED(2, "已接单"),
         * LOADED(3, "已装货"),
         * NOT_DEPARTING(4, "未发车"),
         * CANCELED(7, "已撤单"),
         * DISCARD(8, "已作废"),
         */
        Integer[] dispatcherStatusArray = {
                DispatcherStatusEnum.ASSIGNED.getType(),
                DispatcherStatusEnum.ACCEPTED.getType(),
                DispatcherStatusEnum.LOADED.getType(),
                DispatcherStatusEnum.CANCELED.getType(),
                DispatcherStatusEnum.NOT_DEPARTING.getType(),
                DispatcherStatusEnum.DISCARD.getType(),
        };
        SearchCondition dispatcherStatusSc = SearchConditionUtils.newNotInCondition("status", dispatcherStatusArray);
        list.add(dispatcherStatusSc);

        //过滤掉 城配单子,只查询零担单子
        Integer[] waybillTypeArray = {
                WaybillTypeEnum.SELFENTRANCE.getType(),
                WaybillTypeEnum.APP.getType()
        };
        SearchCondition waybillTypeSC = SearchConditionUtils.newInCondition("waybillType", waybillTypeArray);
        list.add(waybillTypeSC);


        //过滤掉 提货送货 和  提货送货 的单子  fixme 为了 演示 先只查出库送货
        SearchCondition taskIdSC = SearchConditionUtils.newUnEqualCondition("taskId", DispatcherTaskEnum.PICKINBAND.getType());
        list.add(taskIdSC);

        // 签收时间 or 派送时间
        if (null != this.getStartTime() && null != this.getEndTime()
                && this.getStartTime() > 0 && this.getEndTime() > 0) {
            String fieldName;
            if (this.getTimeType() == 1) {
                fieldName = "dispatcherTime";
            } else {
                fieldName = "signTime";
            }
            SearchCondition time = SearchConditionUtils.newBetweenAndCondition(fieldName,
                    this.getStartTime(), this.getEndTime());
            list.add(time);
        }

        // 运单号
        if (StringUtils.isNotBlank(this.getWaybillCode())) {
            SearchCondition waybillCode = SearchConditionUtils.newLikeCondition("code",
                    this.getWaybillCode());
            list.add(waybillCode);
        }
        //当前网点->>派车网点
        if (null != this.getCurDotId() && this.getCurDotId() > 0) {
            //SearchCondition branchId = SearchConditionUtils.newEqualCondition("invoiceOrgId",
            SearchCondition branchId = SearchConditionUtils.newEqualCondition("branchId",
                    this.getCurDotId());
            list.add(branchId);
        }
        //公司id
        if (null != this.getCompanyId() && this.getCompanyId() > 0) {
            SearchCondition companyId = SearchConditionUtils.newEqualCondition("companyId",
                    this.getCompanyId());
            list.add(companyId);
        }

//        if (this.getDeliveryFailure() != null && this.getDeliveryFailure() == true) {
//            //派送失败
//            List<SearchCondition> deliveryFailure = SearchConditionUtils.start().addEqualCondition("detailStatus",
//                    DispatcherDetailStatusEnum.COMPLETE.getType())
//                    .addCondition("signType", SignStatusEnum.NO_SIGN.getType(),ConditionExpressionEnum.EQUAL).end();
//            list.addAll(deliveryFailure);
//            return list;
//        } else{
            // 签收状态
            if (this.getSignStatus() != null && this.getSignStatus() == SignStatusEnum.NO_SIGN){
                    SearchCondition signTypeSc = SearchConditionUtils.newEqualCondition("signType", SignStatusEnum.NO_SIGN.getType());           //派车单签收状态等于未签收
                    SearchCondition statusSc = SearchConditionUtils.newEqualCondition("status", DispatcherDetailStatusEnum.DEPARTED.getType());  //等于已发车
                    SearchCondition detailStatus = SearchConditionUtils.newUnEqualCondition("detailStatus", DispatcherDetailStatusEnum.COMPLETE.getType()); //派车单detailStatus只有两个值，11、已完成 12、未完成；该条件查询不等于已完成
                    list.add(signTypeSc);
                    list.add(statusSc);
                    list.add(detailStatus);
                }
//                else {
//                    SearchCondition signStatus = SearchConditionUtils.newEqualCondition("signType", this.getSignStatus().getType());
//                    list.add(signStatus);
//                }
//            }
//        }

        // 签收单号
        if (StringUtils.isNotBlank(this.getSignBatchNumber())) {
            SearchCondition signBatchNumber = SearchConditionUtils.newLikeCondition("signBatchNumber",
                    this.getSignBatchNumber());
            list.add(signBatchNumber);
        }
        // 派送批次号
        if (StringUtils.isNotBlank(this.getBatchCode())) {
            SearchCondition batchCode = SearchConditionUtils.newLikeCondition("batchCode",
                    this.getBatchCode());
            list.add(batchCode);
        }
        // 车牌号
        if (StringUtils.isNotBlank(this.getVehicleNo())) {
            SearchCondition vehicleNo = SearchConditionUtils.newSearchCondition("vehicleNo",
                    this.getVehicleNo(), ConditionExpressionEnum.LIKE);
            list.add(vehicleNo);
        }

        if (StringUtils.isNotBlank(like)) {
            SearchCondition keywordsSc = SearchConditionUtils.newLikeCondition("keyword", like);

            list.add(keywordsSc);
        }

        return list;
    }

    /**
     * 查询签收失败列表-构建派送失败查询条件
     *
     * @return
     */
    public List<SearchCondition> buildDispatcherFailCondition() {
        List<SearchCondition> list = new ArrayList<>();

        if (null != this.getStartTime() && null != this.getEndTime()
                && this.getStartTime() > 0 && this.getEndTime() > 0) {
            String fieldName;
            if (this.getTimeType() == 1) {
                fieldName = "dispatcherTime";
            } else {
                fieldName = "dispatcherTime";
            }
            SearchCondition time = SearchConditionUtils.newBetweenAndCondition(fieldName,
                    this.getStartTime(), this.getEndTime());
            list.add(time);
        }
        //公司id
        if (null != this.getCompanyId() && this.getCompanyId() > 0) {
            SearchCondition companyId = SearchConditionUtils.newEqualCondition("companyId",
                    this.getCompanyId());
            list.add(companyId);
        }
        //当前网点->>派车网点
        if (null != this.getCurDotId() && this.getCurDotId() > 0) {
            //SearchCondition branchId = SearchConditionUtils.newEqualCondition("invoiceOrgId",
            SearchCondition branchId = SearchConditionUtils.newEqualCondition("branchId",
                    this.getCurDotId());
            list.add(branchId);
        }

        // 运单号
        if (StringUtils.isNotBlank(this.getWaybillCode())) {
            SearchCondition waybillCode = SearchConditionUtils.newLikeCondition("code",
                    this.getWaybillCode());
            list.add(waybillCode);
        }
        // 派送批次号
        if (StringUtils.isNotBlank(this.getBatchCode())) {
            SearchCondition batchCode = SearchConditionUtils.newLikeCondition("batchCode",
                    this.getBatchCode());
            list.add(batchCode);
        }
        // 车牌号
        if (StringUtils.isNotBlank(this.getVehicleNo())) {
            SearchCondition vehicleNo = SearchConditionUtils.newSearchCondition("vehicleNo",
                    this.getVehicleNo(), ConditionExpressionEnum.LIKE);
            list.add(vehicleNo);
        }
        //是否反库
        if (this.getLoaded() != null) {
            SearchCondition batchCode = SearchConditionUtils.newLikeCondition("storage",
                    this.getLoaded());
            list.add(batchCode);

        }
        return list;
    }

    /**
     * 查询签收列表-构建签收查询条件
     *
     * @return
     */
    public List<SearchCondition> buildSignSearchCondition() {
        List<SearchCondition> list = new ArrayList<>();

        //派送类型
        SearchCondition deliveryType = SearchConditionUtils.newEqualCondition("deliveryType", WaybillDistributionTypeEnum.DELIVERY.getType());
        list.add(deliveryType);
        // 签收时间 or 派送时间
        if (null != this.getStartTime() && null != this.getEndTime() && this.getStartTime() > 0 && this.getEndTime() > 0) {
            String fieldName;
            if (this.getTimeType() == 1) {
                fieldName = "dispatcherTime";
            } else {
                fieldName = "signTime";
            }
            SearchCondition time = SearchConditionUtils.newBetweenAndCondition(fieldName,
                    this.getStartTime(), this.getEndTime());
            list.add(time);
        }

        // 运单号
        if (StringUtils.isNotBlank(this.getWaybillCode())) {
            SearchCondition waybillCode = SearchConditionUtils.newLikeCondition("code",
                    this.getWaybillCode());
            list.add(waybillCode);
        }
        //签收网点
        if (null != this.getCurDotId() && this.getCurDotId() > 0) {
            SearchCondition signOrgId = SearchConditionUtils.newEqualCondition("signOrgId",
                    this.getCurDotId());
            list.add(signOrgId);
        }
        //公司ID
        if (null != this.getCompanyId() && this.getCompanyId() > 0) {
            SearchCondition companyId = SearchConditionUtils.newEqualCondition("companyId",
                    this.getCompanyId());
            list.add(companyId);
        }

        // 签收状态
        if (this.getSignStatus() != null) {
            SearchCondition cancelSc = SearchConditionUtils.newEqualCondition("signStatus",
                    this.getSignStatus().getType());
            list.add(cancelSc);
        }

        // 签收单号
        if (StringUtils.isNotBlank(this.getSignBatchNumber())) {
            SearchCondition signBatchNumber = SearchConditionUtils.newLikeCondition("signBatchNumber",
                    this.getSignBatchNumber());
            list.add(signBatchNumber);
        }
        // 派送批次号
        if (StringUtils.isNotBlank(this.getBatchCode())) {
            SearchCondition batchCode = SearchConditionUtils.newLikeCondition("batchCode",
                    this.getBatchCode());
            list.add(batchCode);
        }
        // 车牌号
        if (StringUtils.isNotBlank(this.getVehicleNo())) {
            SearchCondition vehicleNo = SearchConditionUtils.newSearchCondition("vehicleNo",
                    this.getVehicleNo(), ConditionExpressionEnum.LIKE);
            list.add(vehicleNo);
        }
        return list;
    }

    /**
     * 构建分页条件
     *
     * @return
     */
    public PageCondition buildPageCondition(SignQueryReqDTO this) {
        PageCondition pageCondition = new PageCondition();
        pageCondition.setCurrentPage(this.getCurrentPage() == null ? 1 : this.getCurrentPage());
        pageCondition.setPageSize(this.getPageSize() == null ? 10 : this.getPageSize());
        return pageCondition;
    }

    /**
     * 构建排序条件
     *
     * @return
     */
    public List<OrderCondition> buildOrderCondition(SignQueryReqDTO this) {
        List<OrderCondition> orderConditionList = new ArrayList<>();
        OrderCondition orderCondition = new OrderCondition();

        if (this.getTimeType() != null && this.getTimeType().intValue() == 1) {
            orderCondition.setFieldName("dispatcherTime");
        } else {
            orderCondition.setFieldName("signTime");
        }

        orderCondition.setOrderCondition(SortEnum.DESC);
        orderConditionList.add(orderCondition);
        return orderConditionList;
    }

}
