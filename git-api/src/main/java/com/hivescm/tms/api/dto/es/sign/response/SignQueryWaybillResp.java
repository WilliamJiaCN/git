package com.hivescm.tms.api.dto.es.sign.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.stock.WaybillGoodsEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2018年3月26日 上午10:47:13
* 
*/
@Data
@ToString
public class SignQueryWaybillResp implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * 运单信息
	 */
	private WaybillEsDTO waybill;
	
	/**
	 * 运单货物
	 */
	private List<WaybillGoodsEsDTO>  waybillGoods;
	
	/**
	 * 送货费
	 */
	@Mapping
	private BigDecimal deliveryFee;
	/**
	 * 代收货款
	 */
	@Mapping
	private BigDecimal collectPayment;
}
