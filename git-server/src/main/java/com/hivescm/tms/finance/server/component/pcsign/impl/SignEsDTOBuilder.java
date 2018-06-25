package com.hivescm.tms.finance.server.component.pcsign.impl;

import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.DeliverySignBatchReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SelfSignBatchReqDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.enums.biz.sign.NormalSignType;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;

import java.math.BigDecimal;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/4/3
 */
public class SignEsDTOBuilder {

    private SignEsDTO sign;

    public SignEsDTO build() {
        return sign;
    }




    public SignEsDTOBuilder() {
        sign = new SignEsDTO();
    }



    /**
     * 通过 运单 构建  自提签收单
     * @param waybillEsDTO
     * @return
     */
    public SignEsDTOBuilder waybill(WaybillEsDTO waybillEsDTO) {


        return this;
    }
    public SignEsDTOBuilder id(Long signId) {

        sign.setId(signId);
        return this;

    }
    public SignEsDTOBuilder totalReceivable(BigDecimal totalReceivable){

        sign.setTotalReceivable(totalReceivable);
        return this;
    }

//    public SignEsDTOBuilder signBatchNumber(String signBatchNumber){
//        sign.setSignBatchNumber(signBatchNumber);
//        return this;
//    }


    public SignEsDTOBuilder signNumber(Integer signNumber){
        sign.setSignNumber(signNumber);
        return this;
    }


    public SignEsDTOBuilder signBatchNumber(String signBatchNumber){
        sign.setSignBatchNumber(signBatchNumber);
        return this;
    }

    public SignEsDTOBuilder selfSignBatchReqDTO(SelfSignBatchReqDTO req){
        sign.setHandler(req.getHandler());
        sign.setHandlerName(req.getHandlerName());
        sign.setCreateUser(req.getHandler());

        return this;
    }
    public SignEsDTOBuilder deliverySignBatchReqDTO(DeliverySignBatchReqDTO req){
        sign.setHandler(req.getHandler());
        sign.setHandlerName(req.getHandlerName());
        sign.setCreateUser(req.getHandler());
        sign.setCreateUserName(req.getHandlerName());
        sign.setSignOrgId(req.getOrgId().intValue());
        sign.setSignOrgName(req.getOrgName());
        return this;
    }




    public SignEsDTOBuilder signFeeEsDTO(SignFeeEsDTO fee){
//        todo
        BigDecimal bigDecimal = new BigDecimal("0");

        bigDecimal.add(fee.getCollectPayment()==null?BigDecimal.ZERO:fee.getCollectPayment());
        bigDecimal.add(fee.getToPay()==null?BigDecimal.ZERO:fee.getToPay());
        bigDecimal.add(fee.getDeliveryFee()==null?BigDecimal.ZERO:fee.getDeliveryFee());
        bigDecimal.add(fee.getSecondDeliveryFee()==null?BigDecimal.ZERO:fee.getSecondDeliveryFee());
        bigDecimal.add(fee.getOtherFeeStation()==null?BigDecimal.ZERO:fee.getOtherFeeStation());

        sign.setTotalReceivable(bigDecimal);
//        sign.setTotalReceivable(fee.getCollectPayment().add(fee.getToPay()).add(fee.getDeliveryFee()).add(fee.getSecondDeliveryFee()).add(fee.getOtherFeeStation()));

        return this;
    }


    /**
     * 通过 派车单明细 构建  送货签收单
     * @param dispatcherDetail
     * @return
     */
    public SignEsDTOBuilder dispatcherDetail(DispatcherDetailEsDTO dispatcherDetail) {

        sign.acceptDispatcherDetail(dispatcherDetail);

//        sign.setCompanyId(dispatcherDetail.getCompanyId());
////        sign.setDispatcherDetailId(dispatcherDetail.getId());
////        sign.setDispatcherId(dispatcherDetail.getDispatcherId());
////
////        sign.setCreateNumber(dispatcherDetail.getTotalNum());
////        sign.setWaybillId(dispatcherDetail.getWaybillId());
        sign.setSignPeople(dispatcherDetail.getReceiptUser());
////
////        sign.setPhoneNumber(dispatcherDetail.getReceiptConsignorMobleNo());
//////        sign.setCod(dispatcherDetail.get);
////        sign.setDeliveryType(WaybillDistributionTypeEnum.DELIVERY.getType());
////        sign.setDeliveryTypeName(WaybillDistributionTypeEnum.DELIVERY.getName());
////
        sign.setSignNumber(dispatcherDetail.getPackageNum());
////        sign.setCode(dispatcherDetail.getCode());
////        sign.setWaybillCode(dispatcherDetail.getOrderCode());
        sign.setRefuseNumber(0);




        return this;
    }


    public SignEsDTOBuilder init(){

        sign.setNormalSignType(NormalSignType.NORMAL_SIGN.getType());
        sign.setNormalSignTypeName(NormalSignType.NORMAL_SIGN.getName());
        sign.setSignStatus(SignStatusEnum.SIGNED.getType());
        sign.setSignStatusName(SignStatusEnum.SIGNED.getName());
        sign.setSignType(SignStatusEnum.SIGNED.getType());
        sign.setIreceiver(true);
        sign.setIcashierConfirm(false);
        sign.setCreateTime(System.currentTimeMillis());
        sign.setSignTime(sign.getCreateTime());
        sign.setIdeliveryFailure(false);

        return this;
    }

}
