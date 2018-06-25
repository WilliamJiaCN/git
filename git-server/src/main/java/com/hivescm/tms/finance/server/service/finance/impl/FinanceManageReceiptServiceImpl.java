package com.hivescm.tms.finance.server.service.finance.impl;


import com.google.common.collect.Lists;
import com.hivescm.common.domain.DataResult;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.common.exception.TmsBusinessException;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsRecycleEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManagePayEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceManageReceiptEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageReceiptListRespDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.enums.finance.DeliveryStatusEnum;
import com.hivescm.tms.api.enums.finance.ExpensesEnum;
import com.hivescm.tms.api.enums.finance.PayWayEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageReceiptDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageReceiptMapper;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.db.DbFinanceManagePayService;
import com.hivescm.tms.finance.server.service.finance.*;
import com.hivescm.tms.intranet.gateway.api.dto.boss.BizUnitWithFuncDetailVo;
import com.hivescm.tms.intranet.gateway.api.dto.boss.QueryByIdReqParam;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.boss.BossBizUnitApi;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FinanceManageReceiptServiceImpl implements FinanceManageReceiptService {

    @Autowired
    private DbFinanceManageReceiptService dbFinanceManageReceiptService;

    @Autowired
    private EsFinanceManageReceiptService  esFinanceManageReceiptService;
    
    @Autowired
    private EsFinanceManagePayService  esFinanceManagePayService;
    
    @Autowired
    private EsFinanceManageGoodsRecycleService  esFinanceManageGoodsRecycleService;

    @Autowired
    private IGeneratedIdService generatedIdService;

    @Autowired
    private WaybillService waybillService;
    @Autowired
    private DbFinanceManagePayService dbFinanceManagePayService;

    @Autowired
    private BossBizUnitApi bossBizUnitApi;

    @Autowired
    private FinanceManageReceiptMapper financeManageReceiptMapper;

    @Override
    public Boolean insertBatch(SaveFinanceManageReceiptReqDTO financeManageReceiptReqDTO) {
        boolean es=false;
        try{
            initEsData(financeManageReceiptReqDTO);
            es=esFinanceManageReceiptService.insertBatch(financeManageReceiptReqDTO);
            List<FinanceManageReceiptDO> financeManageReceiptDOList= initDbDate(financeManageReceiptReqDTO);
            dbFinanceManageReceiptService.insertBatch(financeManageReceiptDOList);
        }catch (Exception e){
            if(es){//回滚数据
            }
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_INSERT,e.getMessage());
        }

        return true;
    }

    /**
     * 初始化批量插入db数据
     * @param financeManageReceiptReqDTO
     * @return
     */
    private List<FinanceManageReceiptDO> initDbDate(SaveFinanceManageReceiptReqDTO financeManageReceiptReqDTO) {
        List<FinanceManageReceiptDO> financeManageReceiptDOList=Lists.newArrayList();
        financeManageReceiptReqDTO.getFinanceManageReceiptEsDTOList().forEach(financeManageReceiptEsDTO -> {
            FinanceManageReceiptDO financeManageReceiptDO = new FinanceManageReceiptDO();
            EntityUtils.copyProperties(financeManageReceiptEsDTO, financeManageReceiptDO);
            financeManageReceiptDOList.add(financeManageReceiptDO);
        });
        return financeManageReceiptDOList;
    }

    /**
     * 查询列表
     * @param financeManageReceiptListReqDTO
     * @return
     */
    @Override
    public FinanceManageReceiptListRespDTO getEsList(FinanceManageReceiptListReqDTO financeManageReceiptListReqDTO) {
        FinanceManageReceiptListRespDTO resp = new FinanceManageReceiptListRespDTO();
        List<FinanceManageReceiptEsDTO> esList = esFinanceManageReceiptService.getEsList(financeManageReceiptListReqDTO);
        Integer esListCount = esFinanceManageReceiptService.getEsListCount(financeManageReceiptListReqDTO);
        resp.setList(esList);
        resp.setTotalNum(esListCount);
        return resp;
    }

    /**
     * 查询新增收款列表
     * @param financeManageListForReceiptReqDTO
     * @return
     */
    @Override
    public List<FinanceManageReceiptEsDTO> getEsListForReceipt(FinanceManageListForReceiptReqDTO financeManageListForReceiptReqDTO) {
        return esFinanceManageReceiptService.getEsForReceiptList(financeManageListForReceiptReqDTO);
    }

    /**
     * 查询来源单号
     * @param financeReceiptCodeReqDTO
     * @return
     */
    @Override
    public List<FinanceManageReceiptEsDTO> getCodeForReceipt(FinanceReceiptCodeReqDTO financeReceiptCodeReqDTO) {
        return esFinanceManageReceiptService.getCodeForReceipt(financeReceiptCodeReqDTO);
    }

    /**
     * 根据来源单号快速添加
     * @param code
     * @return
     */
    @Override
    public List<FinanceManageReceiptEsDTO> getEsByCodeForReceipt(Long id) {
        return esFinanceManageReceiptService.getEsByCodeForReceipt(id);
    }

    @Override
    public Boolean verify(VerifyFinanceReceiptReqDTO verifyFinanceReceiptReqDTO) {
        boolean es=false;boolean db=false;
        try{
            if(CollectionUtils.isEmpty(verifyFinanceReceiptReqDTO.getVerifyFinanceReceiptEsDTOList())){
                throw new TmsBusinessException(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_VERITY,"传入参数非法");
            }
            initVerifyDeliveryStatus(verifyFinanceReceiptReqDTO);
            //更新es
            es=esFinanceManageReceiptService.updateDeliveryStatus(verifyFinanceReceiptReqDTO);
            List<FinanceManageReceiptDO> financeManageReceiptDOList=initFinanceManageReceiptDOList(verifyFinanceReceiptReqDTO);
            //更新db
            db=dbFinanceManageReceiptService.updateByPrimaryKeySelectiveBatch(financeManageReceiptDOList);

        }catch (Exception e){
            if(es){

            }
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_VERITY,e.getMessage());
        }

        return true;
    }

    @Override
    public Boolean updateAmount(ReceivableFinanceReceiptReqDTO receivableFinanceReceiptReqDTO) {
        boolean es=false;boolean db=false;
        List<ReceivableFinanceReceiptEsDTO> oldReceivableFinanceReceiptEsDTOList=Lists.newArrayList();
        try{
            if(CollectionUtils.isEmpty(receivableFinanceReceiptReqDTO.getReceivableFinanceReceiptEsDTOList())){
                throw new TmsBusinessException(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_VERITY,"传入参数非法");
            }
            List<Long> idList=initIdList(receivableFinanceReceiptReqDTO);
            oldReceivableFinanceReceiptEsDTOList=esFinanceManageReceiptService.findReceivableFinanceReceiptById(idList);
            List<FinanceManageReceiptDO> financeManageReceiptDOList=initAmount(receivableFinanceReceiptReqDTO);
            es=esFinanceManageReceiptService.updateReceivableFinanceReceipt(receivableFinanceReceiptReqDTO.getReceivableFinanceReceiptEsDTOList());
            db=dbFinanceManageReceiptService.updateByPrimaryKeySelectiveBatch(financeManageReceiptDOList);
        }catch (Exception e){
            if(es){
                esFinanceManageReceiptService.updateReceivableFinanceReceipt(oldReceivableFinanceReceiptEsDTOList);
            }
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_AMOUNT,e.getMessage());
        }
        return true;
    }

    /**
     * 获取应收Id集合
     * @param receivableFinanceReceiptReqDTO
     * @return
     */
    private List<Long> initIdList(ReceivableFinanceReceiptReqDTO receivableFinanceReceiptReqDTO) {
        List<Long> list= receivableFinanceReceiptReqDTO.getReceivableFinanceReceiptEsDTOList().stream().map(dto->dto.getId()).collect(Collectors.toList());
        return list;
    }

    private List<FinanceManageReceiptDO> initAmount(ReceivableFinanceReceiptReqDTO receivableFinanceReceiptReqDTO) {
        List<FinanceManageReceiptDO> financeManageReceiptDOList=Lists.newArrayList();
        receivableFinanceReceiptReqDTO.getReceivableFinanceReceiptEsDTOList().forEach(receivableFinanceReceiptEsDTO -> {
            FinanceManageReceiptDO financeManageReceiptDO=new FinanceManageReceiptDO();
            EntityUtils.copyProperties(receivableFinanceReceiptEsDTO,financeManageReceiptDO);
            financeManageReceiptDOList.add(financeManageReceiptDO);
        });
        return financeManageReceiptDOList;
    }

    private List<FinanceManageReceiptDO> initFinanceManageReceiptDOList(VerifyFinanceReceiptReqDTO verifyFinanceReceiptReqDTO) {
        List<FinanceManageReceiptDO> financeManageReceiptDOList=Lists.newArrayList();
        verifyFinanceReceiptReqDTO.getVerifyFinanceReceiptEsDTOList().forEach(verifyFinanceReceiptEsDTO -> {
            FinanceManageReceiptDO financeManageReceiptDO=new FinanceManageReceiptDO();
            financeManageReceiptDO.setId(verifyFinanceReceiptEsDTO.getId());
            financeManageReceiptDO.setCompanyId(verifyFinanceReceiptReqDTO.getCompanyId());
            financeManageReceiptDO.setDeliveryStatus(verifyFinanceReceiptEsDTO.getDeliveryStatus());
            financeManageReceiptDOList.add(financeManageReceiptDO);
        });
        return financeManageReceiptDOList;
    }

    private void initVerifyDeliveryStatus(VerifyFinanceReceiptReqDTO verifyFinanceReceiptReqDTO) {
        List<VerifyFinanceReceiptEsDTO> verifyFinanceReceiptEsDTOList=verifyFinanceReceiptReqDTO.getVerifyFinanceReceiptEsDTOList();
        verifyFinanceReceiptEsDTOList.forEach(verifyFinanceReceiptEsDTO -> {
            if(verifyFinanceReceiptReqDTO.getType()==1){
                verifyFinanceReceiptEsDTO.setDeliveryStatus(DeliveryStatusEnum.NO_DELIVERY.getType());
                verifyFinanceReceiptEsDTO.setCheckTime(System.currentTimeMillis());
                verifyFinanceReceiptEsDTO.setCheckUserId(verifyFinanceReceiptReqDTO.getOpUserId());
                verifyFinanceReceiptEsDTO.setCheckUserName(verifyFinanceReceiptReqDTO.getOpUserName());
            }else{
                verifyFinanceReceiptEsDTO.setDeliveryStatus(DeliveryStatusEnum.NO_CHECK.getType());
                verifyFinanceReceiptEsDTO.setCancelCheckTime(System.currentTimeMillis());
                verifyFinanceReceiptEsDTO.setCancelCheckUserId(verifyFinanceReceiptReqDTO.getOpUserId());
                verifyFinanceReceiptEsDTO.setCancelCheckUserName(verifyFinanceReceiptReqDTO.getOpUserName());
            }

        });
    }

    @Override
    public Boolean insert(WaybillEsDTO waybillEsDTO, FinanceManageReceiptEsDTO financeManageReceiptEsDTO) {
        financeManageReceiptEsDTO.setCreateTime(Instant.now().toEpochMilli());//创建时间
        financeManageReceiptEsDTO.setCreateUserId(waybillEsDTO.getCreateUser());//创建人ID
        financeManageReceiptEsDTO.setCreateUserName(waybillEsDTO.getCreateUserName());//创建人姓名
        financeManageReceiptEsDTO.setUnreceiptAmount(financeManageReceiptEsDTO.getReceiptAmount());
        financeManageReceiptEsDTO.setReceiptedAmount(BigDecimal.ZERO);
        boolean es=false;boolean db=false;
        try{
            FinanceManageReceiptDO financeManageReceiptDO=initFinanceManageReceiptDO(waybillEsDTO, financeManageReceiptEsDTO);
            //更新es
            es=esFinanceManageReceiptService.insertFinanceManageReceiptEsDTO(financeManageReceiptEsDTO);
            //更新db
            db=dbFinanceManageReceiptService.insert(financeManageReceiptDO);

        }catch (Exception e){
            if(es){
                esFinanceManageReceiptService.deleteById(financeManageReceiptEsDTO.getId());
            }
            e.printStackTrace();
        }

        return db;
    }

    @Override
    public FinanceManageReceiptEsDTO findFinanceManageReceipt(FinanceManageReceiptReqDTO financeManageReceiptReqDTO) {
        return esFinanceManageReceiptService.findFinanceManageReceipt(financeManageReceiptReqDTO);
    }

    /**
     * 初始化应收插入信息
     * @param financeManageReceiptEsDTO
     * @return
     */
    private FinanceManageReceiptDO initFinanceManageReceiptDO(WaybillEsDTO waybillEsDTO, FinanceManageReceiptEsDTO financeManageReceiptEsDTO) {
        copyProperties(waybillEsDTO,financeManageReceiptEsDTO,generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_RECEIPT));
        FinanceManageReceiptDO financeManageReceiptDO =new FinanceManageReceiptDO();
        EntityUtils.copyProperties(financeManageReceiptEsDTO,financeManageReceiptDO);
        return financeManageReceiptDO;
    }

    /**
     * 初始化批量插入Es数据
     * @param financeManageReceiptReqDTO
     */
    private void initEsData(SaveFinanceManageReceiptReqDTO financeManageReceiptReqDTO) {
        List<FinanceManageReceiptEsDTO> financeManageReceiptEsDTOList=financeManageReceiptReqDTO.getFinanceManageReceiptEsDTOList();
        if(CollectionUtils.isEmpty(financeManageReceiptEsDTOList)){
            throw new TmsBusinessException(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_RECEIPT_PARAM,"参数为空");
        }
        List<String> codeList=financeManageReceiptEsDTOList.stream().map(dto->dto.getOrderSourceCode()).collect(Collectors.toList());
        //查询运单信息
        List<WaybillEsDTO> waybillEsDTOList=waybillService.getWaybillEsDTOListByCodes(codeList,financeManageReceiptReqDTO.getCompanyId());
        Map<String,WaybillEsDTO> waybillEsDTOMap=waybillEsDTOList.stream().collect(Collectors.toMap(WaybillEsDTO::getCode,dto->dto));
        if(CollectionUtils.isEmpty(waybillEsDTOList)){
            throw new TmsBusinessException(ExceptionCodeConstants.ERROR_WAYBILL_NOT_EXIST,"查询运单不存在");
        }
        if(CollectionUtils.isNotEmpty(financeManageReceiptEsDTOList)){
            List<Long> idList = generatedIdService.getIncrBatchUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_RECEIPT, financeManageReceiptEsDTOList.size());
            for(int i=0;i<financeManageReceiptEsDTOList.size();i++){
                FinanceManageReceiptEsDTO financeManageReceiptEsDTO=financeManageReceiptEsDTOList.get(i);
                WaybillEsDTO waybillEsDTO=waybillEsDTOMap.get(financeManageReceiptEsDTO.getOrderSourceCode());
                copyProperties(waybillEsDTO,financeManageReceiptEsDTO,idList.get(i));
            }
        }
    }

    private void copyProperties(WaybillEsDTO waybillEsDTO, FinanceManageReceiptEsDTO financeManageReceiptEsDTO,Long id) {
        financeManageReceiptEsDTO.setId(id);
        financeManageReceiptEsDTO.setWaybillId(waybillEsDTO.getId());
        financeManageReceiptEsDTO.setOrderSourceCode(waybillEsDTO.getCode());
        financeManageReceiptEsDTO.setUnreceiptAmount(financeManageReceiptEsDTO.getReceiptAmount());
        financeManageReceiptEsDTO.setReceiptedAmount(new BigDecimal(0));
        financeManageReceiptEsDTO.setCompanyId(waybillEsDTO.getCompanyId().longValue());//公司ID
        //根据支付类型判断收银状态
        if(PayWayEnum.NOWPAY.getType()==financeManageReceiptEsDTO.getPayWay()){
            financeManageReceiptEsDTO.setDeliveryStatus(DeliveryStatusEnum.NO_DELIVERY.getType());//收银状态
            financeManageReceiptEsDTO.setCheckTime(waybillEsDTO.getCreateTime());
            financeManageReceiptEsDTO.setCheckUserId(waybillEsDTO.getCreateUser());
            financeManageReceiptEsDTO.setCheckUserName(waybillEsDTO.getCreateUserName() );
        }else{
            financeManageReceiptEsDTO.setDeliveryStatus(DeliveryStatusEnum.NO_CHECK.getType());//收银状态
        }
        if(PayWayEnum.CHANGESENF.getType()==financeManageReceiptEsDTO.getPayWay()) {
        	
        }else if(PayWayEnum.TOPAY.getType()==financeManageReceiptEsDTO.getPayWay()){//到付
            BizUnitWithFuncDetailVo bizUnitWithFuncDetailVo = null;
            //判断是否中转
            if(financeManageReceiptEsDTO.getIsTransit()){
                //取中转网点的
                bizUnitWithFuncDetailVo=findBizUnitWithFuncDetailVo(financeManageReceiptEsDTO.getTransitNetworkId());
                financeManageReceiptEsDTO.setPayeeName(financeManageReceiptEsDTO.getTransitCompanyName());
            }else{
                bizUnitWithFuncDetailVo=findBizUnitWithFuncDetailVo(waybillEsDTO.getDestOrgId());
                financeManageReceiptEsDTO.setPayeeName(StringUtils.isNotBlank(waybillEsDTO.getReceiptCompany())?waybillEsDTO.getReceiptCompany():waybillEsDTO.getReceiptUser());
            }
            financeManageReceiptEsDTO.setDeliveryNetworkId(bizUnitWithFuncDetailVo.getLogistics().getSettleOrgId());
            financeManageReceiptEsDTO.setDeliveryNetworkName(bizUnitWithFuncDetailVo.getLogistics().getSettleOrgName());
        }else{
        	financeManageReceiptEsDTO.setPayeeName(StringUtils.isNotBlank(waybillEsDTO.getInvoiceCompany())?waybillEsDTO.getInvoiceCompany():waybillEsDTO.getInvoiceUser());
            BizUnitWithFuncDetailVo bizUnitWithFuncDetailVo=findBizUnitWithFuncDetailVo(waybillEsDTO.getInvoiceOrgId());
            financeManageReceiptEsDTO.setDeliveryNetworkId(bizUnitWithFuncDetailVo.getLogistics().getSettleOrgId());
            financeManageReceiptEsDTO.setDeliveryNetworkName(bizUnitWithFuncDetailVo.getLogistics().getSettleOrgName());
        }

        if(null!=waybillEsDTO.getInvoiceCustomerId()){//发货方ID
            financeManageReceiptEsDTO.setInvoiceCustomerId(waybillEsDTO.getInvoiceCustomerId());
        }
        if(StringUtils.isNotBlank(waybillEsDTO.getInvoiceCompany())){//发货方名称
            financeManageReceiptEsDTO.setInvoiceCompany(waybillEsDTO.getInvoiceCompany());
        }
        if(StringUtils.isNotBlank(waybillEsDTO.getInvoiceUser())){//发货人
            financeManageReceiptEsDTO.setInvoiceUser(waybillEsDTO.getInvoiceUser());
        }
        if(null!=waybillEsDTO.getInvoiceCustomerVipId()){//发货人vipID
            financeManageReceiptEsDTO.setInvoiceCustomerVipId(waybillEsDTO.getInvoiceCustomerVipId());
        }
        if(null!=waybillEsDTO.getInvoiceTelNo()){//发货人电话
            financeManageReceiptEsDTO.setInvoiceTelNo(waybillEsDTO.getInvoiceTelNo());
        }
        if(null!=waybillEsDTO.getInvoiceMobleNo()){//发货人手机
            financeManageReceiptEsDTO.setInvoiceMobleNo(waybillEsDTO.getInvoiceMobleNo());
        }
        if(null!=waybillEsDTO.getInvoiceOrgId()){//发货网点ID
            financeManageReceiptEsDTO.setInvoiceOrgId(waybillEsDTO.getInvoiceOrgId());
        }
        if(null!=waybillEsDTO.getInvoiceOrgName()){//发货网点名称
            financeManageReceiptEsDTO.setInvoiceOrgName(waybillEsDTO.getInvoiceOrgName());
        }
        if(null!=waybillEsDTO.getReceiptCustomerId()){//收货方ID
            financeManageReceiptEsDTO.setReceiptCustomerId(waybillEsDTO.getReceiptCustomerId());
        }
        if(null!=waybillEsDTO.getReceiptCompany()){//收货方名称
            financeManageReceiptEsDTO.setReceiptCompany(waybillEsDTO.getReceiptCompany());
        }
        if(null!=waybillEsDTO.getReceiptUser()){//收货人
            financeManageReceiptEsDTO.setReceiptUser(waybillEsDTO.getReceiptUser());
        }
        if(null!=waybillEsDTO.getReceiptCustomerVipId()){//收货方vipID
            financeManageReceiptEsDTO.setReceiptCustomerVipId(waybillEsDTO.getReceiptCustomerVipId());
        }
        if(null!=waybillEsDTO.getReceiptConsignorTelNo()){//收货人电话
            financeManageReceiptEsDTO.setInvoiceTelNo(waybillEsDTO.getReceiptConsignorTelNo());
        }
        if(null!=waybillEsDTO.getReceiptConsignorMobleNo()){//收货人手机号码
            financeManageReceiptEsDTO.setReceiptConsignorMobleNo(waybillEsDTO.getReceiptConsignorMobleNo());
        }
        if(null!=waybillEsDTO.getDestId()){//目的地ID
            //financeManageReceiptEsDTO.setDestId(waybillEsDTO.getDestId());
        }
        if(null!=waybillEsDTO.getDestName()){//目的地名称
            financeManageReceiptEsDTO.setDestName(waybillEsDTO.getDestName());
        }
        if(null!=waybillEsDTO.getDestOrgId()){//目的网点ID
            financeManageReceiptEsDTO.setDestOrgId(waybillEsDTO.getDestOrgId());
        }
        if(null!=waybillEsDTO.getDestOrgName()){//目的网点ID
        	financeManageReceiptEsDTO.setDestOrgName(waybillEsDTO.getDestOrgName());
        }
        if(null!=waybillEsDTO.getRemark()){//运单备注
            financeManageReceiptEsDTO.setRemark(waybillEsDTO.getRemark());
        }
        //业务时间--生成应收的时间
        financeManageReceiptEsDTO.setBusinessTime(financeManageReceiptEsDTO.getCreateTime());
        if(null!=waybillEsDTO.getSalesmanName()){//业务员
            financeManageReceiptEsDTO.setSalesmanName(waybillEsDTO.getSalesmanName());
        }

    }

    /**
     * 通用调用BOSS业务单元接口
     * @param branchId
     * @return
     */
    private BizUnitWithFuncDetailVo findBizUnitWithFuncDetailVo(Integer branchId) {
        QueryByIdReqParam queryByIdReqParam=new QueryByIdReqParam();
        queryByIdReqParam.setId(branchId);
        DataResult<BizUnitWithFuncDetailVo> dataResult= bossBizUnitApi.queryBizUnitById(queryByIdReqParam);
        if(!dataResult.isSuccess()){
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_DEPENDENCY,"调用BOSS业务单元查询失败");
        }
        return dataResult.getResult();
    }


    @Override
	public Boolean deleteByCode(FinanceDeleteReqDTO financeDeleteReqDTO) {
		boolean db = dbFinanceManageReceiptService.deleteByCode(financeDeleteReqDTO)>0;
		boolean dbPay = dbFinanceManagePayService.deleteByCode(financeDeleteReqDTO)>0;
		boolean es = esFinanceManageReceiptService.deleteByCode(financeDeleteReqDTO);
		boolean esPay = esFinanceManagePayService.deleteByCode(financeDeleteReqDTO);
		return db&&es&&dbPay&&esPay;
	}

	@Override
	public Boolean getPayStatusByCode(FinanceDeleteReqDTO financeDeleteReqDTO) {
		List<FinanceManageReceiptEsDTO> list = esFinanceManageReceiptService.getPayStatusByCode(financeDeleteReqDTO);
		List<FinanceManageReceiptEsDTO> listnowpay = esFinanceManageReceiptService.getPayStatusByCodeNowPay(financeDeleteReqDTO);
		List<FinanceManagePayEsDTO> payList = esFinanceManagePayService.getPayStatusByCode(financeDeleteReqDTO);
		if(CollectionUtils.isNotEmpty(list)||CollectionUtils.isNotEmpty(payList)||CollectionUtils.isNotEmpty(listnowpay)) {
			return false;
		}
		return true;
	}

	@Override
	public FinanceManageReceiptEsDTO findFinanceManageReceiptById(Long id) {
		return esFinanceManageReceiptService.findFinanceManageReceipt(id);
	}

	@Override
	public Boolean getPayStatusByCodeSign(FinanceDeleteReqDTO financeDeleteReqDTO) {
		List<FinanceManageReceiptEsDTO> list = esFinanceManageReceiptService.getPayStatusByCodeSign(financeDeleteReqDTO);
		List<FinanceManageGoodsRecycleEsDTO> listrecyle =esFinanceManageGoodsRecycleService.getPayStatusByCodeSign(financeDeleteReqDTO);
		if(CollectionUtils.isNotEmpty(list)||CollectionUtils.isNotEmpty(listrecyle)) {
			return false;
		}
		return true;
	}

    @Override
    public Boolean deleteByPayway(FinanceDeleteReqDTO financeDeleteReqDTO) {
        boolean db = dbFinanceManageReceiptService.deleteByPayway(financeDeleteReqDTO);
        boolean dbPay = dbFinanceManagePayService.deleteByCode(financeDeleteReqDTO) > 0;
        boolean es = esFinanceManageReceiptService.deleteByPayway(financeDeleteReqDTO);
        boolean esPay = esFinanceManagePayService.deleteByCode(financeDeleteReqDTO);
        return db && es && dbPay && esPay;
    }

    @Override
    @ChainedTransaction(mapper = {FinanceManageReceiptMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public Boolean deleteReceiptInfo(@RouteParam("FinanceManageReceiptMapper.companyId") FinanceDeleteReqDTO financeDeleteReqDTO) {
        try {
            financeManageReceiptMapper.deleteByPayway(financeDeleteReqDTO);
            esFinanceManageReceiptService.deleteByPayway(financeDeleteReqDTO);
            return true;
        } catch (Exception e) {
            log.error("操作数据库或ES异常");
            throw e;
        }
    }

	@Override
	public Boolean updateSignStatus(FinanceReceiptStatusUpdateDTO financeReceiptStatusUpdateDTO) {
		try {
			List<Long> ids = financeReceiptStatusUpdateDTO.getIds();
			if(financeReceiptStatusUpdateDTO.getPayWay()!=null
					&&financeReceiptStatusUpdateDTO.getPayWay()==PayWayEnum.BACKPAY.getType()) {
				if(CollectionUtils.isNotEmpty(ids)) {
					List<Long> id = ids.stream().filter(c->{
						FinanceManageReceiptEsDTO dto = esFinanceManageReceiptService.findByWaybillId(c);
						if(dto!=null)
							return true;
						else 
							return false;
					}).collect(Collectors.toList());
					//TODO  @Author ke.huang 06.01 fix bug ids to id 
					if(CollectionUtils.isNotEmpty(id))
						financeReceiptStatusUpdateDTO.setIds(id);
					else
						return true;
				}else {
					return true;
				}
			}
			boolean db = financeManageReceiptMapper.updateSignStatus(financeReceiptStatusUpdateDTO)>0;
            boolean es = esFinanceManageReceiptService.updateSignStatus(financeReceiptStatusUpdateDTO);
            return db&&es;
        } catch (Exception e) {
            log.error("操作数据库或ES异常");
            throw e;
        }
	}

	@Override
	public Boolean getPayStatusByCodeTrans(FinanceDeleteReqDTO financeDeleteReqDTO) {
		List<FinanceManageReceiptEsDTO> list = esFinanceManageReceiptService.getPayStatusByCodeSign(financeDeleteReqDTO);
		List<FinanceManageGoodsRecycleEsDTO> listrecyle =esFinanceManageGoodsRecycleService.getPayStatusByCodeSign(financeDeleteReqDTO);
		List<FinanceManagePayEsDTO> listpay = esFinanceManagePayService.getPayStatusByTran(financeDeleteReqDTO);;
		if(CollectionUtils.isNotEmpty(list)||CollectionUtils.isNotEmpty(listrecyle)||CollectionUtils.isNotEmpty(listpay)) {
			return false;
		}
		return true;
	}

	@Override
	public Boolean getStatusForUpdateWaybill(FinanceForUpdateWaybill financeForUpdateWaybill) {
		FinanceDeleteReqDTO financeDeleteReqDTO = EntityUtils.copyProperties(financeForUpdateWaybill, FinanceDeleteReqDTO.class);
		Integer type = financeForUpdateWaybill.getType();
		switch(type) {
		case 1:
			List<FinanceManageReceiptEsDTO> list = esFinanceManageReceiptService.getPayStatusByCode(financeDeleteReqDTO);
			List<FinanceManageReceiptEsDTO> listnowpay = esFinanceManageReceiptService.getPayStatusByCodeNowPay(financeDeleteReqDTO);
			List<FinanceManagePayEsDTO> payList = esFinanceManagePayService.getPayStatusByCode(financeDeleteReqDTO);
			if(CollectionUtils.isNotEmpty(list)||CollectionUtils.isNotEmpty(payList)||CollectionUtils.isNotEmpty(listnowpay)) {
				return false;
			}
			return true;
		case 2:
			List<FinanceManageReceiptEsDTO> listToPay = esFinanceManageReceiptService.getPayStatusByCodeSign(financeDeleteReqDTO);
			List<FinanceManageGoodsRecycleEsDTO> recyle = esFinanceManageGoodsRecycleService.getPayStatusByCodeSign(financeDeleteReqDTO);
			if(CollectionUtils.isNotEmpty(listToPay)||CollectionUtils.isNotEmpty(recyle)) {
				return false;
			}
			return true;
		}
		return false;
	}

    @Override
    public Boolean insertBatchForExpClose(SaveFinanceManageReceiptReqDTO financeManageReceiptReqDTO) {
        List<FinanceManageReceiptEsDTO> financeManageReceiptEsDTOList = financeManageReceiptReqDTO.getFinanceManageReceiptEsDTOList();
        List<Long> idList = generatedIdService.getIncrBatchUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_RECEIPT, financeManageReceiptEsDTOList.size());
        for (int i = 0; i < financeManageReceiptEsDTOList.size(); i++) {
            FinanceManageReceiptEsDTO financeManageReceiptEsDTO = financeManageReceiptEsDTOList.get(i);
            financeManageReceiptEsDTO.setId(idList.get(i));
        }
        List<FinanceManageReceiptDO> financeManageReceiptDOList = initDbDate(financeManageReceiptReqDTO);
        boolean db = dbFinanceManageReceiptService.insertBatch(financeManageReceiptDOList) > 0;
        boolean es = esFinanceManageReceiptService.insertBatch(financeManageReceiptReqDTO);

        return db && es;
    }
}
