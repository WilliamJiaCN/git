package com.hivescm.tms.finance.server.service.pcsign.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hivescm.common.utils.Assert;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.OutWareHousePrintReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.OutWareHousePrintRespDTO;
import com.hivescm.tms.finance.server.service.pcsign.OutboundOrderService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;
@Service
public class OutboundOrderServiceImpl implements OutboundOrderService {

	@Autowired
	private EsSignService esSignService;

	/**
	 * 查询获得待打印的的签收单所需信息
	 * @param tmsSignEsDTO
	 * @return
	 */
	@Override
	public OutWareHousePrintRespDTO printOutboundOrder(OutWareHousePrintReqDTO outWareHousePrintReqDTO){
		// >> 1.数据合法性校验
		Assert.isNotNull(outWareHousePrintReqDTO.getSignId(),"签收单主键不能为空！");
		List<SearchCondition> scs = SearchConditionUtils.start().addEqualCondition("id", outWareHousePrintReqDTO.getSignId()).end();
		// >> 2.查询签收单信息
		List<SignEsDTO> querySignEsByConditions = esSignService.querySignEsByConditions(scs);
		if(CollectionUtils.isEmpty(querySignEsByConditions)){
			return null;
		}
		OutWareHousePrintRespDTO resp = EntityUtils.copyProperties(querySignEsByConditions.get(0), OutWareHousePrintRespDTO.class);
		return resp;
	}
}
