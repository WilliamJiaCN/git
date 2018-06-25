package com.hivescm.tms.api.dto.es.distribution.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.transport.TransportArrivalCostDetailEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 城配指派司机请求DTO
 * @author lutingting
 * @date 2017年8月9日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionAssignRequestDTO implements Serializable{
	private static final long serialVersionUID = -2801125059760228371L;
	
//    @ApiModelProperty("id")
//    private Long id;
	 /**
     * 公司id
     */
	
    @ApiModelProperty("指派司机/地图指派")
    private DistributionDriverRequestDTO distributionDriverRequestDTO;
    
    @ApiModelProperty("指派车队")
    private List<DistributionDriverGroupRequestDTO> distributionDriverGroupRequestDTO;
    
    @ApiModelProperty("公司ID")
    private Long companyId;
    @ApiModelProperty("公司ID")
    private Long groupId;
	
	@ApiModelProperty("公司名称")
    private String companyName;

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
	
//	@ApiModelProperty("运单ID")
//    private Long waybillId;

	

	@ApiModelProperty("运单ID，多个以逗号区分")
    private List<Long> waybillId;

	

	

}
