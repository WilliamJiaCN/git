package com.hivescm.tms.finance.server.service.finance.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.finance.component.FinanceReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.ReceiptReqDTO;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.finance.FinanceService;

@Service
public class FinanceServiceImpl implements FinanceService {
	private static Logger logger = LoggerFactory.getLogger(FinanceServiceImpl.class);

	@Autowired
	private ESSearchService eSSearchService;

	@Override
	public DataResult<List<FinanceReceiptEsDTO>> getReceiptList(ReceiptReqDTO receiptReqDTO) {
		DataResult<List<FinanceReceiptEsDTO>> result = new DataResult<>();
		try {
			List<FinanceReceiptEsDTO> resp = new ArrayList<>();
			if (receiptReqDTO != null) {

				// 当收款单不为null时
				List<SearchCondition> scs = new ArrayList<>();
				// 查询条件
				Map<String, Object> searchMap = this.getFinanceSearchCondition(scs, receiptReqDTO);
				// 查询结果
				resp = this.getResp(searchMap);
				result.setResult(resp);
			} else {
				// 当收款单为null时
				List<SearchCondition> scs = new ArrayList<>();
				// 查询条件
				Map<String, Object> searchMap = this.getFinanceSearchCondition(scs, receiptReqDTO);
				// 查询结果
				resp = this.getResp(searchMap);
				result.setResult(resp);
			}
			result.setResult(resp);
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_FINANCE_SEARCH,
					"getReceiptList has error:recode={" + receiptReqDTO + "},error={" + e.getLocalizedMessage() + "}");
			logger.error(ex.getMessage(), e);
		}
		return result;
	}

	private List<FinanceReceiptEsDTO> getResp(Map<String, Object> map) {
		List<SearchCondition> scs = (List<SearchCondition>) map.get("searchCondition");
		PageCondition pageCondition = (PageCondition) map.get("pageCondition");
		List<OrderCondition> orderConditions = (List<OrderCondition>) map.get("orderConditions");
		// 查询收款单
		List<FinanceReceiptEsDTO> financeList = new DefaultAbstractSearchQueryExecutor<FinanceReceiptEsDTO>(
				eSSearchService) {

			@Override
			public EsConfig getConfig() {
				return EsConfig.finance();
			}

		}.list(scs, orderConditions, pageCondition);

		return financeList;
	}

	/**
	 * 查询条件
	 * 
	 * @param scs
	 * @param receiptReqDTO
	 * @return
	 */
	private Map<String, Object> getFinanceSearchCondition(List<SearchCondition> scs, ReceiptReqDTO receiptReqDTO) {
		// 司机姓名
		if (StringUtils.isNotEmpty(receiptReqDTO.getDriverName())
				) {
			SearchCondition driverName = new SearchCondition.Builder().setFieldName("driverName")
					.setConditionExpression(ConditionExpressionEnum.EQUAL).setSingleValue(receiptReqDTO.getDriverName())
					.build();
			scs.add(driverName);
		}
		// 收款日期
		if (null != receiptReqDTO.getStartCollectionTime() && null != receiptReqDTO.getEndCollectionTime()
				&& receiptReqDTO.getStartCollectionTime() > 0 && receiptReqDTO.getEndCollectionTime() > 0) {
			SearchCondition createTime = new SearchCondition.Builder().setFieldName("createTime")
					.setConditionExpression(ConditionExpressionEnum.BETWEEN_AND)
					.setMinValue(receiptReqDTO.getStartCollectionTime().toString())
					.setMxValue(receiptReqDTO.getEndCollectionTime().toString()).build();
			scs.add(createTime);
		}
		// 签收日期
		if (null != receiptReqDTO.getStartSignTime() && null != receiptReqDTO.getEndSignTime()
				&& receiptReqDTO.getStartSignTime() > 0 && receiptReqDTO.getEndSignTime() > 0) {
			SearchCondition billDate = new SearchCondition.Builder().setFieldName("billDate")
					.setConditionExpression(ConditionExpressionEnum.BETWEEN_AND)
					.setMinValue(receiptReqDTO.getStartSignTime().toString())
					.setMxValue(receiptReqDTO.getEndSignTime().toString()).build();
			scs.add(billDate);
		}
		// 确认收银日期
		if (null != receiptReqDTO.getStartConfirmReceiptTime() && null != receiptReqDTO.getEndConfirmReceiptTime()
				&& receiptReqDTO.getStartConfirmReceiptTime() > 0 && receiptReqDTO.getEndConfirmReceiptTime() > 0) {
			SearchCondition confirmReceiptTime = new SearchCondition.Builder().setFieldName("confirmReceiptTime")
					.setConditionExpression(ConditionExpressionEnum.BETWEEN_AND)
					.setMinValue(receiptReqDTO.getStartConfirmReceiptTime().toString())
					.setMxValue(receiptReqDTO.getEndConfirmReceiptTime().toString()).build();
			scs.add(confirmReceiptTime);
		}
		// 付款方
		if (StringUtils.isNotEmpty(receiptReqDTO.getPayObject())) {
			SearchCondition payObject = new SearchCondition.Builder().setFieldName("payObject")
					.setConditionExpression(ConditionExpressionEnum.EQUAL).setSingleValue(receiptReqDTO.getPayObject())
					.build();
			scs.add(payObject);
		}

		// 收款单号
		if (null != receiptReqDTO.getRiId() && receiptReqDTO.getRiId() > 0) {
			SearchCondition riId = new SearchCondition.Builder().setFieldName("riId")
					.setConditionExpression(ConditionExpressionEnum.EQUAL)
					.setSingleValue(receiptReqDTO.getRiId().toString()).build();
			scs.add(riId);
		}
		if (StringUtils.isNotEmpty(receiptReqDTO.getCode())  ) {
			SearchCondition code = new SearchCondition.Builder().setFieldName("code")
					.setConditionExpression(ConditionExpressionEnum.EQUAL).setSingleValue(receiptReqDTO.getCode())
					.build();
			scs.add(code);
		}
		if (null != receiptReqDTO.getCollectStatus() && receiptReqDTO.getCollectStatus().size() > 0) {
			int size = receiptReqDTO.getCollectStatus().size();
			String[] arrStatus = new String[size];
			for (int i = 0; i < size; i++) {
				arrStatus[i] = String.valueOf(receiptReqDTO.getCollectStatus().get(i).getType());
			}
			// -> 收银状态
			SearchCondition collectStatus = new SearchCondition.Builder().setFieldName("collectStatus")
					.setConditionExpression(ConditionExpressionEnum.IN).setFeldValues(arrStatus).build();
			scs.add(collectStatus);
		}
		// 公司ID
		if (null != receiptReqDTO.getCompanyId() && receiptReqDTO.getCompanyId() > 0) {
			// SearchCondition invoiceOrgCondition = new
			// SearchCondition.Builder().setFieldName("companyId")
			// .setSingleValue(dispatcherQueryReqDTO.getCompanyId().toString())
			// .setConditionExpression(ConditionExpressionEnum.EQUAL).build();
			// searchConditionList.add(invoiceOrgCondition);
			// searchConditionUtils.addEqualCondition("companyId",
			// dispatcherQueryReqDTO.getCompanyId());
			// -> 收银状态
			SearchCondition collectCompany = new SearchCondition.Builder().setFieldName("companyId")
					.setConditionExpression(ConditionExpressionEnum.EQUAL).setSingleValue(receiptReqDTO.getCompanyId()).build();
			scs.add(collectCompany);
		}

		// 派车网点-当前网点
		if (null != receiptReqDTO.getBranchId() && receiptReqDTO.getBranchId() > 0) {
			// searchConditionUtils.addEqualCondition("invoiceOrgId",
			// dispatcherWaybillQueryReqEsDTO.getBranchId());
			SearchCondition collectBranch = new SearchCondition.Builder().setFieldName("invoiceOrgId")
					.setConditionExpression(ConditionExpressionEnum.EQUAL).setSingleValue(receiptReqDTO.getBranchId()).build();
			scs.add(collectBranch);
		}

		PageCondition pageCondition = new PageCondition();
		pageCondition.setCurrentPage(receiptReqDTO.getCurrentPage());
		pageCondition.setPageSize(receiptReqDTO.getPageSize());

		List<OrderCondition> orderConditionList = new ArrayList<OrderCondition>();
		OrderCondition orderCondition = new OrderCondition();
		orderCondition.setFieldName("companyId");// todo
		orderCondition.setOrderCondition(SortEnum.DESC);
		orderConditionList.add(orderCondition);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchCondition", scs);
		map.put("pageCondition", pageCondition);
		map.put("orderConditions", orderConditionList);
		return map;
	}

	@Override
	public DataResult<List<FinanceReceiptEsDTO>> getNoReceiptList(ReceiptReqDTO receiptReqDTO) {
		DataResult<List<FinanceReceiptEsDTO>> result = new DataResult<>();
		try {
			List<SearchCondition> scs = new ArrayList<>();
			List<FinanceReceiptEsDTO> resp = new ArrayList<>();
			// ->组装查询条件
			Map<String, Object> searchMap = this.getSearchCondition(scs, receiptReqDTO);
			// ->查询结果
			resp = this.getResp(searchMap);
			result.setResult(resp);
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_FINANCE_SEARCH,
					"getNoReceiptList has error: record={" + receiptReqDTO + "}, error={" + e.getLocalizedMessage()
							+ "}");
			logger.error(ex.getMessage(), e);
			throw ex;
		}
		return result;
	}

	private HashMap<String, Object> getSearchCondition(List<SearchCondition> scs, ReceiptReqDTO receiptReqDTO) {
		// 司机姓名
		if (null != receiptReqDTO.getDriverName()) {
			SearchCondition driverName = new SearchCondition.Builder().setFieldName("driverName")
					.setConditionExpression(ConditionExpressionEnum.EQUAL).setSingleValue(receiptReqDTO.getDriverName())
					.build();
			scs.add(driverName);
		}
		// 收款日期
		if (null != receiptReqDTO.getStartCollectionTime() && null != receiptReqDTO.getEndCollectionTime()

				&& receiptReqDTO.getStartCollectionTime() > 0 && receiptReqDTO.getEndCollectionTime() > 0) {
			SearchCondition createTime = new SearchCondition.Builder().setFieldName("createTime")
					.setConditionExpression(ConditionExpressionEnum.BETWEEN_AND)
					.setMinValue(receiptReqDTO.getStartCollectionTime().toString())
					.setMxValue(receiptReqDTO.getEndCollectionTime().toString()).build();
			scs.add(createTime);
		}
		// 收银时间
		if (null != receiptReqDTO.getStartConfirmReceiptTime() && null != receiptReqDTO.getEndConfirmReceiptTime()

				&& receiptReqDTO.getStartConfirmReceiptTime() > 0 && receiptReqDTO.getEndConfirmReceiptTime() > 0) {
			SearchCondition confirmReceiptTime = new SearchCondition.Builder().setFieldName("confirmReceiptTime")
					.setConditionExpression(ConditionExpressionEnum.BETWEEN_AND)
					.setMinValue(receiptReqDTO.getStartConfirmReceiptTime().toString())
					.setMxValue(receiptReqDTO.getEndConfirmReceiptTime().toString()).build();
			scs.add(confirmReceiptTime);
		}
		// 签收时间
		if (null != receiptReqDTO.getStartSignTime() && null != receiptReqDTO.getEndSignTime()

				&& receiptReqDTO.getStartSignTime() > 0 && receiptReqDTO.getEndSignTime() > 0) {
			SearchCondition billDate = new SearchCondition.Builder().setFieldName("billDate")
					.setConditionExpression(ConditionExpressionEnum.BETWEEN_AND)
					.setMinValue(receiptReqDTO.getStartSignTime().toString())
					.setMxValue(receiptReqDTO.getEndSignTime().toString()).build();
			scs.add(billDate);
		}

		// 派车批次号
		if (null != receiptReqDTO.getBatchCode()) {
			SearchCondition batchCode = new SearchCondition.Builder().setFieldName("batchCode")
					.setConditionExpression(ConditionExpressionEnum.EQUAL).setSingleValue(receiptReqDTO.getBatchCode())
					.build();
			scs.add(batchCode);
		}

		// 签收单号
		if (null != receiptReqDTO.getSourceNumber()) {
			SearchCondition sourceNumber = new SearchCondition.Builder().setFieldName("sourceNumber")
					.setConditionExpression(ConditionExpressionEnum.EQUAL)
					.setSingleValue(receiptReqDTO.getSourceNumber()).build();
			scs.add(sourceNumber);
		}
		if (null != receiptReqDTO.getCollectStatus() && receiptReqDTO.getCollectStatus().size() > 0) {
			int size = receiptReqDTO.getCollectStatus().size();
			String[] arrStatus = new String[size];
			for (int i = 0; i < size; i++) {
				arrStatus[i] = String.valueOf(receiptReqDTO.getCollectStatus().get(i).getType());
			}
			// -> 收银状态
			SearchCondition scStatus = new SearchCondition.Builder().setFieldName("status")
					.setConditionExpression(ConditionExpressionEnum.IN).setFeldValues(arrStatus).build();
			scs.add(scStatus);
		}
		PageCondition pageCondition = new PageCondition();
		pageCondition.setCurrentPage(receiptReqDTO.getCurrentPage());
		pageCondition.setPageSize(receiptReqDTO.getPageSize());

		List<OrderCondition> orderConditionList = new ArrayList<OrderCondition>();
		OrderCondition orderCondition = new OrderCondition();
		orderCondition.setFieldName("companyId");// todo
		orderCondition.setOrderCondition(SortEnum.DESC);
		orderConditionList.add(orderCondition);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchCondition", scs);
		map.put("pageCondition", pageCondition);
		map.put("orderConditions", orderConditionList);
		return map;

	}

	@Override
	public DataResult<List<FinanceReceiptEsDTO>> getAlreadyReceiptList(ReceiptReqDTO receiptReqDTO) {
		DataResult<List<FinanceReceiptEsDTO>> result = new DataResult<>();
		try {
			List<SearchCondition> scs = new ArrayList<>();
			List<FinanceReceiptEsDTO> resp = new ArrayList<>();
			// ->组装查询条件
			Map<String, Object> hashMap = this.getFinanceSearchCondition(scs, receiptReqDTO);
			// ->查询结果
			resp = this.getResp(hashMap);
			result.setResult(resp);
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_FINANCE_SEARCH,
					"getAlreadyReceiptList has error: record={" + receiptReqDTO + "}, error={" + e.getLocalizedMessage()
							+ "}");
			logger.error(ex.getMessage(), e);
			throw ex;
		}
		return result;
	}
	
	@Override
	public FinanceReceiptEsDTO getByID(Long receiptId) {
		List<SearchCondition> searchConditions = SearchConditionUtils.start()
                .addCondition("id", receiptId, ConditionExpressionEnum.EQUAL).end();
        return new DefaultAbstractSearchQueryExecutor<FinanceReceiptEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.finance();
            }
        }.get(searchConditions);
	}
}
