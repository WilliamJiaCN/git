package com.hivescm.tms.finance.server.service.finance.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.hivescm.tms.api.dto.es.finance.component.FinancePaymentESDTO;
import com.hivescm.tms.api.dto.es.finance.request.PaymentReqDTO;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.finance.PaymentService;
import com.hivescm.tms.utils.RedisUserInfoUtils;

@Service
public class PaymentServiceImpl implements PaymentService {
	private static Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
	@Autowired
	private RedisUserInfoUtils redisUserInfoUtils;
	@Autowired
	private ESSearchService eSSearchService;

	@Override
	public DataResult<List<FinancePaymentESDTO>> getPaymentList(PaymentReqDTO paymentReqDTO) {
		DataResult<List<FinancePaymentESDTO>> result = new DataResult<>();
		try {
			List<FinancePaymentESDTO> resp = new ArrayList<>();
			if (paymentReqDTO != null) {

				// 当付款单不为null时
				List<SearchCondition> scs = new ArrayList<>();
				// 查询条件
				Map<String, Object> searchMap = this.getPaymentSearchCondition(scs, paymentReqDTO);
				// 查询结果
				resp = this.getResp(searchMap);

				result.setResult(resp);
			} else {
				// 当付款单不为null时
				List<SearchCondition> scs = new ArrayList<>();
				// 查询条件
				Map<String, Object> searchMap = this.getPaymentSearchCondition(scs, paymentReqDTO);
				// 查询结果
				resp = this.getResp(searchMap);

				result.setResult(resp);
			}
			result.setResult(resp);

		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_PAYMENT_SEARCH,
					"getPaymentList has error:recode={" + paymentReqDTO + "},error={" + e.getLocalizedMessage() + "}");
			logger.error(ex.getMessage(), e);
		}
		return result;
	}

	private List<FinancePaymentESDTO> getResp(Map<String, Object> map) {
		List<SearchCondition> scs = (List<SearchCondition>) map.get("searchCondition");
		PageCondition pageCondition = (PageCondition) map.get("pageCondition");
		List<OrderCondition> orderConditions = (List<OrderCondition>) map.get("orderConditions");
		// 查询收款单
		List<FinancePaymentESDTO> paymentList = new DefaultAbstractSearchQueryExecutor<FinancePaymentESDTO>(
				eSSearchService) {

			@Override
			public EsConfig getConfig() {
				return EsConfig.payment();
			}

		}.list(scs, orderConditions, pageCondition);
		return paymentList;
	}

	private Map<String, Object> getPaymentSearchCondition(List<SearchCondition> scs, PaymentReqDTO paymentReqDTO) {
		/*
		 * ArrayList<String> clientList =
		 * redisUserInfoUtils.getClient(paymentReqDTO.getUserId().toString());
		 * SearchCondition userId = new
		 * SearchCondition.Builder().setFieldName("userId")
		 * .setFeldValues(clientList.toArray(new
		 * String[0])).setConditionExpression(ConditionExpressionEnum.IN)
		 * .build(); scs.add(userId);
		 */
		// 收款方
		if (null != paymentReqDTO.getPayeeObject()) {
			SearchCondition payeeObject = new SearchCondition.Builder().setFieldName("payeeObject")
					.setConditionExpression(ConditionExpressionEnum.EQUAL)
					.setSingleValue(paymentReqDTO.getPayeeObject()).build();
			scs.add(payeeObject);
		}
		// 签收时间
		if (null != paymentReqDTO.getStartTime() && null != paymentReqDTO.getEndTime()
				&& paymentReqDTO.getStartTime() > 0 && paymentReqDTO.getEndTime() > 0) {
			SearchCondition signTime = new SearchCondition.Builder().setFieldName("signTime")
					.setConditionExpression(ConditionExpressionEnum.BETWEEN_AND)
					.setMinValue(paymentReqDTO.getStartTime().toString())
					.setMxValue(paymentReqDTO.getEndTime().toString()).build();
			scs.add(signTime);
		}

		// 发放时间
		if (null != paymentReqDTO.getStartTime() && null != paymentReqDTO.getEndTime()
				&& paymentReqDTO.getStartTime() > 0 && paymentReqDTO.getEndTime() > 0) {
			SearchCondition grantTime = new SearchCondition.Builder().setFieldName("grantTime")
					.setConditionExpression(ConditionExpressionEnum.BETWEEN_AND)
					.setMinValue(paymentReqDTO.getStartTime().toString())
					.setMxValue(paymentReqDTO.getEndTime().toString()).build();
			scs.add(grantTime);
		}
		// 确认收银时间
		if (null != paymentReqDTO.getStartTime() && null != paymentReqDTO.getEndTime()
				&& paymentReqDTO.getStartTime() > 0 && paymentReqDTO.getEndTime() > 0) {
			SearchCondition confirmReceiptTime = new SearchCondition.Builder().setFieldName("confirmReceiptTime")
					.setConditionExpression(ConditionExpressionEnum.BETWEEN_AND)
					.setMinValue(paymentReqDTO.getStartTime().toString())
					.setMxValue(paymentReqDTO.getEndTime().toString()).build();
			scs.add(confirmReceiptTime);
		}

		// 付款单号
		if (null != paymentReqDTO.getPId() && paymentReqDTO.getPId() > 0) {
			SearchCondition pId = new SearchCondition.Builder().setFieldName("pId")
					.setConditionExpression(ConditionExpressionEnum.EQUAL)
					.setSingleValue(paymentReqDTO.getPId().toString()).build();
			scs.add(pId);
		}
		if (null != paymentReqDTO.getPCode()) {
			SearchCondition pCode = new SearchCondition.Builder().setFieldName("pCode")
					.setConditionExpression(ConditionExpressionEnum.EQUAL).setSingleValue(paymentReqDTO.getPCode())
					.build();
			scs.add(pCode);
		}
		// 运单号
		if (null != paymentReqDTO.getSourceCode()) {
			SearchCondition sourceCode = new SearchCondition.Builder().setFieldName("sourceCode")
					.setConditionExpression(ConditionExpressionEnum.EQUAL).setSingleValue(paymentReqDTO.getSourceCode())
					.build();
			scs.add(sourceCode);
		}

		if (null != paymentReqDTO.getGrantStatus() && paymentReqDTO.getGrantStatus().size() > 0) {
			int size = paymentReqDTO.getGrantStatus().size();
			String[] arrStatus = new String[size];
			for (int i = 0; i < size; i++) {
				arrStatus[i] = String.valueOf(paymentReqDTO.getGrantStatus().get(i).getType());
			}
			// -> 发放状态
			SearchCondition grantStatus = new SearchCondition.Builder().setFieldName("grantStatus")
					.setConditionExpression(ConditionExpressionEnum.IN).setFeldValues(arrStatus).build();
			scs.add(grantStatus);
		}

		List<OrderCondition> orderConditionList = new ArrayList<OrderCondition>();
		OrderCondition orderCondition = new OrderCondition();
		orderCondition.setFieldName("companyId");// todo
		orderCondition.setOrderCondition(SortEnum.DESC);
		orderConditionList.add(orderCondition);

		PageCondition pageCondition = new PageCondition();
		pageCondition.setCurrentPage(paymentReqDTO.getCurrentPage());
		pageCondition.setPageSize(paymentReqDTO.getPageSize());

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchCondition", scs);
		map.put("pageCondition", pageCondition);
		map.put("orderConditions", orderConditionList);
		return map;

	}

	@Override
	public DataResult<List<FinancePaymentESDTO>> getNoPaymentList(PaymentReqDTO paymentReqDTO) {
		DataResult<List<FinancePaymentESDTO>> result = new DataResult<>();
		try {
			List<FinancePaymentESDTO> resp = new ArrayList<>();
			// 当付款单不为null时
			List<SearchCondition> scs = new ArrayList<>();
			// 查询条件
			Map<String, Object> searchMap = this.getSearchCondition(scs, paymentReqDTO);
			// 查询结果
			resp = this.getResp(searchMap);
			result.setResult(resp);

			result.setResult(resp);
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_PAYMENT_SEARCH,
					"getNoPaymentList has error:recode={" + paymentReqDTO + "},error={" + e.getLocalizedMessage()
							+ "}");
			logger.error(ex.getMessage(), e);
		}
		return result;
	}

	private Map<String, Object> getSearchCondition(List<SearchCondition> scs, PaymentReqDTO paymentReqDTO) {
		/*
		 * ArrayList<String> clientList =
		 * redisUserInfoUtils.getClient(paymentReqDTO.getUserId().toString());
		 * SearchCondition userId = new
		 * SearchCondition.Builder().setFieldName("userId")
		 * .setFeldValues(clientList.toArray(new
		 * String[0])).setConditionExpression(ConditionExpressionEnum.IN)
		 * .build(); scs.add(userId);
		 */
		// 运单号
		if (null != paymentReqDTO.getSourceCode()) {
			SearchCondition sourceCode = new SearchCondition.Builder().setFieldName("sourceCode")
					.setConditionExpression(ConditionExpressionEnum.EQUAL).setSingleValue(paymentReqDTO.getSourceCode())
					.build();
			scs.add(sourceCode);
		}
		if (null != paymentReqDTO.getGrantStatus() && paymentReqDTO.getGrantStatus().size() > 0) {
			int size = paymentReqDTO.getGrantStatus().size();
			String[] arrStatus = new String[size];
			for (int i = 0; i < size; i++) {
				arrStatus[i] = String.valueOf(paymentReqDTO.getGrantStatus().get(i).getType());
			}
			// -> 发放状态
			SearchCondition grantStatus = new SearchCondition.Builder().setFieldName("grantStatus")
					.setConditionExpression(ConditionExpressionEnum.IN).setFeldValues(arrStatus).build();
			scs.add(grantStatus);
		}
		PageCondition pageCondition = new PageCondition();
		pageCondition.setCurrentPage(paymentReqDTO.getCurrentPage());
		pageCondition.setPageSize(paymentReqDTO.getPageSize());

		boolean orderConditions = new ArrayList<OrderCondition>().add(new OrderCondition());

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("searchCondition", scs);
		map.put("pageCondition", pageCondition);
		map.put("orderConditions", orderConditions);
		return map;

	}

	@Override
	public DataResult<List<FinancePaymentESDTO>> getAlreadyPaymentList(PaymentReqDTO paymentReqDTO) {
		DataResult<List<FinancePaymentESDTO>> result = new DataResult<>();
		try {
			List<FinancePaymentESDTO> resp = new ArrayList<>();
			// 当付款单不为null时
			List<SearchCondition> scs = new ArrayList<>();
			// 查询条件
			Map<String, Object> searchMap = this.getPaymentSearchCondition(scs, paymentReqDTO);
			// 查询结果
			resp = this.getResp(searchMap);

			result.setResult(resp);
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_PAYMENT_SEARCH,
					"getAlreadyPaymentList has error:recode={" + paymentReqDTO + "},error={" + e.getLocalizedMessage()
							+ "}");
			logger.error(ex.getMessage(), e);
		}
		return result;
	}

}
