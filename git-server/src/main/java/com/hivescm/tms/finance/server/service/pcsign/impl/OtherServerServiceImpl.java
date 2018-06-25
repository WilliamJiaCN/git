/**
 * 
 */
package com.hivescm.tms.finance.server.service.pcsign.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.lucene.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.response.WaybillTrackRespDTO;
import com.hivescm.tms.finance.server.service.pcsign.OtherServerService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;

/**
 * @author  boqiang.deng
 * @date    2018年4月10日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Service
public class OtherServerServiceImpl implements OtherServerService{

	@Autowired
	private EsSignService esSignService;

	@Override
	public WaybillTrackRespDTO getWaybillTrackInfo(Long waybillId) {
		List<SignEsDTO> querySignEsByWaybillId = esSignService.querySignInfoByWaybillId(waybillId);
		if(CollectionUtils.isEmpty(querySignEsByWaybillId)){
			return null;
		}
		WaybillTrackRespDTO resp = new WaybillTrackRespDTO();
		StringBuffer sb = new StringBuffer();
		querySignEsByWaybillId.forEach(ation->{
			sb.append(ation.getSignPic());
		});
		resp.setPictures(sb.toString());
		resp.setSignTime(querySignEsByWaybillId.get(0).getSignTime());
		return resp;
	}
	
	
}
