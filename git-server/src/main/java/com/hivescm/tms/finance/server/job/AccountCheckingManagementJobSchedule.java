package com.hivescm.tms.finance.server.job;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.sign.AccountCheckingManagementEsDTO;
import com.hivescm.tms.api.dto.es.sign.OrderPaymentInfoESDTO;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.finance.server.component.finance.AccountCheckingManagementEsService;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.dao.entity.finance.AccountCheckingManagementDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.AccountCheckingManagementMapper;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.mogujie.distributed.transction.DynamicTransctionManagerFactory;
import com.mogujie.trade.utils.TransactionManagerUtils;

/**
 * 货款对账
 * 
 * @author lifan
 *
 *         2017年11月27日
 *
 */
@Service
public class AccountCheckingManagementJobSchedule implements SimpleJob {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

//	@Autowired
//	private TradeBillApi tradeBillApi;

	@Autowired
	private ESSearchService esSearchService;

	@Autowired
	private AccountCheckingManagementEsService esService;

	@Autowired
	private IGeneratedIdService generatedIdService;

	@Autowired
	private AccountCheckingManagementMapper doMapper;

	/**
	 * 事务
	 */
	@Autowired
	private DynamicTransctionManagerFactory dtmFactory;

	private static final String BUSINESS_CODE = "4000";
	
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public void execute(ShardingContext shardingContext) {
		logger.info("每天上午10:00执行。开始……");
		// 获取昨天日期：
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.DATE, -6);
//		String yesterday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
//		// 创建请求参数：
//		TradeBillParamDto param = new TradeBillParamDto();
//		param.setBusinessCode(BUSINESS_CODE);
//		param.setTradeDate(yesterday);
//
//		try {
//			DataResult<String> dataResult = tradeBillApi.getPath(param);
//			// 得到文件路径
//			String path = dataResult.getResult();
//			if(null==path) {
//				return;
//				//path = "D:\\test.csv";
//			}
//			//File csv = new File(path); // CSV文件路径
//			//BufferedReader reader = new BufferedReader(new FileReader(csv));
//			URL url = new URL(path);
//			InputStream openStream = url.openStream();
//			
//			InputStreamReader reader1 = new InputStreamReader(openStream, "GBK");
//			BufferedReader reader = new BufferedReader(reader1);
//			String headLine = reader.readLine();// 第一行信息，为标题信息（读取后就没有了）
//			String headItem[] = headLine.split(",");
//
//			// 定义交易类型，交易流水，业务平台订单号，数据状态 下标
//			int tradeTypeIndex = 0;
//			int tradeFlowIndex = 0;
//			int businessOrderIndex = 0;
//			int tradeDateIndex = 0;
//			int tradeStatusIndex = 0;
//			for (int i = 0; i < headItem.length; i++) {
//				String itemVal = headItem[i];
//				if ("TRADE_TYPE".equals(itemVal)) {
//					tradeTypeIndex = i;
//				} else if ("TRADE_FLOW".equals(itemVal)) {
//					tradeFlowIndex = i;
//				} else if ("BUSINESS_ORDER".equals(itemVal)) {
//					businessOrderIndex = i;
//				} else if ("TRADE_DATE".equals(itemVal)) {
//					tradeDateIndex = i;
//				} else if ("TRADE_STATUS".equals(itemVal)) {
//					tradeStatusIndex = i;
//				}
//			}
//
//			List<AccountCheckingManagementDO> doList = new ArrayList<AccountCheckingManagementDO>();
//			List<AccountCheckingManagementEsDTO> dtoList = new ArrayList<AccountCheckingManagementEsDTO>();
//			String line = null;
//			while ((line = reader.readLine()) != null) {
//				String item[] = line.split(",");// CSV格式文件为逗号分隔符文件，这里根据逗号切分
//				String tradeType = item[tradeTypeIndex];
//				String tradeFlow = item[tradeFlowIndex]; // 交易流水
//				String tradeStatus = item[tradeStatusIndex]; // 交易状态
//				String tradeDate = item[tradeDateIndex]; //交易日期
//				String businessOrder = item[businessOrderIndex]; // 业务平台订单号
//
//				// 根据订单号查询订单支付数据
//				List<SearchCondition> scs = SearchConditionUtils.start()
//						.addCondition("businessOrderNo", businessOrder, ConditionExpressionEnum.EQUAL).end();
//				OrderPaymentInfoESDTO orderPaymentInfoEsDto = new DefaultAbstractSearchQueryExecutor<OrderPaymentInfoESDTO>(
//						esSearchService) {
//					@Override
//					public EsConfig getConfig() {
//						return EsConfig.orderPaymentInfo();
//					}
//				}.get(scs);
//
//				if (null != orderPaymentInfoEsDto) { // 说明没有出现数据丢失的情况
//					AccountCheckingManagementDO managementDo = new AccountCheckingManagementDO();
//					AccountCheckingManagementEsDTO managementEsDto = new AccountCheckingManagementEsDTO();
//					String orderStatus = orderPaymentInfoEsDto.getOrderStatus();
//
//					managementDo.setCompanyId(orderPaymentInfoEsDto.getCompanyId());
//					managementDo.setAccountCheckingTime(System.currentTimeMillis()); //对账时间
//					managementDo.setWaybillCode(orderPaymentInfoEsDto.getWaybillCode()); //运单号
//					managementDo.setOrderCode(businessOrder);  //订单编号
//					managementDo.setPayModel(orderPaymentInfoEsDto.getPaymentMode());  //支付方式
//					managementDo.setTradeSerialNo(tradeFlow); //交易流水号
//					managementDo.setTradeModel(tradeType);  //交易类型
//					managementDo.setPayFee(orderPaymentInfoEsDto.getAmountMoney());  //支付金额
//					managementDo.setWechatPayStatus(orderPaymentInfoEsDto.getOrderStatus());  //微信支付状态
//					managementDo.setPaymentPlatformPayModel(tradeStatus); //支付平台交易状态
//					managementDo.setCreateTime(System.currentTimeMillis());
//					managementDo.setRebackFee(orderPaymentInfoEsDto.getAmountMoney());
//					managementDo.setTradeTime(sdf.parse(tradeDate).getTime());
//
//					if ("TRADE_SUCCESS".equals(orderStatus) && "4".equals(tradeStatus)) {
//						managementDo.setAccountRegulationResult("2");  //调账结果 失败
//						managementDo.setAccountCheckingResult("2");  //对账结果 异常
//					} else if ("TRADE_SUCCESS".equals(orderStatus) && "3".equals(tradeStatus)) {
//						managementDo.setAccountRegulationResult(null);
//						managementDo.setAccountCheckingResult("1"); //成功
//					} else if ("TRADE_SUCCESS".equals(orderStatus) && "2".equals(tradeStatus)) {
//						managementDo.setAccountRegulationResult(null);
//						managementDo.setAccountCheckingResult("3");  //挂账
//					} else if ("TRADE_FAIL".equals(orderStatus) && "4".equals(tradeStatus)) {
//						managementDo.setAccountRegulationResult(null);
//						managementDo.setAccountCheckingResult("1");
//					} else if ("TRADE_FAIL".equals(orderStatus) && "3".equals(tradeStatus)) {
//						managementDo.setAccountRegulationResult("1");
//						managementDo.setAccountCheckingResult("2");
//					} else if ("TRADE_FAIL".equals(orderStatus) && "2".equals(tradeStatus)) {
//						managementDo.setAccountRegulationResult(null);
//						managementDo.setAccountCheckingResult("3");
//					}
//
//					managementEsDto = EntityUtils.copyProperties(managementDo, AccountCheckingManagementEsDTO.class);
//					managementEsDto.setInvoiceOrgId(orderPaymentInfoEsDto.getInvoiceOrgId());
//					doList.add(managementDo);
//					dtoList.add(managementEsDto);
//				}
//			}
//			//获取主键
//			List<Long> ids = generatedIdService.getIncrBatchUniqueId(GeneratedIdConstants.TMS_ACCOUNT_CHECKING_MANAGE,
//					doList.size());
//			for (int i = 0; i < doList.size(); i++) {
//				doList.get(i).setId(ids.get(i));
//				dtoList.get(i).setId(ids.get(i));
//			}
//			//保存数据
//			Long carrierId = doList.get(0).getCompanyId().longValue();
//			TransactionManagerUtils.TransactionProxy transaction = getTransaction(carrierId);
//			try {
//				batchSave(doList, dtoList);
//				transaction.commit();
//			} catch (Exception e) {
//				transaction.rollback();
//				SystemException ex = new SystemException(ExceptionCodeConstants.ERROR_ACCOUNT_CHECKING,
//						"批量保存对账数据的方法batchSave has error: error={" + e.getMessage() + "}");
//				logger.error(ex.getMessage(), e);
//			}
//			
//			logger.info("对账任务结束");
//		} catch (Exception e) {
//			logger.error("对账任务异常，错误信息：",e);
//		}
	}

	public Boolean batchSave(List<AccountCheckingManagementDO> doList, List<AccountCheckingManagementEsDTO> dtoList)
			throws Exception {
		Boolean dbResult = false, esResult = false;

		dbResult = doMapper.insertBatch(doList,doList.get(0).getCompanyId()) > 0; // 保存到数据库
		if (dbResult) {
			esResult = esService.batchSave(dtoList);
			if(!esResult) {
				throw new SystemException(ExceptionCodeConstants.ERROR_ACCOUNT_CHECKING,"批量保存对账数据异常");
			}
		}
		return dbResult && esResult;
	}

	private TransactionManagerUtils.TransactionProxy getTransaction(Long companyId) {
		return null;
//		return dtmFactory.create().addTransManager(WaybillMapper.class, companyId)
//				.addTransManager(DispatcherDetailMapper.class, companyId).build();
	}
}
