package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/4 9:46
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceivableFinanceReceiptReqDTO implements Serializable{

    private static final long serialVersionUID = 2030342536682555268L;

    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
    private Long companyId;

    /**
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;


    @Mapping
    @ApiModelProperty("应收集合")
    List<ReceivableFinanceReceiptEsDTO> receivableFinanceReceiptEsDTOList;
}
