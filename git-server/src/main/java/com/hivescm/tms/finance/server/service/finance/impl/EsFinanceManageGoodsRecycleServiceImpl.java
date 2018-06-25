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
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsRecycleEsDTO;
import com.hivescm.tms.api.dto.es.finance.query.FinanceManageRecyleQueryDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceDeleteReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageRecycleCodeReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageRecycleConfirmQueryReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageRecycleListReqDTO;
import com.hivescm.tms.api.enums.finance.PayWayEnum;
import com.hivescm.tms.api.enums.finance.RecycleStatusEnum;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageGoodsRecycleService;
import com.hivescm.tms.utils.StringUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 15:49
 * @company 蜂网供应链管理（上海）有限公司
 */
@Service
@Slf4j
public class EsFinanceManageGoodsRecycleServiceImpl implements EsFinanceManageGoodsRecycleService {
    @Autowired
    private ESSearchService esSearchService;

    @Autowired
    private ESStatisticService statisticService;

    /**
     * es配置
     */
    private static TypeIndexConfiguration typeIndexConfiguration = EsConfig.financeManageGoodsRecycle();

    @Override
    public Boolean insert(FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO) {
        return new DefaultAbstractSearchSaveExecutor<FinanceManageGoodsRecycleEsDTO>(esSearchService) {
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(financeManageGoodsRecycleEsDTO);
    }

    @Override
    public Boolean update(FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO) {
        return new DefaultAbstractSearchSaveExecutor<FinanceManageGoodsRecycleEsDTO>(esSearchService) {
            @Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
        }.execute(financeManageGoodsRecycleEsDTO);
    }

	@Override
	public FinanceManageGoodsRecycleEsDTO findByID(Long id) {
		List<SearchCondition> searchCondition = SearchConditionUtils.start().addEqualCondition("id", id).end();
		return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsRecycleEsDTO>(esSearchService) {
			@Override
            public TypeIndexConfiguration getConfig() {
                return typeIndexConfiguration;
            }
		}.get(searchCondition);
	}

    /**
     * 查询货款回收列表
     * @param financeManageRecycleListReqDTO
     * @return
     */
    @Override
    public List<FinanceManageGoodsRecycleEsDTO> getEsList(FinanceManageRecycleListReqDTO financeManageRecycleListReqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsListSearchConditionUtils(utils,financeManageRecycleListReqDTO);
        List<SearchCondition> searchConditions = utils.end();
        //分页条件
        PageCondition pageCondition = new PageCondition();
        pageCondition.setCurrentPage(financeManageRecycleListReqDTO.getCurrentPage());
        pageCondition.setPageSize(financeManageRecycleListReqDTO.getPageSize());
        // 排序条件
        List<OrderCondition> orderConditions = new ArrayList<>();
        OrderCondition orderCondition = new OrderCondition();
        orderCondition.setFieldName("createTime");
        orderCondition.setOrderCondition(SortEnum.DESC);
        orderConditions.add(orderCondition);
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsRecycleEsDTO>(esSearchService){
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsRecycle();
            }
        }.list(searchConditions,orderConditions,pageCondition);
    }

    /**
     * 查询总数
     * @param financeManageRecycleListReqDTO
     * @return
     */
    @Override
    public Integer getEsListCount(FinanceManageRecycleListReqDTO financeManageRecycleListReqDTO) {
        Integer count = 0;
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsListSearchConditionUtils(utils, financeManageRecycleListReqDTO);
        List<SearchCondition> searchConditions=utils.end();
        count = new DefaultAbstractSearchAggregateExecutor<FinanceManageGoodsRecycleEsDTO>(statisticService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsRecycle();
            }
        }.count(searchConditions);
        return count;
    }

    /**
     * 查询回收确认列表
     * @param financeManageRecycleConfirmQueryReqDTO
     * @return
     */
    @Override
    public List<FinanceManageGoodsRecycleEsDTO> getEsForRecyckeConfirmList(FinanceManageRecycleConfirmQueryReqDTO financeManageRecycleConfirmQueryReqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils = makeEsForRecycleConfirmListSearchConditionUtils(utils, financeManageRecycleConfirmQueryReqDTO);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsRecycleEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {

                return EsConfig.financeManageGoodsRecycle();
            }
        }.list(searchConditions);
    }

    /**
     * 查询运单号
     * @param financeManageRecycleCodeReqDTO
     * @return
     */
    @Override
    public List<FinanceManageGoodsRecycleEsDTO> getCodeByRecycle(FinanceManageRecycleCodeReqDTO financeManageRecycleCodeReqDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        if(StringUtils.isNotBlank(financeManageRecycleCodeReqDTO.getCode())){
            utils.addCondition("orderSourceCode",financeManageRecycleCodeReqDTO.getCode(),ConditionExpressionEnum.LIKE);
        }else {
        	return null;
        }
        if(financeManageRecycleCodeReqDTO.getIdList()!=null && financeManageRecycleCodeReqDTO.getIdList().size()>0){
            utils.addCondition("id",financeManageRecycleCodeReqDTO.getIdList().toArray(),ConditionExpressionEnum.NOT_IN);
        }
        Integer [] recycleStatus = {RecycleStatusEnum.UNRECYCLE.getType(),RecycleStatusEnum.PARY_RECYCLE.getType()};
        utils.addCondition("recycleStatus",recycleStatus,ConditionExpressionEnum.IN);
        utils.addEqualCondition("receiveBranchId", financeManageRecycleCodeReqDTO.getOrgId());
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsRecycleEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsRecycle();
            }
        }.list(searchConditions);
    }

    /**
     * 查询运单号
     * @param financeManageRecyleQueryDTO
     * @return
     */
    @Override
    public FinanceManageGoodsRecycleEsDTO getRecycleByWaybillCode(FinanceManageRecyleQueryDTO financeManageRecyleQueryDTO) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("orderSourceCode",financeManageRecyleQueryDTO.getWaybillCode(),ConditionExpressionEnum.EQUAL);
        utils.addCondition("companyId",financeManageRecyleQueryDTO.getCompanyId(),ConditionExpressionEnum.EQUAL);
//        utils.addCondition("payWay", PayWayEnum.COLLECTIONOFGOODS.getType(), ConditionExpressionEnum.EQUAL);
        Integer [] recycleStatus = {RecycleStatusEnum.UNRECYCLE.getType(),RecycleStatusEnum.PARY_RECYCLE.getType()};
        utils.addCondition("recycleStatus",recycleStatus,ConditionExpressionEnum.IN);
        List<SearchCondition> searchConditions=utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsRecycleEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsRecycle();
            }
        }.get(searchConditions);
    }

    /**
     * 根据id快速添加
     * @param id
     * @return
     */
    @Override
    public List<FinanceManageGoodsRecycleEsDTO> getEsByCodeForRecycle(Long id) {
        SearchConditionUtils utils = SearchConditionUtils.start();
        utils.addCondition("id",id,ConditionExpressionEnum.EQUAL);
        Integer [] recycleStatus = {RecycleStatusEnum.UNRECYCLE.getType(),RecycleStatusEnum.PARY_RECYCLE.getType()};
        utils.addCondition("recycleStatus",recycleStatus,ConditionExpressionEnum.IN);
        List<SearchCondition> searchConditions = utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsRecycleEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsRecycle();
            }
        }.list(searchConditions);
    }

    private SearchConditionUtils makeEsListSearchConditionUtils(SearchConditionUtils utils,FinanceManageRecycleListReqDTO financeManageRecycleListReqDTO){
    	if(financeManageRecycleListReqDTO.getCompanyId()!=null) {
			utils.addEqualCondition("companyId", financeManageRecycleListReqDTO.getCompanyId());
		}
    	if(financeManageRecycleListReqDTO.getDateType()!=null&&financeManageRecycleListReqDTO.getDateStart()!=null
                &&financeManageRecycleListReqDTO.getDateEnd()!=null){
            switch (financeManageRecycleListReqDTO.getDateType()){
                case 0:
                    utils.addCondition("waybillCreateTime",financeManageRecycleListReqDTO.getDateStart(),financeManageRecycleListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
                case 1:
                    utils.addCondition("signTime",financeManageRecycleListReqDTO.getDateStart(),financeManageRecycleListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
                case 2:
                    utils.addCondition("recycleTime",financeManageRecycleListReqDTO.getDateStart(),financeManageRecycleListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
                case 3:
                    utils.addCondition("remitTime",financeManageRecycleListReqDTO.getDateStart(),financeManageRecycleListReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
                    break;
            }
        }
        if(financeManageRecycleListReqDTO.getReceiveBranchId()!=null && financeManageRecycleListReqDTO.getReceiveBranchId().size()>0){
            utils.addCondition("receiveBranchId",financeManageRecycleListReqDTO.getReceiveBranchId().toArray(),ConditionExpressionEnum.IN);
        }
        if(financeManageRecycleListReqDTO.getInvoiceOrgId()!=null && financeManageRecycleListReqDTO.getInvoiceOrgId().size()>0){
            utils.addCondition("invoiceOrgId",financeManageRecycleListReqDTO.getInvoiceOrgId().toArray(),ConditionExpressionEnum.IN);
        }
        if(financeManageRecycleListReqDTO.getDestOrgId()!=null && financeManageRecycleListReqDTO.getDestOrgId().size()>0){
            utils.addCondition("destOrgId",financeManageRecycleListReqDTO.getDestOrgId().toArray(),ConditionExpressionEnum.IN);
        }
        if(StringUtils.isNotBlank(financeManageRecycleListReqDTO.getOrderSourceCode())){
            utils.addCondition("orderSourceCode",financeManageRecycleListReqDTO.getOrderSourceCode(),ConditionExpressionEnum.LIKE);
        }
        if(CollectionUtils.isNotEmpty(financeManageRecycleListReqDTO.getOrderSourceCodeList())){
            utils.addCondition("orderSourceCode",financeManageRecycleListReqDTO.getOrderSourceCodeList().toArray(),ConditionExpressionEnum.IN);
        }
        if(financeManageRecycleListReqDTO.getSignStatus()!=null){
            utils.addEqualCondition("signStatus",financeManageRecycleListReqDTO.getSignStatus());
        }
        if(financeManageRecycleListReqDTO.getRecycleStatus()!=null){
            utils.addEqualCondition("recycleStatus",financeManageRecycleListReqDTO.getRecycleStatus());
        }
        if(financeManageRecycleListReqDTO.getRemitStatus()!=null){
            utils.addEqualCondition("remitStatus",financeManageRecycleListReqDTO.getRemitStatus());
        }
        if(StringUtils.isNotBlank(financeManageRecycleListReqDTO.getPayeeName())){
            utils.addCondition("payeeName",financeManageRecycleListReqDTO.getPayeeName(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageRecycleListReqDTO.getReceiptCompanyName())){
            utils.addCondition("receiptCompanyName",financeManageRecycleListReqDTO.getReceiptCompanyName(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageRecycleListReqDTO.getReceiptUserName())){
            utils.addCondition("receiptUserName",financeManageRecycleListReqDTO.getReceiptUserName(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageRecycleListReqDTO.getReceiptUserMobile())){
            utils.addCondition("receiptUserMobile",financeManageRecycleListReqDTO.getReceiptUserMobile(),ConditionExpressionEnum.LIKE);
        }
        if(StringUtils.isNotBlank(financeManageRecycleListReqDTO.getTransitCompanyName())){
            utils.addCondition("transitCompanyName",financeManageRecycleListReqDTO.getTransitCompanyName(),ConditionExpressionEnum.LIKE);
        }
        return utils;
    }
    private SearchConditionUtils makeEsForRecycleConfirmListSearchConditionUtils(SearchConditionUtils utils,FinanceManageRecycleConfirmQueryReqDTO financeManageRecycleConfirmQueryReqDTO){
		if (financeManageRecycleConfirmQueryReqDTO.getDateType() != null&&financeManageRecycleConfirmQueryReqDTO.getDateStart()!=null
                &&financeManageRecycleConfirmQueryReqDTO.getDateEnd()!=null) {
			switch (financeManageRecycleConfirmQueryReqDTO.getDateType()) {
			case 0:
				utils.addCondition("waybillCreateTime", financeManageRecycleConfirmQueryReqDTO.getDateStart(),
						financeManageRecycleConfirmQueryReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
				break;
			case 1:
				utils.addCondition("signTime", financeManageRecycleConfirmQueryReqDTO.getDateStart(),
						financeManageRecycleConfirmQueryReqDTO.getDateEnd(), ConditionExpressionEnum.BETWEEN);
				break;
			}
		}
		if (StringUtils.isNotBlank(financeManageRecycleConfirmQueryReqDTO.getReceiptCompanyName())) {
			utils.addCondition("receiptCompanyName", financeManageRecycleConfirmQueryReqDTO.getReceiptCompanyName(),
					ConditionExpressionEnum.LIKE);
		}
		if (StringUtils.isNotBlank(financeManageRecycleConfirmQueryReqDTO.getTransitCompanyName())) {
			utils.addCondition("transitCompanyName", financeManageRecycleConfirmQueryReqDTO.getTransitCompanyName(),
					ConditionExpressionEnum.LIKE);
		}
		if (StringUtils.isNotBlank(financeManageRecycleConfirmQueryReqDTO.getOrderSourceCode())) {
			utils.addCondition("orderSourceCode", financeManageRecycleConfirmQueryReqDTO.getOrderSourceCode(),
					ConditionExpressionEnum.LIKE);
		}
		Integer[] recycleStatus = { RecycleStatusEnum.UNRECYCLE.getType(), RecycleStatusEnum.PARY_RECYCLE.getType() };
		utils.addCondition("recycleStatus", recycleStatus, ConditionExpressionEnum.IN);
		utils.addEqualCondition("receiveBranchId", financeManageRecycleConfirmQueryReqDTO.getOrgId());
		return utils;
    }

	@Override
	public List<FinanceManageGoodsRecycleEsDTO> getPayStatusByCodeSign(FinanceDeleteReqDTO financeDeleteReqDTO) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		if(StringUtils.isNotBlank(financeDeleteReqDTO.getCode()))
			utils.addCondition("orderSourceCode",financeDeleteReqDTO.getCode(),ConditionExpressionEnum.EQUAL);
		if(financeDeleteReqDTO.getWaybillId()!=null)
			utils.addCondition("waybillId",financeDeleteReqDTO.getWaybillId(),ConditionExpressionEnum.EQUAL);
		if(financeDeleteReqDTO.getTransitBillId()!=null)
			utils.addEqualCondition("transitBillId", financeDeleteReqDTO.getTransitBillId());
		if(StringUtil.isNotBlank(financeDeleteReqDTO.getTransitCode()))
			utils.addEqualCondition("transitBillCode", financeDeleteReqDTO.getTransitCode());
        Integer [] recycleStatus = {RecycleStatusEnum.RECYCLEED.getType(),RecycleStatusEnum.PARY_RECYCLE.getType()};
        utils.addCondition("recycleStatus",recycleStatus,ConditionExpressionEnum.IN);
        List<SearchCondition> searchConditions = utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsRecycleEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsRecycle();
            }
        }.list(searchConditions);
	}

	@Override
	public FinanceManageGoodsRecycleEsDTO findByWaybillId(Long id) {
		SearchConditionUtils utils = SearchConditionUtils.start();
		utils.addCondition("waybillId",id,ConditionExpressionEnum.EQUAL);
        List<SearchCondition> searchConditions = utils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsRecycleEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsRecycle();
            }
        }.get(searchConditions);
	}

    @Override
    public Boolean deleteEsByWaybillId(Long waybillId) {
    	SearchConditionUtils utils = SearchConditionUtils.start();
		utils.addCondition("waybillId",waybillId,ConditionExpressionEnum.EQUAL);
        List<SearchCondition> searchConditions = utils.end();
        return new DefaultAbstractSearchDeleteExecutor<FinanceManageGoodsRecycleEsDTO>(esSearchService){
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsRecycle();
            }
        }.execute(searchConditions);
    }

	@Override
	public Boolean updateByBatch(List<FinanceManageGoodsRecycleEsDTO> rollReceipt) {
		return new DefaultAbstractSearchUpdateExecutor<FinanceManageGoodsRecycleEsDTO>(esSearchService) {
			@Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsRecycle();
            }
		}.execute(rollReceipt);
	}

    @Override
    public List<FinanceManageGoodsRecycleEsDTO> findByIdList(List<Long> idList, Integer branchId) {
        SearchConditionUtils searchConditionUtils = SearchConditionUtils.start();
        if(null!=branchId && branchId>0){//当前网点==收款网点
            searchConditionUtils.addEqualCondition("receiveBranchId",branchId);
        }
        if(CollectionUtils.isNotEmpty(idList)){
            searchConditionUtils.addCondition("id",idList.toArray(),ConditionExpressionEnum.IN);
        }
        List<SearchCondition> searchConditions = searchConditionUtils.end();
        return new DefaultAbstractSearchQueryExecutor<FinanceManageGoodsRecycleEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.financeManageGoodsRecycle();
            }
        }.list(searchConditions);
    }
}
