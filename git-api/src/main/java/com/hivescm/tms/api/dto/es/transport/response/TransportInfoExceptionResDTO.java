package com.hivescm.tms.api.dto.es.transport.response;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 运输批次基础信息
 *
 * @author 李洪春
 */
@Data
@ToString
public class TransportInfoExceptionResDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 72214749865435368L;

	  @Mapping
	  @ApiModelProperty("主键")
	   private Long id;
	  
	  @Mapping
	  @ApiModelProperty("发车批次 ， 自动生成")
	   private String departBatch;

	  @Mapping
	  @ApiModelProperty("车牌号码")
	   private String vehicleNo;
	  
	  @Mapping
	  @ApiModelProperty("司机id")
	   private Integer driverId;
	  
	  @Mapping
	  @ApiModelProperty("司机姓名")
	   private String driverName;
	
	   @Mapping
	   @ApiModelProperty("承运商ID")
	   private Integer carrierId;

	  
	   @Mapping
	   @ApiModelProperty("承运商名称")
	   private String carrierName;
}