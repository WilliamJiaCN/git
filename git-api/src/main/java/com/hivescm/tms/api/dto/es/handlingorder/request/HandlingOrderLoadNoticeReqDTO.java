package com.hivescm.tms.api.dto.es.handlingorder.request;

import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.enums.biz.handlingorder.HandingOrderLoadOperationTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 装卸单 发车确认 通知
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/23
 */
@Data
@ToString
public class HandlingOrderLoadNoticeReqDTO {

//    @Mapping
//    @ApiModelProperty("装卸单号")
//    private String handlingOrderCode;
    @Required
    @ApiModelProperty("装卸单id")
    private Long HandlingOrderId;

    @Required
    @ApiModelProperty("操作类型")
    private HandingOrderLoadOperationTypeEnum operationType;

}
