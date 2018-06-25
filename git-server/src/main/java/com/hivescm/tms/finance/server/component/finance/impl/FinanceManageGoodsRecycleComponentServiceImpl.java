package com.hivescm.tms.finance.server.component.finance.impl;

import com.google.common.collect.Lists;
import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsGrantEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsRecycleEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.enums.finance.*;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.component.finance.DbFinanceGoodsRecycleComponentService;
import com.hivescm.tms.finance.server.component.finance.FinanceManageGoodsRecycleComponentService;
import com.hivescm.tms.finance.server.component.pcsign.CashierConfirmationService;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsRecycleDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageCashSerialMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageGoodsGrantMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageGoodsRecycleMapper;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.finance.*;
import com.hivescm.tms.finance.server.service.finance.constant.FinanceConstant;
import com.hivescm.tms.intranet.gateway.api.dto.boss.BizUnitWithFuncDetailVo;
import com.hivescm.tms.intranet.gateway.api.dto.boss.QueryByIdReqParam;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.ReconciliationStatus;
import com.hivescm.tms.intranet.gateway.api.dto.boss.enums.Status;
import com.hivescm.tms.intranet.gateway.api.dto.boss.finance.CodeOpParam;
import com.hivescm.tms.intranet.gateway.api.dto.boss.finance.SettleBillDTO;
import com.hivescm.tms.intranet.gateway.api.dto.boss.finance.SettleDetailDTO;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.boss.BossBizUnitApi;
import com.hivescm.tms.intranet.gateway.api.feign.boss.IBossFinanceNewReceiptPayApi;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FinanceManageGoodsRecycleComponentServiceImpl implements FinanceManageGoodsRecycleComponentService {
	
	@Autowired
	private DbFinanceGoodsRecycleComponentService dbFinanceGoodsRecycleComponentService;
    @Autowired
    private EsFinanceManageCashSerialService esFinanceManageCashSerialService;
    @Autowired
    private EsFinanceManageGoodsRecycleService esFinanceManageGoodsRecycleService;
	@Autowired
	private BossBizUnitApi bossBizUnitApi;
	@Autowired
    private IGeneratedIdService generatedIdService;
    @Autowired
    private IBossFinanceNewReceiptPayApi bossFinanceNewReceiptPayApi;
	@Autowired
	private FinanceManageGoodsRecycleComponentService financeManageGoodsRecycleComponentService;

	@Autowired
	private FinanceManageCashSerialService financeManageCashSerialService;
	@Autowired
	private WaybillService waybillService;
	@Autowired
	private EsFinanceManageReceiptService esFinanceManageReceiptService;
	@Autowired
	private EsFinanceManageGoodsGrantService esFinanceManageGoodsGrantService;

	@Autowired
	private FinanceManageGoodsGrantMapper financeManageGoodsGrantMapper;

	@Autowired
	private FinanceManageGoodsRecycleMapper financeManageGoodsRecycleMapper;

	@Autowired
	private FinanceManageCashSerialMapper financeManageCashSerialMapper;
	
	@Autowired
	private CashierConfirmationService ashierConfirmationService;
	

	@Override
	public Boolean saveRecycle(FinanceSaveRecycleDTO financeSaveRecycleDTO) {
		if(financeSaveRecycleDTO.getCompanyId()==null) {
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_PAYCONFIRM, "创建收款单失败参数错误，companyId不能为null");
		}
		Boolean db = false;
		Boolean es = false;
		List<FinanceSaveReceiptIDDTO> listID = financeSaveRecycleDTO.getList();
		List<FinanceManageGoodsRecycleEsDTO> list = new ArrayList<>();
		List<FinanceManageCashSerialEsDTO> cashInsert = new ArrayList<>();
		List<FinanceManageGoodsRecycleEsDTO> recyleUpdate = new ArrayList<>();
		List<FinanceManageGoodsGrantEsDTO> grantInsert = new ArrayList<>();
		List<FinanceManageGoodsGrantEsDTO> grantUpdate = new ArrayList<>();
		List<FinanceManageGoodsGrantEsDTO> grantBack = new ArrayList<>();
		ArrayList<Long> cahsierID = new ArrayList<>();
		try {
			for(FinanceSaveReceiptIDDTO vo:listID) {
				FinanceManageGoodsRecycleEsDTO dto = esFinanceManageGoodsRecycleService.findByID(vo.getId());
				dto.setReceiptAmount(vo.getReceiptAmount());
				list.add(dto);
			}
			for(FinanceManageGoodsRecycleEsDTO esDTO:list) {
				//组装代收货款收款单实体--》调用boss接口--》更新本地数据
				SettleBillDTO settleBillDTO = setSettleBillDTO(esDTO, financeSaveRecycleDTO);
				DataResult<SettleBillDTO> info = bossFinanceNewReceiptPayApi.createPayInfo(settleBillDTO);
				FinanceManageCashSerialEsDTO createCashSerialEntity = createCashSerialEntity(esDTO, financeSaveRecycleDTO);
				if(info!=null&&info.getResult()!=null) {
					String receiptCode = info.getResult().getCode();
					createCashSerialEntity.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_CASH_SERIAL));
					createCashSerialEntity.setReceiptCode(receiptCode);
					cashInsert.add(createCashSerialEntity);
					FinanceManageGoodsRecycleEsDTO updateEntity = createUpdateEntity(esDTO, 1);
					updateEntity.setRecycleUserId(financeSaveRecycleDTO.getUserId());
					updateEntity.setRecycleUserName(financeSaveRecycleDTO.getUserName());
					updateEntity.setRecycleTime(System.currentTimeMillis());
					updateEntity.setUpdateUserId(financeSaveRecycleDTO.getUserId());
					updateEntity.setUpdateUserName(financeSaveRecycleDTO.getUserName());
					updateEntity.setUpdateTime(System.currentTimeMillis());
					recyleUpdate.add(updateEntity);
					FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO = esFinanceManageGoodsGrantService.findByWaybillId(esDTO.getWaybillId());
					if(financeManageGoodsGrantEsDTO!=null) {
						grantBack.add(financeManageGoodsGrantEsDTO);
						financeManageGoodsGrantEsDTO.setRecycleUserId(financeSaveRecycleDTO.getUserId());
						financeManageGoodsGrantEsDTO.setRecycleUserName(financeSaveRecycleDTO.getUserName());
						financeManageGoodsGrantEsDTO.setRecycleTime(System.currentTimeMillis());
						grantUpdate.add(financeManageGoodsGrantEsDTO);
					}else {
						financeManageGoodsGrantEsDTO = createGoodsGrantEntity(esDTO, financeSaveRecycleDTO);
						financeManageGoodsGrantEsDTO.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_GOODS_GRANT));
						grantInsert.add(financeManageGoodsGrantEsDTO);
					}
					cahsierID.add(esDTO.getWaybillId());
				}else {
					throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_PAYCONFIRM, info.getStatus().getStatusReason());
				}
			}
			db = saveDb(cashInsert,recyleUpdate,grantInsert);
			es = saveEs(cashInsert,recyleUpdate,grantInsert,grantUpdate);
			//更新签收的收银状态
			if(cahsierID.size()>0) {
				WayBillCashierConfirmationReqDTO cashier = new WayBillCashierConfirmationReqDTO();
				cashier.setCompanyId(financeSaveRecycleDTO.getCompanyId());
				cashier.setCashierConfirmStatus(2);
				cashier.setWaybillIds(cahsierID);
				ashierConfirmationService.updateSignCashierStatus(cashier);
			}
		} catch (Exception e) {
			e.printStackTrace();
			rollBack(cashInsert,list,grantInsert,grantBack);
			throw e;
		}
		return true;
	}
	
	private void rollBack(List<FinanceManageCashSerialEsDTO> cashInsert, List<FinanceManageGoodsRecycleEsDTO> list,
			List<FinanceManageGoodsGrantEsDTO> grantInsert, List<FinanceManageGoodsGrantEsDTO> grantBack) {
		if(CollectionUtils.isEmpty(cashInsert)) {
			return;
		}
		List<FinanceManageCashSerialDO> cashDo = cashInsert.stream().map(c->{
			return EntityUtils.copyProperties(c, FinanceManageCashSerialDO.class);}).collect(Collectors.toList());
		list.forEach(c->c.setReceiptAmount(null));
		List<FinanceManageGoodsRecycleDO> recyleDo = list.stream().map(c->{
			return EntityUtils.copyProperties(c, FinanceManageGoodsRecycleDO.class);}).collect(Collectors.toList());
		if(CollectionUtils.isNotEmpty(grantInsert)) {
			List<FinanceManageGoodsGrantDO> grantAdd = grantInsert.stream().map(c->{
				return EntityUtils.copyProperties(c, FinanceManageGoodsGrantDO.class);}).collect(Collectors.toList());
			dbFinanceGoodsRecycleComponentService.rollBatchGrant(cashDo,recyleDo,grantAdd);
		}else {
			dbFinanceGoodsRecycleComponentService.rollBatch(cashDo,recyleDo);
		}
		List<Long> ids = cashInsert.stream().map(c->{
			return c.getId();}).collect(Collectors.toList());
		esFinanceManageCashSerialService.deleteEsBatch(ids);
		esFinanceManageGoodsRecycleService.updateByBatch(list);
		List<Long> idGrant = grantInsert.stream().map(c->{
			return c.getId();}).collect(Collectors.toList());
		if(CollectionUtils.isNotEmpty(grantInsert)) {
			esFinanceManageGoodsGrantService.deleteBatch(idGrant);
		}
		if(CollectionUtils.isNotEmpty(grantBack)) {
			esFinanceManageGoodsGrantService.updateBatch(grantBack);
		}
		cashInsert.forEach(s->{
			CodeOpParam opParam = new CodeOpParam();
			opParam.setTypeSeparate(1);
			opParam.setGroupId(s.getGroupId());
			opParam.setOpUser(s.getCreateUserId());
			opParam.setOpTime(System.currentTimeMillis());
			opParam.setCode(s.getReceiptCode());
			bossFinanceNewReceiptPayApi.deleteBySource(opParam);
		});
	}

	private Boolean saveEs(List<FinanceManageCashSerialEsDTO> cashInsert,
			List<FinanceManageGoodsRecycleEsDTO> recyleUpdate, List<FinanceManageGoodsGrantEsDTO> grantInsert,
			List<FinanceManageGoodsGrantEsDTO> grantUpdate) {
		boolean grantadd = true;
		boolean grantupd = true;
		boolean cash = esFinanceManageCashSerialService.insertBatch(cashInsert);
		Boolean recyle = esFinanceManageGoodsRecycleService.updateByBatch(recyleUpdate);
		if(CollectionUtils.isNotEmpty(grantInsert)) {
			grantadd = esFinanceManageGoodsGrantService.insertBatch(grantInsert);
		}
		if(CollectionUtils.isNotEmpty(grantUpdate)) {
			grantupd = esFinanceManageGoodsGrantService.updateBatch(grantUpdate);
		}
		return cash&&recyle&&grantadd&&grantupd;
	}

	private Boolean saveDb(List<FinanceManageCashSerialEsDTO> cashInsert,
			List<FinanceManageGoodsRecycleEsDTO> recyleUpdate, List<FinanceManageGoodsGrantEsDTO> grantInsert) {
		List<FinanceManageCashSerialDO> cashDo = cashInsert.stream().map(c->{
			return EntityUtils.copyProperties(c, FinanceManageCashSerialDO.class);}).collect(Collectors.toList());
		List<FinanceManageGoodsRecycleDO> recyleDo = recyleUpdate.stream().map(c->{
			return EntityUtils.copyProperties(c, FinanceManageGoodsRecycleDO.class);}).collect(Collectors.toList());
		if(CollectionUtils.isNotEmpty(grantInsert)) {
			List<FinanceManageGoodsGrantDO> grantAdd = grantInsert.stream().map(c->{
				return EntityUtils.copyProperties(c, FinanceManageGoodsGrantDO.class);}).collect(Collectors.toList());
			return dbFinanceGoodsRecycleComponentService.updateBatchAndGrantInsert(cashDo,recyleDo,grantAdd);
		}
		return dbFinanceGoodsRecycleComponentService.updateBatch(cashDo,recyleDo);
	}


	@Override
	public Boolean cancleRecycle(FinanceCancleReceiptDTO financeCancleReceiptDTO) {
		FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO = financeManageCashSerialService.findById(financeCancleReceiptDTO.getId());
		CodeOpParam opParam = new CodeOpParam();
		opParam.setTypeSeparate(1);
		opParam.setGroupId(financeManageCashSerialEsDTO.getGroupId());
		opParam.setOpUser(financeCancleReceiptDTO.getUserId());
		opParam.setOpTime(System.currentTimeMillis());
		opParam.setCode(financeManageCashSerialEsDTO.getReceiptCode());
		Boolean result = bossFinanceNewReceiptPayApi.deleteBySource(opParam).getResult();
		if(result!=null&&result) {
			BigDecimal receiptAmount = financeManageCashSerialEsDTO.getReceiptAmount();
			//---->修改资金流水状态为取消
			FinanceManageCashSerialEsDTO updateDO = new FinanceManageCashSerialEsDTO();
			updateDO.setId(financeManageCashSerialEsDTO.getId());
			updateDO.setCompanyId(financeManageCashSerialEsDTO.getCompanyId());
			updateDO.setStatus(FlowStatusEnum.CANCEL.getType());
			//--->新增资金流水
			financeManageCashSerialEsDTO.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_CASH_SERIAL));
			financeManageCashSerialEsDTO.setReceiptAmount(receiptAmount.negate());
			financeManageCashSerialEsDTO.setStatus(FlowStatusEnum.CANCELLATION.getType());
			financeManageCashSerialEsDTO.setUpdateTime(System.currentTimeMillis());
			financeManageCashSerialEsDTO.setUpdateUserId(financeCancleReceiptDTO.getUserId());
			financeManageCashSerialEsDTO.setRemark("取消回收");
			FinanceManageGoodsRecycleEsDTO recycle = esFinanceManageGoodsRecycleService.findByID(financeManageCashSerialEsDTO.getFinanceId());
			recycle.setReceiptAmount(receiptAmount);
			FinanceManageGoodsRecycleEsDTO updateEntity = createUpdateEntity(recycle, 2);
			updateEntity.setRecycleUserId(financeCancleReceiptDTO.getUserId());
			updateEntity.setRecycleUserName(financeCancleReceiptDTO.getUserName());
			updateEntity.setRecycleTime(System.currentTimeMillis());
			updateEntity.setUpdateUserId(financeCancleReceiptDTO.getUserId());
			updateEntity.setUpdateUserName(financeCancleReceiptDTO.getUserName());
			updateEntity.setUpdateTime(System.currentTimeMillis());
			FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO = esFinanceManageGoodsGrantService.findByWaybillId(recycle.getWaybillId());
			if(RecycleStatusEnum.UNRECYCLE.getType()==updateEntity.getRecycleStatus()) {
				financeManageGoodsRecycleComponentService.cancleByRecycleDelete(financeManageCashSerialEsDTO, updateDO, updateEntity,financeManageGoodsGrantEsDTO);
			}else {
				financeManageGoodsRecycleComponentService.cancleByRecycle(financeManageCashSerialEsDTO, updateDO, updateEntity,financeManageGoodsGrantEsDTO);
			}
		}
		return true;
	}

	@Override
	public Boolean updateByRecycle(FinanceManageCashSerialEsDTO createCashSerialEntity,
			FinanceManageGoodsRecycleEsDTO record,FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO) {
		createCashSerialEntity.setCreateTime(System.currentTimeMillis());
        FinanceManageCashSerialDO cashSerial = EntityUtils.copyProperties(createCashSerialEntity,FinanceManageCashSerialDO.class);
        FinanceManageGoodsRecycleDO goodsRecycle = EntityUtils.copyProperties(record,FinanceManageGoodsRecycleDO.class);
        FinanceManageGoodsGrantDO goodsGrant = EntityUtils.copyProperties(financeManageGoodsGrantEsDTO,FinanceManageGoodsGrantDO.class);
        Boolean saveDB = dbFinanceGoodsRecycleComponentService.saveRecycleUpdateGrant(cashSerial, goodsRecycle,goodsGrant);
        boolean insertES = esFinanceManageCashSerialService.insert(createCashSerialEntity);
        Boolean updateDb = esFinanceManageGoodsRecycleService.update(record);
        boolean update = esFinanceManageGoodsGrantService.update(financeManageGoodsGrantEsDTO);
        return saveDB&&insertES&&updateDb&&update;
	}
	

	@Override
	public Boolean updateByRecycleGrantInsert(FinanceManageCashSerialEsDTO createCashSerialEntity,
			FinanceManageGoodsRecycleEsDTO updateEntity, FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO) {
		createCashSerialEntity.setCreateTime(System.currentTimeMillis());
        FinanceManageCashSerialDO cashSerial = EntityUtils.copyProperties(createCashSerialEntity,FinanceManageCashSerialDO.class);
        FinanceManageGoodsRecycleDO goodsRecycle = EntityUtils.copyProperties(updateEntity,FinanceManageGoodsRecycleDO.class);
        FinanceManageGoodsGrantDO goodsGrant = EntityUtils.copyProperties(financeManageGoodsGrantEsDTO,FinanceManageGoodsGrantDO.class);
        Boolean saveDB = dbFinanceGoodsRecycleComponentService.saveRecycle(cashSerial, goodsRecycle,goodsGrant);
        boolean insertES = esFinanceManageCashSerialService.insert(createCashSerialEntity);
        Boolean updateDb = esFinanceManageGoodsRecycleService.update(updateEntity);
        boolean insert = esFinanceManageGoodsGrantService.insert(financeManageGoodsGrantEsDTO);
        return saveDB&&insertES&&updateDb&&insert;
	}

	@Override
	public Boolean cancleByRecycle(FinanceManageCashSerialEsDTO createCashSerialEntity,
			FinanceManageCashSerialEsDTO updateDO, FinanceManageGoodsRecycleEsDTO record, FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO) {
		createCashSerialEntity.setCreateTime(System.currentTimeMillis());
		FinanceManageCashSerialDO cashSerial = EntityUtils.copyProperties(createCashSerialEntity,FinanceManageCashSerialDO.class);
		FinanceManageCashSerialDO cashSerialUpdate = EntityUtils.copyProperties(updateDO,FinanceManageCashSerialDO.class);
        FinanceManageGoodsRecycleDO goodsRecycle = EntityUtils.copyProperties(record,FinanceManageGoodsRecycleDO.class);
        FinanceManageGoodsGrantDO goodsGrant = EntityUtils.copyProperties(financeManageGoodsGrantEsDTO,FinanceManageGoodsGrantDO.class);
        Boolean saveDB = dbFinanceGoodsRecycleComponentService.cancleRecycle(cashSerial, cashSerialUpdate, goodsRecycle,goodsGrant);
        boolean insertES = esFinanceManageCashSerialService.insert(createCashSerialEntity);
        Boolean updateDb = esFinanceManageGoodsRecycleService.update(record);
        boolean updateCash = esFinanceManageCashSerialService.updateEs(updateDO);
        boolean update = esFinanceManageGoodsGrantService.update(financeManageGoodsGrantEsDTO);
        return saveDB&&insertES&&updateDb&&updateCash&&update;
	}
	

	@Override
	public Boolean cancleByRecycleDelete(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO,
			FinanceManageCashSerialEsDTO updateDO, FinanceManageGoodsRecycleEsDTO updateEntity,
			FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO) {
		financeManageCashSerialEsDTO.setCreateTime(System.currentTimeMillis());
		FinanceManageCashSerialDO cashSerial = EntityUtils.copyProperties(financeManageCashSerialEsDTO,FinanceManageCashSerialDO.class);
		FinanceManageCashSerialDO cashSerialUpdate = EntityUtils.copyProperties(updateDO,FinanceManageCashSerialDO.class);
        FinanceManageGoodsRecycleDO goodsRecycle = EntityUtils.copyProperties(updateEntity,FinanceManageGoodsRecycleDO.class);
        FinanceManageGoodsGrantDO goodsGrant = EntityUtils.copyProperties(financeManageGoodsGrantEsDTO,FinanceManageGoodsGrantDO.class);
        Boolean saveDB = dbFinanceGoodsRecycleComponentService.cancleRecycleDeleteGrant(cashSerial, cashSerialUpdate, goodsRecycle,goodsGrant);
        boolean insertES = esFinanceManageCashSerialService.insert(financeManageCashSerialEsDTO);
        Boolean updateDb = esFinanceManageGoodsRecycleService.update(updateEntity);
        boolean updateCash = esFinanceManageCashSerialService.updateEs(updateDO);
        Boolean delete = esFinanceManageGoodsGrantService.delete(financeManageGoodsGrantEsDTO.getId());
        return saveDB&&insertES&&updateDb&&updateCash&&delete;
	}

	@Override
	public Boolean financeManageRecycleRemit(FinanceManageRecycleRemitReqDTO financeManageRecycleRemitReqDTO) {
		boolean es=false;boolean db=false;
		try{
			List<FinanceManageGoodsRecycleEsDTO> financeManageGoodsRecycleEsDTOList=esFinanceManageGoodsRecycleService.findByIdList(financeManageRecycleRemitReqDTO.getIdList(),financeManageRecycleRemitReqDTO.getBranchId());
			if(null==financeManageGoodsRecycleEsDTOList){
				throw new SystemException(ExceptionCodeConstants.ERROR_FINANCE_GOODS_GRANT_REMIT,"只能对当前网点的货款回收进行汇款");
			}
			List<Long> transportIdList=financeManageGoodsRecycleEsDTOList.stream().map(FinanceManageGoodsRecycleEsDTO::getWaybillId).collect(Collectors.toList());
			List<FinanceManageGoodsGrantEsDTO> financeManageGoodsGrantEsDTOList=esFinanceManageGoodsGrantService.findByWaybillIdList(transportIdList);
			if(CollectionUtils.isNotEmpty(financeManageGoodsGrantEsDTOList)){
				initFinanceManageGoodsGrantEsDTOList(financeManageGoodsGrantEsDTOList,financeManageRecycleRemitReqDTO);
				//现金流水
				List<FinanceManageCashSerialEsDTO> financeManageCashSerialEsDTOList= Lists.newArrayList();
				initFinanceManageGoodsRecycleEsDTOList(financeManageGoodsRecycleEsDTOList,financeManageRecycleRemitReqDTO,financeManageCashSerialEsDTOList);
				es=esFinanceManageRecycleRemit(financeManageGoodsRecycleEsDTOList,financeManageGoodsGrantEsDTOList,financeManageCashSerialEsDTOList);
				db=updateManageRecycleRemit(financeManageGoodsGrantEsDTOList,financeManageGoodsRecycleEsDTOList,financeManageCashSerialEsDTOList);
			}
		}catch (Exception e){
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_GOODS_GRANT_REMIT,e.getMessage());
		}
		return db;
	}

	@Override
	@ChainedTransaction(mapper = {
			FinanceManageGoodsGrantMapper.class,
			FinanceManageGoodsRecycleMapper.class,
			FinanceManageCashSerialMapper.class
	},timeout= TransactionConstants.TIME_OUT)
	public Boolean updateManageRecycleRemit(@RouteParam("FinanceManageGoodsGrantMapper.companyId") List<FinanceManageGoodsGrantEsDTO> financeManageGoodsGrantEsDTOList, @RouteParam("FinanceManageGoodsRecycleMapper.companyId")List<FinanceManageGoodsRecycleEsDTO> financeManageGoodsRecycleEsDTOList, @RouteParam("FinanceManageCashSerialMapper.companyId")List<FinanceManageCashSerialEsDTO> financeManageCashSerialEsDTOList) {
		if(CollectionUtils.isNotEmpty(financeManageGoodsGrantEsDTOList)){
			List<FinanceManageGoodsGrantDO> financeManageGoodsGrantDOList=Lists.newArrayList();
			financeManageGoodsGrantEsDTOList.forEach(financeManageGoodsGrantEsDTO -> {
				financeManageGoodsGrantDOList.add(EntityUtils.copyProperties(financeManageGoodsGrantEsDTO,FinanceManageGoodsGrantDO.class));
			});
			financeManageGoodsGrantMapper.updateBatchPayBranchId(financeManageGoodsGrantDOList);
		}
		if(CollectionUtils.isNotEmpty(financeManageGoodsRecycleEsDTOList)){
			List<FinanceManageGoodsRecycleDO> financeManageGoodsRecycleDOList=Lists.newArrayList();
			financeManageGoodsRecycleEsDTOList.forEach(financeManageGoodsRecycleEsDTO -> {
				financeManageGoodsRecycleDOList.add(EntityUtils.copyProperties(financeManageGoodsRecycleEsDTO,FinanceManageGoodsRecycleDO.class));
			});
			financeManageGoodsRecycleMapper.batchUpdateRemitInfo(financeManageGoodsRecycleDOList);
		}
		if(CollectionUtils.isNotEmpty(financeManageCashSerialEsDTOList)){
			List<FinanceManageCashSerialDO> financeManageCashSerialDOList=Lists.newArrayList();
			financeManageCashSerialEsDTOList.forEach(financeManageCashSerialEsDTO -> {
				financeManageCashSerialDOList.add(EntityUtils.copyProperties(financeManageCashSerialEsDTO,FinanceManageCashSerialDO.class));
			});
			financeManageCashSerialMapper.insertBatch(financeManageCashSerialDOList);
		}
		return true;
	}

	private void initFinanceManageCash(List<FinanceManageGoodsRecycleEsDTO> financeManageGoodsRecycleEsDTOList) {

	}

	private boolean esFinanceManageRecycleRemit(List<FinanceManageGoodsRecycleEsDTO> financeManageGoodsRecycleEsDTOList, List<FinanceManageGoodsGrantEsDTO> financeManageGoodsGrantEsDTOList, List<FinanceManageCashSerialEsDTO> financeManageCashSerialEsDTOList) {
		esFinanceManageGoodsRecycleService.updateByBatch(financeManageGoodsRecycleEsDTOList);
		esFinanceManageGoodsGrantService.updateBatchEs(financeManageGoodsGrantEsDTOList);
		if(CollectionUtils.isNotEmpty(financeManageCashSerialEsDTOList)){
			esFinanceManageCashSerialService.insertBatch(financeManageCashSerialEsDTOList);
		}
		return true;
	}

	private void initFinanceManageGoodsGrantEsDTOList(List<FinanceManageGoodsGrantEsDTO> financeManageGoodsGrantEsDTOList, FinanceManageRecycleRemitReqDTO financeManageRecycleRemitReqDTO) {
		financeManageGoodsGrantEsDTOList.forEach(financeManageGoodsGrantEsDTO -> {
			financeManageGoodsGrantEsDTO.setPayBranchId(financeManageRecycleRemitReqDTO.getRemitBranchId());
			financeManageGoodsGrantEsDTO.setPayBranchName(financeManageRecycleRemitReqDTO.getRemitBranchName());
			financeManageGoodsGrantEsDTO.setRemitBranchId(financeManageRecycleRemitReqDTO.getBranchId());
			financeManageGoodsGrantEsDTO.setRemitBranchName(financeManageRecycleRemitReqDTO.getBranchName());
			financeManageGoodsGrantEsDTO.setRemitOutAccount(financeManageRecycleRemitReqDTO.getPayAccount());
			financeManageGoodsGrantEsDTO.setRemitInAccount(financeManageRecycleRemitReqDTO.getReceivableBankAccount());
			financeManageGoodsGrantEsDTO.setRemitRemark(financeManageRecycleRemitReqDTO.getRemark());
			financeManageGoodsGrantEsDTO.setRemitTime(financeManageRecycleRemitReqDTO.getRemitTime());
			financeManageGoodsGrantEsDTO.setRemitUserId(financeManageRecycleRemitReqDTO.getOpUserId());
			financeManageGoodsGrantEsDTO.setRemitUserName(financeManageRecycleRemitReqDTO.getOpUserName());
		});

	}

	/**
	 * 初始化数据
	 * @param financeManageGoodsRecycleEsDTOList
	 * @param financeManageRecycleRemitReqDTO
	 * @param financeManageCashSerialEsDTOList
	 */
	private void initFinanceManageGoodsRecycleEsDTOList(List<FinanceManageGoodsRecycleEsDTO> financeManageGoodsRecycleEsDTOList, FinanceManageRecycleRemitReqDTO financeManageRecycleRemitReqDTO, List<FinanceManageCashSerialEsDTO> financeManageCashSerialEsDTOList) {
		financeManageGoodsRecycleEsDTOList.forEach(financeManageGoodsRecycleEsDTO -> {
			financeManageGoodsRecycleEsDTO.setRemitStatus(RemittanceStatusEnum.REMITTANCEED.getType());
			financeManageGoodsRecycleEsDTO.setRemitStatusName(RemittanceStatusEnum.REMITTANCEED.getName());
			financeManageGoodsRecycleEsDTO.setRemitUserId(financeManageRecycleRemitReqDTO.getOpUserId());
			financeManageGoodsRecycleEsDTO.setRemitUserName(financeManageRecycleRemitReqDTO.getOpUserName());
			financeManageGoodsRecycleEsDTO.setRemitTime(System.currentTimeMillis());
			financeManageGoodsRecycleEsDTO.setRemitBranchId(financeManageRecycleRemitReqDTO.getRemitBranchId());
			financeManageGoodsRecycleEsDTO.setRemitBranchName(financeManageRecycleRemitReqDTO.getRemitBranchName());
			financeManageGoodsRecycleEsDTO.setRemitInAccount(financeManageRecycleRemitReqDTO.getReceivableBankAccount());
			financeManageGoodsRecycleEsDTO.setRemitOutAccount(financeManageRecycleRemitReqDTO.getPayAccount());
			financeManageGoodsRecycleEsDTO.setRemitRemark(financeManageRecycleRemitReqDTO.getRemark());
			FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO=new FinanceManageCashSerialEsDTO();
			financeManageCashSerialEsDTO.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGER_CASH_TRANSFER));
			financeManageCashSerialEsDTO.setFinanceId(financeManageGoodsRecycleEsDTO.getId());
			financeManageCashSerialEsDTO.setType(FinanceIdEnum.FIANCEGOODSRECYCLE.getType());
			financeManageCashSerialEsDTO.setOrdersourceId(financeManageGoodsRecycleEsDTO.getOrderSourceCode());
			financeManageCashSerialEsDTO.setSubmitBillState(TransferAccountsStatusEnum.UNTRANSFER.getType());
			financeManageCashSerialEsDTO.setSubmitBillStateName(TransferAccountsStatusEnum.UNTRANSFER.getName());
			financeManageCashSerialEsDTO.setCompanyId(financeManageRecycleRemitReqDTO.getCompanyId());
			financeManageCashSerialEsDTO.setCodType(CalculationFeeEnum.DSHKHK.getType());
			financeManageCashSerialEsDTO.setCodTypeName(CalculationFeeEnum.DSHKHK.getName());
			financeManageCashSerialEsDTO.setFeeType(ExpensesEnum.COLLECTIONOFMONEY.getDocType());
			financeManageCashSerialEsDTO.setFeeTypeName(ExpensesEnum.COLLECTIONOFMONEY.getName());
			financeManageCashSerialEsDTO.setFundAccount(financeManageRecycleRemitReqDTO.getPayAccount());//资金账户
			financeManageCashSerialEsDTO.setReceiptAmount(new BigDecimal(0));
			financeManageCashSerialEsDTO.setPayAmount(financeManageGoodsRecycleEsDTO.getDeliveryAmount());
			financeManageCashSerialEsDTO.setSurplus(new BigDecimal(0));
			financeManageCashSerialEsDTO.setReceiptPayNetworkId(financeManageRecycleRemitReqDTO.getBranchId());
			financeManageCashSerialEsDTO.setReceiptPayNetworkName(financeManageRecycleRemitReqDTO.getBranchName());
			financeManageCashSerialEsDTO.setPayeeName(financeManageRecycleRemitReqDTO.getBranchName());
			financeManageCashSerialEsDTO.setPayeeObject(financeManageRecycleRemitReqDTO.getRemitBranchName());
			financeManageCashSerialEsDTO.setSettleNetworkId(setCheckObjectId(financeManageRecycleRemitReqDTO.getBranchId()));//核算网点
			financeManageCashSerialEsDTO.setSettleNetworkName(setSettleObjectName(financeManageRecycleRemitReqDTO.getBranchId()));//核算网点名称
			financeManageCashSerialEsDTO.setStatus(FlowStatusEnum.NORMAL.getType());
			financeManageCashSerialEsDTO.setPayAccount(financeManageRecycleRemitReqDTO.getPayAccount());
			financeManageCashSerialEsDTO.setReceivableBankAccount(financeManageRecycleRemitReqDTO.getReceivableBankAccount());
			financeManageCashSerialEsDTO.setCreateTime(System.currentTimeMillis());
			financeManageCashSerialEsDTO.setCreateUserId(financeManageRecycleRemitReqDTO.getOpUserId());
			financeManageCashSerialEsDTO.setCreateUserName(financeManageRecycleRemitReqDTO.getOpUserName());
			financeManageCashSerialEsDTO.setRemark(financeManageRecycleRemitReqDTO.getRemark());
			financeManageCashSerialEsDTO.setReceiptPayTime(System.currentTimeMillis());
			financeManageCashSerialEsDTO.setCreateBillTime(System.currentTimeMillis());
			financeManageCashSerialEsDTOList.add(financeManageCashSerialEsDTO);
		});
	}


	private SettleBillDTO setSettleBillDTO(FinanceManageGoodsRecycleEsDTO esDTO,FinanceSaveRecycleDTO financeSaveRecycleDTO) {
		SettleBillDTO settleBillDTO = new SettleBillDTO();
		SettleDetailDTO detailDto = new SettleDetailDTO();
		List<SettleDetailDTO> details = new ArrayList<>();
		details.add(detailDto);
		settleBillDTO.setDetails(details);
		long time = System.currentTimeMillis();
		settleBillDTO.setTypeSeparate(1); // 类型区分(1收款;2付款)
		settleBillDTO.setGroupId(financeSaveRecycleDTO.getGroupId()); // 集团 ID
		settleBillDTO.setBillType(FinanceConstant.RECEIPT_BILL_TYPE); // 收款单类型(10001 销售收款单;10002 采购退货收款单;10003 代收货款收款单;10004 其他收款单;10005 服务收款单)")
		settleBillDTO.setSourceBillType(FinanceConstant.SOURCE_BILL_TYPE); // 来源单据类型(数据字典 BOSS030)
		settleBillDTO.setBillDate(time); // 单据日期
		settleBillDTO.setBillStatus(Status.APPROVED); // 单据状态-以保存
		settleBillDTO.setSettleOrgAccount(financeSaveRecycleDTO.getReceiveAccount()); // 结算组织账户(收款组织账号)
		settleBillDTO.setCooperationType(2); // 往来对象类型(1-供应商;2-客户;3-部门;4-人员;5-会员)")
		settleBillDTO.setCurrencyId(1);// 币种 ID (人民币为 1)
		settleBillDTO.setSettleOrgAccount(financeSaveRecycleDTO.getReceiveAccount());
		settleBillDTO.setSettlePartyAccount(financeSaveRecycleDTO.getPayAccount());
		settleBillDTO.setPushed(true);
		settleBillDTO.setPullable(false);
		settleBillDTO.setCreateUser(financeSaveRecycleDTO.getUserId());
		settleBillDTO.setCreateTime(time);
		settleBillDTO.setRemark(financeSaveRecycleDTO.getComment());// 备注
		detailDto.setGroupId(financeSaveRecycleDTO.getGroupId());
		detailDto.setSourceBillId(esDTO.getId()); // 来源单ID--运单ID
		detailDto.setSourceBillCode(esDTO.getOrderSourceCode()); // 来源单编号--运单号
		detailDto.setSourceDetailId(1L); // 来源单行号
		detailDto.setSourceTaxedAmount(esDTO.getDeliveryAmount()); // 来源单金额
		detailDto.setOriginalBillId(esDTO.getWaybillId()); // 源头单ID--应收记录ID
		detailDto.setOriginalBillCode(esDTO.getOrderSourceCode());//// 源头单编号
		detailDto.setOriginalBillType(FinanceConstant.SOURCE_BILL_TYPE); // 源头单类型
		detailDto.setOriginalDetailId(1L); // 源头单行号
		detailDto.setTotalAmmount(esDTO.getReceiptAmount());
		detailDto.setSettleType(FinanceConstant.RECEIPT_PAY_TYPE_COLLECTIONONDELIVERY_ID); //收款类型 - 服务款id
		detailDto.setSettleCharactor(FinanceConstant.RECEIVABLES_PROPERTY_RECEIVABLE);//应收、预收
		detailDto.setPayMode(String.valueOf(PayWayEnum.COLLECTIONOFGOODS.getType()));
		detailDto.setSettleMode(financeSaveRecycleDTO.getSettlementMode());
		detailDto.setRemark(financeSaveRecycleDTO.getComment());
		detailDto.setReceiveAccount(financeSaveRecycleDTO.getReceiveAccount());
		detailDto.setPayAccount(financeSaveRecycleDTO.getPayAccount());
		detailDto.setCheckStatus(ReconciliationStatus.RECONCILICATIONSUCCESS); //收款对账状态 - 对账成功
		Integer payObject = 0;//付款方、业务方、应付方
		//运单中收款方Id和收款会员Id只能有一个有值
		if(esDTO.getReceiptCustomerId() != null ){
			payObject = esDTO.getReceiptCustomerId();
		}else if(esDTO.getReceiptCustomerVipId() != null){
			payObject = esDTO.getReceiptCustomerVipId().intValue();
		}
		settleBillDTO.setSettleOrg(setSettleObjectId(esDTO.getDestOrgId()));//收款组织 - 目的网点的结算组织ID
		settleBillDTO.setSettleParty(payObject);//付款方--收货方在客商档案的客户ID 或者收货人在会员档案的会员ID
		detailDto.setAccountOrg(setCheckObjectId(esDTO.getDestOrgId()));//应收组织 - 目的网点的核算组织ID
		detailDto.setAccountParty(payObject);//应付方 - 收货方在客商档案的客户ID 或者收货人在会员档案的会员ID
		
		//新加字段
		settleBillDTO.setTotalAmmount(esDTO.getReceiptAmount());
		settleBillDTO.setSettledAmount(esDTO.getReceiptAmount());
		detailDto.setSettledAmount(esDTO.getReceiptAmount());
		return settleBillDTO;
	}
	
	private FinanceManageCashSerialEsDTO createCashSerialEntity(FinanceManageGoodsRecycleEsDTO esDTO,FinanceSaveRecycleDTO financeSaveRecycleDTO) {
		FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO = new FinanceManageCashSerialEsDTO();
		financeManageCashSerialEsDTO.setFinanceId(esDTO.getId());
		financeManageCashSerialEsDTO.setType(FinanceIdEnum.FIANCEGOODSRECYCLE.getType());
		financeManageCashSerialEsDTO.setCompanyId(financeSaveRecycleDTO.getCompanyId());
		financeManageCashSerialEsDTO.setOrdersourceId(esDTO.getOrderSourceCode());
		financeManageCashSerialEsDTO.setCodType(CalculationFeeEnum.DSHKHS.getType());
		financeManageCashSerialEsDTO.setCodTypeName(CalculationFeeEnum.DSHKHS.getName());
		financeManageCashSerialEsDTO.setFeeType(ExpensesEnum.COLLECTIONOFMONEY.getDocType());
		financeManageCashSerialEsDTO.setFeeTypeName(ExpensesEnum.COLLECTIONOFMONEY.getName());
		if(financeSaveRecycleDTO.getPayMode()!=null) {
			financeManageCashSerialEsDTO.setPaymentType(Integer.parseInt(financeSaveRecycleDTO.getPayMode()));
			financeManageCashSerialEsDTO.setPaymentTypeName(financeSaveRecycleDTO.getPayModeName());
		}
		financeManageCashSerialEsDTO.setPaymentTypeName(financeSaveRecycleDTO.getPayModeName());
		financeManageCashSerialEsDTO.setFundAccount(String.valueOf(financeSaveRecycleDTO.getReceiveAccount()));
		financeManageCashSerialEsDTO.setReceiptAmount(esDTO.getReceiptAmount());
		financeManageCashSerialEsDTO.setReceiptPayNetworkId(esDTO.getReceiveBranchId());
		financeManageCashSerialEsDTO.setReceiptPayNetworkName(esDTO.getDeliveryNetworkName());
		financeManageCashSerialEsDTO.setSettleNetworkId(setCheckObjectId(esDTO.getReceiveBranchId()));
		financeManageCashSerialEsDTO.setSettleNetworkName(setSettleObjectName(esDTO.getReceiveBranchId()));
		financeManageCashSerialEsDTO.setPayeeName(esDTO.getPayeeName());
		financeManageCashSerialEsDTO.setRemark(financeSaveRecycleDTO.getComment());
		financeManageCashSerialEsDTO.setSettlementMethod(financeSaveRecycleDTO.getSettlementMode());
		financeManageCashSerialEsDTO.setSettlementMethodName(financeSaveRecycleDTO.getSettlementModeName());
		if(financeSaveRecycleDTO.getPayChannel()!=null) {
			financeManageCashSerialEsDTO.setPayMethod(Integer.parseInt(financeSaveRecycleDTO.getPayChannel()));
			financeManageCashSerialEsDTO.setPayMethodName(financeSaveRecycleDTO.getPayChannelName());
		}
		financeManageCashSerialEsDTO.setCreateBillTime(System.currentTimeMillis());
		financeManageCashSerialEsDTO.setCreateUserId(financeSaveRecycleDTO.getUserId());
		financeManageCashSerialEsDTO.setCreateUserName(financeSaveRecycleDTO.getUserName());
		financeManageCashSerialEsDTO.setReceiptPayTime(financeSaveRecycleDTO.getCashierTime());
		financeManageCashSerialEsDTO.setGroupId(financeSaveRecycleDTO.getGroupId());
		financeManageCashSerialEsDTO.setStatus(FlowStatusEnum.NORMAL.getType());
		financeManageCashSerialEsDTO.setSubmitBillState(TransferAccountsStatusEnum.UNTRANSFER.getType());
		financeManageCashSerialEsDTO.setSubmitBillStateName(TransferAccountsStatusEnum.UNTRANSFER.getName());
		financeManageCashSerialEsDTO.setCreateTime(System.currentTimeMillis());
		financeManageCashSerialEsDTO.setReceivableBankAccount(financeSaveRecycleDTO.getReceiveAccount());
		financeManageCashSerialEsDTO.setPayAccount(financeSaveRecycleDTO.getPayAccount());
		return financeManageCashSerialEsDTO;
	}
	
	private FinanceManageGoodsGrantEsDTO createGoodsGrantEntity(FinanceManageGoodsRecycleEsDTO esDTO,FinanceSaveRecycleDTO financeSaveRecycleDTO) {
		FinanceManageGoodsGrantEsDTO dto = null;
		Long waybillId = esDTO.getWaybillId();
		WaybillEsDTO waybill = waybillService.queryWaybillEsDTO(waybillId);
		FinanceManageReceiptReqDTO financeManageReceiptReqDTO = new FinanceManageReceiptReqDTO();
		financeManageReceiptReqDTO.setWaybillId(waybillId);
		financeManageReceiptReqDTO.setPayWay(PayWayEnum.PAYMENTDUCTION.getType());
		FinanceManageReceiptEsDTO findFinanceManageReceipt = esFinanceManageReceiptService.findFinanceManageReceipt(financeManageReceiptReqDTO);
		BigDecimal goodsAmount = new BigDecimal(0);
		BigDecimal goodsAmountFee = waybill.getGoodsPaymentDeduction()==null?BigDecimal.ZERO:esDTO.getDeliveryGoodsAmount();
		BigDecimal ungoodsAmount =goodsAmountFee;
		if(findFinanceManageReceipt!=null) {
			goodsAmount = findFinanceManageReceipt.getReceiptedAmount();
			ungoodsAmount = findFinanceManageReceipt.getUnreceiptAmount();
			goodsAmountFee = findFinanceManageReceipt.getReceiptAmount();
		}
		if(waybill!=null) {
			dto = EntityUtils.copyProperties(esDTO, FinanceManageGoodsGrantEsDTO.class);
			dto.setPayBranchId(financeSaveRecycleDTO.getCurentOrgId());
			dto.setPayBranchName(financeSaveRecycleDTO.getCurentOrgName());
			dto.setSendStatus(GrantStatusEnum.UNGRANT.getType());
			dto.setSendStatusName(GrantStatusEnum.UNGRANT.getName());
			dto.setAcountStatus(ReceiptsStatusEnum.UNRECEIPTS.getType());
			dto.setAcountStatusName(ReceiptsStatusEnum.UNRECEIPTS.getName());
			dto.setSignStatus(waybill.getSignStatus());
			dto.setSignStatusName(waybill.getSignStatusName());
			dto.setReceiveName(waybill.getInvoiceCompany());
			dto.setSendAmount(new BigDecimal(0));
			dto.setUnsendAmount(esDTO.getDeliveryAmount());
			dto.setGoodsAmount(goodsAmount);
			dto.setGoodsAmountFee(goodsAmountFee);
			dto.setUngoodsAmount(ungoodsAmount);
			dto.setUngoodsFee(esDTO.getDeliveryGoodsAmount()==null?BigDecimal.ZERO:esDTO.getDeliveryGoodsAmount());
			dto.setDeliveryGoodsAmount(esDTO.getDeliveryGoodsAmount()==null?BigDecimal.ZERO:esDTO.getDeliveryGoodsAmount());
			dto.setPayFee(new BigDecimal(0));
			dto.setGoodsFee(new BigDecimal(0));
			dto.setActualAmount(new BigDecimal(0));
			dto.setConfirmBillwayStatus(ConfirmBillwayStatusEnum.UNRECEIVE.getType());
			dto.setConfirmBillwayStatusName(ConfirmBillwayStatusEnum.UNRECEIVE.getName());
			dto.setCreateUserId(financeSaveRecycleDTO.getUserId());
			dto.setCreateUserName(financeSaveRecycleDTO.getUserName());
			dto.setCreateTime(System.currentTimeMillis());
			dto.setUpdateUserId(null);
			dto.setUpdateUserName(null);
			dto.setUpdateTime(null);
			dto.setInvoiceCustomerId(waybill.getInvoiceCustomerId());
			dto.setInvoiceCustomerVipId(waybill.getInvoiceCustomerVipId());
		}
		return dto;
	}

	private FinanceManageGoodsRecycleEsDTO createUpdateEntity(FinanceManageGoodsRecycleEsDTO esDTO,int type) {
		FinanceManageGoodsRecycleEsDTO returns = EntityUtils.copyProperties(esDTO, FinanceManageGoodsRecycleEsDTO.class);
		BigDecimal currentReceiptedAmount = esDTO.getReceiptAmount()==null?new BigDecimal(0):esDTO.getReceiptAmount();
		BigDecimal unreceiptAmount = esDTO.getUnreceiptAmount()==null?new BigDecimal(0):esDTO.getUnreceiptAmount();
		BigDecimal receiptAmount = esDTO.getReceiptedAmount()==null?new BigDecimal(0):esDTO.getReceiptedAmount();
		if(type==1) {
			receiptAmount = receiptAmount.add(currentReceiptedAmount);
			unreceiptAmount = unreceiptAmount.subtract(currentReceiptedAmount);
		}else if(type==2) {
			receiptAmount = receiptAmount.subtract(currentReceiptedAmount);
			unreceiptAmount = unreceiptAmount.add(currentReceiptedAmount);
		}
		RecycleStatusEnum recycleStatus = null;
		if(receiptAmount.compareTo(BigDecimal.ZERO)<0) {
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_PAYCONFIRM, "已收金额为负数");
		}
		if(unreceiptAmount.compareTo(esDTO.getDeliveryAmount())==0) {
			recycleStatus = RecycleStatusEnum.UNRECYCLE;
		}else if(unreceiptAmount.compareTo(BigDecimal.ZERO)>0) {
			recycleStatus = RecycleStatusEnum.PARY_RECYCLE;
		}else if(unreceiptAmount.compareTo(BigDecimal.ZERO)==0) {
			recycleStatus = RecycleStatusEnum.RECYCLEED;
			
		}else {
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_PAYCONFIRM, "实付金额大于应收金额");
		}
		returns.setId(esDTO.getId());
		returns.setUnreceiptAmount(unreceiptAmount);
		returns.setReceiptedAmount(receiptAmount);
		returns.setReceiptAmount(null);
		if(recycleStatus!=null) {
			returns.setRecycleStatus(recycleStatus.getType());
			returns.setRecycleStatusName(recycleStatus.getName());
		}
		return returns;
	}

	//核算组织
	private Integer setCheckObjectId(Integer id) {
		if(id!=null) {
			QueryByIdReqParam queryByIdReqParam = new QueryByIdReqParam();
			queryByIdReqParam.setId(id);
			BizUnitWithFuncDetailVo result = bossBizUnitApi.queryBizUnitById(queryByIdReqParam).getResult();
			if(result!=null&&result.getLogistics()!=null) {
				return result.getLogistics().getAccountOrgId();
			}
		}
		return null;
	}
	
	//结算组织
	@Override
	public Integer setSettleObjectId(Integer id) {
		if(id!=null) {
			QueryByIdReqParam queryByIdReqParam = new QueryByIdReqParam();
			queryByIdReqParam.setId(id);
			BizUnitWithFuncDetailVo result = bossBizUnitApi.queryBizUnitById(queryByIdReqParam).getResult();
			if(result!=null&&result.getLogistics()!=null) {
				return result.getLogistics().getSettleOrgId();
			}
		}
		return null;
	}
	

	//结算组织名称
	private String setSettleObjectName(Integer id) {
		if(id!=null) {
			QueryByIdReqParam queryByIdReqParam = new QueryByIdReqParam();
			queryByIdReqParam.setId(id);
			BizUnitWithFuncDetailVo result = bossBizUnitApi.queryBizUnitById(queryByIdReqParam).getResult();
			if(result!=null&&result.getLogistics()!=null) {
				return result.getLogistics().getSettleOrgName();
			}
		}
		return null;
	}


}
