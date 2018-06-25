package com.hivescm.tms.api.dto.es.delivery.component;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author  qsk
 * @company 蜂网供应链(上海)管理有限公司
 * @version 2017年11月18日 下午1:41:49
 * 快递公司信息
 */
@Data
@ToString
public class TmsDeliveryCompanyEsDTO implements Serializable {
    private static final long serialVersionUID = 8589098250150171477L;
    @ApiModelProperty("物流组织id")
    private Long companyId;
    @ApiModelProperty("物流组织名称")
    private String companyName;
}
