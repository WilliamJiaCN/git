package com.hivescm.tms.api.dto.es.alteration.request;

import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.bill.alteration.AlterationProcessStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自提改配送查询更请求DTO
 *
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/21
 */
@Data
@ToString
public class AlterationDeliveryChangeQueryReqDTO {
    @ApiModelProperty("处理状态")
    private AlterationProcessStatusEnum processingStatus;

    @ApiModelProperty("申请网点Id数组")
    private Integer[] applicantNetworks;

    @ApiModelProperty("发货网点id数组")
    private Integer[] invoiceOrgIds;
    @ApiModelProperty("到达网点id数组")
    private Integer[] destOrgIds;

    @ApiModelProperty("运单号")
    private String waybillCode;

    @ApiModelProperty("(新)收货人姓名")
    private String newReceiptUser;

    @ApiModelProperty("(新)收货人手机")
    private String newReceiptConsignorMobleNo;

    @ApiModelProperty("公司id")
    private Long companyId;

    @ApiModelProperty("公司名称")
    private String companyName;

    @Required
    @ApiModelProperty("申请时间 0 申请时间  1 审核时间")
    private int timeType;

    @ApiModelProperty("开始时间")
    private Long startTime;

    @ApiModelProperty("结束时间")
    private Long endTime;

    @Required
    private Integer currentPage;

    @Required
    private Integer pageSize;

    /**
     * 构建分页条件
     *
     * @return
     */
    public PageCondition buildPageCondition() {

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
    public List<OrderCondition> buildOrderCondition() {
        List<OrderCondition> orderConditionList = new ArrayList<>();
        OrderCondition orderCondition = new OrderCondition();

        if (this.getTimeType()==0){
            orderCondition.setFieldName("applicantTime");
        }else {
            orderCondition.setFieldName("confirmTime");
        }


        orderCondition.setOrderCondition(SortEnum.DESC);
        orderConditionList.add(orderCondition);
        return orderConditionList;
    }

    public List<SearchCondition> buildSearchCondition(){
        List<SearchCondition> list = new ArrayList<>();

        if (null != this.getStartTime() && null != this.getEndTime()
                && this.getStartTime() > 0 && this.getEndTime() > 0) {
            String fieldName;
            if (this.getTimeType() == 0) {
//                fieldName = "sendTime";
                fieldName = "applicantTime";
            } else {
                fieldName = "confirmTime";
            }
            SearchCondition time = SearchConditionUtils.newBetweenAndCondition(fieldName,
                    this.getStartTime(), this.getEndTime());
            list.add(time);
        }


        // 运单号
        if (StringUtils.isNotBlank(this.getWaybillCode())) {
            SearchCondition waybillCode = SearchConditionUtils.newEqualCondition("waybillCode",
                    this.getWaybillCode());
            list.add(waybillCode);
        }

        if (null != this.getCompanyId() && this.getCompanyId() > 0) {
            SearchCondition companyId = SearchConditionUtils.newEqualCondition("companyId",
                    this.getCompanyId());
            list.add(companyId);
        }
//        @ApiModelProperty("申请网点Id数组")
//        private Integer[] applicantNetworks;
//
//        @ApiModelProperty("发货网点id数组")
//        private Integer[] invoiceOrgIds;
//        @ApiModelProperty("到达网点id数组")
//        private Integer[] destOrgIds;
        // 审核状态

        if (processingStatus != null) {
            SearchCondition sc=SearchConditionUtils.newEqualCondition("processingStatus",
                    processingStatus.name());
            list.add(sc);
        }

        if (applicantNetworks!=null&&applicantNetworks.length>0) {
            SearchCondition sc = SearchConditionUtils.newInCondition("applicantNetwork",
                    applicantNetworks);
            list.add(sc);
        }

        if (invoiceOrgIds!=null&&invoiceOrgIds.length>0){
            SearchCondition sc = SearchConditionUtils.newInCondition("invoiceOrgId",
                    invoiceOrgIds);
            list.add(sc);
        }
        if (destOrgIds!=null&&destOrgIds.length>0){
            SearchCondition sc = SearchConditionUtils.newInCondition("destOrgId",
                    destOrgIds);
            list.add(sc);
        }

        if (StringUtils.isNotBlank(newReceiptUser)) {
            SearchCondition sc = SearchConditionUtils.newEqualCondition("newReceiptUser",
                    newReceiptUser);
            list.add(sc);
        }
        if (StringUtils.isNotBlank(newReceiptConsignorMobleNo)) {
            SearchCondition sc = SearchConditionUtils.newEqualCondition("newReceiptConsignorMobleNo",
                    newReceiptConsignorMobleNo);
            list.add(sc);
        }

        SearchCondition ideleteSc= SearchConditionUtils.newEqualCondition("idelete",
                false);
        list.add(ideleteSc);

        return list;
    }
}
