package com.hivescm.tms.api.dto.es.transport.component;

import com.hivescm.tms.api.dto.es.transport.redundancy.RfTransportWaybillEsDTO;
import com.hivescm.tms.api.dto.es.transport.redundancy.TransportWaybillTagEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@Data
@ToString
public class RfTransportDTO  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4984127344170229778L;

	 /**
     * 发车批次ID
     */
     private @ApiModelProperty("发车批次ID") Long transportId;
    /**
    * 车辆id
    */
     private   @ApiModelProperty("车辆id") Integer vehicleId;
	 /**
	  * 运单信息
	  */
	 private @ApiModelProperty("运单信息") RfTransportWaybillEsDTO rfTransportWaybillEsDTO;

	  /**
	    * 标签详情
	   */
	  private @ApiModelProperty("标签信息") List<TransportWaybillTagEsDTO> transportWaybillTagEsDTOList;
}
