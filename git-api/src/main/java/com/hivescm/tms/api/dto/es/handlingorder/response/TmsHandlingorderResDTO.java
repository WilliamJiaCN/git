package com.hivescm.tms.api.dto.es.handlingorder.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@Data
@ToString
public class TmsHandlingorderResDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2807393093964330299L;
	
	@ApiModelProperty(value = "运单")
	private  HandlingorderDetailResDTO  handlingorderDetailResDTO;
	
	@ApiModelProperty(value = "货物明细")
	private  List<HandlingorderGoodsResDTO> handlingorderGoodsResDTOList;
	
	 @ApiModelProperty(value = "来源车辆 ，车牌号")
	 private String vehicleNo; 
	 
	 @ApiModelProperty(value = "批次来源  1 = 派车批次，2 = 到货批次 ")
	 private Integer batchType; 
	
	 @ApiModelProperty(value = "批次号 ")
	 private String batch; 
}
