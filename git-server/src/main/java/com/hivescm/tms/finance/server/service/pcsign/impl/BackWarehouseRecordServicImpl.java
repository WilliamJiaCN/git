package com.hivescm.tms.finance.server.service.pcsign.impl;

import com.hivescm.common.domain.PagedList;
import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchDeleteExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchUpdateExecutor;
import com.hivescm.framework.elasticsearch.utils.OrderConditionUtils;
import com.hivescm.framework.elasticsearch.utils.PageConditionUtils;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.sign.BackWarehouseDetailEsDTO;
import com.hivescm.tms.api.dto.es.sign.BackWarehouseEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.SearchBankWarehouseReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SearchBackWarehouseRespDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.pcsign.BackWarehouseRecordService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BackWarehouseRecordServicImpl implements BackWarehouseRecordService {
	@Autowired
	private ESSearchService eSSearchService;

	@Autowired
	private WaybillService waybillService;
	@Override
	public PagedList<SearchBackWarehouseRespDTO> getBackWarehouse(SearchBankWarehouseReqDTO searchBankWarehouseReqDTO) {
		//->查询返回入库信息
		PagedList<SearchBackWarehouseRespDTO> resp = getBackWarehouseInfo(searchBankWarehouseReqDTO);
		//->查询运单信息
		//getwaybill(resp);
		return resp;
	}

	@Override
	public List<SearchBackWarehouseRespDTO> getBackWarehouseDetail(Long dispatcherDetailId) {

		List<SearchCondition> backWarehouseDetailSC = SearchConditionUtils.start().addEqualCondition("dipatcherDetailId",
				dispatcherDetailId)
				.addEqualCondition("idelete",false).end();

		BackWarehouseEsDTO backWarehouseEsDTO = new DefaultAbstractSearchQueryExecutor<BackWarehouseEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.backWarehouse();
			}
		}.get(backWarehouseDetailSC);

		if (backWarehouseEsDTO==null){
			return Collections.emptyList();
		}

		List<SearchCondition> detailSC = SearchConditionUtils.start().addEqualCondition("backWarehouseId",
				backWarehouseEsDTO.getId()).end();

		List<SearchBackWarehouseRespDTO> backWarehouseRespDTOS = new DefaultAbstractSearchQueryExecutor<SearchBackWarehouseRespDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.backWarehouseDetail();
			}
		}.list(backWarehouseDetailSC);

		return backWarehouseRespDTOS;
	}

	/**
	 * 通过签收ID查询返回入库信息
	 * @param signId
	 * @return
	 */
	@Override
	public BackWarehouseEsDTO getBackWarehouseBySignId(Long signId) {
		List<SearchCondition> backWarehouseDetailSC = SearchConditionUtils.start().addEqualCondition("signId",
				signId).addEqualCondition("idelete",false).end();

		BackWarehouseEsDTO backWarehouseEsDTO = new DefaultAbstractSearchQueryExecutor<BackWarehouseEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.backWarehouse();
			}
		}.get(backWarehouseDetailSC);


		return backWarehouseEsDTO;
	}

	/**
	 * 通过签收ID查询返回入库信息
	 * @param backWarehouseEsDTO
	 * @return
	 */
	@Override
	public boolean updateBackWareHouse(BackWarehouseEsDTO backWarehouseEsDTO) {
		return new DefaultAbstractSearchUpdateExecutor<BackWarehouseEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.backWarehouse();
			}
		}.execute(backWarehouseEsDTO);
	}



	/**
	 * 查询返回入库信息
	 * @param req
	 * @return
	 */
	private PagedList<SearchBackWarehouseRespDTO> getBackWarehouseInfo(SearchBankWarehouseReqDTO req){
		//->组装查询条件
		List<SearchCondition> scs = componentSearchConditions(req);
		PageCondition pageCondition = PageConditionUtils.create(req.getPageSize(),req.getPageNumber());
		List<OrderCondition> orderCondition = OrderConditionUtils
				.start().addCondition("storageTime", SortEnum.DESC).end();

		List<BackWarehouseEsDTO> backWarehouseEsDTO = new DefaultAbstractSearchQueryExecutor<BackWarehouseEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.backWarehouse();
			}
		}.list(scs,orderCondition,pageCondition);

		List<SearchBackWarehouseRespDTO> resp = new ArrayList<>();
		if(backWarehouseEsDTO != null && backWarehouseEsDTO.size()>0){
			backWarehouseEsDTO.forEach(item->{
				SearchBackWarehouseRespDTO dto= EntityUtils.copyProperties(item,SearchBackWarehouseRespDTO.class);
				resp.add(dto);
			});
		}
		PagedList<SearchBackWarehouseRespDTO> pagedList = PagedList.createInstance(req.getPageNumber(), req.getPageSize(),pageCondition.getTotalDocs().intValue(),resp);
		return pagedList;
	}
	/**
	 * 组装查询条件
	 * @param reqDTO
	 * @return
	 */
	private List<SearchCondition> componentSearchConditions(SearchBankWarehouseReqDTO reqDTO) {
		List<SearchCondition> scs = new ArrayList<>();

		if (reqDTO.getCompanyId()!=null&&reqDTO.getCompanyId()>0){
			SearchCondition scCompanyId = new SearchCondition.Builder().setFieldName("companyId")
					.setConditionExpression(ConditionExpressionEnum.EQUAL)
					.setSingleValue(reqDTO.getCompanyId().toString()).build();
			scs.add(scCompanyId);
		}
		//- >反库批次 有值直接返回
		if(StringUtils.isNotBlank(reqDTO.getStorageBatchCode())) {
			SearchCondition scStorageBatchCode = new  SearchCondition.Builder()
					.setFieldName("storageBatchCode")
					.setConditionExpression(ConditionExpressionEnum.EQUAL)
					.setSingleValue(reqDTO.getStorageBatchCode()).build();
			scs.add(scStorageBatchCode);

			return scs;
		}

		//- > 反库条件
		if(null != reqDTO.getStartTime() && null != reqDTO.getEndTime() &&
				reqDTO.getStartTime()>0 && reqDTO.getEndTime()>0) {
			SearchCondition scTime = new  SearchCondition.Builder()
					.setFieldName("storageTime")
					.setConditionExpression(ConditionExpressionEnum.BETWEEN_AND)
					.setMinValue(reqDTO.getStartTime().toString())
					.setMxValue(reqDTO.getEndTime().toString()).build();
			scs.add(scTime);
		}

		//- >派车批次
		if(StringUtils.isNotBlank(reqDTO.getBatchCode())){
			SearchCondition scStorageBatchCode = new  SearchCondition.Builder()
					.setFieldName("batchCode")
					.setConditionExpression(ConditionExpressionEnum.EQUAL)
					.setSingleValue(reqDTO.getBatchCode()).build();
					
			scs.add(scStorageBatchCode);
		}
//		//- >待修改 反库类型
//		if(reqDTO.getBackType() != null && reqDTO.getBackType().size()>0) {
//			String[] arr = new String[reqDTO.getBackType().size()];
//			SearchCondition scBackType = new  SearchCondition.Builder()
//					.setFieldName("backType")
//					.setConditionExpression(ConditionExpressionEnum.IN)
//					.setFeldValues(reqDTO.getBackType().toArray(arr)).build();
//
//			scs.add(scBackType);
//		}
		//- >待修改 反库类型
		if(reqDTO.getBackType() != null ) {
			SearchCondition scBackType = new  SearchCondition.Builder()
					.setFieldName("backType")
					.setConditionExpression(ConditionExpressionEnum.EQUAL)
					.setSingleValue(reqDTO.getBackType().getType()).build();

			scs.add(scBackType);
		}
		//- > 运单号
		if(StringUtils.isNotBlank(reqDTO.getWaybillCode())){
			SearchCondition scStorageBatchCode = new  SearchCondition.Builder()
					.setFieldName("waybillCode")
					.setConditionExpression(ConditionExpressionEnum.EQUAL)
					.setSingleValue(reqDTO.getWaybillCode()).build();
					
			scs.add(scStorageBatchCode);
		}

		SearchCondition idelete = new  SearchCondition.Builder()
				.setFieldName("idelete")
				.setConditionExpression(ConditionExpressionEnum.EQUAL)
				.setSingleValue(false).build();

		scs.add(idelete);
		return scs;
	}
	
	/**
	 *
	 * 得到运单信息
	 * @param backWarehouseEsDTOS
	 * @return
	 */
	private void getwaybill(PagedList<SearchBackWarehouseRespDTO> backWarehouseEsDTOS){
		List<SearchBackWarehouseRespDTO> items = backWarehouseEsDTOS.getItems();
		if(null != items && items.size()>0) {
			items.forEach(item->{
    			WaybillEsDTO waybillEsDTO = waybillService.queryWaybillEsDTO(item.getWaybillId());
				item.setWaybill(waybillEsDTO);
			});
		}
		return;
	}

	@Override
	public Boolean deleteByBackWarehouseId(Long backWarehouseId) {
		return new DefaultAbstractSearchDeleteExecutor<BackWarehouseEsDTO>(eSSearchService) {
			@Override
				public EsConfig getConfig() {
					return EsConfig.backWarehouse();
				}
		}.execute(backWarehouseId);
	}

	@Override
	public Boolean deleteDetailsByBackWarehouseId(Long backWarehouseId){
		List<SearchCondition> scs = SearchConditionUtils.start()
				.addCondition("backWarehouseId",backWarehouseId, ConditionExpressionEnum.EQUAL).end();
		return new DefaultAbstractSearchDeleteExecutor<BackWarehouseDetailEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.backWarehouseDetail();
			}
		}.execute(scs);
	}


}
