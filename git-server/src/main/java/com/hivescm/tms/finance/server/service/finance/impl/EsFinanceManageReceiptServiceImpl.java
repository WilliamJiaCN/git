package com.hivescm.tms.finance.server.service.finance.impl;

import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.escenter.service.ESStatisticService;
import com.hivescm.framework.common.utils.StringUtils;
import com.hivescm.framework.elasticsearch.conf.TypeIndexConfiguration;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchAggregateExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchDeleteExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchSaveExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchUpdateExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.finance.FinanceManagePayEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.query.FinanceManageReceiptQueryDTO;
import com.hivescm.tms.api.enums.finance.DeliveryStatusEnum;
import com.hivescm.tms.api.enums.finance.PayWayEnum;
import com.hivescm.tms.api.enums.finance.PaymentStatusEnum;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageReceiptService;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class EsFinanceManageReceiptServiceImpl implements EsFinanceManageReceiptService{

    /**
     * es
     */
    @Autowired
    private ESSearchService esSearchService;

    @Autowired
    private ESStatisticService statisticService;

    /**
     * es配置
     */
    private static TypeIndexConfiguration typeIndexConfiguration = EsConfig.financeManageReceipt();


    @Override
    public boolean insertBatch(SaveFinanceManageReceiptReqDTO financeManageReceiptReqDTO) {
            return new DefaultAbstractSearchSaveExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
                @Override
                public TypeIndexConfiguration getConfig() {
                    return typeIndexConfiguration;
                }
            }.execute(financeManageReceiptReqDTO.getFinanceManageReceiptEsDTOList());
    }


    /**
     * 查询列表
     * @param financeManageReceiptListReqDTO
     * @return
     */
    @Override
    public List<FinanceManageReceiptEsDTO> getEsList(FinanceManageReceiptListReqDTO financeManageReceiptListReqDTO) {

        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsListSearchConditionUtils(utils,financeManageReceiptListReqDTO);
        List<SearchCondition> searchConditions = utils.end();
        //分页条件
        PageCondition pageCondition = new PageCondition();
        pageCondition.setCurrentPage(financeManageReceiptListReqDTO.getCurrentPage());
        pageCondition.setPageSize(financeManageReceiptListReqDTO.getPageSize());
        // 排序条件
        List<OrderCondition> orderConditions = new ArrayList<>();
        OrderCondition orderCondition = new OrderCondition();
        orderCondition.setFieldName("createTime");
        orderCondition.setOrderCondition(SortEnum.DESC);
        orderConditions.add(orderCondition);
        return new DefaultAbstractSearchQueryExecutor<FinanceManageReceiptEsDTO>(esSearchService){
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageReceipt();
            }
        }.list(searchConditions,orderConditions,pageCondition);
    }

    /**
     * 查询总数
     * @param financeManageReceiptListReqDTO
     * @return
     */
    @Override
    public Integer getEsListCount(FinanceManageReceiptListReqDTO financeManageReceiptListReqDTO) {

        Integer count =0;
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsListSearchConditionUtils(utils, financeManageReceiptListReqDTO);
        List<SearchCondition> searchConditions=utils.end();
        count = new DefaultAbstractSearchAggregateExecutor<FinanceManageReceiptEsDTO>(statisticService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageReceipt();
            }
        }.count(searchConditions);
        return count;
    }

    /**
     * 查询新增收款列表
     * @param financeManageListForReceiptReqDTO
     * @return
     */
    @Override
    public List<FinanceManageReceiptEsDTO> getEsForReceiptList(FinanceManageListForReceiptReqDTO financeManageListForReceiptReqDTO) {

        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsForReceiptListSearchConditionUtils(utils, financeManageListForReceiptReqDTO);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {

                return EsConfig.financeManageReceipt();
            }
        }.list(searchConditions);
    }
    
    /**
     * 查询来源单号
     * @param financeReceiptCodeReqDTO
     * @return
     */
    @Override
    public List<FinanceManageReceiptEsDTO> getCodeForReceipt(FinanceReceiptCodeReqDTO financeReceiptCodeReqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        if(StringUtils.isNotBlank(financeReceiptCodeReqDTO.getCode())){
            utils.addCondition("orderSourceCode",financeReceiptCodeReqDTO.getCode(),ConditionExpressionEnum.LIKE);
        }else {
            return null;
        }
        if(financeReceiptCodeReqDTO.getIdList()!=null && financeReceiptCodeReqDTO.getIdList().size()>0){
            utils.addCondition("id",financeReceiptCodeReqDTO.getIdList().toArray(),ConditionExpressionEnum.NOT_IN);
        }
        utils.addCondition("deliveryNetworkId",financeReceiptCodeReqDTO.getOrgId(),ConditionExpressionEnum.EQUAL);
        //TODO 只能查出未收银或者部分收银  和产品确认
        Integer [] deliveryStatus = {2,3};
        utils.addCondition("deliveryStatus",deliveryStatus,ConditionExpressionEnum.IN);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageReceipt();
            }
        }.list(searchConditions);
    }

    /**
     * 通过id快速添加
     * @param id
     * @return
     */
    @Override
    public List<FinanceManageReceiptEsDTO> getEsByCodeForReceipt(Long id) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("id",id,ConditionExpressionEnum.EQUAL);
        Integer [] deliveryStatus = {DeliveryStatusEnum.NO_DELIVERY.getType(),DeliveryStatusEnum.PARTLY_DELIVERY.getType()};
        utils.addCondition("deliveryStatus", deliveryStatus, ConditionExpressionEnum.IN);
        List<SearchCondition> searchConditions = utils.end();

        return new DefaultAbstractSearchQueryExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageReceipt();
            }
        }.list(searchConditions);
    }

    @Override
    public List<FinanceManageReceiptEsDTO> getEsForReceipt(FinanceManageReceiptQueryDTO financeManageReceiptQueryDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        String code = financeManageReceiptQueryDTO.getWaybillCode();
        Boolean isqueryPayWay = financeManageReceiptQueryDTO.getIsQueryPayWay();
        utils.addCondition("orderSourceCode",code,ConditionExpressionEnum.EQUAL);
        utils.addCondition("companyId",financeManageReceiptQueryDTO.getCompanyId(),ConditionExpressionEnum.EQUAL);
        if(isqueryPayWay!=null&&isqueryPayWay==true){
            Integer [] payWays = {PayWayEnum.TOPAY.getType(),PayWayEnum.COLLECTIONOFGOODS.getType()};
            utils.addCondition("payWay", payWays, ConditionExpressionEnum.IN);
        }
//        Integer [] deliveryStatus = {DeliveryStatusEnum.NO_DELIVERY.getType(),DeliveryStatusEnum.PARTLY_DELIVERY.getType()};
//        utils.addCondition("deliveryStatus", deliveryStatus, ConditionExpressionEnum.IN);
        List<SearchCondition> searchConditions = utils.end();

        return new DefaultAbstractSearchQueryExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageReceipt();
            }
        }.list(searchConditions);
    }

    @Override
    public FinanceManageReceiptEsDTO findFinanceManageReceipt(FinanceManageReceiptReqDTO financeManageReceiptReqDTO) {
    	SearchConditionUtils utils = SearchConditionUtils.start();
    	if(financeManageReceiptReqDTO.getCodeType()!=null) {
            utils.addEqualCondition("codeType", financeManageReceiptReqDTO.getCodeType());
        }
        if(financeManageReceiptReqDTO.getPayWay()!=null) {
            utils.addEqualCondition("payWay", financeManageReceiptReqDTO.getPayWay());
        }
        if(StringUtils.isNotBlank(financeManageReceiptReqDTO.getOrderSourceId())) {
            utils.addEqualCondition("orderSourceCode", financeManageReceiptReqDTO.getOrderSourceId());
        }
        if(financeManageReceiptReqDTO.getCompanyId()!=null) {
            utils.addEqualCondition("companyId", financeManageReceiptReqDTO.getCompanyId());
        }
        if(financeManageReceiptReqDTO.getBranchId()!=null) {
        	utils.addEqualCondition("deliveryNetworkId", financeManageReceiptReqDTO.getBranchId());
        }
        if(financeManageReceiptReqDTO.getWaybillId()!=null) {
        	utils.addEqualCondition("waybillId", financeManageReceiptReqDTO.getWaybillId());
        }
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {

                return EsConfig.financeManageReceipt();
            }
        }.get(searchConditions);
    }

    @Override
    public boolean updateDeliveryStatus(VerifyFinanceReceiptReqDTO verifyFinanceReceiptReqDTO) {
        return new DefaultAbstractSearchUpdateExecutor<VerifyFinanceReceiptEsDTO>(esSearchService) {
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(verifyFinanceReceiptReqDTO.getVerifyFinanceReceiptEsDTOList());
    }

    @Override
    public boolean updateReceivableFinanceReceipt(List<ReceivableFinanceReceiptEsDTO> receivableFinanceReceiptEsDTOList) {
        return new DefaultAbstractSearchUpdateExecutor<ReceivableFinanceReceiptEsDTO>(esSearchService) {
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(receivableFinanceReceiptEsDTOList);
    }

    @Override
    public List<ReceivableFinanceReceiptEsDTO> findReceivableFinanceReceiptById(List<Long> idList) {
        SearchConditionUtils searchConditionUtils=SearchConditionUtils.start();
        searchConditionUtils.addCondition("id",idList.toArray(),ConditionExpressionEnum.IN);
        List<SearchCondition> searchConditions=searchConditionUtils.end();
        return new DefaultAbstractSearchQueryExecutor<ReceivableFinanceReceiptEsDTO>(esSearchService){
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.list(searchConditions);
    }

    @Override
    public boolean insertFinanceManageReceiptEsDTO(FinanceManageReceiptEsDTO financeManageReceiptEsDTO) {
        return new DefaultAbstractSearchSaveExecutor<FinanceManageReceiptEsDTO>(esSearchService){

            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(financeManageReceiptEsDTO);
    }

    @Override
    public boolean deleteById(Long id) {
        return new DefaultAbstractSearchDeleteExecutor<FinanceManageReceiptEsDTO>(esSearchService){
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(id);
    }

    private SearchConditionUtils makeEsListSearchConditionUtils(SearchConditionUtils utils,FinanceManageReceiptListReqDTO financeManageReceiptListReqDTO){
    	if(financeManageReceiptListReqDTO.getCompanyId()!=null) {
			utils.addEqualCondition("companyId", financeManageReceiptListReqDTO.getCompanyId());
		}
        if(financeManageReceiptListReqDTO.getDateStart()!=null&&financeManageReceiptListReqDTO.getDateEnd()!=null){
            utils.addCondition("businessTime",financeManageReceiptListReqDTO.getDateStart(),financeManageReceiptListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
        }
        if(financeManageReceiptListReqDTO.getDeliveryNetworkIds()!=null && financeManageReceiptListReqDTO.getDeliveryNetworkIds().size()>0) {
            utils.addCondition("deliveryNetworkId", financeManageReceiptListReqDTO.getDeliveryNetworkIds().toArray(),ConditionExpressionEnum.IN);
        }
        if(financeManageReceiptListReqDTO.getDeliveryStatus()!=null) {
            utils.addEqualCondition("deliveryStatus", financeManageReceiptListReqDTO.getDeliveryStatus());
        }
        if(CollectionUtils.isNotEmpty(financeManageReceiptListReqDTO.getPayWay())) {
            utils.addCondition("payWay", financeManageReceiptListReqDTO.getPayWay().toArray(),ConditionExpressionEnum.IN);
        }
        if(StringUtils.isNotBlank(financeManageReceiptListReqDTO.getOrdersourceId())) {
            utils.addCondition("orderSourceCode", financeManageReceiptListReqDTO.getOrdersourceId(),ConditionExpressionEnum.LIKE);
        }
        if(CollectionUtils.isNotEmpty(financeManageReceiptListReqDTO.getOrdersourceIdList())) {
        	utils.addCondition("orderSourceCode", financeManageReceiptListReqDTO.getOrdersourceIdList().toArray(),ConditionExpressionEnum.IN);
        }
        if(financeManageReceiptListReqDTO.getInvoiceOrgIds()!=null && financeManageReceiptListReqDTO.getInvoiceOrgIds().size()>0) {
            utils.addCondition("invoiceOrgId", financeManageReceiptListReqDTO.getInvoiceOrgIds().toArray(),ConditionExpressionEnum.IN);
        }
        if(financeManageReceiptListReqDTO.getDestIds()!=null && financeManageReceiptListReqDTO.getDestIds().size()>0) {
            utils.addCondition("destOrgId", financeManageReceiptListReqDTO.getDestIds().toArray(),ConditionExpressionEnum.IN);
        }
        if(StringUtils.isNotBlank(financeManageReceiptListReqDTO.getInvoiceCompanyName())) {
            utils.addCondition("invoiceCompany", financeManageReceiptListReqDTO.getInvoiceCompanyName(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageReceiptListReqDTO.getInvoiceUserName())) {
            utils.addCondition("invoiceUser", financeManageReceiptListReqDTO.getInvoiceUserName(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageReceiptListReqDTO.getInvoiceUserMobile())) {
            utils.addCondition("invoiceMobleNo", financeManageReceiptListReqDTO.getInvoiceUserMobile(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageReceiptListReqDTO.getInvoiceTelNo())) {
        	utils.addCondition("invoiceTelNo", financeManageReceiptListReqDTO.getInvoiceTelNo(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageReceiptListReqDTO.getReceiptCompanyName())) {
            utils.addCondition("receiptCompany", financeManageReceiptListReqDTO.getReceiptCompanyName(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageReceiptListReqDTO.getReceiptUserName())) {
            utils.addCondition("receiptUser", financeManageReceiptListReqDTO.getReceiptUserName(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageReceiptListReqDTO.getReceiptUserMobile())) {
            utils.addCondition("receiptConsignorMobleNo", financeManageReceiptListReqDTO.getReceiptUserMobile(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageReceiptListReqDTO.getReceiptConsignorTelNo())) {
        	utils.addCondition("receiptConsignorTelNo", financeManageReceiptListReqDTO.getReceiptConsignorTelNo(),ConditionExpressionEnum.LIKE);
        }
        if(CollectionUtils.isNotEmpty(financeManageReceiptListReqDTO.getCodeType())) {
            utils.addCondition("codeType", financeManageReceiptListReqDTO.getCodeType().toArray(),ConditionExpressionEnum.IN);
        }
        return utils;
    }

    private SearchConditionUtils makeEsForReceiptListSearchConditionUtils(SearchConditionUtils utils,FinanceManageListForReceiptReqDTO financeManageListForReceiptReqDTO){

        if(CollectionUtils.isNotEmpty(financeManageListForReceiptReqDTO.getCodeType())) {
            utils.addCondition("codeType", financeManageListForReceiptReqDTO.getCodeType().toArray(),ConditionExpressionEnum.IN);
        }
        if(CollectionUtils.isNotEmpty(financeManageListForReceiptReqDTO.getPayWay())) {
            utils.addCondition("payWay", financeManageListForReceiptReqDTO.getPayWay().toArray(),ConditionExpressionEnum.IN);
        }
        if(StringUtils.isNotBlank(financeManageListForReceiptReqDTO.getOrdersourceId())) {
            utils.addCondition("orderSourceCode", financeManageListForReceiptReqDTO.getOrdersourceId(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageListForReceiptReqDTO.getInvoiceUserName())) {
            utils.addCondition("invoiceUser", financeManageListForReceiptReqDTO.getInvoiceUserName(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageListForReceiptReqDTO.getReceiptUserName())) {
            utils.addCondition("receiptUser", financeManageListForReceiptReqDTO.getReceiptUserName(),ConditionExpressionEnum.LIKE);
        }

        Integer [] deliveryStatus = {2,3};
        utils.addCondition("deliveryStatus",deliveryStatus,ConditionExpressionEnum.IN);
        utils.addCondition("deliveryNetworkId",financeManageListForReceiptReqDTO.getOrgId(),ConditionExpressionEnum.EQUAL);

        return utils;
    }

	@Override
	public boolean deleteByCode(FinanceDeleteReqDTO financeDeleteReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		if(StringUtils.isNotBlank(financeDeleteReqDTO.getCode()))
			utils.addEqualCondition("orderSourceCode", financeDeleteReqDTO.getCode());
		if(financeDeleteReqDTO.getWaybillId()!=null)
			utils.addEqualCondition("waybillId", financeDeleteReqDTO.getWaybillId());
		utils.addCondition("payWay", PayWayEnum.TOPAY.getType(),ConditionExpressionEnum.UNEQUAL);
        List<SearchCondition> searchConditions=utils.end();
		return new DefaultAbstractSearchDeleteExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.financeManageReceipt();
			}
		}.execute(searchConditions);
	}

    @Override
    public boolean deleteByPayway(FinanceDeleteReqDTO financeDeleteReqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        if(StringUtils.isNotBlank(financeDeleteReqDTO.getCode()))
            utils.addEqualCondition("orderSourceCode", financeDeleteReqDTO.getCode());
        if(financeDeleteReqDTO.getWaybillId()!=null)
            utils.addEqualCondition("waybillId", financeDeleteReqDTO.getWaybillId());
        utils.addEqualCondition("payWay", PayWayEnum.TOPAY.getType());
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchDeleteExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageReceipt();
            }
        }.execute(searchConditions);
    }

    @Override
	public List<FinanceManageReceiptEsDTO> getPayStatusByCode(FinanceDeleteReqDTO financeDeleteReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		if(StringUtils.isNotBlank(financeDeleteReqDTO.getCode()))
			utils.addEqualCondition("orderSourceCode", financeDeleteReqDTO.getCode());
		if(financeDeleteReqDTO.getWaybillId()!=null)
			utils.addEqualCondition("waybillId", financeDeleteReqDTO.getWaybillId());
		Integer [] deliveryStatus = {DeliveryStatusEnum.NO_DELIVERY.getType(),DeliveryStatusEnum.PARTLY_DELIVERY.getType(),DeliveryStatusEnum.DELIVERY.getType()};
		utils.addCondition("deliveryStatus", deliveryStatus, ConditionExpressionEnum.IN);
		utils.addCondition("payWay", PayWayEnum.TOPAY.getType(),ConditionExpressionEnum.UNEQUAL);
		utils.addCondition("payWay", PayWayEnum.NOWPAY.getType(),ConditionExpressionEnum.UNEQUAL);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {

                return EsConfig.financeManageReceipt();
            }
        }.list(searchConditions);
	}


	@Override
	public FinanceManageReceiptEsDTO findFinanceManageReceipt(Long id) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		utils.addEqualCondition("id", id);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {

                return EsConfig.financeManageReceipt();
            }
        }.get(searchConditions);
	}


	@Override
	public boolean updateByReceipt(ReceivableFinanceReceiptEsDTO receivableFinanceReceiptEsDTO) {
		 return new DefaultAbstractSearchUpdateExecutor<ReceivableFinanceReceiptEsDTO>(esSearchService) {
	            @Override
	            public TypeIndexConfiguration getConfig() {
	                return typeIndexConfiguration;
	            }
	        }.execute(receivableFinanceReceiptEsDTO);
	}

    @Override
    public Boolean updateEs(FinanceManageReceiptEsDTO financeManageReceiptEsDTO) {
        boolean result = new DefaultAbstractSearchUpdateExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(financeManageReceiptEsDTO);
        return result;
    }


	@Override
	public List<FinanceManageReceiptEsDTO> getPayStatusByCodeSign(FinanceDeleteReqDTO financeDeleteReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		if(StringUtils.isNotBlank(financeDeleteReqDTO.getCode()))
			utils.addEqualCondition("orderSourceCode", financeDeleteReqDTO.getCode());
		if(financeDeleteReqDTO.getWaybillId()!=null)
			utils.addEqualCondition("waybillId", financeDeleteReqDTO.getWaybillId());
		if(financeDeleteReqDTO.getTransitBillId()!=null)
			utils.addEqualCondition("transitBillId", financeDeleteReqDTO.getTransitBillId());
		if(StringUtils.isNotBlank(financeDeleteReqDTO.getTransitCode()))
			utils.addEqualCondition("transitBillCode", financeDeleteReqDTO.getTransitCode());
		Integer [] deliveryStatus = {DeliveryStatusEnum.NO_DELIVERY.getType(),DeliveryStatusEnum.PARTLY_DELIVERY.getType(),DeliveryStatusEnum.DELIVERY.getType()};
		utils.addCondition("deliveryStatus", deliveryStatus, ConditionExpressionEnum.IN);
		utils.addCondition("payWay", PayWayEnum.TOPAY.getType(),ConditionExpressionEnum.EQUAL);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {

                return EsConfig.financeManageReceipt();
            }
        }.list(searchConditions);
	}


	@Override
	public List<FinanceManageReceiptEsDTO> getPayStatusByCodeNowPay(FinanceDeleteReqDTO financeDeleteReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		if(StringUtils.isNotBlank(financeDeleteReqDTO.getCode()))
			utils.addEqualCondition("orderSourceCode", financeDeleteReqDTO.getCode());
		if(financeDeleteReqDTO.getWaybillId()!=null)
			utils.addEqualCondition("waybillId", financeDeleteReqDTO.getWaybillId());
		Integer [] deliveryStatus = {DeliveryStatusEnum.PARTLY_DELIVERY.getType(),DeliveryStatusEnum.DELIVERY.getType()};
		utils.addCondition("deliveryStatus", deliveryStatus, ConditionExpressionEnum.IN);
		utils.addCondition("payWay", PayWayEnum.NOWPAY.getType(),ConditionExpressionEnum.EQUAL);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {

                return EsConfig.financeManageReceipt();
            }
        }.list(searchConditions);
	}


	@Override
	public boolean updateSignStatus(FinanceReceiptStatusUpdateDTO financeReceiptStatusUpdateDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		if(CollectionUtils.isNotEmpty(financeReceiptStatusUpdateDTO.getIds()))
			utils.addCondition("waybillId", financeReceiptStatusUpdateDTO.getIds().toArray(),ConditionExpressionEnum.IN);
		if(financeReceiptStatusUpdateDTO.getPayWay()!=null)
			utils.addEqualCondition("payWay", financeReceiptStatusUpdateDTO.getPayWay());
		List<SearchCondition> searchConditions=utils.end();
		FinanceManageReceiptEsDTO dto = new FinanceManageReceiptEsDTO();
		if(financeReceiptStatusUpdateDTO.getSignStatus()!=null)
			dto.setSignStatus(financeReceiptStatusUpdateDTO.getSignStatus());
		if(StringUtils.isNotBlank(financeReceiptStatusUpdateDTO.getSignStatusName()))
			dto.setSignStatusName(financeReceiptStatusUpdateDTO.getSignStatusName());
		if(financeReceiptStatusUpdateDTO.getSignTime()!=null)
			dto.setSignTime(financeReceiptStatusUpdateDTO.getSignTime());
		if(financeReceiptStatusUpdateDTO.getBackStatus()!=null)
			dto.setBackStatus(financeReceiptStatusUpdateDTO.getBackStatus());
		if(StringUtils.isNotBlank(financeReceiptStatusUpdateDTO.getBackStatusName()))
			dto.setBackStatusName(financeReceiptStatusUpdateDTO.getBackStatusName());
		if(financeReceiptStatusUpdateDTO.getBackGrantTime()!=null)
			dto.setBackGrantTime(financeReceiptStatusUpdateDTO.getBackGrantTime());
		return new DefaultAbstractSearchUpdateExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
			@Override
            public EsConfig getConfig() {

                return EsConfig.financeManageReceipt();
            }
		}.execute(dto, searchConditions);
	}


	@Override
	public FinanceManageReceiptEsDTO findByWaybillId(Long c) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		utils.addEqualCondition("waybillId", c);
		utils.addEqualCondition("payWay", PayWayEnum.BACKPAY.getType());
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageReceiptEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {

                return EsConfig.financeManageReceipt();
            }
        }.get(searchConditions);
	}
}
