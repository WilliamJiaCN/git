package com.hivescm.tms.api.dto.es.dispatcher.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

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
public class DispatcherSendReqDTO implements Serializable {

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
    @Required
    @ApiModelProperty("派车单id")
    private Long dispatcherId;

    /**
     * 配送时间
     */
    @ApiModelProperty("配送时间")
    private Long sendTime;

    /**
     * 配送人
     */
  
    @ApiModelProperty("配送人")
    private String sendUser;
    @Logger
    @Required
    @ApiModelProperty("仓库交接单号")
    private String warehouseReceiptNo;
    @ApiModelProperty("是否生成装卸单")
    private Boolean allowedAdd;
  
	@ApiModelProperty("仓库交接单主键")
	private Long warehouseReceiptId;
}
