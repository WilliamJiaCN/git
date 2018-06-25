package com.hivescm.tms.api.dto.es.orderrule.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.orderrule.CarrierCapacityConfigEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
/**
 * 提交承运商运力 请求体
 * @author Administrator
 *
 */
@Data
@ToString
public class CopyCapacityConfigReq implements Serializable {
	
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
     * 复制目标日期
     */
    @ApiModelProperty(value = "复制目标日期")
    private String configDate;

    /**
     * 运力列表
     */
    @ApiModelProperty(value = "运力列表")
    private List<CarrierCapacityConfigEsDTO> esDTOList;
    
}
