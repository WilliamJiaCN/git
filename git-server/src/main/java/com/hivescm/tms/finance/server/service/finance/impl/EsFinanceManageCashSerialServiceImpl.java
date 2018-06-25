package com.hivescm.tms.finance.server.service.finance.impl;

import com.google.common.collect.Lists;
import com.hivescm.common.utils.Assert;
import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.escenter.service.ESStatisticService;
import com.hivescm.framework.elasticsearch.conf.TypeIndexConfiguration;
import com.hivescm.framework.elasticsearch.executor.impl.*;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageSerialCancelCommitEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageSerialUpdateEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageSerialdeleteEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageCashSerialListRespDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageCashTransferAddRespDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManagePrintTransferDteailsRsepDTO;
import com.hivescm.tms.api.enums.finance.FinanceTransferStatusEnum;
import com.hivescm.tms.api.enums.finance.FlowStatusEnum;
import com.hivescm.tms.api.enums.finance.PayWayEnum;
import com.hivescm.tms.api.enums.finance.TransferAccountsStatusEnum;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageCashSerialService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/4 19:27
 * @company 蜂网供应链管理（上海）有限公司
 */
@Service
@Slf4j
public class EsFinanceManageCashSerialServiceImpl implements EsFinanceManageCashSerialService {


    @Autowired
    private ESSearchService esSearchService;

    @Autowired
    private ESStatisticService statisticService;


    /**
     * es配置
     */
    private static TypeIndexConfiguration typeIndexConfiguration = EsConfig.financeManageCash();

    @Override
    public boolean insert(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO) {
        return new DefaultAbstractSearchSaveExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(financeManageCashSerialEsDTO);
    }
    /**
     * 现金流水修改
     * @param financeManageCashSerialEsDTO
     * @return
     */
    @Override
    public boolean updateEs(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO) {
        boolean result = new DefaultAbstractSearchUpdateExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.execute(financeManageCashSerialEsDTO);
        return result;
    }

    @Override
    public boolean updateEsWithNull(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO) {
        boolean result = new DefaultAbstractSearchUpdateExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.executeWithNull(financeManageCashSerialEsDTO);
        return result;
    }

    @Override
    public FinanceManageCashSerialEsDTO getEs(Long id) {
        return new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.get(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return new DefaultAbstractSearchDeleteExecutor<FinanceManageCashSerialEsDTO>(esSearchService){
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(id);
    }

    @Override
    public FinanceManageCashSerialEsDTO findById(Long id) {
        return new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService){
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.get(id);
    }

    @Override
    public boolean updateStatusById(Long id, Integer status) {
        List<SearchCondition> searchConditions = SearchConditionUtils.start().addEqualCondition("id", id).end();

        FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO=new FinanceManageCashSerialEsDTO();
        financeManageCashSerialEsDTO.setStatus(status);
        return new DefaultAbstractSearchUpdateExecutor<FinanceManageCashSerialEsDTO>(esSearchService){
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(financeManageCashSerialEsDTO,searchConditions);
    }

    @Override
    public boolean cancelCommitUpdateBySubmitBillId(Long submitBillId,FinanceManageSerialCancelCommitEsDTO financeManageSerialCancelCommitEsDTO) {
        List<SearchCondition> searchConditions = SearchConditionUtils.start().addEqualCondition("submitBillId", submitBillId).end();

        return new DefaultAbstractSearchUpdateExecutor<FinanceManageSerialCancelCommitEsDTO>(esSearchService){
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.executeWithNull(financeManageSerialCancelCommitEsDTO,searchConditions);
    }

    @Override
    public boolean updateBatchByIdContainsNull(List<FinanceManageSerialUpdateEsDTO> financeManageSerialUpdateEsDTOS) {

        return new DefaultAbstractSearchUpdateExecutor<FinanceManageSerialUpdateEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.executeWithNull(financeManageSerialUpdateEsDTOS);
    }


    @Override
    public boolean deleteUpdateBySubmitBillId(Long submitBillId, FinanceManageSerialdeleteEsDTO financeManageSerialdeleteEsDTO) {
        List<SearchCondition> searchConditions = SearchConditionUtils.start().addEqualCondition("submitBillId", submitBillId).end();

        return new DefaultAbstractSearchUpdateExecutor<FinanceManageSerialdeleteEsDTO>(esSearchService){
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.executeWithNull(financeManageSerialdeleteEsDTO,searchConditions);
    }

    @Override
    public List<FinanceManageCashSerialEsDTO> querySerialListByIDS(List<Long> ids) {

        SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("id",ids.toArray(), ConditionExpressionEnum.IN);
        List<SearchCondition> searchConditions = utils.end();
        return  new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService){

            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.list(searchConditions);

    }

    @Override
    public boolean updateBySubmitBillId(Long submitBillId,FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO) {
        List<SearchCondition> searchConditions = SearchConditionUtils.start().addEqualCondition("submitBillId", submitBillId).end();

        return new DefaultAbstractSearchUpdateExecutor<FinanceManageCashSerialEsDTO>(esSearchService){
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(financeManageCashSerialEsDTO,searchConditions);
    }

    @Override
    public List<FinanceManageCashSerialEsDTO> checkFundRecord(Integer id, Integer type) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("financeId",id, ConditionExpressionEnum.EQUAL);
        utils.addCondition("type",type, ConditionExpressionEnum.EQUAL);
        List<SearchCondition> searchConditions = utils.end();
        return  new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService){

            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.list(searchConditions);
    }

    /**
     * 查询现金流水列表
     * @param financeManageCashSerialListReqDTO
     * @return
     */
    @Override
    public List<FinanceManageCashSerialEsDTO> getCashSerialEsList(FinanceManageCashSerialListReqDTO financeManageCashSerialListReqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsListSearchConditionUtils(utils, financeManageCashSerialListReqDTO);
        List<SearchCondition> searchConditions=utils.end();
        // 分页条件
        PageCondition pageCondition = new PageCondition();
        pageCondition.setCurrentPage(financeManageCashSerialListReqDTO.getCurrentPage());
        pageCondition.setPageSize(financeManageCashSerialListReqDTO.getPageSize());

        // 排序条件
        List<OrderCondition> orderConditions = new ArrayList<>();
        OrderCondition orderCondition = new OrderCondition();
        orderCondition.setFieldName("createTime");
        orderCondition.setOrderCondition(SortEnum.DESC);
        orderConditions.add(orderCondition);
        List<FinanceManageCashSerialEsDTO> list = new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.list(searchConditions, orderConditions, pageCondition);
        if(list==null){
            list = Lists.newArrayList();
        }
        return list;
    }

    @Override
    public Integer getEsListCount(FinanceManageCashSerialListReqDTO financeManageListReqDTO) {
        Integer count = 0;
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsListSearchConditionUtils(utils,financeManageListReqDTO);
        List<SearchCondition> searchConditions=utils.end();
        count = new DefaultAbstractSearchAggregateExecutor<FinanceManageCashSerialEsDTO>(statisticService){

            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.count(searchConditions);
        return count;
    }

    private SearchConditionUtils makeEsListSearchConditionUtils(SearchConditionUtils utils,FinanceManageCashSerialListReqDTO financeManageCashSerialListReqDTO) {
    	if(financeManageCashSerialListReqDTO.getCompanyId()!=null) {
			utils.addEqualCondition("companyId", financeManageCashSerialListReqDTO.getCompanyId());
		}
    	if (financeManageCashSerialListReqDTO.getDateType() != null&&financeManageCashSerialListReqDTO.getDateStart()!=null
        		&&financeManageCashSerialListReqDTO.getDateEnd()!=null) {
            switch (financeManageCashSerialListReqDTO.getDateType()) {
                case 0:
                    utils.addCondition("createBillTime", financeManageCashSerialListReqDTO.getDateStart(), financeManageCashSerialListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
                case 1:
                    utils.addCondition("receiptPayTime", financeManageCashSerialListReqDTO.getDateStart(), financeManageCashSerialListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
            }
        }
        if(financeManageCashSerialListReqDTO.getReceiptPayNetworkIds()!=null && financeManageCashSerialListReqDTO.getReceiptPayNetworkIds().size()>0){
            utils.addCondition("receiptPayNetworkId",financeManageCashSerialListReqDTO.getReceiptPayNetworkIds().toArray(),ConditionExpressionEnum.IN);
        }
        if(CollectionUtils.isNotEmpty(financeManageCashSerialListReqDTO.getFundAccount())){
            utils.addCondition("fundAccount",financeManageCashSerialListReqDTO.getFundAccount().toArray(),ConditionExpressionEnum.IN);
        }
        if(StringUtils.isNotBlank(financeManageCashSerialListReqDTO.getOrdersourceId())){
            utils.addCondition("ordersourceId",financeManageCashSerialListReqDTO.getOrdersourceId(),ConditionExpressionEnum.LIKE);
        }
        if(CollectionUtils.isNotEmpty(financeManageCashSerialListReqDTO.getOrdersourceIdList())){
        	utils.addCondition("id",financeManageCashSerialListReqDTO.getOrdersourceIdList().toArray(),ConditionExpressionEnum.IN);
        }
        if(StringUtils.isNotBlank(financeManageCashSerialListReqDTO.getPayCode())){
            utils.addCondition("payCode",financeManageCashSerialListReqDTO.getPayCode(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageCashSerialListReqDTO.getReceiptCode())){
            utils.addCondition("receiptCode",financeManageCashSerialListReqDTO.getReceiptCode(),ConditionExpressionEnum.LIKE);
        }
        return utils;
    }

	@Override
	public BigDecimal getOldBalance(FinanceManageCashSerialListReqDTO financeManageCashSerialListReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		if(financeManageCashSerialListReqDTO.getCompanyId()!=null) {
			utils.addEqualCondition("companyId", financeManageCashSerialListReqDTO.getCompanyId());
		}
		if (financeManageCashSerialListReqDTO.getDateType() != null) {
            switch (financeManageCashSerialListReqDTO.getDateType()) {
                case 0:
                	if(financeManageCashSerialListReqDTO.getDateStart()!=null) {
                		utils.addCondition("createBillTime", financeManageCashSerialListReqDTO.getDateStart(), ConditionExpressionEnum.LESSER_OR_EQUAL);
                	}else if(financeManageCashSerialListReqDTO.getDateEnd()!=null) {
                		utils.addCondition("createBillTime", financeManageCashSerialListReqDTO.getDateEnd(), ConditionExpressionEnum.LESSER_OR_EQUAL);
                	}else {
                    	return new BigDecimal(0);
                	}
                    break;
                case 1:
                	if(financeManageCashSerialListReqDTO.getDateStart()!=null) {
                		utils.addCondition("receiptPayTime", financeManageCashSerialListReqDTO.getDateStart(), ConditionExpressionEnum.LESSER_OR_EQUAL);
                	}else if(financeManageCashSerialListReqDTO.getDateEnd()!=null) {
                		utils.addCondition("receiptPayTime", financeManageCashSerialListReqDTO.getDateEnd(), ConditionExpressionEnum.LESSER_OR_EQUAL);
                	}else {
                    	return new BigDecimal(0);
                	}
                    break;
            }
        }else {
        	return new BigDecimal(0);
        }
        if(financeManageCashSerialListReqDTO.getReceiptPayNetworkIds()!=null && financeManageCashSerialListReqDTO.getReceiptPayNetworkIds().size()>0){
            utils.addCondition("receiptPayNetworkId",financeManageCashSerialListReqDTO.getReceiptPayNetworkIds().toArray(),ConditionExpressionEnum.IN);
        }
        if(CollectionUtils.isNotEmpty(financeManageCashSerialListReqDTO.getFundAccount())){
            utils.addCondition("fundAccount",financeManageCashSerialListReqDTO.getFundAccount().toArray(),ConditionExpressionEnum.IN);
        }
        if(StringUtils.isNotBlank(financeManageCashSerialListReqDTO.getOrdersourceId())){
            utils.addCondition("ordersourceId",financeManageCashSerialListReqDTO.getOrdersourceId(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageCashSerialListReqDTO.getPayCode())){
            utils.addCondition("payCode",financeManageCashSerialListReqDTO.getPayCode(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageCashSerialListReqDTO.getReceiptCode())){
            utils.addCondition("receiptCode",financeManageCashSerialListReqDTO.getReceiptCode(),ConditionExpressionEnum.LIKE);
        }
        List<SearchCondition> searchConditions = utils.end();
        double receiptAmount = new DefaultAbstractSearchAggregateExecutor<FinanceManageCashSerialEsDTO>(statisticService) {
        	public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
		}.sum("receiptAmount", searchConditions);
		double payAmount = new DefaultAbstractSearchAggregateExecutor<FinanceManageCashSerialEsDTO>(statisticService) {
			public EsConfig getConfig() {
				return EsConfig.financeManageCash();
			}
		}.sum("payAmount", searchConditions);
		BigDecimal oldBalacne = new BigDecimal(receiptAmount-payAmount);
        return oldBalacne;
	}

	@Override
	public List<FinanceManageCashSerialEsDTO> checkFundRecordByCode(CashSerialRecordReqDTO cashSerialRecordReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("ordersourceId",cashSerialRecordReqDTO.getOrdersourceId(), ConditionExpressionEnum.EQUAL);
        utils.addCondition("paymentType",PayWayEnum.PAYMENTDUCTION.getType(), ConditionExpressionEnum.EQUAL);
        utils.addCondition("status",FlowStatusEnum.CANCELLATION.getType(), ConditionExpressionEnum.UNEQUAL);
        List<SearchCondition> searchConditions = utils.end();
        // 排序条件
        List<OrderCondition> orderConditions = new ArrayList<>();
        OrderCondition orderCondition = new OrderCondition();
        orderCondition.setFieldName("createTime");
        orderCondition.setOrderCondition(SortEnum.DESC);
        orderConditions.add(orderCondition);
        return  new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService){

            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.list(searchConditions,orderConditions);
	}

	@Override
	public List<FinanceManageCashSerialEsDTO> checkFundRecordById(CashSerialRecordReqDTO cashSerialRecordReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("financeId",cashSerialRecordReqDTO.getId(), ConditionExpressionEnum.EQUAL);
        utils.addCondition("type",cashSerialRecordReqDTO.getType(), ConditionExpressionEnum.EQUAL);
        utils.addCondition("status",FlowStatusEnum.CANCELLATION.getType(), ConditionExpressionEnum.UNEQUAL);
        utils.addCondition("paymentType",PayWayEnum.PAYMENTDUCTION.getType(), ConditionExpressionEnum.UNEQUAL);
        List<SearchCondition> searchConditions = utils.end();
        // 排序条件
        List<OrderCondition> orderConditions = new ArrayList<>();
        OrderCondition orderCondition = new OrderCondition();
        orderCondition.setFieldName("createTime");
        orderCondition.setOrderCondition(SortEnum.DESC);
        orderConditions.add(orderCondition);
        return  new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService){

            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.list(searchConditions,orderConditions);
	}

	@Override
	public boolean deleteEsBatch(List<Long> ids) {
		List<SearchCondition> scs = SearchConditionUtils.start()
				.addCondition("id", ids.toArray(), ConditionExpressionEnum.IN).end();
		Boolean es = new DefaultAbstractSearchDeleteExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.financeManageCash();
			}
		}.execute(scs);
		return es;
	}

	@Override
	public boolean updateBatch(List<FinanceManageCashSerialEsDTO> cashDetails) {
		return new DefaultAbstractSearchUpdateExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.financeManageCash();
			}
		}.execute(cashDetails);
	}

    @Override
    public List<FinanceManageCashSerialEsDTO> querySerialList(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO) {

        SearchConditionUtils utils = SearchConditionUtils.start();
        if(financeManageCashSerialEsDTO.getSubmitBillId()!=null){
            utils.addCondition("submitBillId",financeManageCashSerialEsDTO.getSubmitBillId(), ConditionExpressionEnum.EQUAL);
        }
        List<SearchCondition> searchConditions = utils.end();
        return  new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService){

            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.list(searchConditions);

    }

    /**
     * 查询详情（现金转账）
     * @param submitBillId
     * @return
     */
    @Override
    public List<FinanceManageCashTransferAddRespDTO> getDetails(Long submitBillId) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("submitBillId",submitBillId,ConditionExpressionEnum.EQUAL);
        List<SearchCondition> searchConditions = utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageCashTransferAddRespDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.list(searchConditions);
    }

    @Override
    public List<FinanceManageCashSerialEsDTO> printCashDetails(FinanceManagePrintCashDetails req) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("submitBillId",req.getId(),ConditionExpressionEnum.EQUAL);
        List<SearchCondition> searchConditions = utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.list(searchConditions);
    }


    /**
     * 查询转账新增列表（现金转账）
     * @param financeManageCashTransferAddReqDTO
     * @return
     */
    @Override
    public List<FinanceManageCashSerialEsDTO> getEsForTransferAddList(FinanceManageCashTransferAddReqDTO financeManageCashTransferAddReqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsForCashTransferAddListSearchConditionUtils(utils, financeManageCashTransferAddReqDTO);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {

                return EsConfig.financeManageCash();
            }
        }.list(searchConditions);
    }

    /**
     * 查询来源单号(现金转账)
     * @param financeManageCashTransferCodeReqDTO
     * @return
     */
    @Override
    public List<FinanceManageCashSerialEsDTO> getCodeByTransfer(FinanceManageCashTransferCodeReqDTO financeManageCashTransferCodeReqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        if(StringUtils.isNotBlank(financeManageCashTransferCodeReqDTO.getCode())){
            utils.addCondition("ordersourceId",financeManageCashTransferCodeReqDTO.getCode(),ConditionExpressionEnum.LIKE);
        }else {
            return null;
        }
        if(CollectionUtils.isNotEmpty(financeManageCashTransferCodeReqDTO.getIdList())){
            utils.addCondition("id",financeManageCashTransferCodeReqDTO.getIdList().toArray(),ConditionExpressionEnum.NOT_IN);
        }
        utils.addCondition("submitBillState",TransferAccountsStatusEnum.UNTRANSFER.getType(),ConditionExpressionEnum.EQUAL);
        utils.addCondition("fundAccount",financeManageCashTransferCodeReqDTO.getTransferAccount(),ConditionExpressionEnum.EQUAL);
        utils.addCondition("submitBillCode","",ConditionExpressionEnum.NULL);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.list(searchConditions);

    }

    /**
     * 根据id快速添加（现金转账）
     * @param id
     * @return
     */
    @Override
    public List<FinanceManageCashSerialEsDTO> getEsByCodeForTransfer(Long id) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("id",id,ConditionExpressionEnum.EQUAL);
        Integer [] transferStatus = {FinanceTransferStatusEnum.UNTRANSFER.getType()};
        utils.addCondition("submitBillState",transferStatus,ConditionExpressionEnum.IN);
        List<SearchCondition> searchConditions = utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageCash();
            }
        }.list(searchConditions);
    }

    private SearchConditionUtils makeEsForCashTransferAddListSearchConditionUtils(SearchConditionUtils utils,FinanceManageCashTransferAddReqDTO financeManageCashTransferAddReqDTO){
        if(financeManageCashTransferAddReqDTO.getDateType()!=null&&financeManageCashTransferAddReqDTO.getDateStart()!=null
                &&financeManageCashTransferAddReqDTO.getDateEnd()!=null){
            switch (financeManageCashTransferAddReqDTO.getDateType()){
                case 0:
                    utils.addCondition("createBillTime",financeManageCashTransferAddReqDTO.getDateStart(),financeManageCashTransferAddReqDTO.getDateEnd(),ConditionExpressionEnum.BETWEEN);
                    break;
                case 1:
                    utils.addCondition("receiptPayTime",financeManageCashTransferAddReqDTO.getDateStart(),financeManageCashTransferAddReqDTO.getDateEnd(),ConditionExpressionEnum.BETWEEN);
                    break;
            }
        }
        if(financeManageCashTransferAddReqDTO.getCodType()!=null && financeManageCashTransferAddReqDTO.getCodType().size()>0){
            utils.addCondition("codType",financeManageCashTransferAddReqDTO.getCodType().toArray(),ConditionExpressionEnum.IN);
        }
        if(StringUtils.isNotBlank(financeManageCashTransferAddReqDTO.getOrdersourceId())){
            utils.addCondition("ordersourceId",financeManageCashTransferAddReqDTO.getOrdersourceId(),ConditionExpressionEnum.LIKE);
        }
        if(financeManageCashTransferAddReqDTO.getSerialId()!=null && financeManageCashTransferAddReqDTO.getSerialId().size()>0){
            utils.addCondition("serialId",financeManageCashTransferAddReqDTO.getSerialId().toArray(),ConditionExpressionEnum.IN);
        }
        utils.addCondition("submitBillState",TransferAccountsStatusEnum.UNTRANSFER.getType(),ConditionExpressionEnum.EQUAL);
        utils.addCondition("fundAccount",financeManageCashTransferAddReqDTO.getTransferAccount(),ConditionExpressionEnum.EQUAL);
        utils.addCondition("submitBillCode","",ConditionExpressionEnum.NULL);

        return utils;
    }

	@Override
	public boolean insertBatch(List<FinanceManageCashSerialEsDTO> financeManageCashSerialList) {
		 return new DefaultAbstractSearchSaveExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
	            public TypeIndexConfiguration getConfig() {
	                return typeIndexConfiguration;
	            }
	        }.execute(financeManageCashSerialList);
	}

}

