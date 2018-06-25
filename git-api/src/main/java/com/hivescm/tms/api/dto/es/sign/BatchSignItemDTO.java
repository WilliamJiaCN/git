package com.hivescm.tms.api.dto.es.sign;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 *
 * 批量签收 元素 相当于联合主键
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/3/28
 */

@Data
@ToString
public class BatchSignItemDTO {

    @Mapping
    @ApiModelProperty("运单id")
    private Long waybillId;

    @Mapping
    @ApiModelProperty("派车单id")
    private Long dispatcherId;

    @Mapping
    @ApiModelProperty("派车单详情id")
    private Long dispatcherDetailId;

}
