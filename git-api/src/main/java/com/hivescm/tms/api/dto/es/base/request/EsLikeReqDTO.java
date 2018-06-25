package com.hivescm.tms.api.dto.es.base.request;

import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/10
 */
@Data
@ToString
public class EsLikeReqDTO {

    private List<SearchCondition> scs;

    private List<OrderCondition> orders;

    private PageCondition pageCondition;

    private String likeString;

    private String cascadeName;

}
