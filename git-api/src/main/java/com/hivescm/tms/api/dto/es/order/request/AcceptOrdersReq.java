package com.hivescm.tms.api.dto.es.order.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.dto.es.order.OrderEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
/**
 * 接单请求体
 * @author Administrator
 *
 */
@Data
@ToString
public class AcceptOrdersReq implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2842147249563617274L;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
    private Integer opUserId;

    /**
     * 承运商ID
     */
    @Logger 
    @ApiModelProperty(value = "承运商ID")
    private Integer carrierId;
    
    /**
     * 网点ID
     */
    @Logger 
    @ApiModelProperty(value = "网点ID")
    private Integer branchId;
    
    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;
    
    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
    private Integer companyId;

    /**
     * orderInfoList
     */
    @ApiModelProperty(value = "订单主表列表")
    private List<OrderEsDTO> orderDTOList;
    
}
