package com.hivescm.tms.api.dto.es.distribution.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class DistributionLoadingReqDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Mapping
    @ApiModelProperty("派车单ID")
    private Long dispatcherId;

    /**
     * 公司id
     */
    @ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 派车批次
     */
    @ApiModelProperty("派车批次")
    private String batchCode;
    /**
     * 运单ID
     */
    @ApiModelProperty("运单ID")
    private Long waybillId;
    
    @ApiModelProperty("司机姓名(装货时必填)")
	private String driverName;
    
    @ApiModelProperty("司机(装货时必填)")
    private Integer driverId;
    
    @ApiModelProperty("司机电话号码(装货时必填)")
    private String driverPhone;
    
    @ApiModelProperty("签收验证码(单个签收时必填)")
    private Integer signCode;
    
    @ApiModelProperty("单个装货或签收必填城配单类型")
    private Integer billType;
    
    @ApiModelProperty("网点id")
    private Integer dotId;
    
    @ApiModelProperty("网点名称")
    private String dotName;
    
    @ApiModelProperty("图片验证码地址 单个签收必填")
    private String[] pictures;
    
}
