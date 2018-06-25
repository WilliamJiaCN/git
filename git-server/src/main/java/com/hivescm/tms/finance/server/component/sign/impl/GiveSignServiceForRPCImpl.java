package com.hivescm.tms.finance.server.component.sign.impl;

import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.bossfreight.BillingCalculateRep;
import com.hivescm.tms.api.dto.bossfreight.BillingRecordReq;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignForDetailsReqDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.enums.biz.waybill.changedistribution.WaybillChangeDispatcherTypeEnum;
import com.hivescm.tms.api.enums.capacity.order.OrderTypeEnum;
import com.hivescm.tms.finance.server.component.sign.GiveSignServiceForRPC;
import com.hivescm.tms.finance.server.feign.waybill.WaybillForBossSerivce;
import com.hivescm.tms.intranet.gateway.api.dto.oms.OrderSignOMSDto;
import com.hivescm.tms.intranet.gateway.api.feign.oms.IOmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 送货签收远程调用
 * @author 杨彭伟
 * @date 2018-01-05 20:11
 */
@Slf4j
@Service
public class GiveSignServiceForRPCImpl implements GiveSignServiceForRPC {
    @Autowired
    private IOmsService omsService;
    @Autowired
    private WaybillForBossSerivce waybillInfoToBossBillService;

    /**
     * 签收单通知给OMS
     * @param fee
     * @param signEsDTO
     * @param signForDetailsReqDTO
     * @param waybill
     */
    @Override
    public void sendResultToOMS(SignFeeEsDTO fee, SignEsDTO signEsDTO, SignForDetailsReqDTO signForDetailsReqDTO,
                                WaybillEsDTO waybill) {
        // TODO 若是销退单 不通知OMS
        if (waybill.getOrderType().equals(OrderTypeEnum.CANCEL_ORDER.getCode())) {
            return;
        }
        //改配返仓单  也不通知给OMS
        if (waybill.getChangeDispatcherType()!=null
                &&waybill.getChangeDispatcherType().intValue()== WaybillChangeDispatcherTypeEnum.RETURNWAREHOUSE.getType()){
            return;
        }


        // 通知oms
        OrderSignOMSDto orderSignDto = new OrderSignOMSDto();
        if (fee == null || fee.getCollectPayment() == null) {
            orderSignDto.setRealAmount(BigDecimal.ZERO);
        } else {
            orderSignDto.setRealAmount(fee.getCollectPayment());
        }

        orderSignDto.setExternalOrderId(waybill.getOrderCode());
        orderSignDto.setExternalOrderNo(waybill.getOrderCode());

        orderSignDto.setOrderSignUrl(signEsDTO.getSignPic());

        /**
         * WaybillChangeDispatcherTypeEnum
         */
        if (waybill.getChangeDispatcherType()!=null&&waybill.getChangeDispatcherType()!=0){

            orderSignDto.setChangeDeliveryFlag("1");
        }else {
            orderSignDto.setChangeDeliveryFlag("0");
        }

        orderSignDto.setWarehouseId(waybill.getWarehouseId());
        orderSignDto.setSourceSystem(1);
        orderSignDto.setSignStatus(signForDetailsReqDTO.getSignType());
        orderSignDto.setSignTime(signEsDTO.getCreateTime());
        orderSignDto.setPayChannel(signForDetailsReqDTO.getSettlementMethod());
        orderSignDto.setPayStatus(1);
        orderSignDto.setDeliveryCompanyDeptId(waybill.getCarrierId());
        orderSignDto.setDeliveryCompanyDeptName(waybill.getCarrierName());
        orderSignDto.setSignUserName(waybill.getReceiptUser());
        // todo       orderSignDto.setDeliveryCompanyDeptName(waybill.getCarrierName()); 不传
        orderSignDto.setDeliveryCompanySalesman(waybill.getSalesmanName());
        orderSignDto.setDeliveryCompanySalesmanPhone("18942137758");
        // todo
        // 调用接口
        omsService.postSignBill(orderSignDto);
    }

    /**
     * 计算代收货款手续费,并通知给BOSS
     * @param waybillEsDTO 运单id
     * @param totalReceivable 实收代收货款
     */
    @Override
    public BillingRecordReq getDeliveryCharge(WaybillEsDTO waybillEsDTO, BigDecimal totalReceivable) {
        BigDecimal deliveryCharge = BigDecimal.ZERO;
        BigDecimal deliveryChargeNoTax = BigDecimal.ZERO;
        BigDecimal taxOfDeliveryCharge = BigDecimal.ZERO;
        BillingRecordReq req = new BillingRecordReq();
        // TODO 若是销退单 无需计算手续费
        // TODO 若是代收货款为0 无需计算手续费
        if (waybillEsDTO.getOrderType().equals(OrderTypeEnum.SALE_ORDER.getCode())
                && BigDecimal.ZERO.compareTo(totalReceivable) < 0) {
            BillingCalculateRep billingCalculateRep = waybillInfoToBossBillService.calculateDeliveryCharge(waybillEsDTO.getId(),
                    totalReceivable);
            if (billingCalculateRep != null) {
                deliveryCharge = billingCalculateRep.getDeliveryCharge();
                deliveryChargeNoTax = billingCalculateRep.getDeliveryChargeNoTax();
                taxOfDeliveryCharge = billingCalculateRep.getTaxOfDeliveryCharge();
                EntityUtils.copyProperties(billingCalculateRep, req);
            }
        }
        //计费请求体
        
        req.setWaybillId(waybillEsDTO.getId());
        req.setDeliveryCharge(deliveryCharge);
        req.setDeliveryChargeNoTax(deliveryChargeNoTax);
        req.setTaxOfDeliveryCharge(taxOfDeliveryCharge);
        req.setGoodsPayment(totalReceivable);

        return req;
    }

    @Override
    public void sendWaybillInfoToBoss(BillingRecordReq req) {
        waybillInfoToBossBillService.sendWaybillInfoToBoss(req);
    }
}
