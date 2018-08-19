package com.hivescm.tms.api.dto.es.transport.response;

import com.hivescm.tms.api.dto.es.transport.TransportInfoEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportLineEsDTO;
import com.hivescm.tms.api.dto.es.transport.component.TmsTransportDetailDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class WayOfTransportResDTO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -8450154549386405440L;

	
	/**
	 * 运输批次基本信息
	 */
	@ApiModelProperty("运输批次基本信息")
	private TransportInfoEsDTO transportInfo;
	
	/**
     * 发车批次当前网点线路信息
     */
    @ApiModelProperty("发车批次当前网点线路信息")
    private TransportLineEsDTO transportLineEsDTO;
    
    /**
     * 运输批次线路信息
     */
    @ApiModelProperty("运输批次线路信息")
    private List<TransportLineEsDTO> transportLineEsDTOList;
    
    /**
     * 发车批次明细信息列表
     */
    @ApiModelProperty("运输批次明细信息列表")
    private List<TmsTransportDetailDTO> tmsTransportDetailList;
    
}
