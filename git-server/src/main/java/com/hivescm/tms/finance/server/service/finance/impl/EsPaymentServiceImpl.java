package com.hivescm.tms.finance.server.service.finance.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.common.domain.PagedList;
import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.escenter.service.ESStatisticService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchAggregateExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.finance.PayOriginazationDTO;
import com.hivescm.tms.api.dto.es.finance.PayeeObjectDTO;
import com.hivescm.tms.api.dto.es.finance.component.FinancePaymentESDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinancePaymentReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinancePaymentRespDTO;
import com.hivescm.tms.api.enums.biz.finance.GrantStatusEnum;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.finance.EsPaymentService;
import com.hivescm.tms.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @author 杨彭伟
 * @date 2018-01-03 21:22
 */
@Slf4j
@Service
public class EsPaymentServiceImpl implements EsPaymentService {
	@Autowired
	private ESSearchService esSearchService;
	@Autowired
	private ESStatisticService esStatisticService;

	@Override
	public FinancePaymentRespDTO getPaymentList(FinancePaymentReqDTO financePaymentReqDTO) {
		FinancePaymentRespDTO respDTO = new FinancePaymentRespDTO();
		PagedList<FinancePaymentESDTO> pagedList = new PagedList<>();
		List<SearchCondition> searchConditions = buildSearchConditions(financePaymentReqDTO);
		PageCondition pageCondition = buildPageCondition(financePaymentReqDTO);
		List<OrderCondition> orderConditions = buildOrderCondition();
		List<FinancePaymentESDTO> list = new ArrayList<FinancePaymentESDTO>();
		Integer total = getTotal(financePaymentReqDTO);
		// 如果是未发放的初始化查询，需要查询全部符合条件的数据来构建付款组织和收款方
		if (GrantStatusEnum.NOT_GRANT.getType() == financePaymentReqDTO.getGrantStatus()) {
			Integer startIndex = (pageCondition.getCurrentPage() - 1) * pageCondition.getPageSize();
			Integer endIndex = pageCondition.getCurrentPage() * pageCondition.getPageSize();
			pageCondition.setCurrentPage(1);
			pageCondition.setPageSize(total);
			List<FinancePaymentESDTO> allList = getList(searchConditions, orderConditions, pageCondition);
			if(null!=allList&&!allList.isEmpty()) {  
				int size = allList.size();
				if(startIndex<size) {  //返回页面的数据
					if(endIndex>size) {  
						endIndex = size;
						for (int i = startIndex; i < endIndex; i++) {
							list.add(allList.get(i));
						}
					}
				}

				// 构建付款组织和收款方
				List<PayOriginazationDTO> payOriginazationList = respDTO.getPayOriginazationList();
				List<Integer> payOriginazationIdList = new ArrayList<Integer>(); // 判断付款组织id是否重复
				List<PayeeObjectDTO> payeeObjectList = respDTO.getPayeeObjectList();
				List<Integer> payeeObjectIdList = new ArrayList<Integer>(); // 判断收款方id是否重复
				Iterator<FinancePaymentESDTO> iterator = allList.iterator();
				PayOriginazationDTO payOriginazationDTO = null;
				PayeeObjectDTO payeeObjectDTO = null;
				while (iterator.hasNext()) {
					FinancePaymentESDTO next = iterator.next();
					Integer payOriginazationId = next.getPayOriginazationId(); // 付款组织
					Integer payeeObjectId = next.getPayeeObjectId(); // 收款方id
					// 添加付款组织
					if (!payOriginazationIdList.contains(payOriginazationId)) {
						payOriginazationIdList.add(payOriginazationId);
						payOriginazationDTO = new PayOriginazationDTO();
						payOriginazationDTO.setPayOriginazationId(payOriginazationId);
						payOriginazationDTO.setPayOriginazationName(next.getPayOriginazation());
						payOriginazationList.add(payOriginazationDTO);
					}
					// 添加收款方
					if (!payeeObjectIdList.contains(payeeObjectId)) {
						payeeObjectIdList.add(payeeObjectId);
						payeeObjectDTO = new PayeeObjectDTO();
						payeeObjectDTO.setPayeeObjectId(payeeObjectId);
						payeeObjectDTO.setPayeeObjectName(next.getPayeeObjectName());
						payeeObjectList.add(payeeObjectDTO);
					}
				}
				respDTO.setPayOriginazationList(payOriginazationList);
				respDTO.setPayeeObjectList(payeeObjectList);
			}
			
		} else {
			list = getList(searchConditions, orderConditions, pageCondition);
		}

		pagedList.setTotalCount(total);
		pagedList.setItems(list);
		pagedList.setCurrentPage(
				financePaymentReqDTO.getCurrentPage() == null ? 1 : financePaymentReqDTO.getCurrentPage());
		pagedList.setPageSize(financePaymentReqDTO.getPageSize() == null ? 10 : financePaymentReqDTO.getPageSize());

		respDTO.setFinancePaymentEsList(pagedList);
		return respDTO;
	}

	private Integer getTotal(FinancePaymentReqDTO financePaymentReqDTO) {
		List<SearchCondition> searchConditions = buildSearchConditions(financePaymentReqDTO);
		return new DefaultAbstractSearchAggregateExecutor<FinancePaymentESDTO>(esStatisticService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.payment();
			}
		}.count(searchConditions);
	}

	private List<FinancePaymentESDTO> getList(List<SearchCondition> searchConditions,
			List<OrderCondition> orderConditions, PageCondition pageCondition) {
		return new DefaultAbstractSearchQueryExecutor<FinancePaymentESDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.payment();
			}
		}.list(searchConditions, orderConditions, pageCondition);
	}

	private List<SearchCondition> buildSearchConditions(FinancePaymentReqDTO financePaymentReqDTO) {
		List<SearchCondition> list = new ArrayList<>();
		//状态
		if(0 != financePaymentReqDTO.getGrantStatus()) {
			SearchCondition grantStatusCondition = SearchConditionUtils.newEqualCondition("grantStatus",
					financePaymentReqDTO.getGrantStatus());
			list.add(grantStatusCondition);
		}
		//付款组织id
		if (null != financePaymentReqDTO.getPayOriginazationId()) {
			SearchCondition payOriginazationCondition = SearchConditionUtils.newEqualCondition("payOriginazationId",
					financePaymentReqDTO.getPayOriginazationId());
			list.add(payOriginazationCondition);
		}
		//收款方id
		if (null != financePaymentReqDTO.getPayeeObjectId()) {
			SearchCondition payeeObjectCondition = SearchConditionUtils.newEqualCondition("payeeObjectId",
					financePaymentReqDTO.getPayeeObjectId());
			list.add(payeeObjectCondition);
		}
		//收款方名称
		if ( StringUtils.isNotEmpty(financePaymentReqDTO.getPayeeObjectName())) {
			SearchCondition payeeObjectCondition = SearchConditionUtils.newLikeCondition("payeeObjectName",
					financePaymentReqDTO.getPayeeObjectName());
			list.add(payeeObjectCondition);
		}
        //时间
        String fieldName = "";
        //1.发放 2.录单 3.签收 4.收银）
        if (financePaymentReqDTO.getTimeType() == 1) {
            fieldName = "grantTime";
        } else if (financePaymentReqDTO.getTimeType() == 2){
            fieldName = "waybillCreateTime";
        } else if (financePaymentReqDTO.getTimeType() == 3){
            fieldName = "signTime";
        } else if (financePaymentReqDTO.getTimeType() == 4){
            fieldName = "confirmReceiptTime";
        }
		if (StringUtil.isNotBlank(fieldName) &&
                null != financePaymentReqDTO.getStartTime() && null != financePaymentReqDTO.getEndTime()
				&& financePaymentReqDTO.getStartTime() > 0 && financePaymentReqDTO.getEndTime() > 0) {
			SearchCondition signTimeCondition = SearchConditionUtils.newBetweenAndCondition(fieldName,
					financePaymentReqDTO.getStartTime(), financePaymentReqDTO.getEndTime());
			list.add(signTimeCondition);
		}
		//付款单号
		if(StringUtils.isNotBlank(financePaymentReqDTO.getPayCode())) {
			SearchCondition payCodeCondition = SearchConditionUtils.newLikeCondition("payCode",
					financePaymentReqDTO.getPayCode());
			list.add(payCodeCondition);
		}
		//运单号
		if(StringUtils.isNotBlank(financePaymentReqDTO.getSourceNumber())) {
			SearchCondition sourceCodeCondition = SearchConditionUtils.newLikeCondition("code",
					financePaymentReqDTO.getSourceNumber());
			list.add(sourceCodeCondition);
		}
		
		//公司
        if (null != financePaymentReqDTO.getCompanyId() && financePaymentReqDTO.getCompanyId() > 0) { // 公司ID
        	SearchCondition companyIdCondition = SearchConditionUtils.newEqualCondition("companyId",
        			financePaymentReqDTO.getCompanyId());
            list.add(companyIdCondition);
		}
//
//		// 发货网点ID-单据网点
		if (null != financePaymentReqDTO.getCurDotId() && financePaymentReqDTO.getCurDotId()> 0) {
			SearchCondition invoiceOrgIdsCondition = SearchConditionUtils.newEqualCondition("invoiceOrgId",
					financePaymentReqDTO.getCurDotId());
			list.add(invoiceOrgIdsCondition);
		}
		
		return list;
	}

	private PageCondition buildPageCondition(FinancePaymentReqDTO financePaymentReqDTO) {
		PageCondition pageCondition = new PageCondition();
		pageCondition.setCurrentPage(
				financePaymentReqDTO.getCurrentPage() == null ? 1 : financePaymentReqDTO.getCurrentPage());
		pageCondition.setPageSize(financePaymentReqDTO.getPageSize() == null ? 10 : financePaymentReqDTO.getPageSize());
		return pageCondition;
	}

	private List<OrderCondition> buildOrderCondition() {
		List<OrderCondition> orderConditionList = new ArrayList<OrderCondition>();
		OrderCondition orderCondition = new OrderCondition();
		orderCondition.setFieldName("createTime");
		orderCondition.setOrderCondition(SortEnum.DESC);
		orderConditionList.add(orderCondition);
		return orderConditionList;
	}
}
