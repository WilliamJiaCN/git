package com.hivescm.tms.finance.server.component.sign.impl;

import com.hivescm.common.domain.DataResult;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillGoodsEsDTO;
import com.hivescm.tms.finance.server.component.sign.AllSignForListService;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <p>
 * Title:AllSignForListServiceImpl
 * </p>
 * <p>
 * Description: 全部签收的条件查询
 * </p>
 * <p>
 * Company: 蜂网供应链（上海）有限公司
 * </p>
 * 
 * @author 王小雪
 * @date 上午11:09:01
 */
@Service
public class AllSignForListServiceImpl implements AllSignForListService {
	private static Logger logger = LoggerFactory.getLogger(AllSignForListServiceImpl.class);
	@Autowired
	private ESSearchService eSSearchService;

    @Autowired
    private WaybillService waybillService;
	/**
	 * 签收详情
	 * @param signId
	 * @return
	 */
	@Override
	public DataResult<List<SignDetailsEsDTO>> getSignGoodsDetails(Long signId) {
		DataResult<List<SignDetailsEsDTO>> dataResult = new DataResult<>();
		List<SearchCondition> searchConditions = new ArrayList<>();
		SearchCondition searchCondition = SearchConditionUtils.newEqualCondition("signId", signId);
		searchConditions.add(searchCondition);
		List<SignDetailsEsDTO> list = new DefaultAbstractSearchQueryExecutor<SignDetailsEsDTO>(
				eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.signGoodsDetail();
			}
		}.list(searchConditions);
		dataResult.setResult(list);
		return dataResult;
	}

    @Override
    public DataResult<List<SignDetailsEsDTO>> getGoodsDetails(Integer type, Long signId) {
	    // TODO 未签收时
        if (type == 0) {
            return getWaybillGoods(signId);
        } else {
            return getSignGoodsDetails(signId);
        }
    }


	private DataResult<List<SignDetailsEsDTO>> getWaybillGoods(Long waybillId) {
		DataResult<List<SignDetailsEsDTO>> dataResult = new DataResult<>();
		List<WaybillGoodsEsDTO> list =  waybillService.queryWaybillGoodsEsDTOList(waybillId);
		List<SignDetailsEsDTO> signDetailsEsDTOList = new ArrayList<>();
		if (list != null && list.size() != 0) {
			list.forEach(waybillGoods -> {
				SignDetailsEsDTO signDetailsEsDTO = EntityUtils.copyProperties(waybillGoods, SignDetailsEsDTO.class);
				signDetailsEsDTO.setSignNumber(0);
				signDetailsEsDTO.setRefuseNumber(0);
				signDetailsEsDTO.setCreateNumber(signDetailsEsDTO.getPackageNum());
				signDetailsEsDTO.setUnsignedNumber(signDetailsEsDTO.getPackageNum());
				signDetailsEsDTOList.add(signDetailsEsDTO);
			});
		}
		dataResult.setResult(signDetailsEsDTOList);
		return dataResult;
	}

}