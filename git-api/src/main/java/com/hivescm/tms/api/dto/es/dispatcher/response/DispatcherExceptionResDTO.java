package com.hivescm.tms.api.dto.es.dispatcher.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 异常提供派车单信息
 *
 * @author 李洪春
 */
@Data
@ToString
public class DispatcherExceptionResDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 72214749865435368L;

	  @Mapping
	  @ApiModelProperty("主键")
	   private Long id;
	  
	  @Mapping
	  @ApiModelProperty("派车批次 ")
	   private String batchCode;

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