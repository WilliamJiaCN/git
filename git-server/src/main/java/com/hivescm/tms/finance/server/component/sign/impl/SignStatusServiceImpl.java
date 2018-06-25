package com.hivescm.tms.finance.server.component.sign.impl;

import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.ConditionExpressionEnum;
import com.hivescm.escenter.service.ESSearchService;
import com.hivescm.framework.elasticsearch.executor.impl.DefaultAbstractSearchQueryExecutor;
import com.hivescm.framework.elasticsearch.utils.SearchConditionUtils;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignStatusRespDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import com.hivescm.tms.api.enums.biz.sign.SignLifeCycleStatusEnum;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillFeeTypeEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillPayWayEnum;
import com.hivescm.tms.finance.server.component.sign.SignStatusService;
import com.hivescm.tms.finance.server.config.EsConfig;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * 查询签收的生命周期状态
 *
 * @author m5itao
 * @since 2017/12/4
 */
@Service
public class SignStatusServiceImpl implements SignStatusService {
    @Autowired
    private ESSearchService esSearchService;

    @Autowired
    private WaybillService waybillService;

    /**
     * 1、如果有签收单，则已签收
     * 2、没有签收单则查询拒收单，没有拒收单，则待签收
     * 3、没有金额，则待审核
     * 4、有金额，则待支付
     *
     * @param waybillId
     * @return
     */
    @Override
    public SignStatusRespDTO getSignStatus(Long waybillId) {
        if (waybillId == null || waybillId == 0L)
            throw new IllegalArgumentException("参数为空");

        //签收状态返回结果
        SignStatusRespDTO signStatusRespDTO = new SignStatusRespDTO();
        //查询运单
        WaybillEsDTO waybillEsDTO = waybillService.queryWaybillEsDTO(waybillId);
        if (waybillEsDTO == null) {
            throw new IllegalArgumentException("运单信息为空");
        }
        //查询签收单
        List<SearchCondition> signSearchConditions = SearchConditionUtils.start()
                .addCondition("waybillId", waybillId, ConditionExpressionEnum.EQUAL)
                .end();

        SignEsDTO signEsDTO = new DefaultAbstractSearchQueryExecutor<SignEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.sign();
            }
        }.get(signSearchConditions);

        if (signEsDTO != null) {
//
//            //todo
//
            signStatusRespDTO.setStatus(SignLifeCycleStatusEnum.SIGNED.getType());
//            signStatusRespDTO.setStatus(signEsDTO.getSignStatus());
//            return signStatusRespDTO;
        }

//        if (signEsDTO!=null&&SignLifeCycleStatusEnum.SIGNED.getType()==signEsDTO.getSignStatus().intValue()){
//            signStatusRespDTO.setStatus(signEsDTO.getSignStatus());
//            return signStatusRespDTO;
//        }

        //查询拒收单
        List<SearchCondition> searchConditions = SearchConditionUtils.start()
                .addCondition("waybillId", waybillId, ConditionExpressionEnum.EQUAL)
                .addCondition("refuseType", SignStatusEnum.CANCEL_SIGN.getType(), ConditionExpressionEnum.UNEQUAL)
                .end();

        SignRefuseEsDTO signRefuseEsDTO = new DefaultAbstractSearchQueryExecutor<SignRefuseEsDTO>(esSearchService) {
            @Override
            public EsConfig getConfig() {
                return EsConfig.refuseSign();
            }
        }.get(searchConditions);

        //未签收
        if (signRefuseEsDTO == null) {
            signStatusRespDTO.setStatus(SignLifeCycleStatusEnum.UNSIGNE.getType());
            return signStatusRespDTO;
        }


        //上次签收的状态
        if (signRefuseEsDTO.getRefusePay() == null) {
            signStatusRespDTO.setStatus(SignLifeCycleStatusEnum.UNAUDIT.getType());//待审核
        } else {
            signStatusRespDTO.setStatus(SignLifeCycleStatusEnum.UNPAY.getType());//待支付
        }
        signStatusRespDTO.setDispatcherId(signRefuseEsDTO.getDispatcherId());//派车单ID
        signStatusRespDTO.setWaybillId(signRefuseEsDTO.getWaybillId());//运单ID
        signStatusRespDTO.setSignType(signRefuseEsDTO.getRefuseType());//签收方式
        signStatusRespDTO.setPaymentMethod(signRefuseEsDTO.getSettlementMethod());//支付方式
        signStatusRespDTO.setSignAmount(signRefuseEsDTO.getOrderPay());//签收金额
        signStatusRespDTO.setRejectAmount(signRefuseEsDTO.getRefusePay());//拒收金额
        signStatusRespDTO.setReceiptUser(waybillEsDTO.getReceiptUser());//收货人
        signStatusRespDTO.setReceiptConsignorMobleNo(waybillEsDTO.getReceiptConsignorMobleNo());//电话
        signStatusRespDTO.setCode(waybillEsDTO.getCode());//运单        
        //如果是到付
        if (waybillEsDTO.getPayWay().equals(WaybillPayWayEnum.topay.getType())) {
            //运费
            signStatusRespDTO.setTotalFee(waybillEsDTO.getTotalFee());
        } else {
            //运费
            signStatusRespDTO.setTotalFee(BigDecimal.ZERO);
        }
        signStatusRespDTO.setPayWay(waybillEsDTO.getPayWay());
        signStatusRespDTO.setOrderCode(waybillEsDTO.getOrderCode());


        //todo  付款方式为到付的  总费用等于代收货款加运费   一期默认总费用为代收货款
        WaybillFeeEsDTO waybillFeeEsDTO1 = new WaybillFeeEsDTO();
        waybillFeeEsDTO1.setWaybillId(waybillId);
        waybillFeeEsDTO1.setAttrId(WaybillFeeTypeEnum.COLLECT_PAYMENT.getType());
        List<WaybillFeeEsDTO> listfee = waybillService.queryWaybillFeeEsDTOList(waybillFeeEsDTO1);

        if (CollectionUtils.isNotEmpty(listfee)) {
            listfee.forEach(waybillFeeEsDTO -> {
                if (Objects.equals(waybillFeeEsDTO.getAttrId(), WaybillFeeTypeEnum.COLLECT_PAYMENT.getType())) {

                    signStatusRespDTO.setCollectPayment(waybillFeeEsDTO.getFee());
                }
            });
        }

        return signStatusRespDTO;
    }

}
