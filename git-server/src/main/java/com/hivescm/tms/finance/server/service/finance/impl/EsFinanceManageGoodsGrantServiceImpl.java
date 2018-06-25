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
import com.hivescm.framework.elasticsearch.executor.impl.*;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsGrantEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsRecycleEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantCodeReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantConfirmQueryReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantListReqDTO;
import com.hivescm.tms.api.enums.finance.GrantStatusEnum;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageGoodsGrantService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 15:29
 * @company 蜂网供应链管理（上海）有限公司
 */
@Service
@Slf4j
public class EsFinanceManageGoodsGrantServiceImpl implements EsFinanceManageGoodsGrantService {

    @Autowired
    private ESSearchService esSearchService;

    @Autowired
    private ESStatisticService statisticService;

    /**
     * es配置
     */
    private static TypeIndexConfiguration typeIndexConfiguration = EsConfig.financeManageGoodsGrant();

    @Override
    public boolean insert(FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO) {
        return new DefaultAbstractSearchSaveExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(financeManageGoodsGrantEsDTO);
    }

    @Override
    public boolean update(FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO) {
        return new DefaultAbstractSearchUpdateExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(financeManageGoodsGrantEsDTO);
    }

    @Override
    public FinanceManageGoodsGrantEsDTO getById(Long id) {
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.get(id);
    }

    /**
     * 查询货款发放列表
     * @param financeManageGrantListReqDTO
     * @return
     */
    @Override
    public List<FinanceManageGoodsGrantEsDTO> getEsList(FinanceManageGrantListReqDTO financeManageGrantListReqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsListSearchConditionUtils(utils, financeManageGrantListReqDTO);
        List<SearchCondition> searchConditions=utils.end();
        // 分页条件
        PageCondition pageCondition = new PageCondition();
        pageCondition.setCurrentPage(financeManageGrantListReqDTO.getCurrentPage());
        pageCondition.setPageSize(financeManageGrantListReqDTO.getPageSize());

        // 排序条件
        List<OrderCondition> orderConditions = new ArrayList<>();
        OrderCondition orderCondition = new OrderCondition();
        orderCondition.setFieldName("createTime");
        orderCondition.setOrderCondition(SortEnum.DESC);
        orderConditions.add(orderCondition);
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsGrant();
            }
        }.list(searchConditions,orderConditions,pageCondition);
    }

    /**
     * 查询总数
     * @param financeManageGrantListReqDTO
     * @return
     */
    @Override
    public Integer getEsListCount(FinanceManageGrantListReqDTO financeManageGrantListReqDTO) {
        Integer count = 0;
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsListSearchConditionUtils(utils, financeManageGrantListReqDTO);
        List<SearchCondition> searchConditions=utils.end();
        count = new DefaultAbstractSearchAggregateExecutor<FinanceManageGoodsGrantEsDTO>(statisticService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsGrant();
            }
        }.count(searchConditions);
        return count;
    }

    /**
     * 查询发放确认列表
     * @param financeManageGrantConfirmQueryReqDTO
     * @return
     */
    @Override
    public List<FinanceManageGoodsGrantEsDTO> getEsForGrantConfirmList(FinanceManageGrantConfirmQueryReqDTO financeManageGrantConfirmQueryReqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsForGrantConfirmListSearchConditionUtils(utils, financeManageGrantConfirmQueryReqDTO);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {

                return EsConfig.financeManageGoodsGrant();
            }
        }.list(searchConditions);
    }

    /**
     * 查询运单号
     * @param financeManageGrantCodeReqDTO
     * @return
     */
    @Override
    public List<FinanceManageGoodsGrantEsDTO> getCodeByGrant(FinanceManageGrantCodeReqDTO financeManageGrantCodeReqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        if(StringUtils.isNotBlank(financeManageGrantCodeReqDTO.getCode())){
            utils.addCondition("orderSourceCode",financeManageGrantCodeReqDTO.getCode(),ConditionExpressionEnum.LIKE);
        }else {
        	return null;
        }
        if(financeManageGrantCodeReqDTO.getIdList()!=null && financeManageGrantCodeReqDTO.getIdList().size()>0){
            utils.addCondition("id",financeManageGrantCodeReqDTO.getIdList().toArray(),ConditionExpressionEnum.NOT_IN);
        }
        Integer [] grantStatus = {GrantStatusEnum.UNGRANT.getType(),GrantStatusEnum.PART_GRANT.getType()};
        utils.addCondition("sendStatus",grantStatus,ConditionExpressionEnum.IN);
        utils.addCondition("payBranchId",financeManageGrantCodeReqDTO.getOrgId(),ConditionExpressionEnum.EQUAL);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsGrant();
            }
        }.list(searchConditions);
    }

    /**
     * 根据id快速添加
     * @param id
     * @return
     */
    @Override
    public List<FinanceManageGoodsGrantEsDTO> getEsByCodeForGrant(Long id) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("id",id,ConditionExpressionEnum.EQUAL);
        Integer [] grantStatus = {GrantStatusEnum.UNGRANT.getType(),GrantStatusEnum.PART_GRANT.getType()};
        utils.addCondition("sendStatus",grantStatus,ConditionExpressionEnum.IN);
        List<SearchCondition> searchConditions = utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsGrant();
            }
        }.list(searchConditions);
    }

    @Override
    public List<FinanceManageGoodsGrantEsDTO> getEsList(List<Long> ids) {
        List<SearchCondition> conditions = SearchConditionUtils.start()
                .addCondition("id", ids.toArray(), ConditionExpressionEnum.IN).end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsGrant();
            }
        }.list(conditions);
    }

    @Override
    public Boolean updateBatchEs(List<FinanceManageGoodsGrantEsDTO> financeManageGoodsGrantEsDtoList) {
        return new DefaultAbstractSearchUpdateExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsGrant();
            }
        }.execute(financeManageGoodsGrantEsDtoList);
    }

    @Override
    public Boolean deleteById(Long id) {
        return new DefaultAbstractSearchDeleteExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService){
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsGrant();
            }
        }.execute(id);
    }

    @Override
    public List<FinanceManageGoodsGrantEsDTO> findByWaybillIdList(List<Long> transportIdList) {
        SearchConditionUtils searchConditionUtils = SearchConditionUtils.start();

        if(CollectionUtils.isNotEmpty(transportIdList)){
            searchConditionUtils.addCondition("waybillId",transportIdList.toArray(),ConditionExpressionEnum.IN);
        }
        List<SearchCondition> searchConditions = searchConditionUtils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsGrant();
            }
        }.list(searchConditions);
    }

    private SearchConditionUtils makeEsListSearchConditionUtils(SearchConditionUtils utils,FinanceManageGrantListReqDTO financeManageGrantListReqDTO){
    	if(financeManageGrantListReqDTO.getCompanyId()!=null) {
			utils.addEqualCondition("companyId", financeManageGrantListReqDTO.getCompanyId());
		}
    	if (financeManageGrantListReqDTO.getDateType() != null&&financeManageGrantListReqDTO.getDateStart()!=null
                &&financeManageGrantListReqDTO.getDateEnd()!=null) {
            switch (financeManageGrantListReqDTO.getDateType()){
                case 0:
                    utils.addCondition("waybillCreateTime", financeManageGrantListReqDTO.getDateStart(), financeManageGrantListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
                case 1:
                    utils.addCondition("signTime", financeManageGrantListReqDTO.getDateStart(), financeManageGrantListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
                case 2:
                    utils.addCondition("recycleTime", financeManageGrantListReqDTO.getDateStart(), financeManageGrantListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
                case 3:
                    utils.addCondition("remitTime", financeManageGrantListReqDTO.getDateStart(), financeManageGrantListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
                case 4:
                    utils.addCondition("sendTime", financeManageGrantListReqDTO.getDateStart(), financeManageGrantListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
                case 5:
                    utils.addCondition("confirmAccountTime", financeManageGrantListReqDTO.getDateStart(), financeManageGrantListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
            }
        }
        if(CollectionUtils.isNotEmpty(financeManageGrantListReqDTO.getPayBranchId())){
            utils.addCondition("payBranchId",financeManageGrantListReqDTO.getPayBranchId().toArray(),ConditionExpressionEnum.IN);
        }
        if(CollectionUtils.isNotEmpty(financeManageGrantListReqDTO.getInvoiceOrgId())){
            utils.addCondition("invoiceOrgId",financeManageGrantListReqDTO.getInvoiceOrgId().toArray(),ConditionExpressionEnum.IN);
        }
        if(CollectionUtils.isNotEmpty(financeManageGrantListReqDTO.getDestOrgId())){
            utils.addCondition("destOrgId",financeManageGrantListReqDTO.getDestOrgId().toArray(),ConditionExpressionEnum.IN);
        }
        if(StringUtils.isNotBlank(financeManageGrantListReqDTO.getOrderSourceCode())){
            utils.addCondition("orderSourceCode",financeManageGrantListReqDTO.getOrderSourceCode(),ConditionExpressionEnum.LIKE);
        }
        if(CollectionUtils.isNotEmpty(financeManageGrantListReqDTO.getOrderSourceCodeList())){
            utils.addCondition("orderSourceCode",financeManageGrantListReqDTO.getOrderSourceCodeList().toArray(),ConditionExpressionEnum.IN);
        }
        if(financeManageGrantListReqDTO.getSignStatus()!=null){
            utils.addEqualCondition("signStatus",financeManageGrantListReqDTO.getSignStatus());
        }
        if(financeManageGrantListReqDTO.getSendStatus()!=null){
            utils.addEqualCondition("sendStatus",financeManageGrantListReqDTO.getSendStatus());
        }
        if(financeManageGrantListReqDTO.getAcountStatus()!=null){
            utils.addEqualCondition("acountStatus",financeManageGrantListReqDTO.getAcountStatus());
        }
        if(StringUtils.isNotBlank(financeManageGrantListReqDTO.getReceiveName())){
            utils.addCondition("receiveName",financeManageGrantListReqDTO.getReceiveName(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageGrantListReqDTO.getInvoiceCompanyName())){
            utils.addCondition("invoiceCompanyName",financeManageGrantListReqDTO.getInvoiceCompanyName(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageGrantListReqDTO.getInvoiceUserName())){
            utils.addCondition("invoiceUserName",financeManageGrantListReqDTO.getInvoiceUserName(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageGrantListReqDTO.getInvoiceUserMobile())){
            utils.addCondition("invoiceUserMobile",financeManageGrantListReqDTO.getInvoiceUserMobile(),ConditionExpressionEnum.LIKE);
        }
        return utils;
    }
    private SearchConditionUtils makeEsForGrantConfirmListSearchConditionUtils(SearchConditionUtils utils,FinanceManageGrantConfirmQueryReqDTO financeManageGrantConfirmQueryReqDTO){
        if(financeManageGrantConfirmQueryReqDTO.getDateType()!=null&&financeManageGrantConfirmQueryReqDTO.getDateStart()!=null
                &&financeManageGrantConfirmQueryReqDTO.getDateEnd()!=null){
            switch (financeManageGrantConfirmQueryReqDTO.getDateType()){
                case 0:
                    utils.addCondition("waybillCreateTime", financeManageGrantConfirmQueryReqDTO.getDateStart(), financeManageGrantConfirmQueryReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
                case 1:
                    utils.addCondition("signTime", financeManageGrantConfirmQueryReqDTO.getDateStart(), financeManageGrantConfirmQueryReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
            }
        }
        if(StringUtils.isNotBlank(financeManageGrantConfirmQueryReqDTO.getInvoiceCompanyName())){
            utils.addCondition("invoiceCompanyName",financeManageGrantConfirmQueryReqDTO.getInvoiceCompanyName(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageGrantConfirmQueryReqDTO.getInvoiceUserName())){
            utils.addCondition("invoiceUserName",financeManageGrantConfirmQueryReqDTO.getInvoiceUserName(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageGrantConfirmQueryReqDTO.getOrderSourceCode())){
            utils.addCondition("orderSourceCode",financeManageGrantConfirmQueryReqDTO.getOrderSourceCode(),ConditionExpressionEnum.LIKE);
        }
        Integer [] grantStatus = {GrantStatusEnum.UNGRANT.getType(),GrantStatusEnum.PART_GRANT.getType()};
        utils.addCondition("sendStatus",grantStatus,ConditionExpressionEnum.IN);
        utils.addCondition("payBranchId",financeManageGrantConfirmQueryReqDTO.getOrgId(),ConditionExpressionEnum.EQUAL);
        return utils;
    }

	@Override
	public FinanceManageGoodsGrantEsDTO findByCode(String orderSourceCode) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		utils.addCondition("orderSourceCode",orderSourceCode,ConditionExpressionEnum.EQUAL);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsGrant();
            }
        }.get(searchConditions);
	}

	@Override
	public FinanceManageGoodsGrantEsDTO findByWaybillId(Long waybillId) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		utils.addCondition("waybillId",waybillId,ConditionExpressionEnum.EQUAL);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsGrant();
            }
        }.get(searchConditions);
	}

	@Override
	public Boolean delete(Long id) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		utils.addCondition("id",id,ConditionExpressionEnum.EQUAL);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchDeleteExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsGrant();
            }
        }.execute(searchConditions);
	}

	@Override
	public boolean insertBatch(List<FinanceManageGoodsGrantEsDTO> financeManageGoodsGrantList) {
		return new DefaultAbstractSearchSaveExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(financeManageGoodsGrantList);
	}

	@Override
	public boolean updateBatch(List<FinanceManageGoodsGrantEsDTO> financeManageGoodsGrantList) {
		return new DefaultAbstractSearchUpdateExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(financeManageGoodsGrantList);
	}

	@Override
	public Boolean deleteBatch(List<Long> idGrant) {
		List<SearchCondition> scs = SearchConditionUtils.start()
				.addCondition("id", idGrant.toArray(), ConditionExpressionEnum.IN).end();
		Boolean es = new DefaultAbstractSearchDeleteExecutor<FinanceManageGoodsGrantEsDTO>(esSearchService) {
			 @Override
	            public TypeIndexConfiguration getConfig() {
	                return typeIndexConfiguration;
	            }
		}.execute(scs);
		return es;
	}
}
