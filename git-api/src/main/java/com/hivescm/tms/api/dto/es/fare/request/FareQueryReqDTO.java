package com.hivescm.tms.api.dto.es.fare.request;

import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.utils.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
public class FareQueryReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("公司Id")
    private @Logger
    Long companyId;
    @ApiModelProperty("申请网点id")
    private Integer createOrgId;
    @ApiModelProperty("状态 1未确认2已同意3已否决")
    private Integer status;

    @ApiModelProperty("时间排序状态 1申请日期 2审核日期")
    private Integer timeType;
    @ApiModelProperty("更改批次号")
    private String changeCode;
    @ApiModelProperty("单据批次号")
    private String billCode;
    @ApiModelProperty("单据类型（2派车单3配载单7到货确认单）")
    private Integer billType;
    @ApiModelProperty("开始时间")
    private Long startTime;
    @ApiModelProperty("结束时间")
    private Long endTime;
    @ApiModelProperty("分页大小")
    private Integer pageSize;

    @ApiModelProperty("当前页数")
    private Integer currentPage;


    public Boolean isValid() {
        if (null == this.companyId) {
            return false;
        }
        if (null != startTime || null != endTime) {
            return null != startTime && null != endTime;
        }
        if (null != billType || StringUtil.isNotBlank(billCode)) {
            return null != billType && StringUtil.isNotBlank(billCode);
        }
        return true;
    }

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

        if (this.getTimeType()!=null&&this.getTimeType().intValue()==1){
            orderCondition.setFieldName("createTime");
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
            if (this.getTimeType() == 1) {
//                fieldName = "sendTime";
                fieldName = "createTime";
            } else {
                fieldName = "confirmTime";
            }
            SearchCondition time = SearchConditionUtils.newBetweenAndCondition(fieldName,
                    this.getStartTime(), this.getEndTime());
            list.add(time);
        }


        if (StringUtils.isNotBlank(changeCode)) {
            SearchCondition sc = SearchConditionUtils.newEqualCondition("changeCode",
                    changeCode);
            list.add(sc);
        }
        if (StringUtils.isNotBlank(billCode)) {
            SearchCondition sc = SearchConditionUtils.newEqualCondition("billCode",
                    billCode);
            list.add(sc);
        }

        if (null != this.getCompanyId() && this.getCompanyId() > 0) {
            SearchCondition companyId = SearchConditionUtils.newEqualCondition("companyId",
                    this.getCompanyId());
            list.add(companyId);
        }


        if (billType != null) {
            SearchCondition sc=SearchConditionUtils.newEqualCondition("billType",
                    billType);
            list.add(sc);
        }
        if (createOrgId != null) {
            SearchCondition sc=SearchConditionUtils.newEqualCondition("createOrgId",
                    createOrgId);
            list.add(sc);
        }
        if (status != null) {
            SearchCondition sc=SearchConditionUtils.newEqualCondition("status",
                    status);
            list.add(sc);
        }

        return list;
    }
}
