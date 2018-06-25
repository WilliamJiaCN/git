package com.hivescm.tms.api.dto.es.dispatcher.request;

import java.io.Serializable;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 派车单请求体
 * TmsDispatcherEsDTO
 *
 * @author lutingting
 * @date 2017年11月11日
 * @company 蜂网供应链管理
 */
@Data
@ToString
public class DispatcherHandleReqDTO implements Serializable {

    private static final long serialVersionUID = -6933611350149830800L;

    /**
     * 公司id
     */
    @Required
    @ApiModelProperty("公司id")
    private Long companyId;

  
    /**
     * 派车单ID
     */
    @Logger
    @ApiModelProperty("派车单id")
    private Long dispatcherId;

    /**
     * 交接时间
     */
    @Required
    @ApiModelProperty("交接时间")
    private Long handleTime;

    /**
     * 交接人
     */
    
    @ApiModelProperty("交接人")
    private String handleUser;
    @Logger
	@Required
	@ApiModelProperty("仓库交接单号")
    private String warehouseReceiptNo;
    @Logger
	@ApiModelProperty("仓库交接单主键")
	private Long warehouseReceiptId;

}
