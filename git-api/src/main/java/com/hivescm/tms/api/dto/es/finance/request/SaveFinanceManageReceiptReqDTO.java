package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/2 14:36
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class SaveFinanceManageReceiptReqDTO implements Serializable{




    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;

    @Mapping
    @ApiModelProperty("应收集合列表")
    private List<FinanceManageReceiptEsDTO> financeManageReceiptEsDTOList;
}
