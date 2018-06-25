package com.hivescm.tms.api.dto.es.transport.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/30 19:36
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class FareTransportArrivalReqDTO implements Serializable {

    @ApiModelProperty("批次号")
    private String batchCode;

    @ApiModelProperty("公司ID")
    private Long companyId;

}
