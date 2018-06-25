package com.hivescm.tms.finance.server.component.pcsign.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.common.exception.TmsBusinessException;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignInitializeWayBillReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.UpdateSignStatusReqDTO;
import com.hivescm.tms.api.dto.es.stock.StockLockSyncDTO;
import com.hivescm.tms.api.dto.es.waybill.VehicleTailAfterEsDTO;
import com.hivescm.tms.api.dto.es.waybill.component.TmsWaybillEsDTO;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptRequirmentTypeEnum;
import com.hivescm.tms.api.enums.biz.sign.NormalSignType;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.stock.StockLockTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillDistributionTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillTrackStatusEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.constants.TmsCodeRuleBizCode;
import com.hivescm.tms.finance.server.component.pcsign.AddSignService;
import com.hivescm.tms.finance.server.component.pcsign.SignReceiptService;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDetailDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignFeeDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignRefuseDO;
import com.hivescm.tms.finance.server.feign.stock.IStockLockService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.db.DbOperationService;
import com.hivescm.tms.finance.server.service.lock.SignLockService;
import com.hivescm.tms.finance.server.service.pcsign.BillTrackingService;
import com.hivescm.tms.finance.server.service.pcsign.SignBillingFlowService;
import com.hivescm.tms.finance.server.service.pcsign.SignFinanceService;
import com.hivescm.tms.finance.server.service.pcsign.SignStockService;
import com.hivescm.tms.finance.server.service.sign.EsSignDetailsService;
import com.hivescm.tms.finance.server.service.sign.EsSignFeeService;
import com.hivescm.tms.finance.server.service.sign.EsSignRefuseService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;
import com.hivescm.tms.intranet.gateway.api.dto.IdCode.IdCodeReqNewDTO;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.IdCodeService;

/**
 * 
 * 
 * @author Administrator
 *
 */
@Service
public class AddSignServiceImpl implements AddSignService {
	private Logger logger = LoggerFactory.getLogger(AddSignServiceImpl.class);

	@Autowired
	private IGeneratedIdService generatedIdService;

	@Autowired
	private EsSignService esSignService;

	@Autowired
	private EsSignDetailsService esSignDetailsService;

	@Autowired
	private EsSignFeeService esSignFeeService;

	@Autowired
	private DbOperationService dbOperationService;

	@Autowired
	private WaybillService waybillService;

	@Autowired
	private IdCodeService idCodeService;

	@Autowired
	private EsSignRefuseService esSignRefuseService;

	@Autowired
	private SignReceiptService signReceiptService;
	
	@Autowired
	private SignLockService signLockService;
	
	@Autowired
	private BillTrackingService billTrackingService;
	
	@Autowired
	private SignFinanceService signFinanceService;
	
	@Autowired
	private SignBillingFlowService signBillingFlowService;
	
	@Autowired
	private SignStockService signStockService;
	
	@Autowired
	private  IStockLockService iStockLockService;
	
	private ExecutorService executor = Executors.newFixedThreadPool(5);
	
	@Override
	public Boolean insertSign(TmsSignEsDTO tmsSignEsDTO) {
		Boolean db = false, es = false;
		DataResult<Boolean> upWaybillStatus = null;
		String waybillcode = "";
		try {
			this.lockStock(tmsSignEsDTO.getSignEsDTO().getSignOrgId().longValue(), tmsSignEsDTO.getSignEsDTO().getWaybillId());
			//查询运单
			TmsWaybillEsDTO waybill = this.searchWaybill(tmsSignEsDTO);
			// 锁定运单防止重复操作
			waybillcode = waybill.getCode()+waybill.getCompanyId();
			lockWaybill(waybillcode);
			// 减库存操作参数
			List<StockLockSyncDTO> stocks = new ArrayList<>();
			// 签收明细
			List<SignDetailDO> signDetailDOs = compParam(tmsSignEsDTO, waybill,stocks);
			// 保存数据库
			db = saveDb(tmsSignEsDTO,signDetailDOs);
			// ->保存es
			es = insertEs(tmsSignEsDTO.getSignEsDTO(),tmsSignEsDTO.getSignDetailsEsDTO(),tmsSignEsDTO.getSignFeeEsDTO());
			// ->更改运单状态及es
			upWaybillStatus = updateWaybill(tmsSignEsDTO);
			// 库存操作
			reduceStock(stocks);
			// 调用回单服务
			Boolean receipt = receipt(tmsSignEsDTO,waybill);
			// 运单跟踪 计费流水
			newThread(tmsSignEsDTO,waybill);
			// 调用财务接口
			sendFinanceMq(waybill);
			// 解除锁定
			unlockWaybill(waybillcode);
			return db && es && receipt;
		}catch (SystemException se) {
			logger.error(se.getMessage(), se);
			throw se;
		}catch (Exception e) {
			// 回滚操作
			//解除锁定
			iStockLockService.unlock(tmsSignEsDTO.getSignEsDTO().getSignOrgId().longValue(), tmsSignEsDTO.getSignEsDTO().getWaybillId());
			unlockWaybill(waybillcode);
			rollBack(db,es,upWaybillStatus,tmsSignEsDTO);
			TmsBusinessException ex = ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, e,
					"新增签收失败,TmsSignEsDTO:%s", tmsSignEsDTO.toString());
			logger.error(ex.getMessage(), e);
			throw ex;
		}
	}

	/**
	 * 锁定库存
	 * @param orgId
	 * @param waybillId
	 * @return
	 */
	private Boolean lockStock(Long orgId,Long waybillId){
		DataResult<Boolean> lock = iStockLockService.lock(orgId, waybillId);
		if(lock.getResult() == null || !lock.isSuccess() || !lock.getResult()){
			throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_LOCK, "该运单正被其他用户操作");
		}
		return lock.isSuccess();
		
	}
	/**
	 * 查运单
	 * @param tmsSignEsDTO
	 * @return
	 */
	private TmsWaybillEsDTO searchWaybill(TmsSignEsDTO tmsSignEsDTO){
		SignEsDTO signEsDTO = tmsSignEsDTO.getSignEsDTO();
		SignInitializeWayBillReqDTO req = new SignInitializeWayBillReqDTO();
		req.setCompanyId(signEsDTO.getCompanyId().intValue());
		req.setOrgId(signEsDTO.getSignOrgId());
		req.setWayillCode(signEsDTO.getCode());
		TmsWaybillEsDTO signQueryWaybillResp = waybillService.queryWaybillDteails(req).getResult();
		if (SignStatusEnum.REFUSE_SIGN.getType() == tmsSignEsDTO.getSignMode().getType()) {
			if(null == signQueryWaybillResp || null == signQueryWaybillResp.getWaybillStockEsDto()){
				 throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_LOCK, "运单已被其他用户签收");
			}
		}else{
			if(null == signQueryWaybillResp || null == signQueryWaybillResp.getWaybillStockEsDto() || signEsDTO.getSignNumber()>signQueryWaybillResp.getWaybillStockEsDto().getPackageNum()){
				 throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_LOCK, "运单已被其他用户签收");
			}
		}
		return signQueryWaybillResp;
	}
	/**
	 * 调用生成应收
	 * @param waybill
	 */
	private void sendFinanceMq(TmsWaybillEsDTO waybill){
		List<Long> waybillIds = new ArrayList<>();
		waybillIds.add(waybill.getId());
		signFinanceService.sendMq(waybillIds, SignStatusEnum.SIGNED.getType());
	}
	/**
	 * 起新线程 执行跟踪信息和推计费流水
	 * @param tmsSignEsDTO
	 * @param waybill
	 */
	private void newThread(TmsSignEsDTO tmsSignEsDTO,TmsWaybillEsDTO waybill){
		executor.submit(()->{
			try {
				//运单跟踪
				billtracking(tmsSignEsDTO);
				//当运单的签收状态为空，或签收状态为未签收时推送计费流水
				if(null == waybill.getSignStatus() || waybill.getSignStatus().intValue() == SignStatusEnum.NO_SIGN.getType()){
					List<Long> waybillids = new ArrayList<>();
					waybillids.add(tmsSignEsDTO.getSignEsDTO().getWaybillId());
					signBillingFlowService.billingFlow(waybillids, SignStatusEnum.SIGNED.getType());
				}
			} catch (Exception e) {
				// 子线程内异常不能反馈，吃掉异常，单据失败
				logger.error("同步运单跟踪信息失败 ", e);
			}
		});
	}
	/**
	 * 运单跟踪信息
	 * @param tmsSignEsDTO
	 */
	private void billtracking(TmsSignEsDTO tmsSignEsDTO){
		VehicleTailAfterEsDTO vehicleTailAfterEsDTO = new VehicleTailAfterEsDTO();
		SignEsDTO sign = tmsSignEsDTO.getSignEsDTO();
		vehicleTailAfterEsDTO.setCompanyId(sign.getCompanyId().intValue());
		vehicleTailAfterEsDTO.setWaybillCode(sign.getCode());
		vehicleTailAfterEsDTO.setWaybillId(sign.getWaybillId());
		vehicleTailAfterEsDTO.setCompanyName(null);
		vehicleTailAfterEsDTO.setStatus(WaybillTrackStatusEnum.SIGNED.getType());
		vehicleTailAfterEsDTO.setStatusName(WaybillTrackStatusEnum.SIGNED.getName());
		vehicleTailAfterEsDTO.setCreateUser(sign.getCreateUser());
		vehicleTailAfterEsDTO.setCreateUserName(sign.getCreateUserName());
		vehicleTailAfterEsDTO.setCreateTime(System.currentTimeMillis());
		vehicleTailAfterEsDTO.setInvoiceOrgId(sign.getSignOrgId());
		vehicleTailAfterEsDTO.setInvoiceOrgName(sign.getSignOrgName());
		vehicleTailAfterEsDTO.setLightbulbType(1);//可见
		vehicleTailAfterEsDTO.setSignUserName(sign.getSignPeople());
		billTrackingService.billTracking(vehicleTailAfterEsDTO);
	}
	/**
	 * 组装参数
	 * @param tmsSignEsDTO
	 * @param waybill
	 * @param stocks
	 * @return
	 */
	private List<SignDetailDO> compParam(TmsSignEsDTO tmsSignEsDTO,TmsWaybillEsDTO waybill,List<StockLockSyncDTO> stocks){
		// 签收主表数据
		assembleSign(tmsSignEsDTO,waybill);
		// 组装签收明细信息
		List<SignDetailDO> signDetailDOs = assembleSignDetails(tmsSignEsDTO, stocks);
		// 计算签收后重量体积
		calculate(tmsSignEsDTO,waybill);
		return signDetailDOs;
	}
	/**
	 * 计算签收后的重量体积
	 * @param tmsSignEsDTO
	 * @param waybill
	 */
	private void calculate(TmsSignEsDTO tmsSignEsDTO,TmsWaybillEsDTO waybill){
		//签收的重量体积
		SignEsDTO signEsDTO = tmsSignEsDTO.getSignEsDTO();
		signEsDTO.setSignWeight(waybill.getWeight().divide(new BigDecimal(waybill.getTotalNum()),2,BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal((signEsDTO.getSignNumber()==null?0:signEsDTO.getSignNumber())+(signEsDTO.getRefuseNumber()==null?0:signEsDTO.getRefuseNumber()))).setScale(2, BigDecimal.ROUND_HALF_UP));
		signEsDTO.setSignVolume(waybill.getVolume().divide(new BigDecimal(waybill.getTotalNum()),2,BigDecimal.ROUND_HALF_UP)
				.multiply(new BigDecimal((signEsDTO.getSignNumber()==null?0:signEsDTO.getSignNumber())+(signEsDTO.getRefuseNumber()==null?0:signEsDTO.getRefuseNumber()))).setScale(2, BigDecimal.ROUND_HALF_UP));
	}
	/**
	 * 防止重复签收
	 * @param waybillCode
	 */
	private void lockWaybill(String waybillCode){
		// 锁定运单防止重复操作
		List<String> list = new ArrayList<>();
		list.add(waybillCode);
		if(!signLockService.lockSign(list)){
			 throw new SystemException(ExceptionCodeConstants.ERROR_SIGN_LOCK, "运单正在被其他用户操作");
		};
	}
	/**
	 * 减库存
	 * @param stocks
	 * @return
	 */
	private void reduceStock(List<StockLockSyncDTO> stocks){
		signStockService.sendMq(stocks);
	}
	/**
	 * 更新运单
	 * @param tmsSignEsDTO
	 * @return
	 */
	private DataResult<Boolean> updateWaybill(TmsSignEsDTO tmsSignEsDTO){
		DataResult<Boolean> upWaybillStatus = waybillService.updateWyabillInfoFromSign(compUpdateWaybillParm(tmsSignEsDTO));
		if(null == upWaybillStatus || !upWaybillStatus.getResult()){
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "新增签收更新运单失败,status:%s"+ upWaybillStatus.getStatus());
		};
		return upWaybillStatus;
	}
	/**
	 * 解除锁定
	 * @param waybillCode
	 */
	private void unlockWaybill(String waybillCode){
		// 锁定运单防止重复操作
		List<String> list = new ArrayList<>();
		list.add(waybillCode);
		signLockService.unlockSign(list);
	}
	/**
	 * 数据回滚
	 * @param es
	 * @param status
	 * @param upWaybillStatus
	 */
	private void rollBack(Boolean db,Boolean es,DataResult<Boolean> upWaybillStatus,TmsSignEsDTO tmsSignEsDTO){
		if((db && !es)||(null == upWaybillStatus || !upWaybillStatus.getResult())){
			//删除数据库
			rollBackDb(tmsSignEsDTO);
			//删除es
			esSignService.deleteBySignId(tmsSignEsDTO.getSignEsDTO().getId());
			esSignDetailsService.deleteBySignId(tmsSignEsDTO.getSignEsDTO().getId());
			esSignFeeService.deleteBySignId(tmsSignEsDTO.getSignEsDTO().getId());
			//没有拒签时不需要删除
			if(null != tmsSignEsDTO.getSignEsDTO().getRefuseNumber() && tmsSignEsDTO.getSignEsDTO().getRefuseNumber()>0){
				esSignRefuseService.deleteSignRefuseEsDTOBySignId(tmsSignEsDTO.getSignEsDTO().getId());
			}
		}
		if(null == upWaybillStatus || !upWaybillStatus.getResult()){
			//TODO回滚库存
		}
	}
	/**
	 * 删除数据库数据
	 * @param tmsSignEsDTO
	 */
	private void rollBackDb(TmsSignEsDTO tmsSignEsDTO){
		SignDO signDO = new SignDO();
		signDO.setCompanyId(tmsSignEsDTO.getSignEsDTO().getCompanyId());
		SignRefuseDO signRefuseDO = new SignRefuseDO();
		signRefuseDO.setCompanyId(tmsSignEsDTO.getSignEsDTO().getCompanyId());
		SignDetailDO details = new SignDetailDO();
		details.setCompanyId(tmsSignEsDTO.getSignEsDTO().getCompanyId());
		SignFeeDO signFeeDO = new SignFeeDO();
		signFeeDO.setCompanyId(tmsSignEsDTO.getSignEsDTO().getCompanyId());
		List<Long> arrayList = new ArrayList<>();
		arrayList.add(tmsSignEsDTO.getSignEsDTO().getId());
		dbOperationService.delete(signDO, details, signFeeDO, signRefuseDO, arrayList);
	}
	/**
	 * 保存es
	 * @param signEsDTO
	 * @param signDetailsEsDTOList
	 * @param signFeeEsDTO
	 * @return
	 */
	private Boolean insertEs(SignEsDTO signEsDTO,List<SignDetailsEsDTO> signDetailsEsDTOList,SignFeeEsDTO signFeeEsDTO){
		if(!esSignService.insertSignEsDTO(signEsDTO)
				|| !esSignDetailsService.insertSignDetailsEsDTO(signDetailsEsDTOList)
				|| !esSignFeeService.insertSignFeeEsDTO(signFeeEsDTO)){
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "新增签收保存es失败");
		}
		return true;
	}
	
	private Boolean saveDb(TmsSignEsDTO tmsSignEsDTO,List<SignDetailDO> signDetailDOs){
		Boolean db = false;
		SignFeeDO fee = assembleSignFee(tmsSignEsDTO);
		// 拒收单信息
		SignRefuseEsDTO refuse = compRefuseParam(tmsSignEsDTO.getSignEsDTO(),tmsSignEsDTO.getGroupId());
		SignDO sign = EntityUtils.copyProperties(tmsSignEsDTO.getSignEsDTO(), SignDO.class);
		// ->保存数据库
		SignRefuseDO refuseDo = null;
		Boolean refuseEs = true;
		if (null != refuse) {
			refuseDo = EntityUtils.copyProperties(refuse, SignRefuseDO.class);
			refuseEs = esSignRefuseService.insertSignRefuseEsDTO(refuse);
			db = dbOperationService.saveSignInfo(sign, signDetailDOs, fee, refuseDo);
		} else {
			db = dbOperationService.saveSignInfo(sign, signDetailDOs, fee);
		}
		return db && refuseEs;
	}
	/**
	 * 签回单逻辑
	 * @param tmsSignEsDTO
	 * @return
	 */
	private Boolean receipt(TmsSignEsDTO tmsSignEsDTO,TmsWaybillEsDTO waybill){
		if(waybill.getBackTypeValue() !=null && (ReceiptRequirmentTypeEnum.SIGN_BILL.getType()==waybill.getBackTypeValue().intValue() || ReceiptRequirmentTypeEnum.SIGN_GRASP.getType()==waybill.getBackTypeValue().intValue() || ReceiptRequirmentTypeEnum.SIGN_RECEIPT.getType()==waybill.getBackTypeValue().intValue())){
			signReceiptService.signReceipt(tmsSignEsDTO);
		}
		return true;
	}
	/**
	 * 保存签收主表数据
	 * 
	 * @param tmsSignEsDTO
	 */
	private void assembleSign(TmsSignEsDTO tmsSignEsDTO,TmsWaybillEsDTO waybill) {
		// --> 生成主键id
		SignEsDTO signEsDTO = tmsSignEsDTO.getSignEsDTO();
		IdCodeReqNewDTO idCodeReqNewDTO = new IdCodeReqNewDTO();
		idCodeReqNewDTO.setGroupId(tmsSignEsDTO.getGroupId());
		idCodeReqNewDTO.setBizCode(TmsCodeRuleBizCode.TMS_NEW_QS);
		DataResult<String> code = idCodeService.generatedNew(idCodeReqNewDTO);

		signEsDTO.setSignBatchNumber(code.getResult());
		signEsDTO.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_SIGN));
		signEsDTO.setDeliveryType(WaybillDistributionTypeEnum.GET.getType());
		signEsDTO.setDeliveryTypeName(WaybillDistributionTypeEnum.GET.getName());
		signEsDTO.setCreateTime(System.currentTimeMillis());
		signEsDTO.setInvoiceUser(waybill.getInvoiceUser());
		signEsDTO.setReceiptUser(waybill.getReceiptUser());
		signEsDTO.setDestOrgId(waybill.getDestOrgId());
		signEsDTO.setWaybillType(waybill.getWaybillType());
		signEsDTO.setTotalNum(waybill.getTotalNum());
		isNormalSign(signEsDTO);
		setSignStatus(tmsSignEsDTO,signEsDTO,waybill);
		// 赋值到站其他费用
		signEsDTO.setOtherFeeStation(tmsSignEsDTO.getSignFeeEsDTO().getOtherFeeStation());
	}
	/**
	 * 判断是否正常签收
	 * @param signEsDTO
	 */
	private void isNormalSign(SignEsDTO signEsDTO){
		//是否正常签收
		if (signEsDTO.getNormalSignType()!=null&& signEsDTO.getNormalSignType()== NormalSignType.ABNORMAL_SIGN.getType()){
			signEsDTO.setNormalSignType(NormalSignType.ABNORMAL_SIGN.getType());
			signEsDTO.setNormalSignTypeName(NormalSignType.ABNORMAL_SIGN.getName());
		}else {
			signEsDTO.setNormalSignType(NormalSignType.NORMAL_SIGN.getType());
			signEsDTO.setNormalSignTypeName(NormalSignType.NORMAL_SIGN.getName());

		}
	}
	/**
	 * 签收状态赋值
	 * @param tmsSignEsDTO
	 * @param signEsDTO
	 */
	private void setSignStatus(TmsSignEsDTO tmsSignEsDTO,SignEsDTO signEsDTO,TmsWaybillEsDTO waybill){
		if (SignStatusEnum.REFUSE_SIGN.getType() == tmsSignEsDTO.getSignMode().getType()) {
			// 拒签逻辑
			signEsDTO.setUnsignedNumber(
					(signEsDTO.getTotalNum() == null ? 0 : signEsDTO.getTotalNum())
					-(waybill.getTotalRefuseSignNumber()==null?0:waybill.getTotalRefuseSignNumber())-(waybill.getTotalSignNumber()==null?0:waybill.getTotalSignNumber()));
			signEsDTO.setSignStatus(SignStatusEnum.REFUSE_SIGN.getType());
			signEsDTO.setSignStatusName(SignStatusEnum.REFUSE_SIGN.getName());
			signEsDTO.setNormalSignType(NormalSignType.ABNORMAL_SIGN.getType());
			signEsDTO.setNormalSignTypeName(NormalSignType.ABNORMAL_SIGN.getName());
		} else {
			signEsDTO.setUnsignedNumber(
					(signEsDTO.getTotalNum() == null ? 0 : signEsDTO.getTotalNum())
							- (signEsDTO.getRefuseNumber() == null ? 0
									: signEsDTO.getRefuseNumber())
							- (signEsDTO.getSignNumber() == null ? 0
									: signEsDTO.getSignNumber())-(waybill.getTotalRefuseSignNumber()==null?0:waybill.getTotalRefuseSignNumber())-(waybill.getTotalSignNumber()==null?0:waybill.getTotalSignNumber()));
			// 签收确认逻辑 拒签数量为零 则是全部签收 签收件数为0则是全部拒签，都不为零则是部分签收
			if (signEsDTO.getRefuseNumber().equals(0)) {
				signEsDTO.setSignStatus(SignStatusEnum.SIGNED.getType());
				signEsDTO.setSignStatusName(SignStatusEnum.SIGNED.getName());
			} else if (signEsDTO.getSignNumber().equals(0)) {
				signEsDTO.setUnsignedNumber(0);
				signEsDTO.setSignStatus(SignStatusEnum.REFUSE_SIGN.getType());
				signEsDTO.setSignStatusName(SignStatusEnum.REFUSE_SIGN.getName());
			} else {
				signEsDTO.setSignStatus(SignStatusEnum.PARTIAL_SIGN.getType());
				signEsDTO.setSignStatusName(SignStatusEnum.PARTIAL_SIGN.getName());
			}
		}
		signEsDTO.setSignType(signEsDTO.getSignStatus());
		signEsDTO.setSignTypeName(signEsDTO.getSignStatusName());

	}
	/**
	 * 保存签收明细表并且扣减库存 送货签收不用扣减库存
	 * 
	 * @param tmsSignEsDTO
	 * @return
	 * @throws Exception
	 */
	private List<SignDetailDO> assembleSignDetails(TmsSignEsDTO tmsSignEsDTO, List<StockLockSyncDTO> stocks) {
		List<SignDetailDO> detailsDOs = new ArrayList<>();
		if (null != tmsSignEsDTO.getSignDetailsEsDTO() && tmsSignEsDTO.getSignDetailsEsDTO().size() > 0) {
			Integer refuseNumber = 0;
			for (SignDetailsEsDTO signDetailsEsDTO : tmsSignEsDTO.getSignDetailsEsDTO()) {
				signDetailsEsDTO.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_SIGN_DETAILS));
				signDetailsEsDTO.setSignId(tmsSignEsDTO.getSignEsDTO().getId());
				signDetailsEsDTO.setWaybillId(tmsSignEsDTO.getSignEsDTO().getWaybillId());
				signDetailsEsDTO.setCompanyId(tmsSignEsDTO.getSignEsDTO().getCompanyId());
				if (SignStatusEnum.REFUSE_SIGN.getType() == tmsSignEsDTO.getSignMode().getType()) {
					// 拒签逻辑 若是拒签时需要在这计算拒签数量 拒签数量=每种货物的派送件数相加
					tmsSignEsDTO.getSignEsDTO().setSignNumber(0);
					refuseNumber = refuseNumber+signDetailsEsDTO.getDispatcherNumber();
					tmsSignEsDTO.getSignEsDTO().setRefuseNumber(refuseNumber);
					signDetailsEsDTO.setRefuseCause(tmsSignEsDTO.getRefuseCause());
					//拒签时拒签件数等于提货件数 签收件数等于0
					signDetailsEsDTO.setRefuseNumber(signDetailsEsDTO.getDispatcherNumber());
					signDetailsEsDTO.setSignNumber(0);
				}
				SignDetailDO detailsDO = EntityUtils.copyProperties(signDetailsEsDTO, SignDetailDO.class);
				detailsDOs.add(detailsDO);
				// 组装减库存信息
				StockLockSyncDTO stockDetail = new StockLockSyncDTO();
				stockDetail.setBranchId(tmsSignEsDTO.getSignEsDTO().getSignOrgId());
				stockDetail.setCompanyId(signDetailsEsDTO.getCompanyId());
				stockDetail.setSignNum(signDetailsEsDTO.getSignNumber());
				stockDetail.setStockDetailId(signDetailsEsDTO.getWaybillStockDetailId());
				stockDetail.setRefusalNum(signDetailsEsDTO.getRefuseNumber());
				stockDetail.setWaybillId(tmsSignEsDTO.getSignEsDTO().getWaybillId());
				stockDetail.setStockLockType(StockLockTypeEnum.SELF_REFERENCE);
				stocks.add(stockDetail);
			}
		}
		return detailsDOs;
	}

	/**
	 * 保存签收费用表
	 * 
	 * @param tmsSignEsDTO
	 * @return
	 * @throws Exception
	 */
	private SignFeeDO assembleSignFee(TmsSignEsDTO tmsSignEsDTO) {
		tmsSignEsDTO.getSignFeeEsDTO().setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_SIGN_FEE));
		tmsSignEsDTO.getSignFeeEsDTO().setSignId(tmsSignEsDTO.getSignEsDTO().getId());
		tmsSignEsDTO.getSignFeeEsDTO().setWaybillId(tmsSignEsDTO.getSignEsDTO().getWaybillId());
		tmsSignEsDTO.getSignFeeEsDTO().setCompanyId(tmsSignEsDTO.getSignEsDTO().getCompanyId());
		SignFeeDO signFeeDO = EntityUtils.copyProperties(tmsSignEsDTO.getSignFeeEsDTO(), SignFeeDO.class);
		return signFeeDO;
	}

	/**
	 * 组装运单更新状态参数
	 * 
	 * @param tmsSignEsDTO
	 * @return
	 */
	private List<UpdateSignStatusReqDTO> compUpdateWaybillParm(TmsSignEsDTO tmsSignEsDTO) {
		List<UpdateSignStatusReqDTO> reqList = new ArrayList<>();
		UpdateSignStatusReqDTO req = new UpdateSignStatusReqDTO();
		req.setWaybillId(tmsSignEsDTO.getSignEsDTO().getWaybillId());
		req.setSignNumber(tmsSignEsDTO.getSignEsDTO().getSignNumber());
		req.setRefuseNumber(tmsSignEsDTO.getSignEsDTO().getRefuseNumber());
		req.setCompanyId(tmsSignEsDTO.getSignEsDTO().getCompanyId());
		req.setTypeEnum(WaybillDistributionTypeEnum.GET);
		reqList.add(req);
		return reqList;
	}

	/**
	 * 组装拒签表信息
	 * 
	 * @param tmsSignEsDTO
	 * @return
	 */
	private SignRefuseEsDTO compRefuseParam(SignEsDTO sign,Integer groupId) {
		if (0 == sign.getRefuseNumber().intValue()) {
			return null;
		}
		// --> 生成主键id
		SignRefuseEsDTO signRefuseEsDTO = new SignRefuseEsDTO();
		signRefuseEsDTO.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_REFUSE_SIGN));
		IdCodeReqNewDTO idCodeReqNewDTO = new IdCodeReqNewDTO();
		idCodeReqNewDTO.setGroupId(groupId);
		idCodeReqNewDTO.setBizCode(TmsCodeRuleBizCode.TMS_NEW_JS);
		DataResult<String> code = idCodeService.generatedNew(idCodeReqNewDTO);
		signRefuseEsDTO.setRefuseCode(code.getResult());
		signRefuseEsDTO.setSignId(sign.getId());
		signRefuseEsDTO.setCompanyId(sign.getCompanyId());
		signRefuseEsDTO.setSignCode(sign.getCode());
		signRefuseEsDTO.setRefuseTime(new Date());
		signRefuseEsDTO.setRefusePeople(sign.getSignPeople());
		signRefuseEsDTO.setRefusePhone(sign.getInvoiceMobleNo());
		signRefuseEsDTO.setRefuseCard(sign.getIdCard());
		signRefuseEsDTO.setWaybillId(sign.getWaybillId());
		signRefuseEsDTO.setCode(sign.getCode());
		signRefuseEsDTO.setDistributionType(sign.getDeliveryType());
		signRefuseEsDTO.setRefuseTime(new Date());
		signRefuseEsDTO.setCompanyId(sign.getCompanyId());
		signRefuseEsDTO.setCreateTime(System.currentTimeMillis());
		signRefuseEsDTO.setCreateUser(sign.getCreateUser());
		signRefuseEsDTO.setRefuseType(sign.getSignStatus());
		signRefuseEsDTO.setDestOrgId(sign.getSignOrgId());
		signRefuseEsDTO.setDestOrgName(sign.getDestOrgName());
		signRefuseEsDTO.setDeliveryType(WaybillDistributionTypeEnum.GET.getType());
		signRefuseEsDTO.setDeliveryTypeName(WaybillDistributionTypeEnum.GET.getName());

		signRefuseEsDTO.setWaybillType(sign.getWaybillType());

		return signRefuseEsDTO;
	}
}
