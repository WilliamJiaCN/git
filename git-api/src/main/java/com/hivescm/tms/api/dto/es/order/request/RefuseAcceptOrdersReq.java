package com.hivescm.tms.api.dto.es.order.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.order.component.TmsOrderInfoDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
/**
 * 拒绝接单请求体
 * @author Administrator
 *
 */
@Data
@ToString
public class RefuseAcceptOrdersReq implements Serializable {
	
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
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;
    
    /**
     * 承运商ID
     */
    @ApiModelProperty(value = "承运商ID")
    private Integer carrierId;

    /**
     * orderInfoList
     */
    @ApiModelProperty(value = "订单主表列表")
    private List<TmsOrderInfoDTO> orderDTOList;
    
}
