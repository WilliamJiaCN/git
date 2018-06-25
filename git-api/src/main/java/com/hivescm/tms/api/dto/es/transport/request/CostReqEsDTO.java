package com.hivescm.tms.api.dto.es.transport.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.google.common.collect.Lists;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.transport.TransportCostDetailEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportInfoEsDTO;
import com.hivescm.tms.api.dto.es.transport.TransportWaybillDetailEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 车费更改
 * @author zxm
 *
 */
@Data
@ToString
public class CostReqEsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8025178667299098715L;
	
	@ApiModelProperty("批次")
	private TransportInfoEsDTO info;
	
	@ApiModelProperty("新增费用明细")
	private List<TransportCostDetailEsDTO> addList ;//新增
	
	@ApiModelProperty("修改费用明细")
	private List<TransportCostDetailEsDTO> updateList;//修改
	
	@ApiModelProperty("删除的费项ID")
    private List<Long> costId;
	
	@ApiModelProperty("费用分摊")
	private List<TransportWaybillDetailEsDTO> transportWaybillDetailEsDTOList;
}
