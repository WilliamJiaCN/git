package com.hivescm.tms.api.dto.es.transport.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/6/5 15:55
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class TransportArrivalPrintReqDTO implements Serializable {

    private static final long serialVersionUID = -4482464464165693177L;
    /**
     * 到货批次ID
     */
    @Required
    @ApiModelProperty(value = "到货批次ID",required = true)
    private Long arrivalId;
}
