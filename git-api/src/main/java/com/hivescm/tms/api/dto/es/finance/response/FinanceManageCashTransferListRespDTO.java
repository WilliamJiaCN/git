package com.hivescm.tms.api.dto.es.finance.response;

import com.hivescm.tms.api.dto.es.finance.FinanceManageCashTransferEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class FinanceManageCashTransferListRespDTO implements Serializable {
    private static final long serialVersionUID = 5298618603631550942L;
    @ApiModelProperty("总条数")
    private Integer totalNum;
    @ApiModelProperty("应付费用列表")
    private List<FinanceManageCashTransferEsDTO> list;
}
