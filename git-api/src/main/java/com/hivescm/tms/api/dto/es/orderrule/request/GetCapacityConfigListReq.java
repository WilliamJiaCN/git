package com.hivescm.tms.api.dto.es.orderrule.request;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
/**
 * 查询承运商运力列表 请求体
 * @author Administrator
 *
 */
@Data
@ToString
public class GetCapacityConfigListReq implements Serializable {
	
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
     * 运力状态
     */
    @ApiModelProperty(value = "运力状态")
    private Integer status ;
    
    /**
     * 运力日期开始日期
     */
    @ApiModelProperty(value = "运力日期开始日期")
    private String configDateStart;
    
    /**
     * 运力日期结束日期
     */
    @ApiModelProperty(value = "运力日期结束日期")
    private String configDateEnd;
    
    /**
     * 仓库ID
     */
    @ApiModelProperty(value = "仓库ID")
    private List<Integer> warehouseIdList;
    
    /**
     * 线路ID 
     */
    @ApiModelProperty(value = "线路ID")
    private Integer carrierLineId;
    
    /**
     * 仓库名称关键字（模糊查询）
     */
    @ApiModelProperty(value = "仓库名称")
    private String warehouseName;
    
    /**
     * 线路名称关键字（模糊查询）
     */
    @ApiModelProperty(value = "线路名称")
    private String carrierLineName;
    
}

