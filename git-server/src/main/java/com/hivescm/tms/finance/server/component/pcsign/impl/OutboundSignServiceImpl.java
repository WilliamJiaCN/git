package com.hivescm.tms.finance.server.component.pcsign.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.common.exception.TmsBusinessException;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.OutboundCancelSignReqDTO;
import com.hivescm.tms.api.dto.es.sign.component.OutboundSignReqDTO;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.constants.BusinessCodeConstants;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.finance.server.component.pcsign.OutboundSignService;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDetailDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignRefuseDO;
import com.hivescm.tms.finance.server.service.db.DbOperationService;
import com.hivescm.tms.finance.server.service.sign.EsSignDetailsService;
import com.hivescm.tms.finance.server.service.sign.EsSignRefuseService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;
import com.hivescm.tms.intranet.gateway.api.dto.IdCode.IdCodeReqDTO;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.IdCodeService;
import com.hivescm.tms.utils.ThreadLocalUtils;

/**
 * @author boqiang.deng
 * @date 2018年4月17日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Service
public class OutboundSignServiceImpl implements OutboundSignService {

	private Logger logger = LoggerFactory.getLogger(OutboundSignServiceImpl.class);

	@Autowired
	private IGeneratedIdService generatedIdService;

	@Autowired
	private DbOperationService dbOperationService;

	@Autowired
	private IdCodeService idCodeService;
	
	@Autowired
	private EsSignService esSignService;

	@Autowired
	private EsSignDetailsService esSignDetailsService;

	@Autowired
	private EsSignRefuseService esSignRefuseService;

	private ThreadLocal<List<SignEsDTO>> signEsDTOs = new ThreadLocal<>();
	private ThreadLocal<List<SignDO>> signDOs = new ThreadLocal<>();
	private ThreadLocal<List<SignDetailsEsDTO>> signDetailsEsDTOs = new ThreadLocal<>();
	private ThreadLocal<List<SignDetailDO>> signDetailDOs = new ThreadLocal<>();
	private ThreadLocal<List<SignRefuseEsDTO>> signRefuseEsDTOs = new ThreadLocal<>();
	private ThreadLocal<List<SignRefuseDO>> signRefuseDOs = new ThreadLocal<>();

	@Override
	public Boolean insert(List<OutboundSignReqDTO> outboundReq) {
		try {
			if (CollectionUtils.isEmpty(outboundReq)) {
				logger.error("外发签收保存失败,参数为空");
				throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_OUTBOUND_ADD_SIGN, "外发签收保存失败,参数为空");
			}
			// 组装参数
			compParam(outboundReq);
			Boolean db = saveDB(this.signDOs.get(), this.signDetailDOs.get(), this.signRefuseDOs.get());
			Boolean es = saveEs(this.signEsDTOs.get(), this.signDetailsEsDTOs.get(), this.signRefuseEsDTOs.get());
			return db && es;
		} catch (Exception e) {
			//TODO 回滚
			TmsBusinessException ex = ExceptionFactory.ex(ExceptionCodeConstants.ERROR_OUTBOUND_ADD_SIGN, e,
					"外发签收新增失败,outboundReq:%s", outboundReq.toString());
			logger.error(ex.getMessage(), e);
			throw ex;
		}
	}
	@Override
	public Boolean cancel(OutboundCancelSignReqDTO outboundCancelSignReqDTO) {
		try {
			// 查询签收信息
			List<SignEsDTO> signEsDTO = esSignService.querySignBySignBatchNumber(outboundCancelSignReqDTO.getSignBatchNumber());
			if(CollectionUtils.isEmpty(signEsDTO)){
				logger.error("外发签取消失败,没有相关签收信息");
				throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_OUTBOUND_CANCEL_SIGN, "外发签取消失败,没有相关签收信息");
			}
			List<Long> signIds = signEsDTO.stream().map(a->a.getId()).collect(Collectors.toList());
			// 查询拒收信息
			List<SignRefuseEsDTO> refuseSign = esSignRefuseService.queryRefuseSignBySignIds(signIds);
			List<Long> refuseSignIds = null;
			if(!CollectionUtils.isEmpty(refuseSign)){
				refuseSignIds = refuseSign.stream().map(a->a.getId()).collect(Collectors.toList());
			}
			List<SignEsDTO> signEs = new ArrayList<>();
			List<SignRefuseEsDTO> signRefuse = new ArrayList<>(); 
			compCancelParam(signIds,refuseSignIds,signEs,signRefuse,outboundCancelSignReqDTO);
			// 更新db
			Boolean db = updateDb(signIds,refuseSignIds,outboundCancelSignReqDTO.getCompanyId(),outboundCancelSignReqDTO);
			// 更新es
			Boolean es = updateEs(signEs,signRefuse);
			return db && es;
		} catch (Exception e) {
			//TODO 回滚
			TmsBusinessException ex = ExceptionFactory.ex(ExceptionCodeConstants.ERROR_OUTBOUND_CANCEL_SIGN, e,
					"外发签收取消失败,signNumber:%s", outboundCancelSignReqDTO.toString());
			logger.error(ex.getMessage(), e);
			throw ex;
		}
	}

	/**
	 * 组装参数
	 * 
	 * @param outboundReq
	 */
	private void compParam(List<OutboundSignReqDTO> outboundReq) {
		List<Long> signIds = generatedIdService.getIncrBatchUniqueId(GeneratedIdConstants.TMS_SIGN, outboundReq.size());
		for (int i = 0; i < signIds.size(); i++) {
			outboundReq.get(i).getSignEsDTO().setId(signIds.get(i));
			copmSignDetailsParam(outboundReq, outboundReq.get(i).getSignEsDTO(),
					outboundReq.get(i).getSignDetailsEsDTO());
			SignDO signdb = EntityUtils.copyProperties(outboundReq.get(i).getSignEsDTO(), SignDO.class);
			ThreadLocalUtils.isNotNull(signEsDTOs).get().add(outboundReq.get(i).getSignEsDTO());
			ThreadLocalUtils.isNotNull(signDOs).get().add(signdb);
			if (null != outboundReq.get(i).getSignRefuseEsDTO()) {
				outboundReq.get(i).getSignRefuseEsDTO().setSignId(signIds.get(i));
				outboundReq.get(i).getSignRefuseEsDTO().setSignNumber(outboundReq.get(i).getSignEsDTO().getSignNumber());
				outboundReq.get(i).getSignRefuseEsDTO().setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_REFUSE_SIGN));
				outboundReq.get(i).getSignRefuseEsDTO().setRefuseCode(idCodeService.generated(new IdCodeReqDTO(BusinessCodeConstants.REFUSE_SIGN_CODE,
								signdb.getCompanyId().toString(), null)).getResult());
				SignRefuseDO refuseDB = EntityUtils.copyProperties(outboundReq.get(i).getSignRefuseEsDTO(), SignRefuseDO.class);
				ThreadLocalUtils.isNotNull(signRefuseEsDTOs).get().add(outboundReq.get(i).getSignRefuseEsDTO());
				ThreadLocalUtils.isNotNull(signRefuseDOs).get().add(refuseDB);
			}
		}
	}

	/**
	 * 组装签收明细信息
	 * 
	 * @param outboundReq
	 * @param signEsDTO
	 */
	private void copmSignDetailsParam(List<OutboundSignReqDTO> outboundReq, SignEsDTO signEsDTO,
			List<SignDetailsEsDTO> signDetailsEsDTO) {
		if (CollectionUtils.isEmpty(outboundReq)) {
			logger.error("外发签收保存失败,签收明细为空");
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_OUTBOUND_ADD_SIGN, "外发签收保存失败,签收明细为空");
		}
		List<Long> signDetailIds = generatedIdService.getIncrBatchUniqueId(GeneratedIdConstants.TMS_SIGN_DETAILS,
				signDetailsEsDTO.size());
		for (int i = 0; i < signDetailIds.size(); i++) {
			signDetailsEsDTO.get(i).setId(signDetailIds.get(i));
			signDetailsEsDTO.get(i).setSignId(signEsDTO.getId());
			signDetailsEsDTO.get(i).setSignNumber(signEsDTO.getSignNumber());
			SignDetailDO detailDo = EntityUtils.copyProperties(signDetailsEsDTO.get(i), SignDetailDO.class);
			ThreadLocalUtils.isNotNull(signDetailDOs).get().add(detailDo);
		}
		ThreadLocalUtils.isNotNull(signDetailsEsDTOs).get().addAll(signDetailsEsDTO);
	}
	/**
	 * 保存签收数据库
	 * @param signDO
	 * @param signDetailDOs
	 * @param signFeeDO
	 * @return
	 */
	private Boolean saveDB(List<SignDO> signDOs, List<SignDetailDO> signDetailDOs, List<SignRefuseDO> signRefuseDOs) {
		return dbOperationService.saveOutboundBatchDB(signDOs, signDetailDOs, signRefuseDOs);
	}

	/**
	 * 保存签收es信息
	 * @param signEsDTOs
	 * @param signDetailsEsDTOList
	 * @param signFeeEsDTO
	 * @return
	 */
	private Boolean saveEs(List<SignEsDTO> signEsDTOs, List<SignDetailsEsDTO> signDetailsEsDTOList,
			List<SignRefuseEsDTO> signRefuseEsDTO) {
		Boolean signFlag = esSignService.insertBatchSignEs(signEsDTOs);
		Boolean signDetails = esSignDetailsService.insertSignDetailsEsDTO(signDetailsEsDTOList);
		Boolean signrefuse = true;
		if(!CollectionUtils.isEmpty(signRefuseEsDTO)){
			signrefuse  = esSignRefuseService.insertBatchSignRefuseEsDTO(signRefuseEsDTO);
		}
		return signFlag && signDetails && signrefuse;
	}
	/**
	 * 组装参数
	 * @param signIds
	 * @param signRefuseIds
	 * @param signEs
	 * @param signRefuse
	 */
	private void compCancelParam(List<Long> signIds,List<Long> signRefuseIds,List<SignEsDTO> signEs,List<SignRefuseEsDTO> signRefuse,OutboundCancelSignReqDTO outboundCancelSignReqDTO){
		if(!CollectionUtils.isEmpty(signRefuseIds)){
			signRefuseIds.forEach(a->{
				SignRefuseEsDTO signRefuseEsDTO = new SignRefuseEsDTO();
				signRefuseEsDTO.setId(a);
				signRefuseEsDTO.setRefuseType(SignStatusEnum.CANCEL_SIGN.getType());
				signRefuseEsDTO.setUpdateTime(System.currentTimeMillis());
				signRefuseEsDTO.setUpdateUser(outboundCancelSignReqDTO.getUpdateUser());
				signRefuse.add(signRefuseEsDTO);
			});
		}
		if(!CollectionUtils.isEmpty(signIds)){
			signRefuseIds.forEach(a->{
				SignEsDTO signEsDTO = new SignEsDTO();
				signEsDTO.setId(a);
				signEsDTO.setSignStatus(SignStatusEnum.CANCEL_SIGN.getType());
				signEsDTO.setUpdateTime(System.currentTimeMillis());
				signEsDTO.setUpdateUser(outboundCancelSignReqDTO.getUpdateUser());
				signEs.add(signEsDTO);
			});
		}
	}
	/**
	 * 更新db
	 * @param signIds
	 * @param refuseSignIds
	 * @param companyId
	 * @return
	 */
	private Boolean updateDb(List<Long> signIds,List<Long> refuseSignIds,Long companyId,OutboundCancelSignReqDTO outboundCancelSignReqDTO){
		SignDO signDO = new SignDO();
		signDO.setCompanyId(companyId);
		signDO.setSignStatus(SignStatusEnum.CANCEL_SIGN.getType());
		signDO.setUpdateTime(System.currentTimeMillis());
		signDO.setUpdateUser(outboundCancelSignReqDTO.getUpdateUser());
		SignRefuseDO signRefuseDO = new SignRefuseDO();
		signRefuseDO.setCompanyId(companyId);
		signRefuseDO.setRefuseType(SignStatusEnum.CANCEL_SIGN.getType());
		signRefuseDO.setUpdateTime(System.currentTimeMillis());
		signRefuseDO.setUpdateUser(outboundCancelSignReqDTO.getUpdateUser());
		return dbOperationService.updateBatchSignAndRefuseSignStatusBySignId(signDO,signRefuseDO,signIds,refuseSignIds);
	}
	/**
	 * 更新es
	 * @param signEs
	 * @param signRefuse
	 * @return
	 */
	private Boolean updateEs(List<SignEsDTO> signEs,List<SignRefuseEsDTO> signRefuse){
		Boolean flagsign = false,flagRefuseSign = false; 
		if(!CollectionUtils.isEmpty(signEs)){
			flagsign = esSignService.updateSignEsBatchById(signEs);
		}
		if(!CollectionUtils.isEmpty(signRefuse)){
			flagRefuseSign = esSignRefuseService.updateBatchSignRefuseEsDTO(signRefuse);
		}else{
			flagRefuseSign = true;
		}
		return flagsign && flagRefuseSign;
	}

}
