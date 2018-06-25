package com.hivescm.tms.api.dto.es.bill.response;

import com.hivescm.tms.api.dto.es.bill.BillInfoEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 单据详情响应类
 *
 * @author ke.huang
 * @date 2017年9月25日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BillInfoRespDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("单据详情列表")
    private List<BillInfoEsDTO> billInfoEsDTOList;

    @ApiModelProperty("单据详情总条数")
    private Integer count = 0;
}
