package com.hivescm.tms.api.dto.es.order.request;

import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
/**
 * 接单管理获取订单列表 请求体
 * @author Administrator
 *
 */
@Data
@ToString
public class GetOrderListForAcceptOrderReq implements Serializable {
	
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
     * 操作网点Id
     */
    @ApiModelProperty(value = "操作网点Id", notes = "前端调用时不传")
    private Integer branchId;
    
    /**
     * 订单状态
     */
    @ApiModelProperty(value = "订单状态")
    private Integer status;
    
    /**
     * 承运商ID
     */
    @ApiModelProperty(value = "承运商ID")
    private Integer carrierId;
    
    /**
     * 接收时间开始
     */
    @ApiModelProperty(value = "接收时间开始")
    private Long platformAcceptTimeStart;
    
    /**
     * 接收时间结束
     */
    @ApiModelProperty(value = "接收时间结束")
    private Long platformAcceptTimeEnd;
    
    /**
     * 运力分配时间开始
     */
    @ApiModelProperty(value = "运力分配时间开始")
    private Long capacityTimeStart;
    
    /**
     * 运力分配时间结束
     */
    @ApiModelProperty(value = "运力分配时间结束")
    private Long capacityTimeEnd;
    
    /**
     * 接单时间开始
     */
    @ApiModelProperty(value = "接单时间开始")
    private Long acceptTimeStart;
    
    /**
     * 接单时间结束
     */
    @ApiModelProperty(value = "接单时间结束")
    private Long acceptTimeEnd;
    
    /**
     * 拒接时间开始
     */
    @ApiModelProperty(value = "拒接时间开始")
    private Long refuseTimeStart;
    
    /**
     * 拒接时间结束
     */
    @ApiModelProperty(value = "拒接时间结束")
    private Long refuseTimeEnd;
    
    /**
     * 撤回时间开始
     */
    @ApiModelProperty(value = "撤回时间开始")
    private Long retractTimeStart;
    
    /**
     * 撤回时间结束
     */
    @ApiModelProperty(value = "撤回时间结束")
    private Long retractTimeEnd;
    
    /**
     * 线路ID
     */
    @Logger
    @ApiModelProperty(value = "线路ID")
    private Long lineId;
    
    /**
     * 仓库ID
     */
    @Logger
    @ApiModelProperty(value = "仓库ID")
    private Long warehouseId;

    
}
