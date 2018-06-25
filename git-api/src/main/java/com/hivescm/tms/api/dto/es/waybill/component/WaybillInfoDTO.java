package com.hivescm.tms.api.dto.es.waybill.component;

import java.io.Serializable;
import java.util.List;
import com.hivescm.tms.api.dto.es.stock.WaybillGoodsEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import lombok.Data;
import lombok.ToString;
@ToString
@Data
public class WaybillInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5436394132838937583L;
	
	private WaybillEsDTO waybill;
	
	private List<WaybillGoodsEsDTO> goods;
	
}
