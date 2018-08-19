package com.hivescm.tms.api.dto.es.waybill.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:跟踪详情
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/24 19:15
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WaybillTrackRespDTO implements Serializable {

    private static final long serialVersionUID = 1322084350242267692L;

    @ApiModelProperty("公司信息")
    private CompanyInfo companyInfo;

    @ApiModelProperty("跟踪信息")
    private List<WaybillTrackListDTO> waybillTrackRespDTOList;



}
