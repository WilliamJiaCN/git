package com.hivescm.tms.api.dto.es.transport.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.transport.component.RfTransportDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RfTransportReqDTO  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4984127344170229778L;
	
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
    @ApiModelProperty(value = " 网点名称", notes = "前端调用时不传")
    private String branchName;
    
    @ApiModelProperty("集团ID")
    private Long groupId;
    
    /**
     * 车牌号码
     */
    private @ApiModelProperty("车牌号码") String vehicleNo;
    
    /**
     * 车辆ID
     */
    private @ApiModelProperty("车辆ID") Integer vehicleId;
    /**
     * 发车批次ID
     */
    private   @ApiModelProperty("发车批次ID")  Long transportId;

    /**
     * 运单标签信息
     */
	private @ApiModelProperty("运单标签信息") List<RfTransportDTO> rfTransportDTOList;

}
