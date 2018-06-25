package com.hivescm.tms.api.dto.es.transport.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/30 13:59
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ArrivalCostReqDTO implements Serializable {

    @ApiModelProperty("ID集合")
    private List<Long> idList;
}
