package com.hivescm.tms.finance.server.service.sign.impl;

import com.google.common.collect.Lists;
import com.hivescm.common.domain.PagedList;
import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.escenter.service.ESStatisticService;
import com.hivescm.framework.elasticsearch.conf.TypeIndexConfiguration;
import com.hivescm.framework.elasticsearch.executor.impl.*;
import com.hivescm.framework.elasticsearch.utils.OrderConditionUtils;
import com.hivescm.framework.elasticsearch.utils.PageConditionUtils;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.PCSignReq;
import com.hivescm.tms.api.dto.es.sign.request.SearchSignCodeReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignRefuseRespDTO;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.service.sign.EsSignService;
import com.hivescm.tms.finance.server.service.sign.SingleFiledDistinctQueryExecutor;
import com.hivescm.tms.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 送货签收
 *
 * @author 杨彭伟
 * @date 2018-01-29 16:07
 */
@Service
public class EsSignServiceImpl implements EsSignService {
	@Autowired
	private ESSearchService eSSearchService;

	@Autowired
	private ESStatisticService esStatisticService;

    /**
     * 去重 返回totalDoc 有问题  所以不用了
     * @param filedName
     * @param scs
     * @param orderConditions
     * @param pageCondition
     * @return
     */
    @Deprecated
    @Override
	public Set<String> queryDistinctResult(String filedName, List<SearchCondition> scs , List<OrderCondition> orderConditions, PageCondition pageCondition) {


        final SingleFiledDistinctQueryExecutor executor = new SingleFiledDistinctQueryExecutor(esStatisticService) {
            @Override
            public TypeIndexConfiguration getConfig() {
                return EsConfig.sign();
            }
        };

        final Set<String> strings = executor.distinctStringSet(filedName, scs, orderConditions, pageCondition);


        return strings;
    }
    @Deprecated
    @Override
    public Set<String> queryDistinctResult(String filedName,PCSignReq pcSignReq) {
        List<SearchCondition> scs = this.compSignSearchCondition(pcSignReq);
        List<OrderCondition> orderConditions = OrderConditionUtils.start().addCondition("createTime", SortEnum.DESC)
                .end();
            PageCondition pageCondition = PageConditionUtils.create(pcSignReq.getPageSize(),
                pcSignReq.getCurrentPage());
        return queryDistinctResult(filedName,scs,orderConditions,pageCondition);
	}

    @Override
    public List<SignEsDTO> querySignEsByWaybillCodes(Collection<String> codes) {
        List<SearchCondition> searchConditions = SearchConditionUtils.start()
                .addCondition("code", codes.toArray(), ConditionExpressionEnum.IN).end();
        return new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.list(searchConditions);
    }

    @Override
	public SignEsDTO querySignEsDTO(Long waybillId) {
		List<SearchCondition> s = SearchConditionUtils.start().addEqualCondition("waybillId", waybillId).end();
		return new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.sign();
			}
		}.get(s);
	}

	@Override
	public SignEsDTO querySignEsDTOById(Long id){
		List<SearchCondition> searchConditions = SearchConditionUtils.start().addEqualCondition("id", id).end();
		return new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService){
			@Override
			public EsConfig getConfig(){return  EsConfig.sign(); }
		}.get(searchConditions);
	}


	@Override
	public Boolean insertSignEsDTO(SignEsDTO signEsDTO) {
		return new DefaultAbstractSearchSaveExecutor<SignEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.sign();
			}
		}.execute(signEsDTO);
	}

	@Override
	public Boolean insertSignDetailsEsDTO(List<SignDetailsEsDTO> signDetailsEsDTOList) {
		return new DefaultAbstractSearchSaveExecutor<SignDetailsEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.signGoodsDetail();
			}
		}.execute(signDetailsEsDTOList);
	}

	@Override
	public Boolean insertSignFeeEsDTO(SignFeeEsDTO signFeeEsDTO) {
		return new DefaultAbstractSearchSaveExecutor<SignFeeEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.signFee();
			}
		}.execute(signFeeEsDTO);
	}

	@Override
	public void deleteSignEsDTOById(Long id) {
		new DefaultAbstractSearchDeleteExecutor<SignEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.sign();
			}
		}.execute(id);
	}

	@Override
	public void deleteSignDetailsEsDTOById(Long signId) {
		List<SearchCondition> searchConditions = Lists.newArrayList();
		SearchCondition condition = SearchConditionUtils.newEqualCondition("signId", signId);
		searchConditions.add(condition);
		new DefaultAbstractSearchDeleteExecutor<SignEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.signGoodsDetail();
			}
		}.execute(searchConditions);
	}

	@Override
	public void deleteSignFeeEsDTOById(Long signId) {
		List<SearchCondition> searchConditions = Lists.newArrayList();
		SearchCondition condition = SearchConditionUtils.newEqualCondition("signId", signId);
		searchConditions.add(condition);
		new DefaultAbstractSearchDeleteExecutor<SignFeeEsDTO>(eSSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.signFee();
			}
		}.execute(searchConditions);
	}

    @Override
    public Boolean updateRefuseSign(SignRefuseEsDTO signRefuseEsDTO) {
        return new DefaultAbstractSearchUpdateExecutor<SignRefuseEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.execute(signRefuseEsDTO);
    }

    @Override
    public SignFeeEsDTO querySignFeeEsDTO(Long signId) {

        List<SearchCondition> s = SearchConditionUtils.start()
                .addEqualCondition("signId", signId).end();
        return new DefaultAbstractSearchQueryExecutor<SignFeeEsDTO>(eSSearchService) {
            @Override
            public TypeIndexConfiguration getConfig() {
                return EsConfig.signFee();
            }
        }.get(s);
    }


    @Override
    public Boolean updateSignEsDTO(SignEsDTO signEsDTO){
        return new DefaultAbstractSearchUpdateExecutor<SignEsDTO>(eSSearchService){
            @Override
            public EsConfig getConfig(){ return EsConfig.sign(); }
        }.execute(signEsDTO);
    }

    @Override
    public Integer countSignNumber(Long waybillId) {
        List<SearchCondition> searchConditions = SearchConditionUtils.start()
                .addCondition("waybillId", waybillId.toString(), ConditionExpressionEnum.EQUAL).end();
        Double signNumber = new DefaultAbstractSearchAggregateExecutor<Integer>(esStatisticService) {

            @Override
            public EsConfig getConfig() {

                return EsConfig.sign();
            }
        }.sum("signNumber", searchConditions);
        return signNumber.intValue();

    }

    public Integer countByConditions(List<SearchCondition> scs){
//        List<SearchCondition> searchConditions = SearchConditionUtils.start()
//                .addCondition("waybillId", waybillId.toString(), ConditionExpressionEnum.EQUAL).end();
        Integer count = new DefaultAbstractSearchAggregateExecutor<Integer>(esStatisticService) {

            @Override
            public EsConfig getConfig() {

                return EsConfig.sign();
            }
        }.count( scs);
        return count;


    }

    @Override
    public List<SignEsDTO> querySignEsByConditions(PCSignReq pcSignReq) {
        List<SearchCondition> scs = this.compSignSearchCondition(pcSignReq);
        List<OrderCondition> orderConditions = OrderConditionUtils.start().addCondition("createTime", SortEnum.DESC)
                .end();
        PageCondition pageCondition = PageConditionUtils.create(pcSignReq.getPageSize(),
                pcSignReq.getCurrentPage());
        return querySignEsByConditions(scs,orderConditions,pageCondition);
    }

    @Override
    public List<SignEsDTO> querySignEsByConditions(List<SearchCondition> scs , List<OrderCondition> orderConditions,PageCondition pageCondition) {

        List<SignEsDTO> list = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.list(scs, orderConditions, pageCondition);
        return list;
    }

    @Override
    public List<SignEsDTO> querySignEsByConditions(List<SearchCondition> scs , List<OrderCondition> orderConditions) {

        List<SignEsDTO> list = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.list(scs, orderConditions);
        return list;
    }
    @Override
    public List<SignRefuseEsDTO> queryRefuseSignEsByConditions(PCSignReq pcSignReq) {
        List<SearchCondition> scs = this.compSignSearchCondition(pcSignReq);
        List<OrderCondition> orderConditions = OrderConditionUtils.start().addCondition("createTime", SortEnum.DESC)
                .end();
        PageCondition pageCondition = PageConditionUtils.create(pcSignReq.getPageSize(),
                pcSignReq.getCurrentPage());
        List<SignRefuseEsDTO> list = new DefaultAbstractSearchQueryExecutor<SignRefuseEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.list(scs, orderConditions, pageCondition);
        return list;
    }

    @Override
    public List<SignRefuseEsDTO> queryRefuseSignEsByConditions(List<SearchCondition> scs, List<OrderCondition> orderConditions, PageCondition pageCondition) {
        List<SignRefuseEsDTO> list = new DefaultAbstractSearchQueryExecutor<SignRefuseEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.list(scs, orderConditions, pageCondition);
        return list;
    }
    @Override
    public List<SignRefuseRespDTO> queryRefuseRespByConditions(List<SearchCondition> scs, List<OrderCondition> orderConditions, PageCondition pageCondition) {
        List<SignRefuseRespDTO> list = new DefaultAbstractSearchQueryExecutor<SignRefuseRespDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.list(scs, orderConditions, pageCondition);
        return list;
    }

    /**
     * 组合查询条件
     *
     * @param pcSignReq
     * @return
     */
    private List<SearchCondition> compSignSearchCondition(PCSignReq pcSignReq) {
        List<SearchCondition> scs = new ArrayList<SearchCondition>();
         // s-> 目的网点
        if (!CollectionUtils.isEmpty(pcSignReq.getDestOrgId())) {
			SearchCondition scDest = new SearchCondition.Builder().setFieldName("destOrgId")
					.setConditionExpression(ConditionExpressionEnum.IN)
					.setFeldValues(pcSignReq.getDestOrgId().toArray()).build();
			scs.add(scDest);
		}
        if (null != pcSignReq.getCompanyId() && pcSignReq.getCompanyId() > 0) {
            SearchCondition companyId = SearchConditionUtils.newEqualCondition("companyId",
                    pcSignReq.getCompanyId());
            scs.add(companyId);
        }
        // ->发货网点
        if (!CollectionUtils.isEmpty(pcSignReq.getInvoiceOrgId())) {
			SearchCondition scInvoice = new SearchCondition.Builder().setFieldName("invoiceOrgId")
					.setConditionExpression(ConditionExpressionEnum.EQUAL)
					.setFeldValues(pcSignReq.getInvoiceOrgId().toArray()).build();
			scs.add(scInvoice);
		}

        // -> 签收人
        if (StringUtils.isNotBlank(pcSignReq.getSignPeople())) {
            SearchCondition scReceipt = new SearchCondition.Builder().setFieldName("signPeople")
                    .setConditionExpression(ConditionExpressionEnum.EQUAL).setSingleValue(pcSignReq.getSignPeople())
                    .build();
            scs.add(scReceipt);
        }

        // ->运单号
        if (StringUtils.isNotBlank(pcSignReq.getWaybillCode())) {
            SearchCondition scWaybillCode = new SearchCondition.Builder().setFieldName("code")
                    .setConditionExpression(ConditionExpressionEnum.EQUAL)
                    .setSingleValue(pcSignReq.getWaybillCode()).build();
            scs.add(scWaybillCode);
        }
        // ->签收单号
        if (StringUtils.isNotBlank(pcSignReq.getSignBatchNumber())) {
            SearchCondition scSignBatchNumber = new SearchCondition.Builder().setFieldName("signBatchNumber")
                    .setConditionExpression(ConditionExpressionEnum.EQUAL)
                    .setSingleValue(pcSignReq.getSignBatchNumber()).build();
            scs.add(scSignBatchNumber);
        }

        // ->签收日期
        if (null != pcSignReq.getStartTime() && null != pcSignReq.getEndTime()
                && pcSignReq.getStartTime() > 0 && pcSignReq.getEndTime() > 0) {
            SearchCondition scTime = new SearchCondition.Builder().setFieldName("signTime")
                    .setConditionExpression(ConditionExpressionEnum.BETWEEN_AND)
                    .setMinValue(pcSignReq.getStartTime().toString())
                    .setMxValue(pcSignReq.getEndTime().toString()).build();
            scs.add(scTime);
        }

        // -> 签收状态
        if (null != pcSignReq.getSignStatus()) {
            SearchCondition scStatus = new SearchCondition.Builder().setFieldName("signStatus")
                    .setConditionExpression(ConditionExpressionEnum.EQUAL)
                    .setSingleValue(String.valueOf(pcSignReq.getSignStatus().getType())).build();
            scs.add(scStatus);
        }
        if (null != pcSignReq.getCurrentDotId() && pcSignReq.getCurrentDotId() > 0) {
            SearchCondition signId = SearchConditionUtils.newEqualCondition("signOrgId",
            		pcSignReq.getCurrentDotId());
            scs.add(signId);
        }
        if (pcSignReq.getDeliveryType()!=null){

            SearchCondition deliveryType = new SearchCondition.Builder().setFieldName("deliveryType")
                    .setConditionExpression(ConditionExpressionEnum.EQUAL)
                    .setSingleValue(pcSignReq.getDeliveryType()).build();
            scs.add(deliveryType);
        }
        SearchCondition companyId = new SearchCondition.Builder().setFieldName("companyId")
                .setConditionExpression(ConditionExpressionEnum.EQUAL)
                .setSingleValue(pcSignReq.getCompanyId()).build();
        scs.add(companyId);
        return scs;
    }

    @Override
    public List<SignEsDTO> querySignEsByWaybillId(Long waybillId) {
        List<SearchCondition> scs = SearchConditionUtils.start()
                .addEqualCondition("waybillId", waybillId)
                .addCondition("signStatus", SignStatusEnum.CANCEL_SIGN.getType(), ConditionExpressionEnum.UNEQUAL)
                .end();
        List<SignEsDTO> signs = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.list(scs);
        return signs;
    }

    @Override
    public List<SignEsDTO> querySignEsByWaybillCode(String waybillCode) {
        List<SearchCondition> scs = SearchConditionUtils.start().addEqualCondition("code", waybillCode)
                .addCondition("signStatus",SignStatusEnum.CANCEL_SIGN.getType(),ConditionExpressionEnum.UNEQUAL)
                .end();
        List<SignEsDTO> signs = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.list(scs);
        return signs;
    }

    @Override
    public Boolean deleteBySignIds(List<Long> signIds) {
        List<SearchCondition> scs = SearchConditionUtils.start().addCondition("id", signIds.toArray(), ConditionExpressionEnum.IN)
                .end();
        Boolean es = new DefaultAbstractSearchDeleteExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.execute(scs);
        return es;
    }

    @Override
    public List<SignEsDTO> querySignEsByWaybillIds(List<Long> waybillIds) {
        List<SearchCondition> scs = SearchConditionUtils.start()
                .addCondition("waybillId", waybillIds.toArray(), ConditionExpressionEnum.IN)
                .addCondition("signStatus",SignStatusEnum.CANCEL_SIGN.getType(),ConditionExpressionEnum.UNEQUAL)
                .end();
        List<SignEsDTO> es = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.list(scs);
        return es;
    }
    @Override
    public List<SignEsDTO> querySignEsByIds(List<Long> ids) {
        List<SearchCondition> scs = SearchConditionUtils.start()
                .addCondition("id", ids.toArray(), ConditionExpressionEnum.IN)
                .addCondition("signStatus", SignStatusEnum.CANCEL_SIGN.getType(), ConditionExpressionEnum.UNEQUAL).end();
        List<SignEsDTO> es = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.list(scs);
        return es;
    }

    @Override
    public Boolean insertBatchSignEs(List<SignEsDTO> signEsDTOs) {
        Boolean flag = new DefaultAbstractSearchSaveExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.execute(signEsDTOs);
        return flag;
    }

    @Override
    public SignEsDTO querySignEsDTOByDispatcherDetailId(Long dispatcherDetailId) {
        List<SearchCondition> s = SearchConditionUtils.start().addEqualCondition("dispatcherDetailId", dispatcherDetailId).end();
        return new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.get(s);
    }
    @Override
    public SignEsDTO querySignEsDTOByDispatcherDetailIdNoCancel(Long dispatcherDetailId) {
        List<SearchCondition> s = SearchConditionUtils.start().addEqualCondition("dispatcherDetailId", dispatcherDetailId)
                .addCondition("signStatus",SignStatusEnum.CANCEL_SIGN.getType(),ConditionExpressionEnum.UNEQUAL).end();
        return new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.get(s);
    }

    @Override
    public List<SignDetailsEsDTO> querySignDetailBySignId(Long signId) {
        List<SearchCondition> s = SearchConditionUtils.start().addEqualCondition("signId", signId).end();
        return new DefaultAbstractSearchQueryExecutor<SignDetailsEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.signGoodsDetail();
            }
        }.list(s);
    }
    @Override
    public List<SignDetailsEsDTO> querySignDetailByDispatcherDetailId(Long dispatcherDetailId) {
        List<SearchCondition> s = SearchConditionUtils.start().addEqualCondition("dispatcherDetailId", dispatcherDetailId).end();
        return new DefaultAbstractSearchQueryExecutor<SignDetailsEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.signGoodsDetail();
            }
        }.list(s);
    }

	@Override
	public List<SignEsDTO> querySignInfoByWaybillId(Long waybillId) {
		  List<SearchCondition> s = SearchConditionUtils.start().addEqualCondition("waybillId", waybillId).end();
		  List<OrderCondition> end = OrderConditionUtils.start().addCondition("signTime", SortEnum.DESC).end();
		  List<SignEsDTO> resp =  new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
	            @Override
	            public EsConfig getConfig() {
	                return EsConfig.sign();
	            }
	        }.list(s,end);
		return resp;
	}
	
	 @Override
    public Boolean updateSignEsBatchById(List<SignEsDTO> signEsDTO){
        return new DefaultAbstractSearchUpdateExecutor<SignEsDTO>(eSSearchService){
            @Override
            public EsConfig getConfig(){ return EsConfig.sign(); }
        }.execute(signEsDTO);
    }
	 
	 
	@Override
	public List<SignEsDTO> querySignBySignBatchNumber(List<String> signBatchNumber) {
		 List<SearchCondition> s = SearchConditionUtils.start().addCondition("signBatchNumber", signBatchNumber.toArray(),ConditionExpressionEnum.IN).end();
		 
		List<SignEsDTO> signs = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.list(s);
		return signs;
	}
	@Override
	public Boolean updateSignById(List<SignEsDTO> sign) {
		return new DefaultAbstractSearchUpdateExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.execute(sign);
	}
	@Override
	public Integer countSignBills(PCSignReq pcSignReq) {
		Integer count = new DefaultAbstractSearchAggregateExecutor<SignEsDTO>(esStatisticService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.sign();
			}
		}.count(this.compSignSearchCondition(pcSignReq));
		return count==null?0:count;
	}

    @Override
    public Integer countRefuseSignBills(List<SearchCondition> scs) {
        Integer count = new DefaultAbstractSearchAggregateExecutor<SignEsDTO>(esStatisticService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.count(scs);
        return count==null?0:count;
    }
    @Override
	public SignEsDTO querySignBySignBatchNumber(String signBatchNumber,Long companyId) {
		List<SearchCondition> s = SearchConditionUtils.start().addCondition("signBatchNumber", signBatchNumber,ConditionExpressionEnum.EQUAL)
				.addEqualCondition("companyId", companyId).end();
		 
		SignEsDTO signs = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.get(s);
		return signs;
	}
	@Override
	public List<String> querySignNumber(SearchSignCodeReqDTO req) {
		List<Integer> status = new ArrayList<>();
		status.add(SignStatusEnum.PARTIAL_SIGN.getType());
		status.add(SignStatusEnum.SIGNED.getType());
		status.add(SignStatusEnum.REFUSE_SIGN.getType());
		List<SearchCondition> s = SearchConditionUtils.start().addCondition("code", req.getWaybillCode(),ConditionExpressionEnum.EQUAL)
				.addCondition("signOrgId", req.getCurrentDotId(),ConditionExpressionEnum.EQUAL)
				.addCondition("signStatus", status.toArray(), ConditionExpressionEnum.IN)
				.addEqualCondition("companyId", req.getCompanyId()).end();
		List<SignEsDTO> signs = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.list(s);
        if(CollectionUtils.isEmpty(signs)){
        	return null;
        }
        List<String> list = signs.stream().map(a->a.getSignBatchNumber()).collect(Collectors.toList());
		return list;
	}

    /**
     * 拒收单 模糊匹配
     * @param refuseSign
     * @param like
     * @param searchConditions
     * @param orderConditions
     * @param pageCondition
     * @return
     */
    @Override
    public PagedList<SignRefuseRespDTO> queryRefuseLikeRespByConditions(String refuseSign, String like, List<SearchCondition> searchConditions, List<OrderCondition> orderConditions, PageCondition pageCondition) {
        List<SignRefuseRespDTO> signs = new DefaultAbstractSearchQueryExecutor<SignRefuseRespDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.cascade(refuseSign,like,searchConditions,orderConditions,pageCondition);

        if (signs==null){
            return PagedList.createInstance(pageCondition.getCurrentPage(),pageCondition.getPageSize(),pageCondition.getTotalDocs().intValue(), Collections.EMPTY_LIST);
        }

        return PagedList.createInstance(pageCondition.getCurrentPage(),pageCondition.getPageSize(),pageCondition.getTotalDocs().intValue(),signs);
    }

    /**
     * 签收单 模糊匹配
     * @param cascadeName
     * @param like
     * @param searchConditions
     * @param orderConditions
     * @param pageCondition
     * @return
     */
    @Override
    public PagedList<SignEsDTO> querySignLikeRespByConditions(String cascadeName, String like, List<SearchCondition> searchConditions, List<OrderCondition> orderConditions, PageCondition pageCondition) {
        DefaultAbstractSearchQueryExecutor<SignEsDTO> queryExecutor = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        };
        List<SignEsDTO> signs =null;
        if (StringUtil.isNotBlank(like)) {
            //模糊查询,cascadeName必须和查询结果SignEsDTO类型注解相同的名字deliverySign
            signs = queryExecutor.cascade(cascadeName, like, searchConditions, orderConditions, pageCondition);
        }else {
            signs = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
                @Override
                public EsConfig getConfig() {
                    return EsConfig.sign();
                }
            }.list(searchConditions, orderConditions, pageCondition);
        }
        if (signs==null||signs.size()==0){
            return PagedList.createInstance(pageCondition.getCurrentPage(),pageCondition.getPageSize(),pageCondition.getTotalDocs().intValue(), Collections.EMPTY_LIST);
        }

        return PagedList.createInstance(pageCondition.getCurrentPage(),pageCondition.getPageSize(),pageCondition.getTotalDocs().intValue(),signs);
    }

    /**
     *
     * @param signId
     * @return
     */
    @Override
	public Boolean deleteBySignId(Long signId) {
        Boolean es = new DefaultAbstractSearchDeleteExecutor<SignEsDTO>(eSSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.execute(signId);
		return es;
	}

    @Override
	public List<SignEsDTO> querySignEsByConditions(List<SearchCondition> scs) {
		  List<SignEsDTO> list = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(eSSearchService) {
	            @Override
	            public EsConfig getConfig() {
	                return EsConfig.sign();
	            }
	        }.list(scs);
	  return list;
	}
}
