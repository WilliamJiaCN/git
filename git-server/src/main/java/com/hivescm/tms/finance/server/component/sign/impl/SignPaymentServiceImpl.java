package com.hivescm.tms.finance.server.component.sign.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchSaveExecutor;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchUpdateExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.order.redundancy.WriteMqInfoDTO;
import com.hivescm.tms.api.dto.es.sign.OrderPaymentInfoESDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignForDetailsReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignGetQRCodeReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignPaymentQueryStatusReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignBillEsRespDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignGetQRCodeRespDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignPaymentQueryStatusRespDto;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.enums.biz.sign.SignPaymentStatusEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.constants.SignPaymentConstants;
import com.hivescm.tms.finance.server.component.sign.GiveSignService;
import com.hivescm.tms.finance.server.component.sign.SignPaymentService;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.config.PaySignKeyProperties;
import com.hivescm.tms.finance.server.dao.entity.sign.OrderPaymentInfoDO;
import com.hivescm.tms.finance.server.dao.mapper.sign.OrderPaymentInfoDOMapper;
import com.hivescm.tms.finance.server.feign.dispatcher.DispatcherService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.utils.PlatRsaUtil;
import com.hivescm.tms.intranet.gateway.api.dto.boss.IFinanceEntrustRelationInfoDto;
import com.hivescm.tms.intranet.gateway.api.dto.boss.IQueryFinanceEntrustRelationParam;
import com.hivescm.tms.intranet.gateway.api.dto.message.AppMessageReqDTO;
import com.hivescm.tms.intranet.gateway.api.dto.pay.*;
import com.hivescm.tms.intranet.gateway.api.feign.IAppMessageService;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.boss.BossFinanceEntrusRelationService;
import com.hivescm.tms.intranet.gateway.api.feign.pay.IPayService;
import com.hivescm.tms.utils.BeanMapUtils;
import com.hivescm.tms.utils.JsonUtil;
import com.hivescm.tms.utils.StringUtil;
import com.mogujie.distributed.transction.DynamicTransctionManagerFactory;
import com.mogujie.trade.utils.TransactionManagerUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SignPaymentServiceImpl implements SignPaymentService {
	private static Logger logger = LoggerFactory.getLogger(SignPaymentServiceImpl.class);
	
	@Autowired
	private IPayService payService;
	@Autowired
	private ESSearchService esSearchService;
	@Autowired
	private DynamicTransctionManagerFactory dtmFactory;
	/**
	 * 订单支付信息mapper
	 */
	@Autowired
	private OrderPaymentInfoDOMapper orderPaymentInfoDOMapper;
	/**
	 * 获取财务委托关系集合服务
	 */
	@Autowired
	private BossFinanceEntrusRelationService bossFinanceEntrusRelationService;
    @Autowired
    private IAppMessageService appMessageService;
//    @Autowired
//    private WriteMqInfoService writeMqInfoService;
	/**
	 * 索引自增生成器服务
	 */
    @Autowired
    private IGeneratedIdService generatedIdService;
    @Autowired
    private PaySignKeyProperties paySignKeyProperties;
    @Autowired
    private GiveSignService giveSignService;
	@Autowired
	private WaybillService waybillService;
    @Autowired
    private DispatcherService dispatcherService;
	/**
	 * 获取支付二维码
	 */
	@Override
	public DataResult<SignGetQRCodeRespDTO> getPaymentQRCode(SignGetQRCodeReqDTO signGetQRCodeReqDTO) {
		DataResult<SignGetQRCodeRespDTO> result = new DataResult<SignGetQRCodeRespDTO>();
		SignGetQRCodeRespDTO sgqrc = new SignGetQRCodeRespDTO();
		//支付平台统一下单接口入参对象
		IMobileOrderPayDto mobileOrderPayDto =new IMobileOrderPayDto();
		//订单支付信息do
		OrderPaymentInfoDO orderPaymentInfoDO = new OrderPaymentInfoDO();
		//订单支付信息ES
		OrderPaymentInfoESDTO orderPaymentInfoESDTO = new OrderPaymentInfoESDTO();
		//支付平台SDK支付请求入参
		IMobilePayDto mobilePayDto = new IMobilePayDto();
		TransactionManagerUtils.TransactionProxy transaction = getDispatcherTransaction(signGetQRCodeReqDTO.getCompanyId());
		try{
			WaybillEsDTO waybillEsDTO = waybillService.queryWaybillEsDTO(signGetQRCodeReqDTO.getWaybillId());


			//派车单明细表查询条件
			DispatcherDetailEsDTO dispatcherDetailEsDTO = dispatcherService.queryDispatcherDetailByWaybillId(signGetQRCodeReqDTO.getWaybillId());

			//封装要传给支付的参数对象
			mobileOrderPayDto = getmobileOrderPayDto(signGetQRCodeReqDTO,mobileOrderPayDto,dispatcherDetailEsDTO,waybillEsDTO);
			//请求支付平台生成订单接口
            DataResult<ICreatePayOrderVo> dr = this.payService.getPayOrder(mobileOrderPayDto);
            if (dr.getResult() == null) {
                throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_GETQC_CODE_FAIL,
                        "调用支付平台统一下单接口成功，但返回结果信息为空");
            }
            ICreatePayOrderVo cp = dr.getResult();
            //请求支付平台sdk支付接口
            mobilePayDto = getMobilePayDto(signGetQRCodeReqDTO, cp, mobileOrderPayDto, mobilePayDto, waybillEsDTO);
            DataResult<ICreatePamentVo> drpayment = this.payService.createPayment(mobilePayDto);
            if (drpayment.getResult() == null) {
                throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_GETQC_CODE_FAIL,
                        "调用支付平台聚合支付接口成功，但返回结果信息为空");
            }
            ICreatePamentVo cpv = drpayment.getResult();
            String orderNo = cp.getOrderNo();
            String qrCodeUrl = cpv.getQrCodeUrl();
            orderPaymentInfoESDTO = getOrderPaymentInfoDO(mobileOrderPayDto, signGetQRCodeReqDTO, dispatcherDetailEsDTO, orderNo, qrCodeUrl);
            //做数据库保存
            orderPaymentInfoDO = saveDB(orderPaymentInfoESDTO);
            if (null != orderPaymentInfoDO) {
                //同步数据到es
                saveES(orderPaymentInfoESDTO);
                //封装返回给前端的参数
                sgqrc.setQrcodeurl(qrCodeUrl);
                sgqrc.setBusinessOrderNo(mobileOrderPayDto.getBusinessOrderNo());
                sgqrc.setOrderNo(orderNo);
                sgqrc.setBusinessCode(SignPaymentConstants.BUSINESS_CODE);
                result.setResult(sgqrc);
                // 提交事务
            } else {
                return DataResult.faild(ExceptionCodeConstants.ERROR_SIGN_GETQC_CODE_FAIL, "获取二维码失败");
            }
            transaction.commit();
        } catch (SystemException e) {
            logger.error("保存订单支付信息失败，  TMS订单支付信息为:" + orderPaymentInfoDO, e);
            transaction.rollback();
            throw new SystemException(e.getErrorCode(), e.getErrorReason());
        } catch (Exception e) {
            logger.error("保存订单支付信息失败，  TMS订单支付信息为:" + orderPaymentInfoESDTO, e);
            transaction.rollback();
			throw new SystemException(-1, e.getLocalizedMessage());
		}
        return result;
	}
	/**
	 * 查询扫码支付是否成功付款
	 */
	@Override
	public DataResult<SignPaymentQueryStatusRespDto> queryPaymentStatusByOrderNo(SignPaymentQueryStatusReqDTO signPaymentQueryStatusReqDTO) {
		DataResult<SignPaymentQueryStatusRespDto> result = new DataResult<SignPaymentQueryStatusRespDto>();
		SignPaymentQueryStatusRespDto spqsr = new SignPaymentQueryStatusRespDto();
		OrderPaymentInfoDO orderPaymentInfoDO = new OrderPaymentInfoDO();
		boolean isGetQRCode = false;
		try{
            List<SearchCondition> sc = getSearchConditions(signPaymentQueryStatusReqDTO);
            OrderPaymentInfoESDTO orderPaymentInfoESDTO = getOrderPaymentInfo(sc);
            if (orderPaymentInfoESDTO == null) {
			    throw new SystemException(ExceptionCodeConstants.ERROR_QUERY_PAY_RESULT_FAIL,
                        "订单编号：" + signPaymentQueryStatusReqDTO.getQueryOrderNo() + "的订单不存在");
            }
            if(SignPaymentStatusEnum.WAIT_BUYER_PAY.getName().equals(orderPaymentInfoESDTO.getOrderStatus())
                    || SignPaymentStatusEnum.TRADE_ING.getName().equals(orderPaymentInfoESDTO.getOrderStatus())){
                //交易处理中需要去查询支付平台
                DataResult<IQueryResultVo> dr = queryStatusByOrderId(signPaymentQueryStatusReqDTO);
                if (dr.getResult() == null) {
                    throw new SystemException(ExceptionCodeConstants.ERROR_QUERY_PAY_RESULT_FAIL, "支付平台查询支付结果失败");
                }
                logger.debug("调用支付平台订单查询接口返回结果信息:" + dr.getResult());
                IQueryResultVo qrv = dr.getResult();
                String status = qrv.getStatus();
                if (SignPaymentStatusEnum.TRADE_SUCCESS.getName().equals(status)) {
                    spqsr.setOrderStatus(2);
                    updateOrderPaymentInfo(signPaymentQueryStatusReqDTO.getCompanyId(), orderPaymentInfoDO, sc, orderPaymentInfoESDTO, status, false);
                } else if (SignPaymentStatusEnum.WAIT_BUYER_PAY.getName().equals(status)
                        || SignPaymentStatusEnum.TRADE_ING.getName().equals(status)) {
                    // TODO 等待交易结果，不需要更新
                    spqsr.setOrderStatus(1);
                } else if (SignPaymentStatusEnum.TRADE_FAIL.getName().equals(status)) {
                    spqsr.setOrderStatus(3);
                    updateOrderPaymentInfo(signPaymentQueryStatusReqDTO.getCompanyId(), orderPaymentInfoDO, sc, orderPaymentInfoESDTO, status, false);
                } else {
                    spqsr.setOrderStatus(3);
                    updateOrderPaymentInfo(signPaymentQueryStatusReqDTO.getCompanyId(), orderPaymentInfoDO, sc, orderPaymentInfoESDTO, status, false);
                }
            } else if (SignPaymentStatusEnum.TRADE_SUCCESS.getName().equals(orderPaymentInfoESDTO.getOrderStatus())) {
                //交易成功
                spqsr.setOrderStatus(2);
            } else if (SignPaymentStatusEnum.TRADE_FAIL.getName().equals(orderPaymentInfoESDTO.getOrderStatus())) {
                //交易失败
                spqsr.setOrderStatus(3);
            } else {
                //不知道是否需要重新生成二维码 todo...
                isGetQRCode = true;
                spqsr.setOrderStatus(3);
            }
			//封装返回对象给前端
			spqsr.setBusinessOrderNo(signPaymentQueryStatusReqDTO.getQueryBusinessOrderNo());
			spqsr.setOrderNo(signPaymentQueryStatusReqDTO.getQueryOrderNo());
			spqsr.setIsGetQRCode(isGetQRCode);
			result.setResult(spqsr);
		} catch (SystemException e) {
			logger.error("更新订单支付信息失败，  TMS订单支付信息为:" + orderPaymentInfoDO, e);
			throw new SystemException(e.getErrorCode(), e.getErrorReason());
		} catch (Exception e) {
			logger.error("更新订单支付ES信息失败，  TMS订单支付信息为:" + orderPaymentInfoDO, e);
			throw new SystemException(ExceptionCodeConstants.ERROR_QUERY_PAY_RESULT_FAIL, "更新订单支付ES信息失败");
		}
		return result;
	}

    private void updateOrderPaymentInfo(Long companyId, OrderPaymentInfoDO orderPaymentInfoDO, List<SearchCondition> sc,
                                        OrderPaymentInfoESDTO orderPaymentInfoESDTO, String status, Boolean needPush) {
        TransactionManagerUtils.TransactionProxy transaction = getDispatcherTransaction(companyId);
        try{
            orderPaymentInfoDO = this.updateOrderPaymentInfoDO(false, status, orderPaymentInfoESDTO);
            if(updateOrderPaymentInfoESDTO(orderPaymentInfoDO, sc)) {
                transaction.commit();
                if (needPush) {
                    paySign(orderPaymentInfoESDTO);
                }
            } else {
                throw new SystemException(ExceptionCodeConstants.ERROR_QUERY_PAY_RESULT_FAIL, "支付成功回调修改ES状态失败");
            }
        } catch (SystemException e) {
            logger.error("更新订单支付信息失败，  TMS订单支付信息为:" + orderPaymentInfoDO, e);
            transaction.rollback();
            throw new SystemException(e.getErrorCode(), e.getErrorReason());
        } catch (Exception e) {
            logger.error("更新订单支付ES信息失败，  TMS订单支付信息为:" + orderPaymentInfoDO, e);
            transaction.rollback();
            throw new SystemException(ExceptionCodeConstants.ERROR_QUERY_PAY_RESULT_FAIL, "更新订单支付信息失败");
        }
    }

    private void paySign(OrderPaymentInfoESDTO orderPaymentInfoESDTO) {
        SignBillEsRespDTO signBillEsRespDTO = null;
	    try {
            SignForDetailsReqDTO signForDetailsReqDTO = EntityUtils.copyProperties(orderPaymentInfoESDTO,
                    SignForDetailsReqDTO.class);
            signForDetailsReqDTO.setUserId(orderPaymentInfoESDTO.getDriverId().intValue());
            signForDetailsReqDTO.setSettlementMethod(2);
            signBillEsRespDTO = giveSignService.insertSign(signForDetailsReqDTO);
            pushAppMsg(signBillEsRespDTO, orderPaymentInfoESDTO.getDriverId().intValue(), 0, null);
        } catch (SystemException se) {
            logger.error(se.getErrorReason());
            pushAppMsg(signBillEsRespDTO, orderPaymentInfoESDTO.getDriverId().intValue(), se.getErrorCode(), se.getErrorReason());
        }
    }

    private OrderPaymentInfoESDTO getOrderPaymentInfo(List<SearchCondition> sc) {
        return new DefaultAbstractSearchQueryExecutor<OrderPaymentInfoESDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.orderPaymentInfo();
            }
        }.get(sc);
    }

    private List<SearchCondition> getSearchConditions(SignPaymentQueryStatusReqDTO signPaymentQueryStatusReqDTO) {
        return SearchConditionUtils.start()
                    .addEqualCondition("businessOrderNo", signPaymentQueryStatusReqDTO.getQueryBusinessOrderNo())
                    .addEqualCondition("orderNo", signPaymentQueryStatusReqDTO.getQueryOrderNo()).end();
    }

    /**
	 * 接收支付结果通知并推送给前端
	 */
	@Override
	public Boolean acceptPayResultNotify(IPayNoticeVo payNotice) {
        WriteMqInfoDTO record = new WriteMqInfoDTO();
        record.setOrderType("acceptPayResultNotify");
        record.setQueueName("接收支付结果通知并推送给前端");
        record.setMainInfo(JsonUtil.GsonString(payNotice));
        OrderPaymentInfoDO orderPaymentInfoDO = new OrderPaymentInfoDO();


        try {
            if (null == payNotice) {
                logger.error("接收支付结果异步通知失败,参数为null");
                throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_PATMENT_ORDER_STATUS, "接收支付结果异步通知失败,参数为空");
            }

            List<SearchCondition> condition = SearchConditionUtils.start()
                    .addCondition("businessOrderNo", payNotice.getBusinessOrderNo(), ConditionExpressionEnum.EQUAL)
                    .addCondition("orderNo", payNotice.getOrderNo(), ConditionExpressionEnum.EQUAL).end();
            OrderPaymentInfoESDTO orderPaymentInfoESDTO = getOrderPaymentInfo(condition);
            if (orderPaymentInfoESDTO == null) {
                throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_PATMENT_ORDER_STATUS, "查无此订单信息");
            }

            //中间需要验签
            boolean flag = checkValueSign(payNotice);
            if (!flag) {
                throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_PATMENT_ORDER_STATUS,
                        "接收支付结果异步通知失败,签名验证失败");
            }
            String status = payNotice.getTranStatus();
            if(StringUtils.isBlank(status)){
                throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_PATMENT_ORDER_STATUS,
                        "接收支付结果异步通知失败,通知接口参数tranStatus为空");
            }
            if (SignPaymentStatusEnum.TRADE_SUCCESS.getName().equals(status)) { //成功，更新订单支付信息，做签收
                updateOrderPaymentInfo(orderPaymentInfoESDTO.getCompanyId(), orderPaymentInfoDO,
                        condition, orderPaymentInfoESDTO, status, true);
            }else { //不成功，只更新订单支付信息
            	updateOrderPaymentInfo(orderPaymentInfoESDTO.getCompanyId(), orderPaymentInfoDO,
                        condition, orderPaymentInfoESDTO, status, false);
            }
            return true;
        } catch (SystemException e) {
            logger.error("更新订单支付信息失败，  TMS订单支付信息为:" + orderPaymentInfoDO, e);
            record.setErrorReason(JsonUtil.GsonString(e.getErrorReason()));
//            writeMqInfoService.insert(record);
            throw new SystemException(e.getErrorCode(), e.getErrorReason());
        } catch (Exception e) {
            logger.error("更新订单支付ES信息失败，  TMS订单支付信息为:" + orderPaymentInfoDO, e);
            record.setErrorReason(JsonUtil.GsonString(e.getMessage()));
//            writeMqInfoService.insert(record);
            throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_PATMENT_ORDER_STATUS,
                    "acceptPayResultNotify has error: record={" + payNotice + "}, error={" + e.getMessage() + "}");
        }
    }

    private void pushAppMsg(SignBillEsRespDTO signBillEsRespDTO, int driverId, int status, String msg) {
        try {
            Map<String, Object> info = BeanMapUtils.objectToMap(signBillEsRespDTO);
            info.put("noticeType", 200);
            info.put("status", status);
            info.put("msg", msg);
            WriteMqInfoDTO record = new WriteMqInfoDTO();
            record.setOrderType("pay_push");
            record.setQueueName("pay_push");
            record.setMainInfo(JsonUtil.GsonString(info));
//            writeMqInfoService.insert(record);
            AppMessageReqDTO appMessageReqDTO = new AppMessageReqDTO(driverId, "任务", "您有一个运单已成功签收！", info);
            appMessageReqDTO.setInfo(info);
            appMessageService.pushAppMessage(appMessageReqDTO);
        } catch (Exception e) {
            logger.error("签收支付成功消息推送失败，{}", e.getLocalizedMessage());
        }
    }
	/**
	 * 计算两个时间相差的分钟数
	 * @param acceptTime
	 * @param createTime
	 * @return
	 */
	private boolean getOutTimeFlag(Long acceptTime, Long createTime) {
	    long diff = (acceptTime-createTime);
	    // 计算差多少分钟
	    long min = diff/(1000 * 60);
	    if(min>30){
	    	return true;
	    }
		return false;
	}
	/**
	 * 验证支付平台签名信息
	 * @param payNotice
	 * @return
	 */
	private boolean checkValueSign(IPayNoticeVo payNotice) throws Exception {
        // TODO 如果签名验证开启，则验证；否则，直接返回 true
	    if (paySignKeyProperties.isEnable()) {
            String rsaPublic = paySignKeyProperties.getPublicKey();
            if (StringUtil.isBlank(rsaPublic)) {
                throw new Exception("公钥文件内容为空");
            }
            Map<String, Object> map = BeanMapUtils.objectToMap(payNotice);
            return PlatRsaUtil.checkSignRSAMap(map, rsaPublic);
        } else {
            return true;
        }
	}
	/**
	 * 更新支付数据库状态信息
	 * @param status
	 * @param orderPaymentInfoESDTO
	 * @return
	 */
	private OrderPaymentInfoDO updateOrderPaymentInfoDO(boolean outTimeFlag,String status, OrderPaymentInfoESDTO orderPaymentInfoESDTO) {
		boolean flag = false;
		OrderPaymentInfoDO op = new OrderPaymentInfoDO();
		try{
			op.setId(orderPaymentInfoESDTO.getId());
			op.setCompanyId(orderPaymentInfoESDTO.getCompanyId());
			if(outTimeFlag){
				op.setOrderStatus(SignPaymentStatusEnum.TRADE_FAIL.name());
			}else{
				op.setOrderStatus(status);
			}
			op.setOrderStatusTime(System.currentTimeMillis());
			flag = orderPaymentInfoDOMapper.updateByPrimaryKeySelective(op) ==1;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_SIGN_PATMENT_ORDER_STATUS,
					"updateOrderPaymentInfoDO has error: record={" + op + "}, error={" + e.getMessage() + "}");
			logger.error(ex.getMessage(), e);
			throw ex;
		}
		if(flag){
			return op;
		}
		return op;
	}
	/**
	 * 更新支付es信息
	 * @param orderPaymentInfoDO
	 * @param searchConditions
	 * @return
	 */
	private boolean updateOrderPaymentInfoESDTO(OrderPaymentInfoDO orderPaymentInfoDO, List<SearchCondition> searchConditions) {
		OrderPaymentInfoESDTO opies = new OrderPaymentInfoESDTO();
		try{
			EntityUtils.copyProperties(orderPaymentInfoDO, opies);
			Boolean result = new DefaultAbstractSearchUpdateExecutor<OrderPaymentInfoESDTO>(esSearchService) {
				@Override
                public EsConfig getConfig() {
					return EsConfig.orderPaymentInfo();
				}
			}.execute(opies,searchConditions);
			return result;
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_SIGN_PATMENT_ORDER_STATUS,
					"updateOrderPaymentInfoESDTO has error: record={" + opies + "}, error={" + e.getMessage() + "}");
			logger.error(ex.getMessage(), e);
			throw ex;
		}
	}
	/**
	 * 获取查询支付接口返回
	 * @param signPaymentQueryStatusReqDTO
	 * @return
	 */
	private DataResult<IQueryResultVo> queryStatusByOrderId(SignPaymentQueryStatusReqDTO signPaymentQueryStatusReqDTO) {
		//根据支付订单去查询支付查询接口
		IQueryPayStatusDto queryPayStatusDto = new IQueryPayStatusDto();
		//入参对象赋值
		queryPayStatusDto = this.getQueryPayStatusDto(signPaymentQueryStatusReqDTO,queryPayStatusDto);
		return this.payService.queryStatusByOrderId(queryPayStatusDto);
	}
	//封装mobileOrderPayDto对象参数值
	private IMobileOrderPayDto getmobileOrderPayDto(SignGetQRCodeReqDTO signGetQRCodeReqDTO, IMobileOrderPayDto mobileOrderPayDto,
                                                   DispatcherDetailEsDTO dispatcherDetailEsDTO, WaybillEsDTO waybillEsDTO) {
		//配置参数
		String tranType = SignPaymentConstants.TRAN_TYPE;//"CONSUME";
		//todo...
		String businessOrderNo = String.valueOf(System.currentTimeMillis());//"4639578412312312";//waybillEsDTO.getOrderCode(); 
		String takeDelivery = SignPaymentConstants.TAKE_DELIVERY;//"NOTPASSAGE";


		BigDecimal money = signGetQRCodeReqDTO.getAmountMoney().multiply(new BigDecimal("100"));
		// TODO 假支付金额
//		BigDecimal money = new BigDecimal("1");
        String amountMoney = String.format("%.0f", money);
		String userId = String.valueOf(waybillEsDTO.getMerchantId());
		String currency = SignPaymentConstants.CURRENCY;
		String supplement  = toSupplementJson(amountMoney,waybillEsDTO);
		String organizationCode = waybillEsDTO.getMerchantId().toString();
		String goodsInfo = waybillEsDTO.getOrderTypeName();
		String notifyUrl = SignPaymentConstants.NOTIFY_URL;
		//业务平台编号(B2B：6000,TMS:4000,BOSS:100)
		String businessCode = SignPaymentConstants.BUSINESS_CODE;
		//todo...
		String sign = "RMB";
		mobileOrderPayDto.setBusinessCode(businessCode);
		mobileOrderPayDto.setAmountMoney(amountMoney);
		mobileOrderPayDto.setOrganizationCode(organizationCode);
		mobileOrderPayDto.setBusinessOrderNo(businessOrderNo);
		mobileOrderPayDto.setTranType(tranType);
		mobileOrderPayDto.setTakeDelivery(takeDelivery);
		mobileOrderPayDto.setCurrency(currency);
		mobileOrderPayDto.setUserId(userId);
		mobileOrderPayDto.setUserCode("");
		mobileOrderPayDto.setNotifyUrl(notifyUrl);
		mobileOrderPayDto.setGoodsInfo(goodsInfo);
		mobileOrderPayDto.setSupplement(supplement);
		mobileOrderPayDto.setSign(sign);
		return mobileOrderPayDto;
	}
	//map转换成json
	private String toSupplementJson(String amountMoney, WaybillEsDTO waybillEsDTO) {
		Map<String,String> jsonMap = new HashMap<String,String>();
		List<Map> listMap  =new ArrayList<Map>();
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();  
		IQueryFinanceEntrustRelationParam qf = new IQueryFinanceEntrustRelationParam();
		qf.setBizOrgId(waybillEsDTO.getDealerId().intValue());
//        qf.setBizOrgId(370); // TODO 测试支付组织id，数据完整后放开上面代码
		List<IFinanceEntrustRelationInfoDto> financeIds =this.bossFinanceEntrusRelationService.queryFinanceEntrustRelation(qf);
		jsonMap.put("userId", financeIds.get(0).getSettleOrgId().toString());
		jsonMap.put("username", financeIds.get(0).getSettleOrgName());
		jsonMap.put("subAmountMoney", amountMoney);
		listMap.add(jsonMap);
		return gson.toJson(listMap);
	}
	//封装MobilePayDto对象参数
	private IMobilePayDto getMobilePayDto(SignGetQRCodeReqDTO signGetQRCodeReqDTO, ICreatePayOrderVo cp, IMobileOrderPayDto mobileOrderPayDto, IMobilePayDto mobilePayDto, WaybillEsDTO waybillEsDTO) {
		mobilePayDto.setAmountMoney(mobileOrderPayDto.getAmountMoney());
		mobilePayDto.setBusinessCode(mobileOrderPayDto.getBusinessCode());
		mobilePayDto.setOrderNo(cp.getOrderNo());
		mobilePayDto.setPaymentMode(signGetQRCodeReqDTO.getPaymentMode());
		mobilePayDto.setUserId(mobileOrderPayDto.getUserId());
		mobilePayDto.setSign("");
		return mobilePayDto;
	}
	//封装查询接口入参对象
	private IQueryPayStatusDto getQueryPayStatusDto(SignPaymentQueryStatusReqDTO signPaymentQueryStatusReqDTO, IQueryPayStatusDto queryPayStatusDto) {
		EntityUtils.copyProperties(signPaymentQueryStatusReqDTO, queryPayStatusDto);
		// TODO sign先暂时空着
		String sign = "";
		queryPayStatusDto.setSign(sign);
		return queryPayStatusDto;
	}
	/**保存数据到数据库*/
	private OrderPaymentInfoDO saveDB(OrderPaymentInfoESDTO orderPaymentInfoESDTO) {
		try{
            OrderPaymentInfoDO orderPaymentInfoDO = EntityUtils.copyProperties(orderPaymentInfoESDTO, OrderPaymentInfoDO.class);
            int i = orderPaymentInfoDOMapper.insertSelective(orderPaymentInfoDO);
            if (i == 1) {
                return orderPaymentInfoDO;
            }
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_SIGN_PATMENT_SAVE_FAIL,
					"saveDB has error: record={" + orderPaymentInfoESDTO + "}, error={" + e.getMessage() + "}");
			logger.error(ex.getMessage(), e);
			throw ex;
		}
		return null;
	}

    private OrderPaymentInfoESDTO getOrderPaymentInfoDO(IMobileOrderPayDto mobileOrderPayDto, SignGetQRCodeReqDTO signGetQRCodeReqDTO,
                                                        DispatcherDetailEsDTO dispatcherDetailEsDTO,
                                                        String orderNo, String qrCodeUrl) {
        OrderPaymentInfoESDTO esdto = new OrderPaymentInfoESDTO();
        Long orderPaymentId = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_ORDER_PAYMENT_INFO);
        esdto.setId(orderPaymentId);
        esdto.setAmountMoney(new BigDecimal(mobileOrderPayDto.getAmountMoney()));
        esdto.setBusinessCode(mobileOrderPayDto.getBusinessCode());
        esdto.setBusinessOrderNo(mobileOrderPayDto.getBusinessOrderNo());
        esdto.setPaymentMode(SignPaymentConstants.PAYMENT_MODE);
        esdto.setLoaded(signGetQRCodeReqDTO.getLoaded());
        esdto.setSignType(signGetQRCodeReqDTO.getSignType());
        esdto.setQrcodeurl(qrCodeUrl);
        esdto.setOrderNo(orderNo);
        // TODO 二维码生成时候给数据库一个初始值订单创建中
        // 真实支付代码
        esdto.setOrderStatus(SignPaymentStatusEnum.TRADE_ING.getName());
        // 假支付代码 生成二维码后直接支付完成
//			esdto.setOrderStatus(SignPaymentStatusEnum.TRADE_SUCCESS.getName());
        esdto.setSupplement(mobileOrderPayDto.getSupplement());
//			esdto.setUserCode(mobileOrderPayDto.getUserCode());
        esdto.setUserId(mobileOrderPayDto.getUserId());
        esdto.setCompanyId(dispatcherDetailEsDTO.getCompanyId());
        esdto.setWaybillId(dispatcherDetailEsDTO.getWaybillId());
        esdto.setWaybillCode(dispatcherDetailEsDTO.getCode());
        esdto.setDispatcherId(dispatcherDetailEsDTO.getDispatcherId());
        esdto.setDriverId(dispatcherDetailEsDTO.getDriverId().longValue());
        esdto.setCreateTime(System.currentTimeMillis());
        return esdto;
    }

    /**同步订单支付信息数据到ES中*/
	private void saveES(OrderPaymentInfoESDTO opes) {
		try{
			new DefaultAbstractSearchSaveExecutor<OrderPaymentInfoESDTO>(esSearchService) {
	            @Override
	            public EsConfig getConfig() {
	                return EsConfig.orderPaymentInfo();
	            }
	        }.execute(opes);
		} catch (Exception e) {
			SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_SIGN_PATMENT_SAVE_FAIL,
					"saveES has error: record={" + opes + "}, error={" + e.getMessage() + "}");
			logger.error(ex.getMessage(), e);
			throw ex;
		}
	}

	@Override
	public Boolean isPaySuccess(Long waybillId) {
        List<SearchCondition> searchConditions = SearchConditionUtils.start()
                .addEqualCondition("waybillId", waybillId).end();
        List<OrderPaymentInfoESDTO> list = new DefaultAbstractSearchQueryExecutor<OrderPaymentInfoESDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.orderPaymentInfo();
            }
        }.list(searchConditions);
        // TODO 订单支付信息中只要有一个是支付成功的，即是 支付成功
        long count = list.stream()
                .filter(item -> "TRADE_SUCCESS".equalsIgnoreCase(item.getOrderStatus()))
                .count();
        return count > 0;
    }
	/**
	 * 开启订单支付事物事务
	 * @param companyId
	 *            派车单信息
	 * @return 事物
	 */
	private TransactionManagerUtils.TransactionProxy getDispatcherTransaction(long companyId) {
		return dtmFactory.create()
				.addTransManager(OrderPaymentInfoDOMapper.class, companyId).build();
	}
	
	
	public static void main1(String[] args) {
		Map<String,String> jsonMap = new HashMap<String,String>();
		
		List<Map> list1  =new ArrayList<Map>();
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();  
		String userId ="123";// waybillEsDTO.getDealerId().toString();
		jsonMap.put("userId", userId);
		jsonMap.put("username", "李磊");
		jsonMap.put("subAmountMoney", "1000");
		jsonMap.put("userCode", userId);
		jsonMap.put("organizationCode", userId);
		list1.add(jsonMap);
		System.out.println("["+gson.toJson(jsonMap)+"]");
		System.out.println(gson.toJson(list1));
	}
}
