package com.hivescm.tms.api.dto.es.dispatcher.response;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.waybill.component.TmsWaybillEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 派车单发车请求数据对象
 *
 * @author 李洪春
 * @since 2017/8/14 20:51
 */
@Data
@ToString
public class WaybillStorageRespDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -875225972115997910L;


    /**
     * 派车单Id
     */
    @Logger
    @Required
    @ApiModelProperty("派车单Id")
    private Long dispatcherId;
    
    /**
     * 派车单Id
     */
    @Logger
    @Required
    @ApiModelProperty("派车单明细Id")
    private Long dispatcherDetailId;
    @Logger
    @Required
    @ApiModelProperty("运单Id")
    private Long waybillId;

    @Required
    @ApiModelProperty("操作类型（1.提货入库 2补录运单）")
    private Integer type;

    /**
     * 派车单明细ID列表
     */
    @Required
    @ApiModelProperty(value = "运单基本信息", notes = "派车单提货入库时需传此参数")
    private TmsWaybillEsDTO waybillEsDto;
    
    
}
