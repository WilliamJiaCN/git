package com.hivescm.tms.api.dto.es.handlingorder.response;

import java.io.Serializable;

import com.hivescm.tms.api.dto.es.handlingorder.TmsPickHandlingOrderDetailEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/23 16:41
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class HandlingOrderTransportInfoRespDTO implements Serializable {

    private static final long serialVersionUID = 2505765418772059566L;


    @ApiModelProperty("装卸单明细信息列表")
    private TmsPickHandlingOrderDetailEsDTO tmsPickHandlingOrderDetailEsDTO;

}
