package com.hivescm.tms.api.dto.es.handlingorder.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 装卸单请求
 * HandlingorderQueryReqDTO
 *
 * @author lutingting
 * @date 2017年11月21日
 * @company 蜂网供应链管理
 */
@Data
@ToString
public class HandlingorderReqDTO implements Serializable {

    private static final long serialVersionUID = -6933611350149830800L;

    /**
     * 公司id
     */
    @ApiModelProperty("公司id")
    private Long companyId;
    @ApiModelProperty("派车单id")
    private Long dispatcherId;
    @ApiModelProperty("交接单号")
	private String receiveNo;
    
    @ApiModelProperty("是否生成派车单")
	private Boolean ifAllowed=false;

}
