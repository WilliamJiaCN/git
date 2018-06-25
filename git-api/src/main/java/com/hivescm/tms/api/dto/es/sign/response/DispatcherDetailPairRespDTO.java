package com.hivescm.tms.api.dto.es.sign.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/5/2
 */
@Data
@ToString
@ApiModel
public class DispatcherDetailPairRespDTO {

    @Mapping
    @ApiModelProperty("派送批次号")
    private String batchCode;
    @Mapping
    @ApiModelProperty("运单号")
    private String code;
    @Mapping
    @ApiModelProperty("派车明细ID")
    private Long id;

}
