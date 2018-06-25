package com.hivescm.tms.api.dto.es.transport.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 单票移除
 *
 * @author 李洪春
 * @since 2017/9/7 8:46
 */
@Data
@ToString
public class DelTransportWaybillReqDTO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8748793261495040325L;


    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
    private Long companyId;

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
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;
    
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
    private String branchName;


    @Required
    @ApiModelProperty(value = "运输批次ID",required=true)
    private Long transportId;
    
    @Required
    @ApiModelProperty(value = "运输单明细ID",required=true)
	 private Long transportWaybillDetailId;

    @Required
    @ApiModelProperty(value = "移除货物信息",required=true)
    private List<TransportGoodsDetailReqDTO> transportGoodsDetailReqDTOList;

    @Required
    @ApiModelProperty(value = "移除后入库仓库ID",required=true)
    private Integer warehouseId;
    
    @Required
    @ApiModelProperty(value = "移除后入库仓库名称",required=true)
    private String warehouseName; 

    
}
