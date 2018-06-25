package com.hivescm.tms.api.dto.es.delivery.resp;

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
public class DeliveryCompanyRespDTO implements Serializable {

    private static final long serialVersionUID = -8773015249459366806L;
    
    @ApiModelProperty("物流组织id")
    private Long companyId;
    @ApiModelProperty("物流组织名称")
    private String companyName;
    //true 是  false 否
    @ApiModelProperty("是否已关联")
    private Boolean relation;
}
