package com.hivescm.tms.api.dto.es.dispatcher.towarehouse;

import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherStatusFromWarehouseEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 仓库通知修改派车单状态
 * @author  m5itao
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/11/8
 */
@Data
@ToString
public class DispatcherChangeStatusFromWarehouseDTO {


    @ApiModelProperty("派车批次")
    private String transportDispatcherBatchCode;

    @ApiModelProperty("修改的状态")
    private DispatcherStatusFromWarehouseEnum dispatcherStatusFromWarehouseEnum;

}
