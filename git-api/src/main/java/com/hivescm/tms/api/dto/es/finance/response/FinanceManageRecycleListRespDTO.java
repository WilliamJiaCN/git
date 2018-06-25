package com.hivescm.tms.api.dto.es.finance.response;

import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsGrantEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsRecycleEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class FinanceManageRecycleListRespDTO implements Serializable{
    private static final long serialVersionUID = 7761975107788089180L;
    @ApiModelProperty("总条数")
    private Integer totalNum;
    @ApiModelProperty("应付费用列表")
    private List<FinanceManageGoodsRecycleEsDTO> list;
}
