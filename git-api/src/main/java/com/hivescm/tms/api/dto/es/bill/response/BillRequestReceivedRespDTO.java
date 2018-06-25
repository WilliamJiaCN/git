package com.hivescm.tms.api.dto.es.bill.response;

import com.hivescm.tms.api.dto.es.bill.BillRequestReceivedEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 单据申领响应类
 *
 * @author ke.huang
 * @date 2017年9月25日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BillRequestReceivedRespDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("单据申领列表")
    private List<BillRequestReceivedEsDTO> billRequestReceivedEsDTOList;

    @ApiModelProperty("单据申领总条数")
    private Integer count = 0;
}
