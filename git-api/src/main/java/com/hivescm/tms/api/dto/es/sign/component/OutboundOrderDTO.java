package com.hivescm.tms.api.dto.es.sign.component;

import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 出库单信息
 */
@Data
@ToString
public class OutboundOrderDTO implements Serializable {
	/**
	 * 签收单信息
	 */
	SignEsDTO signEsDTO;
	/**
	 * 运单信息
	 */
	WaybillEsDTO waybillEsDTO;
	/**
	 * 费用信息
	 */
	List<WaybillFeeEsDTO> waybillFeeEsDTOListist;
}
