package com.hivescm.tms.finance.server.component.pcsign.impl;

import com.alibaba.fastjson.JSON;
import com.hivescm.common.domain.DataResult;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherGoodsEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.request.SignNoticeForDispatcherReqDTO;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.DeliverySignBatchReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.UpdateSignStatusReqDTO;
import com.hivescm.tms.api.dto.es.waybill.VehicleTailAfterEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptRequirmentTypeEnum;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillDistributionTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillFeeTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillTrackStatusEnum;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.constants.TmsCodeRuleBizCode;
import com.hivescm.tms.finance.server.component.pcsign.DeliveryBatchSignService;
import com.hivescm.tms.finance.server.component.pcsign.SignReceiptService;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDetailDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignFeeDO;
import com.hivescm.tms.finance.server.feign.dispatcher.DispatcherService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.db.DbOperationService;
import com.hivescm.tms.finance.server.service.pcsign.BillTrackingService;
import com.hivescm.tms.finance.server.service.pcsign.SignBillingFlowService;
import com.hivescm.tms.finance.server.service.pcsign.SignFinanceService;
import com.hivescm.tms.finance.server.service.sign.EsSignDetailsService;
import com.hivescm.tms.finance.server.service.sign.EsSignFeeService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;
import com.hivescm.tms.intranet.gateway.api.dto.IdCode.IdCodeReqNewDTO;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.IdCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/4/3
 */
@Service
public class DeliveryBatchSignServiceImpl implements DeliveryBatchSignService{
    protected final static Logger logger= LoggerFactory.getLogger(DeliveryBatchSignService.class);

    @Autowired
    private IGeneratedIdService generatedIdService;
    @Autowired
    private IdCodeService idCodeService;

    @Autowired
    private DbOperationService dbOperationService;
    @Autowired
    private DispatcherService dispatcherService;

    @Autowired
    private WaybillService waybillService;

    @Autowired
    private EsSignService esSignService;

    @Autowired
    private EsSignDetailsService esSignDetailsService;
    @Autowired
    private EsSignFeeService esSignFeeService;
    @Autowired
    private SignFinanceService signFinanceService;
    @Autowired
    private SignBillingFlowService signBillingFlowService;
    @Autowired
    private BillTrackingService billTrackingService;
    @Autowired
    private SignReceiptService signReceiptService;

    private static Executor threadPool= Executors.newSingleThreadExecutor();

    /**
     * 我觉得 这个是垃圾需求
     * @param reqDTO
     * @return
     */
    @Override
    public Boolean batchSign(DeliverySignBatchReqDTO reqDTO) {
        //获得
        Long signOrgId = reqDTO.getOrgId();
        List<Long> detailIds = reqDTO.getDetailIds();
//        List<DispatcherDetailEsDTO> batchDispatcherDetails = dispatcherService.queryBatchDispatcherDetails(detailIds);


        List<SignDO> signDOS = new ArrayList<>();
        List<SignDetailDO> signDetailDOS = new ArrayList<>();
        List<SignFeeDO> signFeeDOS = new ArrayList<>();

        List<SignEsDTO> signEsDTOS = new ArrayList<>();
        List<SignDetailsEsDTO> signDetailsEsDTOS = new ArrayList<>();
        List<SignFeeEsDTO> signFeeEsDTOS = new ArrayList<>();

        //组装 批量签收数据
        List<TmsSignEsDTO> tmsSignEsDTOS=assembleBatchSignDOS(reqDTO,signOrgId,detailIds,signDOS,signDetailDOS,signFeeDOS,signEsDTOS,signDetailsEsDTOS,signFeeEsDTOS);

        Boolean db = saveDB(signDOS,signDetailDOS,signFeeDOS);
        Boolean es = saveEs(signEsDTOS,signDetailsEsDTOS,signFeeEsDTOS);


        if (db&&es){
            //通知其他模块 更新状态
            //通知 派车单
            //通知运单
            //回单
            //财务
            //计费
            //运单跟踪
            noticeDispatcher(signEsDTOS);
            noticeWaybill(signEsDTOS);
            //签回单
            noticeReceiptBill(tmsSignEsDTOS);

//            LongStream longStream = signEsDTOS.stream().mapToLong(SignEsDTO::getWaybillId);
            LinkedList<Long> waybillIds = new LinkedList<>();
            signEsDTOS.forEach(e->{

                Long waybillId = e.getWaybillId();
                waybillIds.add(waybillId);
                //发送计费流水
                sendBillingFlow(waybillId);

            });
            signFinanceService.sendMq(waybillIds, SignStatusEnum.SIGNED.getType());

            billtracking(signEsDTOS);

        }
        return db&&es;
    }

    /**
     * 发送计费流水
     * @param waybillId
     */
    private void sendBillingFlow(Long waybillId) {
        threadPool.execute(()->{
            List<SignEsDTO> waybillSignEsDTOS = esSignService.querySignEsByWaybillId(waybillId);

            //如果该运单没被签收过
            if (waybillSignEsDTOS.size()<2){

                Long[] longs = {waybillId};
                //计费流水
                signBillingFlowService.billingFlow( Arrays.asList(longs), SignStatusEnum.SIGNED.getType());
            }
        });
    }

    private void noticeReceiptBill(List<TmsSignEsDTO> tmsSignEsDTOS) {
        tmsSignEsDTOS.forEach(e ->{
            SignEsDTO signEsDTO = e.getSignEsDTO();
            threadPool.execute(()->{
                //sign 主表没冗余  backTypeValue -.-!
                WaybillEsDTO waybill = waybillService.queryWaybillEsDTO(signEsDTO.getWaybillId());
                if(waybill.getBackTypeValue() !=null && (ReceiptRequirmentTypeEnum.SIGN_BILL.getType()==waybill.getBackTypeValue().intValue() || ReceiptRequirmentTypeEnum.SIGN_GRASP.getType()==waybill.getBackTypeValue().intValue() || ReceiptRequirmentTypeEnum.SIGN_RECEIPT.getType()==waybill.getBackTypeValue().intValue())){
                    signReceiptService.signReceipt(e);
                }

            });
        });


    }

    /**
     * 运单跟踪信息
     * @param signEsDTOS
     */
    private void billtracking( List<SignEsDTO> signEsDTOS){
        signEsDTOS.forEach(sign ->{
            threadPool.execute(()->{
                try {
                    VehicleTailAfterEsDTO vehicleTailAfterEsDTO = new VehicleTailAfterEsDTO();
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
                } catch (Exception e) {
                    // 子线程内异常不能反馈，吃掉异常，单据失败
                    logger.error("同步运单跟踪信息失败 ", e);
                }
            });
        });
    }

    private void noticeDispatcher(List<SignEsDTO> signEsDTOS) {


        //fork join
//        ArrayList<DataResult<Boolean>> batchResult = new ArrayList<>(signEsDTOS.size());
//
//        RecursiveTask<DataResult<Boolean>> recursiveTask = new RecursiveTask<DataResult<Boolean>>() {
//            @Override
//            protected DataResult<Boolean> compute() {
//                return null;
//            }
//        };
        List<DataResult<Boolean>> results = new ArrayList<>();

        signEsDTOS.forEach(e->{
            SignNoticeForDispatcherReqDTO reqDTO = new SignNoticeForDispatcherReqDTO();

            EntityUtils.copyProperties(e,reqDTO);
            reqDTO.setSignId(e.getId());

            DataResult<Boolean> result = dispatcherService.synecDispatcherStatus(reqDTO);

            results.add(result);
        });
        logger.info("BatchSign synecDispatcherStatus {}", JSON.toJSONString(results));

    }


    private void noticeWaybill(List<SignEsDTO> signEsDTOS) {


        List<UpdateSignStatusReqDTO> reqList = new ArrayList<>();

       /* signEsDTOS.forEach(e->{
            UpdateSignStatusReqDTO statusReqDTO = EntityUtils.copyProperties(e,UpdateSignStatusReqDTO.class);
            statusReqDTO.setTypeEnum(WaybillDistributionTypeEnum.DELIVERY);
            reqList.add(statusReqDTO);
        });*/
        //批量修改运单操作需要合并运单总数
        //按照运单进行分组
        Map<Long,List<SignEsDTO>> collect = signEsDTOS.stream().collect(Collectors.groupingBy(SignEsDTO::getWaybillId));
        //循环分装数据
        for (Map.Entry<Long,List<SignEsDTO>> entry : collect.entrySet()) {
            UpdateSignStatusReqDTO statusReqDTO = new UpdateSignStatusReqDTO();
            statusReqDTO.setWaybillId(entry.getKey());;
            statusReqDTO.setTypeEnum(WaybillDistributionTypeEnum.DELIVERY);
            statusReqDTO.setCompanyId(entry.getValue().get(0).getCompanyId());
            statusReqDTO.setDispatcherDetailId(entry.getValue().get(0).getDispatcherDetailId());
            for(SignEsDTO item :entry.getValue()){
                statusReqDTO.setSignNumber(statusReqDTO.getSignNumber()+(item.getSignNumber()==null?0:item.getSignNumber()));
                statusReqDTO.setRefuseNumber(statusReqDTO.getRefuseNumber()+(item.getRefuseNumber()==null?0:item.getSignNumber()));
            }
            reqList.add(statusReqDTO);
        }
        DataResult<Boolean> result = waybillService.updateWyabillInfoFromSign(reqList);
        logger.info("BatchSign updateWyabillInfoFromSign {}",result.toString());

    }


    /**
     * 组装 批次签收 DOs
     * @param req
     * @param signOrgId
     * @param detailIds
     * @param signDOS
     * @param signDetailDOS
     * @param signFeeDOS
     * @param signEsDTOS
     * @param signDetailsEsDTOS
     * @param signFeeEsDTOS
     */
    private List<TmsSignEsDTO> assembleBatchSignDOS(DeliverySignBatchReqDTO req,Long signOrgId, List<Long> detailIds, List<SignDO> signDOS, List<SignDetailDO> signDetailDOS, List<SignFeeDO> signFeeDOS, List<SignEsDTO> signEsDTOS, List<SignDetailsEsDTO> signDetailsEsDTOS, List<SignFeeEsDTO> signFeeEsDTOS) {

        List<Long> signIds = generatedIdService.getIncrBatchUniqueId(GeneratedIdConstants.TMS_SIGN, detailIds.size());
        List<Long> signFeeIds = generatedIdService.getIncrBatchUniqueId(GeneratedIdConstants.TMS_SIGN_FEE, detailIds.size());
        List<TmsSignEsDTO> tmsSignEsDTOS=new ArrayList<>();

        for (int i = 0; i < detailIds.size(); i++) {


            Long detailId = detailIds.get(i);


            DispatcherDetailEsDTO detailEsDTO = dispatcherService.queryDispatcherDetailById(detailId);

            List<DispatcherGoodsEsDTO> dispatcherGoods = dispatcherService.getDispatcherGoodsByDetailId(detailId);

            Long signId = signIds.get(i);
            Long signFeeId = signFeeIds.get(i);


            TmsSignEsDTO tmsSignEsDTO=coordinatesSignSetObject(signId,signFeeId,detailEsDTO,req,signDOS,signDetailDOS,signFeeDOS,signEsDTOS,signDetailsEsDTOS,signFeeEsDTOS);

            tmsSignEsDTOS.add(tmsSignEsDTO);
//            SignDO signDO =new SignDO();
//            SignDetailDO signDetailDO = new SignDetailDO();
//            SignFeeDO signFeeDO = new SignFeeDO();
//            SignEsDTO signEsDTO = new SignEsDTO();
//            SignDetailsEsDTO signDetailsEsDTO = new SignDetailsEsDTO();
//            SignFeeEsDTO signFeeEsDTO = new SignFeeEsDTO();


        }


        return tmsSignEsDTOS;
    }


    /**
     *  组装 一整套  签收参数  (签收主表  签收明细表  签收费用表)
     * @param signId
     * @param signFeeId
     * @param detailEsDTO
     * @param req
     * @param signDOS
     * @param signDetailDOS
     * @param signFeeDOS
     * @param signEsDTOS
     * @param signDetailsEsDTOS
     * @param signFeeEsDTOS
     */
    private TmsSignEsDTO coordinatesSignSetObject(Long signId, Long signFeeId, DispatcherDetailEsDTO detailEsDTO, DeliverySignBatchReqDTO req, List<SignDO> signDOS, List<SignDetailDO> signDetailDOS, List<SignFeeDO> signFeeDOS, List<SignEsDTO> signEsDTOS, List<SignDetailsEsDTO> signDetailsEsDTOS, List<SignFeeEsDTO> signFeeEsDTOS) {

        List<DispatcherGoodsEsDTO> dispatcherGoods = dispatcherService.getDispatcherGoodsByDetailId(detailEsDTO.getId());
        TmsSignEsDTO tmsSignEsDTO = new TmsSignEsDTO();
        //TODO调用签收批次号
        List<Long> signDetailIds = generatedIdService.getIncrBatchUniqueId(GeneratedIdConstants.TMS_SIGN_DETAILS, dispatcherGoods.size());
        for (int i = 0; i < signDetailIds.size(); i++) {
            //组装签收明细信息
            SignDetailsEsDTO signDetail = compSignDetail(dispatcherGoods.get(i),detailEsDTO.getCompanyId(),signDetailIds.get(i),req);
            signDetail.setSignId(signId);
            SignDetailDO detailDo = EntityUtils.copyProperties(signDetail, SignDetailDO.class);

            signDetailDOS.add(detailDo);
            signDetailsEsDTOS.add(signDetail);

            tmsSignEsDTO.setSignDetailsEsDTO(signDetailsEsDTOS);
        }

        //todo  waybillService
//        List<WaybillFeeEsDTO> waybillFeeEsDTOS = waybillService.getWaybillFeeByWaybillId(detailEsDTO.getDispatcherDetailEsDTO().getWaybillId());
        //组装费用表信息
//        SignFeeEsDTO fee = compSignFee(detailEsDTO,waybillFeeEsDTOS,req,signFeeId,signId);
        List<WaybillFeeEsDTO> waybillFee = waybillService.getWaybillFeeByWaybillId(detailEsDTO.getWaybillId());

        SignFeeEsDTO fee = compSignFee(detailEsDTO,waybillFee,req,signFeeId,signId);
        tmsSignEsDTO.setSignFeeEsDTO(fee);
        SignFeeDO feedo = EntityUtils.copyProperties(fee, SignFeeDO.class);

        signFeeDOS.add(feedo);
        signFeeEsDTOS.add(fee);

        //签收主表
        SignEsDTO signEsDTO=signComp(signId,req,detailEsDTO,fee,signDOS,signEsDTOS);

        tmsSignEsDTO.setSignEsDTO(signEsDTO);

        return tmsSignEsDTO;

    }

    /**
     * 组装 签收主表 数据
     * @param signId
     * @param req
     * @param detailEsDTO
     * @param fee
     * @param signDOS
     * @param signEsDTOS
     */
    private SignEsDTO signComp(Long signId, DeliverySignBatchReqDTO req, DispatcherDetailEsDTO detailEsDTO, SignFeeEsDTO fee, List<SignDO> signDOS, List<SignEsDTO> signEsDTOS) {


        SignDO signDO = new SignDO();


        IdCodeReqNewDTO idCodeReqNewDTO = new IdCodeReqNewDTO();
		idCodeReqNewDTO.setGroupId(req.getGroupId());
		idCodeReqNewDTO.setBizCode(TmsCodeRuleBizCode.TMS_NEW_QS);
		DataResult<String> code = idCodeService.generatedNew(idCodeReqNewDTO);

        SignEsDTOBuilder signEsDTOBuilder = new SignEsDTOBuilder();
        //通过 派车单明细 组装 签收es
        signEsDTOBuilder.init().dispatcherDetail(detailEsDTO)
                .id(signId)
                .signBatchNumber(code.getResult())
                .deliverySignBatchReqDTO(req)
                .signFeeEsDTO(fee);

        SignEsDTO sign = signEsDTOBuilder.build();

//        sign.setTotalReceivable(fee.getCollectPayment().add(fee.getToPay()).add(fee.getDeliveryFee()).add(fee.getSecondDeliveryFee()).add(fee.getOtherFeeStation()));
        SignDO signdb = EntityUtils.copyProperties(sign, SignDO.class);

        signEsDTOS.add(sign);
        signDOS.add(signdb);

        return sign;

    }

    /**
     * TODO 组装签收费用信息
     * 目前来说 这个表 没什么用
     * @return
     */
    private SignFeeEsDTO compSignFee(DispatcherDetailEsDTO detailEsDTO,List<WaybillFeeEsDTO> waybillFeeEsDTOS, DeliverySignBatchReqDTO req, Long signFeeId, Long signId){
        SignFeeEsDTO fee = new SignFeeEsDTO();
        fee.setId(signFeeId);
        fee.setSignId(signId);
        fee.setCreateUser(req.getHandler());
        fee.setUpdateUser(req.getHandler());
        long now = System.currentTimeMillis();
        fee.setCreateTime(now);
        fee.setUpdateTime(now);
        fee.setCompanyId(detailEsDTO.getCompanyId().longValue());
        //TODO 批量签收  派车单明细
        Map<Integer,WaybillFeeEsDTO> waybillFee =  waybillFeeEsDTOS.stream().collect(Collectors.toMap(WaybillFeeEsDTO::getAttrId, a->a));
        // TODO费用
        WaybillFeeEsDTO collectPayment = waybillFee.get(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType());
        fee.setCollectPayment(collectPayment == null?BigDecimal.ZERO: collectPayment.getFee());


//        if (signEsDTO.getCod() != null) {
//            fee.setToPay(new BigDecimal(signEsDTO.getCod()));
//        }
//        fee.setCollectPayment(detailEsDTO.getCollectPayment());

//        WaybillFeeEsDTO collectPayment = waybillFee.get(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType());


//        fee.setToPay(detailEsDTO.getTotalFee());

        return fee;
    }
    /**
     * 组装签收明细信息
     * @return
     */
    private SignDetailsEsDTO compSignDetail(DispatcherGoodsEsDTO goodsEsDTO, Long companyId,Long id, DeliverySignBatchReqDTO req){
        SignDetailsEsDTO signDetail = new SignDetailsEsDTO();
        signDetail.setCompanyId(companyId);
        signDetail.setId(id);
        signDetail.setGoodsName(goodsEsDTO.getGoodsName());
        signDetail.setWaybillGoodsId(goodsEsDTO.getGoodsId());
        signDetail.setWaybillStockDetailId(goodsEsDTO.getWaybillStockDetailId());
//        signDetail.setSignNumber(goodsEsDTO.getStockNum());
        signDetail.setSignNumber(goodsEsDTO.getPackageNum());
        signDetail.setRefuseNumber(0);
        signDetail.setPackageNum(goodsEsDTO.getPackageNum());
//        signDetail.setUnsignedNumber(goodsEsDTO.getPackageNum()-goodsEsDTO.getStockNum());
        signDetail.setUnsignedNumber(0);
        signDetail.setPackages(null);
        signDetail.setCreateTime(System.currentTimeMillis());
        signDetail.setCreateUser(req.getHandler());
        return signDetail;
    }




    /**
     * 保存签收数据库
     * @param signDOs
     * @param signDetailDOs
     * @param signFeeDOs
     * @return
     */
    private Boolean saveDB(List<SignDO> signDOs,List<SignDetailDO> signDetailDOs,List<SignFeeDO> signFeeDOs){
        return dbOperationService.saveBatchDB(signDOs, signDetailDOs, signFeeDOs);
    }

    /**
     * 保存签收es信息
     * @param signEsDTOs
     * @param signDetailsEsDTOList
     * @param signFeeEsDTO
     * @return
     */
    private Boolean saveEs(List<SignEsDTO> signEsDTOs,List<SignDetailsEsDTO> signDetailsEsDTOList,List<SignFeeEsDTO> signFeeEsDTO){
        return esSignService.insertBatchSignEs(signEsDTOs) && esSignDetailsService.insertSignDetailsEsDTO(signDetailsEsDTOList) && esSignFeeService.insertBatchSignFeeEs(signFeeEsDTO);
    }



}
