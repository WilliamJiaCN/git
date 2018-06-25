package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.framework.validation.annotation.Required;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 到付送货费请求dto
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/6/11
 */
@Data
@ToString
public class ToPayDeliveryFeeAddReqDTO {

    @Required
    private Long waybillId;

    @Required
    private BigDecimal toPayDeliveryFee;

    @Required
    private Integer createUser;

    @Required
    private String createUserName;


}
