package com.hivescm.tms.finance.server.component.sign.impl;

import com.alibaba.fastjson.JSON;
import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchUpdateExecutor;
import com.hivescm.framework.elasticsearch.utils.OrderConditionUtils;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.response.DispatcherPackageDetailsRespDTO;
import com.hivescm.tms.api.dto.es.dispatcher.response.DispatcherPackageInfoRespDTO;
import com.hivescm.tms.api.dto.es.dispatcher.response.DispatcherWaybillDetailsRespDTO;
import com.hivescm.tms.api.dto.es.packageinfo.PackageDetailEsDTO;
import com.hivescm.tms.api.dto.es.packageinfo.PackageInfoEsDTO;
import com.hivescm.tms.api.dto.es.sign.*;
import com.hivescm.tms.api.dto.es.sign.component.TmsGoodsDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.VerificationCodeDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignPictureReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignDetailsRespDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillGoodsEsDTO;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillPayWayEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillStatusEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.component.sign.SignForService;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDO;
import com.hivescm.tms.finance.server.dao.mapper.sign.SignMapper;
import com.hivescm.tms.finance.server.feign.dispatcher.DispatcherService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;
import com.hivescm.tms.utils.StringUtil;
import com.mogujie.distributed.transction.DynamicTransctionManagerFactory;
import com.mogujie.trade.utils.TransactionManagerUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class SignForServiceImpl implements SignForService {

	private static Logger logger = LoggerFactory.getLogger(SignForServiceImpl.class);
	@Autowired
	private ESSearchService esSearchService;
	@Autowired
	private SignMapper signMapper;
    @Autowired
    private WaybillService waybillService;
    @Autowired
    private DispatcherService dispatcherService;
    @Autowired
    private EsSignService esSignService;

	/**
	 * 运单详情
	 * @param waybillId 运单id
	 * @return 运单详情信息(含有包裹数据)
	 */
	@Override
	public DataResult<SignDetailsRespDTO> getSignBill(Long waybillId) {
		// 查询结果
		DataResult<SignDetailsRespDTO> result = new DataResult<>();
		try {
			if (waybillId != null && waybillId > 0) {
				// 查询运单详情信息
				SignDetailsRespDTO resp = getWaybillId(waybillId);
				if (resp != null) {
					result.setResult(resp);
				}
			}
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_WAYBILL_NOT_EXIST,
					"getSignBill has error: record={" + waybillId + "}, error={" + e.getLocalizedMessage() + "}");
			logger.error(ex.getMessage(), e);
			throw ex;
		}
		return result;
	}

	/**
	 * 查找运单
	 * 
	 * @param waybillId
	 * @return
	 */
	private SignDetailsRespDTO getWaybillId(Long waybillId) throws Exception {
		SignDetailsRespDTO sdr = new SignDetailsRespDTO();
        // TODO 1 查询运单信息
        WaybillEsDTO waybillEsDTO = waybillService.queryWaybillEsDTO(waybillId);
        if (waybillEsDTO == null) {
            throw new IllegalArgumentException("运单信息为空");
        }
        // TODO 2 查询签收主表信息
		SignEsDTO signEsDTO = esSignService.querySignEsDTO(waybillId);
        if (signEsDTO == null) {
            throw new SystemException(ExceptionCodeConstants.ERROR_DISPATCHER_PUBLISH, "未生成签收单！");
        }

//        DispatcherDetailEsDTO dispatcherDetailEsDTO = dispatcherService.queryDispatcherDetailByWaybillId(waybillId);


//        //如果是
//        if (dispatcherDetailEsDTO==null){
//
//            throw new SystemException(ExceptionCodeConstants.ERROR_DISPATCHER_NOT_EXIST,"派车单不存在");
//        }

        sdr.setId(signEsDTO.getId());
        sdr.setWaybillId(waybillId);
        sdr.setWaybillCode(signEsDTO.getWaybillCode());
        sdr.setCode(signEsDTO.getCode());
        sdr.setSignType(signEsDTO.getSignType());
        sdr.setSignPic(signEsDTO.getSignPic());
        sdr.setTotalReceivable(signEsDTO.getTotalReceivable());
        sdr.setOrderCode(signEsDTO.getOrderCode());
        sdr.setSettlementMethod(signEsDTO.getSettlementMethod());
        sdr.setReceiptUser(signEsDTO.getReceiptUser());
        sdr.setReceiptConsignorMobleNo(signEsDTO.getReceiptConsignorMobleNo());

		sdr.setStoreName(waybillEsDTO.getStoreName());
		sdr.setAppointDispatchTime(getAppointDispatchTime(waybillEsDTO.getAppointDate(),waybillEsDTO.getAppointStartTime(),waybillEsDTO.getAppointEndTime()));
		sdr.setIemergency(waybillEsDTO.getIemergency());

        // TODO 3 查询拒收单信息
		List<SearchCondition> c = SearchConditionUtils.start()
				.addCondition("waybillId", waybillId, ConditionExpressionEnum.EQUAL).end();
		SignRefuseEsDTO signRefuseEsDTO = new DefaultAbstractSearchQueryExecutor<SignRefuseEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.refuseSign();
			}
		}.get(c);
		if (signRefuseEsDTO != null) {
            sdr.setLoaded(signRefuseEsDTO.getLoaded());
		}

		// 查询费用表
		List<SearchCondition> cc = SearchConditionUtils.start()
				.addCondition("signId", signEsDTO.getId(), ConditionExpressionEnum.EQUAL).end();
		SignFeeEsDTO signFeeEsDTO = new DefaultAbstractSearchQueryExecutor<SignFeeEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.signFee();
			}
		}.get(cc);
		// 到付运费
		if (signFeeEsDTO != null) {
			if (signFeeEsDTO.getToPay() != null) {
				sdr.setToPay(signFeeEsDTO.getToPay());
			}
			if (signFeeEsDTO.getCollectPayment() != null) {
				// 代收货款
				sdr.setCollectPayment(signFeeEsDTO.getCollectPayment());
			}
		}

		List<SignPackageDTO> signs = new ArrayList<>();
		List<SignDetailsEsDTO> signDetailsEsDTOS = null;
		List<WaybillGoodsEsDTO> wayGoodsList = waybillService.queryWaybillGoodsEsDTOList(waybillId);

		// 如果运单信息不为空去查询包裹以及包裹以下的物品
        if (signEsDTO.getSignType().equals(SignStatusEnum.SIGNED.getType())) {
            // 根据waybillId查询包裹信息
            List<PackageInfoEsDTO> packageInfoEsDTOS = waybillService.queryPackageListByWaybillId(waybillId);
            signs = EntityUtils.doBatchClone(packageInfoEsDTOS, SignPackageDTO.class);
            // 根据waybillId查询包裹下物品详细信息
			List<SignPackageDetailsDTO> pdetail = new ArrayList<>();

			if (signs != null && signs.size() > 0) {
                for (SignPackageDTO spd : signs) {
                    List<PackageDetailEsDTO> packageDetails = waybillService.getPackageDetailsByPackageId(spd.getId());
					List<SignPackageDetailsDTO> detailsDTOS = EntityUtils.doBatchClone(packageDetails, SignPackageDetailsDTO.class);
					pdetail.addAll(detailsDTOS);

                    //三级单位
					batchBuildUnitLevelList(null,pdetail,wayGoodsList);

				}
				sdr.setSignPackageDetailsDTO(pdetail);

			} else {
                // TODO 假包裹信息，包裹详情里放实际是waybillGoods数据
                SignPackageDTO dpr = new SignPackageDTO();
                dpr.setId(1000L);
                signs = new ArrayList<>();
                signs.add(dpr);
                // 根据waybillId查询waybillGoods详细信息
//                List<WaybillGoodsEsDTO> wayGoodsList = waybillService.queryWaybillGoodsEsDTOList(waybillId);
                pdetail = copyWaybillGoods(wayGoodsList, dpr);
                sdr.setSignPackageDetailsDTO(pdetail);
				batchBuildUnitLevelList(null,pdetail,wayGoodsList);

			}
        } else if (signEsDTO.getSignType().equals(SignStatusEnum.PARTIAL_SIGN.getType())) {
            List<SearchCondition> sc = SearchConditionUtils.start()
                    .addCondition("waybillId", waybillId, ConditionExpressionEnum.EQUAL).end();
            signDetailsEsDTOS = new DefaultAbstractSearchQueryExecutor<SignDetailsEsDTO>(esSearchService) {
                @Override
                public EsConfig getConfig() {
                    return EsConfig.signGoodsDetail();
                }
            }.list(sc);
            sdr.setSignDetailsEsDTO(signDetailsEsDTOS);
            List<SignPackageDetailsDTO> pdetail = copyWaybillGoodsPartSign(signDetailsEsDTOS);
            sdr.setSignPackageDetailsDTO(pdetail);
			batchBuildUnitLevelList(null,pdetail,wayGoodsList);
        } else {
            sdr.setSignPackageDTO(null);
        }


        // 包裹信息
        sdr.setSignPackageDTO(signs);

		// 如果是到付运费
		if (waybillEsDTO.getPayWay().equals(WaybillPayWayEnum.topay.getType())) {
			sdr.setTotalFee(waybillEsDTO.getTotalFee());
		} else {
			sdr.setTotalFee(BigDecimal.ZERO);
		}
		sdr.setPayWay(waybillEsDTO.getPayWay());
		return sdr;
	}

	// 转化数据
    private List<SignPackageDetailsDTO> copyWaybillGoods(List<WaybillGoodsEsDTO> wayGoodsList, SignPackageDTO dpr) {
        List<SignPackageDetailsDTO> list = new ArrayList<>();
        if (null != wayGoodsList && wayGoodsList.size() > 0) {
            for (WaybillGoodsEsDTO wgd : wayGoodsList) {
                SignPackageDetailsDTO dkr = new SignPackageDetailsDTO();
                dkr.setPackageId(dpr.getId());
                dkr.setGoodsName(wgd.getGoodsName());
                dkr.setAmount(wgd.getPackageNum());
                dkr.setUnit(wgd.getUnit());
                dkr.setMainUnitNum(wgd.getPackageNum());
                dkr.setMainUnit(wgd.getUnit());
                dkr.setDeliveryNum(wgd.getDeliveryNum());
                dkr.setSkuid(wgd.getSkuid());
                list.add(dkr);
            }
        }
        return list;
    }

    private List<SignPackageDetailsDTO> copyWaybillGoodsPartSign(List<SignDetailsEsDTO> signDetailsEsDTOS){
        List<SignPackageDetailsDTO> list = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(signDetailsEsDTOS)) {
            signDetailsEsDTOS.forEach(item -> {
                SignPackageDetailsDTO dkr = new SignPackageDetailsDTO();
                dkr.setGoodsName(item.getGoodsName());
                dkr.setSkuid(item.getSkuid());
                dkr.setAmount(item.getSignNumber());
                dkr.setUnit(item.getUnit());
                dkr.setMainUnitNum(item.getSignNumber());
                dkr.setMainUnit(item.getUnit());
                list.add(dkr);
            });
        }
        return list;
    }

    @Override
	public DataResult<DispatcherWaybillDetailsRespDTO> getWaybillDetail(Long waybillId) {
		// 查询结果
		DataResult<DispatcherWaybillDetailsRespDTO> result = new DataResult<>();
		try {
			if (waybillId != null && waybillId > 0) {
				// 查询运单详情信息
				DispatcherWaybillDetailsRespDTO resp = getWaybillById(waybillId);
				if (resp != null) {
					result.setResult(resp);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_WAYBILL_NOT_EXIST,
					"getWaybillById has error: record={" + waybillId + "}, error={" + e.getLocalizedMessage() + "}");
//			logger.error(ex.getMessage(), e);
			throw ex;
		}
		return result;
	}

	/**
	 * 拼接 指定配送时间
	 * @param data
	 * @param from
	 * @param end
	 * @return
	 */
	private String getAppointDispatchTime(String data,String from,String end){
		StringBuilder appointDispatchTime = new StringBuilder("");

		if (StringUtil.isNotBlank(data)){
			appointDispatchTime.append(data).append(" ");
		}
		if (StringUtil.isNotBlank(from)){
			appointDispatchTime.append(from).append("~")
					.append(end);
		}
		if (appointDispatchTime.length()==0){
			return null;
		}

		return appointDispatchTime.toString();
	}
	/**
	 * 查找运单
	 *
	 * @param waybillId
	 * @return
	 */
	private DispatcherWaybillDetailsRespDTO getWaybillById(Long waybillId) throws Exception {
		WaybillEsDTO waybillEsDTO = waybillService.queryWaybillEsDTO(waybillId);
		if (waybillEsDTO == null) {
			throw new SystemException(ExceptionCodeConstants.ERROR_WAYBILL_NOT_EXIST, "运单信息不存在");
		}
		List<SearchCondition> waybillIdCondition = SearchConditionUtils.start()
				.addEqualCondition("waybillId", waybillId).end();
		// 根据waybillId查询运单信息或者派车单明细
		DispatcherWaybillDetailsRespDTO sdr = getDispatcherWaybillDetailsRespDTO(waybillId);
		if (sdr == null) {
			throw new SystemException(ExceptionCodeConstants.ERROR_WAYBILL_NOT_EXIST, "运单对应的派车明细为空");
		}

		//拼接 指定配送时间
		sdr.setAppointDispatchTime(getAppointDispatchTime(sdr.getAppointDate(),sdr.getAppointStartTime(),sdr.getAppointEndTime()));

		if (waybillEsDTO.getTotalFee() != null) {
			// 到付运费
			sdr.setCod(waybillEsDTO.getTotalFee().toPlainString());
		}
		if (waybillEsDTO.getOrderGoodsPayment() != null) {
			// 代收货款
			sdr.setCollectPayment(waybillEsDTO.getOrderGoodsPayment());
		}

		// 排序条件
		List<OrderCondition> orders = OrderConditionUtils.start().addCondition("createTime", SortEnum.ASC).end();
		SignEsDTO signDto = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.sign();
			}
		}.get(waybillIdCondition);
		if (signDto != null) {
			sdr.setSignType(signDto.getSignType());
			List<SearchCondition> feesc = SearchConditionUtils.start()
					.addCondition("signId", signDto.getId(), ConditionExpressionEnum.EQUAL).end();
			SignFeeEsDTO signFeeDto = new DefaultAbstractSearchQueryExecutor<SignFeeEsDTO>(esSearchService) {
				@Override
				public EsConfig getConfig() {
					return EsConfig.signFee();
				}
			}.get(feesc);
			if (signFeeDto != null) {
				sdr.setSettlementMethod(signFeeDto.getSettlementMethod());
			}
		} else {
			// 若签收单不存在，取拒收单
			SignRefuseEsDTO signRefuseEsDTO = new DefaultAbstractSearchQueryExecutor<SignRefuseEsDTO>(esSearchService) {
				@Override
				public EsConfig getConfig() {
					return EsConfig.refuseSign();
				}
			}.get(waybillIdCondition);
			if (signRefuseEsDTO != null) {
				sdr.setSignType(signRefuseEsDTO.getRefuseType());
				sdr.setSettlementMethod(signRefuseEsDTO.getSettlementMethod());
				sdr.setLoaded(signRefuseEsDTO.getLoaded());
				List<TmsGoodsDetailsEsDTO> goodsDetail = new DefaultAbstractSearchQueryExecutor<TmsGoodsDetailsEsDTO>(
						esSearchService) {
					@Override
					public EsConfig getConfig() {
						return EsConfig.refuseSignGoodsDetails();
					}
				}.list(waybillIdCondition);
				if (CollectionUtils.isNotEmpty(goodsDetail)) {
					sdr.setRefuseReason(goodsDetail.get(0).getRefuseCause());
				}
			}
		}
		// 查询包裹以及包裹以下的物品
		// 根据waybillId查询包裹信息
        List<PackageInfoEsDTO> packageInfoEsDTOS = waybillService.queryPackageListByWaybillId(waybillId);
        List<DispatcherPackageInfoRespDTO> signs = EntityUtils.doBatchClone(packageInfoEsDTOS, DispatcherPackageInfoRespDTO.class);

        // 根据waybillId查询包裹下物品详细信息
        List<PackageDetailEsDTO> packageDetailEsDTOS = waybillService.getPackageDetailsByWaybillId(waybillId);
        List<DispatcherPackageDetailsRespDTO> pdetail = EntityUtils.doBatchClone(packageDetailEsDTOS, DispatcherPackageDetailsRespDTO.class);

        // 根据waybillId查询waybillGoods详细信息
        List<WaybillGoodsEsDTO> wayGoodsList = waybillService.queryWaybillGoodsEsDTOList(waybillId);
		// 根据派车单id查询派车单信息 主要获取派车单发布时间
		DispatcherEsDTO dispatcherEsDTO = dispatcherService.queryDispatcherEsDTO(sdr.getDispatcherId());
		if (dispatcherEsDTO != null) {
			// 派车单发布时间
			sdr.setReleaseTime(dispatcherEsDTO.getReleaseTime());
		}
		if (null != signs && signs.size() > 0) {
			// 判断货物是否已包裹形式存在
			sdr.setIsPackageInfo(true);
			// 包裹信息
			sdr.setDispatcherPackageInfoDTO(signs);
			// 包裹物品详细
			sdr.setDispatcherPackageDetailDTO(pdetail);
			//批量生成三级转换列表
            batchBuildUnitLevelList(pdetail,null,wayGoodsList);
		} else {
			signs = new ArrayList<>();
			sdr.setIsPackageInfo(false);
			// 假包裹信息 方便前台展示用 然而并没有什么卵用
			DispatcherPackageInfoRespDTO dpr = new DispatcherPackageInfoRespDTO();
			// 给个假的id
			dpr.setId(1000L);
			signs.add(dpr);
			sdr.setDispatcherPackageInfoDTO(signs);
			// 包裹详情里放实际是waybillGoods数据
			pdetail = copyWaybillGoods(wayGoodsList, dpr);
			sdr.setDispatcherPackageDetailDTO(pdetail);
		}
		// 运单是否整车字段 经理说目前所有的都是整车,先写死,后期优化对应前端叫 接单类型
		// 计算应收总金额
		Double aggregateAmount = addAggregateAmount(sdr);
		sdr.setAggregateAmount(aggregateAmount);
		// TODO  是否整车先写死,目前全都是整车,以后优化
		sdr.setItruckLoad(true);
		// 判断订单类型1位销售单,2位销退单
		int orderType = sdr.getOrderType();
		if (orderType == 1) {
			sdr.setIsXTOrder(false);
		} else {
			sdr.setIsXTOrder(true);
		}
		// 判断运单签收状态
		// 若签收 ，将 签收时间(signTime) 赋值给 预约提货时间 (deliveryPickTime)
		int waybillStatus = sdr.getWaybillStatus();
		if (waybillStatus == WaybillStatusEnum.SIGNED.getType()) {
			sdr.setDeliveryPickTime(sdr.getSignTime());
		}
		// 如果是到付
		if (waybillEsDTO.getPayWay().equals(WaybillPayWayEnum.topay.getType())) {
			sdr.setTotalFee(waybillEsDTO.getTotalFee());// 运费
		} else {
			sdr.setTotalFee(BigDecimal.ZERO);// 运费
		}
		sdr.setPayWay(waybillEsDTO.getPayWay());
		sdr.setOrderSource(waybillEsDTO.getOrderSource());
		sdr.setOrderSourceName(waybillEsDTO.getOrderSourceName());
		sdr.setRemark(waybillEsDTO.getRemark());
		return sdr;
	}

    private DispatcherWaybillDetailsRespDTO getDispatcherWaybillDetailsRespDTO(Long waybillId) {
        DispatcherDetailEsDTO esDTO = dispatcherService.queryDispatcherDetailByWaybillId(waybillId);
        return EntityUtils.copyProperties(esDTO, DispatcherWaybillDetailsRespDTO.class);
    }
    // 转化数据
    private List<DispatcherPackageDetailsRespDTO> copyWaybillGoods(List<WaybillGoodsEsDTO> wayGoodsList,
                                                                   DispatcherPackageInfoRespDTO dpr) {
        List<DispatcherPackageDetailsRespDTO> list = new ArrayList<DispatcherPackageDetailsRespDTO>();
        if (null != wayGoodsList && wayGoodsList.size() > 0) {
            for (WaybillGoodsEsDTO wgd : wayGoodsList) {
                DispatcherPackageDetailsRespDTO dispatcherPackageDetails = new DispatcherPackageDetailsRespDTO();
                dispatcherPackageDetails.setPackageId(dpr.getId());
                dispatcherPackageDetails.setGoodsName(wgd.getGoodsName());
                dispatcherPackageDetails.setAmount(wgd.getPackageNum());
                dispatcherPackageDetails.setUnit(wgd.getUnit());
                dispatcherPackageDetails.setMainUnitNum(wgd.getPackageNum());
                dispatcherPackageDetails.setMainUnit(wgd.getUnit());
                dispatcherPackageDetails.setSkuid(wgd.getSkuid());
                dispatcherPackageDetails.setId(wgd.getId());
                dispatcherPackageDetails.setMaxDelivery(wgd.getMaxDelivery());
                dispatcherPackageDetails.setDeliveryNum(wgd.getDeliveryNum());


                List<UnitLevelDTO> unitLevelDTOS = buildUnitLevelListByWaybillGoodsEsDTO(wgd);
                dispatcherPackageDetails.setUnitLevelDTOS(unitLevelDTOS);

                list.add(dispatcherPackageDetails);
            }
        }
        return list;
    }


    /**
     * 批量转换
     * @param dpdrds
	 * @param spdds
     * @param wayGoodsList
     */
    private void batchBuildUnitLevelList(List<DispatcherPackageDetailsRespDTO> dpdrds,List<SignPackageDetailsDTO> spdds, List<WaybillGoodsEsDTO> wayGoodsList ){

		//存在 同种Goods 在不同包裹的情况 通过skuid 分类

		for (WaybillGoodsEsDTO goodsEsDTO : wayGoodsList) {
			String skuid = goodsEsDTO.getSkuid();
			List<UnitLevelDTO> unitLevelDTOS = buildUnitLevelListByWaybillGoodsEsDTO(goodsEsDTO);
			if (dpdrds!=null) {
				for (DispatcherPackageDetailsRespDTO packageDetailsRespDTO : dpdrds) {
					if (packageDetailsRespDTO.getSkuid().equals(skuid)) {
						packageDetailsRespDTO.setUnitLevelDTOS(unitLevelDTOS);
					}
				}
			}

			if (spdds!=null){
				for (SignPackageDetailsDTO pdrd : spdds) {
					if (pdrd.getSkuid().equals(skuid)) {
						pdrd .setUnitLevelDTOS(unitLevelDTOS);
					}
				}
			}
		}


    }
	/**
	 * 通过 WaybillGoodsEsDTO 构建 商品级别转换列表
	 *
	 * 傻逼马勇 说让我倒着返回 这种事让我后台做 凸 ^ 凸
	 * @param wgd
	 * @return
	 */
    private static List<UnitLevelDTO> buildUnitLevelListByWaybillGoodsEsDTO(WaybillGoodsEsDTO wgd){
		logger.error("qinchuan"+wgd.toString());

        if (StringUtil.isNotBlank(wgd.getUnit())){
			LinkedList<UnitLevelDTO> unitLevelDTOS = new LinkedList<>();
			UnitLevelDTO lv1 = new UnitLevelDTO(wgd.getUnit(),0);
			unitLevelDTOS.push(lv1);

			if (StringUtil.isNotBlank(wgd.getUnit2())){
				UnitLevelDTO lv2 = new UnitLevelDTO(wgd.getUnit2(), wgd.getUnitRule() );
				unitLevelDTOS.push(lv2);



				if (StringUtil.isNotBlank(wgd.getUnit3())){
                    Integer unitRule = wgd.getUnitRule();
                    if (unitRule==0){
                        unitRule=1;
                    }
                    int l3Num = wgd.getUnitRule2() / unitRule;

                    UnitLevelDTO l3 = new UnitLevelDTO(wgd.getUnit3(),l3Num);



					unitLevelDTOS.push(l3);
				}
			}
			return unitLevelDTOS;

		}else {
			//返回空的只读List
			return Collections.EMPTY_LIST;
		}
	}



    /**
     * 计算应收总金额
     *
     * @param sdr
     * @return
     */
    private Double addAggregateAmount(DispatcherWaybillDetailsRespDTO sdr) {
        // 计算应收总金额
        String cod;// 运费
        String collectPayment;// 代收货款
        if (StringUtils.isBlank(sdr.getCod())) {
            cod = "0";
        } else {
            cod = sdr.getCod();
        }
		if (sdr.getCollectPayment() == null) {
			collectPayment = "0";
		} else {
			collectPayment = sdr.getCollectPayment().toPlainString();
		}
        return Double.valueOf(cod) + Double.valueOf(collectPayment);
    }
    /**
	 * 检查验证码
	 */
	@Override
	public DataResult<Boolean> checkSignode(VerificationCodeDTO verificationCodeDTO) {
		DataResult<Boolean> result = new DataResult<>();
		// 获取前端签收验证码
		String code = verificationCodeDTO.getCode();

		if (StringUtils.isNotBlank(code)) {

			//todo app能绕过去 怎么能 前台校验呢
			// 前端要加挡板 9999 直接不校验 上线在删掉
//			if ("9999".equals(code)) {
//				result.setResult(true);
//			} else {
				// 获取确认装货时生成的验证码
				DispatcherDetailEsDTO esDto = dispatcherService.queryDispatcherDetailByWaybillId(verificationCodeDTO.getWaybillId());
                if (esDto == null) {
                    throw new SystemException(-1, "派车单不存在");
                }
				if (code.equals(esDto.getSignCode())) {
					result.setResult(true);
				} else {
					result.setResult(false);
				}
//			}
		}
		return result;
	}

	/**
	 * 查询短信验证码
	 * 
	 * @param dispatcherId
	 * @return
	 */
	public DispatcherDetailEsDTO getSMSCodeByDispatcherId(Long dispatcherId, Long waybillId) {
		DispatcherDetailEsDTO dto = null;
		try {
			List<SearchCondition> scs = SearchConditionUtils.start()
					.addCondition("dispatcherId", dispatcherId, ConditionExpressionEnum.EQUAL)
					.addCondition("waybillId", waybillId, ConditionExpressionEnum.EQUAL).end();
//			dto = new DefaultAbstractSearchQueryExecutor<DispatcherDetailEsDTO>(esSearchService) {
//				@Override
//				public EsConfig getConfig() {
//					return EsConfig.dispatcherDetail();
//				}
//			}.get(scs);
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_DEPENDENCY,
					"esQuery：获取数据失败，失败原因:" + e.getLocalizedMessage());
			throw ex;
		}

		return dto;
	}

	@Autowired
	private DynamicTransctionManagerFactory dtmFactory;

	/**
	 * 开启事务
	 *
	 * @param companyId
	 * @return
	 */
	private TransactionManagerUtils.TransactionProxy getTransaction(Long companyId) {
		return dtmFactory.create().addTransManager(SignMapper.class, companyId).build();
	}

	/**
	 * 更新签收图片路径
	 * 
	 * @param
	 * @return
	 */
	@Override
	public boolean updateSignPicture(SignPictureReqDTO signPictureReqDTO) {
		boolean dbResult = false, esResult = false;
		TransactionManagerUtils.TransactionProxy transactionProxy = this
				.getTransaction(signPictureReqDTO.getCompanyId());

		try {
			if (null == signPictureReqDTO || !signPictureReqDTO.isValid()) {
				throw new Exception("SignPictureReqDTO 参数不能为空");
			}
			List<SearchCondition> sc = SearchConditionUtils.start()
					.addCondition("companyId", signPictureReqDTO.getCompanyId(), ConditionExpressionEnum.EQUAL)
					.addCondition("id", signPictureReqDTO.getId(), ConditionExpressionEnum.EQUAL).end();
			List<SignEsDTO> s = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(esSearchService) {
				@Override
				public EsConfig getConfig() {
					return EsConfig.sign();
				}
			}.list(sc);
			String signPictureUrl = signPictureReqDTO.getSignPic();
			if (null != s && s.size() > 0) {
				SignEsDTO ses = s.get(0);
				// 重新new SignDO和SignEsDTO 防止其他人同时修改同一条数据
				SignDO sd = new SignDO();
				SignEsDTO sed = new SignEsDTO();
				// 给es赋值
				sed.setId(ses.getId());
				sed.setCompanyId(ses.getCompanyId());
				// 给do赋值
				sd.setId(ses.getId());
				sd.setCompanyId(ses.getCompanyId());
				String signPic = ses.getSignPic();
				// 校验此字段是否有值
				if (StringUtils.isNotBlank(signPic)) {
					StringBuffer sb = new StringBuffer(signPic);
					sb.append(",").append(signPictureUrl);
					sd.setSignPic(sb.toString());
					sed.setSignPic(sb.toString());
				} else {
					sd.setSignPic(signPictureUrl);
					sed.setSignPic(signPictureUrl);
				}
				// 跟新数据库
				dbResult = this.signMapper.updateByPrimaryKeySelective(sd) == 1;
				// >> 更新ES
				if (dbResult) {
					esResult = this.updateSignPictureEs(sed, sc);
				}
			} else {
				dbResult = false;
				esResult = false;
			}
			transactionProxy.commit();
		} catch (Exception e) {
			transactionProxy.rollback();
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN,
					"updateSignPicture has error: record={" + signPictureReqDTO + "}, error={" + e.getMessage() + "}");
			logger.error(ex.getMessage(), e);
			throw ex;
		}
		return dbResult && esResult;
	}

	/**
	 * 更新签收图片路径 - 更新ES
	 * 
	 * @param signEsDTO
	 * @return
	 * @throws Exception
	 */
	private Boolean updateSignPictureEs(SignEsDTO signEsDTO, List<SearchCondition> searchConditions) throws Exception {
		return new DefaultAbstractSearchUpdateExecutor<SignEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.sign();
			}
		}.execute(signEsDTO, searchConditions);
	}

	@Override
	public DataResult<List<TmsSignEsDTO>> getSignBillByWayBillId(Long waybillId) {
		DataResult<List<TmsSignEsDTO>> result = new DataResult<>();
		try {
			// 查询签收信息
			List<TmsSignEsDTO> resp = getSignBillByWaybillCode(waybillId);
			result.setResult(resp);
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_SEARCH_SIGN,
					"getSignBillByWayBillCode has error:record={" + waybillId + "},error={" + e.getLocalizedMessage()
							+ "}");
			logger.error(ex.getMessage(), e);
			throw ex;

		}
		return result;
	}

	private List<TmsSignEsDTO> getSignBillByWaybillCode(Long waybillId) {
		List<TmsSignEsDTO> list = new ArrayList<>();
		List<SearchCondition> scs = SearchConditionUtils.start()
				.addCondition("waybillId", waybillId, ConditionExpressionEnum.EQUAL).end();

		List<SignEsDTO> signs = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(esSearchService) {

			@Override
			public EsConfig getConfig() {
				return EsConfig.sign();
			}
		}.list(scs);
		if (null != signs && signs.size() > 0) {
			TmsSignEsDTO tmsSignEsDTO = new TmsSignEsDTO();
			// 计算出累计签收和累计拒绝签收件数
			Integer signNumber = 0;
			Integer refuseNumber = 0;
			for (SignEsDTO item : signs) {
				// 查询签收明细条件
				List<SearchCondition> scsDetails = SearchConditionUtils.start()
						.addCondition("signId", item.getId(), ConditionExpressionEnum.EQUAL).end();
				// 查询签收明细
				List<SignDetailsEsDTO> details = new DefaultAbstractSearchQueryExecutor<SignDetailsEsDTO>(
						esSearchService) {
					@Override
					public EsConfig getConfig() {
						return EsConfig.signGoodsDetail();
					}
				}.list(scsDetails);
				// 查询签收费用条件
				List<SearchCondition> sceFee = SearchConditionUtils.start()
						.addCondition("signId", item.getId(), ConditionExpressionEnum.EQUAL).end();
				SignFeeEsDTO fee = new DefaultAbstractSearchQueryExecutor<SignFeeEsDTO>(esSearchService) {

					@Override
					public EsConfig getConfig() {
						return EsConfig.signFee();
					}
				}.get(sceFee);
				signNumber = signNumber + item.getSignNumber();
				refuseNumber = refuseNumber + item.getRefuseNumber();
				tmsSignEsDTO.setSignEsDTO(item);
				tmsSignEsDTO.setSignDetailsEsDTO(details);
				tmsSignEsDTO.setSignFeeEsDTO(fee);
				// 开单件数
				tmsSignEsDTO.setCreateNumber(item.getCreateNumber());
				tmsSignEsDTO.setSignPic(item.getSignPic());
				list.add(tmsSignEsDTO);
			}
			// 给累计签收和累计拒绝赋值
			for (TmsSignEsDTO dto : list) {
				dto.setRefuseNumber(refuseNumber);
				dto.setSignNumber(signNumber);
				dto.setUnsignedNumber(dto.getCreateNumber() - refuseNumber - signNumber);
			}
		}
		return list;
	}

	@Override
	public SignEsDTO querySignEsDTOByWaybillId(Long waybillId) {
		List<SearchCondition> scsc = SearchConditionUtils.start()
				.addEqualCondition("waybillId", waybillId).end();
		// 排序条件
		SignEsDTO signEsDTO = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(esSearchService) {
			@Override
			public EsConfig getConfig() {
				return EsConfig.sign();
			}
		}.get(scsc);
		return signEsDTO == null ? null : signEsDTO;
	}

}
