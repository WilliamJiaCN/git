package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.framework.elasticsearch.utils.OrderConditionUtils;
import com.hivescm.framework.elasticsearch.utils.PageConditionUtils;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/4/21
 */
@Data
@ToString
public class PCRefuseSignQueryReq {
    @Mapping()
    @ApiModelProperty("公司id")
    private Long companyId;
    @Mapping()
    @ApiModelProperty("查询开始时间")
    private Long startTime;

    @Mapping()
    @ApiModelProperty("查询结束时间")
    private Long endTime;

    @Mapping()
    @ApiModelProperty("目的网点")
//    @Required
    private Integer destOrgId;

    @Mapping()
    @ApiModelProperty("当前网点")
    private Integer curOrgId;

    @Mapping()
    @ApiModelProperty(value = "签收状态(PARTIAL_SIGN =部分签收 REFUSE_SIGN= 全部拒签)")
    private SignStatusEnum signStatus;
    /**
     * 拒收单号
     */
    @Mapping
    @ApiModelProperty("拒收单号")
    private String refuseCode;
    /**
     * 运单号
     */
    @Mapping
    @ApiModelProperty("运单号")
    private String code;

    @ApiModelProperty("模糊匹配")
    private String like;

    private @ApiModelProperty("分页 - 每页显示数") Integer pageSize = 10;
    /**
     * 分页 - 当前页数
     */
    private @ApiModelProperty("分页 - 当前页数") Integer currentPage = 1;





    public List<OrderCondition> toOrderConditions() {
        return OrderConditionUtils.start().addCondition("createTime", SortEnum.DESC)
                .end();
    }

    public List<SearchCondition> toSearchCondition() {




        List<SearchCondition> scs = new ArrayList<SearchCondition>();

        if (StringUtils.isNotBlank(code)){
            SearchCondition codeSc = new SearchCondition.Builder().setFieldName("code")
                    .setConditionExpression(ConditionExpressionEnum.EQUAL)
                    .setSingleValue(code).build();
            scs.add(codeSc);

            return scs;
        }

        //如果填了拒签单号 直接返回
        if (StringUtils.isNotBlank(refuseCode)){

            SearchCondition scDest = new SearchCondition.Builder().setFieldName("refuseCode")
                    .setConditionExpression(ConditionExpressionEnum.EQUAL)
                    .setSingleValue(refuseCode).build();
            scs.add(scDest);

            return scs;
        }


        if (companyId!=null&&companyId>0){
            SearchCondition scCompanyId = new SearchCondition.Builder().setFieldName("companyId")
                    .setConditionExpression(ConditionExpressionEnum.EQUAL)
                    .setSingleValue(companyId.toString()).build();
            scs.add(scCompanyId);
        }

        // -> 目的网点  fixme 派送网点
//        if (null != destOrgId && destOrgId > 0) {
//            SearchCondition scDest = new SearchCondition.Builder().setFieldName("destOrgId")
//                    .setConditionExpression(ConditionExpressionEnum.EQUAL)
//                    .setSingleValue(destOrgId.toString()).build();
//            scs.add(scDest);
//        }
        if (null != curOrgId && curOrgId > 0) {
            SearchCondition scBranch = new SearchCondition.Builder().setFieldName("destOrgId")
                    .setConditionExpression(ConditionExpressionEnum.EQUAL)
                    .setSingleValue(curOrgId.toString()).build();
            scs.add(scBranch);
        }



        // -> 签收状态
        if (signStatus!=null) {
            SearchCondition scReceipt = new SearchCondition.Builder().setFieldName("refuseType")
                    .setConditionExpression(ConditionExpressionEnum.EQUAL).setSingleValue(signStatus.getType())
                    .build();
            scs.add(scReceipt);
        }

        // ->签收日期
        if (null != startTime && null != endTime
                && startTime > 0 && endTime > 0) {
            SearchCondition scTime = new SearchCondition.Builder().setFieldName("refuseTime")
                    .setConditionExpression(ConditionExpressionEnum.BETWEEN_AND)
                    .setMinValue(startTime.toString())
                    .setMxValue(endTime.toString()).build();
            scs.add(scTime);
        }

        List<SearchCondition> waybillTypeSc = SearchConditionUtils.start()
                .addCondition("waybillType", WaybillTypeEnum.OMSENTRANCE.getType(), ConditionExpressionEnum.UNEQUAL)
                .addCondition("waybillType", WaybillTypeEnum.OMSENTRANCE.getType(), ConditionExpressionEnum.NOT_NULL)
                .end();

        scs.addAll(waybillTypeSc);
        return scs;
    }

    public PageCondition toPageCondition() {
        return PageConditionUtils.create(pageSize,
                currentPage);
    }

}
