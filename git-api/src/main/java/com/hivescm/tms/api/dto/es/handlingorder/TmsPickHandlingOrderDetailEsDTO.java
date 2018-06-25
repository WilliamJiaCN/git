package com.hivescm.tms.api.dto.es.handlingorder;

import com.hivescm.tms.api.dto.es.handlingorder.response.PickHandlingOrderDetailRespDTO;
import com.hivescm.tms.api.dto.es.handlingorder.response.PickHandlingOrderGoodsRespDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/23 17:05
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class TmsPickHandlingOrderDetailEsDTO implements Serializable{

    private static final long serialVersionUID = 7929728616724334778L;

    @ApiModelProperty("装卸单运单信息")
    private PickHandlingOrderDetailRespDTO pickHandlingOrderDetailRespDTO;

    @ApiModelProperty("装卸单货物列表")
    private List<PickHandlingOrderGoodsRespDTO> pickHandlingOrderGoodsResDTOList;
}
