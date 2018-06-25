package com.hivescm.tms.api.dto.es.transport.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class WaitUnloadTransportWaybillListReqDTO {
	
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
    
    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
    private String branchName;


    /**
     * 运输批次ID
     */
    @ApiModelProperty("运输批次ID")
    private Long transportId;

    /**
     * 运单编码
     */
    private String waybillCode;

    /**
     * 到货批次ID
     */
    @ApiModelProperty("到货批次ID")
    private Long arrivalId;
    
    /**
     * 目的地id
     */
    @ApiModelProperty("目的地id")
    private Long destId;

    /**
     * 目的地名称
     */
    @ApiModelProperty("目的地名称")
    private String destName;
    
    /**
     * 到达网点id
     */

    @ApiModelProperty("到达网点id")
    private Integer destOrgId;

    /**
     * 到达网点名称
     */

    @ApiModelProperty("到达网点名称")
    private String destOrgName;

    @ApiModelProperty("id集合")
    private List<Long> idList;

}
