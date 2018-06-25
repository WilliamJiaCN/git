package com.hivescm.tms.finance.server.service.finance.impl;

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
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashTransferEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageTransferCancelCommitEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageCashTransferService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: LiXuan
 * @Date: Created in 2018/5/11 10:28
 * @company 蜂网供应链管理（上海）有限公司
 */
@Service
@Slf4j
public class EsFinanceManageCashTransferServiceImpl implements EsFinanceManageCashTransferService{

    @Autowired
    private ESSearchService esSearchService;

    @Autowired
    private ESStatisticService statisticService;

    /**
     * es配置
     */
    private static TypeIndexConfiguration typeIndexConfiguration = EsConfig.financeManageCashTransfer();

    /**
     * 新增现金转账
     * @param financeManageCashTransferEsDTO
     * @return
     */
    @Override
    public Boolean insert(FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO) {

        return new DefaultAbstractSearchSaveExecutor<FinanceManageCashTransferEsDTO>(esSearchService){

            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(financeManageCashTransferEsDTO);
    }

    /**
     * 更新现金转账
     * @param financeManageCashTransferEsDTO
     * @return
     */
    @Override
    public Boolean update(FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO) {
        return new DefaultAbstractSearchUpdateExecutor<FinanceManageCashTransferEsDTO>(esSearchService) {
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(financeManageCashTransferEsDTO);
    }


    /**
     * 更新现金转账更新NULL值
     * @param financeManageTransferCancelCommitEsDTO
     * @return
     */
    @Override
    public Boolean updateNull(FinanceManageTransferCancelCommitEsDTO financeManageTransferCancelCommitEsDTO) {
        return new DefaultAbstractSearchUpdateExecutor<FinanceManageTransferCancelCommitEsDTO>(esSearchService) {
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.executeWithNull(financeManageTransferCancelCommitEsDTO);
    }

    @Override
    public boolean deleteById(Long id) {
        return new DefaultAbstractSearchDeleteExecutor<FinanceManageCashTransferEsDTO>(esSearchService){
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(id);
    }

    /**
     * 详情查询
     * @param id
     * @return
     */
    @Override
    public FinanceManageCashTransferEsDTO getDetails(Long id) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("id",id,ConditionExpressionEnum.EQUAL);
        List<SearchCondition> searchConditions = utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageCashTransferEsDTO>(esSearchService){

            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageCashTransfer();
            }
        }.get(id);
    }

    /**
     * 打印主表
     * @param req
     * @return
     */
    @Override
    public FinanceManageCashTransferEsDTO printCash(FinanceManagePrintCash req) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("id",req.getId(),ConditionExpressionEnum.EQUAL);
        List<SearchCondition> searchConditions = utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageCashTransferEsDTO>(esSearchService){

            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageCashTransfer();
            }
        }.get(searchConditions);
    }

    /**
     * 查询现金转账列表
     * @param financeManageCashTransferListReqDTO
     * @return
     */
    @Override
    public List<FinanceManageCashTransferEsDTO> getEsList(FinanceManageCashTransferListReqDTO financeManageCashTransferListReqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsListSearchConditionUtils(utils, financeManageCashTransferListReqDTO);
        List<SearchCondition> searchConditions=utils.end();
        // 分页条件
        PageCondition pageCondition = new PageCondition();
        pageCondition.setCurrentPage(financeManageCashTransferListReqDTO.getCurrentPage());
        pageCondition.setPageSize(financeManageCashTransferListReqDTO.getPageSize());

        // 排序条件
        List<OrderCondition> orderConditions = new ArrayList<>();
        OrderCondition orderCondition = new OrderCondition();
        orderCondition.setFieldName("createTime");
        orderCondition.setOrderCondition(SortEnum.DESC);
        orderConditions.add(orderCondition);
        return new DefaultAbstractSearchQueryExecutor<FinanceManageCashTransferEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageCashTransfer();
            }
        }.list(searchConditions,orderConditions,pageCondition);
    }

    /**
     * 查询总数
     * @param financeManageCashTransferListReqDTO
     * @return
     */
    @Override
    public Integer getEsListCount(FinanceManageCashTransferListReqDTO financeManageCashTransferListReqDTO) {
        Integer count = 0;
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsListSearchConditionUtils(utils, financeManageCashTransferListReqDTO);
        List<SearchCondition> searchConditions=utils.end();
        count = new DefaultAbstractSearchAggregateExecutor<FinanceManageCashTransferEsDTO>(statisticService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageCashTransfer();
            }
        }.count(searchConditions);
        return count;
    }

    /**
     * 查询转账新增列表
     * @param financeManageCashTransferAddReqDTO
     * @return
     */
   /* @Override
    public List<FinanceManageCashSerialEsDTO> getEsForTransferAddList(FinanceManageCashTransferAddReqDTO financeManageCashTransferAddReqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsForCashTransferAddListSearchConditionUtils(utils, financeManageCashTransferAddReqDTO);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {

                return EsConfig.financeManageCashTransfer();
            }
        }.list(searchConditions);
    }*/

    /**
     * 查询来源单号
     * @param financeManageCashTransferCodeReqDTO
     * @return
     */
    /*@Override
    public List<FinanceManageCashSerialEsDTO> getCodeByTransfer(FinanceManageCashTransferCodeReqDTO financeManageCashTransferCodeReqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        if(StringUtils.isNotBlank(financeManageCashTransferCodeReqDTO.getCode())){
            utils.addCondition("ordersourceId",financeManageCashTransferCodeReqDTO.getCode(),ConditionExpressionEnum.LIKE);
        }else {
        	return null;
        }
        if(financeManageCashTransferCodeReqDTO.getIdList()!=null && financeManageCashTransferCodeReqDTO.getIdList().size()>0){
            utils.addCondition("id",financeManageCashTransferCodeReqDTO.getIdList(),ConditionExpressionEnum.IN);
        }
        Integer [] transferStatus = {FinanceTransferStatusEnum.UNTRANSFER.getType()};
        utils.addCondition("transferStatus",transferStatus,ConditionExpressionEnum.IN);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageCashTransfer();
            }
        }.list(searchConditions);

    }*/

    /**
     * 根据submitBillId查询
     * @param submitBillId
     * @return
     */
    @Override
    public FinanceManageCashTransferEsDTO queryListBySubmitBillId(Long submitBillId) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("id",submitBillId,ConditionExpressionEnum.EQUAL);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageCashTransferEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageCashTransfer();
            }
        }.get(searchConditions);

    }

    /**
     * 根据来id快速添加
     * @param
     * @return
     */
    /*@Override
    public List<FinanceManageCashSerialEsDTO> getEsByCodeForTransfer(Long id) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("id",id,ConditionExpressionEnum.EQUAL);
        Integer [] transferStatus = {FinanceTransferStatusEnum.UNTRANSFER.getType()};
        utils.addCondition("transferStatus",transferStatus,ConditionExpressionEnum.IN);
        List<SearchCondition> searchConditions = utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageCashSerialEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageCashTransfer();
            }
        }.list(searchConditions);
    }*/

    private SearchConditionUtils makeEsListSearchConditionUtils(SearchConditionUtils utils,FinanceManageCashTransferListReqDTO financeManageCashTransferListReqDTO){
    	if(financeManageCashTransferListReqDTO.getCompanyId()!=null) {
			utils.addEqualCondition("companyId", financeManageCashTransferListReqDTO.getCompanyId());
		}
    	if(financeManageCashTransferListReqDTO.getDateType()!=null&&financeManageCashTransferListReqDTO.getDateStart()!=null
                &&financeManageCashTransferListReqDTO.getDateEnd()!=null){
            switch (financeManageCashTransferListReqDTO.getDateType()){
                case 0:
                    utils.addCondition("createbillTime",financeManageCashTransferListReqDTO.getDateStart(),financeManageCashTransferListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
                case 1:
                    utils.addCondition("submitTime",financeManageCashTransferListReqDTO.getDateStart(),financeManageCashTransferListReqDTO.getDateEnd(),ConditionExpressionEnum.BETWEEN);
                    break;
                case 2:
                    utils.addCondition("incomeTime",financeManageCashTransferListReqDTO.getDateStart(),financeManageCashTransferListReqDTO.getDateEnd(),ConditionExpressionEnum.BETWEEN);
                    break;
            }
        }
        if(financeManageCashTransferListReqDTO.getBelongNetworkType()!=null&&financeManageCashTransferListReqDTO.getBelongNetwork()!=null&&financeManageCashTransferListReqDTO.getBelongNetwork().size()>0){
            switch (financeManageCashTransferListReqDTO.getBelongNetworkType()){
                case 0:
                    utils.addCondition("transferNetworkId",financeManageCashTransferListReqDTO.getBelongNetwork().toArray(),ConditionExpressionEnum.IN);
                    break;
                case 1:
                    utils.addCondition("incomeNetworkId",financeManageCashTransferListReqDTO.getBelongNetwork().toArray(),ConditionExpressionEnum.IN);
                    break;
            }
        }
        if(financeManageCashTransferListReqDTO.getTransferAccount()!=null && financeManageCashTransferListReqDTO.getTransferAccount().size()>0){
            utils.addCondition("transferAccount",financeManageCashTransferListReqDTO.getTransferAccount().toArray(),ConditionExpressionEnum.IN);
        }
        if(financeManageCashTransferListReqDTO.getIncomeAccount()!=null && financeManageCashTransferListReqDTO.getIncomeAccount().size()>0){
            utils.addCondition("incomeAccount",financeManageCashTransferListReqDTO.getIncomeAccount().toArray(),ConditionExpressionEnum.IN);
        }
        if(StringUtils.isNotBlank(financeManageCashTransferListReqDTO.getTransferOrdersource())){
            utils.addCondition("transferOrdersource",financeManageCashTransferListReqDTO.getTransferOrdersource(),ConditionExpressionEnum.LIKE);
        }
        if(CollectionUtils.isNotEmpty(financeManageCashTransferListReqDTO.getTransferOrdersourceList())){
            utils.addCondition("transferOrdersource",financeManageCashTransferListReqDTO.getTransferOrdersourceList().toArray(),ConditionExpressionEnum.IN);
        }
        if(financeManageCashTransferListReqDTO.getTransferStatus()!=null){
            utils.addEqualCondition("transferStatus",financeManageCashTransferListReqDTO.getTransferStatus());
        }
        return utils;
    }

   /* private SearchConditionUtils makeEsForCashTransferAddListSearchConditionUtils(SearchConditionUtils utils,FinanceManageCashTransferAddReqDTO financeManageCashTransferAddReqDTO){
        if(financeManageCashTransferAddReqDTO.getDateType()!=null&&financeManageCashTransferAddReqDTO.getDateStart()!=null
                &&financeManageCashTransferAddReqDTO.getDateEnd()!=null){
            switch (financeManageCashTransferAddReqDTO.getDateType()){
                case 0:
                    utils.addCondition("createbillTime",financeManageCashTransferAddReqDTO.getDateStart(),financeManageCashTransferAddReqDTO.getDateEnd(),ConditionExpressionEnum.BETWEEN);
                    break;
                case 1:
                    utils.addCondition("happenTime",financeManageCashTransferAddReqDTO.getDateStart(),financeManageCashTransferAddReqDTO.getDateEnd(),ConditionExpressionEnum.BETWEEN);
                    break;
            }
        }
        if(financeManageCashTransferAddReqDTO.getCodeType()!=null && financeManageCashTransferAddReqDTO.getCodeType().size()>0){
            utils.addCondition("codeType",financeManageCashTransferAddReqDTO.getCodeType().toArray(),ConditionExpressionEnum.IN);
        }
        if(StringUtils.isNotBlank(financeManageCashTransferAddReqDTO.getOrdersourceId())){
            utils.addCondition("ordersourceId",financeManageCashTransferAddReqDTO.getOrdersourceId(),ConditionExpressionEnum.LIKE);
        }
        if(financeManageCashTransferAddReqDTO.getSerialId()!=null && financeManageCashTransferAddReqDTO.getSerialId().size()>0){
            utils.addCondition("serialId",financeManageCashTransferAddReqDTO.getSerialId().toArray(),ConditionExpressionEnum.IN);
        }
        Integer [] transferStatus = {FinanceTransferStatusEnum.UNTRANSFER.getType()};
        utils.addCondition("transferStatus",transferStatus,ConditionExpressionEnum.IN);
        return utils;
    }*/
}
