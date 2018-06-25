package com.hivescm.tms.api.dto.es.orderrule.request;

import java.io.Serializable;
import com.hivescm.tms.api.dto.es.orderrule.CarrierOrderRuleEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
/**
 * 承运商接单规则创建
 * @author Administrator
 *
 */
@Data
@ToString
public class CreateCarrierOrderRuleReq implements Serializable {
	
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
     * 规则信息
     */
    @ApiModelProperty(value = "规则信息")
    private CarrierOrderRuleEsDTO carrierOrderRuleEsDTO;
    
}
