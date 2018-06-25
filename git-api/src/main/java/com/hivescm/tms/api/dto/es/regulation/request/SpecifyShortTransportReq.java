package com.hivescm.tms.api.dto.es.regulation.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.waybill.component.TmsWaybillStockDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class SpecifyShortTransportReq implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 599757090384106377L;

	/**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
    private Integer companyId;

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
    
    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
    private String branchName;
    
    /**
     * 短途批次Id
     */
    @ApiModelProperty(value = "短途批次Id")
    private Long transportId;
    
    /**
     * 该批次在当前途径网点的状态
     */
    @ApiModelProperty(value = "途径网点状态")
    private Integer status;
    
    /**
     * 库存信息
     */
    @ApiModelProperty(value = "库存信息")
	List<TmsWaybillStockDTO> tmsWaybillStockDTOList;

}
