package com.hivescm.tms.api.dto.es.orderrule.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
/**
 * 查询承运商运力 请求体
 * @author Administrator
 *
 */
@Data
@ToString
public class DeleteCapacityConfigReq implements Serializable {
	
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
     * 运力IDList
     */
    @ApiModelProperty(value = "运力ID")
    private List<Long> capacityConfigIdList;
    
}
