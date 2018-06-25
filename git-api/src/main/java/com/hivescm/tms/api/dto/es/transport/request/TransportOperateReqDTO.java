package com.hivescm.tms.api.dto.es.transport.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 运输批次操作通用请求对象
 * <p>
 * 发车、取消发车、删除、等操作请求对象
 *
 * @author 李洪春
 * @since 2017/8/31 11:10
 */
@Data
@ToString
public class TransportOperateReqDTO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 8685787002410561441L;

	/**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传",hidden = true)
    private Long companyId;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传",hidden = true)
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传",hidden = true)
    private String opUserName;

    /**
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传",hidden = true)
    private Integer branchId;
    
    /**
     * 网点名称
     */
    @ApiModelProperty("网点名称")
    private String departBranchName;
    
    @ApiModelProperty("公司名称")
    private String companyName;

    /**
     * 到车时间
     */
    @ApiModelProperty(value = "到车时间" , notes = "前端不传时用系统默认时间")
    private Long arrivedTime;
    /**
     * 发车批次ID
     */
    @ApiModelProperty("发车批次ID")
    private Long transportId;

    

}
