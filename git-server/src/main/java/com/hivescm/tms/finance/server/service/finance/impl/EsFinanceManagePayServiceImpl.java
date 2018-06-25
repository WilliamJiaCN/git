package com.hivescm.tms.finance.server.service.finance.impl;

import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.escenter.service.ESStatisticService;
import com.hivescm.framework.common.utils.StringUtils;
import com.hivescm.framework.elasticsearch.executor.impl.*;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.fare.request.BillInfoReqDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManagePayEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.enums.finance.ExpensesEnum;
import com.hivescm.tms.api.enums.finance.PaymentStatusEnum;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManagePayService;
import com.hivescm.tms.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author wenxiong.jia
 * @date 2018/4/27
 */
@Service
@Slf4j
public class EsFinanceManagePayServiceImpl implements EsFinanceManagePayService {

    @Autowired
    private ESSearchService esSearchService;

    @Autowired
	private ESStatisticService statisticService;

    @Override
    public Boolean insertEs(FinanceManagePayEsDTO financeManagePayEsDto) {
        boolean result = new DefaultAbstractSearchSaveExecutor<FinanceManagePayEsDTO>(esSearchService) {
            public EsConfig getConfig() {
                return EsConfig.financeManagePay();
            }
        }.execute(financeManagePayEsDto);
        return result;
    }

    @Override
    public Boolean insertBatchEs(List<FinanceManagePayEsDTO> financeManagePayEsDTOList) {
        return new DefaultAbstractSearchSaveExecutor<FinanceManagePayEsDTO>(esSearchService) {
            public EsConfig getConfig() {
                return EsConfig.financeManagePay();
            }
        }.execute(financeManagePayEsDTOList);
    }

	@Override
	public List<FinanceManagePayEsDTO> getEsList(FinanceManageListReqDTO financeManageListReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		utils = makeEsListSearchConditionUtils(utils, financeManageListReqDTO);
		List<SearchCondition> searchConditions=utils.end();
		 // 分页条件
		PageCondition pageCondition = new PageCondition();
		pageCondition.setCurrentPage(financeManageListReqDTO.getCurrentPage());
		pageCondition.setPageSize(financeManageListReqDTO.getPageSize());

		// 排序条件
		List<OrderCondition> orderConditions = new ArrayList<>();
		OrderCondition orderCondition = new OrderCondition();
		orderCondition.setFieldName("createTime");
		orderCondition.setOrderCondition(SortEnum.DESC);
		orderConditions.add(orderCondition);
        return new DefaultAbstractSearchQueryExecutor<FinanceManagePayEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManagePay();
            }
        }.list(searchConditions,orderConditions,pageCondition);
	}

	@Override
	public Integer getEsListCount(FinanceManageListReqDTO financeManageListReqDTO) {
		Integer count = 0;
		SearchConditionUtils utils = SearchConditionUtils.start();
		utils = makeEsListSearchConditionUtils(utils, financeManageListReqDTO);
		List<SearchCondition> searchConditions=utils.end();
		count = new DefaultAbstractSearchAggregateExecutor<FinanceManagePayEsDTO>(statisticService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.financeManagePay();
			}
		}.count(searchConditions);
		return count;
	}

	@Override
	public List<FinanceManagePayEsDTO> getEsListForPay(FinanceManageListForPayReqDTO financeManageListForPayReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		if(financeManageListForPayReqDTO.getDateStart()!=null) {
			utils.addCondition("businessDate", financeManageListForPayReqDTO.getDateStart(),financeManageListForPayReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
		}
		if(CollectionUtils.isNotEmpty(financeManageListForPayReqDTO.getSheetType())) {
			utils.addCondition("sheetType", financeManageListForPayReqDTO.getSheetType().toArray(),ConditionExpressionEnum.IN);
		}
		if(CollectionUtils.isNotEmpty(financeManageListForPayReqDTO.getFeeType())) {
			List<String> collect = financeManageListForPayReqDTO.getFeeType().stream().map(s->{return ExpensesEnum.getTypeByFeeType(s).getDocType();}).collect(Collectors.toList());
			utils.addCondition("feeType",collect.toArray(),ConditionExpressionEnum.IN);
		}
		if(StringUtils.isNotBlank(financeManageListForPayReqDTO.getDataSourceSheetId())) {
			utils.addCondition("dataSourceSheetId", financeManageListForPayReqDTO.getDataSourceSheetId(),ConditionExpressionEnum.LIKE);
		}
		if(CollectionUtils.isNotEmpty(financeManageListForPayReqDTO.getDataSourceSheetCodes())) {
			utils.addCondition("dataSourceSheetId", financeManageListForPayReqDTO.getDataSourceSheetCodes().toArray(),ConditionExpressionEnum.IN);
		}
		Integer [] payStatus = {2,3};
		utils.addCondition("paymentStatus",payStatus ,ConditionExpressionEnum.IN);
		utils.addCondition("paymentNetworkId",financeManageListForPayReqDTO.getOrgId() ,ConditionExpressionEnum.EQUAL);
		if(StringUtils.isNotBlank(financeManageListForPayReqDTO.getPayee())) {
			utils.addCondition("payee", financeManageListForPayReqDTO.getPayee(),ConditionExpressionEnum.LIKE);
		}
		List<SearchCondition> searchConditions=utils.end();
		return new DefaultAbstractSearchQueryExecutor<FinanceManagePayEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManagePay();
            }
        }.list(searchConditions);
	}

	@Override
	public List<FinanceManagePayEsDTO> getEsBySheetCode(Long id) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		utils.addCondition("id", id,ConditionExpressionEnum.EQUAL);
		Integer [] payStatus = {2,3};
		utils.addCondition("paymentStatus",payStatus ,ConditionExpressionEnum.IN);
		List<SearchCondition> searchConditions=utils.end();
		return new DefaultAbstractSearchQueryExecutor<FinanceManagePayEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManagePay();
            }
        }.list(searchConditions);
	}

	@Override
	public List<FinanceManagePayEsDTO> getCodeForPay(FinanceSheetCodeReqDTO financeSheetCodeReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		if(StringUtils.isNotBlank(financeSheetCodeReqDTO.getCode())) {
			utils.addCondition("dataSourceSheetId", financeSheetCodeReqDTO.getCode(), ConditionExpressionEnum.LIKE);
		}else {
			return null;
		}
		if(financeSheetCodeReqDTO.getIdList()!=null&&financeSheetCodeReqDTO.getIdList().size()>0) {
			utils.addCondition("id", financeSheetCodeReqDTO.getIdList().toArray(), ConditionExpressionEnum.NOT_IN);
		}
		Integer [] payStatus = {2,3};
		utils.addCondition("paymentStatus", payStatus,ConditionExpressionEnum.IN);
		utils.addCondition("paymentNetworkId",financeSheetCodeReqDTO.getOrgId() ,ConditionExpressionEnum.EQUAL);
		List<SearchCondition> searchConditions=utils.end();
		return new DefaultAbstractSearchQueryExecutor<FinanceManagePayEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.financeManagePay();
			}
		}.list(searchConditions);

	}


	private SearchConditionUtils makeEsListSearchConditionUtils(SearchConditionUtils utils,
			FinanceManageListReqDTO financeManageListReqDTO) {
		if(financeManageListReqDTO.getDateType()!= null&&financeManageListReqDTO.getDateStart()!=null
				&&financeManageListReqDTO.getDateEnd()!=null) {
			switch(financeManageListReqDTO.getDateType()) {
			case 0:
				utils.addCondition("businessDate", financeManageListReqDTO.getDateStart(),financeManageListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
				break;
			case 1:
				utils.addCondition("postTime", financeManageListReqDTO.getDateStart(),financeManageListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
				break;
			case 2:
				utils.addCondition("checkTime", financeManageListReqDTO.getDateStart(),financeManageListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
				break;
			}
		}
		if(CollectionUtils.isNotEmpty(financeManageListReqDTO.getSheetType())) {
			utils.addCondition("sheetType", financeManageListReqDTO.getSheetType().toArray(),ConditionExpressionEnum.IN);
		}
		if(CollectionUtils.isNotEmpty(financeManageListReqDTO.getFeeType())) {
			List<String> collect = financeManageListReqDTO.getFeeType().stream().map(s->{return ExpensesEnum.getTypeByFeeType(s).getDocType();}).collect(Collectors.toList());
			utils.addCondition("feeType",collect.toArray(),ConditionExpressionEnum.IN);
		}
		if(financeManageListReqDTO.getPaymentStatus()!=null) {
			utils.addEqualCondition("paymentStatus", financeManageListReqDTO.getPaymentStatus());
		}
		if(StringUtils.isNotBlank(financeManageListReqDTO.getDataSourceSheetId())) {
			utils.addCondition("dataSourceSheetId", financeManageListReqDTO.getDataSourceSheetId(),ConditionExpressionEnum.LIKE);
		}
		if(CollectionUtils.isNotEmpty(financeManageListReqDTO.getDataSourceSheetIdList())) {
			utils.addCondition("dataSourceSheetId", financeManageListReqDTO.getDataSourceSheetIdList().toArray(),ConditionExpressionEnum.IN);
		}
		if(financeManageListReqDTO.getBusinessNetworkIds()!=null&&financeManageListReqDTO.getBusinessNetworkIds().size()>0) {
			utils.addCondition("businessNetworkId", financeManageListReqDTO.getBusinessNetworkIds().toArray(),ConditionExpressionEnum.IN);
		}
		if(financeManageListReqDTO.getPaymentNetworkIds()!=null&&financeManageListReqDTO.getPaymentNetworkIds().size()>0) {
			utils.addCondition("paymentNetworkId", financeManageListReqDTO.getPaymentNetworkIds().toArray(),ConditionExpressionEnum.IN);
		}
		if(StringUtils.isNotBlank(financeManageListReqDTO.getPayee())) {
			utils.addCondition("payee", financeManageListReqDTO.getPayee(),ConditionExpressionEnum.LIKE);
		}
		if(StringUtils.isNotBlank(financeManageListReqDTO.getInvoiceCompanyName())) {
			utils.addCondition("invoiceCompanyName", financeManageListReqDTO.getInvoiceCompanyName(),ConditionExpressionEnum.LIKE);
		}
		if(StringUtils.isNotBlank(financeManageListReqDTO.getInvoiceUserName())) {
			utils.addCondition("invoiceUserName", financeManageListReqDTO.getInvoiceUserName(),ConditionExpressionEnum.LIKE);
		}
		if(StringUtils.isNotBlank(financeManageListReqDTO.getInvoiceUserMobile())) {
			utils.addCondition("invoiceUserMobile", financeManageListReqDTO.getInvoiceUserMobile(),ConditionExpressionEnum.LIKE);
		}
		if(StringUtils.isNotBlank(financeManageListReqDTO.getInvoiceUserTel())) {
			utils.addCondition("invoiceUserTel", financeManageListReqDTO.getInvoiceUserTel(),ConditionExpressionEnum.LIKE);
		}
		if(StringUtils.isNotBlank(financeManageListReqDTO.getReceiptCompanyName())) {
			utils.addCondition("receiptCompanyName", financeManageListReqDTO.getReceiptCompanyName(),ConditionExpressionEnum.LIKE);
		}
		if(StringUtils.isNotBlank(financeManageListReqDTO.getReceiptUserName())) {
			utils.addCondition("receiptUserName", financeManageListReqDTO.getReceiptUserName(),ConditionExpressionEnum.LIKE);
		}
		if(StringUtils.isNotBlank(financeManageListReqDTO.getReceiptUserMobile())) {
			utils.addCondition("receiptUserMobile", financeManageListReqDTO.getReceiptUserMobile(),ConditionExpressionEnum.LIKE);
		}
		if(StringUtils.isNotBlank(financeManageListReqDTO.getReceiptUserTel())) {
			utils.addCondition("receiptUserTel", financeManageListReqDTO.getReceiptUserTel(),ConditionExpressionEnum.LIKE);
		}
		if(StringUtils.isNotBlank(financeManageListReqDTO.getVehicleNo())) {
			utils.addCondition("vehicleNo", financeManageListReqDTO.getVehicleNo(),ConditionExpressionEnum.LIKE);
		}
		if(StringUtils.isNotBlank(financeManageListReqDTO.getDriverName())) {
			utils.addCondition("driverName", financeManageListReqDTO.getDriverName(),ConditionExpressionEnum.LIKE);
		}
		if(financeManageListReqDTO.getCompanyId()!=null) {
			utils.addEqualCondition("companyId", financeManageListReqDTO.getCompanyId());
		}
		return utils;
	}

	@Override
	public Boolean checkFeeBatch(List<FinanceManagePayEsDTO> esFinance) {
		 return new DefaultAbstractSearchUpdateExecutor<FinanceManagePayEsDTO>(esSearchService){
	            @Override
	            public EsConfig getConfig(){ return EsConfig.financeManagePay();}
	        }.execute(esFinance);
	}

	@Override
	public List<FinanceManagePayEsDTO> getEsListByIds(List<Long> ids) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		utils.addCondition("id",ids.toArray() ,ConditionExpressionEnum.IN);
		List<SearchCondition> searchConditions=utils.end();
		return new DefaultAbstractSearchQueryExecutor<FinanceManagePayEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManagePay();
            }
        }.list(searchConditions);
	}

	@Override
	public List<FinanceManagePayEsDTO> getPayStatusByCode(FinanceDeleteReqDTO financeDeleteReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		if(StringUtils.isNotBlank(financeDeleteReqDTO.getCode()))
			utils.addEqualCondition("dataSourceSheetId", financeDeleteReqDTO.getCode());
		if(financeDeleteReqDTO.getWaybillId()!=null)
			utils.addEqualCondition("sheetId", financeDeleteReqDTO.getWaybillId());
		Integer [] deliveryStatus = {PaymentStatusEnum.NO_PAYMENT.getType(),PaymentStatusEnum.PARTLY_PAYMENT.getType(),PaymentStatusEnum.ALL_PAYMENT.getType()};
		utils.addCondition("paymentStatus", deliveryStatus, ConditionExpressionEnum.IN);
		utils.addCondition("feeType", ExpensesEnum.TERMINALCHARGE.getDocType(), ConditionExpressionEnum.UNEQUAL);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManagePayEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {

                return EsConfig.financeManagePay();
            }
        }.list(searchConditions);
        
	}

	public FinanceManagePayEsDTO getEs(Long id) {
		return new DefaultAbstractSearchQueryExecutor<FinanceManagePayEsDTO>(esSearchService) {
			public EsConfig getConfig() {
				return EsConfig.financeManagePay();
			}
		}.get(id);
	}

	@Override
	public Boolean updateBatchEs(List<FinanceManagePayEsDTO> financeManagePayEsDtoList) {
		return new DefaultAbstractSearchUpdateExecutor<FinanceManagePayEsDTO>(esSearchService) {
			public EsConfig getConfig() {
				return EsConfig.financeManagePay();
			}
		}.execute(financeManagePayEsDtoList);
	}

	@Override
	public Boolean updateEs(FinanceManagePayEsDTO financeManagePayEsDto) {
		boolean result = new DefaultAbstractSearchUpdateExecutor<FinanceManagePayEsDTO>(esSearchService) {
			public EsConfig getConfig() {
				return EsConfig.financeManagePay();
			}
		}.execute(financeManagePayEsDto);
		return result;
	}

	@Override
	public boolean deleteByCode(FinanceDeleteReqDTO financeDeleteReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		if(StringUtils.isNotBlank(financeDeleteReqDTO.getCode()))
			utils.addEqualCondition("dataSourceSheetId", financeDeleteReqDTO.getCode());
		if(financeDeleteReqDTO.getWaybillId()!=null)
			utils.addEqualCondition("sheetId", financeDeleteReqDTO.getWaybillId());
        List<SearchCondition> searchConditions=utils.end();
		return new DefaultAbstractSearchDeleteExecutor<FinanceManagePayEsDTO>(esSearchService) {
			public EsConfig getConfig() {
				return EsConfig.financeManagePay();
			}
		}.execute(searchConditions);
	}

	@Override
	public boolean deleteById(Long id) {
		return new DefaultAbstractSearchDeleteExecutor<FinanceManagePayEsDTO>(esSearchService){
			@Override
			public EsConfig getConfig() {
				return EsConfig.financeManagePay();
			}
		}.execute(id);
	}

	@Override
	public FinanceManagePayEsDTO getEsBySheetIdFeeType(FinanceManagePayQueryReqDTO financeManagePayQueryReqDto) {
		SearchConditionUtils start = SearchConditionUtils.start();
		start.addEqualCondition("sheetId", financeManagePayQueryReqDto.getSheetId());
		if(StringUtils.isNotBlank(financeManagePayQueryReqDto.getFeeType())) {
			start.addEqualCondition("feeType", financeManagePayQueryReqDto.getFeeType());
		}
		if(financeManagePayQueryReqDto.getSheetType()!=null) {
			start.addEqualCondition("sheetType", financeManagePayQueryReqDto.getSheetType());
		}
		if(StringUtils.isNotBlank(financeManagePayQueryReqDto.getDataSourceCode())) {
			start.addEqualCondition("dataSourceSheetId", financeManagePayQueryReqDto.getDataSourceCode());
		}
		List<SearchCondition> conditions = start.end();
		return new DefaultAbstractSearchQueryExecutor<FinanceManagePayEsDTO>(esSearchService) {
			public EsConfig getConfig() {
				return EsConfig.financeManagePay();
			}
		}.get(conditions);
	}

	@Override
	public Boolean deleteEsBySheetIdFeeType(FinanceManagePayQueryReqDTO financeManagePayQueryReqDto) {
		SearchConditionUtils start = SearchConditionUtils.start();
		start.addEqualCondition("sheetId", financeManagePayQueryReqDto.getSheetId());
		if(StringUtils.isNotBlank(financeManagePayQueryReqDto.getFeeType())) {
			start.addEqualCondition("feeType", financeManagePayQueryReqDto.getFeeType());
		}
		if(financeManagePayQueryReqDto.getSheetType()!=null) {
			start.addEqualCondition("sheetType", financeManagePayQueryReqDto.getSheetType());
		}
		if(StringUtils.isNotBlank(financeManagePayQueryReqDto.getDataSourceCode())) {
			start.addEqualCondition("dataSourceSheetId", financeManagePayQueryReqDto.getDataSourceCode());
		}
		List<SearchCondition> conditions = start.end();
		return new DefaultAbstractSearchDeleteExecutor<FinanceManagePayEsDTO>(esSearchService) {
			public EsConfig getConfig() {
				return EsConfig.financeManagePay();
			}
		}.execute(conditions);
	}

	@Override
	public List<FinanceManagePayEsDTO> getEsListBySheetIdListAndDataSourceCode(FinanceCancelReqDTO financeCancelReqDto) {
		List<SearchCondition> conditions = SearchConditionUtils.start().end();
		conditions.add(SearchConditionUtils.newInCondition("sheetId", financeCancelReqDto.getIdList().toArray()));
		conditions.add(SearchConditionUtils.newEqualCondition("dataSourceSheetId", financeCancelReqDto.getBatchCode()));
		return new DefaultAbstractSearchQueryExecutor<FinanceManagePayEsDTO>(esSearchService) {
			public EsConfig getConfig() {
				return EsConfig.financeManagePay();
			}
		}.list(conditions);
	}

	@Override
    public List<FinanceManagePayEsDTO> getListForFeeUpdate(BillInfoReqDTO reqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        if (StringUtil.isNotBlank(reqDTO.getBillCode()))
            utils.addEqualCondition("dataSourceSheetId", reqDTO.getBillCode());
        if (reqDTO.getBillType() != null)
            utils.addEqualCondition("sheetType", reqDTO.getBillType());
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManagePayEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {

                return EsConfig.financeManagePay();
            }
        }.list(searchConditions);
	}

	@Override
	public List<FinanceManagePayEsDTO> getEsListByBatchCode(FinanceQueryReqDTO financeQueryReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		utils.addEqualCondition("dataSourceSheetId", financeQueryReqDTO.getBatchCode());
		utils.addEqualCondition("companyId", financeQueryReqDTO.getCompanyId());
		List<SearchCondition> searchConditions=utils.end();
		return new DefaultAbstractSearchQueryExecutor<FinanceManagePayEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {

				return EsConfig.financeManagePay();
			}
		}.list(searchConditions);
	}

	@Override
	public List<FinanceManagePayEsDTO> getPayStatusByTran(FinanceDeleteReqDTO financeDeleteReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
        if (StringUtil.isNotBlank(financeDeleteReqDTO.getCode()))
            utils.addEqualCondition("dataSourceSheetId", financeDeleteReqDTO.getCode());
        if(financeDeleteReqDTO.getWaybillId()!=null)
			utils.addCondition("sheetId",financeDeleteReqDTO.getWaybillId(),ConditionExpressionEnum.EQUAL);
		if(financeDeleteReqDTO.getTransitBillId()!=null)
			utils.addEqualCondition("transitBillId", financeDeleteReqDTO.getTransitBillId());
		if(StringUtil.isNotBlank(financeDeleteReqDTO.getTransitCode()))
			utils.addEqualCondition("transitBillCode", financeDeleteReqDTO.getTransitCode());
		Integer [] deliveryStatus = {PaymentStatusEnum.NO_PAYMENT.getType(),PaymentStatusEnum.PARTLY_PAYMENT.getType(),PaymentStatusEnum.ALL_PAYMENT.getType()};
		utils.addCondition("paymentStatus", deliveryStatus, ConditionExpressionEnum.IN);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManagePayEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {

                return EsConfig.financeManagePay();
            }
        }.list(searchConditions);
	}
}
