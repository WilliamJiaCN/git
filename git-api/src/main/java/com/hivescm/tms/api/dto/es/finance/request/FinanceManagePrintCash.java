package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class FinanceManagePrintCash  implements Serializable{
    private static final long serialVersionUID = 1886594723670487067L;

    @Required
    @ApiModelProperty(value = "转账ID",required=true)
    private Long id;
}
