package com.hivescm.tms.api.dto.es.finance.response;

import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class FinanceManageCashSerialListRespDTO implements Serializable{

    private static final long serialVersionUID = 3527238869061086713L;

    @ApiModelProperty("总条数")
    private Integer totalNum;
    @ApiModelProperty("上期结余")
    private BigDecimal oldBalance;
    @ApiModelProperty("应付费用列表")
    private List<FinanceManageCashSerialEsDTO> list;
}
