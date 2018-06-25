package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.tms.api.dto.es.stock.WaybillGoodsEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 运单请求体
 * 
 * @author ke.huang
 * @date 2017年7月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WaybillBossInfoFeeReqEsDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private WaybillEsDTO waybillEs;
	
	private List<WaybillGoodsEsDTO> goodsEsList;

}
