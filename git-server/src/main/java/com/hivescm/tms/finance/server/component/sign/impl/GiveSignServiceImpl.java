package com.hivescm.tms.finance.server.component.sign.impl;

import com.alibaba.fastjson.JSON;
import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.bossfreight.BillingRecordReq;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherEsDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceReceiptCreateDTO;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.SignCreateDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsGoodsDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignForDetailsReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignBillEsRespDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillGoodsEsDTO;
import com.hivescm.tms.api.dto.es.waybill.request.SyncWaybillStatusToOmsReq;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherDetailStatusEnum;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherStatusEnum;
import com.hivescm.tms.api.enums.biz.finance.SettlementModeEnum;
import com.hivescm.tms.api.enums.biz.sign.SignGoodsTypeEnum;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillFeeTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillStatusEnum;
import com.hivescm.tms.api.enums.capacity.order.OrderTypeEnum;
import com.hivescm.tms.constants.BusinessCodeConstants;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.finance.server.component.finance.FinanceReceiptService;
import com.hivescm.tms.finance.server.component.sign.GiveSignService;
import com.hivescm.tms.finance.server.component.sign.GiveSignServiceForRPC;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDetailDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignFeeDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignRefuseDO;
import com.hivescm.tms.finance.server.dao.mapper.sign.SignDetailDOMapper;
import com.hivescm.tms.finance.server.dao.mapper.sign.SignFeeMapper;
import com.hivescm.tms.finance.server.dao.mapper.sign.SignMapper;
import com.hivescm.tms.finance.server.dao.mapper.sign.SignRefuseDOMapper;
import com.hivescm.tms.finance.server.feign.dispatcher.DispatcherService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.sign.EsSignRefuseDetailService;
import com.hivescm.tms.finance.server.service.sign.EsSignRefuseService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;
import com.hivescm.tms.intranet.gateway.api.dto.IdCode.IdCodeReqDTO;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.IdCodeService;
import com.mogujie.distributed.transction.DynamicTransctionManagerFactory;
import com.mogujie.trade.utils.TransactionManagerUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 送货签收
 * @author 杨彭伟
 * @date 2018-01-05 13:59
 */
@Slf4j
@Service
public class GiveSignServiceImpl implements GiveSignService {
    @Autowired
    private IGeneratedIdService generatedIdService;
    @Autowired
    private IdCodeService idCodeService;
    @Autowired
    private DynamicTransctionManagerFactory dtmFactory;
    @Autowired
    private EsSignService esSignService;
    @Autowired
    private EsSignRefuseService esSignRefuseService;
    @Autowired
    private EsSignRefuseDetailService esSignRefuseDetailService;
    @Autowired
    private DispatcherService dispatcherService;
    @Autowired
    private WaybillService waybillService;
    @Autowired
    private GiveSignServiceForRPC giveSignServiceForRPC;
    @Autowired
    private FinanceReceiptService financeReceiptService;

    @Autowired
    private SignMapper signMapper;
    @Autowired
    private SignDetailDOMapper signDetailDOMapper;
    @Autowired
    private SignFeeMapper signFeeMapper;
    @Autowired
    private SignRefuseDOMapper signRefuseDOMapper;

    @Override
    public SignBillEsRespDTO insertSign(SignForDetailsReqDTO signForDetailsReqDTO) {
//        Boolean isSubmit = idempotencyService.manualIdempotency(IdempotencyConstants.BIZ_TMS_SIGN_INSERT,
//                signForDetailsReqDTO.getWaybillId());
//        if (!isSubmit) {
//            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "请勿重复提交");
//        }
        if (!checkQRCodePayed(signForDetailsReqDTO)) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "未支付，不可签收");
        }
        SignCreateDTO signCreateDTO = new SignCreateDTO();
        // TODO 查询运单、查询运单货物明细、查询运单费用、查询派车单、查询派车单明细、拒收单明细
        getData(signForDetailsReqDTO, signCreateDTO);

        // TODO 构建签收单、签收单货物明细、签收费用
        setData(signForDetailsReqDTO, signCreateDTO);

        setRefuseData(signCreateDTO, signCreateDTO.getSignEsDTO(), signCreateDTO.getSignRefuseEsDTO());

        TransactionManagerUtils.TransactionProxy transaction = this.getTransaction(signForDetailsReqDTO.getCompanyId());
        try {


            //更新数据库
            //同时会
            //        //  生成收款记录
            //        //  通知给OMS
            //        //  计算手续费
            //        //  更新运单信息
            //        //  通知给BOSS
            Long signId = insertDB(transaction, signForDetailsReqDTO, signCreateDTO.getSignEsDTO(),
                    signCreateDTO.getSignDetailsEsDTOList(), signCreateDTO.getSignFeeEsDTO(),
                    signCreateDTO.getWaybillEsDTO(), signCreateDTO.getUpdateDispatcherDetailEsDTO(),
                    signCreateDTO.getNewDispatcherEsDTO(), signCreateDTO.getNewSignRefuseEsDTO(),
                    signCreateDTO.getUpdateDispacherFlag(), signCreateDTO.getDispatcherDetailEsDTO());
            signCreateDTO.setSignId(signId);

            insertES(signForDetailsReqDTO, signCreateDTO);
            updateDispatcher(signCreateDTO);
            return buildResponseData(signForDetailsReqDTO, signCreateDTO);
        } catch (SystemException e) {
            transaction.rollback();
            rollBackESData(signCreateDTO);
            log.error("生成签收单失败，签收单={}，签收明细={}，签收费用={}", signCreateDTO.getSignEsDTO(),
                    signCreateDTO.getSignDetailsEsDTOList(), signCreateDTO.getSignFeeEsDTO(), e);
            throw new SystemException(e.getErrorCode(), e.getErrorReason());
        } finally {
//            idempotencyService.deleteIdempotency(IdempotencyConstants.BIZ_TMS_SIGN_INSERT,
//                    signForDetailsReqDTO.getWaybillId());
        }
    }

    /**
     * 设置拒收单修改信息
     * @param signCreateDTO
     * @param signEsDTO
     * @param signRefuseEsDTO 原拒收单
     */
    private void setRefuseData(SignCreateDTO signCreateDTO, SignEsDTO signEsDTO, SignRefuseEsDTO signRefuseEsDTO) {
        if (signRefuseEsDTO != null) {
            SignRefuseEsDTO newSignRefuseEsDTO = new SignRefuseEsDTO();
            newSignRefuseEsDTO.setId(signRefuseEsDTO.getId());
            newSignRefuseEsDTO.setSignId(signEsDTO.getId());
            newSignRefuseEsDTO.setCompanyId(signEsDTO.getCompanyId());
            newSignRefuseEsDTO.setSignCode(signEsDTO.getSignBatchNumber());
            signCreateDTO.setNewSignRefuseEsDTO(newSignRefuseEsDTO);
        }
    }

    private SignBillEsRespDTO buildResponseData(SignForDetailsReqDTO signForDetailsReqDTO, SignCreateDTO signCreateDTO) {
        SignEsDTO signEsDTO = signCreateDTO.getSignEsDTO();
        SignFeeEsDTO signFeeEsDTO = signCreateDTO.getSignFeeEsDTO();
        SignBillEsRespDTO sber = new SignBillEsRespDTO();
        // 签收类型为全部签收
        if (signForDetailsReqDTO.getSignType() == SignStatusEnum.SIGNED.getType()) {

            // 有金额
            if (signFeeEsDTO.getCollectPayment() != null) {
                // 现金付款
                if (signFeeEsDTO.getSettlementMethod() == 1) {
                    sber.setFlag(11);
                } else {
                    // 扫码付款
                    sber.setFlag(10);
                }
            } else {
                // 无金额
                sber.setFlag(12);
            }

            // 签收类型为全部拒签
        } else if (signForDetailsReqDTO.getSignType() == SignStatusEnum.REFUSE_SIGN.getType()) {

            sber.setFlag(30);
            SignRefuseEsDTO signRefuseEsDTO = signCreateDTO.getSignRefuseEsDTO();
            if (signRefuseEsDTO != null) {
                sber.setRefuseWaybillId(signRefuseEsDTO.getRefuseWaybillId());
            }
        } else if (signForDetailsReqDTO.getSignType() == SignStatusEnum.PARTIAL_SIGN.getType()) {
            SignRefuseEsDTO signRefuseEsDTO = signCreateDTO.getSignRefuseEsDTO();
            if (signRefuseEsDTO != null) {
                sber.setRefuseWaybillId(signRefuseEsDTO.getRefuseWaybillId());
            }
            // 签收类型为部分签收
            // 已装车
            if (signForDetailsReqDTO.getLoaded() != null) {
                // 有金额
                if (signFeeEsDTO.getCollectPayment() != null) {
                    sber.setLoaded(signForDetailsReqDTO.getLoaded() == true);
                    sber.setFlag(22);
                } else {
                    // 无金额
                    sber.setFlag(23);
                }
                // 未装车
            } else {
                // 有金额
                if (signFeeEsDTO.getCollectPayment() != null) {
                    sber.setLoaded(signForDetailsReqDTO.getLoaded());
                    sber.setFlag(20);
                } else {
                    // 无金额
                    sber.setFlag(21);
                }
            }

        }
        sber.setSignId(signEsDTO.getId());
        sber.setSignType(signForDetailsReqDTO.getSignType());
        sber.setSettlementMethod(signForDetailsReqDTO.getSettlementMethod());
        sber.setWayBillId(signEsDTO.getWaybillId());
        sber.setSignCode(signEsDTO.getSignBatchNumber());
        sber.setLoaded(signForDetailsReqDTO.getLoaded());
        return sber;
    }


    private Long insertDB(TransactionManagerUtils.TransactionProxy transaction, SignForDetailsReqDTO signForDetailsReqDTO,
                          SignEsDTO signEsDTO, List<SignDetailsEsDTO> signDetailsEsDTOList, SignFeeEsDTO signFeeEsDTO,
                          WaybillEsDTO waybillEsDTO, DispatcherDetailEsDTO updateDispatcherDetailEsDTO,
                          DispatcherEsDTO newDispatcherEsDTO, SignRefuseEsDTO newSignRefuseEsDTO, Boolean updateDispatcher,
                          DispatcherDetailEsDTO dispatcherDetailEsDTO) {
        Long companyId = signForDetailsReqDTO.getCompanyId();
        try {
            SignDO signDO = EntityUtils.copyProperties(signEsDTO, SignDO.class);
            int i = signMapper.insertSelective(signDO);
            if (i != 1) {
                log.error("sign db-save fail! json:{} ,", JSON.toJSONString(signDO));

                throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收单DB 保存失败");
            }

            List<SignDetailDO> detailDOList = null;
            try {
                detailDOList = EntityUtils.doBatchClone(signDetailsEsDTOList, SignDetailDO.class);
            } catch (Exception e) {
                throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, e.getMessage());
            }
            if (CollectionUtils.isNotEmpty(detailDOList)) {
                int j = signDetailDOMapper.insertBatch(companyId, detailDOList);


                if (j < detailDOList.size()) {
                    log.error("sign-detail db-save fail! ,company: {} ,json:{} ,",companyId, JSON.toJSONString(detailDOList));

                    throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收明细DB 保存失败, save: "+j +" details: "+detailDOList.size());
                }
            }

            SignFeeDO signFeeDO = EntityUtils.copyProperties(signFeeEsDTO, SignFeeDO.class);
            int k = signFeeMapper.insertSelective(signFeeDO);
            if (k != 1) {

                log.error("sign-fee db-save fail! ,json:{} ,", JSON.toJSONString(signFeeDO));

                throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收费用DB 保存失败");
            }
            if (signForDetailsReqDTO.getSignType() != 1) {
                SignRefuseDO signRefuseDO = new SignRefuseDO();
                EntityUtils.copyProperties(newSignRefuseEsDTO, signRefuseDO);
                signRefuseDOMapper.updateByPrimaryKeySelective(signRefuseDO);
            }

//            DispatcherDetailDO dispatcherDetailDO = EntityUtils.copyProperties(updateDispatcherDetailEsDTO, DispatcherDetailDO.class);
//            dispatcherDetailMapper.updateByPrimaryKeySelective(dispatcherDetailDO);
//
//            if (updateDispatcher) {
//                DispatcherDO dispatcherDO = EntityUtils.copyProperties(newDispatcherEsDTO, DispatcherDO.class);
//                dispatcherMapper.updateByPrimaryKeySelective(dispatcherDO);
//            }
            // TODO 生成收款记录
            saveReceiptRecord(signForDetailsReqDTO, waybillEsDTO, signEsDTO);
            // TODO 通知给OMS
            giveSignServiceForRPC.sendResultToOMS(signFeeEsDTO, signEsDTO, signForDetailsReqDTO, waybillEsDTO);
            // TODO 计算手续费
            BillingRecordReq billingRecordReq = giveSignServiceForRPC.getDeliveryCharge(waybillEsDTO, signEsDTO.getTotalReceivable());
            BigDecimal deliveryCharge = billingRecordReq.getDeliveryCharge();
            // TODO 更新运单信息
            updateWayBillStatusById(signForDetailsReqDTO, signEsDTO, deliveryCharge, dispatcherDetailEsDTO);
            // TODO 通知给BOSS
            billingRecordReq.setStatus(WaybillStatusEnum.SIGNED.getType());
            billingRecordReq.setSignTime(signEsDTO.getSignTime());
            giveSignServiceForRPC.sendWaybillInfoToBoss(billingRecordReq);
            transaction.commit();
            return signEsDTO.getId();
        } catch (SystemException e) {
            transaction.rollback();
            log.error("生成签收单失败，签收单={}，签收明细={}，签收费用={}", signEsDTO, signDetailsEsDTOList, signFeeEsDTO, e);
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, e.getErrorReason());
        } catch (Exception e) {
            transaction.rollback();
            log.error("生成签收单失败，签收单={}，签收明细={}，签收费用={}", signEsDTO, signDetailsEsDTOList, signFeeEsDTO, e);
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "生成签收单失败");
        }
    }

    private void insertES(SignForDetailsReqDTO signForDetailsReqDTO, SignCreateDTO signCreateDTO) {
        Boolean updateDispatcher = signCreateDTO.getUpdateDispacherFlag();
        SignEsDTO signEsDTO = signCreateDTO.getSignEsDTO();
        List<SignDetailsEsDTO> signDetailsEsDTOList = signCreateDTO.getSignDetailsEsDTOList();
        SignFeeEsDTO signFeeEsDTO = signCreateDTO.getSignFeeEsDTO();
        DispatcherDetailEsDTO updateDispatcherDetailEsDTO = signCreateDTO.getUpdateDispatcherDetailEsDTO();
        DispatcherEsDTO newDispatcherEsDTO = signCreateDTO.getNewDispatcherEsDTO();
        SignRefuseEsDTO newSignRefuseEsDTO = signCreateDTO.getNewSignRefuseEsDTO();

        if (!esSignService.insertSignEsDTO(signEsDTO)) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收单ES 保存失败");
        }
        if (CollectionUtils.isNotEmpty(signDetailsEsDTOList)) {
            if (!esSignService.insertSignDetailsEsDTO(signDetailsEsDTOList)) {
                throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收单ES 保存失败");
            }
        }
        if (!esSignService.insertSignFeeEsDTO(signFeeEsDTO)) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收单ES 保存失败");
        }
        if (signForDetailsReqDTO.getSignType() != 1) {
            Boolean refuseSign = esSignService.updateRefuseSign(newSignRefuseEsDTO);
            if (!refuseSign) {
                throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收单ES 保存失败");
            }
        }
    }

    private Boolean updateDispatcherDetailById(DispatcherDetailEsDTO updateDispatcherDetailEsDTO) {
        try {
            return dispatcherService.updateDispatcherDetailById(updateDispatcherDetailEsDTO);
        } catch (Exception e) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收单ES 保存失败，原因：更新派车单明细失败");
        }
    }

    private Boolean updateDispatcherById(DispatcherEsDTO newDispatcherEsDTO) {
        try {
            return dispatcherService.updateDispatcherById(newDispatcherEsDTO);
        } catch (Exception e) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收单ES 保存失败，原因：更新派车单主表失败");
        }
    }

    private void updateDispatcher(SignCreateDTO signCreateDTO) {
        DispatcherDetailEsDTO updateDispatcherDetailEsDTO = signCreateDTO.getUpdateDispatcherDetailEsDTO();
        DispatcherEsDTO newDispatcherEsDTO = signCreateDTO.getNewDispatcherEsDTO();
        Boolean updateDispatcher = signCreateDTO.getUpdateDispacherFlag();
        if(!updateDispatcherDetailById(updateDispatcherDetailEsDTO)) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收单ES 保存失败，原因：更新派车单明细失败");
        }
        if (updateDispatcher && !updateDispatcherById(newDispatcherEsDTO)) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收单ES 保存失败，原因：更新派车单主表失败");
        }
    }

    private void rollBackESData(SignCreateDTO signCreateDTO) {
        log.error("sign es rollback {}", signCreateDTO);
        Long signId = signCreateDTO.getSignId();
        if (signId == null) {
            return;
        }
        try {
            esSignService.deleteSignEsDTOById(signId);
            esSignService.deleteSignDetailsEsDTOById(signId);
            esSignService.deleteSignFeeEsDTOById(signId);
//            esDispatcherService.updateDispatcherEsDTOAll(signCreateDTO.getDispatcherEsDTO());
//            esDispatcherDetailService.updateDispatcherDetailEsDTOAll(signCreateDTO.getDispatcherDetailEsDTO());
//            esSignService.updateWaybillEsDTOAll(signCreateDTO.getWaybillEsDTO());
        } catch (Exception e) {
            e.getLocalizedMessage();
        }
    }

    //codereview TODO 检查单据是否存在尽量使用count接口
    private void getData(SignForDetailsReqDTO signForDetailsReqDTO, SignCreateDTO signCreateDTO) {
        Long waybillId = signForDetailsReqDTO.getWaybillId();
        // 查询运单
        WaybillEsDTO waybillEsDTO = waybillService.queryWaybillEsDTO(waybillId);
        signCreateDTO.setWaybillEsDTO(waybillEsDTO);
        if (waybillEsDTO == null) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "运单不存在");
        }

        if (waybillEsDTO.getStatus().equals(WaybillStatusEnum.HANDOVERCANCEL.getType())||
                waybillEsDTO.getStatus().equals(WaybillStatusEnum.NOTHANDOVERCANCEL.getType())) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "运单已取消，不可签收");
        }

        // 查询签收单是否已经生成
        SignEsDTO querySignEsDTO = esSignService.querySignEsDTO(waybillId);
        if (querySignEsDTO != null) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "已生成签收单，不能重复生成");
        }

        SignRefuseEsDTO signRefuseEsDTOFlag = esSignRefuseService.getSignRefuseEsDTOByWaybillId(waybillId);
        if (signRefuseEsDTOFlag != null && signForDetailsReqDTO.getSignType() == SignStatusEnum.SIGNED.getType()) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "不可重复签收");
        }

        // 查询运单货物明细
        List<WaybillGoodsEsDTO> waybillGoodsEsDTOList = waybillService.queryWaybillGoodsEsDTOList(waybillId);
        signCreateDTO.setWaybillGoodsEsDTOList(waybillGoodsEsDTOList);
        if (waybillGoodsEsDTOList == null || waybillGoodsEsDTOList.size() == 0) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "待签收的运单货物为空，不可签收");
        }

        // 查询运单费用
        WaybillFeeEsDTO waybillFeeEsDTO = new WaybillFeeEsDTO();
        waybillFeeEsDTO.setWaybillId(waybillId);
        List<WaybillFeeEsDTO> waybillFeeEsDTOList = waybillService.queryWaybillFeeEsDTOList(waybillFeeEsDTO);
        signCreateDTO.setWaybillFeeEsDTOList(waybillFeeEsDTOList);
        if (waybillFeeEsDTOList == null || waybillFeeEsDTOList.size() == 0) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "待签收的运单货物费用为空，不可签收");
        }

        // 查询派车单
        DispatcherEsDTO dispatcherEsDTO = getDispatcherEsDTO(signForDetailsReqDTO);
        signCreateDTO.setDispatcherEsDTO(dispatcherEsDTO);

        // 查询派车单明细
        DispatcherDetailEsDTO dispatcherDetailEsDTO = getDispatcherDetailEsDTO(waybillId);
        signCreateDTO.setDispatcherDetailEsDTO(dispatcherDetailEsDTO);

        if (signForDetailsReqDTO.getSignType() != 1) {
            // 非全部签收时，查询拒收单主表
            SignRefuseEsDTO signRefuseEsDTO = esSignRefuseService.getSignRefuseEsDTOByWaybillId(waybillId);
            signCreateDTO.setSignRefuseEsDTO(signRefuseEsDTO);
            if (signRefuseEsDTO == null) {
                throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "拒收单为空，不可签收");
            }
            // 查询拒收单明细
            List<TmsGoodsDetailsEsDTO> refuseSignGoods = esSignRefuseDetailService.queryRefuseSignGoodsEsDTOList(waybillEsDTO.getId());
            signCreateDTO.setRefuseSignGoods(refuseSignGoods);
            if (refuseSignGoods == null || refuseSignGoods.size() == 0) {
                throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "拒收单明细为空，不可签收");
            }
        }
    }

    private DispatcherDetailEsDTO getDispatcherDetailEsDTO(Long waybillId) {
        DispatcherDetailEsDTO dispatcherDetailEsDTO = null;
        try {
            dispatcherDetailEsDTO = dispatcherService.queryDispatcherDetailByWaybillId(waybillId);
        } catch (Exception e) {
            log.error("系统内部调用错误：", e);
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "查询派车单明细出错");
        }
        if (dispatcherDetailEsDTO == null) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "派车单明细为空，不可签收");
        }
        return dispatcherDetailEsDTO;
    }

    private DispatcherEsDTO getDispatcherEsDTO(SignForDetailsReqDTO signForDetailsReqDTO) {
        DispatcherEsDTO dispatcherEsDTO = null;
        try {
            dispatcherEsDTO = dispatcherService.queryDispatcherEsDTO(signForDetailsReqDTO.getDispatcherId());
        } catch (Exception e) {
            log.error("系统内部调用错误：", e);
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "查询派车单出错");
        }
        if (dispatcherEsDTO == null) {
            throw new SystemException(ExceptionCodeConstants.ERROR_ADD_SIGN, "派车单不存在，不可签收");
        }
        return dispatcherEsDTO;
    }

    private void setData(SignForDetailsReqDTO signForDetailsReqDTO, SignCreateDTO signCreateDTO) {
        Long companyId = signForDetailsReqDTO.getCompanyId();
        // 设置签收主表
        signCreateDTO.setSignEsDTO(getSignEsDTO(signForDetailsReqDTO, signCreateDTO, companyId));

        // 设置签收明细表
        signCreateDTO.setSignDetailsEsDTOList(getSignDetailsEsDTO(signCreateDTO.getWaybillGoodsEsDTOList(),
                signCreateDTO.getRefuseSignGoods(), signForDetailsReqDTO, signCreateDTO.getSignEsDTO()));

        // 设置签收费用表
        signCreateDTO.setSignFeeEsDTO(getSignFeeEsDTO(signForDetailsReqDTO, signCreateDTO.getSignEsDTO()));

        // 设置要更新的派车单明细表
        signCreateDTO.setUpdateDispatcherDetailEsDTO(getUpdateDispatcherDetailEsDTO(signCreateDTO.getDispatcherDetailEsDTO(),
                signForDetailsReqDTO, signCreateDTO.getSignEsDTO()));

        // 设置要更新的派车单表
        signCreateDTO.setUpdateDispacherFlag(dispatcherService.checkUpdateDispacher(
                signForDetailsReqDTO.getDispatcherId(), signForDetailsReqDTO.getWaybillId()));
        signCreateDTO.setNewDispatcherEsDTO(getNewDispatcherEsDTO(signCreateDTO.getDispatcherEsDTO(),
                signForDetailsReqDTO, signCreateDTO.getUpdateDispacherFlag()));

        signCreateDTO.setNewSignRefuseEsDTO(new SignRefuseEsDTO());
    }

    private SignEsDTO getSignEsDTO(SignForDetailsReqDTO signForDetailsReqDTO, SignCreateDTO signCreateDTO, Long companyId) {
        WaybillEsDTO waybill = signCreateDTO.getWaybillEsDTO();
        DispatcherEsDTO dispatcher = signCreateDTO.getDispatcherEsDTO();
        List<WaybillFeeEsDTO> waybillFeeEsDTOList = signCreateDTO.getWaybillFeeEsDTOList();
        BigDecimal collectPayment = waybillFeeEsDTOList.stream()
                .filter(i -> i.getAttrId() != null)
                .filter(i -> i.getAttrId().equals(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType()))
                .map(WaybillFeeEsDTO::getFee)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        long now = System.currentTimeMillis();
        Long waybillId = signForDetailsReqDTO.getWaybillId();
        Long signId = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_SIGN);
        DataResult<String> code = idCodeService.generated(new IdCodeReqDTO(BusinessCodeConstants.SIGN_CODE, companyId.toString(), null));

        SignEsDTO signEsDTO = EntityUtils.copyProperties(waybill, SignEsDTO.class);
        signEsDTO.setId(signId);
        signEsDTO.setSignBatchNumber(code.getResult());
        signEsDTO.setCompanyId(companyId);
        signEsDTO.setCreateNumber(waybill.getTotalNum());
        signEsDTO.setUnsignedNumber(0);
        signEsDTO.setSignType(signForDetailsReqDTO.getSignType());
        signEsDTO.setSignTime(now);
        signEsDTO.setWaybillCode(waybill.getCode());
        signEsDTO.setSignPeople(waybill.getReceiptUser());
        signEsDTO.setPhoneNumber(waybill.getReceiptConsignorMobleNo());
        signEsDTO.setDispatcherId(signForDetailsReqDTO.getDispatcherId());
        signEsDTO.setSettlementMethod(signForDetailsReqDTO.getSettlementMethod());
        signEsDTO.setIcashierConfirm(true);
        signEsDTO.setPhoneNo(dispatcher.getPhoneNo());

        // 全部签收
        if (signForDetailsReqDTO.getSignType() == SignStatusEnum.SIGNED.getType()) {
            signEsDTO.setSignStatus(SignStatusEnum.SIGNED.getType());
            signEsDTO.setSignNumber(waybill.getTotalNum());
            signEsDTO.setRefuseNumber(0);
            signEsDTO.setSignWeight(waybill.getWeight());
            signEsDTO.setSignVolume(waybill.getVolume());
            signEsDTO.setTotalReceivable(collectPayment);
        } else if (signForDetailsReqDTO.getSignType() == SignStatusEnum.PARTIAL_SIGN.getType()) {
            // 部分签收
            signEsDTO.setSignStatus(SignStatusEnum.PARTIAL_SIGN.getType());
            // 查询拒签单
            SignRefuseEsDTO signRefuseEsDTO = esSignRefuseService.getSignRefuseEsDTO(waybillId);
            if (signRefuseEsDTO == null) {
                throw new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "拒签单为空，不能签收");
            }
            signEsDTO.setTotalReceivable(signRefuseEsDTO.getOrderPay());
        } else if (signForDetailsReqDTO.getSignType() == SignStatusEnum.REFUSE_SIGN.getType()) {
            // 全部拒签
            signEsDTO.setSignStatus(SignStatusEnum.REFUSE_SIGN.getType());
            signEsDTO.setSignNumber(0);
            signEsDTO.setRefuseNumber(waybill.getTotalNum());
            signEsDTO.setSignWeight(BigDecimal.ZERO);
            signEsDTO.setSignVolume(BigDecimal.ZERO);
            signEsDTO.setTotalReceivable(BigDecimal.ZERO);
        }

        signEsDTO.setCreateTime(now);
        signEsDTO.setCreateUser(signForDetailsReqDTO.getUserId());
        signEsDTO.setUpdateTime(now);
        signEsDTO.setUpdateUser(signForDetailsReqDTO.getUserId());
        return signEsDTO;
    }

    private List<SignDetailsEsDTO> getSignDetailsEsDTO(List<WaybillGoodsEsDTO> waybillGoods,
                                                       List<TmsGoodsDetailsEsDTO> refuseSignGoods,
                                                       SignForDetailsReqDTO signForDetailsReqDTO, SignEsDTO signEsDTO) {
        List<SignDetailsEsDTO> list = new ArrayList<>();
        // 全部签收
        if (signForDetailsReqDTO.getSignType() == 1) {
            list = buildTotalSignDetails(signEsDTO.getId(), waybillGoods, signForDetailsReqDTO);
        }
        // 部分签收
        if (signForDetailsReqDTO.getSignType() == 2) {
            list = buildPartSignDetails(signEsDTO, refuseSignGoods, signForDetailsReqDTO);
        }
        // 全部拒签
        if (signForDetailsReqDTO.getSignType() == 3) {
            list = buildRefuseSignDetails(signEsDTO.getId(), refuseSignGoods, signForDetailsReqDTO);
        }
        return list;

    }

    private List<SignDetailsEsDTO> buildTotalSignDetails(Long signId, List<WaybillGoodsEsDTO> waybillGoods,
                                                         SignForDetailsReqDTO signForDetailsReqDTO) {
        List<SignDetailsEsDTO> list = new ArrayList<>();
        if (null != waybillGoods && waybillGoods.size() > 0) {
            waybillGoods.forEach(item -> {
                Long id = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_SIGN_DETAILS);
                Integer userId = signForDetailsReqDTO.getUserId();
                SignDetailsEsDTO details = EntityUtils.copyProperties(item, SignDetailsEsDTO.class);

                setCreateParameter(details, id, userId);
                details.setSignId(signId);
                // ->运单货物明细里面的库存件数对应此处的件数
                details.setPackageNum(item.getPackageNum());
                details.setDispatcherNumber(item.getPackageNum());
                details.setSignNumber(item.getPackageNum());
                details.setCreateNumber(item.getPackageNum());
                details.setRefuseNumber(0);
                details.setUnsignedNumber(0);
                details.setWaybillStockDetailId(item.getStockDetailId());
                details.setWaybillGoodsId(item.getGoodsId());
                details.setCompanyId(signForDetailsReqDTO.getCompanyId());
                details.setDispatcherDetailId(signForDetailsReqDTO.getDispatcherId());
                details.setWeight(item.getWeight());
                details.setVolume(item.getVolume());
                details.setPackages(item.getPackingName());
                details.setUnit(item.getUnit());
                details.setSkuid(item.getSkuid());
                details.setRemark(item.getRemark());
                details.setProdType(item.getProdType());
                details.setProdTypeName(item.getProdTypeName());
                list.add(details);
            });
        }
        return list;
    }

    private List<SignDetailsEsDTO> buildPartSignDetails(SignEsDTO signEsDTO, List<TmsGoodsDetailsEsDTO> refuseSignGoods,
                                                        SignForDetailsReqDTO signForDetailsReqDTO) {
        List<SignDetailsEsDTO> signDetailsEsDTO = new ArrayList<>();
        if (null != refuseSignGoods && refuseSignGoods.size() > 0) {
            refuseSignGoods.stream()
                    .filter(item-> item.getGoodsType() == SignGoodsTypeEnum.SIGN.getType())
                    .forEach(item -> {
                Long id = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_SIGN_DETAILS);
                Integer userId = signForDetailsReqDTO.getUserId();
                // 拒收信息存储到签收单货物详情中

                // 获得签收数量哥拒签数量
                SignDetailsEsDTO details = EntityUtils.copyProperties(item, SignDetailsEsDTO.class);
                setCreateParameter(details, id, userId);
                details.setSignId(signEsDTO.getId());
                details.setSignNumber(item.getSignNum());
                details.setRefuseNumber(item.getRefuseNum());
                details.setCreateNumber(item.getCreateNum());
                details.setDispatcherNumber(item.getDispacherNum());
                details.setUnsignedNumber(0);
                details.setWeight(item.getRefuseWeight());
                details.setVolume(item.getRefuseVolume());
                details.setPackages(item.getPackages());
                details.setBoxNumber(item.getBoxNum());
                details.setUnit(item.getUnit());
                details.setSkuid(item.getSkuid());
                details.setWaybillGoodsId(item.getWaybillGoodsId());
                details.setRemark(item.getRemark());
                details.setRefuseCause(item.getRefuseCause());
                signDetailsEsDTO.add(details);
            });
            int refuseNumber, signNumber;
            // TODO 计算拒收货物总件数
            refuseNumber = refuseSignGoods.stream()
                    .filter(item -> item.getRefuseNum() != null
                            && item.getGoodsType() == SignGoodsTypeEnum.REFUSE_SIGN.getType())
                    .mapToInt(TmsGoodsDetailsEsDTO::getRefuseNum).sum();
            // TODO 计算签收货物总件数
            signNumber = refuseSignGoods.stream()
                    .filter(item -> item.getSignNum() != null
                            && item.getGoodsType() == SignGoodsTypeEnum.SIGN.getType())
                    .mapToInt(TmsGoodsDetailsEsDTO::getSignNum).sum();
            // TODO 计算签收货物总重量
            BigDecimal totalWeight = refuseSignGoods.stream()
                    .filter(item -> item.getRefuseWeight() != null
                            && item.getGoodsType() == SignGoodsTypeEnum.SIGN.getType())
                    .map(TmsGoodsDetailsEsDTO::getRefuseWeight)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            // TODO 计算签收货物总体积
            BigDecimal totalVolume = refuseSignGoods.stream()
                    .filter(item -> item.getRefuseVolume() != null
                            && item.getGoodsType() == SignGoodsTypeEnum.SIGN.getType())
                    .map(TmsGoodsDetailsEsDTO::getRefuseVolume)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            // TODO 回写签收单主表
            signEsDTO.setSignNumber(signNumber);
            signEsDTO.setRefuseNumber(refuseNumber);
            signEsDTO.setSignWeight(totalWeight);
            signEsDTO.setSignVolume(totalVolume);
        }
        return signDetailsEsDTO;
    }

    private List<SignDetailsEsDTO> buildRefuseSignDetails(Long signId, List<TmsGoodsDetailsEsDTO> refuseSignGoods,
                                                          SignForDetailsReqDTO signForDetailsReqDTO) {
        List<SignDetailsEsDTO> list = new ArrayList<>();
        if (null != refuseSignGoods && refuseSignGoods.size() > 0) {
            refuseSignGoods.stream()
                .filter(item -> item.getGoodsType() == SignGoodsTypeEnum.REFUSE_SIGN.getType())
                .forEach(item -> {
                    Long id = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_SIGN_DETAILS);
                    Integer userId = signForDetailsReqDTO.getUserId();
                    // 拒收信息存储到签收单货物详情中

                    // 获得签收数量哥拒签数量
                    SignDetailsEsDTO details = EntityUtils.copyProperties(item, SignDetailsEsDTO.class);
                    setCreateParameter(details, id, userId);
                    details.setSignId(signId);
                    details.setSignNumber(item.getSignNum());
                    details.setRefuseNumber(item.getRefuseNum());
                    details.setCreateNumber(item.getCreateNum());
                    details.setDispatcherNumber(item.getDispacherNum());
                    details.setUnsignedNumber(0);
                    details.setWeight(item.getRefuseWeight());
                    details.setVolume(item.getRefuseVolume());
                    details.setPackages(item.getPackages());
                    details.setBoxNumber(item.getBoxNum());
                    details.setUnit(item.getUnit());
                    details.setSkuid(item.getSkuid());
                    details.setWaybillGoodsId(item.getWaybillGoodsId());
                    details.setRemark(item.getRemark());
                    details.setRefuseCause(item.getRefuseCause());
                    list.add(details);
                });
        }
        return list;
    }

    private SignFeeEsDTO getSignFeeEsDTO(SignForDetailsReqDTO signForDetailsReqDTO, SignEsDTO signEsDTO) {
        // ->查询费用项
        SignFeeEsDTO fee = new SignFeeEsDTO();
        long now = System.currentTimeMillis();
        Long id = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_SIGN_FEE);
        Integer userId = signForDetailsReqDTO.getUserId();

        fee.setId(id);
        fee.setSignId(signEsDTO.getId());
        fee.setCompanyId(signForDetailsReqDTO.getCompanyId());
        //查询 waybillfee
        fee.setCollectPayment(getCollectPayment(signForDetailsReqDTO.getWaybillId()));
        fee.setSettlementMethod(signForDetailsReqDTO.getSettlementMethod());
        if (signEsDTO.getCod() != null) {
            fee.setToPay(signEsDTO.getCod());
        }
        fee.setCreateUser(userId);
        fee.setUpdateUser(userId);
        fee.setCreateTime(now);
        fee.setUpdateTime(now);
        return fee;

    }

    private BigDecimal getCollectPayment(Long waybillId) {
        WaybillFeeEsDTO feeEsDTO = new WaybillFeeEsDTO();
        feeEsDTO.setAttrId(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType());
        feeEsDTO.setWaybillId(waybillId);

        List<WaybillFeeEsDTO> feeEsDTOList = waybillService.queryWaybillFeeEsDTOList(feeEsDTO);
        if (CollectionUtils.isNotEmpty(feeEsDTOList)) {
            WaybillFeeEsDTO waybillFeeEsDTO = feeEsDTOList.get(0);
            return waybillFeeEsDTO.getFee();
        } else {
            return BigDecimal.ZERO;
        }
    }

    private DispatcherDetailEsDTO getUpdateDispatcherDetailEsDTO(DispatcherDetailEsDTO dispatcherDetailEs,
                                                                 SignForDetailsReqDTO signForDetailsReqDTO, SignEsDTO signEsDTO) {
        DispatcherDetailEsDTO updateDispatcherDetailEsDTO = new DispatcherDetailEsDTO();
        updateDispatcherDetailEsDTO.setId(dispatcherDetailEs.getId());
        updateDispatcherDetailEsDTO.setCompanyId(signForDetailsReqDTO.getCompanyId());
        updateDispatcherDetailEsDTO.setStatus(DispatcherDetailStatusEnum.SIGNED.getType());
        updateDispatcherDetailEsDTO.setWaybillStatus(WaybillStatusEnum.SIGNED.getType());
        // TODO 改变派车单明细中的运单状态
        updateDispatcherDetailEsDTO.setUpdateTime(System.currentTimeMillis());
        // TODO 派车单明细中 签收单相关赋值
        updateDispatcherDetailEsDTO.setSignId(signEsDTO.getId());
        updateDispatcherDetailEsDTO.setSignBatchNumber(signEsDTO.getSignBatchNumber());
        updateDispatcherDetailEsDTO.setSignType(signForDetailsReqDTO.getSignType());
        updateDispatcherDetailEsDTO.setSignStatus(signForDetailsReqDTO.getSignType());
        updateDispatcherDetailEsDTO.setSignStatusName(SignStatusEnum.getName(signForDetailsReqDTO.getSignType()));
        updateDispatcherDetailEsDTO.setSignTime(signEsDTO.getSignTime());
        updateDispatcherDetailEsDTO.setUpdateUser(signForDetailsReqDTO.getUserId());
        updateDispatcherDetailEsDTO.setUpdateTime(System.currentTimeMillis());

        if (signForDetailsReqDTO.getUserId() != null) {
            updateDispatcherDetailEsDTO.setUpdateUser(signForDetailsReqDTO.getUserId());
        }
        return updateDispatcherDetailEsDTO;
    }

    private void updateWayBillStatusById(SignForDetailsReqDTO signForDetailsReqDTO, SignEsDTO goods,
                                         BigDecimal deliveryCharge, DispatcherDetailEsDTO dispatcherDetailEsDTO) {
        int waybillStatus = WaybillStatusEnum.SIGNED.getType();
        SyncWaybillStatusToOmsReq req = new SyncWaybillStatusToOmsReq();
        req.setWaybillId(signForDetailsReqDTO.getWaybillId());
        req.setWaybillStatus(waybillStatus);
        if (goods != null && goods.getSignPic() != null) {
            req.setSignPic(goods.getSignPic());
        }
        if (goods != null && goods.getSignTime() != null) {
            req.setSignTime(goods.getSignTime());
        }
        req.setSignStatus(signForDetailsReqDTO.getSignType());
        req.setSignStatusName(SignStatusEnum.getName(signForDetailsReqDTO.getSignType()));
        if (deliveryCharge != null) {
            req.setDeliveryCharge(deliveryCharge);// 代收货款手续费
        }
        if (goods.getTotalReceivable() != null) {
            req.setGoodsPayment(goods.getTotalReceivable());// 实收代收货款
        }
        if (dispatcherDetailEsDTO != null && dispatcherDetailEsDTO.getDriverName() != null) {
            req.setOpUser(dispatcherDetailEsDTO.getDriverName());
        } else {
            req.setOpUser("系统");
        }
        req.setSettlementMethod(signForDetailsReqDTO.getSettlementMethod());// 支付渠道
        req.setSettlementMethodName(SettlementModeEnum.getName(signForDetailsReqDTO.getSettlementMethod()));// 支付渠道名称
        req.setTotalSignNumber(goods.getSignNumber());
        waybillService.syncWaybillStatusToOms(req);

    }

    private DispatcherEsDTO getNewDispatcherEsDTO(DispatcherEsDTO dispatcherEsDTO, SignForDetailsReqDTO signForDetailsReqDTO,
                                                  Boolean flag) {
        DispatcherEsDTO newDispatcherEsDTO = new DispatcherEsDTO();
        newDispatcherEsDTO.setCompanyId(signForDetailsReqDTO.getCompanyId());
        newDispatcherEsDTO.setId(dispatcherEsDTO.getId());
        if (flag) {
            newDispatcherEsDTO.setStatus(DispatcherStatusEnum.SIGNED.getType());
        }
        newDispatcherEsDTO.setSignNum(dispatcherEsDTO.getSignNum() + 1);
        newDispatcherEsDTO.setUnSignNum(dispatcherEsDTO.getUnSignNum() - 1);
        return newDispatcherEsDTO;
    }

    private Long saveReceiptRecord(SignForDetailsReqDTO signForDetailsReqDTO, WaybillEsDTO waybillEsDTO,
                                   SignEsDTO signEsDTO) {
        Long receiptId = 0L;
        if (waybillEsDTO.getOrderType() != null && waybillEsDTO.getOrderType() == OrderTypeEnum.CANCEL_ORDER.getCode()) {
            // TODO 订单为销退订单，不生成收款单
            return receiptId;
        }
        if (BigDecimal.ZERO.compareTo(signEsDTO.getTotalReceivable()) == 0) {
            // TODO 金额为0，不生成收款单
            return receiptId;
        }
        FinanceReceiptCreateDTO financeReceiptCreateDTO = new FinanceReceiptCreateDTO();
        try {
            financeReceiptCreateDTO.setUserId(signForDetailsReqDTO.getUserId());
            financeReceiptCreateDTO.setCompanyId(signForDetailsReqDTO.getCompanyId());
            financeReceiptCreateDTO.setSignEsDTO(signEsDTO);
            financeReceiptCreateDTO.setWaybillId(waybillEsDTO.getId());
            financeReceiptCreateDTO.setSettlementMode(signForDetailsReqDTO.getSettlementMethod());
           receiptId = financeReceiptService.saveReceipt(financeReceiptCreateDTO);
        } catch (SystemException e) {
            log.error(e.getErrorReason(), e);
//            throw new SystemException(e.getErrorCode(), e.getErrorReason());
        }
        return receiptId;
    }

    private boolean checkQRCodePayed(SignForDetailsReqDTO signForDetailsReqDTO) {
        // TODO 若是二维码支付，则需验证是否支付成功；否则，直接放行
        if (signForDetailsReqDTO.getSettlementMethod() == SettlementModeEnum.QR_CODE.getType()) {
//            return signPaymentService.isPaySuccess(signForDetailsReqDTO.getWaybillId());
            // TODO 测试阶段，允许未支付也可签收
            return true;
        } else {
            return true;
        }
    }

    private boolean checkUpdateDispacher(SignForDetailsReqDTO signForDetailsReqDTO) {
        Long dispatcherId = signForDetailsReqDTO.getDispatcherId();
        // 根据派车单id查到派车单明细
        List<DispatcherDetailEsDTO> dispatcherDetailList = dispatcherService.queryDispatcherDetailByDispatcherId(dispatcherId);

        // 遍历dis 查出运单状态
        boolean allSigned = true;
        for (DispatcherDetailEsDTO dispatcherDetailEsDTO : dispatcherDetailList) {
            // 判断当前运单状态
            if (dispatcherDetailEsDTO.getWaybillId().equals(signForDetailsReqDTO.getWaybillId())) {
                continue;
            }

            // 派车单明细中运单状态是否签收或者取消
            if (dispatcherDetailEsDTO.getWaybillStatus() != WaybillStatusEnum.SIGNED.getType() &&
                    dispatcherDetailEsDTO.getWaybillStatus() != WaybillStatusEnum.HANDOVERCANCEL.getType()) {
                allSigned = false;
                break;
            }
        }
        return allSigned;
    }

    private void setCreateParameter(SignDetailsEsDTO details, Long id, Integer userId) {
        long now = System.currentTimeMillis();
        details.setId(id);
        details.setCreateUser(userId);
        details.setUpdateUser(userId);
        details.setCreateTime(now);
        details.setUpdateTime(now);
    }

    private TransactionManagerUtils.TransactionProxy getTransaction(Long companyId) {
        return dtmFactory.create()
                .addTransManager(SignMapper.class, companyId)
                .addTransManager(SignFeeMapper.class, companyId)
                .addTransManager(SignDetailDOMapper.class, companyId)
                .build();
    }


    @Override
    public DataResult<Boolean> reCommitToOMS(Long waybillId, Integer commitType) {
        WaybillEsDTO waybillEsDTO = waybillService.queryWaybillEsDTO(waybillId);
        SignEsDTO signEsDTO = esSignService.querySignEsDTO(waybillId);
        //新加的
        SignFeeEsDTO signFeeEsDTO =esSignService.querySignFeeEsDTO(signEsDTO.getId());
        SignForDetailsReqDTO signForDetailsReqDTO = new SignForDetailsReqDTO();


        signForDetailsReqDTO.setSignType(signEsDTO.getSignType());
        signForDetailsReqDTO.setSettlementMethod(signEsDTO.getSettlementMethod());

        giveSignServiceForRPC.sendResultToOMS(signFeeEsDTO, signEsDTO, signForDetailsReqDTO, waybillEsDTO);

        return null;
    }
}
